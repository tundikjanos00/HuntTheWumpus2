package com.htw;

import java.io.*;
import java.util.Arrays;

public class Game implements Serializable
{
    private GameMap map;
    private Player player;

    public Game(int difficulty)
    {

        System.out.println("Use the following controls to play:");
        System.out.println("To move up ▲: mu");
        System.out.println("To move down ▼: md");
        System.out.println("To move left ◄: ml");
        System.out.println("To move right ►: mr");
        System.out.println("To shoot up: su");
        System.out.println("To shoot down: sd");
        System.out.println("To shoot left: sl");
        System.out.println("To shoot right: sr");
        System.out.println("Exit and save game: exit");


        this.map = new GameMap(difficulty);
        this.player = new Player();

        player.setPosition(map.getInitialPosition());

    }

    public void startGameLoop()
    {
        while (player.isAlive())
        {
            map.printMatrix();


            System.out.println("You are at, " + Arrays.toString(player.getPosition()));
            scoutSurroundings();
            String move = player.makeAMove();
            switch (move)
            {
                case "mu":
                    moveUp();
                    break;
                case "md":
                    moveDown();
                    break;
                case "ml":
                    moveLeft();
                    break;
                case "mr":
                    moveRight();
                    break;
                case "su":
                    shootUp();
                    break;
                case "sd":
                    shootDown();
                    break;
                case "sl":
                    shootLeft();
                    break;
                case "sr":
                    shootRight();
                    break;
                case "exit":
                    exitAndSave();
                    break;
                default:
                    System.out.println("Unexpected input.");
            }
        }
    }

    private void exitAndSave()
    {
        try
        {
            FileOutputStream f = new FileOutputStream(new File("gamesave.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(this);

            o.close();
            f.close();
            System.exit(0);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        catch (IOException e)
        {
            System.out.println("Error initializing stream");
        }
    }

    private void checkNewPosition(int pos)
    {
        switch (pos)
        {
            case 1:
                System.out.println("You fell down a pit!");
                player.setAlive(false);
            case 2:
                System.out.println("The Wumpus ate you!");
                player.setAlive(false);
                break;
            case 3:
                System.out.println("You got the Gold! You Win");
                player.gotGold();
                System.exit(0);
                break;
        }
    }

    private void shootRight()
    {
        if (map.shootArrow())
        {
            if (map.getWumpusPosition()[1] > player.getPosition()[1])
            {
                map.removeWumpus();
            }
        }
    }

    private void shootLeft()
    {
        if (map.shootArrow())
        {
            if (map.getWumpusPosition()[1] < player.getPosition()[1])
            {
                map.removeWumpus();
            }
        }
    }

    private void shootDown()
    {
        if (map.shootArrow())
        {
            if (map.getWumpusPosition()[0] > player.getPosition()[0])
            {
                map.removeWumpus();
            }
        }
    }

    private void shootUp()
    {
        if (map.shootArrow())
        {
            if (map.getWumpusPosition()[0] < player.getPosition()[0])
            {
                map.removeWumpus();
            }
        }
    }

    private void moveRight()
    {
        if (player.getPositionY() < map.width - 1 )
        {
            map.removePlayer();
            player.setPositionY(player.getPositionY() + 1);
            checkNewPosition(map.setInitialPosition(player.getPosition()));
        }
    }

    private void moveLeft()
    {
        if (player.getPositionY() > 0)
        {
            map.removePlayer();
            player.setPositionY(player.getPositionY() - 1);
            checkNewPosition(map.setInitialPosition(player.getPosition()));
        }
    }

    private void moveDown()
    {
        if (player.getPositionX() < map.length)
        {
            map.removePlayer();
            player.setPositionX(player.getPositionX() + 1);
            checkNewPosition(map.setInitialPosition(player.getPosition()));
        }
    }

    private void moveUp()
    {
        if (player.getPositionX() > 0)
        {
            map.removePlayer();
            player.setPositionX(player.getPositionX() - 1);
            checkNewPosition(map.setInitialPosition(player.getPosition()));
        }
    }

    private void scoutSurroundings()
    {
        int[] position = player.getPosition();
        if (position[0] > 0) {
            map.scout(position[0] - 1, position[1]);
        }
        if (position[0] < map.length - 1)
        {
            map.scout(position[0] + 1, position[1]);
        }
        if (position[1] > 0)
        {
            map.scout(position[0], position[1] - 1);
        }
        if (position[1] < map.width - 1)
        {
            map.scout(position[0], position[1] + 1);
        }
    }
}
