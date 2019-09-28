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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import java.math.RoundingMode;
import java.text.DecimalFormat;


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
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mFirebaseAuth;

    private static DecimalFormat df2 = new DecimalFormat("#.##");

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
        mFirebaseAuth=FirebaseAuth.getInstance();

        expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getContext(),Expense.class);
                intent.putExtra("name",Group.name);
                startActivity(intent);
            }
        });

        payment=(FloatingActionButton)view.findViewById(R.id.payment);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Payment.class);
                intent.putExtra("name",Group.name);
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

        mDatabaseReference= FirebaseDatabase.getInstance().getReference("groups");
        mDatabaseReference.keepSynced(true);
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mNammes.clear();
                mRate.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Upload upload=dataSnapshot1.getValue(Upload.class);
                    if(dataSnapshot1.child("name").getValue().equals(Group.name) && dataSnapshot1.child("createdBy").getValue().equals(mFirebaseAuth.getCurrentUser().getEmail()))
                    {
                        df2.setRoundingMode(RoundingMode.UP);

                        for(int i=0;i<upload.getMembers().size();i++)
                        {
                            mNammes.add(upload.getMembers().get(i));
                            mRate.add("INR "+df2.format(upload.getNetAmt().get(i)));
                        }
                    }
                }
                initRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });


        /*mNammes.add("Giri Kishore");
        mRate.add("INR 1500.00");

        mNammes.add("Karthik MK");
        mRate.add("INR 500.00");

        mNammes.add("Sonal Roche");
        mRate.add("-INR 800.00");

        mNammes.add("Andrew Winston");
        mRate.add("-INR 1200.00");*/




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
