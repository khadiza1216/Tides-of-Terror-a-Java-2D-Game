import java.awt.Graphics2D;
import java.awt.Image;
public class ammo extends GameObject {
    float dx,dy;

    public ammo (float x,float y,float dx,float dy,Image image){
        super(x,y,40,40,image);
        this.dx=dx;
        this.dy=dy;

    }
    public void update(){
        x += dx;
        y += dy;
    }
    
    public void draw(Graphics2D g){
      g.drawImage(image,(int)x,(int)y,width,height,null);
    }
}
