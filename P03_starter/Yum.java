public class Yum extends Entity {
        //Location of image file to be drawn for an Avoid
        public static final String YUM_IMAGE_FILE = "assets/yum.gif";
        //Dimensions of the Avoid    
        public static final int YUM_WIDTH = 50;
        public static final int YUM_HEIGHT = 50;
        //Speed that the avoid moves each time the game scrolls
        private int visibilityDuration; 
        
        public Yum(){
            this(0, 0);       
        }
        
        public Yum(int x, int y){
            super(x, y, YUM_WIDTH, YUM_HEIGHT, YUM_IMAGE_FILE);  
            this.visibilityDuration = 100;
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
