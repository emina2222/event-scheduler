package com.emina.event_scheduler.scheduling;

import org.springframework.util.CollectionUtils;

import java.util.*;

public class TaskScheduler {

    private PriorityQueue<Task> minHeap;
    private Map<Task, List<Task>> dependencyMap = new HashMap<>();

    public TaskScheduler(){
        this.minHeap = new PriorityQueue<>((a, b) -> Math.toIntExact(a.getPriority() - b.getPriority()));
    }

    public void addTask(Task task){
        minHeap.offer(task);

        // Add dependent tasks to the map
        // KEY = task to be executed prior, VALUE = task to be executed after
        task.getDependencies().forEach(d -> dependencyMap.computeIfAbsent(d, k -> new ArrayList<>()).add(task));
    }

    public Task getNextExecutableTask(){
        while(!minHeap.isEmpty()){
            Task task = minHeap.peek();
            if(task.canExecute()){
                return task;
            }
            minHeap.poll();
        }

        return null;
    }

    public void completeTask(Task task){
        task.setCompleted(true);

        // Add ready tasks to the heap when the task is completed
        List<Task> dependentTasks = dependencyMap.get(task);
        if(!CollectionUtils.isEmpty(dependentTasks)){
            dependentTasks.forEach(dependentTask -> minHeap.offer(dependentTask));
        }
    }

    public void printCurrentSchedule(){
        System.out.println("Task schedule:");
        minHeap.forEach(t -> System.out.println(t.toString()));
        System.out.println();
    }
}
