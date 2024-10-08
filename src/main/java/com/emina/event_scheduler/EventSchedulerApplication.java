package com.emina.event_scheduler;

import com.emina.event_scheduler.scheduling.Task;
import com.emina.event_scheduler.scheduling.TaskDependencyDag;
import com.emina.event_scheduler.scheduling.TaskScheduler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventSchedulerApplication.class, args);
	}


}
