package main;

public class Main {


	protected static Application application;

	public static void main(String args[]) {

		//helper.db.Manager dbconnection = new helper.db.Manager();
		//dbconnection.open();
		
		//model.User user = new model.User();
		//user.readUser(1);
		long timestamp = 1334959260000L;
		helper.Datetime datetime = new helper.Datetime(timestamp);
		//datetime.format("yyyy-MM-dd hh:mm");
		System.out.println(datetime.format2("yyyy-dd"));
		//Migration.run();

		//application = new Application();
		//application.setVisible(true);
		
	}

	public static Application getApplication() {
		return application;
	}

}
