package com.appstuff.ben.agenda;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*/ Import Tasks
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task("Fuck bitches."));
        tasks.add(new Task("Make money."));
        tasks.add(new Task("Suck my own dick, like a BOSS!"));
        tasks.get(2).SetColor(Color.RED);
        Log.d("airey", String.format("The task list has %d entries.", tasks.size()));*/

        TaskManager taskManager = TaskManager.INSTANCE;
        taskManager.AddTask(new Task("Fuck bitches"));
        taskManager.AddTask(new Task("Make money"));

        // Add TaskViews
        LinearLayout myLayout = (LinearLayout)findViewById(R.id.main);
        ArrayList<Task> tasks = taskManager.GetTasks();

        for (int i = 0; i < tasks.size(); i++) {
            TaskView taskView = new TaskView(tasks.get(i), LocalDate.now(), this);
            myLayout.addView(taskView);
            Log.d("airey", "Adding new TaskView.");
        }

        // Set listener for the FAB (floating action button)
        findViewById(R.id.buttonAddTask).setOnClickListener(this);
    }

    public void OpenTaskEditor() {
        OpenTaskEditor(null);
    }

    public void OpenTaskEditor(Task taskToEdit) {
        Log.d("airey", "Starting new Activity...");
        Intent intent = new Intent(MainActivity.this, EditTask.class);
        intent.putExtra("com.appstuff.ben.agenda.TaskToEdit", taskToEdit);
        startActivity(intent);
    }

    public void onClick(View view) {
        // logic for when the floating action button is clicked
        Log.d("airey", "Floating Action Button Clicked.");
        OpenTaskEditor();
    }
}
