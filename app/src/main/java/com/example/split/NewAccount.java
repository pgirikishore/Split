package com.example.split;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewAccount extends AppCompatActivity {

    private static final int FILTER_ID =1 ;
    private CircleImageView groupImage;
    private static final int PICK_IMAGE = 1;
    private static final int PICK_IMAGE_REQUEST = 1;
    Uri imageUri,resultUri;
    Toolbar mToolbar;
    ListView listView;
    Button addmember;
    Bitmap bitmap;
    TextInputLayout addmem;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    byte[] data1;
    TextInputLayout gname;
    final ArrayList<String> members= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.appid);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Create Group");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gname=(TextInputLayout) findViewById(R.id.gname);
        listView=(ListView)findViewById(R.id.listview);
        addmember=(Button)findViewById(R.id.addmember);
        addmem=(TextInputLayout)findViewById(R.id.addmem);
        mStorageRef= FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("groups");

        addmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=addmem.getEditText().getText().toString();

                if(name.isEmpty())
                {
                    addmem.getEditText().setError("Please enter the name");
                    addmem.getEditText().requestFocus();
                    return;
                }
                members.add(name);

                ArrayAdapter arrayAdapter= new ArrayAdapter(NewAccount.this,android.R.layout.simple_list_item_1,members) {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        // Get the Item from ListView
                        View view = super.getView(position, convertView, parent);

                        // Initialize a TextView for ListView each Item
                        TextView tv = (TextView) view.findViewById(android.R.id.text1);

                        // Set the text color of TextView (ListView Item)
                        tv.setTextColor(Color.parseColor("#F4D529"));

                        // Generate ListView Item using TextView
                        return view;

                    }
                };

                listView.setAdapter(arrayAdapter);

                addmem.getEditText().setText("");
            }
        });

       /* arrayList.add("Giri");
        arrayList.add("Sonal");
        arrayList.add("Karthik");
        arrayList.add("Andrew");
        arrayList.add("Nithish");
        arrayList.add("Manthan");
        //arrayList.add("Giri");

        ArrayAdapter arrayAdapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(arrayAdapter);*/

        groupImage = (CircleImageView) findViewById(R.id.groupimage);
        groupImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(gallery, PICK_IMAGE);
            }
        });
    }
    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        TextView tv = new TextView(this);
        tv.setText(getString(R.string.Save)+"  ");
        tv.setTextColor(getResources().getColor(R.color.white));
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Save",Toast.LENGTH_SHORT).show();
                uploadFile();
            }
        });
        tv.setPadding(5, 0, 5, 0);
        tv.setTypeface(null, Typeface.BOLD);
        tv.setTextSize(14);
        menu.add(0, FILTER_ID, 1, R.string.Save).setActionView(tv).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            // do something here
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data !=null &&data.getData()!=null) {
            //imageUri = data.getData();

            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .start(this);

            //Picasso.with(this).load(imageUri).into(groupImage);
        }

       if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {
                 resultUri = result.getUri();
                 groupImage.setImageURI(resultUri);


                groupImage.setDrawingCacheEnabled(true);
                groupImage.buildDrawingCache();
                Bitmap bitmap = ((BitmapDrawable) groupImage.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                 data1 = baos.toByteArray();
                //groupImage.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private String getFileExtension(Uri uri)
    {
        ContentResolver cR=getContentResolver();
        MimeTypeMap mime= MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile()
    {
        if(data1!=null) {
            final String name = gname.getEditText().getText().toString();
            if (!name.isEmpty()) {
                UploadTask uploadTask = mStorageRef.child(name+"."+getFileExtension(resultUri)).putBytes(data1);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Upload upload = new Upload(taskSnapshot.getMetadata().getReference().getDownloadUrl().toString(), name, members);
                        //String uploadId = mDatabaseRef.push().getKey();
                        mDatabaseRef.child(name).setValue(upload);

                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            }
                        });
            }
            else
            {
                gname.getEditText().setError("Please enter the name");
                gname.getEditText().requestFocus();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"No FIle Selected",Toast.LENGTH_SHORT).show();
        }
        Intent intent= new Intent(getApplicationContext(),Home.class);
        startActivity(intent);
    }

}
