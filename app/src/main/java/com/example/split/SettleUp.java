package com.example.split;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

public class SettleUp {

    static int maxindex=0;
    static int minindex=0;
    public String maxName;
    public String minName;
    public StringBuffer payy=new StringBuffer();
    DatabaseReference mDatabase;
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public static int maximum(Double amt[])
    {
        Double t=amt[0];
        for(int i=0;i<amt.length;i++)
        {
            if(amt[i]>=t)
            {
                t=amt[i];
                maxindex=i;
            }
        }
        return maxindex;
    }

    public static int minimum(Double amt[])
    {
        Double t=amt[0];
        for(int i=0;i<amt.length;i++)
        {
            if(amt[i]<=t)
            {
                t=amt[i];
                minindex=i;
            }
        }
        return minindex;
    }

    public void settleup(Double amt1[],String names[])
    {
        int max=maximum(amt1);
        int min=minimum(amt1);
        maxName=names[max];
        minName=names[min];


        if(amt1[min]<=0 && amt1[max]==0)
        {
            return;
        }

        else
        {
            if(amt1[max]>=(-1*amt1[min]))
            {
                //System.out.println("Person "+min+" has to pay "+(-1*amt1[min])+" to person "+max);
                this.payy.append("\n"+minName+" has to pay "+df2.format(-1*amt1[min])+" to "+maxName+"\n");
                amt1[max]=Double.parseDouble(df2.format(amt1[max]+amt1[min]));
                amt1[min]=Double.parseDouble(df2.format(amt1[min]-amt1[min]));
            }
            else if(amt1[max]<(-1*amt1[min]))
            {
                //System.out.println("Person "+min+" has to pay "+(amt1[max])+" to person "+max);
                this.payy.append("\n"+minName+" has to pay "+df2.format(amt1[max])+" to "+maxName+"\n");
                amt1[min]=Double.parseDouble(df2.format(amt1[min]+amt1[max]));
                amt1[max]=0.0;
            }
            settleup(amt1,names);
        }
    }

}
