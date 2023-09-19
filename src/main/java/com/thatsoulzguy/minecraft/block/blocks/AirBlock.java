package com.thatsoulzguy.minecraft.block.blocks;

import com.thatsoulzguy.minecraft.block.Block;
import com.thatsoulzguy.minecraft.block.BlockData;
import org.joml.Vector2i;

public class AirBlock extends Block
{

    @Override
    public BlockData Register()
    {
        return BlockData.Register(0, "air_block", "null", new Vector2i[]
        {
            new Vector2i(6, -1),
            new Vector2i(6, -1),
            new Vector2i(6, -1),
            new Vector2i(6, -1),
            new Vector2i(6, -1),
            new Vector2i(6, -1)
        });
    }
}