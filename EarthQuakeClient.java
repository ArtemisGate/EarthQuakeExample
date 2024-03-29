package main;



import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
       
        
        for(QuakeEntry qe : quakeData) 
        {
        	if(qe.getMagnitude() > magMin) 
        	{
        		answer.add(qe);
        	}
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        
        for(QuakeEntry qe: quakeData) 
        {
        	Location current = qe.getLocation();
        	
        	if(current.distanceTo(from) < distMax) 
        	{
        		answer.add(qe);
        	}
        	
        }
        
        
        
        
        return answer;
        
        
        
    }

    
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, 
    		double minDepth, double maxDepth) 
    {
    	ArrayList<QuakeEntry>  ans = new ArrayList<QuakeEntry>();
    	
    	
    	for(QuakeEntry k: quakeData) 
    	{
    		if(k.getDepth() > minDepth && k.getDepth() < maxDepth) 
    		{
    			ans.add(k);
    		}
    	}
    	
    	 
    	return ans;
    }

    
    public void quakeOfDepth()
    {
    	
    	EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "Resources\\nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> depths = filterByDepth(list,  -10000.0, -5000.0);
        		
        		
        for(QuakeEntry qe: depths) 
        {
        	System.out.println(qe);
        }
        
        System.out.println("Found " + depths.size());
    	
    	
    	
    }
     
     
     
    
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
//        for(QuakeEntry qe : list){
//            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
//                qe.getLocation().getLatitude(),
//                qe.getLocation().getLongitude(),
//                qe.getMagnitude(),
//                qe.getInfo());
//        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "Resources\\nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        
        ArrayList<QuakeEntry> bigList = filterByMagnitude(list, 5);
        
        for(QuakeEntry qe: bigList) 
        {
        	System.out.println(qe);
        	
        }
        
        System.out.println("Found " + bigList.size());
        
       
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "Resources\\EarthQuakeall_day.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
         Location city =  new Location(38.17, -118.82);

        ArrayList<QuakeEntry> closeList = filterByDistanceFrom(list, 1000 * 1000, city);
        
        
       
        for(int k=0; k < closeList.size(); k++){
           QuakeEntry entry = closeList.get(k);
           double distanceInMeters = city.distanceTo(entry.getLocation());
           System.out.printf("%4.7f %s %s\n" , distanceInMeters/1000, "km", entry.getInfo());
        }
        
        
        System.out.println("number found: "+ closeList.size());
       
        
       
        
        
    }
    
    

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "Resources\\nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
//        for (QuakeEntry qe : list) {
//            System.out.println(qe);
//        }
    }
    
}
