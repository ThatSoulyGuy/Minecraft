package com.thatsoulzguy.minecraft.util;

import com.thatsoulzguy.minecraft.core.LogLevel;
import com.thatsoulzguy.minecraft.core.Logger;
import com.thatsoulzguy.minecraft.rendering.Texture;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.newdawn.slick.util.Log;

public class TextureManager
{
    public static Texture atlas = Texture.Register("textures/atlases/terrain.png");
    public static int tilePixelSize = 16;
    public static final float PADDING_RATIO = 1.0f/256.0f;

    public static Vector2f[] GetTextureCoordinates(Vector2i position)
    {
        float perTextureSize = (float)tilePixelSize / atlas.size.y;

        float u0 = position.x * perTextureSize + PADDING_RATIO;
        float v0 = position.y * perTextureSize + PADDING_RATIO;

        float u1 = u0 + perTextureSize - 2 * PADDING_RATIO;
        float v1 = v0 + perTextureSize - 2 * PADDING_RATIO;

        return new Vector2f[]
        {
            new Vector2f(u0, v0),
            new Vector2f(u1, v0),
            new Vector2f(u1, v1),
            new Vector2f(u0, v1)
        };
    }

    public static Vector2f[] GetTextureCoordinatesRotated(Vector2i position)
    {
        float perTextureSize = (float)tilePixelSize / atlas.size.y;

        float u0 = position.x * perTextureSize + PADDING_RATIO;
        float v0 = position.y * perTextureSize + PADDING_RATIO;

        float u1 = u0 + perTextureSize - 2 * PADDING_RATIO;
        float v1 = v0 + perTextureSize - 2 * PADDING_RATIO;

        return new Vector2f[]
        {
            new Vector2f(u1, v0), // Vertex 1 (bottom-right)
            new Vector2f(u1, v1), // Vertex 2 (top-right)
            new Vector2f(u0, v1), // Vertex 3 (top-left)
            new Vector2f(u0, v0)  // Vertex 4 (bottom-left)
        };
    }

    public static Vector2f GetTextureCoordinates(String name)
    {
        return null;
    }
}