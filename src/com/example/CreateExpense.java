package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateExpense extends Activity implements View.OnClickListener {
    ExpenseController controller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ((EditText)findViewById(R.id.date)).setText(getCurrentDate());
        controller = new ExpenseController(this);
        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);
    }

    private String getCurrentDate() {
        return new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.layout.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        controller.showExpense(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        Editable date = ((EditText) findViewById(R.id.date)).getText();
        Editable amount = ((EditText) findViewById(R.id.amount)).getText();
        Editable reason = ((EditText) findViewById(R.id.reason)).getText();
        controller.saveExpense(date.toString(),new Integer(amount.toString()), reason.toString());
        clearFields();
    }

    private void clearFields() {
        ((TextView) findViewById(R.id.display)).setText("Expense saved!");
        ((EditText) findViewById(R.id.amount)).setText("");
        ((EditText) findViewById(R.id.reason)).setText("");
    }
}
