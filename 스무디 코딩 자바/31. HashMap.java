/*[1. HashMap]
import java.util.HashMap;

public class Main{
	public static void main(String[] args){
		HashMap<Integer, String> hashMap=new HashMap<Integer, String>();
		hashMap.put(101, "HashMap Test");
		hashMap.put(123, "password");
		hashMap.put(203, "apartment number");
		hashMap.put(777, "slot machine");
		hashMap.put(501, "address");
		hashMap.put(999, "last number");
		
		System.out.println("----Iterating HashMap----");
		for(Map.Entry m1: hashMap.entrySet())//Entry is class for Map that has getter of Key, Value
			System.out.println("m1.getKey(): "+m1.getKey()+" | m2.getValue(): "+m1.getValue());
		
		System.out.println("----Remove using key----");
		int keyToRemove=123;
		if(hashMap.containsKey(keyToRemove))
			hashMap.remove(keyToRemove);
		
		System.out.println("----Iterator HashMap----");
		Iterator<Integer> irt=hashMap.keySet().iterator();//iterator method on keySet()!
		while(irt.hasNext()){
			int key=irt.next();
			String str1=hashMap.get(key);//can get value also
			System.out.println("- "+str1);
		}
	}
}*/

//[2. TreeMap]
import java.util.*;

public class Main{
	public static void main(String[] args){
		TreeMap<Integer, String> tMap=new TreeMap<Integer, String>();
		tMap.put(5, "Oh test!");
		tMap.put(2, "Tree Map Avaiable");
		tMap.put(1, "Tree Map is easy");
		tMap.put(3, "Key and Value");
		tMap.put(9, "Binary Tree");
		tMap.put(4, "Map is not Set");
		tMap.put(6, "Integer has comparable");
		tMap.put(8, "treemap use put");
		tMap.put(7, "this is the last one");
		
		for(Map.Entry m1: tMap.entrySet()){//use like HashMap
			System.out.println(m1.getKey()+": "+m1.getValue());
		}
	}
}