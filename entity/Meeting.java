package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Meeting implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7469450633485674453L;
	
	private String book_user;
	private Date start_time;
	private Date end_time;
	private String description_label;
	private int unique_id;
	
	public Meeting() {
		super();
	}
	
	public Meeting(String book_user, Date start_time, Date end_time,
			String description_label, int unique_id) {
		super();
		this.book_user = book_user;
		this.start_time = start_time;
		this.end_time = end_time;
		this.description_label = description_label;
		this.unique_id = unique_id;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + unique_id;
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
		Meeting other = (Meeting) obj;
		if (unique_id != other.unique_id)
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return "You have meeting with " + book_user + ", starts at " + sdf.format(start_time)
				+ ", end at " + sdf.format(end_time) + ", this meeting is about "
				+ description_label + ", and unique_id=" + unique_id;
	}

	public String getBook_user() {
		return book_user;
	}
	
	public void setBook_user(String book_user) {
		this.book_user = book_user;
	}
	
	public Date getStart_time() {
		return start_time;
	}
	
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	
	public String getDescription_label() {
		return description_label;
	}
	
	public void setDescription_label(String description_label) {
		this.description_label = description_label;
	}

	public int getUnique_id() {
		return unique_id;
	}

	public void setUnique_id(int unique_id) {
		this.unique_id = unique_id;
	}
	
}
