package com.thatsoulzguy.minecraft.block;

import com.thatsoulzguy.minecraft.rendering.Camera;
import com.thatsoulzguy.minecraft.rendering.Texture;
import com.thatsoulzguy.minecraft.world.Chunk;

public abstract class Block
{
    public BlockData data = new BlockData();

    public void OnBlockBroken(Camera player) { }

    public void OnBlockPlaced(Camera player) { }

    public void OnTick(Chunk tickers) { };

    public abstract BlockData Register();
}