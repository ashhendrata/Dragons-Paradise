//Interface to be implemented by any Entities which are "consumable"
//Collectable means two things happen when the player collides with the Entity:
//   -The consumable entity collided with is destroyed
//   -The player's score and/or HP are modified upon collision.
public interface Collectable {
    
    //The amount that the player's score is modified (positive or negative) 
    //Ex: if colliding with this entity would increase the Player's score by 3 points,
    //     this method would return 3 
    public int getPoints();
    //The amount that the player's HP is modified (positive or negative)
    //Ex: if colliding with this entity would reduce the Player's HP by 2 points,
    //     this method would return -2
    public int getDamage();
    
}
