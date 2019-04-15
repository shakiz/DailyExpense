package app.shakil.com.dailyexpense.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import app.shakil.com.dailyexpense.ExpandListViewComponents.Group;
import app.shakil.com.dailyexpense.ExpandListViewComponents.MyExpandableListAdapter;
import app.shakil.com.dailyexpense.Models.ExpenseModel;
import app.shakil.com.dailyexpense.R;
import app.shakil.com.dailyexpense.SaveDailyExpense;

public class DashboardOfExpenses extends AppCompatActivity {
    //SparseArray is much efficient than HashMap for mapping integers to objects
    private SparseArray<Group> groups = new SparseArray<Group>();
    private ArrayList<String> stringArrayListDate;
    private ArrayList<String> stringArrayListTitle;
    private ArrayList<String> sortedDateList;
    private ArrayList<ExpenseModel> expenseModels;
    private FloatingActionButton addnewExpenseFloatingActionButton;
    private SaveDailyExpense saveDailyExpense;
    private HashSet<String> removedRepatedDataSet;
    private ExpenseModel expenseModel;
    private ExpandableListView expandableListViewDashboard;
    private MyExpandableListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_listview);
        //This method will be used to initialize all the attributes with xml
        init();
        //Adding the on click listener for floating action button
        addnewExpenseFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardOfExpenses.this,AddExpense.class));
            }
        });
        //This will add the data for the both group and children in expandable listview
        createData();
        //Initializing the adapter for expandable dashboard listview
        adapter = new MyExpandableListAdapter(this,
                groups,stringArrayListTitle,stringArrayListDate,getApplicationContext());
        expandableListViewDashboard.setAdapter(adapter);
    }

    public void createData() {
        Iterator<String> stringIterator=removedRepatedDataSet.iterator();
        while (stringIterator.hasNext()){
            sortedDateList.add(stringIterator.next());
        }
        for (int start = 0; start < sortedDateList.size(); start++) {
            Group group = new Group(sortedDateList.get(start));
            expenseModels=(saveDailyExpense.getSingleExpenseDetailsByDate(sortedDateList.get(start)));
            try{
                for(int count=0;count<expenseModels.size();count++){
                    group.children.add("" + expenseModels.get(count).getTitle());
                }
            }
            catch (Exception e){
                Log.v("Errorror : ",""+e.getMessage());
            }
            groups.append(start, group);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.action_graph){
            startActivity(new Intent(DashboardOfExpenses.this,ExpenseGraph.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    public void init(){
        saveDailyExpense=new SaveDailyExpense(getApplicationContext());
        expandableListViewDashboard =  findViewById(R.id.expandableListviewXml);
        stringArrayListDate=new ArrayList<>();
        expenseModels=new ArrayList<>();
        stringArrayListTitle=new ArrayList<>();
        sortedDateList=new ArrayList<>();
        removedRepatedDataSet=new HashSet<>();
        expenseModel=new ExpenseModel();
        stringArrayListTitle=saveDailyExpense.getExpenseTitle();
        stringArrayListDate=saveDailyExpense.getExpenseDate();
        removedRepatedDataSet.addAll(stringArrayListDate);
        addnewExpenseFloatingActionButton=findViewById(R.id.fabAddNewExpense);
    }
}
