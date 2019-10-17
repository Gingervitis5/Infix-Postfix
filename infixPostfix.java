import java.util.EmptyStackException;
import java.util.Scanner;

public class infixPostfix {

	private String infix = "";
	private String postfix = "";
	private int eval;
	
	public class ArrayStack{
		private int capacity;
		public static final int defaultCapacity = 1000;
		private Object S[];
		private int top = -1;
		
		public ArrayStack(int cap){
			this.capacity = cap;
			this.S = new Object[this.capacity];
		}

		public int size(){
			return (top + 1);
		}
	 
		public boolean isEmpty(){
			return top < 0;
		}

		public void push(Object e){
			if (this.size() == this.capacity){
				return;
			}
			else {
				S[++top] = e;
			}
		}

		public Object pop() throws EmptyStackException{
			Object element;
			if (isEmpty()) throw new EmptyStackException();
			element = this.S[top];
			S[top--] = null;
			return element;
		}

		public Object top() throws EmptyStackException{ 
			if (isEmpty()) throw new EmptyStackException();
			return this.S[top];
		}
	}
	
	public infixPostfix() {
		this.infix = "";
		this.postfix = "";
		this.eval = 0;
	}
	
	public void setInfix() {
		Scanner kb = new Scanner(System.in);
		System.out.println("DO NOT ENTER ANY SPACES");
		System.out.println("Enter a infix expression: ");
		String infix = kb.nextLine();
		kb.close();
		this.infix = infix;
	}
	
	public int precOnStack(String op){
		if(op.equals("^")){
			return 5;
		}
		else if (op.equals("*")){
			return 4;
		}
		else if (op.equals("/")) {
			return 4;
		}
		else if (op.equals("%")) {
			return 4;
		}
		else if (op.equals("+")) {
			return 2;
		}
		else if (op.equals("-")) {
			return 2;
		}
		else {
			return -1;
		}
	}
	
	public int precCurrent(char op){
		if (op == '(') {
			return 100;
		}
		else if(op == '^'){
			return 6;
		}
		else if (op == '*'){
			return 3;
		}
		else if (op == '/') {
			return 3;
		}
		else if (op == '%') {
			return 3;
		}
		else if (op == '+') {
			return 1;
		}
		else if (op == '-') {
			return 1;
		}
		else {
			return -1;
		}
	}
	
	public void infixToPostfix(String infix) {
		this.infix = infix;
		ArrayStack nas = new ArrayStack(10);
		char op = infix.charAt(0);
		char[] ca = infix.toCharArray();
		int ix = 0;
		while (ix < ca.length) {
			if (op == '0' || op == '1' || op == '2' || op == '3' || op == '4' || 
					op == '5' || op == '6' || op == '7' || op == '8' || op == '9') {
				this.postfix += op;
			}
			
			else if (op == ')') {
				while ((char)nas.top() != '(') {
					char temp = (char)nas.pop();
					this.postfix += temp;
				}
				nas.pop();
			}
			else {
				while (!nas.isEmpty() && precOnStack(nas.top().toString()) > precCurrent(op)) {
					char temp = (char)nas.pop();
					this.postfix += temp;
				}
				nas.push(ca[ix]);
			}
			ix++;
			if (ix < ca.length) {
				op = ca[ix];
			}
		}
		while(!nas.isEmpty()) {
			char temp = (char)nas.pop();
			this.postfix += temp;
		}
		
	}
	
	public void postFixEval() throws Exception{
		char[] arr = this.postfix.toCharArray();
		ArrayStack nas = new ArrayStack(10);
		int ix = 0;
		char op = arr[ix];
		while (ix < arr.length) {
			if (op == '0' || op == '1' || op == '2' || op == '3' || op == '4' || 
					op == '5' || op == '6' || op == '7' || op == '8' || op == '9') {
				nas.push(arr[ix]);
			}
			else {
				int rop = Integer.parseInt(nas.pop().toString());
				int lop = Integer.parseInt(nas.pop().toString());
				int result = 0;
				if (op == '+') {
					result = lop + rop;
					nas.push(result);
				}
				else if (op == '-') {
					result = lop - rop;
					nas.push(result);
				}
				else if (op == '/') {
					result = lop / rop;
					nas.push(result);
				}
				else if (op == '*') {
					result = lop * rop;
					nas.push(result);
				}
				else if (op == '^') {
					result = (int)(Math.pow((double)lop,(double)rop));
					nas.push(result);
				}
			}
			ix++;
			if (ix < arr.length) {
				op = arr[ix];
			}
		
		}
		if (nas.top == 0) {
			this.eval = Integer.parseInt(nas.pop().toString());
		}
		else {
			throw new Exception("Postfix has an error in it.");
		}
	}
	
	@Override
	public String toString() {
		return "Infix: " + this.infix + "\nPostfix: " + this.postfix + "\nResult: " + this.eval;
	}

	public static void main(String[] args) throws Exception {
		infixPostfix test1 = new infixPostfix();
		test1.setInfix();
		test1.infixToPostfix(test1.infix);
		test1.postFixEval();
		System.out.println(test1);
	}
	
}
