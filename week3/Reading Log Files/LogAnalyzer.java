import java.util.*;
import edu.duke.*;

/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LogAnalyzer {
    private ArrayList<LogEntry> records;  
    
    public LogAnalyzer() {
        // complete constructor
        records = new ArrayList<LogEntry>();
    }
       
    public void readFile(String filename) {
        // complete method
        FileResource fr = new FileResource(filename);
        for (String line : fr.lines()){
            records.add(WebLogParser.parseEntry(line));
        }
    }
        
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
    
    public int countUniqueIPs(){
        ArrayList<String> ipList = new ArrayList<String>();
        for (LogEntry le : records){
            String ip = le.getIpAddress();
            if (!ipList.contains(ip)){
                ipList.add(ip);
            }
        }
        return ipList.size();
    }
    
    public void printAllHigherThanNum(int num){
        for (LogEntry le : records){
            if (le.getStatusCode() > num){
                System.out.println(le);
            }
        }
    }
    
    public ArrayList uniqueIPVisitsOnDay(String someday){
        ArrayList<String> uniqIPVOD = new ArrayList<String>();
        for (LogEntry le : records){
            String ip = le.getIpAddress();
            String date = le.getAccessTime().toString();
            if (!uniqIPVOD.contains(ip) && date.contains(someday)){
                uniqIPVOD.add(ip);
            }
        }
        return uniqIPVOD;
    }
    
    public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> ipList = new ArrayList<String>();
        for (LogEntry le : records){
            String ip = le.getIpAddress();
            int status = le.getStatusCode();
            if (!ipList.contains(ip) && high>=status && status>=low){
                ipList.add(ip);
            }
        }
        return ipList.size();
    }
    
    public HashMap<String, Integer> countVisitsPerIP(){
        HashMap<String, Integer> IPs = new HashMap<String, Integer>();
            for (LogEntry le : records){
                String addr = le.getIpAddress();
                if (!IPs.containsKey(addr)){
                    IPs.put(addr, 1);
                } else {
                    IPs.put(addr, IPs.get(addr)+1);
                }
            }
        return IPs;
    }
    
    public int mostNumberVisitsByIP(HashMap<String, Integer> x){
        int count = 0;
        String key = new String();
        for (String i : x.keySet()){
            if (x.get(i) > count){
                count = x.get(i);
                key = i;
            }
        }
        return x.get(key);
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> x){
        ArrayList<String> ipArList = new ArrayList<String>();
        int mostVisit = mostNumberVisitsByIP(x);
            for (String i : x.keySet()){
                if (x.get(i)==mostVisit){
                    ipArList.add(i);
                }
            }
        return ipArList;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> ipForDays = new HashMap<String, ArrayList<String>>();
        for (LogEntry le : records){
            String date = le.getAccessTime().toString().substring(4, 10);
            String ip = le.getIpAddress();
            ArrayList<String> ipAddressList = new ArrayList<String>();
            if (!ipForDays.containsKey(date)){
                ipAddressList.add(ip);
                ipForDays.put(date, ipAddressList);
            } else {
                ArrayList<String> currentIpAddress =  ipForDays.get(date);
                currentIpAddress.add(ip);
                ipForDays.put(date, currentIpAddress);
            }
        }
        return ipForDays;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> DayIPs){
        String MostIPsDay = new String();
        int counter = 0;
        for (String x : DayIPs.keySet()){
            //System.out.println("Day:\t" + x + "| IPs:\t" + DayIPs.get(x).size());
            if (DayIPs.get(x).size() > counter){
                MostIPsDay = x;
                counter = DayIPs.get(x).size();
            }
        }
        return MostIPsDay;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> DayIPs, String day){
        ArrayList<String> list = DayIPs.get(day);
            HashMap<String, Integer> countIP = new HashMap<String, Integer>();
            for (String ip: list){
                if (!countIP.containsKey(ip)){
			countIP.put(ip, 1);
                } else {
			countIP.put(ip, countIP.get(ip)+1);
                }
	    }
	ArrayList<String> ipMostVisits = iPsMostVisits(countIP);
	return ipMostVisits;
    }
}
