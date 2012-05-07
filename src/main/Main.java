package main;

import javax.swing.SwingUtilities;

public class Main {

	protected static myview.Wrapper application;

	public static void main(String args[]) {

		//model.User user = new model.User();

		//Migration.run();

		//Application app = new Application();
		//app.setVisible( true );

		application = new myview.Wrapper();
		application.setVisible(true);

	}

	public static myview.Wrapper getApplication() {
		return application;
	}
}
