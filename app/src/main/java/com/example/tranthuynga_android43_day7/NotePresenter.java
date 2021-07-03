package com.example.tranthuynga_android43_day7;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class NotePresenter {
    INote iNote;

    public NotePresenter(INote iNote) {
        this.iNote = iNote;
    }

    public void  chonGio(int hourOfDay, int minute){
                    Calendar calendar= Calendar.getInstance();
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm");
                    calendar.set(0,0,0,hourOfDay,minute);
                   iNote.setTextGio(simpleDateFormat.format(calendar.getTime()));
}
public void chonNgay( int year, int month, int dayOfMonth){
    Calendar calendar=Calendar.getInstance();
            calendar.set(year,month,dayOfMonth);
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
            iNote.setTextNgay(simpleDateFormat.format(calendar.getTime()));

}
public void spinner(){
    ArrayList<String> arrayList=new ArrayList<>();
    arrayList.add("Work");
    arrayList.add("Friend");
    arrayList.add("Family");
    iNote.setTextSpinner(arrayList);
}
public  void tune(int s, int mnFile, int mnDefault){
        if(s==mnFile)
            iNote.setToast("Menu_FromFile");
        if(s==mnDefault){

            String[] strings={"Nexus Tune","Winphone Tune","Peep Tune","Nokia Tune","Etc"};
            iNote.setalertDialog(strings);}
    }

}

