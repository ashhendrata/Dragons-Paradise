public class BloodSplat extends Entity implements Collectable {
        //Location of image file to be drawn for an Avoid
        public static final String BLOOD_IMAGE_FILE = "assets/bloodsplat.gif";
        //Dimensions of the Avoid    
        public static final int BLOOD_WIDTH = 75;
        public static final int BLOOD_HEIGHT = 75;
        //Speed that the avoid moves each time the game scrolls
        private int visibilityDuration; 
        
        public BloodSplat(){
            this(0, 0);   
            this.visibilityDuration = 120;     
        }
        
        public BloodSplat(int x, int y){
            super(x, y, BLOOD_WIDTH, BLOOD_HEIGHT, BLOOD_IMAGE_FILE);  
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

       public boolean isCollidingWith(Entity other){
        return false; // does not matter if it collides with other entities as this is just a visual effect
    }
 
        
}
