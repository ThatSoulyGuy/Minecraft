package com.thatsoulzguy.minecraft.block;

import com.thatsoulzguy.minecraft.rendering.Texture;
import org.joml.Vector2i;

public class BlockData
{
    public long id;
    public String name;
    public String displayName;
    public Vector2i[] uvPositions = new Vector2i[6];

    public static BlockData Register(long id, String name, String displayName, Vector2i[] positions)
    {
        BlockData out = new BlockData();

        if(positions.length != 6)
            return null;

        out.id = id;
        out.name = name;
        out.displayName = displayName;

        out.uvPositions[0] = positions[0];
        out.uvPositions[1] = positions[1];
        out.uvPositions[2] = positions[2];
        out.uvPositions[3] = positions[3];
        out.uvPositions[4] = positions[4];
        out.uvPositions[5] = positions[5];

        return out;
    }
}