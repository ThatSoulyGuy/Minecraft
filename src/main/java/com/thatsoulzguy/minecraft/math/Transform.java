package com.thatsoulzguy.minecraft.math;

import org.joml.Quaternionf;
import org.joml.Vector3d;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class Transform
{
    public Vector3f rotation;
    public Vector3f position;
    public Vector3f up;

    public void Translate(Vector3f translation)
    {
        position.add(translation);
    }

    public void Rotate(Vector3f rotation)
    {
        this.rotation.add(rotation);
    }

    public static Transform Register(Vector3f position)
    {
        Transform out = new Transform();

        out.position = position;
        out.rotation = new Vector3f();
        out.up = new Vector3f(0.0f, 1.0f, 0.0f);

        return out;
    }
}