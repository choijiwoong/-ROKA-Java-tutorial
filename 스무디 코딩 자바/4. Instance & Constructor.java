public class Main{
	public static main(String[] args){
		//1. new object
		Person mp=new Person();//new keyword means Dynamic Memory Allocation+ Making instance in Heap.
		mp.name="Cookie King";
		mp.showName();
		System.out.println(mp);//print com.miniPerson@7ef20235 It's hashcode(virtual address) that is passed to JVM for connecting real address. 
		//mp is called as reference variable; address of instance on memory
		
		//2. constructor
		mp.showRef();//show reference variable of mp instance
		Person p1=new Person("Mini");//overloading! constructor is called in 'new Person("Mini")'
		Person p2=new Person("Tykon");
		Person p3=new Person("Lapi");
		Person p4=new Person();
		
		p1.showName();
		p2.showName();
		p3.showName();
		p4.showName();
	}
}
class Person{
	String name;
	
	public Person(){//user defined constructor! compiler doesn't make default constructor
		name="John Doe";
		System.out.println("constructing... "+name);
	}
	public Person(String name){
		this.name=name;//this reserved word
		System.out.println(this.name+"Constructed...");
	}
	
	void showName(){ System.out.println(name); }
	void showRef(){ System.out.println(this); }
}

/*
	[Instance]
1.	클라우드 환경에서 인스턴스의 개수는 시스템 자원에 영향을 받기 때문에 대량의 인스턴스를 사용하기 전에는 미리 계산하여 데이터의 크기를 감안하여 설계해야 한다.
2.	new키워드를 통해 생성이 필요하지 않은 클래스는 static class(기능상 인스턴스화가 필요하지 않은 클래스)뿐이다.
	[Constructor]
1.	default constructor는 user designed constructor를 설정하면 기본으로 만들지 않는다.
	(p.s) Person(){}//default constructor
2.	this reserved word는 클래스 내부에서 해당 인스턴스를 지칭하는 표현이다.
*/