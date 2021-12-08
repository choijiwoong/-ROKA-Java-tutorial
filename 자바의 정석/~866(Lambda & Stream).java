import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

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
		Stream.of(strArr).forEach(System.out::println);
		
		boolean noEmptyStr=Stream.of(strArr).noneMatch(s->s.length()==0);//check empty is exist in strArr
		Optional<String> sWord=Stream.of(strArr).filter(s->s.charAt(0)=='s').findFirst();//s~ first find return
		System.out.println("noEmptyStr="+noEmptyStr);
		System.out.println("sWord="+sWord.get());
		
		//Convert Stream<String[]> to IntStream
		IntStream intStream1=Stream.of(strArr).mapToInt(String::length);//make IntStream by length of element
		IntStream intStream2=Stream.of(strArr).mapToInt(String::length);
		IntStream intStream3=Stream.of(strArr).mapToInt(String::length);
		IntStream intStream4=Stream.of(strArr).mapToInt(String::length);
		
		int count=intStream1.reduce(0,  (a,b)->a+1);//execution of lambda with reducing element.
		int sum=intStream2.reduce(0,  (a,b)->a+b);//****difference of lambda on count, sum
		OptionalInt max=intStream3.reduce(Integer::max);
		OptionalInt min=intStream4.reduce(Integer::min);
		
		System.out.println("count="+count);
		System.out.println("sum="+sum);
		System.out.println("max="+max.getAsInt());
		System.out.println("min="+min.getAsInt());
		
		
		System.out.println("\n\n[p.847]");//Collectors examples. Collectors is like Arrays. it has many useful tools
		Student[] stuArr= {
				new Student("LeeJ", 3, 300),
				new Student("LeeU", 1, 200),
				new Student("LeeP", 2, 100),
				new Student("LeeE", 2, 150),
				new Student("LeeW", 1, 200),
				new Student("LeeR", 3, 290),
				new Student("LeeN", 3, 180)
		};
		
		List<String> names=Stream.of(stuArr).map(Student::getName).collect(Collectors.toList());//extract name only by mapping & make List by Collectors.toList() method
		System.out.println(names);
		
		Student[] stuArr2=Stream.of(stuArr).toArray(Student[]::new);//toArray with constructor of Student[]
		for(Student s:stuArr2)
			System.out.println(s);
		
		Map<String, Student> stuMap=Stream.of(stuArr).collect(Collectors.toMap(s->s.getName(), stu->stu));//make Map by toMap by lambda expression two.
		for(String name: stuMap.keySet())
			System.out.println(name+"-"+stuMap.get(name));
		
		long count2=Stream.of(stuArr).collect(counting());
		long totalScore=Stream.of(stuArr).collect(summingInt(Student::getTotalScore));//summingInt
		System.out.println("count="+count);
		System.out.println("totalScore="+totalScore);
		
		totalScore=Stream.of(stuArr).collect(reducing(0, Student::getTotalScore, Integer::sum));//three argument mean map+reduce. map to sum, and reduce as totalscore
		System.out.println("totalScore="+totalScore);
		
		Optional<Student> topStudent=Stream.of(stuArr).collect(maxBy(Comparator.comparingInt(Student::getTotalScore)));//find Max with comparetor that compares by getTotalScore
		System.out.println("topStudent="+topStudent.get());
		
		IntSummaryStatistics stat=Stream.of(stuArr).collect(summarizingInt(Student::getTotalScore));//get summarizingInt by getTotalScore()
		System.out.println(stat);//Show SummaryStatistics based on getTotalScore.
		//{count=7, sum=1420, min=100, average=202.857143, max=300}
		
		String stuNames=Stream.of(stuArr).map(Student::getName).collect(joining(", ", "{", "}"));
		System.out.println(stuNames);//join()_merge element and make String
		
		
		System.out.println("\n\n[p.852]");//example of collect(partitioningBy())
		Student2[] stu2Arr= {
				new Student2("LeeJ", true, 1, 1, 300),
				new Student2("LeeU", false, 1, 1, 250),
				new Student2("LeeP", true, 1, 1, 200),
				new Student2("LeeE", false, 1, 2, 150),
				new Student2("LeeW", true, 1, 2, 100),
				new Student2("LeeR", false, 1, 2, 50),
				new Student2("LeeQ", false, 1, 3, 100),
				new Student2("LeeY", false, 1, 3, 150),
				new Student2("LeeN", true, 1, 3, 200),
				
				new Student2("LeeJ", true, 2, 1, 300),
				new Student2("LeeU", false, 2, 1, 250),
				new Student2("LeeP", true, 2, 1, 200),
				new Student2("LeeE", false, 2, 2, 150),
				new Student2("LeeW", true, 2, 2, 100),
				new Student2("LeeR", false, 2, 2, 50),
				new Student2("LeeQ", false, 2, 3, 100),
				new Student2("LeeY", false, 2, 3, 150),
				new Student2("LeeN", true, 2, 3, 200),
		};
		System.out.println("1. common seperation(isMale)");
		Map<Boolean, List<Student2>> stu2BySex=Stream.of(stu2Arr).collect(partitioningBy(Student2::getisMale));
		//Make key by getisMale, and Make value to List<Student2> for convenience of find
		List<Student2> maleStudent2=stu2BySex.get(true);//get value that's key is true
		List<Student2> femaleStudent2=stu2BySex.get(false);
		
		for(Student2 s: maleStudent2)
			System.out.println(s);
		for(Student2 s: femaleStudent2)
			System.out.println(s);
		
		System.out.println("\n2. common seperation+static(sex num)");
		Map<Boolean, Long> stu2NumBySex=Stream.of(stu2Arr).collect(partitioningBy(Student2::getisMale, counting()));
		//we can assign value by 2nd argument of partitioningBy()
		System.out.println("num of male: "+stu2NumBySex.get(true));
		System.out.println("num of female: "+stu2NumBySex.get(false));
		
		Map<Boolean, Optional<Student2>> topScoreBySex=Stream.of(stu2Arr).collect(partitioningBy(Student2::getisMale, maxBy(comparingInt(Student2::getScore))));
		System.out.println("First in male: "+topScoreBySex.get(true));
		System.out.println("First in female: "+topScoreBySex.get(false));
		
		System.out.println("\n3. multi division(isMale fail, <=100)");
		Map<Boolean, Map<Boolean, List<Student2>>> failedStu2BySex=Stream.of(stu2Arr).collect(partitioningBy(Student2::getisMale, partitioningBy(s->s.getScore()<=100)));
		List<Student2> failedMaleStu=failedStu2BySex.get(true).get(true);//male, fail(<=100)
		List<Student2> failedFemaleStu=failedStu2BySex.get(false).get(true);//female, fail(<=100)
		
		for(Student2 s:failedMaleStu)
			System.out.println(s);
		for(Student2 s: failedFemaleStu)
			System.out.println(s);
		
		
		System.out.println("\n\n[p.857]");//example of collect(groupingBy())_Student2 recycling..
		//Student2[] stu2Arr id defined..
		System.out.println("1. common grouping(ban)");
		Map<Integer, List<Student2>> stu2ByBan=Stream.of(stu2Arr).collect(groupingBy(Student2::getBan));
		for(List<Student2> ban: stu2ByBan.values())//<ban, List<Students>(students in ban)
			for(Student2 s:ban)//for each ban's student, 
				System.out.println(s);//print all students
		
		System.out.println("\n2. common grouping(score)");
		Map<Student2.Level, List<Student2>> stu2ByLevel=Stream.of(stu2Arr).collect(groupingBy(s->{
			if(s.getScore()>=200)
				return Student2.Level.HIGH;
			else if(s.getScore()>=100)
				return Student2.Level.MID;
			else
				return Student2.Level.LOW;
		}));
		TreeSet<Student2.Level> keySet=new TreeSet<>(stu2ByLevel.keySet());//keySet of stu2ByLevel
		
		for(Student2.Level key: keySet) {
			System.out.println("["+key+"]");//Level
			for(Student2 s:stu2ByLevel.get(key))
				System.out.println(s);
			System.out.println();
		}
		
		System.out.println("\n3. common grouping+statistic(num per score)");
		Map<Student2.Level, Long> stu2CntByLevel=Stream.of(stu2Arr).collect(groupingBy(s->{
			if(s.getScore()>=200)
				return Student2.Level.HIGH;
			else if(s.getScore()>=100)
				return Student2.Level.MID;
			else
				return Student2.Level.LOW;
		}, counting()));//By score(Student2.Level), make counting(Long)
		
		for(Student2.Level key: stu2CntByLevel.keySet())
			System.out.printf("[%s]-%d명, ", key, stu2CntByLevel.get(key));
		System.out.println();
		
		System.out.println("\n4. multi grouping(hak, ban)");
		Map<Integer, Map<Integer, List<Student2>>> stu2ByHakAndBan=Stream.of(stu2Arr).collect(groupingBy(Student2::getHak, groupingBy(Student2::getBan)));
		
		for(Map<Integer, List<Student2>> hak:stu2ByHakAndBan.values()) {
			for(List<Student2> ban: hak.values()) {
				System.out.println();
				for(Student2 s:ban)
					System.out.println(s);//print sorted? grouped by Hak, Ban
			}
		}
		
		System.out.println("\n5. multi grouping+statistic(hak, first)");
		Map<Integer, Map<Integer, Student2>> topStu2ByHakAndBan=Stream.of(stu2Arr).collect(
			groupingBy(Student2::getHak, groupingBy(
					Student2::getBan, collectingAndThen(//after collecting, get max
							maxBy(comparingInt(Student2::getScore)), Optional::get//return result of maxBy to Optional::get
						)
				))
		);//hak, ban, first by getScore with default value(Not Map<Integer, Map<Integer, List<Student>>>!! it's just first student one.)
		
		for(Map<Integer, Student2> ban:topStu2ByHakAndBan.values())
				for(Student2 s:ban.values())
					System.out.println(s);//print only all element. not use keySet. just print all
		
		System.out.println("\n6. multi grouping+statistic(hak, level on ban)");
		Map<String, Set<Student2.Level>> stu2ByScoreGroup=Stream.of(stu2Arr).collect(groupingBy(s->s.getHak()+"-"+s.getBan(),
				mapping( s->{
					if(s.getScore()>=200)
						return Student2.Level.HIGH;
					else if(s.getScore()>=100)
						return Student2.Level.MID;
					else
						return Student2.Level.LOW;
				}, toSet() )//toSet of Level.
		));//Levelize(set) per Hak-Ban
		
		Set<String> keySet2=stu2ByScoreGroup.keySet();
		for(String key: keySet2)
			System.out.println("["+key+"]"+stu2ByScoreGroup.get(key));
		
		
		System.out.println("\n\n[p.863]");//user designed collector
		String[] strArr3= {"aaa", "bbb", "ccc"};
		Stream<String> strStream2=Stream.of(strArr3);
		
		String result5=strStream2.collect(new ConcatCollector());//StringBuilder as argument.
		
		System.out.println(Arrays.toString(strArr3));
		System.out.println("result5="+result5);
		
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

