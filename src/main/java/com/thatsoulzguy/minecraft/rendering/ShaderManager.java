package com.thatsoulzguy.minecraft.rendering;

import com.thatsoulzguy.minecraft.core.LogLevel;
import com.thatsoulzguy.minecraft.core.Logger;

import java.util.ArrayList;
import java.util.Objects;

public class ShaderManager
{
    private static final ArrayList<ShaderObject> registeredObjects = new ArrayList<>();

    public static void RegisterShader(ShaderObject object)
    {
        Logger.WriteConsole("Registering ShaderObject: '" + object.name + "'.", "ShaderManager", LogLevel.INFO);

        registeredObjects.add(object);
    }

    public static ShaderObject GetShader(String name)
    {
        for(ShaderObject object : registeredObjects)
        {
            if(Objects.equals(object.name, name))
                return (ShaderObject)object.clone();
        }

        return null;
    }

    public static void CleanUp()
    {
        for(ShaderObject object : registeredObjects)
        {
            object.CleanUp();
        }
    }
}