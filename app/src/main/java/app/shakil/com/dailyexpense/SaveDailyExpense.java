package app.shakil.com.dailyexpense;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

import app.shakil.com.dailyexpense.Models.ExpenseModel;

public class SaveDailyExpense extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 007;

    // Database Name
    private static final String DATABASE_NAME = "SaveDailyExpense.db";

    // User table name
    private static final String TABLE_SAVE_EXPENSE = "saveexpense";

    // User Table Columns names
    private static final String COLUMN_EXPENSE_ID = "expense_id";
    private static final String COLUMN_EXPENSE_TITLE = "expense_title";
    private static final String COLUMN_EXPENSE_DESCRIPTION = "expense_description";
    private static final String COLUMN_EXPENSE_DATE = "expense_date";
    private static final String COLUMN_EXPENSE_AMOUNT = "expense_amount";
    private static final String COLUMN_EXPENSE_CURRENCY = "expense_currency";

    // create table sql query
    private String CREATE_EXPENSE_TABLE = "CREATE TABLE " + TABLE_SAVE_EXPENSE + "("
            + COLUMN_EXPENSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_EXPENSE_TITLE + " TEXT,"
            + COLUMN_EXPENSE_DESCRIPTION + " TEXT,"+COLUMN_EXPENSE_DATE+" TEXT,"+COLUMN_EXPENSE_AMOUNT+" REAL,"
            + COLUMN_EXPENSE_CURRENCY+" TEXT"+")";

    // drop table sql query
    private String DROP_EXPENSE_TABLE = "DROP TABLE IF EXISTS " + TABLE_SAVE_EXPENSE;

    /**
     * Constructor
     *
     * @param context
     */
    public SaveDailyExpense(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EXPENSE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_EXPENSE_TABLE);
        // Create tables again
        onCreate(db);
    }

    /**
     * This method is to create expense record
     *
     * @param expenseModel
     */
    public void addNewExpense(ExpenseModel expenseModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EXPENSE_TITLE,expenseModel.getTitle());
        values.put(COLUMN_EXPENSE_DESCRIPTION, expenseModel.getDescription());
        values.put(COLUMN_EXPENSE_DATE, expenseModel.getDate());
        values.put(COLUMN_EXPENSE_AMOUNT, expenseModel.getAmount());
        values.put(COLUMN_EXPENSE_CURRENCY, expenseModel.getCurrency());

        // Inserting Row
        db.insert(TABLE_SAVE_EXPENSE, null, values);
        Log.v("SUCCESS MESSAGE : ","DATA INSERTED SUCCESSFULLY");
        db.close();
    }
    /**
     * This method is to delete terms record
     */
    public void deleteALLExpense() {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_SAVE_EXPENSE,null,null);
        db.close();
    }

    /**
     * This method is to delete a single terms row record
     */
    public  void deleteSingleExpense(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.v("NAME 2 : ",""+name);
        // delete user record by name
        try{
            db.execSQL("DELETE FROM " + TABLE_SAVE_EXPENSE + " WHERE " + COLUMN_EXPENSE_TITLE + "= '" + name + "'");
        }
        catch (Exception e){
            Log.v("EXCEPTION : ",""+e.getMessage());
        }
        db.close();
    }
    /**
     * This method is to fetch all expense and return the list of expense records
     *
     * @return list
     */
    public ArrayList<ExpenseModel> getAllExpense() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_EXPENSE_TITLE,
                COLUMN_EXPENSE_DESCRIPTION,
                COLUMN_EXPENSE_DATE,
                COLUMN_EXPENSE_AMOUNT,
                COLUMN_EXPENSE_CURRENCY
        };
        ArrayList<ExpenseModel> expenseList = new ArrayList<ExpenseModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_SAVE_EXPENSE, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order
        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ExpenseModel expenseModel = new ExpenseModel();
                expenseModel.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_EXPENSE_TITLE)));
                expenseModel.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_EXPENSE_DESCRIPTION)));
                expenseModel.setAmount(cursor.getInt(cursor.getColumnIndex(COLUMN_EXPENSE_AMOUNT)));
                expenseModel.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_EXPENSE_DATE)));
                expenseModel.setCurrency(cursor.getString(cursor.getColumnIndex(COLUMN_EXPENSE_CURRENCY)));
                // Adding user record to list
                expenseList.add(expenseModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return expenseList;
    }


    /**
     * This method is to fetch only the expense name and return the list of expense name list records
     *
     * @return list of string which is the expense title
     */
    public ArrayList<String> getExpenseTitle() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_EXPENSE_TITLE,
        };
        ArrayList<String> expenseList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        // query the user table
        Cursor cursor = db.query(TABLE_SAVE_EXPENSE, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order
        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                // Adding user record to list
                expenseList.add(cursor.getString(cursor.getColumnIndex(COLUMN_EXPENSE_TITLE)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return user list
        return expenseList;
    }
    /**
     * This method is to fetch only the expense name and return the list of expense name list records
     *
     * @return list of string which is the expense title
     */
    public ArrayList<String> getExpenseDate() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_EXPENSE_DATE,
        };
        ArrayList<String> expenseDateList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        // query the user table
        Cursor cursor = db.query(TABLE_SAVE_EXPENSE, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order
        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                // Adding user record to list
                expenseDateList.add(cursor.getString(cursor.getColumnIndex(COLUMN_EXPENSE_DATE)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return user list
        return expenseDateList;
    }
}

