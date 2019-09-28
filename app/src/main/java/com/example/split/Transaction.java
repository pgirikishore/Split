package com.example.split;

public class Transaction {

    private String Title,PaidBy,PaidTo,Date;
    private Double Amount;

    public Transaction()
    {

    }

    public Transaction(String title, String paidBy, String paidTo, String date, Double amount) {
        Title = title;
        PaidBy = paidBy;
        PaidTo = paidTo;
        Date = date;
        Amount = amount;
    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPaidBy() {
        return PaidBy;
    }

    public void setPaidBy(String paidBy) {
        PaidBy = paidBy;
    }

    public String getPaidTo() {
        return PaidTo;
    }

    public void setPaidTo(String paidTo) {
        PaidTo = paidTo;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }
}
