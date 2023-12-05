// The entity that the human player controls in the game window
// The player moves in reaction to player input
public class DragonPlayer extends Player {

// Location of the image file to be drawn for a Player
    public static final String LEFT_DRAGON_IMAGE_FILE = "assets/OrangeDragon.gif";
    public static final String RIGHT_DRAGON_IMAGE_FILE = "assets/RightFacingDragon.gif";
    
    public static final String RIGHT_RAINBOW_DRAGON_IMAGE_FILE = "assets/RainbowRightFacingDragon.gif";
    public static final String LEFT_RAINBOW_DRAGON_IMAGE_FILE = "assets/RainbowLeftFacingDragon.gif";

    // Dimensions of the Player
    public static final int PLAYER_WIDTH = 120;
    public static final int PLAYER_HEIGHT = 100;
    // Default speed that the Player moves (in pixels) each time the user moves it
    public static final int DEFAULT_MOVEMENT_SPEED = 7;
    // Starting hit points
    public static final int STARTING_HP = 5;

    private int rainbowtime;
    private boolean rainbowState;

    public DragonPlayer() {
        this(0, 0);
    }

    public DragonPlayer(int x, int y) {
        super(x, y, LEFT_DRAGON_IMAGE_FILE, STARTING_HP, PLAYER_WIDTH, PLAYER_HEIGHT, DEFAULT_MOVEMENT_SPEED);
        this.rainbowtime = 0;
        this.rainbowState = false;

    }

    public void setRightFacingDragon(){
        if (!this.rainbowState){
            setImageName(RIGHT_DRAGON_IMAGE_FILE);
        } else {
            setImageName(RIGHT_RAINBOW_DRAGON_IMAGE_FILE);
        }
        
    }

    public void setLeftFacingDragon(){
        if (!this.rainbowState){
            setImageName(LEFT_DRAGON_IMAGE_FILE);
        } else {
            setImageName(LEFT_RAINBOW_DRAGON_IMAGE_FILE);
        }    }

    public String getDragonOrientation(){
        if (getImageName().equals(RIGHT_DRAGON_IMAGE_FILE) || getImageName().equals(RIGHT_RAINBOW_DRAGON_IMAGE_FILE)){
            return "RIGHT";
        } 
        if (getImageName().equals(LEFT_DRAGON_IMAGE_FILE) || getImageName().equals(LEFT_RAINBOW_DRAGON_IMAGE_FILE)){
            return "LEFT";
        } 
        throw new IllegalStateException("Unexpected dragon orientation: " + getImageName());

    }

    public void setRainbowState(boolean bool){
        rainbowState = bool;
    }


    public int getRainbowTime(){
        return this.rainbowtime;
    }
    
    public void setRainbowTime(int newDuration){
        this.rainbowtime = newDuration;
    }


}
