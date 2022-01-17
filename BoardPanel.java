
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;

public class BoardPanel extends JPanel implements ActionListener {
	
	/*Declare constants*/
	private static final long serialVersionUID = 1L;
	final static int totalCol = 21;
	final static int totalRow = 18;
	final static int boardCol = 15;
	final static int boardRow = 15;
	final static int size = 53;
	final static int frameWidth = size * totalCol; 
	final static int frameHeight = size * totalRow;
	final static int boardWidth = size * boardCol;
	final static int boardHeight = size * boardRow;
	
	/*Import images*/
	private ImageIcon img = new ImageIcon("res/board.png");
	private ImageIcon let_Α = new ImageIcon("res/A.png");
	private ImageIcon let_Β = new ImageIcon("res/B.png");
	private ImageIcon let_Γ = new ImageIcon("res/G.png");
	private ImageIcon let_Δ = new ImageIcon("res/D.png");
	private ImageIcon let_Ε = new ImageIcon("res/E.png");
	private ImageIcon let_Ζ = new ImageIcon("res/Z.png");
	private ImageIcon let_Η = new ImageIcon("res/H.png");
	private ImageIcon let_Θ = new ImageIcon("res/U.png");
	private ImageIcon let_Ι = new ImageIcon("res/I.png");
	private ImageIcon let_Κ = new ImageIcon("res/K.png");
	private ImageIcon let_Λ = new ImageIcon("res/L.png");
	private ImageIcon let_Μ = new ImageIcon("res/M.png");
	private ImageIcon let_Ν = new ImageIcon("res/N.png");
	private ImageIcon let_Ξ = new ImageIcon("res/J.png");
	private ImageIcon let_Ο = new ImageIcon("res/O.png");
	private ImageIcon let_Π = new ImageIcon("res/P.png");
	private ImageIcon let_Ρ = new ImageIcon("res/R.png");
	private ImageIcon let_Σ = new ImageIcon("res/S.png");
	private ImageIcon let_Τ = new ImageIcon("res/T.png");
	private ImageIcon let_Υ = new ImageIcon("res/Y.png");
	private ImageIcon let_Φ = new ImageIcon("res/F.png");
	private ImageIcon let_Χ = new ImageIcon("res/X.png");
	private ImageIcon let_Ψ = new ImageIcon("res/C.png");
	private ImageIcon let_Ω = new ImageIcon("res/V.png");
	private ImageIcon blank = new ImageIcon("res/blank.png");
	private ImageIcon won = new ImageIcon("res/won.png");
	private ImageIcon heart = new ImageIcon("res/heart.png");
	private ImageIcon background = new ImageIcon("res/back.png");
	private ImageIcon instruction = new ImageIcon("res/instructions.png");
	
	/*Declare the other variables*/
	Object[] options = {"Start Menu", "Play Again"};

	JPanel bpl;

	JTextPane questions;

	JTextField counter, levRound;

	Random r = new Random();

	JButton[][] b = new JButton[15][15];
	JButton[] rack = new JButton[10];
	JButton instr, startmenu, exit, back;
	JButton check = new JButton();

	int[][] point = new int[15][15];

	private int fromRow, toRow, toCol, k, lost, round, level, question, time, passCount;

	boolean isFirstClick = true;
	boolean menu = true;
	boolean nextQuestion = true;
	boolean nextRack = true;
	boolean nextTimer = true;
	boolean firstTime, firstWord, firstLetter, youWon, getInstr, existv, existh, connectedh, connectedv;


	Timer timer;
	long start = 100;
	long period = 1000;
	DecimalFormat dFormat = new DecimalFormat("00");

	String resulth = "";
	String resultv = "";

