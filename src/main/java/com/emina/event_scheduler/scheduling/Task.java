package com.emina.event_scheduler.scheduling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    private Long priority; //smaller number = higher priority
    private String name;
    private List<Task> dependencies; //These tasks must complete before this one
    private boolean isCompleted;

    public Task(String name, Long priority){
        this.name = name;
        this.priority = priority;
        this.dependencies = new LinkedList<Task>();
        this.isCompleted = false;
    }

    public void addDependency(Task task){
        dependencies.add(task);
    }

    public boolean canExecute(){
        return !isCompleted && dependencies.stream().allMatch(Task::isCompleted);
    }

    @Override
    public String toString(){
        return name + " (Priority: " + priority + ")";
    }
}
