package compute;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

import entity.Meeting;

public interface MeetingInterface extends Remote{
	String addMeeting(Date start_time, Date end_time, String description_label, String book_user, String start_user)
			throws RemoteException;
	String clearMeeting(String user, String password)
			throws RemoteException;
	String deleteMeeting(String user, String password, int unique_id)
			throws RemoteException;
	Meeting[] queryMeeting(Date start_time, Date end_time, String user)
			throws RemoteException;
	String registerMeeting(String username, String password) throws RemoteException;
}
