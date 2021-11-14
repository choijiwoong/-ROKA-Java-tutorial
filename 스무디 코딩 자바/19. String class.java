import java.lang.*//it's automatically imported by compiler.

public class Main{
	public static void main(String[] args){
		String greeting="Hello world!";
		String hello="Hello World!";
		
		System.out.println(greeting==hello);
		System.out.println(greeting.equals(hello));
		
		System.out.format("hash code of greeting: %x\n", greeting.hashCode());
		System.out.format("hash code of hello: %x\n", hello.hashCode());
		
		System.out.format("address of greeting: %x\n", System.identityHashCode(greeting));
		System.out.format("address of hello: %x\n", System.identityHashCode(hello));
		
		//
		String sayHello=new String("Hello World!");
		String helloWorld=new String("Hello World!");
		
		//cmp instance address
		System.out.println(sayHello==helloWorld);//cmp address!! not value. object name points hash key
		//cmp String value
		System.out.println(sayHello.equals(helloWorld));
		
		System.out.println("toString: "+sayHello);
		
		System.out.format("hash code of sayHello: %x\n", sayHello.hashCode());//same hashcode!!
		System.out.format("hash code of helloWorld: %x\n", helloWorld.hashCode());
		
		sayHello+="s";//if change value, different hashCode now. C++강의에서 본거같은데.. 값이 바뀌기 전까지 같은 객체 가리키는 최적화 기법이었나...잘 안쓰이는거로 기억하는뎀
		System.out.format("hash code of sayHello: %x\n", sayHello.hashCode());//different hashcode!
		System.out.format("hash code of helloWorld: %x\n", helloWorld.hashCode());
		
		System.out.format("address of sayhello: %x\n", System.identityHashCode(sayHello));
		System.out.format("address of helloWorld: %x\n", System.identityHashCode(helloWorld));
	}
}

/*
	[Common Class in java.lang]
1.	object: 모든 클래스의 조상(base)이다. 운용체제와 JVM사이의 관리를 한다. 모든 클래스는 암묵적으로 extends Object를 한다.
	new, 각종 연산자 등 여러 메소드들이 들어이다.
2.	String: ==로 cmp hashkey, System.identityHashCode(String)으로 진짜 메모리 주소, ""생성과 new생성의 차이가 있다.
	최적화 기법을 사용하는 new가 좀 더 안정적으로 판단된다.
3.	Wrapper
4.	Class
*/