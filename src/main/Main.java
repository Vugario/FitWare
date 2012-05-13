package main;

public class Main {


	protected static Application application;

	public static void main(String args[]) {

		//helper.db.Manager dbconnection = new helper.db.Manager();
		//dbconnection.open();
		
		model.User user = new model.User();
		user.readUser(1);
		
		//Migration.run();

		//application = new Application();
		//application.setVisible(true);
		
	}

	public static Application getApplication() {
		return application;
	}

}
