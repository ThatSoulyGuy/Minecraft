package com.thatsoulzguy.minecraft;

import com.thatsoulzguy.minecraft.core.*;
import com.thatsoulzguy.minecraft.rendering.*;
import com.thatsoulzguy.minecraft.util.ANSIFormatter;
import com.thatsoulzguy.minecraft.world.BlockType;
import com.thatsoulzguy.minecraft.world.Chunk;
import com.thatsoulzguy.minecraft.world.World;
import org.joml.Vector2i;
import org.joml.Vector3f;
import org.joml.Vector3i;
import org.lwjgl.glfw.GLFW;

import java.time.LocalTime;
import java.util.ArrayList;

public class Minecraft implements Runnable
{
    public static Minecraft instance = null;
    public Window window;
    public Camera camera;
    public RenderableObject object;
    public Thread mainThread;

    public boolean loadingDone = false;

    public void start()
    {
        instance = this;

        mainThread = new Thread(Minecraft.GetInstance(), "mainThread");
        mainThread.start();
    }

    public void MainThread_init()
    {
        ShaderManager.RegisterShader(ShaderObject.Register("shaders/default", "default"));

        Input.InitInput();

        window = new Window();
        window.GenerateWindow(new Vector2i(750, 450), "Minecraft* 0.1.1");
        window.data.color = new Vector3f(0.0f, 0.24f, 0.45f);

        loadingDone = true;

        camera = Camera.Register(new Vector3f(0.0f, 0.0f, 0.0f));

        object = new RenderableObject();
        object.GenerateTestObject();

        //Renderer.RegisterRenderableObject(object);

        World.Register(camera);
    }

    public void MainThread_update()
    {
        window.UpdateColors();

        if(Input.GetKeyDown(GLFW.GLFW_KEY_ESCAPE))
            GLFW.glfwSetWindowShouldClose(window.data.window, true);

        camera.Update();
        Renderer.RenderObjects(camera);

        window.UpdateBuffers();
    }

    public void MainThread_close()
    {
        ShaderManager.CleanUp();
        Renderer.CleanUp();
        window.CleanUp();
    }

    @Override
    public void run()
    {
        MainThread_init();

        while(!window.ShouldClose())
            MainThread_update();

        MainThread_close();
    }

    public static Minecraft GetInstance()
    {
        return instance;
    }

    public static void main(String[] args)
    {
        new Minecraft().start();
    }
}