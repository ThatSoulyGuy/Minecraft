package com.thatsoulzguy.minecraft.rendering;

import com.thatsoulzguy.minecraft.util.FileHelper;
import com.thatsoulzguy.minecraft.util.TypeHelper;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

public class ShaderObject implements Cloneable
{
    public String name;
    public String vertexPath, fragmentPath;
    public String vertexData, fragmentData;
    public int programID;

    public void BindShader()
    {
        programID = GL20.glCreateProgram();
        int vertexID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);

        GL20.glShaderSource(vertexID, vertexData);
        GL20.glCompileShader(vertexID);

        if (GL20.glGetShaderi(vertexID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE)
        {
            System.err.println("Vertex Shader: " + GL20.glGetShaderInfoLog(vertexID));
            return;
        }

        int fragmentID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);

        GL20.glShaderSource(fragmentID, fragmentData);
        GL20.glCompileShader(fragmentID);

        if (GL20.glGetShaderi(fragmentID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE)
        {
            System.err.println("Fragment Shader: " + GL20.glGetShaderInfoLog(fragmentID));
            return;
        }

        GL20.glAttachShader(programID, vertexID);
        GL20.glAttachShader(programID, fragmentID);

        GL20.glLinkProgram(programID);
        if (GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS) == GL11.GL_FALSE)
        {
            System.err.println("Program Linking: " + GL20.glGetProgramInfoLog(programID));
            return;
        }

        GL20.glValidateProgram(programID);
        if (GL20.glGetProgrami(programID, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE)
        {
            System.err.println("Program Validation: " + GL20.glGetProgramInfoLog(programID));
            return;
        }

        GL20.glDeleteShader(vertexID);
        GL20.glDeleteShader(fragmentID);
    }

    public void Use()
    {
        GL20.glUseProgram(programID);
    }

    public void UnUse()
    {
        GL20.glUseProgram(0);
    }

    public int getUniformLocation(String name)
    {
        return GL20.glGetUniformLocation(programID, name);
    }

    public void setUniform(String name, float value)
    {
        GL20.glUniform1f(getUniformLocation(name), value);
    }

    public void SetUniform(String name, int value)
    {
        GL20.glUniform1i(getUniformLocation(name), value);
    }

    public void SetUniform(String name, boolean value)
    {
        GL20.glUniform1i(getUniformLocation(name), TypeHelper.Bool2Int(value));
    }

    public void SetUniform(String name, Vector2f value)
    {
        GL20.glUniform2f(getUniformLocation(name), value.x, value.y);
    }

    public void SetUniform(String name, Vector3f value)
    {
        GL20.glUniform3f(getUniformLocation(name), value.x, value.y, value.z);
    }

    public void SetUniform(String name, Matrix4f value)
    {
        FloatBuffer matrix = MemoryUtil.memAllocFloat(16);
        value.get(matrix);

        GL20.glUniformMatrix4fv(getUniformLocation(name), false, matrix);
    }

    public void CleanUp()
    {
        GL20.glDeleteProgram(programID);
    }

    @Override
    protected Object clone()
    {
        try
        {
            return super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static ShaderObject Register(String path, String name)
    {
        ShaderObject object = new ShaderObject();

        object.name = name;

        object.vertexPath = "/assets/minecraft/" + path + "Vertex.glsl";
        object.fragmentPath = "/assets/minecraft/" + path + "Fragment.glsl";

        object.vertexData = FileHelper.LoadFile(object.vertexPath);
        object.fragmentData = FileHelper.LoadFile(object.fragmentPath);

        return object;
    }
}