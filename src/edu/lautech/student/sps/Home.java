package edu.lautech.student.sps;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import sps.org.kisconsult.www.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

public class Home extends Activity implements OnClickListener {
	Spinner matYear, matMonth, matDay, mesYear, mesMonth, mesDay;
	String[] month = { "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "Octomber", "November", "December" };
	String[] year, day;
	Button button;
	ImageButton about;
	GregorianCalendar pDate, tDate;
	int [] appoint = {4,10,14,16,19,25,28,31,34,36,38,40,41,42};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);
		
		initComp();
		
		// last mating date
		// last day of mestration date
		// check if conceive
		// if conceive move to main activity else display a dialog
		// ArrayAdapter<String> adapter = new
	}

	private void initComp() {
		mesYear = (Spinner) findViewById(R.id.mesYear);
		mesMonth = (Spinner) findViewById(R.id.mesMonth);
		mesDay = (Spinner) findViewById(R.id.mesDay);
		
		matYear = (Spinner) findViewById(R.id.matYear);
		matMonth = (Spinner) findViewById(R.id.matMonth);
		matDay = (Spinner) findViewById(R.id.matDay);
		
		year = new String[31];
		day = new String[31];
		
		for(int i=0; i<31; i++)
		{
			day[i] = "" +( i+1);
			year[i] = "" + (2014+i);
		}
		
		ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, year);
		ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, month);
		ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, day);
		
		mesYear.setAdapter(yearAdapter);
		mesMonth.setAdapter(monthAdapter);
		mesDay.setAdapter(dayAdapter);
		
		matYear.setAdapter(yearAdapter);
		matMonth.setAdapter(monthAdapter);
		matDay.setAdapter(dayAdapter);
		
		SharedPreferences settings = getSharedPreferences("DUE_DATE",0);
		matYear.setSelection(settings.getInt("YEAR", 0));
		matMonth.setSelection(settings.getInt("MONTH", 0));
		matDay.setSelection(settings.getInt("DAY", 0));
		
		mesYear.setSelection(settings.getInt("MES_YEAR",0));
		mesMonth.setSelection(settings.getInt("MES_MONTH", 0));
		mesDay.setSelection(settings.getInt("MES_DAY", 0));
		
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(this);
		
		about = (ImageButton) findViewById(R.id.about);
		about.setOnClickListener(this);
	}
	private void conceive()
	{
		SharedPreferences settings = getSharedPreferences("DUE_DATE",0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putInt("YEAR",
				matYear.getSelectedItemPosition());
		editor.putInt("MONTH", matMonth.getSelectedItemPosition());
		editor.putInt("DAY",
				matDay.getSelectedItemPosition());
		
		editor.putInt("MES_YEAR", mesYear.getSelectedItemPosition());
		editor.putInt("MES_MONTH", mesMonth.getSelectedItemPosition());
		editor.putInt("MES_DAY", mesDay.getSelectedItemPosition());
		editor.commit();
		
		ProgressDialog bar = new ProgressDialog(this);
		bar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		bar.setIndeterminate(true);
		bar.setCancelable(false);
		bar.setMessage("Finalizing...");
		bar.show();
		try {
			Random rand= new Random();
				Thread.sleep(200 + rand.nextInt(500));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		startActivity(new Intent(Home.this, Main.class));
		this.finish();
	}
	private void notConceive()
	{
		AlertDialog.Builder ad = new AlertDialog.Builder(this);
		ad.setCancelable(true);
		ad.setMessage("Oppsh! You're not pregnant Medically");
		ad.setPositiveButton("Ok!", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				arg0.cancel();
			}
		});
		AlertDialog alert = ad.create();
		alert.show();
	}
	private void status(int week)
	{
		Apointment.getDetails(week);
		NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		NotificationCompat.Builder notification = new NotificationCompat.Builder(this);
		notification.setAutoCancel(true);
		notification.setTicker("Smart Pregnancy System, Antenatal Notification");
		notification.setSmallIcon(R.drawable.icon);
		long [] vibrate = {0,100,200,300,400,500};
		notification.setVibrate(vibrate);
		Uri sound = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.beep);
		notification.setSound(sound);
		notification.setLights(Color.BLUE, 100, 1000);
		CharSequence title = getResources().getString(Apointment.title);
		CharSequence details = Html.fromHtml(getResources().getString(Apointment.details)).toString().substring(0, 50) + ".....";
		notification.setContentText(details);
		notification.setContentTitle(title);
		Intent intent = new Intent(this, Apointment.class);
		PendingIntent pending = PendingIntent.getActivity(this, 0, intent, 0);
		notification.setContentIntent(pending);
		nm.notify(week, notification.build());
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		SharedPreferences settings = getSharedPreferences("DUE_DATE",0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putInt("YEAR",
				matYear.getSelectedItemPosition());
		editor.putInt("MONTH", matMonth.getSelectedItemPosition());
		editor.putInt("DAY",
				matDay.getSelectedItemPosition());
		
		editor.putInt("MES_YEAR", mesYear.getSelectedItemPosition());
		editor.putInt("MES_MONTH", mesMonth.getSelectedItemPosition());
		editor.putInt("MES_DAY", mesDay.getSelectedItemPosition());
		editor.commit();
		
		SharedPreferences pref = getSharedPreferences("DUE_DATE", 0);
		tDate = new GregorianCalendar();
		pDate = new GregorianCalendar(pref.getInt("YEAR", 0)+2014, pref.getInt("MONTH", 0), pref.getInt("DAY", 0)+1);
		
		int weeks = (tDate.get(Calendar.YEAR) > pDate.get(Calendar.YEAR)) ? ((pDate.getActualMaximum(Calendar.WEEK_OF_YEAR) - pDate.get(Calendar.WEEK_OF_YEAR) ) + tDate.get(Calendar.WEEK_OF_YEAR)) : (tDate.get(Calendar.WEEK_OF_YEAR) - pDate.get(Calendar.WEEK_OF_YEAR));
		
		for(int i=0; i<appoint.length; i++)
		{
			if(appoint[i] == weeks)
			{
				status(weeks);
				break;
			}
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0.getId() == R.id.button)
		{
		if(matYear.getSelectedItemPosition() > mesYear.getSelectedItemPosition())
		{
			conceive();
		}
		else if(matYear.getSelectedItemPosition() == mesYear.getSelectedItemPosition())
		{
			if(matMonth.getSelectedItemPosition() > mesMonth.getSelectedItemPosition())
			{
				conceive();
			}
			else if(matMonth.getSelectedItemPosition() == mesMonth.getSelectedItemPosition())
			{
				if(matDay.getSelectedItemPosition() > mesDay.getSelectedItemPosition())
				{
					conceive();
				}
				else
				{
					notConceive();
				}
			}
			else
			{
				notConceive();
			}
		}
		else
		{
			notConceive();
		}
		}
		else if(arg0.getId() == R.id.about)
		{
			startActivity(new Intent(Home.this, About.class));
		}
	}
}
