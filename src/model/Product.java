package model;

import helper.db.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Product extends Model{
	
	int id;
	double price;
	String name;
    String description;
	String type;
	
	public Product() {	
	}
	
	public Product(ResultSet result) {
		super();
		
		this.result = result;
		this.setPropertiesFromResult();
	}
	
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
	
	public void create(){
		String query = "INSERT INTO \"product\" () VALUES (?, ?, ?, ?)";
	}
	
	public void update(){
		String query = "UPDATE product SET ? = ?";
	}
	
	@Override
	public String toString() {
		return getName() + " " + getDecoratedPrice();
	}

	
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
                        this.price = this.result.getInt("price");
                        this.name = this.result.getString("name");
                        this.description = this.result.getString("description");
			
			
		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
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