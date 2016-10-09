package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import compute.MeetingInterface;


public class Register {
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
           
            MeetingInterface comp = (MeetingInterface) registry.lookup(name);
            System.out.println(comp.registerMeeting(username, password));
        } catch (Exception e) {
            System.err.println("RegisterMeeting exception:");
            e.printStackTrace();
        }
	}
}
