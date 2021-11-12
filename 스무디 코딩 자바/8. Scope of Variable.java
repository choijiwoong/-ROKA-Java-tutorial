public class Main{
	//1. Scope
	public static int num=3;
	
	public static void main(String[] args){
		//This example's scope is already defined
		myMember m1=new myMember();//allocation occur by new keyword in Heap! dynamic allocation!
		m1.serialNumber=1001;//we can check it's belong by m1. unlike global variable (C)
		m1.connectionName="first connection";
		m1.showMemberList();
	}
	
	public static void func1(){
		int Num=2;
		System.out.println("func1!");
	}
	public static void func2(){
		System.out.println("func2!");
		System.out.println(num);//low readability!
	}
	
	//2. Array
	int[] student=new int[30];//allocation in dynamic memory(Heap!)
	for(int i=0; i<30; i++)
		student[i]=(int)(Math.random()*10)+1;
	
	for(int i=0; i<30; i++)
		if(i%10==0)
			System.out.println();
		System.out.print(student[i]+", ");
		
	//3. way of initialization of array
	//3-1)
	int[] myArray1=new int[]{10, 20, 30};//we can't use new int[3]{2,4,6}; for compiler's optimization
	//ERROR: array creation with both dimension expression(int[3]) and initialization({2,4,6})
	System.out.println("Size of myArray1: "+myArray1.length);//3
	//3-2)
	int[] myArray2={11,22,33,44,55};
	System.out.println("Size of myArray2: "+myArray2.length);//5
	//3-3)
	int[] myArray3;
	myArray3=new int[]{5,3,1};
	System.out.println("Size of myArray3: "+myArray3.length);//3
	//3-4)
	int[] myArray4;
	myArray4=new int[2];
	System.out.println("Size of myArray4: "+myArray4.length);//2
	//3-5)
	int[] myArray5=new int[10];
	myArray5[0]=3;
	myArray5[1]=777;
	System.out.println("Size of myArray5: "+myArraay5.length);//10
	
	//4. improved for
	String[] myStr={"apple", "kiwi", "orange", "strawberry"};
	for(String val: myStr)
		System.out.println(val);
}

class myMenber{
	int serialNumber;
	String connectionName;
	public void showMemberList(){
		System.out.println(this.serialNumber);
		System.out.println(this.connectionName);
	}
}

/*
	[Scope]
1.	자바에서는 전역변수의 개념이 없고, 지역&인스턴스&static 변수 세가지이다.
2.	전역변수 대용처럼 사용하는것이 static인데, 어느 함수 안에서 정의되었는지를 이름에 포함시키면 디버깅과 가독성에 좋다.
	또한 설계단계에서 얼마나 static을 사용할 수 있는지 파악 후 꼭 사용할 곳이만 사용하는것이 좋다. 현재 변수가 어떤 메모리를 점유하고 있으며 수명이 얼마나 되는지 인스턴스의 GC상황과 함께 static 변수들의 상황에 대해서도 체크해야한다.
3.	인스턴스는 런타임에 힙메모리에 동적으로 할당되기에 범위는 인스턴스가 생성된 뒤~프로그램 종료시이다.

	[Array]
1.	type(math.random()*range)+1을 통해 난수를 만들 수 있다.
2.	배열의 선언시 초기화를 함께 한다면 배열의 컴파일러가 소스코드를 읽고 배열의 최종 크기를 판단하게 하기 위하여 크기를 넣지 않는다.
3.	new연산자는 malloc처럼 컴파일타임이 아닌 런타임에 크기를 판단하는 것인데, JS나 파이썬같은 Dynamic Typing언어에는 new연산자가 생략된 것과 비슷하다. 즉 컴파일러나 인터프리터가읽는 시점을 기준으로 할당할 메모리의 크기를 결정한다는 것이다.
4.	자바의 향상된 for문이라고 하는 python읜 enumeration, C++의 range based loop같은 것을 지원한다.
*/