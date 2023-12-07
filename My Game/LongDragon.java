public class LongDragon extends Avoid {

    // Location of image file to be drawn for a LongDragon
    public static final String LONG_DRAGON_IMAGE_FILE = "assets/longdragon.gif";
    public static final String LONG_LEFT_DRAGON_IMAGE_FILE = "assets/LeftLongDragon.gif";
    public static final int LONG_DRAGON_WIDTH = 150;
    public static final int LONG_DRAGON_HEIGHT = 60;




    public LongDragon() {
        this(0, 0, LONG_DRAGON_IMAGE_FILE);
    }

    public LongDragon(int x, int y, String img_file) {
        super(x, y, img_file, LONG_DRAGON_WIDTH, LONG_DRAGON_HEIGHT);
    }





    //Move the avoid left by the scroll speed
    public void scroll(){
        if (getImageName().equals(LONG_DRAGON_IMAGE_FILE)){
            setX(getX() + AVOID_SCROLL_SPEED);
        }
        if (getImageName().equals(LONG_LEFT_DRAGON_IMAGE_FILE)){
            setX(getX() - AVOID_SCROLL_SPEED);
        }
        
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

