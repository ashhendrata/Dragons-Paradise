public class LongDragon extends Avoid {

    // Location of image file to be drawn for a LongDragon
    public static final String LONG_DRAGON_IMAGE_FILE = "assets/longdragon.gif";
    public static final int LONG_DRAGON_WIDTH = 150;
    public static final int LONG_DRAGON_LENGTH = 60;




    public LongDragon() {
        super(); // Call the default constructor of Avoid
        setImageName(LONG_DRAGON_IMAGE_FILE); // Set the image file for LongDragon
        setWidth(LONG_DRAGON_WIDTH); // Set the width
        setHeight(LONG_DRAGON_LENGTH); 
    }

    public LongDragon(int x, int y) {
        super(x, y); // Call the parameterized constructor of Avoid
        setImageName(LONG_DRAGON_IMAGE_FILE); // Set the image file for LongDragon
        setWidth(LONG_DRAGON_WIDTH); // Set the width
        setHeight(LONG_DRAGON_LENGTH); 
    }

    //Move the avoid left by the scroll speed
    public void scroll(){
        setX(getX() + AVOID_SCROLL_SPEED);
    }

    // Colliding with a LongDragon does not affect the player's score
    public int getPoints() {
        return 0;
    }

    // Colliding with a LongDragon reduces player's HP by 1
    public int getDamage() {
        return -1;
    }
}

