package com.example.split;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder1> {

    private static final String TAG = "RecyclerViewAdapter2";

    private ArrayList<String> Titleexp= new ArrayList<>();
    private ArrayList<String> Amountexp= new ArrayList<>();
    private ArrayList<String> PaidByexp= new ArrayList<>();
    private ArrayList<String> Dateexp= new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter2(Context mContext,ArrayList<String> title, ArrayList<String> amount, ArrayList<String> paidBy, ArrayList<String> date ) {
        Titleexp = title;
        Amountexp = amount;
        PaidByexp = paidBy;
        Dateexp = date;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem2,parent,false);
        ViewHolder1 holder= new ViewHolder1(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder1 holder, final int position) {
        Log.d(TAG, "onBindViewHolder: ");

        holder.titleexp.setText(Titleexp.get(position));
        holder.amountexp.setText(Amountexp.get(position));
        holder.paidbyexp.setText(PaidByexp.get(position));
        holder.dateexp.setText(Dateexp.get(position));

         holder.parent_layout2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Log.d(TAG, "onClick: ");

                 Toast.makeText(mContext,Titleexp.get(position),Toast.LENGTH_SHORT).show();
             }
         });
    }

    @Override
    public int getItemCount() {
        return Titleexp.size();
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{

        TextView titleexp,amountexp,paidbyexp,dateexp;
        RelativeLayout parent_layout2;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            titleexp=itemView.findViewById(R.id.title);
            amountexp=itemView.findViewById(R.id.amount);
            paidbyexp=itemView.findViewById(R.id.paidby);
            dateexp=itemView.findViewById(R.id.date);
            parent_layout2=itemView.findViewById(R.id.parent_layout2);
        }
    }
}
