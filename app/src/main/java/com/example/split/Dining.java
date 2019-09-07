package com.example.split;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class Dining extends AppCompatActivity {

    ImageView imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10,imageView11,imageView12,imageView13,imageView14,imageView15,imageView16;
    TextView textView,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10,textView11,textView12,textView13,textView14,textView15,textView16,textView17;
    int n;
    double amt;
    double amount[]=new double[17];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dining );

        for(int i=1;i<=16;i++)
        {
            amount[i]=0;
        }

        imageView=(ImageView)findViewById(R.id.imageView);
        imageView2=(ImageView)findViewById(R.id.imageView2);
        imageView3=(ImageView)findViewById(R.id.imageView3);
        imageView4=(ImageView)findViewById(R.id.imageView4);
        imageView5=(ImageView)findViewById(R.id.imageView5);
        imageView6=(ImageView)findViewById(R.id.imageView6);
        imageView7=(ImageView)findViewById(R.id.imageView7);
        imageView8=(ImageView)findViewById(R.id.imageView8);
        imageView9=(ImageView)findViewById(R.id.imageView9);
        imageView10=(ImageView)findViewById(R.id.imageView10);
        imageView11=(ImageView)findViewById(R.id.imageView11);
        imageView12=(ImageView)findViewById(R.id.imageView12);
        imageView13=(ImageView)findViewById(R.id.imageView13);
        imageView14=(ImageView)findViewById(R.id.imageView14);
        imageView15=(ImageView)findViewById(R.id.imageView15);
        imageView16=(ImageView)findViewById(R.id.imageView16);

        textView=(TextView)findViewById(R.id.textView);
        textView2=(TextView)findViewById(R.id.textView2);
        textView3=(TextView)findViewById(R.id.textView3);
        textView4=(TextView)findViewById(R.id.textView4);
        textView5=(TextView)findViewById(R.id.textView5);
        textView6=(TextView)findViewById(R.id.textView6);
        textView7=(TextView)findViewById(R.id.textView7);
        textView8=(TextView)findViewById(R.id.textView8);
        textView9=(TextView)findViewById(R.id.textView9);
        textView10=(TextView)findViewById(R.id.textView10);
        textView11=(TextView)findViewById(R.id.textView11);
        textView12=(TextView)findViewById(R.id.textView12);
        textView13=(TextView)findViewById(R.id.textView13);
        textView14=(TextView)findViewById(R.id.textView14);
        textView15=(TextView)findViewById(R.id.textView15);
        textView16=(TextView)findViewById(R.id.textView16);
        textView17=(TextView)findViewById(R.id.textView17);

        imageView.setVisibility(View.INVISIBLE);
        imageView2.setVisibility(View.INVISIBLE);
        imageView3.setVisibility(View.INVISIBLE);
        imageView4.setVisibility(View.INVISIBLE);
        imageView5.setVisibility(View.INVISIBLE);
        imageView6.setVisibility(View.INVISIBLE);
        imageView7.setVisibility(View.INVISIBLE);
        imageView8.setVisibility(View.INVISIBLE);
        imageView9.setVisibility(View.INVISIBLE);
        imageView10.setVisibility(View.INVISIBLE);
        imageView11.setVisibility(View.INVISIBLE);
        imageView12.setVisibility(View.INVISIBLE);
        imageView13.setVisibility(View.INVISIBLE);
        imageView14.setVisibility(View.INVISIBLE);
        imageView15.setVisibility(View.INVISIBLE);
        imageView16.setVisibility(View.INVISIBLE);

        textView.setVisibility(View.INVISIBLE);
        textView2.setVisibility(View.INVISIBLE);
        textView3.setVisibility(View.INVISIBLE);
        textView4.setVisibility(View.INVISIBLE);
        textView5.setVisibility(View.INVISIBLE);
        textView6.setVisibility(View.INVISIBLE);
        textView7.setVisibility(View.INVISIBLE);
        textView8.setVisibility(View.INVISIBLE);
        textView9.setVisibility(View.INVISIBLE);
        textView10.setVisibility(View.INVISIBLE);
        textView11.setVisibility(View.INVISIBLE);
        textView12.setVisibility(View.INVISIBLE);
        textView13.setVisibility(View.INVISIBLE);
        textView14.setVisibility(View.INVISIBLE);
        textView15.setVisibility(View.INVISIBLE);
        textView16.setVisibility(View.INVISIBLE);

        final NumberPicker numberPicker = new NumberPicker(this);
        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(3);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(numberPicker);
        builder.setTitle("How Many?");
        builder.setMessage("Choose a value :");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                n=numberPicker.getValue();
                Toast.makeText(Dining.this, ""+n,Toast.LENGTH_SHORT).show();
                if(n==3)
                {
                    imageView11.setVisibility(View.VISIBLE);
                    imageView12.setVisibility(View.VISIBLE);
                    imageView4.setVisibility(View.VISIBLE);

                    textView11.setVisibility(View.VISIBLE);
                    textView12.setVisibility(View.VISIBLE);
                    textView4.setVisibility(View.VISIBLE);

                }
                else if(n==4)
                {
                    imageView11.setVisibility(View.VISIBLE);
                    imageView12.setVisibility(View.VISIBLE);
                    imageView4.setVisibility(View.VISIBLE);
                    imageView9.setVisibility(View.VISIBLE);

                    textView11.setVisibility(View.VISIBLE);
                    textView12.setVisibility(View.VISIBLE);
                    textView4.setVisibility(View.VISIBLE);
                    textView9.setVisibility(View.VISIBLE);
                }
                else if(n==5)
                {
                    imageView11.setVisibility(View.VISIBLE);
                    imageView12.setVisibility(View.VISIBLE);
                    imageView13.setVisibility(View.VISIBLE);
                    imageView14.setVisibility(View.VISIBLE);
                    imageView9.setVisibility(View.VISIBLE);

                    textView11.setVisibility(View.VISIBLE);
                    textView12.setVisibility(View.VISIBLE);
                    textView13.setVisibility(View.VISIBLE);
                    textView14.setVisibility(View.VISIBLE);
                    textView9.setVisibility(View.VISIBLE);
                }
                else if(n==6)
                {
                    imageView11.setVisibility(View.VISIBLE);
                    imageView12.setVisibility(View.VISIBLE);
                    imageView13.setVisibility(View.VISIBLE);
                    imageView14.setVisibility(View.VISIBLE);
                    imageView15.setVisibility(View.VISIBLE);
                    imageView16.setVisibility(View.VISIBLE);

                    textView11.setVisibility(View.VISIBLE);
                    textView12.setVisibility(View.VISIBLE);
                    textView13.setVisibility(View.VISIBLE);
                    textView14.setVisibility(View.VISIBLE);
                    textView15.setVisibility(View.VISIBLE);
                    textView16.setVisibility(View.VISIBLE);
                }
                else if(n==7)
                {
                    imageView.setVisibility(View.VISIBLE);
                    imageView2.setVisibility(View.VISIBLE);
                    imageView13.setVisibility(View.VISIBLE);
                    imageView14.setVisibility(View.VISIBLE);
                    imageView6.setVisibility(View.VISIBLE);
                    imageView7.setVisibility(View.VISIBLE);
                    imageView9.setVisibility(View.VISIBLE);

                    textView.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView13.setVisibility(View.VISIBLE);
                    textView14.setVisibility(View.VISIBLE);
                    textView6.setVisibility(View.VISIBLE);
                    textView7.setVisibility(View.VISIBLE);
                    textView9.setVisibility(View.VISIBLE);
                }
                else if(n==8)
                {
                    imageView.setVisibility(View.VISIBLE);
                    imageView2.setVisibility(View.VISIBLE);
                    imageView13.setVisibility(View.VISIBLE);
                    imageView14.setVisibility(View.VISIBLE);
                    imageView6.setVisibility(View.VISIBLE);
                    imageView7.setVisibility(View.VISIBLE);
                    imageView15.setVisibility(View.VISIBLE);
                    imageView16.setVisibility(View.VISIBLE);

                    textView.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView13.setVisibility(View.VISIBLE);
                    textView14.setVisibility(View.VISIBLE);
                    textView6.setVisibility(View.VISIBLE);
                    textView7.setVisibility(View.VISIBLE);
                    textView15.setVisibility(View.VISIBLE);
                    textView16.setVisibility(View.VISIBLE);

                }
                else if(n==9)
                {
                    imageView.setVisibility(View.VISIBLE);
                    imageView2.setVisibility(View.VISIBLE);
                    imageView3.setVisibility(View.VISIBLE);
                    imageView4.setVisibility(View.VISIBLE);
                    imageView5.setVisibility(View.VISIBLE);
                    imageView6.setVisibility(View.VISIBLE);
                    imageView7.setVisibility(View.VISIBLE);
                    imageView15.setVisibility(View.VISIBLE);
                    imageView16.setVisibility(View.VISIBLE);

                    textView.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView3.setVisibility(View.VISIBLE);
                    textView4.setVisibility(View.VISIBLE);
                    textView5.setVisibility(View.VISIBLE);
                    textView6.setVisibility(View.VISIBLE);
                    textView7.setVisibility(View.VISIBLE);
                    textView15.setVisibility(View.VISIBLE);
                    textView16.setVisibility(View.VISIBLE);
                }
                else
                {
                    imageView.setVisibility(View.VISIBLE);
                    imageView2.setVisibility(View.VISIBLE);
                    imageView3.setVisibility(View.VISIBLE);
                    imageView4.setVisibility(View.VISIBLE);
                    imageView5.setVisibility(View.VISIBLE);
                    imageView6.setVisibility(View.VISIBLE);
                    imageView7.setVisibility(View.VISIBLE);
                    imageView8.setVisibility(View.VISIBLE);
                    imageView9.setVisibility(View.VISIBLE);
                    imageView10.setVisibility(View.VISIBLE);

                    textView.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView3.setVisibility(View.VISIBLE);
                    textView4.setVisibility(View.VISIBLE);
                    textView5.setVisibility(View.VISIBLE);
                    textView6.setVisibility(View.VISIBLE);
                    textView7.setVisibility(View.VISIBLE);
                    textView8.setVisibility(View.VISIBLE);
                    textView9.setVisibility(View.VISIBLE);
                    textView10.setVisibility(View.VISIBLE);
                }

            }
        });
        /*builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {


            }
        });*/
        builder.create();
        builder.show();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder image=new AlertDialog.Builder(Dining.this);
                image.setTitle("Add a dish");
                image.setMessage("Enter the amount");

                final EditText input = new EditText(Dining.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput((input), InputMethodManager.SHOW_IMPLICIT);
                input.setLayoutParams(lp);
                image.setView(input);

                image.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        amt=Double.parseDouble(input.getText().toString());
                        amt =(amt+2*(0.09*amt));
                        amount[1] += amt;
                        textView.setText("₹"+round(amount[1],2));
                        total();
                    }
                });
                image.show();
            }

        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder image=new AlertDialog.Builder(Dining.this);
                image.setTitle("Add a dish");
                image.setMessage("Enter the amount");

                final EditText input2 = new EditText(Dining.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input2.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                input2.setLayoutParams(lp);
                image.setView(input2);

                image.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        amt=Double.parseDouble(input2.getText().toString());
                        amt =(amt+2*(0.09*amt));
                        amount[2] += amt;
                        textView2.setText("₹"+round(amount[2],2));
                        total();
                    }
                });
                image.show();
            }

        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder image=new AlertDialog.Builder(Dining.this);
                image.setTitle("Add a dish");
                image.setMessage("Enter the amount");

                final EditText input = new EditText(Dining.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                input.setLayoutParams(lp);
                image.setView(input);

                image.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        amt=Double.parseDouble(input.getText().toString());
                        amt =(amt+2*(0.09*amt));
                        amount[3] += amt;
                        textView3.setText("₹"+round(amount[3],2));
                        total();
                    }
                });
                image.show();
            }

        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder image=new AlertDialog.Builder(Dining.this);
                image.setTitle("Add a dish");
                image.setMessage("Enter the amount");

                final EditText input = new EditText(Dining.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                input.setLayoutParams(lp);
                image.setView(input);

                image.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        amt=Double.parseDouble(input.getText().toString());
                        amt =(amt+2*(0.09*amt));
                        amount[4] += amt;
                        textView4.setText("₹"+round(amount[4],2));
                        total();
                    }
                });
                image.show();
            }

        });

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder image=new AlertDialog.Builder(Dining.this);
                image.setTitle("Add a dish");
                image.setMessage("Enter the amount");

                final EditText input = new EditText(Dining.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                input.setLayoutParams(lp);
                image.setView(input);

                image.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        amt=Double.parseDouble(input.getText().toString());
                        amt =(amt+2*(0.09*amt));
                        amount[5] += amt;
                        textView5.setText("₹"+round(amount[5],2));
                        total();
                    }
                });
                image.show();
            }

        });

        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder image=new AlertDialog.Builder(Dining.this);
                image.setTitle("Add a dish");
                image.setMessage("Enter the amount");

                final EditText input = new EditText(Dining.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                input.setLayoutParams(lp);
                image.setView(input);

                image.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        amt=Double.parseDouble(input.getText().toString());
                        amt =(amt+2*(0.09*amt));
                        amount[6] += amt;
                        textView6.setText("₹"+round(amount[6],2));
                        total();
                    }
                });
                image.show();
            }

        });

        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder image=new AlertDialog.Builder(Dining.this);
                image.setTitle("Add a dish");
                image.setMessage("Enter the amount");

                final EditText input = new EditText(Dining.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                input.setLayoutParams(lp);
                image.setView(input);

                image.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        amt=Double.parseDouble(input.getText().toString());
                        amt =(amt+2*(0.09*amt));
                        amount[7] += amt;
                        textView7.setText("₹"+round(amount[7],2));
                        total();
                    }
                });
                image.show();
            }

        });

        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder image=new AlertDialog.Builder(Dining.this);
                image.setTitle("Add a dish");
                image.setMessage("Enter the amount");

                final EditText input = new EditText(Dining.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                input.setLayoutParams(lp);
                image.setView(input);

                image.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        amt=Double.parseDouble(input.getText().toString());
                        amt =(amt+2*(0.09*amt));
                        amount[8] += amt;
                        textView8.setText("₹"+round(amount[8],2));
                        total();
                    }
                });
                image.show();
            }

        });

        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder image=new AlertDialog.Builder(Dining.this);
                image.setTitle("Add a dish");
                image.setMessage("Enter the amount");

                final EditText input = new EditText(Dining.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                input.setLayoutParams(lp);
                image.setView(input);

                image.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        amt=Double.parseDouble(input.getText().toString());
                        amt =(amt+2*(0.09*amt));
                        amount[9] += amt;
                        textView9.setText("₹"+round(amount[9],2));
                        total();
                    }
                });
                image.show();
            }

        });

        imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder image=new AlertDialog.Builder(Dining.this);
                image.setTitle("Add a dish");
                image.setMessage("Enter the amount");

                final EditText input = new EditText(Dining.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                input.setLayoutParams(lp);
                image.setView(input);

                image.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        amt=Double.parseDouble(input.getText().toString());
                        amt =(amt+2*(0.09*amt));
                        amount[10] += amt;
                        textView10.setText("₹"+round(amount[10],2));
                        total();
                    }
                });
                image.show();
            }

        });
        imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder image=new AlertDialog.Builder(Dining.this);
                image.setTitle("Add a dish");
                image.setMessage("Enter the amount");

                final EditText input = new EditText(Dining.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                input.setLayoutParams(lp);
                image.setView(input);

                image.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        amt=Double.parseDouble(input.getText().toString());
                        amt =(amt+2*(0.09*amt));
                        amount[11] += amt;
                        textView11.setText("₹"+round(amount[11],2));
                        total();
                    }
                });
                image.show();
            }

        });

        imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder image=new AlertDialog.Builder(Dining.this);
                image.setTitle("Add a dish");
                image.setMessage("Enter the amount");

                final EditText input = new EditText(Dining.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                input.setLayoutParams(lp);
                image.setView(input);

                image.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        amt=Double.parseDouble(input.getText().toString());
                        amt =(amt+2*(0.09*amt));
                        amount[12] += amt;
                        textView12.setText("₹"+round(amount[12],2));
                        total();
                    }
                });
                image.show();
            }

        });
        imageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder image=new AlertDialog.Builder(Dining.this);
                image.setTitle("Add a dish");
                image.setMessage("Enter the amount");

                final EditText input = new EditText(Dining.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                input.setLayoutParams(lp);
                image.setView(input);

                image.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        amt=Double.parseDouble(input.getText().toString());
                        amt =(amt+2*(0.09*amt));
                        amount[13] += amt;
                        textView13.setText("₹"+round(amount[13],2));
                        total();
                    }
                });
                image.show();
            }

        });

        imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder image=new AlertDialog.Builder(Dining.this);
                image.setTitle("Add a dish");
                image.setMessage("Enter the amount");

                final EditText input = new EditText(Dining.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                input.setLayoutParams(lp);
                image.setView(input);

                image.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        amt=Double.parseDouble(input.getText().toString());
                        amt =(amt+2*(0.09*amt));
                        amount[14] += amt;
                        textView14.setText("₹"+round(amount[14],2));
                        total();
                    }
                });
                image.show();
            }

        });
        imageView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder image=new AlertDialog.Builder(Dining.this);
                image.setTitle("Add a dish");
                image.setMessage("Enter the amount");

                final EditText input = new EditText(Dining.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                input.setLayoutParams(lp);
                image.setView(input);

                image.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        amt=Double.parseDouble(input.getText().toString());
                        amt =(amt+2*(0.09*amt));
                        amount[15] += amt;

                        textView15.setText("₹"+round(amount[15],2));
                        total();
                    }
                });
                image.show();
            }

        });

        imageView16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder image=new AlertDialog.Builder(Dining.this);
                image.setTitle("Add a dish");
                image.setMessage("Enter the amount");

                final EditText input = new EditText(Dining.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                input.setLayoutParams(lp);
                image.setView(input);

                image.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        amt=Double.parseDouble(input.getText().toString());
                        amt =(amt+2*(0.09*amt));
                        amount[16] += amt;
                        textView16.setText("₹"+round(amount[16],2));
                        total();
                    }
                });
                image.show();
            }

        });
    }

    public void total()
    {
        double sum=0;
        for(int i=1;i<=16;i++)
        {
            sum=sum+amount[i];
        }
        sum=round(sum,2);
        textView17.setText("Total Amount: ₹"+sum);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}

