public class BloodSplat extends Entity implements Collectable {
        //Location of image file to be drawn for an Avoid
        public static final String AVOID_IMAGE_FILE = "assets/bloodsplat.gif";
        //Dimensions of the Avoid    
        public static final int AVOID_WIDTH = 75;
        public static final int AVOID_HEIGHT = 75;
        //Speed that the avoid moves each time the game scrolls
        private int visibilityDuration; 
        
        public BloodSplat(){
            this(0, 0);   
            this.visibilityDuration = 120;     
        }
        
        public BloodSplat(int x, int y){
            super(x, y, AVOID_WIDTH, AVOID_HEIGHT, AVOID_IMAGE_FILE);  
            this.visibilityDuration = 120;
        }

        public int getVisibilityDuration(){
            return this.visibilityDuration;
        }
        
        public void setVisibilityDuration(int newDuration){
            this.visibilityDuration = newDuration;
        }
    
        public int getScrollSpeed(){
            return 0;
        }
        
        //Move the avoid left by the scroll speed
        public void scroll(){
            setX(getX());
        }
        
        //Colliding with an Avoid does not affect the player's score
        public int getPoints(){
           return 0;
        }

       public int getDamage(){
        return 0;
       }
 
        
}
