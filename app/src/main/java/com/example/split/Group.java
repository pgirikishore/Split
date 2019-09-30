package com.example.split;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Group extends AppCompatActivity {

    private Toolbar mToolbar;
    private ViewPager myViewPager;
    private TabLayout myTabLayout;
    private TabsAccessorAdapter myTabsAccessorAdapter;
    private DatabaseReference mDatabaseRef,mDatabaseRef1;
    private StorageReference mStorageRef,mStorageRef1;
    private FirebaseAuth mFirebaseAuth;
    //private String idgroup;
    public static String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        name= getIntent().getStringExtra("Name");
        mToolbar=(Toolbar)findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(name);
        mToolbar.getOverflowIcon().setColorFilter(Color.WHITE , PorterDuff.Mode.SRC_ATOP);

        mDatabaseRef=FirebaseDatabase.getInstance().getReference("groups");
        mStorageRef= FirebaseStorage.getInstance().getReference("uploads");
        mFirebaseAuth=FirebaseAuth.getInstance();

        myViewPager=(ViewPager)findViewById(R.id.main_tabs_pager);
        myTabsAccessorAdapter =new TabsAccessorAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(myTabsAccessorAdapter);

        myTabLayout=(TabLayout)findViewById(R.id.main_tabs);
        myTabLayout.setupWithViewPager(myViewPager);
    }

    @Override
    public void onBackPressed() {
        Intent i= new Intent(this, Home.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.group_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.delGroup) {
            mDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                    {
                        Upload upload= dataSnapshot1.getValue(Upload.class);

                        if(upload.getName().equals(name) && upload.getViewers().contains(mFirebaseAuth.getCurrentUser().getEmail().toString()))
                        {
                            mDatabaseRef1=FirebaseDatabase.getInstance().getReferenceFromUrl(dataSnapshot1.getRef().toString());
                            String key= dataSnapshot1.getKey();
                            Toast.makeText(getApplicationContext(),key,Toast.LENGTH_LONG).show();
                            mDatabaseRef1.removeValue();
                            mStorageRef1=FirebaseStorage.getInstance().getReference("uploads/"+key+".null");
                            mStorageRef1.delete();
                            DatabaseReference db= FirebaseDatabase.getInstance().getReference("Transaction").child(key);
                            db.removeValue();
                            Intent del= new Intent(getApplicationContext(), Home.class);
                            startActivity(del);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            //mStorageRef.child(Home.idgroup+".null").delete();

        }

        return super.onOptionsItemSelected(item);
    }
}
