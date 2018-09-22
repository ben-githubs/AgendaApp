package com.appstuff.ben.agenda;

import java.util.ArrayList;

public class TaskManager {
    public final static TaskManager INSTANCE = new TaskManager();

    private ArrayList<Task> tasks = new ArrayList<Task>();

    private TaskManager() {
        // Lazy Instantiation
    }

    public void AddTask(Task newTask) {
        tasks.add(newTask);
    }

    public void RemoveTask(Task target) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).equals(target)) {
                tasks.remove(i);
                return;
            }
        }
    }

    public ArrayList<Task> GetTasks() {
        return new ArrayList<Task>(tasks); // A copy list, but original task references
    }
}
