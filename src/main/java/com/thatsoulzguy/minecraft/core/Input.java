package com.thatsoulzguy.minecraft.core;

import com.thatsoulzguy.minecraft.Minecraft;
import com.thatsoulzguy.minecraft.util.TypeHelper;
import org.joml.Vector2d;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class Input
{
    private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
    private static boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];

    public static Vector2f mousePosition;

    public static GLFWKeyCallback keyboard;
    public static GLFWCursorPosCallback mouseMovement;
    public static GLFWMouseButtonCallback mouseButtons;

    public static void InitInput()
    {
        mousePosition = new Vector2f(0.0f, 0.0f);

        keyboard = new GLFWKeyCallback()
        {
            public void invoke(long window, int key, int scancode, int action, int mods)
            {
                keys[key] = TypeHelper.Int2Bool(action);
            }
        };

        mouseMovement = new GLFWCursorPosCallback()
        {
            public void invoke(long window, double xpos, double ypos)
            {
                mousePosition.x = (float)xpos;
                mousePosition.y = (float)ypos;
            }
        };

        mouseButtons = new GLFWMouseButtonCallback()
        {
            public void invoke(long window, int button, int action, int mods)
            {
                buttons[button] = TypeHelper.Int2Bool(action);
            }
        };
    }

    public static void SetCursorMode(boolean value)
    {
        if (!value)
            GLFW.glfwSetInputMode(Minecraft.GetInstance().window.data.window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
        else
            GLFW.glfwSetInputMode(Minecraft.GetInstance().window.data.window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
    }

    public static boolean GetKeyDown(int key)
    {
        return keys[key];
    }

    public static boolean GetKeyUp(int key)
    {
        return !keys[key];
    }

    public static boolean GetMouseButtonDown(int button)
    {
        return buttons[button];
    }

    public static boolean GetMouseButtonUp(int button)
    {
        return !buttons[button];
    }

    public void CleanUp()
    {
        keyboard.free();
        mouseMovement.free();
        mouseButtons.free();
    }
}