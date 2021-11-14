/*[1. Use Method of Arrays.stream()]
import java.util.Arrays;

public class Main{
	public static void main(String[] args){
		int[] myArray={2,4,6,8,10};
		System.out.println("----Stream foreach----");
		Arrays.stream(myArray).forEach(a->System.out.print(a+", "));//convert array to stream by using Arrays.stream(array);
		
		System.out.println("\n----Stream sum, count----");
		System.out.println("sum: "+Arrays.stream(myArray).sum());
		System.out.println("count: "+Arrays.stream(myArray).count());
		
		System.out.println("----Stream max, min----");
		System.out.println("max: "+Arrays.stream(myArray).max());
		System.out.println("min: "+Arrays.stream(myArray).min());
	}
}*/

//[2. Stream Class of Collection]
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main{
	public static void main(String[] args){
		List<String> myList=new ArrayList<String>();//cast ArrayList to List
		myList.add("apple");
		myList.add("kiwi");
		myList.add("mango");
		myList.add("banana");
		myList.add("watermelon");
		myList.add("lime");
		myList.add("orange");
		myList.add("strawberry");
		
		Stream<String> myStream=myList.stream();//for using .stream method. get by Stream
		myStream.forEach(str->System.out.print(str+", "));//execute lamda by forEach method
		System.out.println();
		
		myList.stream().sorted().forEach(str->System.out.println(str));//use sorted() method of stream
	}
}

/*
1.	자바에서 말하는 스트임은 콜렉션 프레임워크와 함께 사용하는 스트림을 말한다.
2.	배열과 함께 사용한 스트림을 통하여 forEach(), sum(), count()같은 메소드등을 사용했다. 즉, 스트림에 미리 구현된 메소드들로 연산을 한 것이다.
*/