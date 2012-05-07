package main;

import javax.swing.SwingUtilities;

public class Main {

<<<<<<< HEAD
	protected static myview.Wrapper application;

	public static void main(String args[]) {

		//model.User user = new model.User();

		//Migration.run();

		//Application app = new Application();
		//app.setVisible( true );

		//application = new myview.Wrapper();
		//application.setVisible(true);
		
		View view = new View();
        view.setVisible( true );

	}

	public static myview.Wrapper getApplication() {
		return application;
	}
}
