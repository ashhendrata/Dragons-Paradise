//A SpecialGet is a rare kind of Get that spawns more infrequently than the regular Get
//When consumed, SpecialGets restores the Player's HP in addition to awarding points
//Otherwise, behaves the same as a regular Get
public class SpecialGet extends Get{
    
    //Location of image file to be drawn for a SpecialGet
    public static final String SPECIAL_GET_IMAGE_FILE = "assets/special_get.gif";
    
    public SpecialGet(){
        this(0, 0);        
    }
    
    public SpecialGet(int x, int y){
        super(x, y, SPECIAL_GET_IMAGE_FILE);  
    }
    
    public SpecialGet(int x, int y, String imageFile){
        super(x, y, imageFile);  
    }
    
    
    public int increaseHP(){
        return 1;
    }
    
}
