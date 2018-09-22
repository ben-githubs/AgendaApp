package com.appstuff.ben.agenda;

import android.util.AttributeSet;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.content.Context;

import java.time.LocalDate;

public class PopupCalendar extends LinearLayout {
    private DatePicker datePicker;

    public PopupCalendar(LocalDate date, Context context) {
        this(date, context,null);
    }

    public PopupCalendar(LocalDate date, Context context, AttributeSet attr) {
        this(date, context, attr, 0);
    }

    public PopupCalendar(LocalDate date, Context context, AttributeSet attr, int defStyleAttr) {
        super(context, attr, defStyleAttr);
        init(date);
    }

    private void init(LocalDate date) {
        // Inflate and shit

    }
}
