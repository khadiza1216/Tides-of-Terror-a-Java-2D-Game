
import java.awt.Image;
import java.awt.Graphics2D;

public class powerup extends GameObject {
    private int type; // 0: Ammo, 1: Health, 2: Speed
    
    public powerup(float x, float y, int type, Image image) {
        super(x, y, 50, 50,image);
        this.type = type;
    }
    
    public void update() {
        // Simple power-up, no movement
    }
    
    public void draw(Graphics2D g2d) {
               g2d.drawImage(image, (int) x, (int) y, 50, 50, null);

    }
    
    public int getType() {
        return type;
    }
}