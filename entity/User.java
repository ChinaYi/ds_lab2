package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class User implements Serializable{
	private String username;
	private String password;
	private ArrayList<Meeting> meetings = new ArrayList<Meeting>();
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	public boolean time_confict(Date start_time, Date end_time){
		for(Meeting m : meetings){
			if(start_time.after(m.getStart_time()) && start_time.before(m.getEnd_time()))
				return true;
			else if(end_time.after(m.getStart_time()) && end_time.before(m.getEnd_time()))
				return true;
			else
				return false;
		}
		return false;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Meeting> getMeetings() {
		return meetings;
	}

	public void setMeetings(ArrayList<Meeting> meetings) {
		this.meetings = meetings;
	}
}
