package compica.woman.safety;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;


public class SafetyService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//@override
	public int OnStartCommand(Intent intent, int flags, int startId)
	{
		VoiceDemoActivity vda = new VoiceDemoActivity();
		Bundle savedInstancestate=null;
		vda.onCreate(savedInstancestate);
		return START_STICKY;
		//return START_STICKY;
	}

}
