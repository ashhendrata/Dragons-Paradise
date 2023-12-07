public class Pig extends Get{
        
    //Location of image file to be drawn for a Get
    public static final String PIG_IMAGE_FILE = "assets/Pig.gif";
    //Dimensions of the Get  
    public static final int PIG_WIDTH = 70;
    public static final int PIG_HEIGHT = 70;
    //Speed that the Get moves (in pixels) each time the game scrolls
    public static final int PIG_SCROLL_SPEED = 5;
    //Amount of points received when player collides with a Get
    public static final int PIG_POINT_VALUE = 20;

    public Pig(){
        this(0, 0);
    }

    public Pig(int x, int y){
        super(x, y, PIG_WIDTH, PIG_HEIGHT, PIG_IMAGE_FILE);
    }



}
