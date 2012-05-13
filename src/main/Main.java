package main;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {


	protected static Application application;

	public static void main(String args[]) {

		model.User user = new model.User(true, 1);
		//helper.AnnotationReader.persist(user);
		//System.out.println("");
		//helper.db.Model model = new helper.db.Model();
		
	}

	public static Application getApplication() {
		return application;
	}

}
