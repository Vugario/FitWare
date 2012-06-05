
package main;

import model.User;

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

        // Try to find a user with the given username and password
        User user = new User().readByCredentials(username, password);
		
		if(user.getId() > 0){
			this.loggedInUser = user;
			return true;
		}

        return false;
    }

    /**
     * Log out the current user and display the login page
     */
    public void logOut() {

        // Log out the current user
        this.loggedInUser = null;
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
