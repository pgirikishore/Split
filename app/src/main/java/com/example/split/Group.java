package com.example.split;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.util.Log;
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
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Locale;

public class Group extends AppCompatActivity {

    private static final int STORAGE_CODE = 1000;
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
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Are you sure you wanna delete it?");
            alert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    mDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                            {
                                Upload upload= dataSnapshot1.getValue(Upload.class);

                                if(upload.getName().equals(name) && upload.getViewers().contains(mFirebaseAuth.getCurrentUser().getEmail().toString()))
                                {
                                    if(upload.getViewers().get(0).equals(mFirebaseAuth.getCurrentUser().getEmail()))
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
                                    else
                                    {
                                        ArrayList<String> viewers = new ArrayList<>();
                                        for(int i=0;i<upload.getViewers().size();i++)
                                        {
                                            if(!upload.getViewers().get(i).equals(mFirebaseAuth.getCurrentUser().getEmail()))
                                            {
                                                viewers.add(upload.getViewers().get(i));
                                            }
                                        }
                                        mDatabaseRef.child(dataSnapshot1.getKey()).child("viewers").setValue(viewers);
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    //mStorageRef.child(Home.idgroup+".null").delete();
                }
            });
            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alert.show();
        }

        else if(id == R.id.exportPDF)
        {
            if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M)
            {
                if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED)
                {
                    String[] permissions= {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(permissions,STORAGE_CODE);
                }
                else
                {
                    savePdf();
                }
            }
            else
            {
                savePdf();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void savePdf() {
        final DatabaseReference pdfDatabaseReference=FirebaseDatabase.getInstance().getReference("Transaction");
        mDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(final DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Upload upload= dataSnapshot1.getValue(Upload.class);
                    if(upload.getName().equals(name) && upload.getViewers().contains(mFirebaseAuth.getCurrentUser().getEmail().toString()))
                    {
                        try
                        {
                            Rectangle layout = new Rectangle(PageSize.A4);
                            layout.setBackgroundColor(new BaseColor(37,42,55));
                            final Document mDoc= new Document(layout);
                            String mFileName = name+" "+ new SimpleDateFormat("yyyyMMdd_HHmmss",
                                    Locale.getDefault()).format(System.currentTimeMillis());

                            String mFilePath= Environment.getExternalStorageDirectory()+"/"+mFileName+".pdf";


                            PdfWriter.getInstance(mDoc, new FileOutputStream(mFilePath));
                            mDoc.open();

                            Font red = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD, BaseColor.WHITE);
                            final Font createdby=new Font(Font.FontFamily.HELVETICA,10 , Font.NORMAL, BaseColor.WHITE);
                            Chunk c= new Chunk(name+"\n",red);
                            Paragraph p = new Paragraph(c);

                            mDoc.add(p);
                            mDoc.add(new Paragraph(new Chunk("Created with Split\n\n\n",createdby)));

                            mDoc.addAuthor("Giri Kishore");

                            Font title = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, new BaseColor(244,213,41));
                            PdfPTable table = new PdfPTable(4);


                            PdfPCell dcel = table.getDefaultCell();
                            dcel.setBorder(PdfPCell.NO_BORDER);
                            dcel.setPadding(10);
                            table.addCell(new Paragraph(new Chunk("Title",title)));
                            table.addCell(new Paragraph(new Chunk("Amount",title)));
                            table.addCell(new Paragraph(new Chunk("Paid By",title)));
                            table.addCell(new Paragraph(new Chunk("Date",title)));
                            mDoc.add(table);

                            final Font entry = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(255,255,255));

                            Log.d("Key Value", dataSnapshot1.getKey());
                            final DatabaseReference pdfDatabaseReference=FirebaseDatabase.getInstance().getReference("Transaction").child(dataSnapshot1.getKey());
                            pdfDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {
                                    Double total = 0.0;
                                    for (DataSnapshot dataSnapshot3:dataSnapshot2.getChildren())
                                    {
                                        Transaction t= dataSnapshot3.getValue(Transaction.class);
                                        PdfPTable table1 = new PdfPTable(4);

                                        PdfPCell dcel1 = table1.getDefaultCell();
                                        dcel1.setBorder(PdfPCell.NO_BORDER);
                                        dcel1.setPadding(10);
                                        total = total +t.getAmount();
                                        if(t.getTitle().equals("Payment"))
                                            table1.addCell(new Paragraph(new Chunk(t.getTitle()+" to "+t.getPaidTo(),entry)));
                                        else
                                            table1.addCell(new Paragraph(new Chunk(t.getTitle(),entry)));
                                        table1.addCell(new Paragraph(new Chunk(String.valueOf(t.getAmount()),entry)));
                                        table1.addCell(new Paragraph(new Chunk(t.getPaidBy(),entry)));
                                        table1.addCell(new Paragraph(new Chunk(t.getDate(),createdby)));

                                        try {
                                            mDoc.add(table1);
                                        } catch (DocumentException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    Chunk linebreak = new Chunk(new LineSeparator());
                                    try {
                                        mDoc.add(linebreak);
                                    } catch (DocumentException e) {
                                        e.printStackTrace();
                                    }

                                    PdfPTable table2 = new PdfPTable(4);

                                    PdfPCell dcel12 = table2.getDefaultCell();
                                    dcel12.setBorder(PdfPCell.NO_BORDER);
                                    dcel12.setPaddingLeft(10);
                                    dcel12.setPaddingRight(10);
                                    table2.addCell(new Paragraph(new Chunk("Total",entry)));
                                    table2.addCell(new Paragraph(new Chunk(String.valueOf(total),entry)));
                                    table2.addCell(new Paragraph(new Chunk(" ",entry)));
                                    table2.addCell(new Paragraph(new Chunk(" ",entry)));
                                    try {
                                        mDoc.add(table2);
                                    } catch (DocumentException e) {
                                        e.printStackTrace();
                                    }

                                    mDoc.close();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            Toast.makeText(getApplicationContext(),mFileName+".pdf\n is saved to \n"+mFilePath,Toast.LENGTH_LONG).show();
                        }
                        catch (Exception e)
                        {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode)
        {
            case STORAGE_CODE:
            {
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    savePdf();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Permission Denied...!",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
