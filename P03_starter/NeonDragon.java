public class NeonDragon extends Avoid {

    // Location of image file to be drawn for a LongDragon
    public static final String NEON_DRAGON_IMAGE_FILE = "assets/neondragon.gif";
    public static final int NEON_DRAGON_WIDTH = 50;
    public static final int NEON_DRAGON_LENGTH = 120;




    public NeonDragon() {
        super(); // Call the default constructor of Avoid
        setImageName(NEON_DRAGON_IMAGE_FILE); // Set the image file for LongDragon
        setWidth(NEON_DRAGON_WIDTH); // Set the width
        setHeight(NEON_DRAGON_LENGTH); 
    }

    public NeonDragon(int x, int y) {
        super(x, y); // Call the parameterized constructor of Avoid
        setImageName(NEON_DRAGON_IMAGE_FILE); // Set the image file for LongDragon
        setWidth(NEON_DRAGON_WIDTH); // Set the width
        setHeight(NEON_DRAGON_LENGTH); 
    }

    // You don't need to redefine getScrollSpeed and scroll methods
    // as they are already inherited from the superclass.


    // Colliding with a LongDragon does not affect the player's score
    public int getPoints() {
        return 0;
    }

    // Colliding with a LongDragon reduces player's HP by 1
    public int getDamage() {
        return -1;
    }
}

