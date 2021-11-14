/*[1. Simple Example]
public class Main{
	public static void main(String[] args){
		System.out.println("square(5): "+square(5));
		MyLamda sqr=(x->x*x);//
		System.out.println(lambda expression: "+sqr.mySquare(7));
	}
	
	static int square(int x){ return x*x; }
}
@FunctionalInterface
interface MyLamda{
	int mySquare(int x);//only one method
}*/


/*[2. Example 2]
public class Main{
	public static boid main(String[] args){
		String str1="My String";
		String str2="plus String";
		StringCon con1=(a,b)->System.out.println("("+a+"), ("+b+")");
		con1.myString(str1, str2);
	}
}

interface StringCon{ void myString(String str1, String str2); }
*/


/*[3. Example 3]
public class Main{
	public static void main(String[] args){
		ShowSomething myLamda=str->System.out.println(str);
		myLamda.showSomething("test string");
		showSomething(myLamda);
	}
	static void showSometing(ShowSomething ss){ ss.showSomething("interface parameter"); }
}
interface ShowSomething{ void showSomething(String str1); }
*/

//[4. Example 4]
public class Main{
	public static void main(String[] args){
		myPrint m1=returnStr();
		m1.showSomething("Hello my friend! ");
	}
	static myPrint returnStr(){ return str->System.out.println(str+"plus something"); }
}
interface myPrint{ void showSomething(String str); }

/*
1.	람다식을 구현하기 위해서는 함수형 인터페이스를 만들고, 메소드를 만들고 main에서 람다식을 정의한다. 함수형 인터페이스는 하나의 메소드만 허용한다.
2.	()->에서 ()는 매개변수가 들어가는 자리로 매개 변수가 없다면 아래와 같이 비운 뒤에도 구현이 가능하다.
	ALamda myLam=()->{ System.out.println("Lambda Test"); };
	myLam.show();
	
	interface ALamda{ void show(); }
*/