import java.util.ArrayList;

public class Main{
	public static void main(String[] args){
	//StringBuffer
		//1. append
		StringBuffer sb=new StringBuffer();
		sb.append("Hello");
		sb.append(" ");
		sb.append("jump to jave");
		System.out.println(sb.toString());//convert to String type total 1 StringBuffer object is made.
		
		//this example is like that by String
		String temp="";
		temp+="hello";
		temp+=" ";
		temp+="jump to java";
		System.out.println(temp);//total 4 String object are made.
		//first way creates StringBuffer object once.
		//second way creates String object at each + operation. because String is immutable. so methods of String always return new String object.
		
		//StringBuffer use many memory than String, and speed is low.
		//In many convert case, use StringBuffer. else, use String
		
		//2. insert
		sb.insert(0, "hello ");
		System.out.println(sb.toString());//hello jump to java
		
		//3. substring
		System.out.println(sb.substring(6,10));//jump
		
	//Array
		int[] odds={1,3,5,7,9};
		String[] weeks={"mon","tue","wed","thu","fri","sat","sun"};
		//same work
		String[] weeks2=new String[7];
		weeks[0]="mon";//...etc
		//String[] weeks=new String[];//error! length value is must needed.
		for(int i=0; i<weeks.length; i++)//get length by .length!
			System.out.println(weeks[i]);
		//Take care ArrayIndexOutOfBoundsException error!
		
	//List	
		ArrayList pitches=new ArrayList();//At J2SE 5.0, we have to use like ArrayList<String> pitches=new ArrayList<String>(); explicitly.
		pitches.add("138");//The reason is related with Generics
		pitches.add("129");//.add!
		pitches.add("142");
		
		pitches.add(1,"133");//with index!
		System.out.println(pitches.get(1));//.get!
		System.out.println(new String("Size: "+pitches.size()));//.size()!
		System.out.println(new String("Is there 145 in pitches?: "+pitches.contains("143")));//.containts(search)!
		System.out.println(pitches.remove("129"));
		System.out.println(pitches.remove(0));
			
		for(int i=0; i<pitches.size(); i++)
			System.out.println(new String("putches["+i+"]: "+pitches.get(i)));
	
	//total example code
		ArrayList<String> product_code=new ArrayList<String>();
		product_code.add("08");
		product_code.add("122");
		product_code.add("047");
		product_code.add("00");
		
		System.out.println(product_code.get(1));
		System.out.println(product_code.size());
		System.out.println(product_code.contains("00"));
		
		System.out.println(product_code.remove("047"));
		System.out.println(product_code.size());
		System.out.println(product_code.remove(0));
	
	}
}