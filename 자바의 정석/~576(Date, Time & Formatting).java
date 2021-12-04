import java.util.*;//Calendar
import java.text.*;//DecimalFormat
import java.io.*;//Scanner
import java.time.*;//LocalDate, LocalTime, ZoneId, ZonedDateTime, OffsetDateTime
import java.time.temporal.*;
import static java.time.DayOfWeek.*;
import static java.time.temporal.TemporalAdjusters.*;

public class tutorial {
	public static void main(String[] args) throws Exception{//throws p.512
		//[Chapter 10. Date, Time and Formatting]
		System.out.println("[p. 534]");//(Calendar)Print Calendar by using Calendar
		int year=Integer.parseInt("2015");
		int month=Integer.parseInt("12");
		int START_DAY_OF_WEEK=0;
		int END_DAY=0;
		
		Calendar sDay=Calendar.getInstance();
		Calendar eDay=Calendar.getInstance();
		
		sDay.set(year,  month-1, 1);//0~11
		eDay.set(year, month, 1);
		eDay.add(Calendar.DATE, -1);//End day is gotton by -1 of next month.
		
		START_DAY_OF_WEEK=sDay.get(Calendar.DAY_OF_WEEK);
		END_DAY=eDay.get(Calendar.DATE);
		
		System.out.println("        2015/12");
		System.out.println(" SU MO TU WE TH FR SA");
		
		for(int i=1; i<START_DAY_OF_WEEK; i++)
			System.out.print("   ");
		
		for(int i=1, n=START_DAY_OF_WEEK; i<=END_DAY; i++, n++){
			System.out.print((i<10)? "  "+i: " "+i);//one char(1~9) & two char(10~31)
			if(n%7==0){
				System.out.println();
			}
		}
		
		
		System.out.println("\n\n[p.543]");//(DecimalFormat)User designed parse Format by java.text.*
		DecimalFormat df1=new DecimalFormat("#,###.##");
		DecimalFormat df2=new DecimalFormat("#.###E0");
		
		try {
			Number num=df1.parse("1,234,567.89");//string to Number that has format "#,###.##"
			System.out.print("1,234,567.89"+" -> ");
			
			double d=num.doubleValue();
			System.out.print(d+" -> ");
			
			System.out.println(df2.format(num));//Number to string that has format "#.###E0"
		} catch(Exception e) {}
		
		
		System.out.println("\n\n[p.546]");//(SimpleDateFormat)Common use of SimpleDateFormat by using DateFormat
		DateFormat df3=new SimpleDateFormat("yyyy년 mm월 dd일");
		DateFormat df4=new SimpleDateFormat("yyyy/MM/dd");
		
		try {
			System.out.print("2020년 12월 04일 -> ");
			Date d=df3.parse("2020년 12월 04일");//save result of parse to Date
			System.out.print(d+" ->");
			System.out.println(df4.format(d));//Date to string of df4 format
		}catch(Exception e) {}
		
		
		System.out.println("\n\n[p.548]");//(ChoiceFormat)Common use of ChoiceFormat
		double[] limits= {60, 70, 80, 90};//Special Range(Must sorted! increase)
		String[] grades= {"D", "C", "B", "A"};//kind of result
		
		int[] scores= {100, 95, 88, 70, 52, 60, 70};
		ChoiceFormat form=new ChoiceFormat(limits, grades);//special sorted range, result values
		
		for(int i=0; i<scores.length; i++)
			System.out.println(scores[i]+":"+form.format(scores[i]));//access by .format(element)
		
		
		System.out.println("\n\n[p.550]");//(MessageFormat)extract partial data that is made by special format
		String[] data= {//example of DB command..
				"INSERT INTO CUST_INFO VALUES('LeeJung', '010-2222-8888', 21, '20-22');",
				"INSERT INTO CUST_INFO VALUES('PKSY', '011-131', 22, '18-06');"
		};
		String pattern="INSERT INTO CUST_INFO VALUES({0}, {1}, {2}, {3});";//Set patten for parsing data(DB command). It's just Pattern!
		MessageFormat mf=new MessageFormat(pattern);//Make MessageFormat by using that pattern to use it on parsing.
		
		for(int i=0; i<data.length; i++) {
			Object[] objs=mf.parse(data[i]);//String data to Object[]
			for(int j=0; j<objs.length; j++)
				System.out.println(objs[j]+",");
			System.out.println();
		}
		
		//Advanced version of upper code(feat. file)
		String tableName="CUST_INFO";
		String fileName="data4.txt";
		String msg="INSERT INTO"+tableName+" VALUES({0}, {1}, {2}, {3});";
		
		Scanner s=new Scanner(new File(fileName));
		
		String pattern2="{0}, {1}, {2}, {3}";//Make Pattern
		MessageFormat mf2=new MessageFormat(pattern2);//Make MessageFormat for parsing
		
		while(s.hasNextLine()) {
			String lint=s.nextLine();
			Object[] objs2=mf2.parse(lint);//parsing & save to Object[]
			System.out.println(MessageFormat.format(msg, objs2));//Object[] to String by using MessageFormat
		}
		s.close();
		
		
		System.out.println("\n\n[p.564]");//(LocalDate)Good Example of time
		LocalDate date=LocalDate.of(2020, 12, 04);
		LocalTime time=LocalTime.of(16, 06, 02);
		
		LocalDateTime dt=LocalDateTime.of(date, time);//make LocalDateTime by using LocalTime, LocalDate
		
		ZoneId zid=ZoneId.of("Asia/Seoul");//Set zone
		ZonedDateTime zdt=dt.atZone(zid);//Make ZonedDateTime by using LocalDateTime, ZoneId
		
		ZonedDateTime seoulTime=ZonedDateTime.now();//Get ZonedDateTime of seoul by .now
		ZoneId nyId=ZoneId.of("America/New_York");
		ZonedDateTime nyTime=ZonedDateTime.now().withZoneSameInstant(nyId);//Get ZonedDateTime of NewYort by withZoneSameInstant
		
		OffsetDateTime odt=zdt.toOffsetDateTime();//get duration between seoul, UTC.
		
		System.out.println(dt);
		System.out.println(zid);
		System.out.println(zdt);
		System.out.println(seoulTime);
		System.out.println(nyTime);
		System.out.println(odt);
		
		
		System.out.println("\n\n[p.566]");//(TemporalAdjuster)
		LocalDate today=LocalDate.now();
		LocalDate date2=today.with(new DayAfterTomorrow());//we can use TemporalAdjuster(for convenience) by with
		p(today);
		p(date2);
		p(today.with(firstDayOfNextMonth()));
		p(today.with(firstDayOfMonth()));
		p(today.with(lastDayOfMonth()));
		p(today.with(firstInMonth(TUESDAY)));
		p(today.with(lastInMonth(TUESDAY)));
		p(today.with(previous(TUESDAY)));
		p(today.with(previousOrSame(TUESDAY)));
		p(today.with(next(TUESDAY)));
		p(today.with(nextOrSame(TUESDAY)));
		p(today.with(dayOfWeekInMonth(4, TUESDAY)));
		
	}//main END
	
	static class DayAfterTomorrow implements TemporalAdjuster{//p.566 Use designed TemporalAdjuster by overriding adjustInto
		@Override
		public Temporal adjustInto(Temporal temporal) {
			return temporal.plus(2, ChronoUnit.DAYS);//+=2 day
		}
	}
	static void p(Object obj) { System.out.println(obj); }
	
}//tutorial END
