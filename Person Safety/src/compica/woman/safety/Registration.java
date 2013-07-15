package compica.woman.safety;

//import cmpica.woman.safety.R;
import java.util.ArrayList;
import java.util.Locale;

import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.speech.RecognizerIntent;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Registration extends Activity {

	//@Override
	 static final int check=1111;
	    ListView lv;
	    SQLiteDatabase db=null;
//checking whether table or database exists or not
	    boolean noData=false;
	    
	    Context mcontext=Registration.this;
	    boolean validated=false;
	    //SQLiteDatabase db=null;
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		Button b1=(Button) findViewById(R.id.btnSave);
		///Button b2=(Button) findViewById(R.id.btnUpdate);
		Button b3=(Button) findViewById(R.id.btnCancel);
		Button b4=(Button) findViewById(R.id.btnStart);
		DisplayData();
		b4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			startService(new Intent(getBaseContext(),SafetyService.class));	
			}
		});
		//String fname,lname,voice_text,phone1,phone2,phone3,phone4,phone5;
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				SaveData();
				// TODO Auto-generated method stub
				
			}
		});
	
		b3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			  System.exit(0);	
			}
		});
		b4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//VoiceDemoActivity vda =new VoiceDemoActivity();
			//	vda.StartHelp();
				if (noData==false)
				{
					StartHelp();
				}
				else
				{
					AlertDialog.Builder alertDialog=new AlertDialog.Builder(mcontext);
					alertDialog.setTitle("Data does not exists");
					alertDialog.setMessage("Enter your personal details and atleast one emergency Phone Number. After that you can start emergency help service");
					alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
											
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					});
					alertDialog.show();
				}
			}
		});
		//b2=findViewById(R.id.btnUpdate);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration, menu);
		return true;
	}
	public void StartHelp()
	{
		Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL , RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello");
		startActivityForResult(i, check);
	
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode==check && resultCode==RESULT_OK)
		{
			
			ArrayList <String> results =data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			String username="Chirag";//fetch it from database
			String location="Changa"; //find the geocode;
			//	lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, results));
			//setting the flag to check whether voice is matched or not
			Boolean matched =false;
			String voice_text="help";
			String path;
			//db= SQLiteDatabase.openDatabase(), null, SQLiteDatabase.OPEN_READONLY);
		//	db= SQLiteDatabase.openDatabase("/sdcard/android/data/cmpica/ws", null, SQLiteDatabase.OPEN_READONLY);
			try
			{
				//Environment.
				path=Environment.getExternalStorageDirectory().getPath() + "/ws";
		//db= SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READONLY);
		db= SQLiteDatabase.openOrCreateDatabase(path,null);//,SQLiteDatabase.OPEN_READONLY);
		}catch(SQLiteException e){Log.d("errror", e.getMessage());}		
			//try
			//{
			/*db.execSQL("create table Registration (Reg_ID primary key autoincrement, FirstName text, LastName text, " +
		//}
					"Voice_text text, ec_phone_1 text,ec_phone_2 text,ec_phone_3 text,ec_phone_4 text,ec_phone_5 text) ");
			db.execSQL("Insert into Registration (FirstName, LastName, Voice_text, ec_phone_1, ec_phone_2, ec_phone_3" +
					"ec_phone_4, ec_phone_5) values ('Chirag','Patel','help','66','66','66','88', '9925446618')");*/
			//forming query string - select query
			///}catch(SQLiteException e){Log.d("db creation err:", e.getMessage());}
			Cursor c=null;
			String query=null;
			try
			{
			query="Select * from Registration";
				c=db.rawQuery(query, null); 
			}catch(Exception e){Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();}
			
				//getting number of records;
			
				c.moveToFirst();
				voice_text=c.getString(c.getColumnIndex("Voice_text"));
				username=c.getString(c.getColumnIndex("FirstName")) + " "+ c.getString(c.getColumnIndex("LastName"));
			for (int i=0; i<results.size();i++)
			{
				if (results.get(i).equalsIgnoreCase(voice_text))
				{
					//Write logic for call with db connectivity
					
					
					//temporary
					Toast.makeText(this, "voice is matched", Toast.LENGTH_LONG ).show();
					matched =true;
									
				//}
					break;
					
				}
				else
				{
					// voice is not matched				
					//Toast.makeText(this, "sorry voice is not matched", Toast.LENGTH_SHORT ).show();
					//break;
				}
			}
			
			// if voice is matched then call to emergency numbers 
			if(matched==true)
		{
				//db= SQLiteDatabase.openDatabase("/data/data/cmpica.woman.safety/Womansafety", null, SQLiteDatabase.OPEN_READONLY);
				//forming query string - select query
					query="Select * from Registration";
					 c=db.rawQuery(query, null); 
					//getting number of records;
				
					c.moveToFirst();
				//String Emergency_nos[] =new String[5];
				//Declaring string to store emergency call details
				//String Emergency_call="999";
				//declaring index to search through all emergency contact numbers
			//	int contact_ind=0;
				//setting call
				//following code should be written in loop
				//in
				
				//Fetching username from the database
					username=c.getString(c.getColumnIndex("FirstName")) + " " + c.getString(c.getColumnIndex("LastName"));
				//Fetching location of user from location activity class
					String Ct= Context.LOCATION_SERVICE;
					LocationManager lm;
					lm=(LocationManager) getSystemService(Ct);
					if(!lm.isProviderEnabled(LocationManager.GPS_PROVIDER))
					{
			
						GPSTracker gps=new GPSTracker(mcontext);
						gps.showSettingsAlert();
					/*	AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		 
		        // Setting Dialog Title
		        alertDialog.setTitle("Error");
		 
		        // Setting Dialog Message
		        alertDialog.setMessage("GPS is not enabled please enable GPS and restart application");
		 
		        // Setting Icon to Dialog
		       // alertDialog.setIcon(R.drawable.tick);
		 
		        // Setting OK Button
		        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int which) {
		                // Write your code here to execute after dialog closed
		                System.exit(0);
		                }
		        });
		 
		*/        // Showing Alert Message
		       
						//alertDialog.show();
						
						
						
						
						
						
						
						
						
						//AlertDialog ad=new AlertDialog()
			//ad.setMessage("GPS is not enabled please enable GPS and restart application");
			
						//Toast.makeText(this, "GPS is not enabled please enable GPS and restart application", Toast.LENGTH_SHORT).show();
						//try{Thread.sleep(5000, 500);}catch(Exception e){}
						//System.exit(0);
					}
					else
					{
					 double lat_long[]=new double[2];
					//getting latitude
					LocationActivity la=null;
					try
					{
						la=new LocationActivity();
						lat_long=la.InitLocation(lm);
					}catch(Exception e){Log.d("Locaton activity:",e.getMessage());}
					try{
						//getting latitude
						double lati=lat_long[0];
						//getting longitude
						double longi=lat_long[1];
						//Getting geocoder
						Geocoder gc= new Geocoder(this, Locale.getDefault());
						
						location=la.FindLocation(lati, longi,gc);
					}catch(Exception e){Log.d("Locaton activity:",e.getMessage());}
				String contacts;
				
				
				
				//for (int cont_ind=3; cont_ind <=5; cont_ind++ ) 
				//{
				//forming contact number for retrieving it from database
				
				for (int i=1 ;i <=5;i++)
				{
					String contact_name="ec_phone_" + i;
					contacts=c.getString(c.getColumnIndex(contact_name));	
				//contacts = c.getString(cont_ind);
				//String contacts="9429032148";	
					//Temporary loop for testing purpose
				///	if (contacts.equalsIgnoreCase("9825626845"))
					//{
					Toast.makeText(this, "contacts", Toast.LENGTH_SHORT ).show();
					//Intent callIntent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +contacts));;
					//startActivity(callIntent);
				//sending sms
				//db connect find emergency numbers and then send sms using loop
				//following code is hardcoded for testing
					@SuppressWarnings("deprecation")
					SmsManager sendSms = SmsManager.getDefault();
					//temporary disabling for testing purpose
					if (contacts.length()!=0)
					{
						sendSms.sendTextMessage(contacts, null, username + " is in problem and right now he/she is at " + location, null, null); 
					}
					//}
					//sendSms.sendTextMessage(Emergency_nos[contact_ind], null, username + " is in problem and right now he/she is at " + location, null, null);
				//sendSms.sendTextMessage("", scAddress, text, sentIntent, deliveryIntent)
				//Intent smsIntent=new Intent (Intent.ACTION_SEND,Uri.parse("sms:9099921270"));
				//smsIntent.putExtra("sms_body", username + " is in problem and right now he/she is at " + location);
				//startActivity(smsIntent);
				}
			}//
		//	System.exit(0);
			//lv.ges
		}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	public void SaveData()
	{
	

		String path;
	EditText etfname,etlname, etvoicetext,etp1,etp2,etp3,etp4,etp5;
	etfname=(EditText) findViewById(R.id.etFname);
	etlname=(EditText) findViewById(R.id.etLname);
	etvoicetext=(EditText) findViewById(R.id.etHelp_text);
	etp1=(EditText) findViewById(R.id.etPhone1);
	etp2=(EditText) findViewById(R.id.etPhone2);
	etp3=(EditText) findViewById(R.id.etPhone3);
	etp4=(EditText) findViewById(R.id.etPhone4);
	etp5=(EditText) findViewById(R.id.etPhone5);
	
	AlertDialog.Builder alertDialog =new AlertDialog.Builder(mcontext);
	alertDialog.setTitle("Error");
	boolean v1,v2,v3;
	if (etfname.getText().length()==0 && etlname.getText().length()==0)
	{
		alertDialog.setMessage("First Name or Last Name can't be blank, Please enter your First Name or Last Name");
		alertDialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
	         public void onClick(DialogInterface dialog,int which) {
	         	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	         	//mContext.startActivity(intent);
	         }
	     });
		alertDialog.setCancelable(true);
		alertDialog.show();
		v1=false;
	}
	else
	{
		v1=true;
	}
	if (etvoicetext.getText().length()==0)
	{
		v2=false;
		alertDialog.setMessage("Please enter help text");
		alertDialog.setCancelable(true);
	
	 alertDialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog,int which) {
         	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
         	//mContext.startActivity(intent);
         }
     });
	 alertDialog.show();
	
	}
	else
	{
		v2=true;
	}
	if (etp1.getText().length() == 0 &&etp2.getText().length()==0 && etp3.getText().length()==0 && etp4.getText().length()==0 && etp5.getText().length()==0 )
	{
		v3=false;
		alertDialog.setMessage("Please enter atleast one emergency contact number");
		alertDialog.setCancelable(true);
	
	 alertDialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog,int which) {
         	//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
         	//mContext.startActivity(intent);
         }
     });
	 alertDialog.show();
   }
	else
	{
		v3=true;
	}
	
	if (v1==false || v2==false || v3==false)
	{
		validated=false;
	}
	
	if (validated==true)
	{
	
		//db= SQLiteDatabase.openDatabase(), null, SQLiteDatabase.OPEN_READONLY);
	//	db= SQLiteDatabase.openDatabase("/sdcard/android/data/cmpica/ws", null, SQLiteDatabase.OPEN_READONLY);
		try
		{
			//Environment.
			path=Environment.getExternalStorageDirectory().getPath() + "/ws";
	//db= SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READONLY);
	db= SQLiteDatabase.openOrCreateDatabase(path,null);
	
	}catch(SQLiteException e){Log.d("errror", e.getMessage());}		
	
		try
		{
			//Storing whether table exists or not
			boolean table_exists=true;
			try
			{
				
			Cursor c=db.rawQuery("select * from registration", null);
					
			}catch(Exception e){Log.d("prob",e.getMessage());table_exists=false;}
			
			//create the table if it does not exists
			if (table_exists==false)
			{
				db.execSQL("create table Registration (Reg_ID INTEGER primary key autoincrement, FirstName text, LastName text, " +
					
	
				"Voice_text text, ec_phone_1 text,ec_phone_2 text,ec_phone_3 text,ec_phone_4 text,ec_phone_5 text) ");
			}
			db.execSQL("delete from Registration");	
			String query="Insert into Registration (FirstName, LastName, Voice_text, ec_phone_1, ec_phone_2, ec_phone_3," +
				"ec_phone_4, ec_phone_5) values ('" + etfname.getText() + "','" +etlname.getText() + "','" +
				etvoicetext.getText()  + "','" + etp1.getText()  + "','" + etp2.getText()  + "','" +etp3.getText()+
				  "','"+ etp4.getText()  + "','" +etp5.getText() +"')";
		
		db.execSQL(query); 
		
		
		//
		Toast.makeText(getBaseContext(), "Your Preferences are saved successfully", Toast.LENGTH_SHORT).show();
		
				
				//forming query string - select query
		}catch(SQLiteException e){Log.d("db creation err:", e.getMessage());}
	}
	}
	public void DisplayData()
	{
	

		String path;
	EditText etfname,etlname, etvoicetext,etp1,etp2,etp3,etp4,etp5;
	etfname=(EditText) findViewById(R.id.etFname);
	etlname=(EditText) findViewById(R.id.etLname);
	etvoicetext=(EditText) findViewById(R.id.etHelp_text);
	etp1=(EditText) findViewById(R.id.etPhone1);
	etp2=(EditText) findViewById(R.id.etPhone2);
	etp3=(EditText) findViewById(R.id.etPhone3);
	etp4=(EditText) findViewById(R.id.etPhone4);
	etp5=(EditText) findViewById(R.id.etPhone5);
	
		//db= SQLiteDatabase.openDatabase(), null, SQLiteDatabase.OPEN_READONLY);
	//	db= SQLiteDatabase.openDatabase("/sdcard/android/data/cmpica/ws", null, SQLiteDatabase.OPEN_READONLY);
		try
		{
			//Environment.
			path=Environment.getExternalStorageDirectory().getPath() + "/ws";
	//db= SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READONLY);
	db= SQLiteDatabase.openOrCreateDatabase(path,null);
	
	}catch(SQLiteException e){
		
		Log.d("errror", e.getMessage());
		noData=true;
		
	}		
	
		try
		{
			//Storing whether table exists or not
			boolean table_exists=true;
			Cursor c=null;
			try
			{
				
				c=db.rawQuery("select * from registration", null);
			
	
					
			}catch(Exception e){Log.d("prob",e.getMessage());table_exists=false; noData=true;}
			
			//create the table if it does not exists
			if (table_exists==false)
			{
				db.execSQL("create table Registration (Reg_ID INTEGER primary key autoincrement, FirstName text, LastName text, " +
					
	
				"Voice_text text, ec_phone_1 text,ec_phone_2 text,ec_phone_3 text,ec_phone_4 text,ec_phone_5 text) ");
			}
			else
			{	
				c.moveToFirst();
				etfname.setText(c.getString(c.getColumnIndex("FirstName")));
				etlname.setText(c.getString(c.getColumnIndex("LastName")));
				etvoicetext.setText(c.getString(c.getColumnIndex("Voice_text")));
				etp1.setText(c.getString(c.getColumnIndex("ec_phone_1")));
				etp2.setText(c.getString(c.getColumnIndex("ec_phone_2")));
				etp3.setText(c.getString(c.getColumnIndex("ec_phone_3")));
				etp4.setText(c.getString(c.getColumnIndex("ec_phone_4")));
				etp5.setText(c.getString(c.getColumnIndex("ec_phone_5")));
				
				
				
				//c.getCount();
			}
			/*db.execSQL("delete from Registration");	
			String query="Insert into Registration (FirstName, LastName, Voice_text, ec_phone_1, ec_phone_2, ec_phone_3," +
				"ec_phone_4, ec_phone_5) values ('" + etfname.getText() + "','" +etlname.getText() + "','" +
				etvoicetext.getText()  + "','" + etp1.getText()  + "','" + etp2.getText()  + "','" +etp3.getText()+
				  "','"+ etp4.getText()  + "','" +etp5.getText() +"')";
		
		db.execSQL(query); */
		
		
		//
		//Toast.makeText(getBaseContext(), "Your Preferences are saved successfully", Toast.LENGTH_SHORT).show();
		
				
				//forming query string - select query
		}catch(SQLiteException e){Log.d("db creation err:", e.getMessage()); noData=true;}
	}
	
}

