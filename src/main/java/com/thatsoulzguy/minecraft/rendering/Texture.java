package com.thatsoulzguy.minecraft.rendering;

import org.joml.Vector2f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.IOException;
import java.util.Objects;

public class Texture
{
    public String path;
    public org.newdawn.slick.opengl.Texture texture;
    public Vector2f size = new Vector2f();
    public int textureID;

    public static Texture Register(String path)
    {
        Texture out = new Texture();

        try
        {
            out.path = "/assets/minecraft/" + path;
            out.texture = TextureLoader.getTexture(out.path.split("[.]")[1], Objects.requireNonNull(Texture.class.getResourceAsStream(out.path)), GL11.GL_NEAREST);;
            out.size.x = out.texture.getWidth();
            out.size.y = out.texture.getHeight();
            out.textureID = out.texture.getTextureID();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return out;
    }

    public void CleanUp()
    {
        GL13.glDeleteTextures(textureID);
    }
}