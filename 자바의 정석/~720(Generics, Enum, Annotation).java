import java.util.*;
import java.lang.annotation.*;

@Deprecated//p.719
//@SuppressWarnings("1111");//ignore
@TestInfo(testedBy="aaa", testDate=@DateTime(yymmdd="160101", hhmmss="235959"))//our Annotation
public class tutorial {
	public static void main(String[] args) throws Exception{
		System.out.println("[p.677]");//common use of limited Generics Class with interface.
		/*FruitBox<Fruit> fruitBox=new FruitBox<Fruit>();
		FruitBox<Apple> appleBox=new FruitBox<Apple>();
		FruitBox<Grape> grapeBox=new FruitBox<Grape>();
		//FruitBox<Grape> grapeBox=new FruitBox<Apple>();//Type Error.
		//FruitBox<Toy> toyBox=new FruitBox<Toy>();//Error.
		
		fruitBox.add(new Fruit());
		fruitBox.add(new Apple());
		fruitBox.add(new Grape());
		appleBox.add(new Apple());
		//appleBox.add(new Grape());//Type Error.
		grapeBox.add(new Grape());
		
		System.out.println("fruitBox-"+fruitBox);
		System.out.println("appleBox-"+appleBox);
		System.out.println("grapeBox-"+grapeBox);*/
		
		//Another similar Example p.681
		System.out.println("\n\n[p.681]");
		FruitBox<Apple> appleBox=new FruitBox<Apple>();
		FruitBox<Grape> grapeBox=new FruitBox<Grape>();
		
		appleBox.add(new Apple("GreenApple", 100));
		appleBox.add(new Apple("GreenApple", 300));
		appleBox.add(new Apple("GreenApple", 200));
		
		grapeBox.add(new Grape("GreenGrape", 400));
		grapeBox.add(new Grape("GreenGrape", 300));
		grapeBox.add(new Grape("GreenGrape", 200));
		
		Collections.sort(appleBox.getList(), new AppleComp());//
		Collections.sort(grapeBox.getList(), new GrapeComp());
		System.out.println(appleBox);
		System.out.println(grapeBox);
		System.out.println();
		
		Collections.sort(appleBox.getList(), new FruitComp());
		Collections.sort(grapeBox.getList(), new FruitComp());
		System.out.println(appleBox);
		System.out.println(grapeBox);
		
		
		System.out.println("\n\n[p. 696]");//Enum class
		for(Direction d: Direction.values())
			System.out.printf("%s=%d\n", d.name(), d.getValue());
		
		Direction d1=Direction.EAST;
		Direction d2=Direction.of(1);
		System.out.printf("d1=%s, %d%n",d1.name(), d1.getValue());
		System.out.printf("d2=%s, %d%n", d2.name(), d2.getValue());
		
		System.out.println(Direction.EAST.rotate(1));
		System.out.println(Direction.EAST.rotate(2));
		System.out.println(Direction.EAST.rotate(-1));
		System.out.println(Direction.EAST.rotate(-2));
		
		
		System.out.println("\n\n[p.710]");//Annotation. We can Suppress whole unchecked warning by @SuppressWarnings("unchecked")
		//@SuppressWarnings("unchecked")
		MyArrayList<String> list=MyArrayList.asList("1", "2", "3");
		System.out.println(list);
		
		
		System.out.println("\n\n[p.719]");//User designed Annotation
		Class<tutorial> cls=tutorial.class;//Get tutorial class.
		
		TestInfo anno=(TestInfo)cls.getAnnotation(TestInfo.class);//get tutorial class's Annotation that is TestInfo to anno by using getAnnotation method to class object.
		System.out.println("anno.testedBy()="+anno.testedBy());
		System.out.println("anno.testDate().yymmdd="+anno.testDate().yymmdd());
		System.out.println("anno.testDate().hhmmss()="+anno.testDate().hhmmss());
		
		for(String str: anno.testTools())
			System.out.println("testTools="+str);
		System.out.println();
		
		Annotation[] annoArr=cls.getAnnotations();
		for(Annotation a: annoArr)
			System.out.println(a);
		
	}//main END
	
}//tutorial END


