package com.emina.event_scheduler;

import com.emina.event_scheduler.scheduling.Task;
import com.emina.event_scheduler.scheduling.TaskDependencyDag;
import com.emina.event_scheduler.scheduling.TaskScheduler;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TaskRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        TaskScheduler scheduler = new TaskScheduler();
        TaskDependencyDag dependencyDag = new TaskDependencyDag(null);

        Task task1 = new Task("Release", 1L);
        Task task2 = new Task("Integration testing", 2L);
        Task task3 = new Task("Code review", 3L);
        Task task4 = new Task("Feature development", 4L);

        task1.addDependency(task2);
        task2.addDependency(task3);
        task3.addDependency(task4);

        dependencyDag.printDependencies(task1);

        System.out.println("Adding tasks to the scheduler...\n");

        scheduler.addTask(task1);
        scheduler.addTask(task2);
        scheduler.addTask(task3);
        scheduler.addTask(task4);

        scheduler.printCurrentSchedule();

        Task nextTask = scheduler.getNextExecutableTask();

        while (nextTask != null) {
            System.out.println("Executing: " + nextTask);
            scheduler.completeTask(nextTask);
            nextTask = scheduler.getNextExecutableTask();
        }
    }
}
