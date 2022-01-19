import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class Pass {
	
	static JButton pass;
	/*Initializes the pass button*/
	public Pass() {
		pass = new JButton();
		pass.setText("Pass");
		pass.setBounds(880, 710, 90, 40);
		pass.setBackground(new Color(0, 0, 102));
		pass.setHorizontalAlignment(JButton.CENTER);
		pass.setForeground(Color.white);
		pass.setHorizontalAlignment(JButton.CENTER);
		pass.setFont(new Font("Candara Light", Font.BOLD, 20));
		
	}
	
	/*A method to return the pass*/
	public static JButton getPass() {
		return pass;
	}
}
