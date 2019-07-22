package URL;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class test {

	public static void main(String[] args) {

		LocalDateTime now= LocalDateTime.now();
		System.out.println(now);
		
		ZoneId currentzonwe=ZoneId.systemDefault();
		ZonedDateTime znow=ZonedDateTime.of(now, currentzonwe);
		System.out.println(znow);
		
		LocalTime time= LocalTime.now();
		System.out.println(time);
		String time1="15:55:00.000";
		
		if(time.equals(time1))
		{
			System.out.println("equal");
		}
		else
		{
			System.out.println("Now : " +LocalTime.now());
		}
		
	
}
}
