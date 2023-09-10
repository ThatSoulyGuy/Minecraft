package com.thatsoulzguy.minecraft.math;

import com.thatsoulzguy.minecraft.core.LogLevel;
import com.thatsoulzguy.minecraft.core.Logger;
import org.joml.Vector3f;

public abstract class MovableEntity
{
    public Transform transform = Transform.Register(new Vector3f(0.0f, 0.0f, 0.0f));
    public float pitch = 0, yaw = 0;
    public float moveSpeed = 0.4f;

    /*
    if (Input::GetKeyDown(GLFW_KEY_W))
			{
				//Logger_WriteConsole(std::format("Position: [{}, {}, {}]", transform.position.x, transform.position.y, transform.position.z), LogLevel::INFO);

				transform.position += cameraSpeed * qtov(transform.rotation);

				if (Input::GetKeyDown(GLFW_KEY_LEFT_CONTROL))
				{

					transform.position += cameraSpeed * 3 * qtov(transform.rotation);
				}
			}

			if (Input::GetKeyDown(GLFW_KEY_S))
			{
				transform.position -= cameraSpeed * qtov(transform.rotation);
				//Logger_WriteConsole(std::format("Position: [{}, {}, {}]", transform.position.x, transform.position.y, transform.position.z), LogLevel::INFO);
			}

			if (Input::GetKeyDown(GLFW_KEY_A))
			{
				transform.position -= glm::normalize(glm::cross(qtov(transform.rotation), transform.up)) * cameraSpeed;
				//Logger_WriteConsole(std::format("Position: [{}, {}, {}]", transform.position.x, transform.position.y, transform.position.z), LogLevel::INFO);
			}

			if (Input::GetKeyDown(GLFW_KEY_D))
			{
				transform.position += glm::normalize(glm::cross(qtov(transform.rotation), transform.up)) * cameraSpeed;
				//Logger_WriteConsole(std::format("Position: [{}, {}, {}]", transform.position.x, transform.position.y, transform.position.z), LogLevel::INFO);
			}
     */

    public void Move(Direction direction)
    {
        switch (direction)
        {
            case FORWARD ->
                transform.position.add(new Vector3f(transform.rotation).mul(moveSpeed));

            case BACKWARD ->
                transform.position.sub(new Vector3f(transform.rotation).mul(moveSpeed));


            case RIGHT ->
                transform.position.add((new Vector3f(transform.rotation).cross(new Vector3f(transform.up))).normalize().mul(moveSpeed));

            case LEFT ->
                transform.position.sub((new Vector3f(transform.rotation).cross(new Vector3f(transform.up))).normalize().mul(moveSpeed));
        }
    }

    public void MovementUpdate()
    {
        Vector3f front = new Vector3f();

        front.x = (float) Math.cos(Math.toRadians(yaw)) * (float) Math.cos(Math.toRadians(pitch));
        front.y = (float) Math.sin(Math.toRadians(pitch));
        front.z = (float) Math.sin(Math.toRadians(yaw)) * (float) Math.cos(Math.toRadians(pitch));

        transform.rotation = front;
    }

    //public Vector3f GetRightVector()
    //{
        //return new Vector3f(Math.sin(Math.toRadians(yaw - 90)), 0, Math.cos(Math.toRadians(yaw - 90))).normalize();
    //}
}