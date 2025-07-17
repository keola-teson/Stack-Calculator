package stackCalculator;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Controls window and attributes associated with it. Control components.
 * @author Keola :)
 */
public class Window implements ActionListener
{
	private final JFrame frame = new JFrame("Stack Calculator"); // window
	
	/* labels to user */
	private final JLabel stackSize = new JLabel("Stack Size");
	private final JLabel calculate = new JLabel("Calculate");
	private final JLabel amount = new JLabel("Amount");
	private final JLabel stack = new JLabel("Stack");
	private final JLabel remain = new JLabel("Remaining");
	private final JLabel whole = new JLabel("Whole");
	
	/* typing fields */
	private final JTextField stackField = new JTextField();
	private final JTextField remainField = new JTextField();
	private final JTextField wholeField = new JTextField();
	
	/* buttons for user */
	private final JButton stack64 = new JButton("64");
	private final JButton stack16 = new JButton("16");
	private final JButton wholeToStack = new JButton("Whole");
	private final JButton stackToWhole = new JButton("Stack");
	private final JButton finish = new JButton("Finish");
	
	/* fonts used */
	private final Font font = new Font("Ariel", Font.BOLD, 15);
	private final Font buttonFont = new Font("Ariel", Font.BOLD, 12);
	
	private final Calculator calc = new Calculator(64); // where calculations are done
	
	/**
	 * Sets up window
	 * <P> Creates buttons, text fields, and labels. </P>
	 * <P> Adds them to window </P>
	 */
	public Window()
	{
		/* creates the window */
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 600);
		frame.setLayout(null);
		
		/* creates the labels */
		stackSize.setBounds(158, 25, 104, 28); // sets location and size
		stackSize.setFont(font);
		
		calculate.setBounds(162, 132, 95, 30);
		calculate.setFont(font);
		
		amount.setBounds(168, 241, 83, 28);
		amount.setFont(font);
		
		stack.setBounds(59, 293, 67, 30);
		stack.setFont(font);
		
		remain.setBounds(23, 352, 103, 30);
		remain.setFont(font);
		
		whole.setBounds(54, 408, 72, 30);
		whole.setFont(font);
		
		/* creates the text fields */
		stackField.setBounds(136, 281, 205, 48);
		stackField.setFont(font);
		
		remainField.setBounds(136, 341, 205, 48);
		remainField.setFont(font);
		
		wholeField.setBounds(136, 401, 205, 48);
		wholeField.setFont(font);
		wholeField.setFocusable(false); // sets whether or not user can type in it
		
		/* Creates the buttons */
		stack64.setBounds(135, 65, 45, 40);
		stack64.setFont(new Font("Ariel", Font.BOLD, 9));
		stack64.setBackground(Color.WHITE);
		stack64.addActionListener(this); // allows actions when button is pressed
		
		stack16.setBounds(239, 65, 45, 40);
		stack16.setFont(new Font("Ariel", Font.BOLD, 9));
		stack16.setBackground(Color.WHITE);
		stack16.addActionListener(this);
		
		wholeToStack.setBounds(126, 174, 72, 40);
		wholeToStack.setFont(buttonFont);
		wholeToStack.setBackground(Color.WHITE);
		wholeToStack.addActionListener(this);
		
		stackToWhole.setBounds(223, 174, 67, 40);
		stackToWhole.setFont(buttonFont);
		stackToWhole.setBackground(Color.WHITE);
		stackToWhole.addActionListener(this);
		
		finish.setBounds(157, 477, 105, 61);
		finish.setFont(font);
		finish.setBackground(Color.WHITE);
		finish.addActionListener(this);
		
		/* adds all components to window */
		frame.add(stackSize);
		frame.add(calculate);
		frame.add(amount);
		frame.add(stack);
		frame.add(remain);
		frame.add(whole);
		
		frame.add(stackField);
		frame.add(remainField);
		frame.add(wholeField);
		
		frame.add(stack64);
		frame.add(stack16);
		frame.add(wholeToStack);
		frame.add(stackToWhole);
		frame.add(finish);
		
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		/* checks if the stack size is 64 or 16 */
		if (e.getSource() == stack64)
		{
			stack64.setBackground(Color.LIGHT_GRAY); // colors selected
			stack16.setBackground(Color.WHITE); // uncolors other option
		}
		else if (e.getSource() == stack16)
		{
			stack16.setBackground(Color.LIGHT_GRAY);
			stack64.setBackground(Color.WHITE);
		}
		
		/* checks if calculations done are whole or stack based */
		if (e.getSource() == wholeToStack)
		{
			wholeField.setFocusable(true); // allows field to be typed in
			stackField.setFocusable(false); // disallows field to be typed in
			remainField.setFocusable(false);
			
			wholeToStack.setBackground(Color.LIGHT_GRAY);
			stackToWhole.setBackground(Color.WHITE);
		}
		else if (e.getSource() == stackToWhole)
		{
			wholeField.setFocusable(false);
			stackField.setFocusable(true);
			remainField.setFocusable(true);
			
			wholeToStack.setBackground(Color.WHITE);
			stackToWhole.setBackground(Color.LIGHT_GRAY);
		}
		
		/* what is done when user is finished with inputting numbers */
		if (e.getSource() == finish)
		{
			/*
			 * checks if 16 is selected
			 * default stack is 64
			 */
			if (stack16.getBackground().equals(Color.LIGHT_GRAY))
			{
				calc.setMaxStackSize(16);
			}
			
			/* checks whether stack or whole is selected */
			if (wholeToStack.getBackground().equals(Color.LIGHT_GRAY))
			{
				try // if user doesn't input integer sets unselected text fields to error
				{
					int stack = calc.toStacks(Integer.parseInt(wholeField.getText()))[0];
					int remaining = calc.toStacks(Integer.parseInt(wholeField.getText()))[1];
					stackField.setText("" + stack);
					remainField.setText("" + remaining);
				}
				catch (Exception ex)
				{
					stackField.setText("Error");
					remainField.setText("Error");
				}
			}
			else
			{
				try
				{
					int stack = Integer.parseInt(stackField.getText());
					int remaining = Integer.parseInt(remainField.getText());
					wholeField.setText("" + calc.toWhole(stack, remaining));
				}
				catch (Exception ex)
				{
					wholeField.setText("Error");
				}
			}
		}
	}
	
}
