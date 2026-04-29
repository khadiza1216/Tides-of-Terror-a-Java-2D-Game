import java.awt.Image;

public class GameObject {

   public float x,y;
   public int width,height;
   public Image image;

    public GameObject(float x, float y, int width,int height, Image image) {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.image=image;
    }
    public boolean intersects(GameObject object){
        return x < object.x + object.width &&  
               x + width > object.x && 
               y < object.y + object.height && 
               y + height > object.y ; 

    }   
}

/*player left point x,y..... x+width=right.... y+height=bottom
 * object.x,object,y..... 
 */
