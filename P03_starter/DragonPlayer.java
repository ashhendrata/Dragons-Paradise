// The entity that the human player controls in the game window
// The player moves in reaction to player input
public class DragonPlayer extends Player {

// Location of the image file to be drawn for a Player
    public static final String LEFT_DRAGON_IMAGE_FILE = "assets/OrangeDragon.gif";
    public static final String RIGHT_DRAGON_IMAGE_FILE = "assets/RightFacingDragon.gif";

    // Dimensions of the Player
    public static final int PLAYER_WIDTH = 130;
    public static final int PLAYER_HEIGHT = 120;
    // Default speed that the Player moves (in pixels) each time the user moves it
    public static final int DEFAULT_MOVEMENT_SPEED = 7;
    // Starting hit points
    public static final int STARTING_HP = 3;

    public DragonPlayer() {
        this(0, 0);
    }

    public DragonPlayer(int x, int y) {
        super(x, y, LEFT_DRAGON_IMAGE_FILE, STARTING_HP, PLAYER_WIDTH, PLAYER_HEIGHT, DEFAULT_MOVEMENT_SPEED);
    }

    public void setRightFacingDragon(){
        setImageName(RIGHT_DRAGON_IMAGE_FILE);
    }

    public void setLeftFacingDragon(){
        setImageName(LEFT_DRAGON_IMAGE_FILE);
    }

    public String getDragonOrientation(){
        if (getImageName().equals(RIGHT_DRAGON_IMAGE_FILE)){
            return "RIGHT";
        } 
        if (getImageName().equals(LEFT_DRAGON_IMAGE_FILE)){
            return "LEFT";
        } 
        throw new IllegalStateException("Unexpected dragon orientation: " + getImageName());

    }
}
