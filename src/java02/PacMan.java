import java.awt.*;

public class PacMan {
    protected int x, y;
    protected int size;
    protected Color bcolor = Color.yellow;

    PacMan(int x0, int y0, int s0){
        this.setData(x0, y0, s0);
    }

    PacMan(int x0, int y0){
        this(x0, y0, 60);
        this.bcolor = Color.magenta;
    }
    void setData(int x0, int y0, int s0){
    x = x0;
    y = y0;
    size = s0;
    }
    void draw(Graphics g){
        g.setColor(bcolor);
        g.fillArc(x, y, size, size, 45, 300);
        g.setColor(Color.black);
        g.fillOval(x+size/2, y+size/12, size/6, size/6);
    }
    void move(int dx, int dy){
        x = x + dy;
        y = y + dy;
    }
    void move(int d){
        this.move(d,d);
    }
}