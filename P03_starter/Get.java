//Gets are entities that the player wants to collide with, as they increase
//their score.
public class Get extends Entity implements Collectable, Scrollable {
    
    //Location of image file to be drawn for a Get
    public static final String GET_IMAGE_FILE = "assets/get.gif";
    //Dimensions of the Get  
    public static final int GET_WIDTH = 50;
    public static final int GET_HEIGHT = 50;
    //Speed that the Get moves (in pixels) each time the game scrolls
    public static final int GET_SCROLL_SPEED = 5;
    //Amount of points received when player collides with a Get
    public static final int GET_POINT_VALUE = 20;
    
    
    public Get(){
        this(0, 0);        
    }
    
    public Get(int x, int y){
        super(x, y, GET_WIDTH, GET_HEIGHT, GET_IMAGE_FILE);  
    }
    
    public Get(int x, int y, String imageFileName){
        super(x, y, GET_WIDTH, GET_HEIGHT, imageFileName);
    }
    
    public int getScrollSpeed(){
        return GET_SCROLL_SPEED;
    }
    
    //Move the Get left by its scroll speed
    public void scroll(){
        setX(getX() - GET_SCROLL_SPEED);
    }
    
    //Colliding with a Get increases the player's score by the specified amount
    public int getPoints(){
        return GET_POINT_VALUE;
    }
    
    //Colliding with a Get does not affect the player's HP
    public int getDamage(){
        return 0;
    }
    
}
