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
        init(); 
        Intent getIntent=getIntent();
        dateStr=getIntent.getStringExtra("date");
        titleStr=getIntent.getStringExtra("title");
        descriptionStr=getIntent.getStringExtra("description");
        currencyStr=getIntent.getStringExtra("currency");
        amountInt=getIntent.getIntExtra("amount",0);

        titleTXT.setText(""+titleStr);
        descriptionTXT.setText(""+descriptionStr);
        dateTXT.setText(""+dateStr);
        amountTXT.setText(""+amountInt);
        currencyTXT.setText(""+currencyStr);
    }

    public void init(){
        titleTXT=findViewById(R.id.title);
        descriptionTXT=findViewById(R.id.description);
        dateTXT=findViewById(R.id.date);
        amountTXT=findViewById(R.id.amount);
        currencyTXT=findViewById(R.id.currency);
    }
}
