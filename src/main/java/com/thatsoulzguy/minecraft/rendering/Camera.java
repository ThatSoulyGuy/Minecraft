package com.thatsoulzguy.minecraft.rendering;

import com.thatsoulzguy.minecraft.Minecraft;
import com.thatsoulzguy.minecraft.core.Input;
import com.thatsoulzguy.minecraft.core.LogLevel;
import com.thatsoulzguy.minecraft.core.Logger;
import com.thatsoulzguy.minecraft.math.Direction;
import com.thatsoulzguy.minecraft.math.MovableEntity;
import org.joml.Math;
import org.joml.Vector2d;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Camera extends MovableEntity
{
    public float mouseSensitivity = 0.08f;
    private float oldMouseX = 0, oldMouseY = 0, newMouseX = 0, newMouseY = 0;

    public CameraData GetData()
    {
        CameraData out = CameraData.Register();

        out.view.identity().lookAt(transform.position, new Vector3f(transform.rotation).add(transform.position), transform.up);

        out.projection.identity().perspective((float)Math.toRadians(45f), ((float)Minecraft.GetInstance().window.data.resizedData.x / (float)Minecraft.GetInstance().window.data.resizedData.y), 0.01f, 100.0f);

        return out;
    }

    public void Update()
    {
        UpdateMouseLook();
        UpdateKeyboardMovement();

        MovementUpdate();
    }

    public void UpdateKeyboardMovement()
    {
        if (Input.GetKeyDown(GLFW.GLFW_KEY_W))
            Move(Direction.FORWARD);

        if (Input.GetKeyDown(GLFW.GLFW_KEY_S))
            Move(Direction.BACKWARD);

        if (Input.GetKeyDown(GLFW.GLFW_KEY_D))
            Move(Direction.RIGHT);

        if (Input.GetKeyDown(GLFW.GLFW_KEY_A))
            Move(Direction.LEFT);
    }

    public void UpdateMouseLook()
    {
        newMouseX = Input.mousePosition.x;
        newMouseY = Input.mousePosition.y;

        float dx = (newMouseX - oldMouseX);
        float dy = (newMouseY - oldMouseY);

        yaw += dx * mouseSensitivity;
        pitch -= dy * mouseSensitivity;

        if(pitch > 90)
            pitch = 89.99f;

        if(pitch < -90)
            pitch = -89.99f;

        oldMouseX = newMouseX;
        oldMouseY = newMouseY;
    }

    public static Camera Register(Vector3f position)
    {
        Camera camera = new Camera();

        Input.SetCursorMode(false);
        camera.transform.position = position;
        camera.yaw = 0.0f;
        camera.pitch = 0.0f;
        camera.moveSpeed = 0.4f;

        return camera;
    }
}