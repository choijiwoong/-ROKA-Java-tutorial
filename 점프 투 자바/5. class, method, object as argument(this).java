//[1. control statement]
//List type's contains method can be in condition of if statement. if, switch&case, while condition statement is same with C
//while(true)
//	System.out.println("Press Ctrl-c for exit");
//[1-1. for each]	
//at J2SE 5.0, for each is added.
//String[] numbers={"one", "two", "three"};
//for(String number:numbers)//it's same 'range based for loop(const auto& e: vec)' in C++!
//	System.out.println(number);


public class Main{
	public static void main(String args[]){
	//[2. Object Oriented Programming by Class]
		class Animal{
			String name;
			public void setName(String name) {
				this.name=name;
			}
		}
		Animal cat=new Animal();//create&initialization go together!.
		System.out.println("initial name: "+cat.name);
		cat.setName("boby");
		System.out.println("name after setting: "+cat.name+"\n");
		
	//[3. Method; method & function are same in JAVA]
		class Test{
			public int sum(int a, int b) { return a+b; }//1. input_T, return_T(common)
			public void sum_print(int a, int b) { System.out.println("sum of"+a+", "+b+": "+(a+b)); }//2. input_T, return_F
			public String say() { return "Hi"; }//3. input_F, return_T
			public void say_empty(){ System.out.println("Hi");}//4. input_F, return_F
		}
		
		int a=3, b=4;
		
		Test myTest=new Test();
		
		int c=myTest.sum(a, b);//1.
		System.out.println(c);
		
		myTest.sum_print(a, b);//2.
		
		String d=myTest.say();//3.
		System.out.println(d);
		
		myTest.say_empty();//4.
		
	System.out.println();
	//[4. Pass object to Method]
		class Test2{
			int a;
			void vartest(Test2 test) { test.a++; }//method's body can be replaced to this.a++ without argument 'Test2 test'
		}
		
		Test2 myTest2=new Test2();
		myTest2.a=1;
		myTest2.vartest(myTest2);
		System.out.println(myTest2.a);
	}
}