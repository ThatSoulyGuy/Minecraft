package com.thatsoulzguy.minecraft.util;

import java.util.Objects;

public class NameIDTag {
    public String name;
    public String description;
    public String hash;
    public Object reference;

    public static NameIDTag Register(String name, String description, Object reference)
    {
        NameIDTag tag = new NameIDTag();

        tag.name = name;
        tag.description = description;
        tag.hash = Hash.GenerateMD5Hash(name);
        tag.reference = reference;

        return tag;
    }

    public static NameIDTag Register(String name, Object reference)
    {
        NameIDTag tag = new NameIDTag();

        tag.name = name;
        tag.description = "<any>";
        tag.hash = Hash.GenerateMD5Hash(name);
        tag.reference = reference;

        return tag;
    }

    public static NameIDTag Register(Object reference)
    {
        NameIDTag tag = new NameIDTag();

        tag.name = "<any>";
        tag.description = "<any>";
        tag.hash = Hash.GenerateMD5Hash("<any>");
        tag.reference = reference;

        return tag;
    }

    public static boolean IsMatch(NameIDTag tag, NameIDTag other)
    {
        if(!Objects.equals(tag.name, other.name) && tag.reference != other.reference)
            return false;

        return true;
    }
}