import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class Main{
	public static void main(String[] args){
		//[1. Simple example of HashSet]
		HashSet<String> hs1=new HashSet<>();
		
		hs1.add("jimo");
		hs1.add("john");
		hs1.add("kelly");
		hs1.add("koma");
		hs1.add("jimo");//no overlap
		hs1.add("john");
		System.out.println(hs1);
		
		hs1.remove("koma");
		System.out.println(hs1);
		
		System.out.println(hs1.contains("asjld"));//check is in
		
		hs1.clear();//delete all
		System.out.println(hs1);
		
		//[2.
		HashSet<MyMemo> mySet=new HashSetM<>();
		mySet.add(new MyMemo(2001, "soccor"));
		mySet.add(new MyMemo(3002, "at weekend"));
		mySet.add(new MyMemo(2005, "with newbies"));
		mySet.add(new MyMemo(2010, "little fun?"));
		
		mySet.add(new MyMemo(2010, "little fun?"));
		mySet.add(new MyMemo(2005, "with newbies"));
		
		Iterator<MyMemo> itr=mySet.iterator();
		while(itr.hasNext())
			itr.next().showMemo();
	}
}
class MyMemo{
	private int memoId;
	private String memoText;
	
	public MyMemo(int memoId, String memoText){ this.memoId=memoId; this.memoText=memoText; }
	public void showMemo(){
		System.out.println("instance: "+this.toString()+", Id: "+memoId+", Text: "+memoText);
	}
	
	//HashSet uses .equals() & hashCode for prevention of overlap.
	@Override
	public boolean equals(Object o){
		if(this==o)//this==argument
			return true;
		if(o==null || getClass()!=o.getClass())//null or different class
			return false;
		MyMemo myMemo=(MyMemo)o;
		return memoId==myMemo.memoId && Objects.equals(memoText, myMemo.memoText);//object's method equals is used!
		//if we want to prevent overlap only on memoId, we can delete Object.equals~ part.
	}
	
	@Override
	public int hashCode(){ return Objects.hash(memoId, memoText); }//pass id&text value to hash argument
}

/*
1.	Hash는 랜덤한 길이의 데이터를 고정된 데이터 길이로 매핑하는 함수로 10Byte의 문자열을 32비트 해시 값으로 매칭시키는 것 같은 것이 가능하다.
	랜덤한 길이의 데이터를 입력이라고 하고, 고정된 길이의 데이터를 출력이라고 한다. 입력에 따라 출력이 나오지만 출력으로 입력을 알 수 없기에 암호화에 주로 사용된다. 대표적인 것이 Cryptocurrency의 Hash(SHA256)이다.
	 우리는 순차적이지 않은 유일한 요소를 저장할 필요가 있을 경우 HashSet를 사용한다.
2.	C++의 ordered set처럼 현대 언어에서는 Sequence Unique Map을 사용하지만, 우선 본연의 set에 익숙해지도록 하자.
3.	HashSet에 저장할 형식이 사용자 정의 객체일 경우, 중복 방지를 위해 사용하는 equals와 hashCode메소드를 오버라이드해야한다.
*/