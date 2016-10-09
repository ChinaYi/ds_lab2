package agenda;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;

import compute.*;
import entity.*;


public class AgendaEngine implements MeetingInterface{
	private ArrayList<User> users = new ArrayList<User>();
	public static int id;
	@Override
	/*
	 * FUNCTION addMeeting
	 * @param:
	 * start_time : the time of the meeting start
	 * end_time : the time of the meeting end
	 * description_label : a short description about this meeting
	 * book_user : the invited user
	 * start_user : the user invite others
	 * 
	 * @detail:
	 * find if the book_user exists and whether will have time_conflict in one loop
	 * 
	 */
	public String addMeeting(Date start_time, Date end_time,
			String description_label, String book_user, String start_user) throws RemoteException {
		User temp_start_user = null;
		User temp_book_user = null;
		for(User u : users){
			if(u.getUsername().equals(start_user)){
				if(u.time_confict(start_time, end_time)){
					return "You've already had meeting during " + start_time +"  " + end_time;
				}else{
					temp_start_user = u;
				}	
			}
			else if(u.getUsername().equals(book_user)){
				if(u.time_confict(start_time, end_time)){
					return book_user + " already had meeting during " + start_time +"  " + end_time;
				}else{
					temp_book_user = u;
				}	
			}
		}
		
		//start_user is ensured exists
		//if temp_book_user is null, means no such person registered
		if(temp_book_user == null){
			return "No such user named " + book_user;
		}else{
			temp_start_user.getMeetings()
				.add(new Meeting(book_user, start_time, end_time, description_label, id));
			temp_book_user.getMeetings()
				.add(new Meeting(start_user, start_time, end_time, description_label, id));
			id ++ ;
			return "Add meeting success! And remember the id is " + (id-1);
		}
	}

	@Override
	public String clearMeeting(String user, String password)
			throws RemoteException {
		
		//Override the equals() methods
		User owner = new User();
		owner.setUsername(user);
		owner = users.get(users.indexOf(owner));
		
		//password
		if(owner.getPassword().equals(password)){
			//get this user's all meeting
			ArrayList<Meeting> temp_meetings = owner.getMeetings();
			//for each meeting
			for(Meeting m : temp_meetings){
				User temp_user = new User();
				Meeting temp_meeting = new Meeting();
				//find the booked user, and we will remove the meeting from the booked user
				temp_user.setUsername(m.getBook_user());
				temp_meeting.setUnique_id(m.getUnique_id());
				//Override equals() for User and Meeting entity
				users.get(users.indexOf(temp_user)).getMeetings().remove(temp_meeting);
			}
			//clear all the meetings
			owner.getMeetings().clear();
			return "success clear!";
		}else{
			return "password error!";
		}
	}

	@Override
	public String deleteMeeting(String user, String password, int unique_id)
			throws RemoteException {
		
		User owner = new User();
		owner.setUsername(user);
		//Override equals() for User entity
		//get the owners all attribute
		owner = users.get(users.indexOf(owner));
		
		//password
		if(owner.getPassword().equals(password)){
			User temp_user = new User();
			Meeting temp_meeting = new Meeting();
			temp_meeting.setUnique_id(unique_id);
			
			//before_remove used to check whether has this id
			//if the id not exist, the before_remove will == meetings.size()
			int before_remove = owner.getMeetings().size();
			temp_meeting = owner.getMeetings().get(owner.getMeetings().indexOf(temp_meeting));
			
			//get the booked user and clear the meeting from his meetings
			temp_user.setUsername(temp_meeting.getBook_user());
			owner.getMeetings().remove(temp_meeting);
			if(before_remove == owner.getMeetings().size()){
				return "No such meeting, perhaps already delete by other user";
			}
			
			//Override equals() methods
			users.get(users.indexOf(temp_user)).getMeetings().remove(temp_meeting);
			return "you have delete the meeting with" + temp_user.getUsername()
					+" at " + temp_meeting.getStart_time() + "   " + temp_meeting.getEnd_time();
			
		}else{
			return "password error!";
		}
	}

	@Override
	public Meeting[] queryMeeting(Date start_time, Date end_time, String user)
			throws RemoteException {
		//return all the meetings with Meeting[]
		for(User u : users){
			if(u.getUsername().equals(user)){
				int size = u.getMeetings().size();
				return (Meeting[]) u.getMeetings().toArray(new Meeting[size]);
			}
		}
		return null;
	}

	@Override
	public String registerMeeting(String username, String password)
			throws RemoteException {
		User temp = new User();
		temp.setUsername(username);
		temp.setPassword(password);
		
		//check if exists
		for(User u : users){
			if(u.equals(temp)){
				return "exsit user named " + username; 
			}
		}
		users.add(temp);
		return "success create user named " + username;
	}
	
	//A test function
	public void test() throws RemoteException{
		registerMeeting("yifangqiu", "123456");
		registerMeeting("yuanyongdan", "123456");
		System.out.println(queryMeeting(new Date(), new Date(), "yifangqiu").length);
		System.out.println(addMeeting(new Date(), new Date(), "this is a love meeting", "yuanyongdan", "yifangqiu"));
		Meeting[] ms = queryMeeting(new Date(), new Date(), "yifangqiu");
		for(int i = 0; i < ms.length; i++){
			System.out.println(ms[i].toString());
		}
		ms = queryMeeting(new Date(), new Date(), "yuanyongdan");
		for(int i = 0; i < ms.length; i++){
			System.out.println(ms[i].toString());
		}
		System.out.println(clearMeeting("yifangqiu", "123456"));
		
	}
	public static void main(String[] args){
		/*if(System.getSecurityManager() == null){
			System.setSecurityManager(new SecurityManager());
		}*/
		try {
			LocateRegistry.createRegistry(1099);
			String name = "Agenda";
	        MeetingInterface engine = new AgendaEngine();
	        MeetingInterface stub =
	            (MeetingInterface) UnicastRemoteObject.exportObject(engine, 0);
	        Naming.rebind("//localhost:1099/Agenda", stub);
	        System.out.println("Agenda bound");
		} catch (RemoteException e) {
			System.out.println("Agenda bound fail");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("Agenda Bound fail");
		} 
        
    }
}
