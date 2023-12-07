public class GoldCloud extends SpecialGet{

    public static final String GOLD_CLOUD_IMAGE_FILE = "assets/GoldCloud.gif";
    public static final int GOLD_CLOUD_WIDTH = 70;
    public static final int GOLD_CLOUD_HEIGHT = 40;

    public GoldCloud(){
        this(0, 0);        

    }
    
    public GoldCloud(int x, int y){
        super(x, y, GOLD_CLOUD_WIDTH, GOLD_CLOUD_HEIGHT, GOLD_CLOUD_IMAGE_FILE);  

    }       

}
