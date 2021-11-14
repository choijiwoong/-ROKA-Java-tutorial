import java.util.TreeSet;

public class Main{
	public static void main(String[] args){
		//use Collection Framwork
		TreeSet<Integer> intTree=new TreeSet<>();
		intTree.add(3);
		intTree.add(2);
		intTree.add(5);
		intTree.add(4);
		intTree.add(7);
		intTree.add(9);
		intTree.add(8);
		intTree.add(1);
		
		intTree.add(1);
		intTree.add(8);
		
		for(Integer it: intTree)
			System.out.print(it+", ");
		
		//interwhomade
		BinaryTree tree=new BinaryTree();
		tree.addNode(5);
		tree.addNode(8);
		tree.addNode(7);
		tree.addNode(10);
		tree.addNode(9);
		tree.addNode(11);
		
		if(tree.removeNode(10))
			System.out.println("Delete Node");
		
		tree.preorderTree(tree.top, 0);
	}
}

class BinaryTree{
	Node top=null;
	
	public void addNode(int data){
		if(top==null){//start
			top=new Node(data);
		} else {
			Node head=top;
			Node currentNode;
			
			while(true){
				currentNode=head;
				if(head.data>data){
					head=head.left;
					if(head==null){
						currentNode.left=new Node(data);
						break;
					}
				} else{
					head=head.right;
					if(head==null){
						currentNode.right=new Node(data);
						break;
					}
				}
			}
		}
	}
	
	public boolean removeNode(int data){//**
		Node removeNode=top;
		Node parentOfRemoveNode=null;
		
		while(removeNode.data!=data){//choose direction to go
			parentOfRemoveNode=removeNode;
			
			if(removeNode.data>data)//if smaller than removeNode, go to left
				removeNode=removeNode.left;
			else
				removeNode=removeNode.right;
			
			if(removeNode==null)//if no element
				return false;//remove fail
		}
		
		if(removeNode.left==null && removeNode.right==null){//if node(will be removed)'s left&right is null
			if(removeNode==top)//if it's top
				top=null;
			else if(removeNode==parentOfRemoveNode.right)//remove right or left that points removeNode
				parentOfRemoveNode.right=null;
			else
				parentOfRemoveNode.left=null;
		} else if(removeNode.right==null){//if only left child node is exist
			if(removeNode==top)//if it's rootNoce
				top=top=removeNode.left;
			else if(removeNode==parentOfRemoveNode.right)//if remove node is right of parent
				parentOfRemoveNode.right=removeNode.left;
			else
				parentOfRemoveNode.left=removeNode.left;
		} else if(removeNode.left==null){//if only right child node is exist
			if(removeNode==top)
				top=removeNode.right;
			else if(removeNode==parentOfRemoveNode.right)//if removeNode is parent's right
				parentOfRemoveNode.right=removeNode.right;
			else
				parentOfRemoveNode.left=removeNode.right;
		} else{//if both exist??????????????????????????????????????????잘 이해안댐
			Node parentOfReplaceNode=removeNode;
			Node replaceNode=parentOfReplaceNode.right;
			
			while(replaceNode.left!=null){//search left for find smallest value  대충 왼쪽과 오른쪽중에서 어딜 올릴까 고민하려고
				parentOfReplaceNode=replaceNode;
				replaceNode=replaceNode.left;//우선 오른쪽했다가
			}
			if(replaceNode!=removeNode.right){//왼쪽이 좋아보이면 왼쪽으로 바꿔버린다는 거같음
				parentOfReplaceNode.left=replaceNode.right;
				replaceNode.right=removeNode.right;
			}
			
			if(removeNode==top)//root
				top=replaceNode;
			else if(removeNode==parentOfRemoveNode.right)//오른쪽 지울거에요
				parentOfRemoveNode.right=replaceNode;
			else//왼쪽 지울거에요
				parentOfRemoveNode.left=replaceNode;
			replaceNode.left=removeNode.left;//끊긴 흐름 한쪽 이어줄게요
		}
		
		return true;
	}
	
	public void inorderTree(Node root, int depth){
		if(root!=null){
			inorderTree(root.left, depth+1);
			for(int i=0; i<depth; i++)
				System.out.print("ㄴ");
		}
		System.out.println(root.data);
		inorderTree(root.right, depth+1);
	}
	
	public void postorderTree(Node root, int depth){
		if(root!=null){
			postorderTree(root.left, depth+1);
			postorderTree(root.right, depth+1);
			for(int i=0; i<depth; i++)
				System.out.print("ㄴ");
			System.out.println(root.data);
		}
	}
	
	public void preorderTree(Node root, int depth){
		if(root!=null){
			for(int i=0; i<depth; i++)
				System.out.print("ㄴ");
			System.out.println(root.data);
			preorderTree(root.left, depth+1);
			preorderTree(root.right, depth+1);
		}
	}
}

class Node{
	int data;
	Node left, right;
	
	Node(int data){ 
		this.data=data; 
		this.left=null;
		this.right=null;
	}
}


/*
1.	이진 검색 트리를 구현한 자료형으로 Set인터페이스의 특징으로 중복이 제거된다. binaray tree에 따른 자동 정렬과 unique element가 특징이다. 검색속도가 빠르다.
	규칙에 따라 숫자를 넣다 보면 오름차순으로 정렬이 된다. 알고리즘의 실제 구현은 좀 어려우므로 직접 구현해보아야 한다.
	
*/