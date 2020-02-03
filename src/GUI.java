/*
 * File: GUI.java
 * Zachary Muranaka
 * This is the Graphical User Interface (GUI) for the hypergeometric calculator
 */

// Window Import Statements
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JPanel
{   
	static final long serialVersionUID = 1L; // Visual Studio Code said that I need this
   
	// Window components
	private JFrame window = new JFrame("Hypergeometric Calculator");  
	private JLabel lblPopSize = new JLabel("Population Size: ");
	private JTextField txtPopSize = new JTextField("");
	private JLabel lblPopSuccesses = new JLabel("Population Successes: ");
	private JTextField txtPopSuccesses = new JTextField("");
	private JLabel lblSampleSize = new JLabel("Sample Size: ");
	private JTextField txtSampleSize = new JTextField("");
	private JLabel lblDesiredSuccesses = new JLabel("Desired Successes: ");
	private JTextField txtDesiredSuccesses = new JTextField("");
	private JButton btnCalc = new JButton("Calculate");
	private JLabel lblExactChance = new JLabel(""); // Displays the chance that exactly the number of desired successes are drawn
	private JLabel lblLess = new JLabel(""); // Displays the chance that less than the number of desired successes are drawn
	private JLabel lblOrInclusiveLess = new JLabel(""); // Displays the chance that less than or equal to the number of desired successes are drawn
	private JLabel lblGreater = new JLabel(""); // Displays the chance that greater than the number of desired successes are drawn
	private JLabel lblOrInclusiveGreater = new JLabel(""); // Displays the chance that greater than or equal to the number of desired successes are drawn
	
	// Constructor for the GUI class
	public GUI()
	{
		setLayout(null); // No layout is set
      
		// Set x, y, width, height
		lblPopSize.setBounds(40, 20, 300, 40);
		lblPopSize.setFont(new Font("Sans-Serif", Font.PLAIN, 28));
		txtPopSize.setBounds(360, 20, 100, 40);
		txtPopSize.setFont(new Font("Serif", Font.PLAIN, 28));
		
		lblPopSuccesses.setBounds(40, 100, 300, 40);
		lblPopSuccesses.setFont(new Font("Sans-Serif", Font.PLAIN, 28));
		txtPopSuccesses.setBounds(360, 100, 100, 40);
		txtPopSuccesses.setFont(new Font("Serif", Font.PLAIN, 28));
      
		lblSampleSize.setBounds(40, 180, 300, 40);
		lblSampleSize.setFont(new Font("Sans-Serif", Font.PLAIN, 28));
		txtSampleSize.setBounds(360, 180, 100, 40);
		txtSampleSize.setFont(new Font("Serif", Font.PLAIN, 28));
      
		lblDesiredSuccesses.setBounds(40, 260, 300, 40);
		lblDesiredSuccesses.setFont(new Font("Sans-Serif", Font.PLAIN, 28));
		txtDesiredSuccesses.setBounds(360, 260, 100, 40);
		txtDesiredSuccesses.setFont(new Font("Serif", Font.PLAIN, 28));
      
		btnCalc.setBounds(40, 340, 200, 40);
		btnCalc.setFont(new Font("Sans-Serif", Font.PLAIN, 28));
      
		lblExactChance.setBounds(40, 420, 900, 40);
		lblExactChance.setFont(new Font("Sans-Serif", Font.PLAIN, 28));
		lblLess.setBounds(40, 480, 900, 40);
		lblLess.setFont(new Font("Sans-Serif", Font.PLAIN, 28));
		lblOrInclusiveLess.setBounds(40, 540, 900, 40);
		lblOrInclusiveLess.setFont(new Font("Sans-Serif", Font.PLAIN, 28));
		lblGreater.setBounds(40, 600, 900, 40);
		lblGreater.setFont(new Font("Sans-Serif", Font.PLAIN, 28));
		lblOrInclusiveGreater.setBounds(40, 660, 900, 40);
		lblOrInclusiveGreater.setFont(new Font("Sans-Serif", Font.PLAIN, 28));
		
		// Add all of the labels, text boxes, and the button to the window
		add(lblPopSize);
		add(txtPopSize);
		add(lblPopSuccesses);
		add(txtPopSuccesses);
		add(lblSampleSize);
		add(txtSampleSize);
		add(lblDesiredSuccesses);
		add(txtDesiredSuccesses);
		add(btnCalc);
		add(lblExactChance);
		add(lblLess);
		add(lblOrInclusiveLess);
		add(lblGreater);
		add(lblOrInclusiveGreater);
		
		window.add(this); // Add this GUI to the window
		window.setSize(1000, 800); // Set the window size
		window.setVisible(true); // Allow the window to be seen
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // The application ends when the red 'X' is clicked
      
		// Triggers when btnCalc is pressed
		btnCalc.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					long popSize = Long.parseLong(txtPopSize.getText());
					long popSuccesses = Long.parseLong(txtPopSuccesses.getText());
					long sampleSize = Long.parseLong(txtSampleSize.getText());
					long desiredSuccesses = Long.parseLong(txtDesiredSuccesses.getText());
					displayResults(popSize, popSuccesses, sampleSize, desiredSuccesses);
				}
				catch(NumberFormatException nfException)
				{
					formatError();
				}
			}
		});
      
	}
	
	// Displays the results of the hypergeometric distribution
	public void displayResults(long popSize, long popSuccesses, long sampleSize, long desiredSuccesses)
	{
		Deck orGreaterDeck = new Deck(popSize, popSuccesses, sampleSize, desiredSuccesses);
		Deck orLessDeck = new Deck(orGreaterDeck);
		
		// The next next six statements calculate probabilities for the Decks
		double orGreaterExactChance = orGreaterDeck.probability(orGreaterDeck.getdpopSize(), orGreaterDeck.getdpopSuccesses(), orGreaterDeck.getddesiredSuccesses(), orGreaterDeck.getdpopFailures(), orGreaterDeck.getdsampleFailures());
		double orLessExactChance = orLessDeck.probability(orLessDeck.getdpopSize(), orLessDeck.getdpopSuccesses(), orLessDeck.getddesiredSuccesses(), orLessDeck.getdpopFailures(), orLessDeck.getdsampleFailures());
		orGreaterExactChance *= orGreaterDeck.getcombination(); // Chance of getting exactly desired successes
		double orGreaterInclusiveChance = orGreaterDeck.orGreater(orGreaterExactChance); // Chance of getting desired successes or greater
		orLessExactChance *= orLessDeck.getcombination(); // Chance of getting exactly desired successes
		double orLessInclusiveChance = orLessDeck.orLess(orLessExactChance); // Chance of getting desired successes or less
      
		lblExactChance.setText("Chance of exactly desired successes: " + orGreaterExactChance);
		lblLess.setText("Chance of less than desired successes: " + (orLessInclusiveChance - orLessExactChance));
		lblOrInclusiveLess.setText("Chance of desired successes or less: " + orLessInclusiveChance);
		lblGreater.setText("Chance of greater than desired successes: " + (orGreaterInclusiveChance - orGreaterExactChance));
		lblOrInclusiveGreater.setText("Chance of desired successes or greater: " + orGreaterInclusiveChance);
	}
	
	// Runs when the user enters invalid input
	public void formatError()
	{
		lblExactChance.setText("Input not valid.");
		lblLess.setText("Try again.");
		lblOrInclusiveLess.setText("");
		lblGreater.setText("");
		lblOrInclusiveGreater.setText("");
	}
}
