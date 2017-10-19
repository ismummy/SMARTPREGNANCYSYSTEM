package edu.lautech.student.sps;

import java.util.Calendar;
import java.util.GregorianCalendar;

import sps.org.kisconsult.www.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class Apointment extends Activity implements OnClickListener {
	ImageButton about, home;
	TextView appointTitle, appointDetails, edd;
	GregorianCalendar pDate, tDate;
	String[] month = { "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "Octomber", "November", "December" };
	String [] week = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	int [] appoint = {4,10,14,16,19,25,28,31,34,36,38,40,41,42};
	static int title, details;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.appointment_activity);
		initComp();
	}
	private void initComp()
	{
		about = (ImageButton) findViewById(R.id.about);
		home = (ImageButton) findViewById(R.id.home);
		
		about.setOnClickListener(this);
		home.setOnClickListener(this);
		
		appointTitle = (TextView) findViewById(R.id.appointment_title);
		appointDetails = (TextView) findViewById(R.id.appointment_details);
		
		SharedPreferences pref = getSharedPreferences("DUE_DATE", 0);
		tDate = new GregorianCalendar();
		pDate = new GregorianCalendar(pref.getInt("YEAR", 0)+2014, pref.getInt("MONTH", 0), pref.getInt("DAY", 0)+1);
		
		int weeks = (tDate.get(Calendar.YEAR) > pDate.get(Calendar.YEAR)) ? ((pDate.getActualMaximum(Calendar.WEEK_OF_YEAR) - pDate.get(Calendar.WEEK_OF_YEAR) ) + tDate.get(Calendar.WEEK_OF_YEAR)) : (tDate.get(Calendar.WEEK_OF_YEAR) - pDate.get(Calendar.WEEK_OF_YEAR));
		pDate.add(Calendar.DAY_OF_YEAR, 280);
		String dueDate = week[pDate.get(Calendar.DAY_OF_WEEK) - 1] + ", " + month[pDate.get(Calendar.MONTH)] + " " + pDate.get(Calendar.DAY_OF_MONTH)+ ", " + pDate.get(Calendar.YEAR);
		
		int a = 0;
		for(int i=0; i<appoint.length; i++)
		{
			if(appoint[i] == weeks)
			{
				a = weeks;
				break;
			}
		}
		getDetails(a);
		
		appointTitle.setText(getResources().getString(title));
		appointDetails.setText(Html.fromHtml(getResources().getString(details)));
		
		edd = (TextView) findViewById(R.id.edd);
		edd.setText(dueDate);
	}
	public static void getDetails(int week)
	{
		switch(week)
		{
		case 4: 
			details = R.string.appoint1;
			title = R.string.appoint1title;
			break;
		case 10: 
			details = R.string.appoint2;
			title = R.string.appoint2title;
			break;
		case 14: 
			details = R.string.appoint3;
			title = R.string.appoint3title;
			break;
		case 16: 
			details = R.string.appoint4;
			title = R.string.appoint4title;
			break;
		case 19: 
			details = R.string.appoint5;
			title = R.string.appoint5title;
			break;
		case 25: 
			details = R.string.appoint6;
			title = R.string.appoint6title;
			break;
		case 28: 
			details = R.string.appoint7;
			title = R.string.appoint7title;
			break;
		case 31: 
			details = R.string.appoint8;
			title = R.string.appoint8title;
			break;
		case 34: 
			details = R.string.appoint9;
			title = R.string.appoint9title;
			break;
		case 36: 
			details = R.string.appoint10;
			title = R.string.appoint10title;
			break;
		case 38: 
			details = R.string.appoint11;
			title = R.string.appoint11title;
			break;
		case 40: 
			details = R.string.appoint12;
			title = R.string.appoint12title;
			break;
		case 41: 
			details = R.string.appoint13;
			title = R.string.appoint13title;
			break;
		case 42: 
			details = R.string.appoint14;
			title = R.string.appoint8title;
			break;
		default: 
			details = R.string.appoint0;
			title = R.string.appoint0title;
			break;
		}
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0.getId() == R.id.home)
			startActivity(new Intent(Apointment.this, Main.class));
		else if(arg0.getId() == R.id.about)
			startActivity(new Intent(Apointment.this, About.class));
	}
}
