import java.util.*;
import static java.util.Collections.*;

public class tutorial {
	static Queue q=new LinkedList();//p.611
	static final int MAX_SIZE=5;
	
	public static void main(String[] args) throws Exception{//throws p.512
		//[Chapter 11. Collection Framework]
		System.out.println("[p. 606]");//Structure of Stack.
		
		System.out.println("\n\n[p.610]");//Common use of stack. check () of expression is Valid.
		Stack st=new Stack();
		String expression="((2+3)*1+3";//check isValid
		
		System.out.println("expression: "+expression);
		
		try {
			for(int i=0; i<expression.length(); i++) {
				char ch=expression.charAt(i);
				if(ch=='(')
					st.push(ch+"");
				else if(ch==')')
					st.pop();	
			}
			
			if(st.isEmpty())
				System.out.println("Valid");
			else
				System.out.println("InValid");
		}catch(EmptyStackException e) {System.out.println("InValid");}
		
		
		System.out.println("\n\n[p.611]");//Simple example of queue with LinkedList's Iterator
		System.out.println("type help");
		while(expression.equals("")) {//while(false)가 왜 안되 씨발.. 이클립스 뭐같네..
			System.out.print(">>");
			try {
				Scanner s=new Scanner(System.in);
				String input=s.nextLine().trim();
				
				if("".equals(input))
					continue;
				
				if(input.equalsIgnoreCase("q")) {
					System.exit(0);
				} else if(input.equalsIgnoreCase("help")) {
					System.out.println(" help-show help content");
					System.out.println(" q or Q-Exit program");
					System.out.println(" history-show "+MAX_SIZE+"command you typed");
				} else if(input.equalsIgnoreCase("history")) {
					int i=0;
					save(input);
					
					LinkedList tmp=(LinkedList)q;
					ListIterator it=tmp.listIterator();
					
					while(it.hasNext())
						System.out.println(++i+"."+it.next());
				} else {
					save(input);
					System.out.println(input);
				}
			} catch(Exception e) {System.out.println("InValid Value");}
		}
		
		
		System.out.println("\n\n[p.626]");//Arrays is powerful tool for convenience of Array.
		int[] arr= {0,1,2,3,4,5};
		int[][] arr2D= {{11,12,13}, {21,22,23}};
		
		System.out.println("arr="+Arrays.toString(arr));
		System.out.println("arr2D="+Arrays.deepToString(arr2D));
		
		int[] arr2=Arrays.copyOf(arr, arr.length);
		int[] arr3=Arrays.copyOf(arr, 3);
		int[] arr4=Arrays.copyOf(arr, 7);
		int[] arr5=Arrays.copyOfRange(arr, 2, 4);
		int[] arr6=Arrays.copyOfRange(arr, 0, 7);
		
		System.out.println("arr2="+Arrays.toString(arr2));
		System.out.println("arr3="+Arrays.toString(arr3));
		System.out.println("arr4="+Arrays.toString(arr4));
		System.out.println("arr5="+Arrays.toString(arr5));
		System.out.println("arr6="+Arrays.toString(arr6));
		
		int[] arr7=new int[5];
		Arrays.fill(arr7, 9);
		System.out.println("arr7="+Arrays.toString(arr7));
		
		Arrays.setAll(arr7,  i->(int)(Math.random()*6)+1);
		System.out.println("arr7="+Arrays.toString(arr7));
		
		for(int i:arr7) {
			char[] graph=new char[i];
			Arrays.fill(graph, '*');
			System.out.println(new String(graph)+i);
		}
		
		String[][] str2D=new String[][] {{"aaa","bbb"}, {"AAA", "BBB"}};
		String[][] str2D2=new String[][] {{"aaa","bbb"}, {"AAA", "BBB"}};
		
		System.out.println(Arrays.equals(str2D, str2D2));//cmp reference
		System.out.println(Arrays.deepEquals(str2D, str2D2));//cmp value
		
		char[] chArr= {'A', 'D', 'C', 'B', 'E'};
		
		System.out.println("chArr="+Arrays.toString(chArr));
		System.out.println("index of B="+Arrays.binarySearch(chArr, 'B'));
		System.out.println("---After sorting---");
		Arrays.sort(chArr);
		System.out.println("chArr="+Arrays.toString(chArr));
		System.out.println("index of B="+Arrays.binarySearch(chArr, 'B'));
		
		
		System.out.println("\n\n[p.634]");//HashSet to user designed class; Person
		HashSet set=new HashSet();//will be gotton String, Person element
		
		set.add(new String("abc"));
		set.add(new String("abc"));
		set.add(new Person("David", 21));
		set.add(new Person("Jimo", 22));
		set.add(new Person("Jimo", 22));
		System.out.println(set);
		
		
		System.out.println("\n\n[p.637]");//Common use of HashSet
		HashSet setA=new HashSet();
		HashSet setB=new HashSet();
		HashSet setHab=new HashSet();//union
		HashSet setKyo=new HashSet();//intersection
		HashSet setCha=new HashSet();//difference of sets
		
		setA.add("1"); setA.add("2"); setA.add("3"); setA.add("4"); setA.add("5");
		System.out.println("A = "+setA);
		setB.add("4"); setB.add("5"); setB.add("6"); setB.add("7"); setB.add("8");
		System.out.println("B = "+setB);
		
		Iterator it=setB.iterator();//Get union of setA & setB
		while(it.hasNext()) {
			Object tmp=it.next();
			if(setA.contains(tmp))
				setKyo.add(tmp);
		}
		
		it=setA.iterator();//Get intersection of setA & setB
		while(it.hasNext()) {
			Object tmp=it.next();
			if(!setB.contains(tmp))
				setCha.add(tmp);
		}
		
		it=setA.iterator();//get 
		while(it.hasNext())
			setHab.add(it.next());
		it=setB.iterator();
		while(it.hasNext())
			setHab.add(it.next());
		
		System.out.println("Intersection of A&B: "+setKyo);
		System.out.println("Union of A&B: "+setHab);
		System.out.println("Difference of A&B: "+setCha);
		
		
		System.out.println("\n\n[p/656]");//TreeMap
		String[] data= {"A", "K", "A", "K", "D", "K", "A", "K", "K", "K", "Z", "D"};
		TreeMap map=new TreeMap();
		
		for(int i=0; i<data.length; i++) {//Set value to count
			if(map.containsKey(data[i])) {
				Integer value=(Integer)map.get(data[i]);
				map.put(data[i], new Integer(value.intValue()+1));
			}else { map.put(data[i], new Integer(1)); }
		}
		
		Iterator it1=map.entrySet().iterator();//Make Iterator on Entry of TreeMap
		System.out.println("---Default Sort---");
		while(it1.hasNext()) {
			Map.Entry entry=(Map.Entry)it1.next();//get Entry
			int value=((Integer)entry.getValue()).intValue();//get value to int
			System.out.println(entry.getKey()+": "+printBar('#', value)+" "+value);//print # as value(appear count)
		}
		System.out.println();
		
		System.out.println("---Sort by Value---");//Collections.sort with comparator for value after converting map to ArrayList
		Set set1=map.entrySet();
		List list=new ArrayList(set1);//ArrayList(Collection c)
		Collections.sort(list, new ValueComparator());//for compare by value of Entry
		
		it1=list.iterator();//list is already sorted by Collections.sort with ValueComparator
		while(it1.hasNext()) {
			Map.Entry entry=(Map.Entry)it1.next();
			int value=((Integer)entry.getValue()).intValue();
			System.out.println(entry.getKey()+": "+printBar('#', value)+" "+value);
		}
		
		
		System.out.println("\n\n[p.666]");//common useful tools of Collections like Arrays
		List list1=new ArrayList();
		System.out.println(list1);
		
		addAll(list1, 1,2,3,4,5);
		System.out.println(list1);
		
		rotate(list1, 2);
		System.out.println(list1);
		
		swap(list1, 0, 2);
		System.out.println(list1);
		
		shuffle(list1);
		System.out.println(list1);
		
		sort(list1, reverseOrder());
		System.out.println(list1);
		
		sort(list1);
		System.out.println(list1);
		
		int idx=binarySearch(list1, 3);
		System.out.println("index of 3 = "+idx);
		
		System.out.println("max="+max(list1));
		System.out.println("min="+min(list1));
		System.out.println("min="+max(list1, reverseOrder()));
		
		fill(list1, 9);
		System.out.println("list1="+list1);
		
		List newList=nCopies(list1.size(), 2);//return imutable List. 
		//newList.set(0, 2); //UnsupportedOperationException!
		System.out.println("newList="+newList);
		
		System.out.println(disjoint(list1, newList));//if there is no same element, return true
		
		copy(list1, newList);
		System.out.println("newList="+newList);
		System.out.println("list1="+list1);
		
		replaceAll(list1, 2, 1);
		System.out.println("list1="+list1);
		
		Enumeration e=enumeration(list1);
		ArrayList list2=list(e);
		
		System.out.println("list2="+list2);
	}//main END
	
