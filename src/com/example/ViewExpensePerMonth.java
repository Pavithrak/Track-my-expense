package com.example;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class ViewExpensePerMonth extends ListActivity {
    private ExpenseController controller;
    public ViewExpensePerMonth() {
        this.controller = new ExpenseController(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_expense);

        Integer month = getIntent().getExtras().getInt("month");
        Cursor allExpenseForMonth = controller.getAllExpenseForMonth(month);
        startManagingCursor(allExpenseForMonth);
        String[] from = {"date","amount","reason"};
        int[] to = {R.id.date,R.id.value,R.id.reason};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.expense_row_per_month,allExpenseForMonth,from,to);
        setListAdapter(adapter);

    }
}
