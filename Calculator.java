package stackCalculator;

/**
 * Controls logic for calculator
 * @author Keola :)
 */
public class Calculator {

	private int maxStackSize;
	
	public Calculator(int maxStackSize)
	{
		this.maxStackSize = maxStackSize;
	}
	
	/**
	 * Finds how many stacks and how many items remain after
	 * <P> stacks are found by dividing the total amount of items by the max amount of items in a stack and truncating </P>
	 * <b> stack = amount / maxStackSize </b>
	 * <P></P>
	 * <P> the amount of the rest of the items are found by using the remainder instead of the truncated value of the stack </P>
	 * <b> remainder = amount % maxStackSize </b>
	 * <P></P>
	 * @param amount
	 * @return array containing both stack and remaining values
	 */
	public int[] toStacks(int amount) {
		int stacks = amount / maxStackSize;
		int remainder = amount % maxStackSize;
		
		return new int[] {stacks, remainder};
	}
	
	/**
	 * Finds the total amount of items
	 * <P> found by multiplying the stacks by the max amount of items in a stack and adding the remaining </P>
	 * <b> stack * maxStackSize + remaining </b>
	 * <P></P>
	 * @param stack
	 * @param remainder
	 * @return the total amount of items
	 */
	public int toWhole(int stack, int remainder)
	{
		return stack * maxStackSize + remainder;
	}
	
	/**
	 * Setter
	 * @param stackSize
	 */
	public void setMaxStackSize(int stackSize)
	{
		this.maxStackSize = stackSize;
	}
}
