package com.thatsoulzguy.minecraft.core;

import org.joml.Vector2i;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.opengl.GL11;

public class WindowData
{
    public long window;
    public String title;
    public Vector3f color;
    public boolean isResized = false;
    public Vector2i resizedData = null;

    public GLFWFramebufferSizeCallback resizeWindow = new GLFWFramebufferSizeCallback()
    {
        @Override
        public void invoke(long window, int width, int height)
        {
            GL11.glViewport(0, 0, width, height); //NEW
            isResized = true;

            resizedData = new Vector2i(width, height);
        }
    };

}