 package compica.woman.safety;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
//import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.Uri;
//import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.speech.RecognizerIntent;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
//import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
public class VoiceDemoActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
 
    static final int check=1111;
    ListView lv;
    SQLiteDatabase db=null;
    Context mcontext=VoiceDemoActivity.this;
    
	
	Button btn;
	   @Override
    public void onCreate(Bundle savedInstanceState) {
    	
/*    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    //    lv=(ListView) findViewById(R.id.lvVoice);
        btn=(Button) findViewById(R.id.btnVoice);
      
*/      
		  // Registration reg=new Registration();
		//startActiSvity(new Intent(getBaseContext(),Registration.class ));
		 ///  reg.onCreate(savedInstanceState);
		   mcontext=VoiceDemoActivity.this;
        //PackageManager pm=getPackageManager();
        
    }

	   public void StartHelp()
	   {
		   Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
			i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL , RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
			i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello");
			startActivityForResult(i, check);
			
		   
	   }
	   public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL , RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello");
		startActivityForResult(i, check);
		
		/*String username="Chirag";//fetch it from database
		String location="Changa"; //find the geocode;
		//	lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, results));
		//setting the flag to check whether voice is matched or not
		Boolean matched =false;
		String voice_text="help";
		
		//db= SQLiteDatabase.openDatabase(), null, SQLiteDatabase.OPEN_READONLY);
	//	db= SQLiteDatabase.openDatabase("/sdcard/android/data/cmpica/ws", null, SQLiteDatabase.OPEN_READONLY);
		db= SQLiteDatabase.openOrCreateDatabase("ws",null);
		db.execSQL("create table Registration (Reg_ID primary key autoincrement, Firs	`3tName text, LastName text, " +
				"Voice_text text, ec_phone_1 text,ec_phone_2 text,ec_phone_3 text,ec_phone_4 text,ec_phone_5 text) ");
		db.execSQL("Insert into Registration (FirstName, LastName, Voice_text, ec_phone_1, ec_phone_2, ec_phone_3" +
				"ec_phone_4, ec_phone_5) values ('Chirag','Patel','help','66','66','66','88', '9925446618')");
		//forming query string - select query
			String query="Select * from Registration";
			Cursor c=db.rawQuery(query, null); 
			//getting number of records;
		
			c.moveToFirst();
			voice_text=c.getString(c.getColumnIndex("voice_text"));
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
		
		// 
		//if(matched==true)
	//	{
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
			String contacts;
			for (int cont_ind=3; cont_ind <=5; cont_ind++ ) 
			{
				contacts = c.getString(cont_ind);
			//String contacts="9429032148";	
			Toast.makeText(this, "contacts", Toast.LENGTH_SHORT ).show();
				Intent callIntent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +contacts));;
				startActivity(callIntent);
			//sending sms
			//db connect find emergency numbers and then send sms using loop
			//following code is hardcoded for testing
				@SuppressWarnings("deprecation")
				SmsManager sendSms = SmsManager.getDefault();
			}*/
		
		}
	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
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
					sendSms.sendTextMessage(contacts, null, username + " is in problem and right now he/she is at " + location, null, null); 
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
	
}