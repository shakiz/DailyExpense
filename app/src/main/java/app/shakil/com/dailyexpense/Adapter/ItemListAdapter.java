package app.shakil.com.dailyexpense.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import app.shakil.com.dailyexpense.Activities.ItemDetails;
import app.shakil.com.dailyexpense.Models.ExpenseModel;
import app.shakil.com.dailyexpense.R;
import app.shakil.com.dailyexpense.SaveDailyExpense;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder>{
    private ArrayList<String> stringArrayListTitle;
    private ArrayList<String> stringArrayListDate;
    private ArrayList<ExpenseModel> expenseModelArrayList;
    private Context context;
    private SaveDailyExpense saveDailyExpense;

    public ItemListAdapter(ArrayList<String> stringArrayListTitle,ArrayList<String> stringArrayListDate,Context context){
        this.stringArrayListTitle=stringArrayListTitle;
        this.stringArrayListDate=stringArrayListDate;
        this.context=context;
        saveDailyExpense=new SaveDailyExpense(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_title_recyclerview,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        viewHolder.titleTXT.setText(""+stringArrayListTitle.get(position));
        viewHolder.dateTXT.setText(""+stringArrayListDate.get(position));
        viewHolder.titleTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expenseModelArrayList=saveDailyExpense.getSingleExpenseDetails(stringArrayListTitle.get(position));
                try{
                    Log.v("Size : ",""+saveDailyExpense.getSingleExpenseDetails(stringArrayListTitle.get(position)).size());
                    Log.v("INFO : ",""+expenseModelArrayList.get(position).getDescription());
                }
                catch (Exception e){
                    Log.v("ERROR : ",""+e.getMessage());
                }
                context.startActivity(new Intent(context, ItemDetails.class).putExtra("title",expenseModelArrayList.get(position).getTitle())
                                                                            .putExtra("description",expenseModelArrayList.get(position).getDescription())
                                                                            .putExtra("date",expenseModelArrayList.get(position).getDate())
                                                                            .putExtra("amount",expenseModelArrayList.get(position).getAmount())
                                                                            .putExtra("currency",expenseModelArrayList.get(position).getCurrency())
                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringArrayListTitle.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleTXT,dateTXT;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTXT=itemView.findViewById(R.id.titleTextView);
            dateTXT=itemView.findViewById(R.id.dateTextView);
        }
    }
}
