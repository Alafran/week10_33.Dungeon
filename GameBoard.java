package dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBoard {

    private int length;
    private int height;
    private int[][] gameBoard;
    private List<Vampire> vampires;
    private Player player;
    private Random random;

    public GameBoard(int length, int height) {
        this.length = length;
        this.height = height;
        this.gameBoard = new int[height][length];
        this.random = new Random();
        this.vampires = new ArrayList<Vampire>();
        this.player = new Player();
        
    }

    //in the 2d array an empty space is a 0, player is 1, vampire is 2
    //iterates through and sets every space to empty
    //then sets the player position
    //then creates vampires and sets their position on the board
    public void createGameBoard(int vampires) {
        for(int l = 0; l < this.height; l++) {
            for(int h = 0; h < this.length; h++) {
                this.gameBoard[l][h] = 0;
            }
        }
        
        int[] playerPosition = this.player.getPosition();
        this.gameBoard[playerPosition[0]][playerPosition[1]] = 1;
        
        createVampires(vampires);
        
    }
    
    //in the 2d array an empty space is a 0, player is 1, vampire is 2
    //updates the positions of all the pieces on the board
    public void updateGameBoard() {
        for(int l = 0; l < this.height; l++) {
            for(int h = 0; h < this.length; h++) {
                this.gameBoard[l][h] = 0;
            }
        }
        
        int[] playerPosition = this.player.getPosition();
        this.gameBoard[playerPosition[0]][playerPosition[1]] = 1;
        
        for(Vampire vampire : this.vampires) {
            int[] vampirePosition = vampire.getPosition();
            this.gameBoard[vampirePosition[0]][vampirePosition[1]] = 2;
        }
    }

    //creates a new vampire and sets its position in the array to 2
    public void createVampires(int amountOfVampires) {
        for (int i = 0; i < amountOfVampires; i++) {
            Vampire vampire = new Vampire(vampiresStartingPosition());
            this.vampires.add(vampire);
            int[] vampirePosition = vampire.getPosition();
            this.gameBoard[vampirePosition[0]][vampirePosition[1]] = 2;
        }
    }

    //checks the vampires starting position is empty before creating one
    public int[] vampiresStartingPosition() {
        boolean spaceOccupied = true;
        int[] tempPosition = new int[2];
        while (spaceOccupied) {
            tempPosition[1] = this.random.nextInt(this.height);
            tempPosition[0] = this.random.nextInt(this.length);
            
            if(this.gameBoard[tempPosition[1]][tempPosition[0]] == 0) {
                spaceOccupied = false;
            }
        }
        return tempPosition;
    }
    
    public boolean spaceOccupied(int[] position) {
        if(this.gameBoard[position[1]][position[0]] == 1 || this.gameBoard[position[1]][position[0]] == 2) {
            return true;
        }
        return false;
    }
    
    public void removeVampires(List<Vampire> toBeRemoved) {
        this.vampires.removeAll(toBeRemoved);
    }

    public int getLength() {
        return this.length;
    }

    public int getHeight() {
        return this.height;
    }

    public int[][] getGameBoard() {
        return this.gameBoard;
    }

    public Player getPlayer() {
        return this.player;
    }
    
    public List getVampires() {
        return this.vampires;
    }
    
}
