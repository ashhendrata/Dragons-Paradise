public class HeliumTank extends SpecialGet {

    public static final String HELIUM_IMAGE_FILE = "assets/heliumtank.png";
    public static final int TANK_WIDTH = 30;
    public static final int TANK_HEIGHT = 30;
    
    public HeliumTank(){
        super();        

    }
    
    public HeliumTank(int x, int y){
        super(x, y, HELIUM_IMAGE_FILE);  

    }
    
    
    public int increaseHP(){
        return 1;
    }
}
