package app.shakil.com.dailyexpense.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import app.shakil.com.dailyexpense.R;

public class ItemDetails extends AppCompatActivity {

    private String dateStr,titleStr,descriptionStr,currencyStr;
    private int amountInt;
    private TextView dateTXT,titleTXT,descriptionTXT,currencyTXT,amountTXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        //This method will be used to initialize all the attributes with xml
        init();
        //This method will be used to get all the intent data
        getIntentData();
        //This method will be used to set all the intent data to their respective textviews
        setTextViewResources();
    }

    public void init(){
        titleTXT=findViewById(R.id.title);
        descriptionTXT=findViewById(R.id.description);
        dateTXT=findViewById(R.id.date);
        amountTXT=findViewById(R.id.amount);
        currencyTXT=findViewById(R.id.currency);
    }
    public void getIntentData(){
        Intent getIntent=getIntent();
        dateStr=getIntent.getStringExtra("date");
        titleStr=getIntent.getStringExtra("title");
        descriptionStr=getIntent.getStringExtra("description");
        currencyStr=getIntent.getStringExtra("currency");
        amountInt=getIntent.getIntExtra("amount",0);
    }
    public void setTextViewResources(){
        titleTXT.setText("Title : "+titleStr);
        descriptionTXT.setText("Description : "+descriptionStr);
        dateTXT.setText("Date : "+dateStr);
        amountTXT.setText("Amount : "+amountInt);
        currencyTXT.setText("Currency : "+currencyStr);
    }
}
