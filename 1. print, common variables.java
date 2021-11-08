package java_project_1;

public class Main {

	public static void main(String[] args) {//we can get parameter of main by args
	//[1. common uses]
		System.out.println("Hello Java World!");
		
		int a=1;
		String b="Hello java";
		System.out.println(b);
		
	//[2. type]
		//2-1. Number
		long countOfStar=248912749127L;//we have to put L(literal) to long type if it's over max of int
		float pi=3.14F;//i think it must have literal...to type
		double morePi=3.141592653589793238462643383279;//default set of real number is double!
		
		double d1=123.4;
		double d2=1.234e2;//eN means *10^N
		
		int octal=023;//0num-Octal
		int hex=0xC;//0xnum-Hex
		
		//common calculation of number
		int num1=10;
		int num2=5;
		System.out.println(num1+num2);
		System.out.println(num1-num2);
		System.out.println(num1*num2);
		System.out.println(num1/num2--);
		System.out.println(++num1%num2);
		
		//2-2. Boolean
		int base=180;
		int height=185;
		boolean isTall=height>base;
		if(isTall) {
			boolean isEqual="3".equals("2");
			System.out.println("height is high!");
		}
	}
}
