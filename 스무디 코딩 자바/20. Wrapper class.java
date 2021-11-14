public class Main{
	public static void main(String[] args){
		Integer num1=999;
		System.out.println("num1: "+num1.intValue());//same to +num1);
		
		Integer num2=Integer.valueOf("777");//string to number in Integer
		Integer num3=Integer.valueOf("223");
		System.out.println("num2: "+num2);
		System.out.println("num3: "+num3);
		
		int num4=Integer.parseInt("111");//String to number in int
		System.out.println("num4: "+num4);
		
		//Above function is already made by auto boxing&unboxing of JAVA5
		Integer num5=num4;
		System.out.println("num5: "+num5);
		
		int sum=num2+num3;//unboxing
		System.out.println("sum: "+sum);
	}
}

/*
	[Common Class in java.lang]
1.	object: 모든 클래스의 조상(base)이다. 운용체제와 JVM사이의 관리를 한다. 모든 클래스는 암묵적으로 extends Object를 한다.
	new, 각종 연산자 등 여러 메소드들이 들어이다.
2.	String: ==로 cmp hashkey, System.identityHashCode(String)으로 진짜 메모리 주소, ""생성과 new생성의 차이가 있다.
	최적화 기법을 사용하는 new가 좀 더 안정적으로 판단된다.
3.	Wrapper: 기본 자료형을 클래스로 만들어주는 클래스이다. Object로 형변환이 가능하다는 것이 가장 큰 이점이다. int보단 아무래도 메소드나 상수들로 인해 속도적으로 불리하지만, 하드웨어성능으로 극복?해보자
	valueof로 String을 Wrapper클래스에 담을 수 있으며, parseInt꼴로 int변수에 String을 담을 수 있다.
	 Wrapper의 또다른 장점은 이 또한 Object의 extension이기때문에 object의 메소드를 사용할 수 있다는 것이다.
4.	Class
*/