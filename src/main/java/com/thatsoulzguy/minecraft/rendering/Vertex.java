package com.thatsoulzguy.minecraft.rendering;

import org.joml.Vector2f;
import org.joml.Vector3d;
import org.joml.Vector3f;

public class Vertex
{
    public Vector3d position;
    public Vector3f color;
    public Vector2f textureCoordinates;

    public static Vertex Register(Vector3d position, Vector3f color, Vector2f textureCoordinates)
    {
        Vertex out = new Vertex();

        out.position = position;
        out.color = color;
        out.textureCoordinates = textureCoordinates;

        return out;
    }
}