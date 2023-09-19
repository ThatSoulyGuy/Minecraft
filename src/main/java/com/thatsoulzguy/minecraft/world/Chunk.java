package com.thatsoulzguy.minecraft.world;

import com.thatsoulzguy.minecraft.block.Block;
import com.thatsoulzguy.minecraft.block.BlockType;
import com.thatsoulzguy.minecraft.block.blocks.GrassBlock;
import com.thatsoulzguy.minecraft.math.Transform;
import com.thatsoulzguy.minecraft.rendering.RenderableObject;
import com.thatsoulzguy.minecraft.rendering.Renderer;
import com.thatsoulzguy.minecraft.rendering.Texture;
import com.thatsoulzguy.minecraft.rendering.Vertex;
import com.thatsoulzguy.minecraft.util.NameIDTag;
import com.thatsoulzguy.minecraft.util.TextureManager;
import org.joml.*;

import java.util.ArrayList;
import java.util.Random;

public class Chunk
{
    public static final int CHUNK_SIZE = 16;
    public RenderableObject mesh;
    public Block[][][] blocks = new Block[CHUNK_SIZE][CHUNK_SIZE][CHUNK_SIZE];
    public ArrayList<Vertex> vertices = new ArrayList<>();
    public ArrayList<Integer> indices = new ArrayList<>();
    public Transform transform;
    public ChunkSides sides = new ChunkSides();

    public int indiceIndex = 0;
    public NameIDTag name = NameIDTag.Register("chunk" + new Random().nextInt(), "A Terrain Chunk", this);

    public void Initialize()
    {
        mesh = new RenderableObject();
        mesh.AddTexture(Texture.Register("/textures/atlases/terrain.png"));
        mesh.RegisterValues(name, vertices, indices, "default");

        for(int x = 0; x < CHUNK_SIZE; x++)
        {
            for(int y = 0; y < CHUNK_SIZE; y++)
            {
                for(int z = 0; z < CHUNK_SIZE; z++)
                    blocks[x][y][z] = new GrassBlock();
            }
        }

        Rebuild();

        Renderer.RegisterRenderableObject(mesh);
    }

    public void Rebuild()
    {
        vertices.clear();
        indices.clear();
        indiceIndex = 0;

        for(int x = 0; x < CHUNK_SIZE; x++)
        {
            for(int y = 0; y < CHUNK_SIZE; y++)
            {
                for(int z = 0; z < CHUNK_SIZE; z++)
                {
                    if(y + 1 >= CHUNK_SIZE || blocks[x][y + 1][z] == BlockType.AIR && sides.renderTop)
                        GenerateTopFace(new Vector3f(x, y, z), TextureManager.GetTextureCoordinates(blocks[x][y][z].data.uvPositions[0]));

                    if(y - 1 < 0 || blocks[x][y - 1][z] == BlockType.AIR && sides.renderBottom)
                        GenerateBottomFace(new Vector3f(x, y, z), TextureManager.GetTextureCoordinates(blocks[x][y][z].data.uvPositions[1]));

                    if(z + 1 >= CHUNK_SIZE || blocks[x][y][z + 1] == BlockType.AIR && sides.renderFront)
                        GenerateFrontFace(new Vector3f(x, y, z), TextureManager.GetTextureCoordinatesRotated(blocks[x][y][z].data.uvPositions[2]));

                    if(z - 1 < 0 || blocks[x][y][z - 1] == BlockType.AIR && sides.renderBack)
                        GenerateBackFace(new Vector3f(x, y, z), TextureManager.GetTextureCoordinatesRotated(blocks[x][y][z].data.uvPositions[3]));

                    if(x + 1 >= CHUNK_SIZE || blocks[x + 1][y][z] == BlockType.AIR && sides.renderRight)
                        GenerateRightFace(new Vector3f(x, y, z), TextureManager.GetTextureCoordinatesRotated(blocks[x][y][z].data.uvPositions[4]));

                    if(x - 1 < 0 || blocks[x - 1][y][z] == BlockType.AIR && sides.renderLeft)
                        GenerateLeftFace(new Vector3f(x, y, z), TextureManager.GetTextureCoordinatesRotated(blocks[x][y][z].data.uvPositions[5]));
                }
            }
        }

        mesh.ReRegisterValues(vertices, indices);
        mesh.GenerateRawData();

        mesh.data.transform = transform;
    }

    public void SetBlock(Vector3i position, Block block)
    {
        blocks[position.x][position.y][position.z] = block;
        Rebuild();
    }

