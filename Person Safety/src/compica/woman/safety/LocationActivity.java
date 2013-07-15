package compica.woman.safety;
//package cmpica.woma.safety;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
//import android.view.Menu;
import android.widget.*;
import android.location.Address;

public class LocationActivity extends Activity {
	//String Ct;
    
    //String provider=LocationManager.GPS_PROVIDER;
    //LocationManager lm;
    Location currentlocation=null;
    String latLongString=null;
    //TextView tvAddress;
 
    String addressString = "No addres found";
    double lat=0;
	double lng=0;
	StringBuilder sb=null;
	double lat_long[]=new double[2];
			//LatLong
	//@Override
public LocationActivity(){}
public double[] InitLocation(LocationManager lm)
{
	//setContentView(R.layout.a ctivity_location);
		//Ct= Context.LOCATION_SERVICE;
		//lm=(LocationManager) getSystemService(Ct);
		
		//tvAddress=(TextView) findViewById(R.id.tvAddress); 
		/* try
		    {
		    	currentlocation = lm.getLastKnownLocation(provider);
		    }catch(Exception e){System.out.println(e.getMessage());}
		*/           
        	LocationListener locationlistener = new LocationListener() {
				
				@Override
				public void onStatusChanged(String provider, int status, Bundle extras) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onProviderEnabled(String provider) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onProviderDisabled(String provider) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onLocationChanged(Location location) {
					// TODO Auto-generated method stub
					updateLocation(location);
				}
			};
		
	
		Location location=lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			updateLocation(location);
		
	//	try
		//{
			String allowedprovider=LocationManager.GPS_PROVIDER;
			//Settings.Secure.a
			lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationlistener);        	
		//}catch(Exception e){Toast.makeText(this, "GPS is no enabled please enable it", Toast.LENGTH_LONG).show(); System.exit(0); }
			//FindLocation();
			try{
			
					lat_long[0]=lat;
					lat_long[1]=lng;

			}catch(Exception e){Log.d("error latlong", e.getMessage());} 	
			return lat_long;
        	/* Geocoder gc=null;
	        if (location!=null)
	        {
	        
	        	try{
	        		
	        	
	        	 lat=location.getLatitude();
	        	 lng=location.getLongitude();
	    
	        	 latLongString="Lat:" + lat + "\nLong:" + lng;
	        	//Geocoder gc= new Geocoder(this, Locale.getDefault());
	     	 gc= new Geocoder(getBaseContext(), Locale.getDefault());
	  //      	Geocoder gc= new Geocoder(this);
	        	
	        	tvAddress.setText("Your current position is:\n" + latLongString + "\n" + addressString);
	        	}catch (Exception e){ System.out.println(e.getMessage());}
	        	try
	        	{
	      	List <Address> addresses = gc.getFromLocation(lat, lng, 1);
	        		
	  //      	List <Address> addresses = gc.getFromLocation(tou, lng, 1);

	        	StringBuilder sb=new StringBuilder();
	        	if (addresses.size() > 0)
	        	{
	        		Address address= addresses.get(0);
	        		for(int i=0;i<address.getMaxAddressLineIndex();i++)
	        		{
	        			sb.append(address.getAddressLine(i)).append("\n");
	        		}
	        		sb.append(address.getLocality()).append("\n");
	        		sb.append(address.getPostalCode()).append("\n");
	        		sb.append(address.getCountryName()).append("\n");
	        	}		
	        	addressString=sb.toString();
	        	}catch(IOException e){
	        		Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
	        		//Log.d("a",e.getMessage());
	        	}
	        	
	        }
	        else 
	        {
	        	latLongString = "location not found";
	        }*/
	       // tvAddress.setText("Your current position is:\n" + latLongString + "\n"
	        //		+ addressString);
	}
	String FindLocation(double lati, double longi, Geocoder gc)
	{
		try{
		//Geocoder gc= new Geocoder(getBaseContext(), Locale.getDefault());
	//	lat=lm.getLastKnownLocation(LocationManager.GPS_PROVIDER).getAltitude();
		//lng=lm.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude();
		List <Address> addresses = gc.getFromLocation(lati,longi, 100);
		
	 sb=new StringBuilder();
    	if (addresses.size() > 0)
    	{
    		Address address= addresses.get(0);
    		for(int i=0;i<address.getMaxAddressLineIndex();i++)
    		{
    			sb.append(address.getAddressLine(i)).append("\n");
    		}
    		sb.append(address.getLocality()).append("\n");
    		sb.append(address.getPostalCode()).append("\n");
    		sb.append(address.getCountryName()).append("\n");
    	}		
    	addressString=sb.toString();
		
		
		
		
		}catch(Exception e){Log.d("Into find",e.getMessage()); addressString=e.getMessage();return addressString;}
		return addressString;
 	
 }
	void updateLocation(Location location)
	{
	
		try
		{
			currentlocation=location;
		//lat=currentlocation.getLatitude()/1E6;
		//lng=currentlocation.getLongitude()/1E6;
			lat=currentlocation.getLatitude();
			lng=currentlocation.getLongitude();
			latLongString=lat + "\n" + lng;
		}catch(Exception ex)
		{
			latLongString="Not able to get the location"; 
					
		}
		
		
	
	}


}
