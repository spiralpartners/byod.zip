import java.awt.*;

class NoisyPacMan extends PacMan{
    private String word = "Noisy";

    NoisyPacMan(int x0, int y0, int s0, String w0){
        super(x0, y0, s0);
        super.bcolor = Color.cyan;
        this.setWord(w0);
    }
    void setWord(String w0){
        word = w0;
    }
    void draw(Graphics g){
    }
}