    public void Update()
    {
        for(int x = 0; x < CHUNK_SIZE; x++)
        {
            for (int y = 0; y < CHUNK_SIZE; y++)
            {
                for (int z = 0; z < CHUNK_SIZE; z++)
                {
                    blocks[x][y][z].OnTick(this);
                }
            }
        }
    }

    public void GenerateTopFace(Vector3f position, Vector2f[] uvs)
    {
        if(!sides.renderTop)
            return;

        vertices.add(Vertex.Register(new Vector3d(-0.5f + position.x,  0.5f + position.y,  0.5f + position.z), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(uvs[0].x, uvs[0].y)));
        vertices.add(Vertex.Register(new Vector3d(-0.5f + position.x,  0.5f + position.y, -0.5f + position.z), new Vector3f(0.0f, 1.0f, 0.0f), new Vector2f(uvs[1].x, uvs[1].y)));
        vertices.add(Vertex.Register(new Vector3d( 0.5f + position.x,  0.5f + position.y, -0.5f + position.z), new Vector3f(0.0f, 0.0f, 1.0f), new Vector2f(uvs[2].x, uvs[2].y)));
        vertices.add(Vertex.Register(new Vector3d( 0.5f + position.x,  0.5f + position.y,  0.5f + position.z), new Vector3f(1.0f, 1.0f, 0.0f), new Vector2f(uvs[3].x, uvs[3].y)));

        indices.add(3 + indiceIndex);
        indices.add(1 + indiceIndex);
        indices.add(indiceIndex);

        indices.add(2 + indiceIndex);
        indices.add(1 + indiceIndex);
        indices.add(3 + indiceIndex);

        indiceIndex += 4;
    }

    public void GenerateBottomFace(Vector3f position, Vector2f[] uvs)
    {
        if(!sides.renderBottom)
            return;

        vertices.add(Vertex.Register(new Vector3d(-0.5f + position.x, -0.5f + position.y,  0.5f + position.z), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(uvs[0].x, uvs[0].y)));
        vertices.add(Vertex.Register(new Vector3d(-0.5f + position.x, -0.5f + position.y, -0.5f + position.z), new Vector3f(0.0f, 1.0f, 0.0f), new Vector2f(uvs[1].x, uvs[1].y)));
        vertices.add(Vertex.Register(new Vector3d( 0.5f + position.x, -0.5f + position.y, -0.5f + position.z), new Vector3f(0.0f, 0.0f, 1.0f), new Vector2f(uvs[2].x, uvs[2].y)));
        vertices.add(Vertex.Register(new Vector3d( 0.5f + position.x, -0.5f + position.y,  0.5f + position.z), new Vector3f(1.0f, 1.0f, 0.0f), new Vector2f(uvs[3].x, uvs[3].y)));

        indices.add(indiceIndex);
        indices.add(1 + indiceIndex);
        indices.add(3 + indiceIndex);

        indices.add(3 + indiceIndex);
        indices.add(1 + indiceIndex);
        indices.add(2 + indiceIndex);

        indiceIndex += 4;
    }

    public void GenerateFrontFace(Vector3f position, Vector2f[] uvs)
    {
        if(!sides.renderFront)
            return;

        vertices.add(Vertex.Register(new Vector3d(-0.5f + position.x,  0.5f + position.y,  0.5f + position.z), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(uvs[0].x, uvs[0].y)));
        vertices.add(Vertex.Register(new Vector3d(-0.5f + position.x, -0.5f + position.y,  0.5f + position.z), new Vector3f(0.0f, 1.0f, 0.0f), new Vector2f(uvs[1].x, uvs[1].y)));
        vertices.add(Vertex.Register(new Vector3d( 0.5f + position.x, -0.5f + position.y,  0.5f + position.z), new Vector3f(0.0f, 0.0f, 1.0f), new Vector2f(uvs[2].x, uvs[2].y)));
        vertices.add(Vertex.Register(new Vector3d( 0.5f + position.x,  0.5f + position.y,  0.5f + position.z), new Vector3f(1.0f, 1.0f, 0.0f), new Vector2f(uvs[3].x, uvs[3].y)));

        indices.add(indiceIndex);
        indices.add(1 + indiceIndex);
        indices.add(3 + indiceIndex);

        indices.add(1 + indiceIndex);
        indices.add(2 + indiceIndex);
        indices.add(3 + indiceIndex);

        indiceIndex += 4;
    }

