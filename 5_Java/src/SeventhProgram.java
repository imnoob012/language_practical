package src;

public class SeventhProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num1 = Integer.parseInt(args[0]);
		int num2 = Integer.parseInt(args[1]);
		int result = calculate(num1, num2);
		
		output(num1, num2, result);
	}
	
	private static int calculate(int num1, int num2) {
		 return num1 + num2;
	}
	
	private static void output(int num1, int num2, int result) {
		System.out.println("一つ目の引数：" + num1);
		System.out.println("二つ目の引数：" + num2);
		System.out.println("加算の結果：" + result);
	}
}
