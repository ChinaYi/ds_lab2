package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import compute.MeetingInterface;
import entity.Meeting;;


public class Query {
	public static void main(String[] args){
		/*if (System.getSecurityManager() == null) {
        	System.setSecurityManager(new SecurityManager());
    	}*/
		try(Scanner in = new Scanner(System.in)) {
            String name = "Agenda";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Registry registry = LocateRegistry.getRegistry(args[0]);
            System.out.print("Input your username: ");
            String username = in.nextLine();
            System.out.print("Input the start_time of the meeting(yyyy-MM-dd HH:mm:ss): ");
            Date start_time = sdf.parse(in.nextLine());
            System.out.print("Input the end_time of the meeting(yyyy-MM-dd HH:mm:ss): ");
            Date end_time = sdf.parse(in.nextLine());
            MeetingInterface comp = (MeetingInterface) registry.lookup(name);
            
            Meeting[] meetings = comp.queryMeeting(start_time, end_time, username);
            for(int i = 0;i < meetings.length; i++){
            	System.out.println(meetings[i]);
            }
        } catch (Exception e) {
            System.err.println("queryMeeting exception:");
            e.printStackTrace();
        }
	}
}