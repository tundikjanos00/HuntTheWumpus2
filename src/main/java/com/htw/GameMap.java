package com.htw;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class GameMap implements Serializable
{

    int length;
    int width;

    private int[][] map;
    private int pits;
    private int[] wumpusPosition;
    private int arrows;
    Random rand = new Random();
    private boolean isAliveWumpus = true;

    private int[] initialPosition = new int[2];
    public GameMap(int difficulty)
    {
        if (difficulty == 1)
        {
            this.width = 5;
            this.length = 5;
            this.pits = 3;
            this.arrows = 3;
        }
        else
            if (difficulty == 2)
        {
            this.width = 7;
            this.length = 7;
            this.pits = 5;
            this.arrows = 2;
        }
        else
            if (difficulty == 3)
        {
            this.width = 10;
            this.length = 10;
            this.pits = 10;
            this.arrows = 1;
        }

        map = new int[length][width];
        wumpusPosition = new int[2];
        populateRandomly();
    }

    private void populateRandomly()
    {
        for (int[] ints : map)
        {
            Arrays.fill(ints, 0);
        }
        int pitsLeft = pits;
        while (pitsLeft > 0)
        {
            int row = rand.nextInt(width);
            int col = rand.nextInt(length);
            if (map[row][col] == 0)
            {
                map[row][col] = 1;
                pitsLeft--;
            }
        }
        int wumpusLeft = 1;
        while (wumpusLeft > 0)
        {
            int row = rand.nextInt(width);
            int col = rand.nextInt(length);
            if (map[row][col] == 0)
            {
                map[row][col] = 2;
                wumpusPosition[0] = row;
                wumpusPosition[1] = col;
                wumpusLeft--;
            }
        }
        int goldLeft = 1;
        while (goldLeft > 0)
        {
            int row = rand.nextInt(width);
            int col = rand.nextInt(length);
            if (map[row][col] == 0)
            {
                map[row][col] = 3;
                goldLeft--;
            }
        }
        int playerLeft = 1;
        while (playerLeft > 0)
        {
            int row = rand.nextInt(width);
            int col = rand.nextInt(length);
            if (map[row][col] == 0)
            {
                map[row][col] = 7;
                playerLeft--;
            }
            initialPosition[0] = row;
            initialPosition[1] = col;
        }
    }

    public int[] getInitialPosition()
    {
        return initialPosition;
    }

    public int setInitialPosition(int[] pos)
    {
        int x = pos[0];
        int y = pos[1];
        int current = map[x][y];
        map[x][y] = 7;
        return current;
    }

    public void scout(int i, int j)
    {
        switch (map[i][j])
        {
            case 1:
                System.out.println("You feel a breeze; a pit is near.");
                break;
            case 2:
                System.out.println("You smell a stench; the wumpus is near.");
                break;
            case 3:
                System.out.println("You see a glitter; the gold is near.");
                break;
        }
    }
    public void printMatrix()
    {
        int rows = map.length;
        int cols = map[0].length;

        System.out.print("   ");
        for (int i = 1; i <= cols; i++)
        {
            System.out.print("   " + i);
        }
        System.out.println();

        System.out.print(" +");
        for (int i = 0; i < cols; i++)
        {
            System.out.print("----+");
        }
        System.out.println();

        for (int i = 0; i < rows; i++)
        {
            System.out.print((i + 1) + "|");
            for (int j = 0; j < cols; j++)
            {
                switch (map[i][j]) {
                    case 1:
                        System.out.print("    |");
                        break;
                    case 2:
                        System.out.print("    |");
                        break;
                    case 3:
                        System.out.print("    |");
                        break;
                    case 7:
                        System.out.print(" P  |");
                        break;
                    default:
                        System.out.print("    |");
                        break;
                }
            }
            System.out.println();

            System.out.print(" |");
            for (int k = 0; k < cols; k++)
            {
                System.out.print("----|");
            }
            System.out.println();
        }
    }
    public void removePlayer()
    {
        map[initialPosition[0]][initialPosition[1]] = 0;
    }
    public int getCase(int[] pos)
    {
        return map[pos[0]][pos[1]];
    }
    public int[] getWumpusPosition()
    {
        return wumpusPosition;
    }
    public void removeWumpus()
    {
        if (isAliveWumpus) {
            map[wumpusPosition[0]][wumpusPosition[1]] = 0;
            System.out.println("You killed the Wumpus! Good job!");
        }
        isAliveWumpus = false;
    }
    public boolean shootArrow()
    {
        if (this.arrows > 0)
        {
            this.arrows--;
            return true;
        }
        System.out.println("You have no more arrows!");
        return false;
    }
}
