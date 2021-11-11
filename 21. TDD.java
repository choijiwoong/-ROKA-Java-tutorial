package java_project_1;

//TDD(Test Driven Development)_for Clean code that works!_Ron Jeffries
//Write test code first, and make code that can pass that test code.(feat. refactoring; improve design of source code without function of source code)
//Think target of test->write test code that will be failed->write code that can pass test code->refactoring->repeat!
//Kent Beck said "we can make any shit things for passing test code fast. it will be changed common code by TDD cycle"
//Recommand of book by Kent Beck: Test Driven Development By Example

//Calculate difference of two date by TDD!
import junit.framework.TestCase;//for TDD

public class SubDateTest extends TestCase{//TestCode is inheritanced TestCase. we will use assertEquals(a, b) & assertTrue(a) & assertFalse(a) for writing test code.
	public static void main(String[] args){
		junit.textui.TestRunner.run(SubDateTest.class);
	}
	
	public void testGetYearDay(){//if class's name contains 'test', it will be executed by junit
		assertEquals(0, SubDate.getYearDay(1));//(test code.1)year0 is not exist. so getYearDay(1) have to ZERO.
		assertEquals(365, SubDate.getYearDay(2));//(test code.2)days of one year will be 365 days.
		//SubDate class is not existing yet!
		
		//assertEquals(365+365+365+366, SubDate.getYearDay(5));//(test code.3)for solving this case, logic that search leap-year-day is needed.
		//but we can't make directly logic that check leap-year-day. so we have to approach more smaller.
		assertTrue(SubDate.isLeapYear(0));//(test code.4)
		assertFalse(SubDate.isLeapYear(1));//
		assertTrue(SubDate.isLeapYear(4));//
		
		//Concept of leap-year-day is
		//year%4==0 is True first. If one of that years can be devided by 100 is common year. But year%400==0 is leap-year-day.
		//So 1200 is divided by 400 first, so it's True. 700 is divided by 100, so it's False.
		//We have to apply priority of 400, 100, 4
		assertTrue(SubDate.isLeapYear(0));//(test code.5)
		assertFalse(SubDate.isLeapYear(1));
		assertTrue(SubDate.isLeapYear(4));
		assertTrue(SubDate.isLeapYear(1200));//it will be failed in proxedd 5(refactoring_2) go for refactoring_3!
		assertFalse(SubDate.isLeapYear(700));//it will be failed in process 4(refactoring).
		//so now, we have to improve real code.
		
		//we have to check that test code is believable. if not, we habe to write more test code for assurance
		//now, check test code.3
		assertEquals(365+365+365+366, SubDate.getYearDay(5));//(test code.6(3))
		
		//Let's get days until last month.
		//assertEquals(0, SubDate.getMonthDay(1));//(test code.7)
		//assertEquals(31, SubDate.getMonthDay(2));//before creating (boolean isLeap)
		
		assertEquals(0, SubDate.getMonthDay(1, true));//(test code.8)
		assertEquals(31, SubDate.getMonthDay(2, false));
		
		assertEquals(31+28, SubDate.getMonthDay(3, false));//(test code.9)
		assertEquals(31+29, SubDate.getMonthDay(3, true));
		
		assertEquals(1, SubDate.getTotalDay("00010101"));//(test code.10)
		assertEquals(366, SubDate.getTotalDay("00020101"));
		
		//(final test code.11)
		assertEquals(1, SubDate.sub("20061231", "20070101"));
		assertEquals(31+28+30+31+14, SubDate.sub("20070101", "20070515");
		assertEquals(31+29+30+31+14, SubDate.sub("20080101", "20080515"));
	}
	
	
	public class SubDate{//1. MAKE Skeleton of method that is used in test case!
		//public static int getYearDay(int year){
		//	if(year==1)//2. PASS test2 most fast!
		//		return 0;
		//	return 365;
		//}
		
		public static boolean isLeapYear(int year){//3. PASS test3 most fast!
			//if(year==0) return true;
			//if(year==1) return false;
			//if(year==4) return true;
			//return false;
			
			//if(year%4==0) return true;//4. REFACTORING!
			//return false;
			
			if(year%100==0) return false;//5. REFACTORING_2!
			if(year%4==0) return true;
			return false;
			
			if(year%400==0) return true;//6. REFACTORING_3!
			if(year%100==0) return false;
			if(year%4==0) return true;
			return false;
		}
		public static int getYearDay(int year){//7. PASS test6 most fast by using isLeapYear!
			int result=0;
			for(int i=1; i<year; i++){
				if(isLeapYear(i))
					result+=366;
				else
					result+=365;
			}	
			return result;
			//TDD's power is appeared now, code (that was in a hurry for passing testcode) is changed to common code.
		}
		static final int[] monthDays={31,28,31,30,31,30,31,31,30,31,30,31};//11..PASS test9
		public static int getMonthDay(int month, boolean isLeap){//8. PASS test7 fast & 10. PASS test8 too.
			//if(month==1) return 0;
			//else return 31;
			//9. NOW we have curiosity of process for handling of leap-month(February)
			//February is 28 in common year, 29 in leap-year. February is decided by whether year is leap. so check as parameter. Let's write next test code!
			
			//11.
			int result=0;
			for(int i=1; i<month; i++)
				result+=monthDays[i-1];
			
			if(isLeap && month>2)
				result+=1;//for total days until 3~
			return result;
			
			//12. We have to get total days of special date
		}
		public static int getTotalDay(String date){//13. PASS test10
				int year=Integer.parseInt(date.substring(0,4));
			int month=Integer.parseInt(date.substring(4,6));
			int day=Integer.parseInt(date.substring(6,8));
		
			return getYearDay(year)+getMonthDay(month, isLeapYear(year))+day;
		}
		public static int sub(String date1, String date2){
			return Math.abs(getTotalDay(date1)-getTotalDay(date2));//14. PASS final test 11!
		}
	}
	
}