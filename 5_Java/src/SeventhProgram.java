package src;

public class SeventhProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int result = calculate(args[0], args[1]);
		output(args[0], args[1], result);
	}
	
	private static int calculate(String num1, String num2) {
		 return Integer.parseInt(num1) +Integer.parseInt(num2);
	}
	
	private static void output(String num1, String num2, int result) {
		System.out.println("一つ目の引数：" + num1);
		System.out.println("二つ目の引数：" + num2);
		System.out.println("加算の結果：" + result);
	}
}
