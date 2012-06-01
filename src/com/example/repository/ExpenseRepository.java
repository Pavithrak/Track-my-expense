package com.example.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.DbHelper;
import com.example.model.Expense;

public class ExpenseRepository {
    private DbHelper dbHelper;

    public ExpenseRepository(Context context) {
        this.dbHelper = DbHelper.getInstance(context);
    }

    public void save(Expense expense) {
        getWritableDatabase().execSQL("insert into expense values('" + expense.getDate() +
                "'," + expense.getAmount() + ",'" + expense.getReason() + "')");
    }
    public Cursor getAllExpense(){
        return getWritableDatabase().rawQuery("select rowid as _id,strftime('%m',spentOn) as month, sum(amount) as amount" +
                                              " from expense group by strftime('%m',spentOn);",null);
    }

    private SQLiteDatabase getWritableDatabase() {
        return dbHelper.getWritableDatabase();
    }

    public Cursor getExpenseForMonth(int month) {
        String query = "select rowid as _id ,strftime('%d-%m-%Y',spentOn) as date," +
                "amount,reason from expense where strftime('%m',spentOn)='" + month/10 + "" + month%10 + "'";
        return getWritableDatabase().rawQuery(query,null);
    }
}
