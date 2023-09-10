package com.thatsoulzguy.minecraft.core;

import org.joml.Vector2i;
import org.joml.Vector3f;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.nio.IntBuffer;

public class Window
{
    public WindowData data = new WindowData();

    public void GenerateWindow(Vector2i size, String title)
    {
        data.title = title;
        data.color = new Vector3f(0.0f, 0.0f, 0.0f);
        data.resizedData = new Vector2i(size.x, size.y);

        GLFWErrorCallback.createPrint(System.err).set();

        if (!GLFW.glfwInit())
            throw new IllegalStateException("Failed to initialize GLFW");

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_SAMPLES, 16);
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);

        data.window = GLFW.glfwCreateWindow(size.x, size.y, title, MemoryUtil.NULL, MemoryUtil.NULL);

        if (data.window == MemoryUtil.NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        GLFW.glfwSetKeyCallback(data.window, Input.keyboard);
        GLFW.glfwSetCursorPosCallback(data.window, Input.mouseMovement);
        GLFW.glfwSetMouseButtonCallback(data.window, Input.mouseButtons);
        GLFW.glfwSetFramebufferSizeCallback(data.window, data.resizeWindow);

        SetWindowPosition(new Vector2i((GetScreenResolution().x - GetWindowSize().x) / 2, (GetScreenResolution().y - GetWindowSize().y) / 2));

        GLFW.glfwMakeContextCurrent(data.window);
        GLFW.glfwSwapInterval(1);

        GLFW.glfwShowWindow(data.window);

        GL.createCapabilities();

        GL11.glViewport(0, 0, size.x, size.y);

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL30.GL_MULTISAMPLE);

        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
    }

    public void UpdateColors()
    {
        GL11.glClearColor(data.color.x, data.color.y, data.color.z, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    public void UpdateBuffers()
    {
        GLFW.glfwSwapBuffers(data.window);
        GLFW.glfwPollEvents();
    }

    public boolean ShouldClose()
    {
        return GLFW.glfwWindowShouldClose(data.window);
    }

    public Vector2i GetWindowSize()
    {
        try (MemoryStack stack = MemoryStack.stackPush())
        {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            GLFW.glfwGetWindowSize(data.window, pWidth, pHeight);

            return new Vector2i(pWidth.get(0), pHeight.get(0));
        }
    }

    public Vector2i GetWindowPosition()
    {
        try (MemoryStack stack = MemoryStack.stackPush())
        {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            GLFW.glfwGetWindowPos(data.window, pWidth, pHeight);

            return new Vector2i(pWidth.get(0), pHeight.get(0));
        }
    }

    public static Vector2i GetScreenResolution()
    {
        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        assert videoMode != null;

        return new Vector2i(videoMode.width(), videoMode.height());
    }

    public static int GetAspectRatio(Window window)
    {
        return window.GetWindowSize().x / window.GetWindowSize().y;
    }

    public void SetWindowPosition(Vector2i position)
    {
        try (MemoryStack stack = MemoryStack.stackPush())
        {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            GLFW.glfwGetWindowSize(data.window, pWidth, pHeight);

            GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
            assert videoMode != null;

            GLFW.glfwSetWindowPos(data.window, position.x, position.y);
        }
    }

    public void CleanUp()
    {
        Callbacks.glfwFreeCallbacks(data.window);
        GLFW.glfwDestroyWindow(data.window);

        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();
    }
}