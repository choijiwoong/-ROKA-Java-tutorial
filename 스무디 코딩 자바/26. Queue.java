import java.util.LinkedList;
import java.util.Queue;

public class Main{
	public static void main(String[] args){
		Queue<MyMemo> q1=new LinkedList<>();//****
		
		q1.offer(new MyMemo(1000, "First Memo"));//또야? 오히려 통일성있어서 좋네 
		q1.offer(new MyMemo(2000, "Second Memo"));
		q1.offer(new MyMemo(3000, "FIFO QUEUE"));
		
		System.out.println("[Queue elements]");
		System.out.println(q1);
		
		System.out.println("[peek]");
		q1.peek().showMemo();
		System.out.println("[poll]");
		q1.poll().showMemo();
		
		System.out.println("[Queue elements]");
		System.out.println(q1);
		
		System.out.println("size: "+q1.size());//2
	}
}

class MyMemo{
	private int memoId;
	private String memoText;
	
	public MyMemo(int memoId, String memoText){
		this.memoId=memoId;
		this.memoText=memoText;
	}
	public void showMemo(){//this.toString() convert hashvalue of instance to String.
		System.out.println("this memo: "+this.toString()+", memoId: "+memoId+", memoTxt: "+memoText);
	}
}

/*
1.	Queue는 FIFO인 Stack과 다르게 LIFO이고, *인터페이스이므로 LinkedList의 인스턴스를 참조 변수로 받아서 사용한다*
2.	그냥 println으로 모든 요소들의 해시키를 볼 수 있다.
*/