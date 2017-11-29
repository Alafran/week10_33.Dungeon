

package dungeon;


public class Player{

    private int[] position;
    
    public Player() {
        this.position = new int[2];
        this.position[0] = 0;
        this.position[1] = 0;
    }
    

    public void moveDownOne() {
        this.position[0]++;
    }
    
    public void moveUpOne() {
        this.position[0]--;
    }
    
    public void moveRightOne() {
        this.position[1]++;
    }
    
    public void moveLeftOne() {
        this.position[1]--;
    }


    public int[] getPosition() {
        return this.position;
    }

}
