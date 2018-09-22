package com.appstuff.ben.agenda;

import android.graphics.Color;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Task implements Serializable {
    private String name;
    private List<LocalDate> dates = new ArrayList<LocalDate>();
    private String notes;
    private List<Boolean> completed = new ArrayList<Boolean>();
    private int color;

    public Task(String name) {
        this(name, Arrays.asList(LocalDate.now()));
    }

    public Task(String name, List<LocalDate> dates) {
        this(name, dates, Color.BLACK);
    }

    public Task(String name, List<LocalDate> dates, int color) {
        this(name, dates, color, "");
    }

    public Task(String name, List<LocalDate> dates, int color, String notes) {
        this.name = name;
        this.dates = dates;
        this.color = color;
        this.notes = notes;
        this.completed = new ArrayList<Boolean>();
        for (int i = 0; i < this.dates.size(); i++) {
            this.completed.add(false);
        }
    }

    public void SetCompleted(LocalDate taskDate, Boolean isCompleted) {
        // Finds the Task instance corresponding to the given date and assigns 'completed' to its
        // value.  If there is no instance corresponding to the given date, the it does nothing.

        for (int i = 0; i < dates.size(); i++) {
            if (dates.get(i).equals(taskDate)) {
                completed.set(i, isCompleted);
                return;
            }
        }
    }

    public boolean GetCompleted(LocalDate taskDate) {
        // Finds the Task instance corresponding to the given date and returns its value.  Returns
        // false if no such task exists.

        for (int i = 0; i < dates.size(); i++) {
            if (dates.get(i).equals(taskDate)) {
                return completed.get(i);
            }
        }
        return false; // If we didn't find it :(
    }

    public ArrayList<Boolean> GetCompletedList() {
        return new ArrayList<Boolean>(completed);
    }

    public void SetName(String newName) { name = newName; }
    public String GetName() { return name; }

    public void SetColor(int newColor) { color = newColor; }
    public int GetColor() { return color; }

    public void SetNotes(String newNotes) { notes = newNotes; }
    public String GetNotes() { return notes; }

    public void SetDates(ArrayList<LocalDate> newDates, ArrayList<Boolean> newCompleted) {
        if (newDates.size() == newCompleted.size()) {
            dates = newDates;
            completed = newCompleted;
        }
    }

    public ArrayList<LocalDate> GetDates() {
        return new ArrayList<LocalDate>(dates);
    }
}
