import javax.swing.*;

/*Contains the main method that initializes the frame and adds the board panel to it*/
public class Game {

	static JFrame frame;
	static BoardPanel board;
	
	public static void main(String[] args) {
		board = new BoardPanel();
		
		frame = new JFrame("The Randoms");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		frame.setContentPane(board);
		frame.pack();
		
		frame.setLocationRelativeTo(null);//puts frame to the center
		frame.setVisible(true);		
		
	}
	
	
}
