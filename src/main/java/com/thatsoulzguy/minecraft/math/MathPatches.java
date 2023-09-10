package com.thatsoulzguy.minecraft.math;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class MathPatches
{
    public static Matrix4f LookAt(Vector3f position, Vector3f center, Vector3f up)
    {
        Vector3f f = new Vector3f(center).sub(position).normalize();
        Vector3f r = new Vector3f(up).cross(f).normalize();
        Vector3f u = new Vector3f(f).cross(r).normalize();

        Matrix4f result = new Matrix4f();

        result.m00(r.x);
        result.m10(r.y);
        result.m20(r.z);

        result.m01(u.x);
        result.m11(u.y);
        result.m21(u.z);

        result.m02(-f.x);
        result.m12(-f.y);
        result.m22(-f.z);

        result.m30(-r.dot(position));
        result.m31(-u.dot(position));
        result.m32(f.dot(position));

        return result;
    }
}