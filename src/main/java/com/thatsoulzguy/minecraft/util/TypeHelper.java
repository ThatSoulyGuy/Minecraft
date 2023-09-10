package com.thatsoulzguy.minecraft.util;

import com.thatsoulzguy.minecraft.rendering.Vertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypeHelper
{
    public static boolean Int2Bool(int value)
    {
        return value == 1;
    }

    public static int Bool2Int(boolean value)
    {
        if(value)
            return 1;
        else
            return 0;
    }

    public static int[] IntList2Array(ArrayList<Integer> input)
    {
        int[] out = new int[input.size()];

        for (int i = 0; i < input.size(); i++)
            out[i] = input.get(i);

        return out;
    }

    public static float[] FloatList2Array(ArrayList<Float> input)
    {
        float[] out = new float[input.size()];

        for (int f = 0; f < input.size(); f++)
            out[f] = input.get(f);

        return out;
    }

    public static double[] DoubleList2Array(ArrayList<Double> input)
    {
        double[] out = new double[input.size()];

        for (int d = 0; d < input.size(); d++)
            out[d] = input.get(d);

        return out;
    }

    public static ArrayList<Integer> IntArray2List(int[] input)
    {
        ArrayList<Integer> out = new ArrayList<>();

        for (int i : input)
            out.add(i);

        return out;
    }

    public static ArrayList<Float> FloatArray2List(float[] input)
    {
        ArrayList<Float> out = new ArrayList<>();

        for (float f : input)
            out.add(f);

        return out;
    }

    public static ArrayList<Double> DoubleArray2List(double[] input)
    {
        ArrayList<Double> out = new ArrayList<>();

        for (double d : input)
            out.add(d);

        return out;
    }

    public static ArrayList<Vertex> VertexArray2List(Vertex[] list)
    {
        return new ArrayList<>(Arrays.stream(list).toList());
    }
}