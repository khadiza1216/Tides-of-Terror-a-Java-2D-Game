import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("TIDES OF TERROR");
        UnderwaterSurvival game = new UnderwaterSurvival();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
         frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    } 
}    