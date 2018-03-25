package com.example.mejdo.myisam.fragments.list;

/**
 * Created by lenovo on 21/03/2018.
 */


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mejdo.myisam.fragments.add.AddEventFragment;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {


    public TimePickerFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker

        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);


        return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener)getActivity(), hour, minute,
                DateFormat.is24HourFormat(getActivity()));

     }



        // Create a new instance of TimePickerDialog and return it



    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Toast.makeText(getActivity(),"heur"+hourOfDay+":"+minute,Toast.LENGTH_LONG).show();
        AddEventFragment Add = new AddEventFragment();
  /*   Bundle bundle = new Bundle();
        bundle.putInt("he", hourOfDay);
        Add.setArguments(bundle);*/

        // Do something with the time chosen by the user
    }
}