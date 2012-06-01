package com.example.controller;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import com.example.model.Expense;
import com.example.repository.ExpenseRepository;
import com.example.view.ViewExpense;
import com.example.view.ViewExpensePerMonth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExpenseController {

    private ExpenseRepository expenseRepository;
    private Context context;

    public ExpenseController(Context context) {
        this.context = context;
        expenseRepository = new ExpenseRepository(context);
    }

    public void saveExpense(String date, Integer amount, String reason) {
        Date spentDate = null;
        try {
            spentDate = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        } catch (ParseException e) { }
        Expense expense = new Expense(spentDate, amount, reason);
        expenseRepository.save(expense);
    }

    public void showExpense(int itemId) {
        //if (itemId == R.id.view) {
            Intent intent = new Intent(context, ViewExpense.class);
            context.startActivity(intent);
        //}
    }

    public Cursor getAllExpense() {
        return expenseRepository.getAllExpense();
    }

    public void showMonthView(Integer month) {
        Intent intent = new Intent(context, ViewExpensePerMonth.class);
        intent.putExtra("month",month);
        context.startActivity(intent);
    }

    public Cursor getAllExpenseForMonth(int month) {
        return expenseRepository.getExpenseForMonth(month);
    }
}
