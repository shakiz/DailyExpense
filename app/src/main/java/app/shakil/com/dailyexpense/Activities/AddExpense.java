package app.shakil.com.dailyexpense.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import app.shakil.com.dailyexpense.Models.ExpenseModel;
import app.shakil.com.dailyexpense.R;
import app.shakil.com.dailyexpense.SaveDailyExpense;

public class AddExpense extends AppCompatActivity {

    private EditText title,description,amount;
    private TextView dateTextView;
    private Button addButton;
    private String dateStr,titleStr,descriptionStr,currencyStr;
    private int amountInt;
    private SimpleDateFormat dateFormatter;
    private Spinner currencySpinner;
    private ArrayList<String> currencyArrayList;
    private ArrayAdapter<String> currencyAdapter;
    private RelativeLayout relativeLayout;
    private SaveDailyExpense saveDailyExpense;
    private ExpenseModel expenseModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        init();
        currencyAdapter=new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,currencyArrayList);
        currencySpinner.setAdapter(currencyAdapter);

        currencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currencyStr=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar newCalendar = Calendar.getInstance();
                //Open a date picker dialog to catch or set the date as the user selection
                DatePickerDialog fromDatePickerDialog = new DatePickerDialog(AddExpense.this, new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        dateTextView.setText(dateFormatter.format(newDate.getTime()));
                        try{
                            dateStr=(dateFormatter.format(newDate.getTime()));
                        }
                        catch (Exception e){
                            Log.v("DATE ERROR : ",""+e.getMessage());

                        }
                    }

                },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                fromDatePickerDialog.show();
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title.getText().toString().isEmpty()){
                    title.setError("Please insert title.");
                }
                else{
                    if (description.getText().toString().isEmpty()){
                        description.setError("Please insert description");
                    }
                    else{
                        if (amount.getText().toString().isEmpty()){
                            amount.setError("Please insert amount.");
                        }
                        else{
                            titleStr=title.getText().toString();
                            descriptionStr=description.getText().toString();
                            amountInt=Integer.parseInt(amount.getText().toString());
                            expenseModel=new ExpenseModel(titleStr,descriptionStr,dateStr,amountInt,currencyStr);
                            saveDailyExpense.addNewExpense(expenseModel);
                            Toast.makeText(getApplicationContext(),"Added successfully",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(AddExpense.this, MainActivity.class));
                        }
                    }
                }
            }
        });
    }

    public void init(){
        title=findViewById(R.id.expenseTitleXML);
        description=findViewById(R.id.expenseDescriptionTitleXML);
        amount=findViewById(R.id.amountXML);
        dateTextView=findViewById(R.id.pickADateXMl);
        relativeLayout=findViewById(R.id.mainExpenseRelativeLayoutXML);
        addButton=findViewById(R.id.addNewExpenseButtonXMl);
        currencySpinner=findViewById(R.id.spinnerCurrencyXML);
        currencyArrayList=new ArrayList<>();
        saveDailyExpense=new SaveDailyExpense(getApplicationContext());
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        currencyArrayList.add("Select currency");
        currencyArrayList.add("Taka");
        currencyArrayList.add("USD");
    }
}
