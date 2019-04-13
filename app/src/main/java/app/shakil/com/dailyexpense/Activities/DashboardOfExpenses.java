package app.shakil.com.dailyexpense.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
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
    // more efficient than HashMap for mapping integers to objects
    private SparseArray<Group> groups = new SparseArray<Group>();
    private ArrayList<String> stringArrayListDate;
    private ArrayList<String> stringArrayListTitle;
    private ArrayList<String> sortedDateList;
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

        init();

        addnewExpenseFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardOfExpenses.this,AddExpense.class));
            }
        });

        createData();
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
            expenseModel=(saveDailyExpense.getSingleExpenseDetailsByDate(sortedDateList.get(start)));
            try{
                group.children.add("" + expenseModel.getTitle());
            }
            catch (Exception e){
                Log.v("Errorror : ",""+e.getMessage());
            }
            groups.append(start, group);
        }
    }

    public void init(){
        saveDailyExpense=new SaveDailyExpense(getApplicationContext());
        expandableListViewDashboard =  findViewById(R.id.expandableListviewXml);
        stringArrayListDate=new ArrayList<>();
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
