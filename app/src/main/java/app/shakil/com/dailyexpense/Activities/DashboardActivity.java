package app.shakil.com.dailyexpense.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import app.shakil.com.dailyexpense.Adapter.ItemListAdapter;
import app.shakil.com.dailyexpense.R;
import app.shakil.com.dailyexpense.SaveDailyExpense;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerViewItem;
    private ArrayList<String> stringArrayListTitle;
    private ArrayList<String> stringArrayListDate;
    private ItemListAdapter itemListAdapter;
    private SaveDailyExpense saveDailyExpense;
    private FloatingActionButton addANewExpense;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        init();
        stringArrayListTitle=saveDailyExpense.getExpenseTitle();
        stringArrayListDate=saveDailyExpense.getExpenseDate();
        itemListAdapter=new ItemListAdapter(stringArrayListTitle,stringArrayListDate,getApplicationContext());
        recyclerViewItem.setLayoutManager(linearLayoutManager);
        recyclerViewItem.setAdapter(itemListAdapter);

        addANewExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, AddExpense.class));
            }
        });
    }

    public void init(){
        recyclerViewItem=findViewById(R.id.recyclerViewItemXML);
        addANewExpense = findViewById(R.id.fabAddNewExpense);
        stringArrayListTitle=new ArrayList<>();
        stringArrayListDate=new ArrayList<>();
        saveDailyExpense=new SaveDailyExpense(getApplicationContext());
        linearLayoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
    }
}
