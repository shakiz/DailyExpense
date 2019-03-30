package app.shakil.com.dailyexpense;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AddExpense extends AppCompatActivity {

    private EditText title,description,amount;
    private TextView dateTextView;
    private Button addButton;
    private String dateStr;
    private SimpleDateFormat dateFormatter;
    private Spinner currencySpinner;
    private ArrayList<String> currencyArrayList;
    private ArrayAdapter<String> currencyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        init();
        currencyAdapter=new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,currencyArrayList);
        currencySpinner.setAdapter(currencyAdapter);
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

            }
        });
    }

    public void init(){
        title=findViewById(R.id.expenseTitleXML);
        description=findViewById(R.id.expenseDescriptionTitleXML);
        amount=findViewById(R.id.amountXML);
        dateTextView=findViewById(R.id.pickADateXMl);
        addButton=findViewById(R.id.addNewExpenseButtonXMl);
        currencySpinner=findViewById(R.id.spinnerCurrencyXML);
        currencyArrayList=new ArrayList<>();
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        currencyArrayList.add("Select currency");
        currencyArrayList.add("Taka");
        currencyArrayList.add("USD");
    }
}
