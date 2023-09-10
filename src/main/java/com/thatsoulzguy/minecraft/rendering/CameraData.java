package com.thatsoulzguy.minecraft.rendering;

import org.joml.Matrix4f;

public class CameraData
{
    public Matrix4f view = new Matrix4f().identity();
    public Matrix4f projection = new Matrix4f().identity();

    public static CameraData Register()
    {
        CameraData out = new CameraData();

        out.view.identity();
        out.projection.identity();

        return out;
    }
}