package xyz.javase.swe300.project1;

import java.io.IOException;
import java.util.Scanner;

/**
 * Programming Challenge:  LEQEX
 * @author merlin
 */
public class Project1
{

    private int[] data;
    private int count = 0;
    private  boolean[] t;
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        new Project1().run();
    }

    void run()
    {
    	System.out.println("Starting");
        Scanner x = new Scanner(System.in);
        int n = x.nextInt();
        for (int i = 0; i < n; i++)
        {
            data = readInProblem(x, x.nextInt());
            System.out.println(max(data));
        }
    }

    int[] readInProblem(Scanner x, int sz)
    {
        int[] data = new int[sz];
        for (int i = 0; i < sz; i++) data[i] = x.nextInt();
        return data;
    }

    int max(int[] i)
    {
        int mx = 0;
        int curr = 0;
        while (maxPossible(i.length - curr) > mx)
        {
            int currMax = max(i, curr);
            if (currMax > mx)
            mx = currMax;
            curr++;
        }
        return mx;
    }

    int max(int[] i, int s)
    {
        int x = 0;
        for (int num = 1; num <= maxPossible(data.length - s); num++)

        if (works(s, num*2+1))
        x = num;

        return x;
    }

    int maxPossible(int count)
    {
        int flat = count - 1;
        return flat / 2;
    }

    boolean works(int s, int l)
    {
        t = new boolean[l];
        int f = 0;
        count = 0;
        while (count < maxPossible(l))

        try
        {
            t[f] = true;
            int p = next(t, f);;
            while (data[s + f] != data[s + p])
            p = next(t, p);
            count++;
            t[p] = true;
            f = next(t, f);
        } catch (Nope e)
        {
            return false;
        }
        return true;
    }

    int next(boolean[] u, int x) throws Nope
    {
        x = x+1;
        while (x < u.length && u[x])
            x++;
        if (x == u.length) throw new Nope();
        return x;
    }

    private class Nope extends Exception
    {
    }
}
