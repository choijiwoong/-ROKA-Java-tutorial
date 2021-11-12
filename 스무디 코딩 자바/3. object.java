import java.time.*;//use library by .
package com;//project's folder is Hello, com is in hello.

public class Main{
	public static void main(String[] args){//'void' java don't have to return any returned value to JVM
		//1. import external library
		System.out.println(LocalDateTime.now());
		
		//2. use package
		com.Calculator newCal=new com.Calculatoe();
		
		//3. use user defined method
		myClass mc=new myClass();
		mc.myFunction();
	}
	
	public static int square(int number){ return number*number; }//for use in main function without instantiation
}
class myClass{
	void myFunction(){ System.out.println("new Function"); }
}

/*
1.	패키지는 같은 이름의 클래스 구분을 보다 쉽게 하고, 외부 라이브러리와 겹치지 않게 한다.
2.	public은 접근지시자, main함수는 Main class의 인스턴스를 만들지 않아도 사용할 수 있게끔 static으로 사용한다.
	main함수 역시 class안에 있는 이유는 자바는 모든것이 객체지향이기에 혼자 독립할 수 없기 때문이다.
	 main이 리턴하는 값은 JVM에게 전달된다.
3.	객체 생성 없이 main에서 실행하려면 main처럼 static으로 생성하면 된다.
*/