	public BoardPanel() {
		/*Initialize some buttons and textFields that should be declared just once when the game starts
		 *and set the preferred size, color etc.*/
		UIManager.put("Button.background", Color.white);
		
		back = new JButton("Back");
		startmenu = new JButton("Start Game");
		instr = new JButton("Instructions");
		bpl = new JPanel();
		this.setPreferredSize(new Dimension(frameWidth, frameHeight));
		this.setDoubleBuffered(true);	
		this.setLayout(null);	
		
		levRound = new JTextField();
		
		levRound.setFont(new Font("Candara Light", Font.BOLD, 20));
		levRound.setBackground(new Color(0, 0, 102));
		levRound.setForeground(Color.white);
		levRound.setBounds(880, 100, 200, 45);
		levRound.setHorizontalAlignment(JTextField.CENTER);
		this.add(levRound);
		
		startmenu.setFont(new Font("Candara", Font.BOLD, 30));
		startmenu.setFocusable(false);
		startmenu.setBackground(new Color(238, 137, 43));
		startmenu.setForeground(Color.white);
		startmenu.setBounds(600, 540, 300, 60);
		this.add(startmenu);
		startmenu.addActionListener(this);
		
		instr.setFont(new Font("Candara", Font.BOLD, 30));
		instr.setFocusable(false);
		instr.setBackground(new Color(238, 137, 43));
		instr.setForeground(Color.white);
		instr.setBounds(600, 630, 300, 60);
		this.add(instr);
		instr.addActionListener(this);
		showPanel();
		
		firstTime = true;
		
	}
	
	public void showPanel() {
		
		if (menu == true) {
			/*Show the menu if true and check if you want to show instructions or not
			 *and set preferred size, color etc.*/
			if (getInstr == false) {
				back.setVisible(false);
				startmenu.setVisible(true);
				instr.setVisible(true);
				levRound.setVisible(false);
				
			} else if (getInstr) {
				startmenu.setVisible(false);
				instr.setVisible(false);
				back.setVisible(true);
				levRound.setVisible(false);
				back.setFont(new Font("Candara", Font.BOLD, 30));
				back.setBackground(new Color(238, 137, 43));
				back.setForeground(Color.black);
				back.setBounds(800, 880, 200, 50);
				this.add(back);
				back.addActionListener(this);
			}
		} else {
			/*Else check if its the first time someone is playing or not
			 *to initialize variables and buttons etc.*/
			if( firstTime) {
				lost = 0;
				round = 0;
				level = 0;
				question = 0;
				firstWord = true;
				firstLetter = true;
				
				levRound.setText("Level: " + (level + 1) + "   Round: " + (round + 1));
				new Pass();
				startmenu.setVisible(false);
				instr.setVisible(false);
				back.setVisible(false);
				levRound.setVisible(true);
				this.setBackground(new Color(215, 247, 241));
			
				int top = 130;
				for (int i = 0; i < 15; i++) {		
					int left = -17;
					for (int j = 0; j < 15; j++) {	
						b[i][j] = new JButton();
						b[i][j].setVisible(true);
						b[i][j].setText("");
						b[i][j].setContentAreaFilled(false);
						b[i][j].setBounds(left += size , top, size, size);
						b[i][j].addActionListener(this);
						point[i][j] = 0;
						this.add(b[i][j]);
					}
					top += size;
				}			
			
				check.setBounds(980, 710, 90, 40);
				check.setVisible(true);
				check.setFocusable(false);
				check.setBackground(new Color(0, 0, 102));
				check.setForeground(Color.white);
				check.setFont(new Font("Candara Light", Font.BOLD, 20));
				check.setText("Check");
				check.addActionListener(this);
				this.add(check);
			
				counter = new JTextField();
				counter.setVisible(true);
				counter.setBounds(905, 780, 130, 50);
				counter.setBackground(new Color(0, 0, 0));
				counter.setHorizontalAlignment(JTextField.CENTER);
				counter.setForeground(Color.white);
				counter.setHorizontalAlignment(JTextField.CENTER);
				counter.setFont(new Font("Candara Light", Font.BOLD, 25));
				
				/*A random number from 0-4 to choose a random group of questions in each level*/
				k = r.nextInt(5);
				
				this.add(showQuestions(k));
				
				this.add(Pass.getPass());
				Pass.getPass().setVisible(true);
				Pass.getPass().addActionListener(this);
			
				int y2 = 290;
				for (int i = 0; i < 10; i++) {	
					if ( i == 2 || i == 4 || i == 6 || i == 8) {
						y2 += 80;
					}
					this.add(showRack(i, y2, k));
				}
				nextRack = false;
				
				if (nextQuestion == true) {
					questions.setText(Questions.getQuestion(k, question, level));
					nextQuestion = false;
				}
			
				if (nextTimer == true) {
					this.add(showTimer());
					nextTimer = false;
				}
				
				firstTime = false;
				
			} else {
				/*Set passCount back to 0 so that you can use it again*/
				if(passCount == 0) {
					Pass.getPass().setVisible(true);
				}
				
				for (int i = 0; i < 15; i++) {		
					for (int j = 0; j < 15; j++) {
						b[i][j].setVisible(true);
					}
				}
				
				
				check.setVisible(true);
				questions.setVisible(true);                            
				counter.setVisible(true);
				
				levRound.setText("Level: " + (level + 1) + "   Round: " + (round + 1));
				startmenu.setVisible(false);
				instr.setVisible(false);
				levRound.setVisible(true);
				
				if (nextRack == true) {
					int y2 = 290;
					for (int i = 0; i < 10; i++) {	
						rack[i].setVisible(true);
						if ( i == 2 || i == 4 || i == 6 || i == 8) {
							y2 += 80;
						}
						this.remove(rack[i]);
						this.add(showRack(i, y2, k));
					}
				}
			
				if (nextQuestion == true) {
					questions.setText(Questions.getQuestion(k, question, level));
					nextQuestion = false;
				}
			
				if (nextTimer == true) {
					this.add(showTimer());
					nextTimer = false;
				}
			}
		}
		
		if (youWon) {
			/*If you won hide all the unwanted buttons and Fields*/
			for (int i = 0; i < 15; i++) {		
				for (int j = 0; j < 15; j++) {
					b[i][j].setVisible(false);
				}
			}
			
			Pass.getPass().setVisible(false);
			check.setVisible(false);
			questions.setVisible(false);
			levRound.setVisible(false);
			counter.setVisible(false); 
			
			for (int i = 0; i < rack.length; i++){
				rack[i].setVisible(false);
			}
		}
	}
	
