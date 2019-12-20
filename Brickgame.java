
package anishGame;
import javax.swing.JFrame;

public class Brickgame {

public static void main(String[] args) {
    JFrame frame = new JFrame("brickgame");
    Gameplay gameplay=new Gameplay();
	frame.setBounds(10,10,700,600);
    frame.setVisible(false);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(gameplay);

        
}
}