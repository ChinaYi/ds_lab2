package agenda;

import entity.*;
import java.util.*;
public class Test {
	public static void main(String[] args){
		User u = new User();
		u.setUsername("yifangqiu");
		u.setPassword("123456");
		Meeting m = new Meeting("haha", new Date(), new Date(),"this is a test meeting", 1);
		u.getMeetings().add(m);
		System.out.println(u.getMeetings().size());
		Meeting temp = new Meeting();
		temp.setUnique_id(2);
		u.getMeetings().remove(temp);
		System.out.println("after remove : " + u.getMeetings().size());
	}
}
