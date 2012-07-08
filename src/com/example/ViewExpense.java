package com.example;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.example.controller.ExpenseController;
import com.example.view.R;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class ViewExpense extends ListActivity implements SimpleCursorAdapter.ViewBinder, AdapterView.OnItemClickListener {
    private ExpenseController controller;
    private BiMap<Integer,String> months;
    public ViewExpense() {
        this.controller = new ExpenseController(this);
        initializeMonths();
    }

    private void initializeMonths() {
        months = HashBiMap.create();
        months.put(1,"JANUARY");
        months.put(2,"FEBRUARY");
        months.put(3,"MARCH");
        months.put(4,"APRIL");
        months.put(5,"MAY");
        months.put(6,"JUNE");
        months.put(7,"JULY");
        months.put(8,"AUGUST");
        months.put(9,"SEPTEMBER");
        months.put(10,"OCTOBER");
        months.put(11,"NOVEMBER");
        months.put(12,"DECEMBER");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_expense);
        Cursor allExpense = controller.getAllExpense();
        startManagingCursor(allExpense);
        String[] from = {"month","amount"};
        int[] to = {R.id.expenseFor,R.id.value};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.expense_row,allExpense,from,to);
        adapter.setViewBinder(this);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

    }

    public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
        if(columnIndex ==1){
            ((TextView) view).setText(months.get(cursor.getInt(columnIndex)));
            return true;
        }
        return false;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView monthTextView = (TextView) view.findViewById(R.id.expenseFor);
        controller.showMonthView(months.inverse().get(monthTextView.getText()));

    }
}
