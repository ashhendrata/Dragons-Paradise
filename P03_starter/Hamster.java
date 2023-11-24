public class Hamster extends Get{
    
    public static final String BONE_IMAGE_FILE = "assets/hamster.gif";
    public static final int BONE_WIDTH = 30;
    public static final int BONE_HEIGHT = 30;

    public Hamster() {
        super(); // Call the default constructor of Avoid
        setImageName(BONE_IMAGE_FILE); // Set the image file for LongDragon
        setWidth(BONE_WIDTH); // Set the width
        setHeight(BONE_HEIGHT); 
    }

    public Hamster(int x, int y) {
        super(x, y); // Call the parameterized constructor of Avoid
        setImageName(BONE_IMAGE_FILE); // Set the image file for LongDragon
        setWidth(BONE_WIDTH); // Set the width
        setHeight(BONE_HEIGHT); 
    }
    public int getPoints(){
        return 20;
    }


}
