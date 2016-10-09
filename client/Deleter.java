package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import compute.MeetingInterface;


public class Deleter {
	public static void main(String[] args){
		/*if (System.getSecurityManager() == null) {
        	System.setSecurityManager(new SecurityManager());
    	}*/
		try(Scanner in = new Scanner(System.in)) {
            String name = "Agenda";
            Registry registry = LocateRegistry.getRegistry(args[0]);
            System.out.print("Input your username: ");
            String username = in.nextLine();
            System.out.print("Input your password: ");
            String password = in.nextLine();
            System.out.print("Input the id of the meeting: ");
            int unique_id = in.nextInt();
            MeetingInterface comp = (MeetingInterface) registry.lookup(name);
            System.out.println(comp.deleteMeeting(username, password, unique_id));
        } catch (Exception e) {
            System.err.println("deleteMeeting exception:");
            e.printStackTrace();
        }
	}
}