    public void GenerateBackFace(Vector3f position, Vector2f[] uvs)
    {
        if(!sides.renderBack)
            return;

        vertices.add(Vertex.Register(new Vector3d(-0.5f + position.x,  0.5f + position.y, -0.5f + position.z), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(uvs[0].x, uvs[0].y)));
        vertices.add(Vertex.Register(new Vector3d(-0.5f + position.x, -0.5f + position.y, -0.5f + position.z), new Vector3f(0.0f, 1.0f, 0.0f), new Vector2f(uvs[1].x, uvs[1].y)));
        vertices.add(Vertex.Register(new Vector3d( 0.5f + position.x, -0.5f + position.y, -0.5f + position.z), new Vector3f(0.0f, 0.0f, 1.0f), new Vector2f(uvs[2].x, uvs[2].y)));
        vertices.add(Vertex.Register(new Vector3d( 0.5f + position.x,  0.5f + position.y, -0.5f + position.z), new Vector3f(1.0f, 1.0f, 0.0f), new Vector2f(uvs[3].x, uvs[3].y)));

        indices.add(3 + indiceIndex);
        indices.add(1 + indiceIndex);
        indices.add(indiceIndex);

        indices.add(2 + indiceIndex);
        indices.add(1 + indiceIndex);
        indices.add(3 + indiceIndex);

        indiceIndex += 4;
    }

    public void GenerateRightFace(Vector3f position, Vector2f[] uvs)
    {
        if(!sides.renderRight)
            return;

        vertices.add(Vertex.Register(new Vector3d(0.5f + position.x,  0.5f + position.y, -0.5f + position.z), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(uvs[0].x, uvs[0].y)));
        vertices.add(Vertex.Register(new Vector3d(0.5f + position.x, -0.5f + position.y, -0.5f + position.z), new Vector3f(0.0f, 1.0f, 0.0f), new Vector2f(uvs[1].x, uvs[1].y)));
        vertices.add(Vertex.Register(new Vector3d(0.5f + position.x, -0.5f + position.y,  0.5f + position.z), new Vector3f(0.0f, 0.0f, 1.0f), new Vector2f(uvs[2].x, uvs[2].y)));
        vertices.add(Vertex.Register(new Vector3d(0.5f + position.x,  0.5f + position.y,  0.5f + position.z), new Vector3f(1.0f, 1.0f, 0.0f), new Vector2f(uvs[3].x, uvs[3].y)));

        indices.add(3 + indiceIndex);
        indices.add(1 + indiceIndex);
        indices.add(indiceIndex);

        indices.add(2 + indiceIndex);
        indices.add(1 + indiceIndex);
        indices.add(3 + indiceIndex);

        indiceIndex += 4;
    }

    public void GenerateLeftFace(Vector3f position, Vector2f[] uvs)
    {
        if(!sides.renderLeft)
            return;

        vertices.add(Vertex.Register(new Vector3d(-0.5f + position.x,  0.5f + position.y, -0.5f + position.z), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(uvs[0].x, uvs[0].y)));
        vertices.add(Vertex.Register(new Vector3d(-0.5f + position.x, -0.5f + position.y, -0.5f + position.z), new Vector3f(0.0f, 1.0f, 0.0f), new Vector2f(uvs[1].x, uvs[1].y)));
        vertices.add(Vertex.Register(new Vector3d(-0.5f + position.x, -0.5f + position.y,  0.5f + position.z), new Vector3f(0.0f, 0.0f, 1.0f), new Vector2f(uvs[2].x, uvs[2].y)));
        vertices.add(Vertex.Register(new Vector3d(-0.5f + position.x,  0.5f + position.y,  0.5f + position.z), new Vector3f(1.0f, 1.0f, 0.0f), new Vector2f(uvs[3].x, uvs[3].y)));

        indices.add(indiceIndex);
        indices.add(1 + indiceIndex);
        indices.add(3 + indiceIndex);

        indices.add(3 + indiceIndex);
        indices.add(1 + indiceIndex);
        indices.add(2 + indiceIndex);

        indiceIndex += 4;
    }

    public static Chunk Register()
    {
        Chunk chunk = new Chunk();

        chunk.Initialize();

        return chunk;
    }

    public static Chunk Register(Vector3f position)
    {
        Chunk chunk = new Chunk();

        chunk.transform = Transform.Register(position);
        chunk.Initialize();

        return chunk;
    }

    public static Chunk Register(Vector3f position, String name)
    {
        Chunk chunk = new Chunk();

        chunk.transform = Transform.Register(position);
        chunk.name.name = name;
        chunk.Initialize();

        return chunk;
    }
}