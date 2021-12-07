import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class tutorial{
	public static void main(String[] args) {
		/*
		System.out.println("[p.806]");//Common example of functional interfaces(Supplier, Consumer, Predicate, Function)
		Supplier<Integer> s=()->(int)(Math.random()*100)+1;//Supplier that returns random number
		Consumer<Integer> c=i->System.out.print(i+", ");//Consumer that print argument
		Predicate<Integer> p=i->i%2==0;//Predicate isEven
		Function<Integer, Integer> f=i->i/10*10;//Function that gets Integer, returns Integer
		
		List<Integer> list=new ArrayList<>();
		makeRandomList(s, list);//add 10 random element
		System.out.println(list);
		printEvenNum(p, c, list);
		List<Integer> newList=doSomeThing(f, list);
		System.out.println(newList);*/
		
		
		System.out.println("\n\n[p.811]");//common example2 of functional interfaces with lambda
		Function<String, Integer> f=(s)->Integer.parseInt(s,16);//hex to int
		Function<Integer, String> g=(i)->Integer.toBinaryString(i);//toBinaryString
		
		Function<String, String> h=f.andThen(g);//hex to int, toBinaryString
		Function<Integer, Integer> h2=f.compose(g);//toString, hex to int
		
		System.out.println(h.apply("FF"));//11111111
		System.out.println(h2.apply(2));//16. binary string "10", make to int by parsing hex. 16
		
		Function<String, String> f2=x->x;//identity function
		System.out.println(f2.apply("AAA"));
		
		Predicate<Integer> p=i->i<100;
		Predicate<Integer> q=i->i<200;
		Predicate<Integer> r=i->i%2==0;
		Predicate<Integer> notP=p.negate();
		
		Predicate<Integer> all=notP.and(q.or(r));//make new predicate by all predicate
		System.out.println(all.test(150));//true_check
		
		String str1="abc";
		String str2="abc";
		
		Predicate<String> p2=Predicate.isEqual(str1);
		boolean result=p2.test(str2);
		System.out.println(result);
		
		
		System.out.println("\n\n[p.826]");//stream sort of class that implements Comaparable<>
		Stream<Student> studentStream=Stream.of(
					new Student("LeeJ", 3, 300),
					new Student("LeeU", 1, 200),
					new Student("LeeP", 2, 100),
					new Student("LeeE", 2, 150),
					new Student("LeeW", 1, 200),
					new Student("LeeR", 3, 290),
					new Student("LeeN", 3, 180)
				);
		studentStream.sorted(Comparator.comparing(Student::getBan).thenComparing(Comparator.naturalOrder())).forEach(System.out::println);
		//sort by using getBan, naturalOrder(totalScore). and print all elements by forEach
		
		
		System.out.println("\n\n[p.834]");//normal use of Stream example(distinct, flatMap...)
		Stream<String[]> strArrStrm=Stream.of(//Make Stream(String[])
				new String[] {"abc", "def", "jkl"},
				new String[] {"ABC", "GHI", "JKL"}
		);
		//Stream<Stream<String>> strStrmStrm=strArrStrm.map(Arrays::stream);
		Stream<String> strStrm=strArrStrm.flatMap(Arrays::stream);//Flatize element to Stream<String>
		//normal use of stream as map
		strStrm.map(String::toLowerCase).distinct().sorted().forEach(System.out::println);//Lower, erase space, sort and print
		System.out.println();
		
		String[] lineArr= { "Believe or not It is true", "Do or do not There is no try", };
		Stream<String> lineStream=Arrays.stream(lineArr);//make stream<String> by String[]
		lineStream.flatMap(line->Stream.of(line.split(" +"))).map(String::toLowerCase).distinct().sorted().forEach(System.out::println);
		System.out.println();//after split, ["Believe", "or",...], ["Do", "or",...]. so flatMap to merge each String.
		
		Stream<String> strStrm1=Stream.of("AAA", "ABC", "bBb", "Dd");
		Stream<String> strStrm2=Stream.of("bbb", "aaa", "ccc", "dd");
		
		Stream<Stream<String>> strStrmStrm=Stream.of(strStrm1, strStrm2);//merge two stream(focus on strStrmStrm's type. make two stream in new stream)
		Stream<String> strStream=strStrmStrm.map(s->s.toArray(String[]::new)).flatMap(Arrays::stream);//Each Stream<String> to String[] and flatize. contain all String in on Stream<String>
		strStream.map(String::toLowerCase).distinct().forEach(System.out::println);//Lower, erase space, print
		
		
		System.out.println("\n\n[p.839]");//Common example of Optional play!
		Optional<String> optStr=Optional.of("abcde");
		Optional<Integer> optInt=optStr.map(String::length);
		System.out.println("optStr="+optStr.get());//string
		System.out.println("optInt="+optInt.get());//int (length)
		
		int result1=Optional.of("123").filter(x->x.length()>0).map(Integer::parseInt).get();//isEmpty, convert, get
		int result2=Optional.of("").filter(x->x.length()>0).map(Integer::parseInt).orElse(-1);//isEmpty, convert or -1
		System.out.println("result1="+result1);
		System.out.println("result2="+result2);
		
		Optional.of("456").map(Integer::parseInt).ifPresent(x->System.out.printf("result3=%d%n",x));//if 456 is exist, print
		
		OptionalInt optInt1=OptionalInt.of(0);//not empty
		OptionalInt optInt2=OptionalInt.empty();//empty
		System.out.println(optInt1.isPresent());//true
		System.out.println(optInt2.isPresent());//false
		
		System.out.println(optInt1.getAsInt());//0
		//System.out.println(optInt2.getAdInt());//NoSuchElementException. no element
		System.out.println("optInt1="+optInt1);//OptionalInt[0]
		System.out.println("optInt2="+optInt2);//OptionalInt.empty
		System.out.println("optInt1.equals(optInt2)? "+optInt1.equals(optInt2));//false. 0 & empty is different of coursely.
		
		Optional<String> opt=Optional.ofNullable(null);//nullable Optional
		Optional<String> opt2=Optional.empty();
		System.out.println("opt="+opt);//Optional.empty
		System.out.println("opt2="+opt2);//Optional.empty
		System.out.println("opt.equals(opt2)?"+opt.equals(opt2));//true
		
		int result3=optStrToInt(Optional.of("123"), 0);//try parseInt or return default value by 2nd argument 
		int result4=optStrToInt(Optional.of(""), 0);
		
		System.out.println("result3="+result3);//123
		System.out.println("result4="+result4);//0
		
		
		System.out.println("\n\n[p.845]");//reduce of stream
		String[] strArr= {
				"Inheritance", "Java", "Lambda", "stream",
				"OptionalDouble", "IntStream", "count", "sum"
		};
		
	}//main
	
	static <T> List<T> doSomeThing(Function<T, T> f, List<T> list){//p.806
		List<T> newList=new ArrayList<T>(list.size());//Generic method! doSomeThing<T>(~)_<T> before List<T> and <T> in argument is different!
		
		for(T i: list)
			newList.add(f.apply(i));//apply f to all elements of list.
		return newList;
	}
	static <T> void printEvenNum(Predicate<T> p, Consumer<T> c, List<T> list) {
		System.out.print("[");
		for(T i: list) {
			if(p.test(i))//check i is matched on p(test predicate expression)
				c.accept(i);//pass i to c(consumer's accept)
		}
		System.out.println("]");
	}
	static <T> void makeRandomList(Supplier<T> s, List<T> list) {
		for(int i=0; i<10; i++)
			list.add(s.get());
	}
	
	
	static int optStrToInt(Optional<String> optStr, int defaultValue) {//p. 839
		try {
			return optStr.map(Integer::parseInt).get();
		}catch(Exception e) {
			return defaultValue;
		}
	}
}//tutorial

class Student implements Comparable<Student>{//p.826 implement Comparable
	String name;
	int ban, totalScore;
	
	Student(String name, int ban, int totalScore){
		this.name=name;
		this.ban=ban;
		this.totalScore=totalScore;
	}
	
	public String toString() {
		return String.format("[%s, %d, %d]", name, ban, totalScore);//use String.format
	}
	String getName() { return name; }
	int getBan() { return ban; }
	int getTotalScore() { return totalScore; }
	
	public int compareTo(Student s) {//by totalScore
		return s.totalScore-this.totalScore;
	}
}