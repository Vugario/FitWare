package main;

import model.User;
import view.Popup;
import view.member.Dashboard;

/**
 * This class contains all data needed for the currently logged in user
 *
 * @author Daan
 */
public class Session {

    /**
     * The only instance of this Session class
     */
    private static Session instance;
    /**
     * The currently logged in user
     */
    private User loggedInUser;

    /**
     * A private constructor, so nobody can create another instance
     */
    private Session() {
    }

    /**
     * The singleton class to get the instance of this class
     *
     * @return The instance of this class
     */
    public static Session get() {

        // Create the Session if it not exists yet
        if (Session.instance == null) {
            Session.instance = new Session();
        }

        // Return the only instance of Session
        return Session.instance;
    }

    /**
     * Log in a user
     *
     * @param username The username from the user to log in
     * @param password The password from the user to log in
     * @return True when the user logged in succesfully, false otherwise
     */
    public boolean logIn(String username, String password) {

        Application application = Application.getInstance();

        // Try to find a user with the given username and password
        User user = new User().readByCredentials(username, password);
		
		if(user.getId() > 0){
			this.loggedInUser = user;
			return true;
		};
		
		//this.loggedInUser = user;
		
        /*if (user.getId() == 0) {
            // Login failed
            Popup popup = new Popup();
            popup.showError("Gebruikersnaam en wachtwoord combinatie is niet bekend.");
        } else if (!user.isActive()) {
            // The user is inactive
            Popup popup = new Popup();
            popup.showError("Uw account is niet actief meer. Neem contact op met een medewerker.");
        } else {
            // The credentials are correct
            this.loggedInUser = user;

            // Show the menu
            application.showMenu(true);
            application.changeMenuPanel("member");

            // Display the dashboard
            application.changeContentPanel("dashboard");

            // And display user info in the header
            // TODO
            application.getHeader().updateUserInfo();
            application.getHeader().showUserInfo(true);
            //application.getPage("dashboard");
            application.changeContentPanel("dashboard");
            Dashboard dashboard = (Dashboard) application.getPage("dashboard");
            dashboard.updateUserInfo();
            return true;
        }*/

        return false;
    }

    /**
     * Log out the current user and display the login page
     */
    public void logOut() {

        // Log out the current user
        this.loggedInUser = null;

        // And go to the login page
        Application2 application = Main.getApplication();

        // 1. Hide the menu
        application.showMenu(false);
        // 2. Hide the user info in the right top
        application.getHeader().updateUserInfo();
        // 3. And display the Login page              
        application.changeContentPanel("login");
    }

    /**
     * Get the currently logged in User
     *
     * @return The currently logged in user
     */
    public User getLoggedInUser() {
        return this.loggedInUser;
    }
}
