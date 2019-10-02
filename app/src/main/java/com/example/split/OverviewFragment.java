package com.example.split;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    private Button settleup;
    private Button invite;
    private String inviteKey;

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
        settleup=(Button)view.findViewById(R.id.settleup);
        mFirebaseAuth=FirebaseAuth.getInstance();
        invite=(Button)view.findViewById(R.id.invite);

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

        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                DatabaseReference ref= FirebaseDatabase.getInstance().getReference("groups");
                ref.keepSynced(true);
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                        {
                            Upload upload1=dataSnapshot1.getValue(Upload.class);
                            for(int j=0;j<upload1.getViewers().size();j++)
                            {
                                if(dataSnapshot1.child("name").getValue().equals(Group.name)  &&  dataSnapshot1.child("viewers").child(String.valueOf(j)).getValue().equals(mFirebaseAuth.getCurrentUser().getEmail()))
                                {
                                    inviteKey = dataSnapshot1.getKey();
                                    //alert.setMessage("\t"+inviteKey.substring(inviteKey.length()-6)+"\t\n\t Others can access you group\t\n\t\t using this code\n" );
                                    //TextView myMsg=new TextView(getContext());
                                    //myMsg.setText("\n\n"+inviteKey.substring(inviteKey.length()-6)+"\n\n\n Others can access this group\n using this code\n");
                                    //myMsg.setTextSize(20);
                                    //myMsg.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);


                                    Context context =getContext();
                                    LinearLayout layout = new LinearLayout(context);
                                    layout.setOrientation(LinearLayout.VERTICAL);

// Add a TextView here for the "Title" label, as noted in the comments
                                    final TextView titleBox = new TextView(context);
                                    titleBox.setHint("\n"+inviteKey.substring(inviteKey.length()-6));
                                    titleBox.setGravity(Gravity.CENTER_HORIZONTAL);
                                    titleBox.setTextSize(30);
                                    titleBox.setHintTextColor(Color.BLACK);
                                    titleBox.setTextColor(Color.BLACK);
                                    layout.addView(titleBox); // Notice this is an add method

// Add another TextView here for the "Description" label
                                    final TextView descriptionBox = new TextView(context);
                                    descriptionBox.setHint("\n Others can access this group\n using this code\n\n");
                                    descriptionBox.setGravity(Gravity.CENTER_HORIZONTAL);
                                    descriptionBox.setTextSize(15);
                                    layout.addView(descriptionBox); // Another add method


                                    alert.setView(layout);


                                    //alert.setView(myMsg);





                                    alert.show();
                                    //inviteKey.append(dataSnapshot1.getKey().charAt(dataSnapshot1.getKey().length()-5));
                                    //inviteKey.append(dataSnapshot1.getKey().charAt(dataSnapshot1.getKey().length()-4));
                                    //inviteKey.append(dataSnapshot1.getKey().charAt(dataSnapshot1.getKey().length()-3));
                                    //inviteKey.append(dataSnapshot1.getKey().charAt(dataSnapshot1.getKey().length()-2));
                                    //inviteKey.append(dataSnapshot1.getKey().charAt(dataSnapshot1.getKey().length()-1));
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });



        settleup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference settleUpRef= FirebaseDatabase.getInstance().getReference("groups");
                settleUpRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                        {
                            Upload upload=dataSnapshot1.getValue(Upload.class);
                            Double amt[]=new Double[upload.getNetAmt().size()];
                            String names[]=new String[upload.getMembers().size()];
                            for(int i=0;i<upload.getNetAmt().size();i++)
                            {
                                amt[i]=upload.getNetAmt().get(i);
                                names[i]=upload.getMembers().get(i);
                            }

                            SettleUp set = new SettleUp();
                            set.settleup(amt,names);
                            if(set.payy.toString().trim().isEmpty())
                            {
                                set.payy.append("No Suggested Payments");
                            }

                            AlertDialog.Builder settleupAlert= new AlertDialog.Builder(getContext());
                            settleupAlert.setTitle("Suggested Payments");
                            settleupAlert.setMessage(set.payy);
                            settleupAlert.show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });



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
                    for(int j=0;j<upload.getViewers().size();j++)
                    {
                        if(dataSnapshot1.child("name").getValue().equals(Group.name) && dataSnapshot1.child("viewers").child(String.valueOf(j)).getValue().equals(mFirebaseAuth.getCurrentUser().getEmail()))
                        {
                            df2.setRoundingMode(RoundingMode.UP);

                            for(int i=0;i<upload.getMembers().size();i++)
                            {
                                mNammes.add(upload.getMembers().get(i));
                                mRate.add("INR "+df2.format(upload.getNetAmt().get(i)));
                            }
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
