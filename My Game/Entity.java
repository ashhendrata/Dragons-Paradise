//An Entity represents some indivdual thing that is drawn to the game window.
//Everything drawn and animated in the game window is an Entity, including the player.
//
//Each time the game window "refreshes" all entities in the game's display list
//are drawn according to their respective attributes.
public abstract class Entity {
    
    //Location of image file to be drawn for the Entity
    private String imageName;
    
    //The height and width of the entity (in pixels) to be drawn to the game window
    //Note that all Entities are ultimately drawn as rectangles in the game window.
    //An entity's image will be stretched to fit its rectangle per its height/width
    //This rectangle is also its "hitbox", governing its boundaries when determining collisions.
    private int height, width;
    //The x and y coordinate of the Entity to be drawn in the game window.
    protected int x, y;
    //Determines if the Entity is visible in the game window or not
    private boolean isVisible;
    
    
    public Entity(int x, int y, int width, int height, String imageName){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.imageName = imageName;
        this.isVisible = true; //by default, all newly instantiated Entities are visible
    }
    
    //Set and retrieve this entity's coordinates
    public int getX(){
        return x;
    }
    
    public void setX(int newX){
        this.x = newX;
    }
    
    public int getY(){
        return y;
    }
    
    public void setY(int newY){
        this.y = newY;
    }
    
    
    //Set and retrieve this entity's dimensions
    public int getHeight(){
        return height;
    }
    
    public void setHeight(int newHeight){
        this.height = newHeight;
    }
    
    public int getWidth(){
        return width;
    }
    
    public void setWidth(int newWidth){
        this.width = newWidth;
    }   
    
    
    //Set and retrieve this entity's image and visibility status
    public String getImageName(){
        return imageName;
    }
    
    public void setImageName(String newImageName){
        this.imageName = newImageName;
    }   
    
    public void setVisible(boolean isVisible){
        this.isVisible = isVisible;
    }
    
    public boolean isVisible(){
        return isVisible;
    }
    
    
    //Checks to see if this Entity is colliding with the argument Entity
    //Meaning any part of the two Entities are overlapping
    public boolean isCollidingWith(Entity other){
        return (
            this.x < other.x + other.width &&
            this.x + this.width > other.x &&
            this.y < other.y + other.height &&
            this.y + this.height > other.y
        );    
    }

        public boolean isCollidingWithInX(Entity other){
        return (
            this.y < other.y + other.height &&
            this.y + this.height > other.y
        );    
    }

    
    //Checks to see if this Entity is colliding with the argument x,y coordinate
    //Meaning whether the argument coordinate is inside this Entity
    public boolean isCollidingWith(int x, int y){
        if (this.x <= x && this.x + this.width >= x){
            return (this.y <= y && this.y + this.height >= y);
        }
        return false;
    }
    
}
