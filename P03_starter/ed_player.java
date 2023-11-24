// The entity that the human player controls in the game window
// The player moves in reaction to player input
public class ed_player extends Player {

// Location of the image file to be drawn for a Player
    public static final String PLAYER_IMAGE_FILE = "assets/edballoon.gif";
    // Dimensions of the Player
    public static final int PLAYER_WIDTH = 60;
    public static final int PLAYER_HEIGHT = 120;
    // Default speed that the Player moves (in pixels) each time the user moves it
    public static final int DEFAULT_MOVEMENT_SPEED = 7;
    // Starting hit points
    public static final int STARTING_HP = 200;

    // Current movement speed
    private int movementSpeed;
    // Remaining Hit Points (HP) -- indicates the number of "hits" (i.e., collisions
    // with Avoids) that the player can take before the game is over
    private int hp;

    public ed_player() {
        super(); // Call the default constructor of the superclass
        this.movementSpeed = DEFAULT_MOVEMENT_SPEED;
        setHP(200);
        setImageName(PLAYER_IMAGE_FILE); // Set the image file for the player
        setWidth(PLAYER_WIDTH); // Set the width
        setHeight(PLAYER_HEIGHT); // Set the height
    }

    public ed_player(int x, int y) {
        super(x, y); // Call the parameterized constructor of the superclass
        setHP(200);
        this.movementSpeed = DEFAULT_MOVEMENT_SPEED;
        setImageName(PLAYER_IMAGE_FILE); // Set the image file for the player
        setWidth(PLAYER_WIDTH); // Set the width
        setHeight(PLAYER_HEIGHT); // Set the height
    }
}
