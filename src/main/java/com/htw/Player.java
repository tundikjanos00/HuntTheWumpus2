package com.htw;

import java.io.Serializable;
import java.util.Scanner;

public class Player implements Serializable
{
    private int[] position = new int[2];
    private boolean isAlive = true;
    public int[] getPosition()
    {
        return position;
    }

    public void setPosition(int[] position)
    {
        this.position = position;
    }

    public void setPosition(int i, int j)
    {
        this.position[0] = i;
        this.position[1] = j;
    }

    public void setPositionX(int i)
    {
        this.position[0] = i;
    }

    public int getPositionX()
    {
        return this.position[0];
    }

    public int getPositionY()
    {
        return this.position[1];
    }

    public void setPositionY(int j)
    {
        this.position[1] = j;
    }

    public boolean isAlive()
    {
        return isAlive;
    }

    public void setAlive(boolean alive)
    {
        isAlive = alive;
        if (!alive)
        {
            System.out.println("You Lost The Game!");
            System.exit(0);
        }
    }

    public String makeAMove()
    {
        System.out.println("Play a move:");
        return readAndValidateInput();
    }
    public static String readAndValidateInput()
    {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true)
        {
            userInput = scanner.nextLine().trim().toLowerCase();
            if (userInput.matches("^(mu|md|ml|mr|su|sd|sl|sr|exit)$"))
            {
                break;
            }
                else
            {
                System.out.println("Invalid input. Please try again.");
            }
        }

        return userInput;
    }

    public void gotGold()
    {
        System.out.println("You got the Gold!");
    }
}
