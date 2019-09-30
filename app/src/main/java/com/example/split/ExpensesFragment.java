package com.example.split;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpensesFragment extends Fragment {


    View view;
    private ArrayList<String> TitleExp= new ArrayList<>();
    private ArrayList<String> AmountExp= new ArrayList<>();
    private ArrayList<String> PaidByExp= new ArrayList<>();
    private ArrayList<String> DateExp= new ArrayList<>();
    RecyclerView recyclerView2;
    private FirebaseAuth mFirebaseAuth;
    private String key;
    private DatabaseReference mDatabaseRef1;

    public ExpensesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_expenses, container, false);
        Log.d(TAG, "onCreateView: ");

        mFirebaseAuth=FirebaseAuth.getInstance();

        recyclerView2=(RecyclerView)view.findViewById(R.id.recylerv_view2);
        recyclerView2.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));


        initExpenses();
        return view;
    }

    private void initExpenses()
    {
        Log.d(TAG, "initExpenses: ");
        

        /*mDatabaseRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    Transaction trans =dataSnapshot1.getValue(Transaction.class);

                    Toast.makeText(getContext(),trans.getTitle(),Toast.LENGTH_SHORT).show();

                    TitleExp.add(trans.getTitle());
                    AmountExp.add("INR "+trans.getAmount());
                    PaidByExp.add("Paid by "+trans.getPaidBy());
                    DateExp.add(trans.getDate());
                }
                TitleExp.add("Cafe Xtasi");
                AmountExp.add("INR 600");
                PaidByExp.add("Paid by Giri");
                DateExp.add("September 29,2019");

                initRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
        //TitleExp.add("Cafe Xtasi");
        //AmountExp.add("INR 600");
        //PaidByExp.add("Paid by Giri");
        //DateExp.add("September 29,2019");


        //initRecyclerView();











        DatabaseReference mDatabaseRef= FirebaseDatabase.getInstance().getReference("groups");

        mDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot3) {
                for(DataSnapshot dataSnapshot1:dataSnapshot3.getChildren())
                {
                    Upload upload=dataSnapshot1.getValue(Upload.class);
                    for(int j=0;j<upload.getViewers().size();j++)
                    {
                        if(dataSnapshot1.child("name").getValue().equals(Group.name) && dataSnapshot1.child("viewers").child(String.valueOf(j)).getValue().equals(mFirebaseAuth.getCurrentUser().getEmail()))
                        {
                            key=dataSnapshot1.getKey();

                        }
                    }
                }
                mDatabaseRef1=FirebaseDatabase.getInstance().getReference("Transaction").child(key);

                mDatabaseRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot2:dataSnapshot.getChildren())
                        {
                            Transaction trans =dataSnapshot2.getValue(Transaction.class);

                            if(trans.getTitle().equals("Payment"))
                            {
                                TitleExp.add("Paid To "+trans.getPaidTo());
                            }
                            else
                            {
                                TitleExp.add(trans.getTitle());
                            }
                            AmountExp.add("INR "+trans.getAmount());
                            PaidByExp.add("Paid by "+trans.getPaidBy());
                            DateExp.add(trans.getDate());
                        }
                        //TitleExp.add("Cafe Xtasi");
                        //AmountExp.add("INR 600");
                        //PaidByExp.add("Paid by Giri");
                        //DateExp.add("September 29,2019");
                        Collections.reverse(TitleExp);
                        Collections.reverse(AmountExp);
                        Collections.reverse(PaidByExp);
                        Collections.reverse(DateExp);
                        initRecyclerView();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







    }

    private void initRecyclerView()
    {
       RecyclerViewAdapter2 adapter2= new RecyclerViewAdapter2(getContext(),TitleExp,AmountExp,PaidByExp, DateExp);
       recyclerView2.setAdapter(adapter2);
       recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
