package main;

public class Main {


	protected static Application application;

	public static void main(String args[]) {

		application = new Application();
		application.setVisible(true);
		
	}

	public static Application getApplication() {
		return application;
	}

}
