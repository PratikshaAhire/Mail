package URL;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class MyMainScheduler {

	public static void main(String[] args) throws SchedulerException {
		
		
		
		JobDetail j=JobBuilder.newJob(DemoScheduler.class).build();
		
		Trigger t=org.quartz.TriggerBuilder
        .newTrigger()
        .withIdentity("EMAIL")
        .withSchedule(
             SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(1)
           .repeatForever()).build();
					
		
//		
//		Trigger t1=org.quartz.TriggerBuilder
//		        .newTrigger()
//		        .withIdentity("EMAIL")
//		        .withSchedule(
//		             SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(4).withIntervalInMinutes(30)
//		           .repeatForever()).build();
				Scheduler s=StdSchedulerFactory.getDefaultScheduler();
				
				
				s.start();
				s.scheduleJob(j, t);
				
//				s.scheduleJob(j, t1);
				
				
		
		
		
	}

}