	public static void save(String input) {//p/611
		if(!"".equals(input))
			q.offer(input);
		
		if(q.size()>MAX_SIZE)
			q.remove();
	}
	
	
	static class ValueComparator implements Comparator{//p.656
		public int compare(Object o1, Object o2) {
			if(o1 instanceof Map.Entry && o2 instanceof Map.Entry) {
				Map.Entry e1=(Map.Entry)o1;
				Map.Entry e2=(Map.Entry)o2;
				
				int v1=((Integer)e1.getValue()).intValue();
				int v2=((Integer)e2.getValue()).intValue();
				
				return v2-v1;
			}
			return -1;
		}
	}
	public static String printBar(char ch, int value) {
		char[] bar=new char[value];
		
		for(int i=0; i<bar.length; i++)
			bar[i]=ch;
		return new String(bar);
	}
	
}//tutorial END

class MyStack extends Vector{//p.611
	public Object push(Object item) {
		addElement(item);
		return item;
	}
	public Object pop() {
		Object obj=peek();
		removeElementAt(size()-1);
		return obj;
	}
	public Object peek() {
		int len=size();
		
		if(len==0)
			throw new EmptyStackException();
		
		return elementAt(len-1);
	}
	public boolean empty() {
		return size()==0;
	}
	public int search(Object o) {
		int i=lastIndexOf(0);
		
		if(i>=0)
			return size()-i;
		return -1;
	}
}


class Person{//p.634
	String name;
	int age;
	
	Person(String name, int age){
		this.name=name;
		this.age=age;
	}
	
	public boolean equals(Object obj) {//important to contain on Hash
		if(obj instanceof Person) {
			Person tmp=(Person)obj;
			return name.equals(tmp.name) && age==tmp.age;
		}
		return false;
	}
	
	public int hashCode() {//important to contain on Hash
		return (name+age).hashCode();
	}
	public String toString() { return name+": "+age; }
}