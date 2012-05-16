package main;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String args[]) {
		
		final Application application = Application.getInstance();
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					//application.initialize();
					application.startup();
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
		});
	}

}
