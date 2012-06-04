package model;

import helper.db.Model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import main.Application;
import view.popups.ErrorPopup;
import view.popups.NotificationPopup;
import view.popups.SuccessPopup;

/**
 * This class is used to use define queries for the product table in the database.
 * 
 * @author vm
 */
public class Product extends Model {

	/**
	 * this is the price of the product (double)
	 */
	double price;
	/**
	 * this is the name of the product (String)
	 */
	String name;
	/**
	 * this is the description for the product (String)
	 */
	String description;
	/**
	 * this is the type of the product, either food, drink or other (String)
	 */
	String type;

	/**
	 * The default constructor
	 */
	public Product() {
	}

	/**
	 * This is the constructor for Product
	 * @param result is the outcome of a query.
	 */
	public Product(ResultSet result) {
		super();

		this.result = result;
		this.setPropertiesFromResult();
	}

	/**
	 * This method reads all the products and puts them in a ArrayList
	 * @return returns an array list of products
	 */
	public static ArrayList<Product> readAll() {

		ArrayList<Product> products = new ArrayList<Product>();

		try {
			// Execute the query
			Model model = new Model();
			model.open();
			model.query(
					"SELECT * from product");
			model.result();

			// Loop over all results
			while (model.result.next()) {
				products.add(new Product(model.result));

			}

		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

		return products;
	}

	/**
	 * This method reads the products per category
	 */
	public void readPerCategory() {
		String query = "SELECT * FROM product WHERE cs";
	}

	/**
	 * This method reads the product by it's identifier
	 * @param productId productId is the iodentifier of the selected product in view.admin.BarProductOverview
	 * @return returns object Product
	 * @author vm
	 */
	public Product readById(int productId) {
		try {
			//execute the query
			this.open();
			PreparedStatement query = this.query("SELECT * FROM product WHERE id = ? LIMIT 1");

			query.setInt(1, productId);
			this.result();
			this.result.first();
			this.setPropertiesFromResult();

		} catch (SQLException ex) {
			Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);

		}

		return this;
	}

	/**
	 * This method creates a product in the database
	 * @author vm
	 * @return returns a true when there are products created, on error it returns false
	 */
	public boolean create() {
		try {
			//execute the query
			this.open();
			PreparedStatement query = this.query("INSERT INTO \"product\" (price, name, description, type) VALUES (?, ?, ?, ?)");

			query.setDouble(1, price);
			query.setString(2, name);
			query.setString(3, description);
			query.setString(4, type);
		} catch (SQLException ex) {
			Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
		this.execute();

		return true;

	}

	/**
	 * This method does an update on a product
	 * @return true on succesful update false on a failure
	 */
	public Boolean updateProduct() {
		try {
			//execute the query
			PreparedStatement query = this.query("UPDATE product SET "
					+ "price = ?, "
					+ "name = ?, "
					+ "description = ?, "
					+ "type = ?"
					+ "WHERE id = ?;");

			query.setDouble(1, price);
			query.setString(2, name);
			query.setString(3, description);
			query.setString(4, type);
			query.setInt(5, id);
			Application.getInstance().showPopup(new SuccessPopup("Product met succes aangepast."));
			this.execute();
		} catch (SQLException ex) {
			Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
			Application.getInstance().showPopup(new ErrorPopup("Er is iets misgegaan, probeer het nogmaals."));
			return false;
		}

		return true;

	}

	/**
	 * This method creates a decorative price description
	 * @return The decorated  price in a String
	 */
	@Override
	public String toString() {
		return getName() + " " + getDecoratedPrice();
	}

	/**
	 * This method sets the properties from the query result
	 * 
	 */
	protected final void setPropertiesFromResult() {
		try {

			// Check if there is a result
			if (this.result.getRow() == 0) {
				// There is no result, so return without doing anything
				return;
			}

			// Fill in all properties
			this.id = this.result.getInt("id");
			this.type = this.result.getString("type");
			this.price = this.result.getDouble("price");
			this.name = this.result.getString("name");
			this.description = this.result.getString("description");

		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	/**
	 * This methods gets the objects of a table row
	 * @author daan
	 * @return returns the table row objects
	 */
	public Object[] getTableRowObjects() {

		return new Object[]{
					String.format("%04d", id),
					name,
					this.type,
					description,
					String.format("€ %.2f", price)
				};
	}

	/**
	 * Delete the selected product in the BarProductModify view.
	 */
	public void deleteProduct() {
		try {
			//execute the query
			this.open();
			PreparedStatement query = this.query("DELETE FROM product WHERE id = ?;");

			query.setInt(1, id);
		} catch (SQLException ex) {
			Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
		}
		this.execute();
		Application.getInstance().showPopup(new NotificationPopup("Het product is verwijderd."));
		Application.getInstance().showPanel(new view.admin.BarProductOverview());
	}

	/**
	 * 
	 * @return the description of a product
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description is the description of a product
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return the name of a product
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name is the name of a product
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return the price of a product
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * 
	 * @return the decorated price, including eurosign and only 2 decimals
	 */
	public String getDecoratedPrice() {
		return String.format("€ %.2f", getPrice());

	}

	/**
	 * 
	 * @param price is the price of a product
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * 
	 * @return the type of a product
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param type is the type of product (food, drink or other)
	 */
	public void setType(String type) {
		this.type = type;
	}
}