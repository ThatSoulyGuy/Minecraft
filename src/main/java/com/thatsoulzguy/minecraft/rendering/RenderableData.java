package com.thatsoulzguy.minecraft.rendering;

import com.thatsoulzguy.minecraft.math.Transform;
import com.thatsoulzguy.minecraft.util.NameIDTag;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class RenderableData
{
    public NameIDTag name;
    public List<Vertex> vertices;
    public List<Integer> indices;
    public ShaderObject shader;
    public ArrayList<Texture> textures = new ArrayList<>();
    public Transform transform = Transform.Register(new Vector3f(0.0f, 0.0f, 0.0f));

    public int VBO, VAO, CBO, TBO, EBO;
    public int queuedData = -1;
}