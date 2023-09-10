package com.thatsoulzguy.minecraft.rendering;

import com.thatsoulzguy.minecraft.core.LogLevel;
import com.thatsoulzguy.minecraft.core.Logger;
import com.thatsoulzguy.minecraft.util.NameIDTag;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import java.util.ArrayList;

public class Renderer
{
    private static final ArrayList<RenderableObject> registeredObjects = new ArrayList<>();
    private static Matrix4f translation = new Matrix4f().identity();

    public static void RegisterRenderableObject(RenderableObject object)
    {
        registeredObjects.add(object);
    }

    public static void ReRegisterRenderableObject(RenderableObject object)
    {
        registeredObjects.remove(object);
        registeredObjects.add(object);
    }

    public static void RenderObjects(Camera camera)
    {
        for(RenderableObject object : registeredObjects)
        {
            GL30.glBindVertexArray(object.data.VAO);

            for (int q = 0; q < object.data.queuedData; q++)
                GL30.glEnableVertexAttribArray(q);

            for (int t = 0; t < object.data.textures.size(); t++)
            {
                GL13.glActiveTexture(GL13.GL_TEXTURE0 + t);
                GL13.glBindTexture(GL11.GL_TEXTURE_2D, object.data.textures.get(t).textureID);
            }

            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, object.data.EBO);

            translation.setTranslation(object.data.transform.position);
            translation.setRotationXYZ(object.data.transform.rotation.x, object.data.transform.rotation.y, object.data.transform.rotation.z);

            object.data.shader.Use();
            object.data.shader.SetUniform("projection", camera.GetData().projection);
            object.data.shader.SetUniform("view", camera.GetData().view);
            object.data.shader.SetUniform("model", translation);

            //Logger.WriteConsole(String.format("%d first", object.data.indices.size()), "Renderer", LogLevel.DEBUG);
            GL11.glDrawElements(GL11.GL_TRIANGLES, object.data.indices.size(), GL11.GL_UNSIGNED_INT, 0);
            //Logger.WriteConsole(String.format("%d second", object.data.indices.size()), "Renderer", LogLevel.DEBUG);

            int error = GL11.glGetError();
            if (error != GL11.GL_NO_ERROR && error != 1281)
                Logger.WriteConsole("OpenGL error detected: " + error, "Renderer", LogLevel.ERROR);
        }
    }

    public static RenderableObject GetObject(NameIDTag tag)
    {
        for (RenderableObject object : registeredObjects)
        {
            if(NameIDTag.IsMatch(tag, object.data.name))
                return object;
        }

        return null;
    }

    public static RenderableObject CloneObject(NameIDTag tag)
    {
        for (RenderableObject object : registeredObjects)
        {
            if(NameIDTag.IsMatch(tag, object.data.name))
                return object.clone();
        }

        return null;
    }

    public static void CleanUp()
    {
        for(RenderableObject object : registeredObjects)
            object.CleanUp();
    }
}