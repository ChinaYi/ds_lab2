package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import compute.MeetingInterface;


public class Adder {
	public static void main(String[] args){
		/*if (System.getSecurityManager() == null) {
        	System.setSecurityManager(new SecurityManager());
    	}*/
		try(Scanner in = new Scanner(System.in)) {
            String name = "Agenda";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Registry registry = LocateRegistry.getRegistry(args[0]);
            System.out.print("Input your username: ");
            String start_user = in.nextLine();
            System.out.print("Input the user you want to book: ");
            String book_user = in.nextLine();
            System.out.print("Input the start_time of the meeting(yyyy-MM-dd HH:mm:ss): ");
            Date start_time = sdf.parse(in.nextLine());
            System.out.print("Input the end_time of the meeting(yyyy-MM-dd HH:mm:ss): ");
            Date end_time = sdf.parse(in.nextLine());
            System.out.print("Input the description label of this meeting: ");
            String description_label = in.nextLine();
            MeetingInterface comp = (MeetingInterface) registry.lookup(name);
            System.out.println(comp.addMeeting(start_time, end_time, description_label, book_user, start_user));
        } catch (Exception e) {
            System.err.println("RegisterMeeting exception:");
            e.printStackTrace();
        }
	}
}
