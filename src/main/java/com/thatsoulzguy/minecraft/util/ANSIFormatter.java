package com.thatsoulzguy.minecraft.util;

import java.util.Formatter;

public class ANSIFormatter
{
    public static String Format(String input, Object... args)
    {
        String out = new Formatter().format(input, args).toString();
        return out.replace("&0", "\u001B[30m").replace("&1", "\u001B[34m").replace("&2", "\u001B[32m").replace("&3", "\u001B[36m").replace("&4", "\u001B[31m").replace("&5", "\u001B[35m").replace("&6", "\u001B[33m").replace("&f", "\u001B[37m").replace("&r", "\u001B[0m") + "\u001B[0m";
    }
}