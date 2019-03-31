package app.shakil.com.dailyexpense.Models;

public class ExpenseModel {
    private int id;
    private String title;
    private String description;
    private String date;
    private int amount;
    private String currency;

    public ExpenseModel(){

    }

    public ExpenseModel(String title,String description,String date,int amount,String currency){
        this.title=title;
        this.description=description;
        this.date=date;
        this.amount=amount;
        this.currency=currency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
