import java.util.ArrayList;
import java.util.Iterator;

public class Main{
	public static void main(String[] args){
		ArrayList<MyMemo> memList=new ArrayList<>();
		
		memList.add(new MyMemo(101, "Text and Memo"));
		memList.add(new MyMemo(102, "Iteration"));
		memList.add(new MyMemo(103, "And Collection"));
		
		Iterator<MyMemo> irt=memList.iterator();//Iterator instane get return value of ArrayList instance's iterator() method
		System.out.println(irt.hasNext());//boolean
		
		while(irt.hasNext()){
			MyMemo m1=irt.next();
			m1.showMemo();
			//irt.next().showMemo() 가능
		}
	}
}

class MyMemo{
	private int memoId;
	private String memoText;
	
	public MyMemo(int memoId, String memoText){ this.memoId=memoId; this.memoText=memoText; }
	public void showMemo(){
		System.out.println("this memo: "+this.toString()+", memoId: "+memoId+", memoText: "+memoText);
	}
}

/*
1.	Iteration은 같은 프로시저를 여러번 반복하는 것을 의미한다. 인덱스가 없는 Set같은 컬렉션에 사용한다. 물론 인덱스 있어도 이터레이터 사용이 가능하다.
2.	ArrayList인스턴스의 iterator메소드로 Itertor<generic>으로 받아 사용한다.
*/