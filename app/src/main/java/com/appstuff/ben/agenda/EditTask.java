package com.appstuff.ben.agenda;

import android.hardware.input.InputManager;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;

public class EditTask extends AppCompatActivity {

    private Task task;

    private ConstraintLayout cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        cl = findViewById(R.id.cl);

        Log.d("airey","EditTask activity loaded.");

        // Import, or create, the Task for this activity to edit
        if (getIntent().getSerializableExtra("com.appstuff.ben.agenda.TaskToEdit") != null) {
            task = (Task) getIntent().getSerializableExtra("com.appstuff.ben.agenda.TaskToEdit");
        }
        else {
            task = new Task("New task", Arrays.asList(LocalDate.now()));
        }

        // Set Actions for buttons and crap
        final TextView dateText = findViewById(R.id.dateText);

        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowCalendarPopup(dateText);
            }
        });
    }

    private void ShowCalendarPopup(final TextView dateText) {
        // First, we have to create a new inflater to create the views
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(
                LAYOUT_INFLATER_SERVICE);
        View content = inflater.inflate(R.layout.view_popup_calendar, null);

        // Instantiate the popup window object
        final PopupWindow popupWindow = new PopupWindow(
                content, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        // Hide keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm.isAcceptingText()) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        if(Build.VERSION.SDK_INT>=21){
            popupWindow.setElevation(30.0f);
        }

        // -- Setup the views ----------------------------------------------------------------------

        final DatePicker datePicker = (DatePicker) content.findViewById(R.id.datePicker);

        // Cancel button needs to be able to close the popup WITHOUT editing the text
        TextView cancel = (TextView) content.findViewById(R.id.dateTextCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close the popup window
                popupWindow.dismiss();
            }
        });

        // OK button should edit the text, then close the popup window.
        TextView ok = (TextView) content.findViewById(R.id.dateTextOk);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass date value to out, then close window
                dateText.setText(DateToText(LocalDate.of(datePicker.getYear(),
                        datePicker.getMonth()+1,
                        datePicker.getDayOfMonth())));
                popupWindow.dismiss();
            }
        });

        // Set location of window
        popupWindow.showAtLocation(this.cl, Gravity.CENTER, 0, 0);
    }

    private String DateToText(LocalDate date) {
        /* Converts a date into more friendly human text, including conversions into "Today",
        "Tomorrow", and "Yesterday".
         */

        if (date.equals(LocalDate.now())) { return "Today"; }
        else if (date.equals(LocalDate.now().minusDays(1))) { return "Yesterday"; }
        else if (date.equals(LocalDate.now().plusDays(1))) { return "Tomorrow"; }
        else {
            String weekday = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault());
            String month = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.getDefault());
            String day = String.valueOf(date.getDayOfMonth());
            int year = date.getYear();
            return String.format("%s, %s %s, %d", weekday, month, day, year);
        }
    }
}
