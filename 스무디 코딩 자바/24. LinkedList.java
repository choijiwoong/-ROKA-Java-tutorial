import java.util.LinkedList;

public class Main{
	public static void main(String[] args){
		//handmade version
		sLinkedList myList=new sLinkedList();
		System.out.println("myList.head: "+myList.head);
		
		myList.insertNode(111);
		myList.insertNode(333);
		myList.insertNode(555);
		myList.insertNode(777);
		myList.insertNode(999);
		
		myList.showLinkedList();
		
		//Javamade version
		LinkedList<MyMemo> mList=new LinkedList<MyMemo>();
		
		mList.add(new MyMemo(2001, "hahaha"));
		mList.add(new MyMemo(2002, "my birth last year!"));
		mList.add(new MyMemo(2003, "sinlim..."));
		mList.add(new MyMemo(2004, "TT"));
		for(MyMemo memo: mList)
			System.out.println("id = "+memo.getMemoId()+", text: "+memo.getMemoText());
		
		System.out.println("----------------");
		mList.add(2, new MyMemo(7777, "Interrupt"));
		mList.addFirst(new MyMemo(9999, "Zero"));
		mList.removeLast();
		for(MyMemo memo:mList)
			System.out.println("id = "+memo.getMemoId()+", text: "+memo.getMemoText());
	}
}

//for handmade
class sLinkedList{
	Node head;
	
	sLinkedList(){ head=null; }
	
	public void insertNode(Node preNode, int data){
		Node newNode=new Node(data);
		newNode.next=preNode.next;
		preNode.next=newNode;
	}
	public void insertNode(int data){//last
		Node newNode=new Node(data);
		
		if(this.head==null){
			this.head=newNode;
		} else{
			Node temp=head;
			while(temp.next!=null)
				temp=temp.next;
			temp.next=newNode;
		}
	}
	
	public void showLinkedList(){
		Node temp=this.head;
		while(temp!=null){
			System.out.print(temp.getData()+": ");
			System.out.println(temp.next);
			temp=temp.next;
		}
		System.out.println();
	}
}

class Node{
	private int data;
	Node next;
	
	Node(){ this.data=0; this.next=null; }
	Node(int data){ this.data=data; this.next=null; }
	Node(int data, Node next){ this.data=data; this.next=next; }
	
	public int getData(){ return this.data; }
}

//for java made
class MyMemo{
	private int memoId;
	private String memoText;
	
	public int getMemoId(){ return memoId; }
	public void setMemoId(int memoId){ this.memoId=memoId; }
	public String getMemoText(){ return memoText; }
	public void setMemoText(String memoText){ this.memoText=memoText; }
	public MyMemo(int memoId, String memoText){ this.memoId=memoId; this.memoText=memoText; }
}