	public JButton showRack(int i, int y, int k) { 
			/*Get each character from the words in the class Letters and create
			 * an object Tiles so that you can get the image of the wanted letter
			 */
			Tiles l = new Tiles(Letters.getLetter(k, question, level).charAt(i));
			/*create the wanted button and put the equivalent image*/
        	rack[i] = new JButton(Character.toString(Letters.getLetter(k, question, level).charAt(i)), getLetter(l));
        	rack[i].setFont(new Font("Candara Light", Font.PLAIN, 0));
        	rack[i].setHorizontalTextPosition(JButton.CENTER);
        	
        	/*Put the button in the wanted place*/
        	if (i % 2 == 0) {
        		rack[i].setBounds(905, y, size, size);
        	} else {
        		rack[i].setBounds(995, y, size, size);
        	}       	
        	rack[i].setFocusable(false);
    		rack[i].addActionListener(this);
    		return rack[i];
    }
		
	
	public JTextPane showQuestions(int k) {
		/*Create a textPane with the wanted question*/
		questions = new JTextPane();
		StyledDocument documentStyle = questions.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		documentStyle.setParagraphAttributes(0, documentStyle.getLength(), center, false);
		questions.setText(Questions.getQuestion(k, question, level));
		questions.setBackground(new Color(0, 0, 102));
		questions.setForeground(Color.white);
		questions.setBounds(0, 20, frameWidth, 70);
		questions.setFont(new Font("Candara Light", Font.BOLD, 23));
		return questions;
	}

	
	
