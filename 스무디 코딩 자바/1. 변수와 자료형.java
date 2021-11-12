/*
[1. Variable & type]
Java is static language like C not dynamic language like Javascript, Python...etc that's type isn't defined by programmer
Java occur error if we assign variable more than allocated space unlike C's overflow
long myLong=40000000000L; //Literal!
*/

public class Main(){
	public static void main(String[] args){
		//1. java uses unicode
		char myChar=44032;
		System.out.println(myChar);//"가"
		
		//2. typecasting char
		for(int i=33; i<127; i++){//_ASCII Char Print
			System.out.print((char)i);
			if(i%20==0)//'\n'
				System.out.println();
		}
		for(int i=44032; i<55296; i++){//_UNICODE Char Print
			System.out.print((char)i);
			if(i%20==0)
				System.out.println();
		}
		//(p.s)we can type 16xcode by \u. for using UNICODE, we don't have to convert number to decimal. 
		System.out.println('\uAC00');
		
		//3. range of real type
		System.out.println("Minimum of flaot: "+Float.MIN_VALUE);
		System.out.println("Minimum of float(-): "+-Float.MAX_VALUE);
		System.out.println("Maximum of float(+): "+ Float.MAX_VALUE);
		
		System.out.println("Minimum of double: "+Double.MIN_VALUE);
		System.out.pritnln("Minimum of double(-): "+-Double.MAX_VALUE);
		System.out.println("Maximum of double(+): "+ Double.MAX_VALUE);
		
		//4. Problem of IEEE
		double dVar;//dollar
		dVar=0.1*3/3;
		System.out.println(dVar);//0.10000000000000002
		//
		double myDouble=0;
		for(int i=1; i<=100; i++)
			myDouble+=0.1;
		System.out.println(myDouble);//9.99999999999998
		
		//5. Java's default real type is double.
		float myFloat=10.0;//ERROR!
		float myFloat=10.0F;//GOOD!
	}
}

/*
1.	자바는 파이썬, 자바스트립트같은 동적인 언어(타입지정을 안해요)와 다르게 C와 같은 정적인 언어이다.
	자바는 C와 다르게 할당된 크기를 넘어서면 오버플로우가 아닌 에러를 발생시키기에 조금 더 엄격하다.
2.	long을 사용할 때 int를 넘어선다면 literal L을 붙여야 한다.
3.	자바는 아스키코드뿐만 아닌 유니코드를 사용한다. System.out.println에 유니코드나 아스키코드로 나타내기 위해 char로 type casting을 하면 알아서 decode하여 결과를 나타낸다.
	(p.s 16진수를 명시적으로 말하기 위해 \u를 사용할 수 있다.)
4.	부동소수점은 정수에 비해 연산시간이 길지만, 과학적 표기법(E+133)을 사용하여 표현범위가 넓다는 장점이 있다.
5.	자바의 default실수형은 double이기에 float에 일반 소수를 그대로 넣으면 float에 double을 넣는것으로 간주하여 오류를 발생시키기에 Literal F를 붙여야 한다.
*/