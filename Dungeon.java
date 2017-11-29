package dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

/*
Welcome to my Dungeon! This is the final project of week 10 of Mooc.FI java course.
The dungeon has a gameboard represented by a 2d array with given height and length.
There are vampires in my dungeon that will kill the player if there is no light
    left in the players' lamp.
Player can move around the gameboard by entering a series of movement commands
    with WASD.
After the player moves the vampires will move randomly around the dungeon
    if the user gives a true value to vampiresMove
Collide with all the vampires to win
Run out of lamp first and you lose!

 */
public class Dungeon {

    private GameBoard gameBoard;
    private Lamp lamp;
    private Scanner reader;
    private Random random;
    private boolean vampiresMove;

    public Dungeon(int length, int height, int vampires, int moves, boolean vampiresMove) {
        this.gameBoard = new GameBoard(length, height);
        gameBoard.createGameBoard(vampires);
        this.lamp = new Lamp(moves);
        this.reader = new Scanner(System.in);
        this.random = new Random();
        this.vampiresMove = vampiresMove;
    }

    public void run() {
        int[][] theGameBoard = this.gameBoard.getGameBoard();

        while (this.lamp.isOn()) {

            //prints charges left
            System.out.println(this.lamp.getCharges());
            System.out.println("");

            //gets the players position that will be updated every loop
            int[] playerPosition = this.gameBoard.getPlayer().getPosition();

            //prints the player location on the game board
            System.out.println("@ " + playerPosition[1] + " " + playerPosition[0]);

            //gets the list of vampires that will be updated every loop
            List<Vampire> vampireList = this.gameBoard.getVampires();

            //prints each vampires position
            for (Vampire vampire : vampireList) {
                int[] vampPosition = vampire.getPosition();
                System.out.println("v " + vampPosition[0] + " " + vampPosition[1]);
            }
            System.out.println("");

            //this loop prints the actual status of the gameboard
            for (int l = 0; l < this.gameBoard.getHeight(); l++) {
                for (int h = 0; h < this.gameBoard.getLength(); h++) {
                    if (theGameBoard[l][h] == 0) {
                        System.out.print(".");
                    }
                    if (theGameBoard[l][h] == 1) {
                        System.out.print("@");
                    }
                    if (theGameBoard[l][h] == 2) {
                        System.out.print("v");
                    }
                }
                System.out.println("");
            }

            //after gameboard and state is printed, player inputs commands
            String playerInput = reader.nextLine();
            movePlayer(playerInput);
            
            //after all the moves have been executed, checks if there are any vampires left
            if (vampireList.isEmpty()) {
                break;
            }
            
            //if there are still vampires, it uses a charge and updates the gameboard
            this.lamp.useACharge();
            this.gameBoard.updateGameBoard();
        }
        if (this.lamp.isOn()) {
            System.out.println("YOU WIN!");
        } else {
            System.out.println("YOU LOSE!");
        }
    }

    public void movePlayer(String playerInput) {
        for (int i = 0; i < playerInput.length(); i++) {
            //gets each individual command
            char move = playerInput.charAt(i);
            //updates the player position every iteration
            int[] playerPosition = this.gameBoard.getPlayer().getPosition();

            //first checks that the move wont throw out of bounds exception
            if (move == 'w') {
                if (playerPosition[0] - 1 < 0 || playerPosition[0] - 1 >= this.gameBoard.getHeight()) {
                } else {
                    this.gameBoard.getPlayer().moveUpOne();
                }
            }

            //first checks that the move wont throw out of bounds exception
            if (move == 's') {
                if (playerPosition[0] + 1 < 0 || playerPosition[0] + 1 >= this.gameBoard.getHeight()) {

                } else {
                    this.gameBoard.getPlayer().moveDownOne();
                }
            }

            //first checks that the move wont throw out of bounds exception
            if (move == 'a') {
                if (playerPosition[1] - 1 < 0 || playerPosition[1] - 1 >= this.gameBoard.getLength()) {

                } else {
                    this.gameBoard.getPlayer().moveLeftOne();
                }
            }

            //first checks that the move wont throw out of bounds exception
            if (move == 'd') {
                if (playerPosition[1] + 1 < 0 || playerPosition[1] + 1 >= this.gameBoard.getLength()) {

                } else {
                    this.gameBoard.getPlayer().moveRightOne();
                }
            }

            //checks to see if the player is on a vampire space after move
            testCollision();
            if (this.vampiresMove) {
                moveVampires();
            }
        }
    }

    //tests if the player position is the same as any vampire position
    public void testCollision() {
        int[] playerPosition = this.gameBoard.getPlayer().getPosition();

        List<Vampire> vampireList = this.gameBoard.getVampires();
        List<Vampire> vampiresToBeRemoved = new ArrayList<Vampire>();

        //checks if the player and vampires occupy the same space
        //if so adds them to a list to be removed with the removeVampires method
        for (Vampire vampire : vampireList) {
            int[] vampirePosition = vampire.getPosition();
            if (Arrays.equals(vampirePosition, playerPosition)) {
                vampiresToBeRemoved.add(vampire);
            }
        }

        this.gameBoard.removeVampires(vampiresToBeRemoved);
    }

    public void moveVampires() {
        List<Vampire> vampireList = this.gameBoard.getVampires();
        
        for (Vampire vampire : vampireList) {
            int[] vampirePosition = vampire.getPosition(); //each individual vampires position 
            
            int numToBeMoved = this.random.nextInt(4); //gets a random num 0-3
            
            //checks if the move will put the position of the vampire out of the array
            //then checks to see if the space is empty
            if (numToBeMoved == 0) {
                if (!(vampirePosition[0] + 1 < 0 || vampirePosition[0] + 1 >= this.gameBoard.getHeight())
                        && !this.gameBoard.spaceOccupied(vampirePosition)) {
                    vampire.moveDownOne();
                }
            }
            
            //checks if the move will put the position of the vampire out of the array
            //then checks to see if the space is empty
            if (numToBeMoved == 1) {
                if (!(vampirePosition[0] - 1 < 0 || vampirePosition[0] - 1 >= this.gameBoard.getHeight())
                       && !this.gameBoard.spaceOccupied(vampirePosition)) {
                    vampire.moveUpOne();
                }
            }
            
            //checks if the move will put the position of the vampire out of the array
            //then checks to see if the space is empty
            if (numToBeMoved == 2) {
                if (!(vampirePosition[1] + 1 < 0 || vampirePosition[1] + 1 >= this.gameBoard.getLength())
                        && !this.gameBoard.spaceOccupied(vampirePosition)) {
                    vampire.moveRightOne();
                }}

            //checks if the move will put the position of the vampire out of the array
            //then checks to see if the space is empty
            if (numToBeMoved == 3) {
                if (!(vampirePosition[1] - 1 < 0 || vampirePosition[1] - 1 >= this.gameBoard.getLength())
                       && !this.gameBoard.spaceOccupied(vampirePosition)) {
                    vampire.moveLeftOne();
                }
            }
        }
    }
}
