package com.takecare.backgate;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

public class From_Time extends Activity {

    TimePicker timePicker;
    TextView time_selected;
    ImageView imageButton_from;
    String[] details_array = new String[7];
    String min_str;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.from_time);

        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        details_array = getIntent().getStringArrayExtra("DETAILS");

        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        timePicker=(TimePicker)findViewById(R.id.timePicker1);

        time_selected=(TextView)findViewById(R.id.time_selected);
        String hour = Integer.toString(((timePicker.getCurrentHour().intValue())>12)?((timePicker.getCurrentHour().intValue()) - 12) : (timePicker.getCurrentHour().intValue()));
        String am_pm = get_am_pm(timePicker.getCurrentHour());
        int minute = timePicker.getCurrentMinute();
        if(hour.length()==1){
            hour="0"+hour;
        }
        if(minute<10){
            min_str="0"+String.valueOf(minute);
        }else{
            min_str=String.valueOf(minute);
        }
        time_selected.setText(" "+String.valueOf(hour)+" : "+min_str+" "+am_pm);
        details_array[1] = String.valueOf(time_selected.getText());

        imageButton_from=(ImageView)findViewById(R.id.imageButton_from);

        addListenerOnButton();
    }

    public void addListenerOnButton() {
        //Select a specific button to bundle it with the action you want

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                String hour = Integer.toString(((view.getCurrentHour().intValue()) > 12) ? ((view.getCurrentHour().intValue()) - 12) : (view.getCurrentHour().intValue()));
                minute = timePicker.getCurrentMinute();
                String am_pm = get_am_pm(timePicker.getCurrentHour());
                if(hour.length()==1){
                    hour="0"+hour;
                }
                if(minute<10){
                    min_str="0"+String.valueOf(minute);
                }else{
                    min_str=String.valueOf(minute);
                }
                time_selected.setText(" "+String.valueOf(hour)+" : "+min_str+" "+am_pm);
                details_array[1] = String.valueOf(time_selected.getText());
                //Log.d("Note :",details_array[0]);
                //Log.d("Note :",details_array[1]);
            }
        });

        imageButton_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(From_Time.this, To_Date.class);
                myIntent.putExtra("DETAILS", details_array);
                From_Time.this.startActivity(myIntent);
            }
        });
    }

    public String get_am_pm(int hour){
        if(hour>11)
            return "P.M.";
        else
            return "A.M.";
    }
}
