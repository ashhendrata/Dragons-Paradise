//Interface to be implemented by any Entities which are "scrollable"
//Scrollable means the implementing Entity is moved by their designated scroll
//speed each time the game window scrolls.
//Scrolling happens at a regular interval so long as the game is being played.
public interface Scrollable {
     
    //Retrieve the distance the Scrollable moves (in pixels) when scrolled
    public int getScrollSpeed();
    //Move the scrollable by its respective scroll speed
    public void scroll();
       
}
