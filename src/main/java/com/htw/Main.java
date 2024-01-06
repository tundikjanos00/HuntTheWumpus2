package com.htw;

import java.io.*;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("=================================");
        System.out.println("|| Welcome to Hunt the Wumpus! ||");
        System.out.println("=================================");
        Scanner scanner = new Scanner(System.in);
        int mode;

        while (true)
        {
            System.out.println("Choose an option:");
            System.out.println("1. Start a new game");
            System.out.println("2. Load a saved game");
            if (scanner.hasNextInt())
            {
                mode = scanner.nextInt();

                if (mode >= 1 && mode <= 2)
                {
                    break;
                }
                else
                {
                    System.out.println("Invalid input. Please enter 1 or 2.");
                }
            }
                else
            {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
            scanner.close();
        }

        switch (mode)
        {
            case 1:
                Game game = new Game(readDifficultyLevel());
                game.startGameLoop();
                break;
            case 2:
                loadGame();
        }
    }

    private static void loadGame()
    {
        Game game;
        FileInputStream fi = null;
        try
        {
            fi = new FileInputStream(new File("gamesave.txt"));

            ObjectInputStream oi = new ObjectInputStream(fi);
            game = (Game) oi.readObject();
            oi.close();
            fi.close();
        }
            catch (IOException | ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        game.startGameLoop();
    }

    private static int readDifficultyLevel()
    {
        Scanner scanner = new Scanner(System.in);
        int difficulty;

        while (true)
        {
            System.out.println("To start a new Game, select the game difficulty:");
            System.out.println("1. Easy");
            System.out.println("2. Medium");
            System.out.println("3. Hard");
            System.out.print("Enter the corresponding number (1, 2, or 3): ");

            if (scanner.hasNextInt())
            {
                difficulty = scanner.nextInt();

                if (difficulty >= 1 && difficulty <= 3)
                {
                    break;
                }
                    else
                {
                    System.out.println("Invalid input. Please enter 1, 2, or 3.");
                }
            }
                else
            {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }

        return difficulty;
    }

}