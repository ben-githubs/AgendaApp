package com.appstuff.ben.agenda;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Button;

import java.time.LocalDate;

public class TaskView extends LinearLayout {
    private Button button;
    private CheckBox checkBox;
    private Task task;
    private LocalDate date;

    public TaskView(Task task, LocalDate date, Context context) {
        this(task, date, context,null);
    }

    public TaskView(Task task, LocalDate date, Context context, AttributeSet attr) {
        this(task, date, context, attr, 0);
    }

    public TaskView(Task task, LocalDate date, Context context, AttributeSet attr, int defStyleAttr) {
        super(context, attr, defStyleAttr);
        init(task, date);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TaskView(Task task, LocalDate date, Context context, AttributeSet attr, int defStyleAttr, int defStyleRes) {
        super(context, attr, defStyleAttr, defStyleRes);
        init(task, date);
    }

    // Initialize View
    public void init(Task par_task, LocalDate par_date) {
        // First, inflate (populate) the base linear layout with view
        inflate(getContext(),R.layout.view_taskview,this);

        // Get the stuff
        checkBox = (CheckBox)findViewById(R.id.checkBox);
        button = (Button)findViewById(R.id.button);

        //Assign Task, and task parameters
        task = par_task;
        date = par_date;
        checkBox.setChecked(task.GetCompleted(date));
        button.setTextColor(task.GetColor());
        button.setText(task.GetName());
    }

    //public void SetTask(Task newTask) { task = newTask; }
    public Task GetTask() { return task; }
}
