package com.emina.event_scheduler.scheduling;

import lombok.AllArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.Collections;

/**
 * DAG data structure - Direct acyclic graph is used, to support multiple dependencies without cycles.
 */
@AllArgsConstructor
public class TaskDependencyDag {

    private Task root;

    public void addTaskWithDependency(Task task, Task dependsOn){
        dependsOn.getDependencies().add(task);
    }

    public void printDependencies(Task task){
        System.out.println("Task " + task.getName() + " depends on: \n");

        task.getDependencies().forEach(t -> System.out.println("- " + t.getName() + "\n"));
        task.getDependencies().forEach(this::printDependencies);
    }

}
