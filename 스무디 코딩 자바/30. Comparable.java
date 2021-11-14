/*[1. Compareable interface]
import java.util.ArrayList;
import java.util.Collections;

public class Main{
	public static void main(String[] args){
		ArrayList<MyMemo> mem1=new ArrayList<>();
		mem1.add(new MyMemo(101, "hello world!"));
		mem1.add(new MyMemo(201, "Comparing string length"));
		mem1.add(new MyMemo(301, "short text"));
		mem1.add(new MyMemo(401, "long ang long text"));
		mem1.add(new MyMemo(501, "just text"));
		mem1.add(new MyMemo(601, "meaning what?"));
		mem1.add(new MyMemo(701, "a word"));
		
		System.out.println("----no sort----");
		for(MyMemo m: mem1)
			m.showMemo();
		
		System.out.println("----sort----");
		Collections.sort(mem1);//use compareTo() method.
		for(MyMemo m: mem1)
			m.showMemo();
	}
}

class MyMemo implements Compareable<MyMemo>{
	private int memoId;
	private String memoText;
	
	public MyMemo(int memoId, String memoText){
		this.memoId=memoId;
		this.memoText=memoText;
	}
	
	public void showMemo(){
		System.out.println("instance: "+this.toString()+", Id: "+memoId+", text: "+memoText);
	}
	
	@Override
	public int compareTo(MyMemo o){
		return this.memoText.length()-o.memoText.length();
	}
}*/

//2.
import java.util.*;

public class Main{
	public static void main(String[] ags){
		Set<MyMemo> arrayList=new TreeSet<>(new MyMemo());//TreeSet to Set because MyMemo is Comparator, @Override compare
		
		arrayList.add(new MyMemo(1001, "Comparator"));
		arrayList.add(new MyMemo(1002, "text"));
		arrayList.add(new MyMemo(1003, "two words"));
		arrayList.add(new MyMemo(1004, "sorting"));
		arrayList.add(new MyMemo(1005, "MCdonard"));
		arrayList.add(new MyMemo(1006, "comparing"));
		
		for(MyMemo m1: arrayList)
			m1.showMemo();
	}
}

class MyMemo implements Comparator<MyMemo>{
	private int memoId;
	private String memoText;
	
	public MyMemo(){ this.memoId=0; this.memoText=null; }
	public MyMemo(int memoId, String memoText){ this.memoId=memoId; this.memoText=memoText; }
	public void showMemo(){ System.out.println("Id: "+memoId+", Text: "+memoText); }
	@Override
	public int compare(MyMemo o1, MyMemo o2){ return o1.memoText.length()-o2.memoText.length(); }
}

/*
1.	Compareable인터페이스는 콜렉션 프레임워크에서 비교와 정렬에 사용된다. 자료구조에 따라 사용법의 차이가 조금있으니 확인후 사용해야 한다.
	Compareable을 implement하게 되면 Collections로 취급되어 Collection.sort등의 Collections함수를 사용할 수 있다.
	물론 그 전에 필요한 함수들은 오버로딩해두어야 사용자 정의 객체에서 사용이 가능하다.
2.	뭔 소린지 잘 모르겟넴헤헿..ㅎ 대충 오버로딩과 comparator로 set에 담을 수 있다...
*/