package main;

import javax.swing.SwingUtilities;

public class Main {

	protected static view.Wrapper application;

	public static void main(String args[]) {

		//model.User user = new model.User();

		//Migration.run();

		//Application app = new Application();
		//app.setVisible( true );

		application = new view.Wrapper();
		application.setVisible(true);

	}

	public static view.Wrapper getApplication() {
		return application;
	}
}