/*interface Eatable{}//p.677
class Fruit implements Eatable{
	public String toString() { return "Fruit"; }
}
class Apple extends Fruit{ public String toString() { return "Apple"; } }
class Grape extends Fruit{ public String toString() { return "Grape"; } }
class Toy{ public String toString() { return "Toy"; } }

class FruitBox<T extends Fruit & Eatable> extends Box<T>{}//Limited Generics! 
class Box<T>{//defined with generics for containing Apple, Grape not Toy that doesn't have Eatable
	ArrayList<T> list=new ArrayList<T>();
	void add(T item) { list.add(item); }
	T get(int i) { return list.get(i); }
	int size() { return list.size(); }
	public String toString() { return list.toString(); }
}*/

class Fruit{//p.681_Objects
	String name;
	int weight;
	
	Fruit(String name, int weight){
		this.name=name;
		this.weight=weight;
	}
	public String toString() { return name+"("+weight+")"; }
}
class Apple extends Fruit{
	Apple(String name, int weight){ super(name, weight); }
}
class Grape extends Fruit{
	Grape(String name, int weight){ super(name, weight); }
}

class AppleComp implements Comparator<Apple>{//Comp methods
	public int compare(Apple t1, Apple t2) { return t2.weight-t1.weight; }//reversely
}
class GrapeComp implements Comparator<Grape>{
	public int compare(Grape t1, Grape t2) { return t2.weight-t1.weight; }
}
class FruitComp implements Comparator<Fruit>{
	public int compare(Fruit t1, Fruit t2) {
		return t1.weight-t2.weight;
	}
}

class FruitBox<T extends Fruit> extends Box<T>{}//Box
class Box<T>{
	ArrayList<T> list=new ArrayList();
	void add(T item) { list.add(item); }
	T get(int i) { return list.get(i); }
	ArrayList<T> getList(){ return list; }
	int size() { return list.size(); }
	public String toString() { return list.toString(); }
}


enum Direction{//p.696
	EAST(1, ">"), SOUTH(2, "V"), WEST(3, "<"), NORTH(4, "^");//local global variables..!?
	
	private static final Direction[] DIR_ARR=Direction.values();
	private final int value;
	private final String symbol;
	
	Direction(int value, String symbol){
		this.value=value;
		this.symbol=symbol;
	}
	
	public int getValue() { return value; }
	public String getSymbol() { return symbol; }
	
	public static Direction of(int dir) {
		if(dir<1 || dir>4)
			throw new IllegalArgumentException("Invalid value: "+dir);
		return DIR_ARR[dir-1];
	}
	
	public Direction rotate(int num) {
		num%=4;
		if(num<0)//if negative
			num+=4;//rotate reversely
		
		return DIR_ARR[(value-1+num)%4];//EAST is 1, DIR_ARR[0]=EAST. 
	}
	public String toString() { return name()+getSymbol(); }
}


class MyArrayList<T>{//p.710
	T[] arr;
	
	@SafeVarargs
	@SuppressWarnings("varargs")
	MyArrayList(T... arr){ this.arr=arr; }
	
	@SafeVarargs
	//@SuppressWarnings("unchecked")//we can suppress this varargs by suppressing unchecked
	public static <T> MyArrayList<T> asList(T...a){ return new MyArrayList<>(a); }
	
	public String toString() { return Arrays.toString(arr); }
}


@Retention(RetentionPolicy.RUNTIME)//p.719
@interface TestInfo{
	int count()				default 1;
	String testedBy();
	String[] testTools()	default "JUnit";
	TestType testType()		default TestType.FIRST;
	DateTime testDate();
}

@Retention(RetentionPolicy.RUNTIME)
@interface DateTime{
	String yymmdd();
	String hhmmss();
}

enum TestType{FIRST, FINAL};