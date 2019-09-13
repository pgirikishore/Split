package com.example.split;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class OverviewFragment extends Fragment {

    private static final String TAG = "OverviewFragment";

    //vars
    private ArrayList<String> mNammes= new ArrayList<>();
    private ArrayList<String> mRate=new ArrayList<>();
    RecyclerView recyclerView;
    private View view;
    private FloatingActionMenu group1;
    private FloatingActionButton expense,payment;

    public OverviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_overview, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recylerv_view1);
        expense=(FloatingActionButton)view.findViewById(R.id.expense);

        expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getContext(),Expense.class);
                intent.putExtra("name","expense");
                startActivity(intent);
            }
        });

        payment=(FloatingActionButton)view.findViewById(R.id.payment);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Expense.class);
                intent.putExtra("name","payment");
                startActivity(intent);
            }
        });
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        initNames();
        return view;
    }

    private void initNames(){
        Log.d(TAG, "initNames: ");

        mNammes.add("Giri Kishore");
        mRate.add("INR 1500.00");

        mNammes.add("Karthik MK");
        mRate.add("INR 500.00");

        mNammes.add("Sonal Roche");
        mRate.add("-INR 800.00");

        mNammes.add("Andrew Winston");
        mRate.add("-INR 1200.00");


        initRecyclerView();

    }

    private void initRecyclerView(){
        //recyclerView =(RecyclerView) view.findViewById(R.id.recyclerv_view1);
        RecyclerViewAdapter1 adapter1= new RecyclerViewAdapter1(getContext(),mNammes,mRate);
        recyclerView.setAdapter(adapter1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
