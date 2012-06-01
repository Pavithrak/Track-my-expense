package com.example.model;


import java.text.SimpleDateFormat;
import java.util.Date;
public class Expense {
    private Date date;
    private Integer amount;
    private String reason;

    public Expense() {
    }

    public Expense(Date date, Integer amount, String reason) {
        this.date = date;
        this.amount = amount;
        this.reason = reason;
    }

    public String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
