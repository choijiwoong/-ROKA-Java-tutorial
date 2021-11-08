import java.util.ArrayList
import java.util.Hashmap

public class Main{
	public static void main(String[] args){
	//Generics
		//ArrayList<String> aList=new ArrayList<String>(); //<>is called Generics(explicit type checking by generics).
		
		ArrayList aList=new ArrayList();//if no generics
		aList.add("hello");//hello's type is guessed defaultly 'object' that every object interitance
		aList.add("java");
		
		String hello=(String)aList.get(0);//we have to cast before use!
		String java=(String)aList.get(1);//and if aList has different object like int, it can make casting loss problem
	
		//so we don't use casting by using generics
		ArrayList<String> aList2=new ArrayList<String>();
		aList2.add("hello");
		aList2.add("java");
		
		String hello2=aList2.get(0);
		String java2=aList2.get(1);
		
		System.out.println(hello2+ java2);
		
	//Map(Associative array, Hash_Key&value, get value by key without sequential access)
		HashMap<String, String> map=new HashMap<String, String>();//use generics to key&value's type
		map.put("people", "사람");//.put!
		map.put("baseball", "야구");
		
		System.out.println(map.get("people"));//.get()!
		System.out.println(map.containsKey("people"));//.contatinsKey() search key
		System.out.println(map.remove("perple"));//false! .remove()
		System.out.println(map.size());//.size()!
		
	//example code
		HashMap<Integer, String> library=new HashMap<Integer, String>();//use object type! not primitive like int
		library.put(2943, "The computer");
		library.put(8274, "Ulsan camping");
		library.put(1111, "Pepe Ro");
		
		System.out.println(library.get(1111));
		System.out.println(library.containsKey(8274));
		System.out.println(library.remove(8274));
		System.out.println(library.size());
		
	}
}