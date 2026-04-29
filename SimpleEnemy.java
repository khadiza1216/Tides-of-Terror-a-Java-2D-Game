import java.awt.Image;
import java.awt.Graphics2D;


public class SimpleEnemy extends Enemy {
    public SimpleEnemy(int x,int y, float SpeedMultiplier, int damage,Image image){
        super(x, y, 80, 80, 2.0f * SpeedMultiplier, damage, image);
    }
    @Override
    public void update(float playerX, float playerY){
        float dx = playerX-x;
        float dy = playerY-y;
        float length = (float)Math.sqrt(dx*dx + dy*dy);

        if(length >0){
            dx = dx/ length*speed;
            dy = dy/ length*speed;
            x += dx;
            y += dy;
        }
    }

    @Override
    public void draw(Graphics2D g2d){
        
        g2d.drawImage(image, (int) x, (int) y, width, height, null);
    }
}
