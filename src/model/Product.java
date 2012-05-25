package model;

import helper.db.Model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import view.admin.BarProductModify;
/**
 * This class is used to use define queries for the product table in the database.
 * 
 * 
 * @param id			this is the product id (integer)
 * @param price			this is the price of the product (double)
 * @param name			this is the name of the product (String)
 * @param description	this is the description for the product (String)
 * @param type			this is the type of the product, either food, drink or other (String)
 * 
 * @author vm
 */
public class Product extends Model{
	
	int id;
	double price;
	String name;
    String description;
	String type;
	
	public Product() {	
	}
	
	/**
	 * 
	 * @param result is the outcome of a query.
	 */
	public Product(ResultSet result) {
		super();
		
		this.result = result;
		this.setPropertiesFromResult();
	}
	
	/**
	 * 
	 * @return returns an array list of Product
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
			while(model.result.next()) {
				products.add(new Product(model.result));
			}

		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return products;

	}
	

	public void readPerCategory(){
		String query = "SELECT * FROM product WHERE cs";
	}
	
	/**
	 * 
	 * @param productId productId is the ID of the selected product in view.admin.BarProductOverview
	 * @return returns object Product
	 * @author vm
	 */
	
	public Product readById(int productId){
		try {
			this.open();
			PreparedStatement query = this.query("SELECT * FROM product WHERE id = ? LIMIT 1");
			
			query.setInt(1, productId);
			this.result();
			this.result.first();
			this.setPropertiesFromResult();
			
		
			
		} catch (SQLException ex) {
			Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
			
		}
	
	
		System.out.println(getId());
		System.out.println(getPrice());
		System.out.println(getName());
		return this;
	}
	
	
	/**
	 * @author vm
	 * 
	 * @return returns a true when there are products created, on error it returns false
	 */
	public boolean create(){
		try {
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
	
	public Boolean updateProduct(){
		try {
			PreparedStatement query =  this.query("UPDATE product SET "
					+ "price = ?, "
					+ "name = ?, "
					+ "description = ?, "
					+ "type = ?"
					+ "WHERE id = ?;");
			
				query.setDouble(1, price);
				query.setString(2, name);
				query.setString(3, description);
				query.setString(4, type);
				
		} catch (SQLException ex) {
			Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
		return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return getName() + " " + getDecoratedPrice();
	}

	/**
	 * 
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
			
			System.out.println("Set properties from result completed.");
		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
	/**
	 * @author daan
	 * @return returns the table row objects
	 */
		public Object[] getTableRowObjects() {
		
		return new Object[] {
			String.format("%04d", id),
			name,
			this.type,
			description,
			String.format("€ %.2f", price)
		};
	}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    
    public String getDecoratedPrice(){
        return String.format("€ %.2f", getPrice());
                
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}