	public JTextField showTimer() {
		/*Set a timer of 90 seconds*/
		timer = new Timer();
		time = 90;
		timer.scheduleAtFixedRate(new TimerTask() {
			/*CountDown until it reaches 0*/
			public void run() {
				if (time % 60 == 0) {
					counter.setText(dFormat.format(time / 60) + " : 00");
				} else if (time > 60){
					counter.setText(dFormat.format(time / 60) + " : " + dFormat.format(time - 60));
				} else if(time < 60) {
					counter.setText(dFormat.format(time / 60) + " : " + dFormat.format(time));
				}
				time--;

				if (time < 0) {
					/*If the timer reaches 0 it shows the correct answer and you lose a life */
					timer.cancel();
					lost++;
					
					/*If you have lost 3 lives a message shows up to choose whether you
					 * want to play again or you want to go back to the start menu
					 */
					if (lost == 3) {
						
						nextQuestion = false;
						nextRack = false;
						nextTimer = false;
						
						int result = JOptionPane.showOptionDialog(null, new JLabel("Έχασες!", JLabel.CENTER), "", 
								JOptionPane.YES_NO_CANCEL_OPTION,
				                 JOptionPane.PLAIN_MESSAGE,
				                 null,
				                 options,
				                 null);
								for (int i = 0; i < b.length; i++) {
									for (int j = 0; j < b[0].length; j++) {
										point[i][j] = 0;
										b[i][j].setIcon(null);
										b[i][j].setText("");
										b[i][j].setHorizontalTextPosition(JButton.CENTER);
										isFirstClick = true;
									}
								}
						/*If you choose start menu or close the message you go back to
						 * the start screen*/
						if( result == 0 | result == JOptionPane.CLOSED_OPTION) {
							k = r.nextInt(5);
							firstWord = true;
							firstLetter = true;
							question = 0;
							level = 0;
							round = 0;
							lost = 0;
							menu = true;
							nextQuestion = true;
							nextRack = true;
							nextTimer = true;
							passCount = 0;
							for (int i = 0; i < 15; i++) {		
								for (int j = 0; j < 15; j++) {
									b[i][j].setVisible(false);
								}
							}
							
							Pass.getPass().setVisible(false);
							check.setVisible(false);
							questions.setVisible(false);
							levRound.setVisible(false);
							counter.setVisible(false); 
							
							for (int i = 0; i < rack.length; i++){
								rack[i].setVisible(false);
							}
							
							showPanel();
							repaint();
						/*Else if you press play again the game starts over*/
						} else {
							passCount = 0;
							menu = false;
							nextQuestion = true;
							nextRack = true;
							nextTimer = true;
							round = 0;
							level = 0;
							question = 0;
							firstWord = true;
							firstLetter = true;
							lost = 0;
							k = r.nextInt(5);
							
							for (int i = 0; i < 15; i++) {		
								for (int j = 0; j < 15; j++) {
									b[i][j].setVisible(true);
								}
							}
							
							Pass.getPass().setVisible(true);
							check.setVisible(true);
							questions.setVisible(true);
							levRound.setVisible(true);
							counter.setVisible(true); 
							
							showPanel();
							repaint();
						}
						
					} else {
						JOptionPane.showMessageDialog(null, "Η σωστή απάντηση είναι : "
								+ Answers.getAnswer(k, question, level) + "\n Τοποθέτησε την στον πίνακα και πάτα check.");
					}
					
					repaint();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, start, period);
		return counter;
	}
	
	/*A method that paints the wanted images*/
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		if(menu == true) {
			if (getInstr == false) {
				g2.drawImage(background.getImage(),  0,  0, BoardPanel.frameWidth, BoardPanel.frameHeight,  null);
			} else if (getInstr) {
				g2.drawImage(instruction.getImage(),  0,  0, BoardPanel.frameWidth, BoardPanel.frameHeight,  null);
			}
		} else {
			g2.drawImage(img.getImage(),  25,  120, boardWidth + 20, boardHeight + 20,  null);
		
			int n = 805;
			if(lost == 0) {
				for (int i = 0; i < 3; i++) {
					g2.drawImage(heart.getImage(), n += 70, 180, null);
				} 
			} else if (lost == 1) {
				for (int i = 0; i < 2; i++) {
					g2.drawImage(heart.getImage(), n += 70, 180, null);
				} 
			} else if (lost == 2) {
				g2.drawImage(heart.getImage(), n += 70, 180, null);
			} else {}
		}
		
		if (youWon) {
			g2.drawImage(won.getImage(), 0, 00, frameWidth, frameHeight,  null);
		}
	}
	
	/*It returns the wanted image according to the letter*/
	public ImageIcon getLetter(Tiles t) {
		
		if(t.getLetter() == 'Α') {
			return let_Α;
		} else if (t.getLetter() == 'Β') {
			return let_Β;
		} else if (t.getLetter() == 'Γ') {
			return let_Γ;
		} else if (t.getLetter() == 'Δ') {
			return let_Δ;
		} else if (t.getLetter() == 'Ε') {
			return let_Ε;
		} else if (t.getLetter() == 'Ζ') {
			return let_Ζ;
		} else if (t.getLetter() == 'Η') {
			return let_Η;
		} else if (t.getLetter() == 'Θ') {
			return let_Θ;
		} else if (t.getLetter() == 'Ι') {
			return let_Ι;
		} else if (t.getLetter() == 'Κ') {
			return let_Κ;
		} else if (t.getLetter() == 'Λ') {
			return let_Λ;
		} else if (t.getLetter() == 'Μ') {
			return let_Μ;
		} else if (t.getLetter() == 'Ν') {
			return let_Ν;
		} else if (t.getLetter() == 'Ξ') {
			return let_Ξ;
		} else if (t.getLetter() == 'Ο') {
			return let_Ο;
		} else if (t.getLetter() == 'Π') {
			return let_Π;
		} else if (t.getLetter() == 'Ρ') {
			return let_Ρ;
		} else if (t.getLetter() == 'Σ') {
			return let_Σ;
		} else if (t.getLetter() == 'Τ') {
			return let_Τ;
		} else if (t.getLetter() == 'Υ') {
			return let_Υ;
		} else if (t.getLetter() == 'Φ') {
			return let_Φ;
		} else if (t.getLetter() == 'Χ') {
			return let_Χ;
		} else if (t.getLetter() == 'Ψ') {
			return let_Ψ;
		} else if (t.getLetter() == 'Ω') {
			return let_Ω;
		} else {
			return blank;
		}
			
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		/*If you press start game the game appears and the menu disappears*/
		if (e.getSource() == startmenu) {
			menu = false;
			repaint();
			showPanel();
			
		}
		
		/*If you press the instructions the instructions show up and the start menu disappears*/
		if(e.getSource() == instr) {
			getInstr = true;
			repaint();
			showPanel();
			
			
		}
		
		/*If you press back it goes back to the start menu*/
		if (e.getSource() == back) {
			getInstr = false;
			repaint();
			showPanel();
			
		}
		
		/*If you press any letter from the rack it saves the position
		 * in a variable to show on the board*/
		for(int i = 0; i < rack.length; i++) {
			if (e.getSource() == rack[i]) {
				fromRow = i;
				isFirstClick = false;
			}
		}
		
		try {
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++){
					if (e.getSource() == b[i][j]) {
						/*If you press on the board and it is not the first click it shows 
						 * the pressed letter on the board
						 */
						if(isFirstClick == false & b[i][j].getIcon() == null) {
							toRow = i;
							toCol = j;
							if(firstLetter ) {
								/*If it's the first letter you put on the board it makes sure you 
								 * start from the star otherwise an exception shows up
								 */
								if (toRow == 7 & toCol == 7) {
									ImageIcon o = (ImageIcon) rack[fromRow].getIcon();
									b[toRow][toCol].setIcon(o);
									b[toRow][toCol].setText(rack[fromRow].getText());
									b[i][j].setHorizontalTextPosition(JButton.CENTER);
									b[i][j].setFont(new Font("Candara Light", Font.PLAIN, 0));
									rack[fromRow].setVisible(false);
									isFirstClick = true;
									firstLetter = false;
								} else {
									throw new StarException();
								}
							} else {
								
								ImageIcon o = (ImageIcon) rack[fromRow].getIcon();
								b[toRow][toCol].setIcon(o);
								b[toRow][toCol].setText(rack[fromRow].getText());
								b[i][j].setHorizontalTextPosition(JButton.CENTER);
								b[i][j].setFont(new Font("Candara Light", Font.PLAIN, 0));
								rack[fromRow].setVisible(false);
								isFirstClick = true;
							}
							
						} else {
							/*If it is the first click it removes the letter from the board and 
							 * it show up on the rack again
							 */
							for(int y = 0; y < rack.length; y++) {
								if (b[i][j].getIcon() == rack[y].getIcon() & rack[y].isVisible() == false & point[i][j] == 0) {
									rack[y].setVisible(true);
									break;
								}
							}
							if (i == 7 & j == 7 & point[7][7] == 0) {
								firstLetter = true;
							}
							if (point[i][j] == 0) {
								b[i][j].setIcon(null);
								b[i][j].setText("");
								b[i][j].setHorizontalTextPosition(JButton.CENTER);
								isFirstClick = true;
							}
						}
					}
				}
			}
		} catch(StarException s) {
			b[toRow][toCol].setIcon(null);
			b[toRow][toCol].setText("");
			
			JOptionPane.showMessageDialog(null,
					new JLabel("Πρέπει να ξεκινήσεις από το αστεράκι!", JLabel.CENTER),
				    "",
				    JOptionPane.ERROR_MESSAGE);
			;
		}
		
		/*If you press on the pass the right answer shows up and you move
		 * to the next round without losing a life
		 */
		if (e.getSource() == Pass.getPass()) {
			
			timer.cancel();
			JOptionPane.showMessageDialog(null, "Η σωστή απάντηση είναι : "
					+ Answers.getAnswer(k, question, level) + "\n Τοποθέτησε την στον πίνακα και πάτα check.");
			
			passCount++;
			/*if you use your pass you cannot use another one until the next level*/
			if (passCount == 1) {
				Pass.getPass().setVisible(false);
			}
			
			if (round == 4) {
				passCount = 0;
			}
			repaint();
		}
		
		/*When you press the check button the two methods that check 
		 * if the word you put on the board is equal to the right answer
		 * get called
		 */
		if (e.getSource() == check) {
			if(createdWordh() | createdWordv()) {
				try {
					/*If you got the answer correct it checks if it is connected to 
					 * any of the other words on the board and if it is not it throws
					 * an exception. Unless it is the first word because it cannot 
					 * be connected with any other
					 */
					if(connectedh == true | connectedv == true | firstWord == true) {
						
						connectedh = false;
						connectedv = false;
						nextQuestion = true;
						nextRack = true;
						nextTimer = true;
						timer.cancel();
						
						for (int i = 0; i < 15; i++) {
							for (int j = 0; j < 15; j++) {
								if (b[i][j].getText() != "") {
									point[i][j] = 1;
								}
							}
						}
						
						/*If you are on the last level and on the last round an you find the
						 * correct word the you won image shows up and everything
						 * else disappears
						 */
						if (level == 4 & round == 4) {
							youWon = true;
							showPanel();
							repaint();
						}
						
						/*If you are on the 5th round and you find the correct word you
						 * get to the next level otherwise you just get to the next round
						 */
						if (round == 4) {
							level++;
							firstWord = true;
							
							firstLetter = true;
							Pass.getPass().setVisible(true);
							passCount = 0;
							round = 0;
							question = 0;
							k = r.nextInt(5);
							for (int i = 0; i < b.length; i++) {
								for (int j = 0; j < b[0].length; j++){
									if (point[i][j] == 1) {
										point[i][j] = 0;
										b[i][j].setIcon(null);
										b[i][j].setText("");
										b[i][j].setHorizontalTextPosition(JButton.CENTER);
										isFirstClick = true;				
									}
								}
							}
							
						} else {
							firstWord = false;
							round++;
							question++;
						}
						
						showPanel();
					
					} else if (connectedh == false & connectedv == false & firstWord == false){
						throw new ConnectedException();
					}
				} catch (ConnectedException con) {
					/*If the words are not connected the word you just put on the board clear
					 * so that you can put it again
					 */
					if(point[7][7] == 0) {
						firstLetter = true;
					}
					
					JOptionPane.showMessageDialog(null,
							new JLabel("Οι λέξεις πρέπει να συνδέονται!", JLabel.CENTER),
						    "",
						    JOptionPane.ERROR_MESSAGE);
					;
					
					for(int y = 0; y < rack.length; y++) {
						if (rack[y].isVisible() == false) {
							rack[y].setVisible(true);
						}
					}
					
					for (int i = 0; i < b.length; i++) {
						for (int j = 0; j < b[0].length; j++){
							if (point[i][j] == 0) {
								b[i][j].setIcon(null);
								b[i][j].setText("");
								b[i][j].setHorizontalTextPosition(JButton.CENTER);
								isFirstClick = true;				
							}
						}
					}	
				}
			} else {
				/*If the word you put in is wrong, the letters return to the rack
				 * and you continue your attempt 
				 */
				if(point[7][7] == 0) {
					firstLetter = true;
				}
				
				for(int y = 0; y < rack.length; y++) {
					if (rack[y].isVisible() == false) {
						rack[y].setVisible(true);
					}
				}
				for (int i = 0; i < b.length; i++) {
					for (int j = 0; j < b[0].length; j++){
						if (point[i][j] == 0) {
							b[i][j].setIcon(null);
							b[i][j].setText("");
							b[i][j].setHorizontalTextPosition(JButton.CENTER);
							isFirstClick = true;				
						}
					}
				}	
			}
		}
	}
	
	
	
