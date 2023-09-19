package com.thatsoulzguy.minecraft.block.blocks;

import com.thatsoulzguy.minecraft.block.Block;
import com.thatsoulzguy.minecraft.block.BlockData;
import org.joml.Vector2i;

public class GrassBlock extends Block
{

    @Override
    public BlockData Register()
    {
        return BlockData.Register(0, "grass_block", "Grass Block", new Vector2i[]
                {
                        new Vector2i(0, 0),
                        new Vector2i(2, 0),
                        new Vector2i(3, 0),
                        new Vector2i(3, 0),
                        new Vector2i(3, 0),
                        new Vector2i(3, 0)
                });
    }
}