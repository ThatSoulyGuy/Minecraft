package com.thatsoulzguy.minecraft.rendering;

import com.thatsoulzguy.minecraft.core.LogLevel;
import com.thatsoulzguy.minecraft.core.Logger;
import com.thatsoulzguy.minecraft.util.NameIDTag;
import org.joml.Vector2f;
import org.joml.Vector3d;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class RenderableObject implements Cloneable
{
    public RenderableData data = new RenderableData();

    public void GenerateTestObject()
    {
        ArrayList<Vertex> vertices = new ArrayList<>();

        vertices.add(Vertex.Register(new Vector3d(-0.5f,  0.5f,  0.5f), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(0.0f, 0.0f)));
        vertices.add(Vertex.Register(new Vector3d(-0.5f,  0.5f, -0.5f), new Vector3f(0.0f, 1.0f, 0.0f), new Vector2f(0.0f, 1.0f)));
        vertices.add(Vertex.Register(new Vector3d( 0.5f,  0.5f, -0.5f), new Vector3f(0.0f, 0.0f, 1.0f), new Vector2f(1.0f, 1.0f)));
        vertices.add(Vertex.Register(new Vector3d( 0.5f,  0.5f,  0.5f), new Vector3f(1.0f, 1.0f, 0.0f), new Vector2f(1.0f, 0.0f)));

        vertices.add(Vertex.Register(new Vector3d(-0.5f, -0.5f,  0.5f), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(0.0f, 0.0f)));
        vertices.add(Vertex.Register(new Vector3d(-0.5f, -0.5f, -0.5f), new Vector3f(0.0f, 1.0f, 0.0f), new Vector2f(0.0f, 1.0f)));
        vertices.add(Vertex.Register(new Vector3d( 0.5f, -0.5f, -0.5f), new Vector3f(0.0f, 0.0f, 1.0f), new Vector2f(1.0f, 1.0f)));
        vertices.add(Vertex.Register(new Vector3d( 0.5f, -0.5f,  0.5f), new Vector3f(1.0f, 1.0f, 0.0f), new Vector2f(1.0f, 0.0f)));

        vertices.add(Vertex.Register(new Vector3d(-0.5f,  0.5f,  0.5f), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(0.0f, 0.0f)));
        vertices.add(Vertex.Register(new Vector3d(-0.5f, -0.5f,  0.5f), new Vector3f(0.0f, 1.0f, 0.0f), new Vector2f(0.0f, 1.0f)));
        vertices.add(Vertex.Register(new Vector3d( 0.5f, -0.5f,  0.5f), new Vector3f(0.0f, 0.0f, 1.0f), new Vector2f(1.0f, 1.0f)));
        vertices.add(Vertex.Register(new Vector3d( 0.5f,  0.5f,  0.5f), new Vector3f(1.0f, 1.0f, 0.0f), new Vector2f(1.0f, 0.0f)));

        vertices.add(Vertex.Register(new Vector3d(-0.5f,  0.5f, -0.5f), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(0.0f, 0.0f)));
        vertices.add(Vertex.Register(new Vector3d(-0.5f, -0.5f, -0.5f), new Vector3f(0.0f, 1.0f, 0.0f), new Vector2f(0.0f, 1.0f)));
        vertices.add(Vertex.Register(new Vector3d( 0.5f, -0.5f, -0.5f), new Vector3f(0.0f, 0.0f, 1.0f), new Vector2f(1.0f, 1.0f)));
        vertices.add(Vertex.Register(new Vector3d( 0.5f,  0.5f, -0.5f), new Vector3f(1.0f, 1.0f, 0.0f), new Vector2f(1.0f, 0.0f)));


        vertices.add(Vertex.Register(new Vector3d(0.5f,  0.5f, -0.5f), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(0.0f, 0.0f)));
        vertices.add(Vertex.Register(new Vector3d(0.5f, -0.5f, -0.5f), new Vector3f(0.0f, 1.0f, 0.0f), new Vector2f(0.0f, 1.0f)));
        vertices.add(Vertex.Register(new Vector3d(0.5f, -0.5f,  0.5f), new Vector3f(0.0f, 0.0f, 1.0f), new Vector2f(1.0f, 1.0f)));
        vertices.add(Vertex.Register(new Vector3d(0.5f,  0.5f,  0.5f), new Vector3f(1.0f, 1.0f, 0.0f), new Vector2f(1.0f, 0.0f)));

        vertices.add(Vertex.Register(new Vector3d(-0.5f,  0.5f, -0.5f), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(0.0f, 0.0f)));
        vertices.add(Vertex.Register(new Vector3d(-0.5f, -0.5f, -0.5f), new Vector3f(0.0f, 1.0f, 0.0f), new Vector2f(0.0f, 1.0f)));
        vertices.add(Vertex.Register(new Vector3d(-0.5f, -0.5f,  0.5f), new Vector3f(0.0f, 0.0f, 1.0f), new Vector2f(1.0f, 1.0f)));
        vertices.add(Vertex.Register(new Vector3d(-0.5f,  0.5f,  0.5f), new Vector3f(1.0f, 1.0f, 0.0f), new Vector2f(1.0f, 0.0f)));

        ArrayList<Integer> indices = new ArrayList<>();

        indices.add(0);
        indices.add(1);
        indices.add(3);

        indices.add(3);
        indices.add(1);
        indices.add(2);


        indices.add(4);
        indices.add(5);
        indices.add(7);

        indices.add(7);
        indices.add(5);
        indices.add(6);


        indices.add(8);
        indices.add(9);
        indices.add(11);

        indices.add(11);
        indices.add(9);
        indices.add(10);


        indices.add(12);
        indices.add(13);
        indices.add(15);

        indices.add(15);
        indices.add(13);
        indices.add(14);


        indices.add(16);
        indices.add(17);
        indices.add(19);

        indices.add(19);
        indices.add(17);
        indices.add(18);


        indices.add(20);
        indices.add(21);
        indices.add(23);

        indices.add(23);
        indices.add(21);
        indices.add(22);

        data.textures.add(Texture.Register("textures/block/oak_planks.png"));
        RegisterValues(NameIDTag.Register("testObject", "A Simple Test", this), vertices, indices, "default");
        GenerateRawData();
    }

    public void RegisterValues(NameIDTag name, List<Vertex> vertices, List<Integer> indices, String shader)
    {
        data.name = name;
        data.vertices = vertices;
        data.indices = indices;

        data.shader = ShaderManager.GetShader(shader);
        assert data.shader != null;

        data.shader.BindShader();
    }

    public void ReRegisterValues(List<Vertex> vertices, List<Integer> indices)
    {
        data.vertices = vertices;
        data.indices = indices;
    }

    public void GenerateRawData()
    {
        data.VAO = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(data.VAO);


        DoubleBuffer positionBuffer = MemoryUtil.memAllocDouble(data.vertices.size() * 3);
        double[] positionData = new double[data.vertices.size() * 3];

        for (int v = 0; v < data.vertices.size(); v++)
        {
            positionData[v * 3] = data.vertices.get(v).position.x;
            positionData[v * 3 + 1] = data.vertices.get(v).position.y;
            positionData[v * 3 + 2] = data.vertices.get(v).position.z;
        }

        positionBuffer.put(positionData).flip();
        data.VBO = StoreDataAsDouble(positionBuffer, 0, 3);


        FloatBuffer colorBuffer = MemoryUtil.memAllocFloat(data.vertices.size() * 3);
        float[] colorData = new float[data.vertices.size() * 3];

        for (int c = 0; c < data.vertices.size(); c++)
        {
            colorData[c * 3] = data.vertices.get(c).color.x;
            colorData[c * 3 + 1] = data.vertices.get(c).color.y;
            colorData[c * 3 + 2] = data.vertices.get(c).color.z;
        }

        colorBuffer.put(colorData).flip();
        data.CBO = StoreDataAsFloat(colorBuffer, 1, 3);


        FloatBuffer textureBuffer = MemoryUtil.memAllocFloat(data.vertices.size() * 2);
        float[] textureData = new float[data.vertices.size() * 2];

        for (int t = 0; t < data.vertices.size(); t++)
        {
            textureData[t * 2] = data.vertices.get(t).textureCoordinates.x;
            textureData[t * 2 + 1] = data.vertices.get(t).textureCoordinates.y;
        }

        textureBuffer.put(textureData).flip();
        data.TBO = StoreDataAsFloat(textureBuffer, 2, 2);


        IntBuffer indicesBuffer = MemoryUtil.memAllocInt(data.indices.size());
        int[] indicesData = new int[data.indices.size()];

        for (int i = 0; i < data.indices.size(); i++)
            indicesData[i] = data.indices.get(i);

        indicesBuffer.put(indicesData).flip();

        data.EBO = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, data.EBO);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);

        data.queuedData++;

        int error = GL11.glGetError();
        if (error != GL11.GL_NO_ERROR)
            Logger.WriteConsole("OpenGL error detected: " + error, "RenderableObject", LogLevel.ERROR);
    }

    private int StoreDataAsFloat(FloatBuffer buffer, int index, int size)
    {
        int out = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, out);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(index, size, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        data.queuedData++;

        int error = GL11.glGetError();
        if (error != GL11.GL_NO_ERROR)
            Logger.WriteConsole("OpenGL error detected: " + error, "RenderableObject", LogLevel.ERROR);

        return out;
    }

    private int StoreDataAsDouble(DoubleBuffer buffer, int index, int size)
    {
        int out = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, out);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(index, size, GL11.GL_DOUBLE, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        data.queuedData++;

        int error = GL11.glGetError();
        if (error != GL11.GL_NO_ERROR)
            Logger.WriteConsole("OpenGL error detected: " + error, "RenderableObject", LogLevel.ERROR);

        return out;
    }

    public void AddTexture(Texture texture)
    {
        data.textures.add(texture);
    }

    public void CleanUp()
    {
        GL30.glDeleteVertexArrays(data.VAO);
        GL30.glDeleteBuffers(data.VBO);
        GL30.glDeleteBuffers(data.CBO);
        GL30.glDeleteBuffers(data.TBO);
        GL30.glDeleteBuffers(data.EBO);

        for (Texture texture : data.textures)
            texture.CleanUp();

        data.shader.CleanUp();
    }

    @Override
    public RenderableObject clone()
    {
        try
        {
            return (RenderableObject) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}