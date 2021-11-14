public class Main{
	public static void main(Stirng[] args){
		//Common method of Object like toString(), equals(), hashCode() and operator overloading like =
		MyObject obj1=new MyObject(101, "First Object");
		MyObject obj2=obj1;
		
		System.out.println(obj1.toString());
		System.out.println(obj2.toString());
		System.out.println(obj1.equals(obj2));//cmp not address. just value.
		
		MyObject obj3=new MyObject(102, "Second Instance");
		
		System.out.println(obj3.toString());
		System.out.println("obj1.equals(obj3): "+obj1.equals(obj3));
		
		//	hashcode for instance address
		System.out.format("Hash code of obj1: %x\n", obj1.hashCode());//address of heap memory
		System.out.format("Hash code of obj2: %x\n", obj2.hashCode());
		System.out.format("Hash code of obj3: %x\n", obj3.hashCode());
		
		//	toString Override
		Object obj4=new OverrideToString();
		System.out.println(obj4.toString());
		
		
		//Clone
		CloneEx cl1=new CloneEx(1001, "Clone one");
		CloneEx copiedClone=(CloneEx)cl1.clone();//Type Recasting to CloneEx for using Cloneable interface's function. maybe default
		System.out.println("[clone test]");
		System.out.println("cl1: "+cl1);
		System.out.println("copiedClone: "+copiedClone);
		System.out.println("cl1.id: "+cl1.id);
		System.out.println("copiedClone.id: "+copiedClone.id);
	}
}

class MyObject extends Object{//extends Object is default setting. so it's same code without 'extends Object'
	int objId;
	String objName;
	
	public MyObject(int objId, String objName){
		this.objId=objId;
		this.objName=objName;
	}
}

class OverrideToString{//for show override Object's toString method
	@Override
	public string toString(){
		return "* Overrided to String *";
	}
}

class CloneEx implements Cloneable{
	int id;
	String name;
	
	public CloneEx(int id, String name){
		this.id=id;
		this.name=name;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException{
		return super.clone();//오버라이드 하는 척하며 안해버리기
	}
}

/*
	[Common Class in java.lang]
1.	object: 모든 클래스의 조상(base)이다. 운용체제와 JVM사이의 관리를 한다. 모든 클래스는 암묵적으로 extends Object를 한다.
	new, 각종 연산자 등 여러 메소드들이 들어이다.
2.	String
3.	Wrapper
4.	Class
*/