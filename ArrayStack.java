
 /**
  * Implementation of the stack using a fixed-length array.  An
  * exception is thrown if a push operation is attempted when the size
  * of the stack is equal to the length of the array.  This class
  * includes the most of methods of the built-in class java.util.Stack.
  * 
  */


public class ArrayStack {
	 /**
	  * Length of the array used to implement the stack.
	  */
	  protected int capacity; 	// The actual capacity of the stack array
	 /**
	  * Default array capacity.
	  */
	  public static final int CAPACITY = 1000;	// default array capacity
	 /**
	  * Array used to implement the stack.
	  */
	  protected Object S[];		// Generic array used to implement the stack 
	 /**
	  * Index of the top element of the stack in the array.
	  */
	  protected int top = -1;	// index for the top of the stack, initially top is -1, THIS IS IMPORTANT!
	                            // If not empty, top always points to the top element in the stack.
	 /**
	  * Initializes the stack to use an array of default length.
	  */
	  public ArrayStack() {
	    this(CAPACITY); // default capacity 
	  }
	 /**
	  * Initializes the stack to use an array of given length.
	  * @param cap length of the array.
	  */
	public ArrayStack(int cap) {
	    capacity = cap;
	    S = new Object[capacity]; // compiler may give warning, but this is ok
	  }
	 /**
	  * Returns the number of elements in the stack.
	  * This method runs in O(1) time.
	  * @return number of elements in the stack. 
	  */
	  public int size() { 
	    return (top + 1);
	  }
	 /**
	  * Tests whether the stack is empty.
	  * This method runs in O(1) time.
	  * @return true if the stack is empty, false otherwise.
	  */
	  public boolean isEmpty() {
	    return (top < 0);
	  }
	 /**
	  * Inserts an element at the top of the stack.
	  * This method runs in O(1) time.
	  * @return element inserted.
	  * @param element element to be inserted.
	  * @exception FullStackException if the array storing the elements is full.
	  */
	  public void push(Object element) throws FullStackException {
	    if (size() == capacity)
	      throw new FullStackException("Stack is full.");
	    S[++top] = element;
	  }
	 /**
	  * Inspects the element at the top of the stack.
	  * This method runs in O(1) time.
	  * @return top element in the stack.  
	  * @exception EmptyStackException if the stack is empty. 
	  */
	  public Object top() throws EmptyStackException {
	    if (isEmpty())
	      throw new EmptyStackException("Stack is empty.");
	    return S[top];
	    }
	 /**
	  * Removes the top element from the stack.
	  * This method runs in O(1) time.
	  * @return element removed.
	  * @exception EmptyStackException if the stack is empty.
	  */
	  public Object pop() throws EmptyStackException {
	    Object element;
	    if (isEmpty())
	      throw new EmptyStackException("Stack is empty.");
	    element = S[top];
	    S[top--] = null; // dereference S[top] for garbage collection.
	    return element;
	  }
	
	 /**
	  * Returns a string representation of the stack as a list of elements,
	  * with the top element at the end: [ ... , prev, top ].
	  * This method runs in O(n) time, where n is the size of the stack.
	  * @return textual representation of the stack.
	  */
	
	  public String toString() {
	    String s;
	    s = "[";
	    if (size() > 0) s+= S[0];
	    if (size() > 1)
	      for (int i = 1; i <= size()-1; i++) {
		s += ", " + S[i];
	      }
	    return s + "]";
	  }
	
	 /**
	  * Prints status information about a recent operation and the stack.
	  * @param op operation performed
	  * @param element element returned by the operation
	  * @return information about the operation performed, the element
	  * returned by the operation and the content of the stack after 
	  * the operation.
	  */ 
	
	//  Prints status information about a recent operation and the stack.
	  public void status(String op, Object element) {
	    System.out.print("------> " + op);   // print this operation
	    System.out.println(", returns " + element); // what was returned
	    System.out.print("result: size = " + size() + ", isEmpty = " + isEmpty());
	    System.out.println(", stack: " + this);       // contents of the stack
	  }
	
	
	 /**
	  * Test our program by performing a series of operations on stacks,
	  * printing the operations performed, the returned elements and the
	  * contents of the stack involved, after each operation.
	 * @throws Exception 
	  */
	  public static void main(String[] args) {
	    Object o;
	    ArrayStack A = new ArrayStack();
	    A.status("new ArrayStack() A", null);
	    A.push(7);
	    A.status("A.push(7)", null);
	    o = A.pop();
	    A.status("A.pop()", o);
	    A.push(9);
	    A.status("A.push(9)", null);
	    o = A.pop();
	    A.status("A.pop()", o);
	    ArrayStack B = new ArrayStack();
	    B.status("new ArrayStack<String> B", null);
	    B.push("Bob");
	    B.status("B.push(\"Bob\")", null);
	    B.push("Alice");
	    B.status("B.push(\"Alice\")", null);
	    o = B.pop();
	    B.status("B.pop()", o);
	    B.push("Eve");
	    B.status("B.push(\"Eve\")", null);
	  }
}

class EmptyStackException extends RuntimeException {  
	private static final long serialVersionUID = 1L;

	public EmptyStackException(String err) {
	    super(err);
	  }
}

class FullStackException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FullStackException(String err) {
	    super(err);
	  }
}

