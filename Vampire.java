
package dungeon;


public class Vampire{

    private int[] position;

    public Vampire(int[] position) {
        this.position = position;
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
