import java.util.Stack;

public class Main{
	public static void main(String[] args){
		Stack<MyMemo> stackOfMemo=new Stack<>();//it's possible
		
		stackOfMemo.push(new MyMemo(1, "jogging in the morning"));
		stackOfMemo.push(new MyMemo(2, "lunch with my client"));
		stackOfMemo.push(new MyMemo(3, "writer must like memo..."));
		stackOfMemo.push(new MyMemo(4, "no jam.."));
		for(MyMemo me1: stackOfMemo)//bottom to top
			me1.showMemo();
		
		System.out.println("-------------------------");
		MyMemo justMemo=stackOfMemo.pop();
		justMemo.showMemo();//top(4)
		
		//peek is ㅗsee
		System.out.println("peek top");
		justMemo=stackOfMemo.peek();
		justMemo.showMemo();
		System.out.println(stackOfMemo);
	}
}

class MyMemo{
	private int memoId;
	private String memoText;
	
	public int getMemoId(){ return memoId; }
	public void setMemoId(int memoId){ this.memoId=memoId; }
	public String getMemoText(){ return memoText; }
	public void setMemoText(String memoText){ this.memoText=memoText; }
	public MyMemo(int memoId, String memoText){ this.memoId=memoId; this.memoText=memoText; }
	public void showMemo(){
		System.out.print("this memo: "+this.toString()+", ");
		System.out.print("memoId: "+memoId+", ");
		System.out.print("memoText: "+memoText);
		System.out.println();
	}
}