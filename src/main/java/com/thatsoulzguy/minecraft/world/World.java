package com.thatsoulzguy.minecraft.world;

import com.thatsoulzguy.minecraft.rendering.Camera;
import org.joml.Vector3f;

import java.util.ArrayList;

public class World
{
    public static Camera camera;

    public static int CHUNK_RADIUS = 1;
    public static Chunk[][] chunks = new Chunk[CHUNK_RADIUS][CHUNK_RADIUS];

    public static void Initialize()
    {
        for(int x = 0; x < CHUNK_RADIUS; x++)
        {
            for(int z = 0; z < CHUNK_RADIUS; z++)
            {
                chunks[x][z] = (Chunk.Register(new Vector3f(x * Chunk.CHUNK_SIZE, 0, z * Chunk.CHUNK_SIZE), String.format("Chunk [%d, %d]", x, z)));
            }
        }

        RebuildChunks();
    }

    public static void RebuildChunks()
    {
        for (int x = 0; x < CHUNK_RADIUS; x++)
        {
            for (int z = 0; z < CHUNK_RADIUS; z++)
            {
                chunks[x][z].sides.renderLeft = (x == 0 || chunks[x - 1][z] == null);

                chunks[x][z].sides.renderRight = (x >= CHUNK_RADIUS - 1 || chunks[x + 1][z] == null);

                chunks[x][z].sides.renderFront = (z >= CHUNK_RADIUS - 1 || chunks[x][z + 1] == null);

                chunks[x][z].sides.renderBack = (z == 0 || chunks[x][z - 1] == null);

                chunks[x][z].sides.renderTop = true;
                chunks[x][z].sides.renderBottom = true;

                chunks[x][z].Rebuild();
            }
        }

        for (int x = 0; x < CHUNK_RADIUS; x++)
        {
            for (int z = 0; z < CHUNK_RADIUS; z++)
            {
                chunks[x][z].Rebuild();
            }
        }
    }

    public static void Register(Camera _camera)
    {
        camera = _camera;

        Initialize();
    }
}