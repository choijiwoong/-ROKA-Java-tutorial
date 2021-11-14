import java.util.ArrayList;

public class Main{
	public static void main(String[] args){
		ArrayList<MyMemo> arrLst=new ArrayList<MyMemo>();
		
		MyMemo memo1=new MyMemo(1, "this is first memo");
		MyMemo memo2=new MyMemo(2, "people see me");
		MyMemo memo3=new MyMemo(3, "hyun ta is comming soon");
		MyMemo memo4=new MyMemo(4, "is it funny?");
		MyMemo memo5=new MyMemo(5, "wait 6months");
		
		arrLst.add(memo1);
		arrLst.add(memo2);
		arrLst.add(memo3);
		arrLst.add(memo4);
		arrLst.add(memo5);
		arrLst.add(3, new MyMemo(0, "door push stone"));
		
		int removeId=1;
		System.out.println("if to remove index: "+arrLst.get(removeId).getMemoId());//remove by MyMemo.memoId
		
		for(int i=0; i<arrLst.size(); i++){
			int tempId=arrLst.get(i).getMemoId();
			if(tempId==removeId)
				arrLst.remove(i);
		}
		
		for(MyMemo memo: arrLst)
			System.out.println(memo.getMemoId()+" - "+memo.getMemoText());
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
}