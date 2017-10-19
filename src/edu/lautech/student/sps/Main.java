package edu.lautech.student.sps;

import android.os.Bundle;

import java.util.*;

import sps.org.kisconsult.www.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class Main extends Activity implements OnClickListener {

	ImageButton calendarButton, appointButton, developButton, about;
	TextView todayDate, pWeeks, edd, note;
	GregorianCalendar tDate, pDate;
	String[] month = { "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "Octomber", "November", "December" };
	String [] week = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	int [] appoint = {4,10,14,16,19,25,28,31,34,36,38,40,41,42};
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComp();
    }
	
	private void initComp()
	{
		calendarButton = (ImageButton) findViewById(R.id.calendarButton);
		appointButton = (ImageButton) findViewById(R.id.appointmentButton);
		developButton = (ImageButton) findViewById(R.id.developmentButton);
		about = (ImageButton) findViewById(R.id.about);
		
		calendarButton.setOnClickListener(this);
		appointButton.setOnClickListener(this);
		developButton.setOnClickListener(this);
		about.setOnClickListener(this);
		
		//set Date
		SharedPreferences pref = getSharedPreferences("DUE_DATE", 0);
		tDate = new GregorianCalendar();
		pDate = new GregorianCalendar(pref.getInt("YEAR", 0)+2014, pref.getInt("MONTH", 0), pref.getInt("DAY", 0)+1);
		
		int weeks = (tDate.get(Calendar.YEAR) > pDate.get(Calendar.YEAR)) ? ((pDate.getActualMaximum(Calendar.WEEK_OF_YEAR) - pDate.get(Calendar.WEEK_OF_YEAR) ) + tDate.get(Calendar.WEEK_OF_YEAR)) : (tDate.get(Calendar.WEEK_OF_YEAR) - pDate.get(Calendar.WEEK_OF_YEAR));
		int days = (tDate.get(Calendar.YEAR) > pDate.get(Calendar.YEAR)) ? ((pDate.getActualMaximum(Calendar.DAY_OF_YEAR) - pDate.get(Calendar.DAY_OF_YEAR) ) + tDate.get(Calendar.DAY_OF_YEAR)) : (tDate.get(Calendar.DAY_OF_YEAR) - pDate.get(Calendar.DAY_OF_YEAR));
		pDate.add(Calendar.DAY_OF_YEAR, 280);
		String dueDate = week[pDate.get(Calendar.DAY_OF_WEEK) - 1] + ", " + month[pDate.get(Calendar.MONTH)] + " " + pDate.get(Calendar.DAY_OF_MONTH)+ ", " + pDate.get(Calendar.YEAR);
		
		
		//setTOday date
		
		todayDate = (TextView) findViewById(R.id.todayDate);
		todayDate.setText(week[tDate.get(Calendar.DAY_OF_WEEK) - 1] + ", " + month[tDate.get(Calendar.MONTH)] + " " + tDate.get(Calendar.DAY_OF_MONTH)+ ", " + tDate.get(Calendar.YEAR));
		
		//set weeks
		pWeeks = (TextView) findViewById(R.id.weeks);
		pWeeks.setText(weeks + " week(s), " +( days % 7 ) + " day(s)");
		
		//set edd
		edd = (TextView) findViewById(R.id.edd);
		edd.setText(dueDate);
		
		//set notice
		note = (TextView) findViewById(R.id.note);
		
		for(int i=0; i<appoint.length; i++)
		{
			if(appoint[i] == weeks)
			{
				note.setText(R.string.appointment);
				break;
			}
		}
	}
    
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view.getId() == R.id.calendarButton)
			startActivity(new Intent(Main.this, Calender.class));
		else if(view.getId() == R.id.appointmentButton)
			startActivity(new Intent(Main.this, Apointment.class));
		else if(view.getId() == R.id.developmentButton)
			startActivity(new Intent(Main.this, Development.class));
		else if(view.getId() == R.id.about)
			startActivity(new Intent(Main.this, About.class));
	}
    
}
