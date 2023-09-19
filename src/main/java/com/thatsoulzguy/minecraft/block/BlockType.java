package com.thatsoulzguy.minecraft.block;

import com.thatsoulzguy.minecraft.block.blocks.AirBlock;
import com.thatsoulzguy.minecraft.block.blocks.GrassBlock;

public class BlockType
{
    public static Block AIR = new AirBlock();
    public static Block GRASS = new GrassBlock();

    public static void Initialize()
    {
        AIR = new AirBlock();
        GRASS = new GrassBlock();
    }
}