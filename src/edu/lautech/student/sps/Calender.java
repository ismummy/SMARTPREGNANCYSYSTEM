package edu.lautech.student.sps;

import java.util.Calendar;
import java.util.GregorianCalendar;

import sps.org.kisconsult.www.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class Calender extends Activity implements OnClickListener {
	ImageButton home, about;
	TextView edd;
	GregorianCalendar pDate;
	
	String[] month = { "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "Octomber", "November", "December" };
	String [] week = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar_activity);
		initComp();
	}
	private void initComp()
	{
		home = (ImageButton) findViewById(R.id.home);
		home.setOnClickListener(this);
		
		about = (ImageButton) findViewById(R.id.about);
		about.setOnClickListener(this);
		
		edd = (TextView) findViewById(R.id.edd);
		
		SharedPreferences pref = getSharedPreferences("DUE_DATE", 0);
		pDate = new GregorianCalendar(pref.getInt("YEAR", 0)+2014, pref.getInt("MONTH", 0), pref.getInt("DAY", 0)+1);
		pDate.add(Calendar.DAY_OF_YEAR, 280);
		String dueDate = week[pDate.get(Calendar.DAY_OF_WEEK) - 1] + ", " + month[pDate.get(Calendar.MONTH)] + " " + pDate.get(Calendar.DAY_OF_MONTH)+ ", " + pDate.get(Calendar.YEAR);
		
		edd.setText(dueDate);
	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view.getId() == R.id.home)
			startActivity(new Intent(Calender.this, Main.class));
		else if(view.getId() == R.id.about)
			startActivity(new Intent(Calender.this, About.class));
	}

}
