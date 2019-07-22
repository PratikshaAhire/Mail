package MAIL;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.mail.internet.ParseException;

public class ClassExecutingTask {
	
		long delayfornextstart = 60*60*24*7*1000; // delay in ms : 10 * 1000 ms = 10 sec.
		TimerTask tasktoexecute = new LoopTask();
		Timer timer = new Timer("TaskName");
		public void start() throws ParseException, InterruptedException, java.text.ParseException {
		timer.cancel();
		timer = new Timer("TaskName");
		//@SuppressWarnings("deprecation")
		//Date executionDate = new Date(2013-05-04); // no params = now
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("waiting for the rght day to come");
		Date executionDate = sdf.parse("2019-07-17");

		Date date1 = sdf.parse("2019-07-17");

		Date date2 = sdf.parse("2019-07-17");
		System.out.println(date1+"and"+date2);
		long waitTill=getTimeDiff(date2,date1);
		if(date2==date1)
		{
		    waitTill=0;
		     System.out.println(waitTill);
		}

		   System.out.println(waitTill);
		  Thread.sleep(waitTill);
		timer.scheduleAtFixedRate(tasktoexecute, executionDate, delayfornextstart);
		}

		private class LoopTask extends TimerTask {
		public void run() {
			MailProjectClass EE=new MailProjectClass();
		    EE.test();//ur Mail code or the method which send the MAil
		}
		}

		public static void main(String[] args) throws ParseException, InterruptedException, java.text.ParseException {
		ClassExecutingTask executingTask = new ClassExecutingTask();
		executingTask.start();
		}
		public static long getTimeDiff(Date dateOne, Date dateTwo) {
		    String diff = "";
		    long timeDiff = Math.abs(dateOne.getTime() - dateTwo.getTime());
		    diff = String.format("%d hour(s) %d min(s)",     TimeUnit.MILLISECONDS.toHours(timeDiff),
		            TimeUnit.MILLISECONDS.toMinutes(timeDiff) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeDiff)));
		    return timeDiff;
		}


		}



