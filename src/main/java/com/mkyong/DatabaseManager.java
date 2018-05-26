package com.mkyong;
import java.sql.*;
//import java.util.ArrayList;
import com.mkyong.Car;
public class DatabaseManager {
	public Car ReadCarDetails(String VIN)
	{
		Car car = new Car();
		try
	    {
	      // create our mysql database connection
	      String myDriver = "com.mysql.jdbc.Driver";
	      String myUrl = "jdbc:mysql://localhost:3306/test_db";
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root@localhost", "Password");
	      
	      // our SQL SELECT query. 
	      // if you only need a few columns, specify them by name instead of using "*"
	      String query = "SELECT FROM car WHERE Vin=VIN";

	      // create the java statement
	      Statement st = conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = st.executeQuery(query);
	      //ArrayList <Car> list = new ArrayList<Car>();//needed in case primary key was not used for search
	      // iterate through the java resultset
	      while (rs.next())
	      {
	        String Brand = rs.getString("Brand_Name");
	        String Model = rs.getString("Model_Name");
	        boolean hasHatch = rs.getBoolean("Hatchback");
	        String Class = rs.getString("Seater");
	        String LuxLevel = rs.getString("Luxury_level");
	      		car.setVIN(VIN);
	      		car.setBrand_Name(Brand);
	      		car.setHas_Hatchback(hasHatch);
	      		car.setLuxury_level(LuxLevel);
	      		car.setModel_Name(Model);
	      		car.setSeater_Type(Class);
	      }
	        // print the results
	        //System.out.format("%s, %s, %s, %s, %s, %s\n", id, firstName, lastName, dateCreated, isAdmin, numPoints);
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		 return car;
	  }
	}

