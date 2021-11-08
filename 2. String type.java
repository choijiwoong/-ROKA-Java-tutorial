public class Main{
	public static void main(String[] args){
	//1. String
		String a="Happy Java";//this literal way is better for readability & optimization on compile.
		String b=new String("Happy Java");
		//first way is called "Literal String", that is saved in intern pool. if same String is defined, return cached String.
		//second way always makes new String object.
		
	//2. Primitive; can't make by new keyword(ex. int, long, double, float, boolean, char,...)
		//primitive can be set by literal(no calculation express, just put const value)
		boolean result=true;
		char capitalC='C';
		int i=100000;
		
	//3. some useful methods of String
		//3-1. equals
		String a="hello";
		String b="java";
		String c="hello";
		System.out.println(a.euqals(b));//false
		System.out.println(a.equals(c));//true
		
		System.out.println(a==b);//false! we can't compare String by ==, because == check if that's same object
		
		//3-2. indexOf; get index of start location
		String indexOf_data="Hello Java";
		System.out.println(indexOf_data.indexOf("Java"));//6
		
		//3-3. replaceAll; replace word
		String replaceAll_data="Hello Java";
		System.out.prinln(replaceAll_data.replaceAll("Java", "World");//Hello Word
		
		//3-4. substring; extract special part of String
		String substring_data="hello Java";
		System.out.prinln(substring_data.substring(0,4));//hell! last location doesn't be extracted. start~end-1
		
		//3-5. toUpperCase; Upper all
		String toUpperCase_data="Hello Java";
		System.out.println(toUpperCase_data.toUpperCase());//HELLO JAVA
		
		//check these methods by examples!
		String string="Hello Java";
		System.out.println(string.indexOf("Java"));//get index
		System.out.println(string.replaceAll("Java", "World"));//replace word
		System.out.println(string.substring(0,4));//get part
		System.our.println(string.toUpperCase());//make upper
	}
}