class Student implements Comparable<Student>{//p.826 implement Comparable, p.847 recycle..
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


class Student2{//p.852(partitioningBy), p.857(groupingBy)
	String name;
	boolean isMale;
	int hak;
	int ban;
	int score;
	
	//constructor
	Student2(String name, boolean isMale, int hak, int ban, int score){
		this.name=name;
		this.isMale=isMale;
		this.hak=hak;
		this.ban=ban;
		this.score=score;
	}
	
	//getters
	String getName() {return name;}
	boolean getisMale() {return isMale;}
	int getHak() {return hak;}
	int getBan() {return ban;}
	int getScore() {return score;}
	
	public String toString() {
		return String.format("[%s, %s, %d학년 %d반, %3d점]", name, isMale?"남":"여", hak, ban, score);
	}
	enum Level{HIGH, MID, LOW};//Level.
}


class ConcatCollector implements Collector<String, StringBuilder, String>{//p.863
	@Override
	public Supplier<StringBuilder> supplier(){//return new of <>
		return ()->new StringBuilder();
		//return StringBuilder::new;
	}
	
	@Override
	public BiConsumer<StringBuilder, String> accumulator(){//append
		return (sb, s)->sb.append(s);
	}
	
	@Override
	public Function<StringBuilder, String> finisher(){//toString
		return sb->sb.toString();
	}
	
	@Override
	public BinaryOperator<StringBuilder> combiner(){//concat
		return (sb, sb2)->sb.append(sb2);
	}
	
	@Override
	public Set<Characteristics> characteristics(){//Characteristics is property of work like Characteristics.CONCURRENT, UNORDERED, IDENTITY_FINISH
		return Collections.emptySet();
	}
}