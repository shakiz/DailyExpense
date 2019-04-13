package app.shakil.com.dailyexpense.ExpandListViewComponents;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import app.shakil.com.dailyexpense.Activities.ItemDetails;
import app.shakil.com.dailyexpense.Models.ExpenseModel;
import app.shakil.com.dailyexpense.R;
import app.shakil.com.dailyexpense.SaveDailyExpense;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {
    private final SparseArray<Group> groupSparseArray;
    public LayoutInflater inflater;
    public Activity activity;
    private ArrayList<String> stringArrayListTitle;
    private ArrayList<String> stringArrayListDate;
    private ExpenseModel expenseModel;
    private Context context;
    private SaveDailyExpense saveDailyExpense;

    //creating the constructor which takes two parameters activity , sparsearray
    public MyExpandableListAdapter(Activity activity, SparseArray<Group> sparseArray, ArrayList<String> stringArrayListTitle, ArrayList<String> stringArrayListDate
                    ,Context context){
        this.activity=activity;
        this.groupSparseArray=sparseArray;
        this.stringArrayListTitle=stringArrayListTitle;
        this.stringArrayListDate=stringArrayListDate;
        inflater=activity.getLayoutInflater();
        saveDailyExpense=new SaveDailyExpense(activity);
        this.context=context;
    }
    @Override
    public int getGroupCount() {
        return groupSparseArray.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groupSparseArray.get(groupPosition).children.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupSparseArray.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        //getting the object of chlidren position item
        return groupSparseArray.get(groupPosition).children.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=inflater.inflate(R.layout.listrow_group,null);
        }
        Group group= (Group) getGroup(groupPosition);
        ((CheckedTextView) convertView).setText(group.string);
        ((CheckedTextView) convertView).setChecked(isExpanded);
        return  convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, final ViewGroup parent) {
        //taking a string variable to get the child
        final String children= (String) getChild(groupPosition,childPosition);
        TextView text=null;
        //checking whether the view is null or not, if null then initialize it with layout
        if (convertView==null){
            convertView=inflater.inflate(R.layout.listrowdetails,null);
        }
        text=convertView.findViewById(R.id.textView1);
        text.setText(children);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expenseModel=saveDailyExpense.getSingleExpenseDetails(children);
                try{
                    Log.v("INFO : ",""+expenseModel.getDescription());
                }
                catch (Exception e){
                    Log.v("ERRORr : ",""+e.getMessage());
                }
                context.startActivity(new Intent(activity, ItemDetails.class).putExtra("title",expenseModel.getTitle())
                        .putExtra("description",expenseModel.getDescription())
                        .putExtra("date",expenseModel.getDate())
                        .putExtra("amount",expenseModel.getAmount())
                        .putExtra("currency",expenseModel.getCurrency())
                );
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
