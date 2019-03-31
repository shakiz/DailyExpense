package app.shakil.com.dailyexpense.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import app.shakil.com.dailyexpense.Activities.ItemDetails;
import app.shakil.com.dailyexpense.R;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder>{
    private ArrayList<String> stringArrayListTitle;
    private ArrayList<String> stringArrayListDate;
    private Context context;

    public ItemListAdapter(ArrayList<String> stringArrayListTitle,ArrayList<String> stringArrayListDate,Context context){
        this.stringArrayListTitle=stringArrayListTitle;
        this.stringArrayListDate=stringArrayListDate;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_title_recyclerview,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.titleTXT.setText(""+stringArrayListTitle.get(position));
        viewHolder.dateTXT.setText(""+stringArrayListDate.get(position));
        viewHolder.titleTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ItemDetails.class));
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
