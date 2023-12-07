//Avoids are entities the player needs to avoid colliding with.
//If a player collides with an avoid, it reduces the players Hit Points (HP).
public class Avoid extends Entity implements Collectable, Scrollable {
    
    //Location of image file to be drawn for an Avoid
    public static final String AVOID_IMAGE_FILE = "assets/avoid.gif";
    //Dimensions of the Avoid    
    public static final int AVOID_WIDTH = 75;
    public static final int AVOID_HEIGHT = 75;
    //Speed that the avoid moves each time the game scrolls
    public static final int AVOID_SCROLL_SPEED = 5;
    
    public Avoid(){
        this(0, 0);        
    }
    
    public Avoid(int x, int y){
        super(x, y, AVOID_WIDTH, AVOID_HEIGHT, AVOID_IMAGE_FILE);  
    }

    public Avoid(int x, int y, String img_file, int width, int height){
        super(x, y, width, height, img_file);  
    }


    public int getScrollSpeed(){
        return AVOID_SCROLL_SPEED;
    }
    
    //Move the avoid left by the scroll speed
    public void scroll(){
        setX(getX() - AVOID_SCROLL_SPEED);
    }
    
    //Colliding with an Avoid does not affect the player's score
    public int getPoints(){
       return 0;
    }
    
    //Colliding with an Avoid Reduces players HP by 1
    public int getDamage(){
        return -1;
    }
    
}