	/*Checks the words on the board created horizontally*/
	public boolean createdWordh() {
		existh = false;
		
		for (int i = 0; i < b.length; i++) {
			resulth = "";
			
			for (int j = 0; j < b[0].length; j++) {
				if (b[i][j].getText() != "" & point[i][j] == 0) {
					resulth += b[i][j].getText();
				/*If the letters are connected to already existing words the connected variable turns true*/	
				} else if (point[i][j] == 1) {
					if ( j == 0) {
						if(point[i][j+1] == 0 & b[i][j+1].getText() != "" ) {
							resulth += b[i][j].getText();
							connectedh = true;
						}
						continue;
					} else if ( j == b[0].length-1) {
						if(point[i][j-1] == 0 & b[i][j-1].getText() != "") {
							resulth += b[i][j].getText();
							connectedh = true;
						}
						continue;
					} else {
						if((point[i][j-1] == 0 & b[i][j-1].getText() != "") | (point[i][j+1] == 0 & b[i][j+1].getText() != "")) {
							resulth += b[i][j].getText();
							connectedh = true;
						}
						continue;
					}
				}
			}
			/*If the result is equal to the word we are looking for it returns true*/
			if (resulth.equals(Answers.getAnswer(k, question, level))) {
				existh = true;
				break;
			} else {
				connectedh = false;
			}
		}	
		return existh;
	}
	
	/*Likewise for the vertical words*/
	public boolean createdWordv() {
		existv = false;
		
		for (int j = 0; j < b[0].length; j++) {
			resultv = "";
			
			for (int i = 0; i < b.length; i++) {
				if (b[i][j].getText() != "" & point[i][j] == 0) {
					resultv += b[i][j].getText();
				} else if (point[i][j] == 1) {
					if ( i == 0) {
						if(point[i + 1][j] == 0 & b[i+1][j].getText() != "") {
							resultv += b[i][j].getText();
							connectedv = true;
						}
						continue;
					} else if ( i == b[0].length-1) {
						if(point[i - 1][j] == 0 & b[i - 1][j].getText() != "") {
							resultv += b[i][j].getText();
							connectedv = true;
						}
						continue;
					} else {
						if((point[i-1][j] == 0 & b[i-1][j].getText() != "") | (point[i+1][j] == 0 & b[i+1][j].getText() != "")) {
							resultv += b[i][j].getText();
							connectedv = true;
						}
						continue;
					}
				}
			}
			if (resultv.equals(Answers.getAnswer(k, question, level))) {
				existv = true;
				break;
			} else {
				connectedv = false;
			}
		}	
		return existv;
	}
	
}
	