

/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class Tester{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer LogAnalyzer = new LogAnalyzer();
        LogAnalyzer.readFile("short-test_log");
        LogAnalyzer.printAll(); 
        // complete method
    }
    
    public void testUniqueIP(){
        LogAnalyzer LogAnalyzer = new LogAnalyzer();
        LogAnalyzer.readFile("weblog2_log");
        System.out.println("Unique IPs count: "+LogAnalyzer.countUniqueIPs());
    }
    
    public void testPrintAllHigherThanNum(){
        LogAnalyzer le = new LogAnalyzer();
        le.readFile("weblog1_log");
        le.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer le = new LogAnalyzer();
        le.readFile("weblog2_log");
        System.out.println(le.uniqueIPVisitsOnDay("Sep 27").size());
    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer le = new LogAnalyzer();
        le.readFile("weblog2_log");
        System.out.println(le.countUniqueIPsInRange(400,499));
    }
    
    public void testMostNumberVisitsByIP(){
        LogAnalyzer le = new LogAnalyzer();
        le.readFile("weblog2_log");
        System.out.println(le.mostNumberVisitsByIP(le.countVisitsPerIP()));
    }
    
    public void testIPsMostVisits(){
        LogAnalyzer le = new LogAnalyzer();
        le.readFile("weblog2_log");
        System.out.println(le.iPsMostVisits(le.countVisitsPerIP()));
    }
    
    public void testIPsForDays(){
        LogAnalyzer le = new LogAnalyzer();
        le.readFile("weblog3-short_log");
        System.out.println(le.iPsForDays().get("Sep 14"));
        System.out.println(le.iPsForDays());
    }
    
    public void testDayWithMostIPVisits(){
        LogAnalyzer le = new LogAnalyzer();
        le.readFile("weblog2_log");
        System.out.println(le.dayWithMostIPVisits(le.iPsForDays()));
    }
    
    public void testIPsWithMostVisitsOnDay(){
        LogAnalyzer le = new LogAnalyzer();
        le.readFile("weblog2_log");
        System.out.println(le.iPsWithMostVisitsOnDay(le.iPsForDays(), "Sep 29"));
    }
}