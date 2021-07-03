package com.example.tranthuynga_android43_day7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tranthuynga_android43_day7.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements INote{


    ActivityMainBinding activityMainBinding;
    NotePresenter notePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notePresenter=new NotePresenter(this);
        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        notePresenter.spinner();

        activityMainBinding.tvChonGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                notePresenter.chonGio(hourOfDay,minute);
                            }
                        },14,20,true);
                timePickerDialog.show();
            }
        });

        activityMainBinding.tvChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        notePresenter.chonNgay(year,month,dayOfMonth);
                    }
                },2016,02,04);
                datePickerDialog.show();
            }
        });

        activityMainBinding.tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(getBaseContext(),v);
                MenuInflater menuInflater=popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.menu,popupMenu.getMenu());
                popupMenu.show();
            }
        });
        activityMainBinding.btnTune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(getBaseContext(), v);
                MenuInflater menuInflater= popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.tune, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        notePresenter.tune(item.getItemId(), R.id.mnFile, R.id.mnDefault);
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        activityMainBinding.tvTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] strings={"Family","Game","Android","VTC", "Friend"};
                boolean[] booleans={false, false, false, false, false, false};
                List<String> chon=new ArrayList<>();
                AlertDialog alertDialog=new AlertDialog.Builder(MainActivity.this  )
                        .setTitle("Choose tags")
                        .setMultiChoiceItems(strings, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                Toast.makeText(getBaseContext(),strings[which],Toast.LENGTH_LONG).show();
                                if(isChecked){
                                    chon.add(strings[which]);
                                }
                                else {
                                    chon.remove(strings[which]);
                                }
                            }
                        })
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(chon.size()>0){
                                    activityMainBinding.tvTags.setText("");
                                    for (String s: chon
                                    ) {
                                        activityMainBinding.tvTags.append(s+", ");
                                    }
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create();
                alertDialog.show();
            }
        });
        activityMainBinding.tvWeeks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] strings={"Monday","Tuesday","Wednesday","Thursday", "Friday","Saturday","Sunday"};
                boolean[] booleans={false, false, false, false, false, false,false};
                List<String> chon=new ArrayList<>();
                AlertDialog alertDialog=new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Choose weeks: ")
                        .setMultiChoiceItems(strings, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if(isChecked){
                                    chon.add(strings[which].trim().substring(0,3));
                                }
                                else
                                    chon.remove(strings[which].trim().substring(0,3));
                            }
                        })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(chon.size()>0){
                                    activityMainBinding.tvWeeks.setText("");
                                    for (String s: chon
                                    ) {
                                        activityMainBinding.tvWeeks.append(s+", ");
                                    }
                                }

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create();
                alertDialog.show();
            }
        });
    }

    @Override
    public void setTextGio(String s) {
        activityMainBinding.tvChonGio.setText(s);
    }

    @Override
    public void setTextNgay(String s) {
        activityMainBinding.tvChonNgay.setText(s);
    }

    @Override
    public void setTextSpinner(ArrayList<String> list) {
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,list);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        activityMainBinding.spnType.setAdapter(arrayAdapter);
        activityMainBinding.spnType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, list.get(position), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void setToast(String s) {
        Toast.makeText(getBaseContext(),s,Toast.LENGTH_LONG).show();
    }

    @Override
    public void setalertDialog(String[] strings) {
        AlertDialog alertDialog=new AlertDialog.Builder(MainActivity.this)
                .setTitle("Choose Tune")
                .setSingleChoiceItems(strings, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(),strings[which], Toast.LENGTH_LONG).show();
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
        alertDialog.show();
    }
}