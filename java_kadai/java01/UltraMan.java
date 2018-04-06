public class UltraMan {
    int power;
    String word;

    UltraMan(int p0, String w0) {
        System.out.println("Ultra Man");
    }
}

class PacMan extends UltraMan{
    protected int x, y;
    protected int size;

    PacMan(int x0, int y0, int s0) {
        super(s0*s0,"Pacman");
        this.setData(x0, y0, s0);

    }

    void setData(int x0, int y0, int s0) { //【C】
        x = x0;
        y = y0;
        size = s0;
    }
}