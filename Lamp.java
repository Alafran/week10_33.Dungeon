
package dungeon;

public class Lamp {

    private int chargesLeft;    //how many moves the player has
    private boolean isOn;
    
    public Lamp(int charges) {
        this.chargesLeft = charges;
        
        if(this.chargesLeft >= 1) {
            this.isOn = true;
        }
        else {
            this.isOn = false;
        }
    }
    
    public boolean isOn() {
        return this.isOn;
    }
    
    public int getCharges() {
        return this.chargesLeft;
    }
    
    public void useACharge() {
        this.chargesLeft--;
        
        if(this.chargesLeft == 0) {
            this.isOn = false;
        }
    }
}
