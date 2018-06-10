package com.mkyong;
import java.io.IOException;
import java.sql.*;
//import java.util.ArrayList;
import com.mkyong.Car;
public class DatabaseManager {
	public Connection getDBConnection()
	{
		Connection conn=null;
		try
		{
			  String myDriver = "com.mysql.cj.jdbc.Driver";
		      String myUrl = "jdbc:mysql://127.0.0.1:3306/test_db";
		      Class.forName(myDriver);
		      conn = DriverManager.getConnection(myUrl, "root@localhost", "");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}
	public Car ReadCarDetails(String VIN) throws Exception
	{
		  Car car = new Car();
	      Connection conn = getDBConnection();
	      
	      // our SQL SELECT query. 
	      // if you only need a few columns, specify them by name instead of using "*"
	      String query = "SELECT * FROM car WHERE Vin=?";

	      // create the java statement
	      PreparedStatement pstmt = conn.prepareStatement(query);
	      pstmt.setString(1, VIN);
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = pstmt.executeQuery();
	      //ArrayList <Car> list = new ArrayList<Car>();//needed in case primary key was not used for search
	      // iterate through the java resultset
	      while (rs.next())
	      {
	        String Brand = 		rs.getString("Brand_Name");
	        String Model = 		rs.getString("Model_name");
	        boolean hasHatch = 	rs.getBoolean("Hatchback");
	        String Class = 		rs.getString("Seater");
	        String LuxLevel = 	rs.getString("Luxury_level");
      		car.setVIN(VIN);
      		car.setBrand_Name(Brand);
      		car.setHas_Hatchback(hasHatch);
      		car.setLuxury_level(LuxLevel);
      		car.setModel_Name(Model);
      		car.setSeater_Type(Class);
	      }
	        // print the results
	        //System.out.format("%s, %s, %s, %s, %s, %s\n", id, firstName, lastName, dateCreated, isAdmin, numPoints);
	      pstmt.close();
		//}
		 return car;
	  }
public void EnterCarDetails(Car car)throws Exception
{
	 Connection conn = getDBConnection();
     String VIN	=car.getVIN();
	 String Brand=car.getBrand_Name();
	 boolean Hatch=car.isHas_Hatchback();
	 String LuxLevel=car.getLuxury_level();
	 String Model=car.getModel_Name();
	 String Class=car.getSeater_Type();
      // our SQL SELECT query. 
      // if you only need a few columns, specify them by name instead of using "*"
      String query = "INSERT INTO car(Vin,Brand_Name,Model_name,Hatchback,Seater,Luxury_level) VALUES(?,?,?,?,?,?)";

      // create the java statement
      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setString(1, VIN);
      pstmt.setString(2, Brand);
      pstmt.setString(3, Model);
      pstmt.setBoolean(4,Hatch);
      pstmt.setString(5, Class);
      pstmt.setString(6, LuxLevel);
      pstmt.executeUpdate();
      pstmt.close();
}
public boolean UpdateCarDetails(String VIN,String Brand)throws Exception
{
	boolean res=false;
    Connection conn = getDBConnection();
	String query="UPDATE car SET Brand_Name=? WHERE Vin=?";
	PreparedStatement pstmt = conn.prepareStatement(query);
	pstmt.setString(1, Brand);
	pstmt.setString(2, VIN);
	int i=pstmt.executeUpdate();
	if(i==1) {
		res=true;
	}
	return res;
}
public boolean DeleteCarRecord(String VIN)throws Exception
{
	boolean res=false;
    Connection conn = getDBConnection();
	String query="DELETE FROM car WHERE Vin=?";
	PreparedStatement pstmt = conn.prepareStatement(query);
	pstmt.setString(1, VIN);
	int i=pstmt.executeUpdate();
	if(i==1) {
		res=true;
	}
	return res;
}

public static void main(String args[])throws IOException
{
	try {
	DatabaseManager db=new DatabaseManager();
	Car car=new Car();
	car=db.ReadCarDetails("1234567825");
	if(car.getVIN()!=null)
		System.out.println(car.getBrand_Name());
	Car car1=new Car();
	car1.setVIN("MHKLOP123478529");
	car1.setBrand_Name("AUDI");
	car1.setModel_Name("A8");
	car1.setHas_Hatchback(true);
	car1.setSeater_Type("4-SEATERS");
	car1.setLuxury_level("High");
	db.EnterCarDetails(car1);
	}
	catch(Exception e)
	{
		System.err.println("Inside main:"+e.getMessage());
		//e.printStackTrace();
	}
	/*Car car1=new Car();
	car1.setVIN("BL12357845962PU87M");
	car1.setBrand_Name("TATA");
	car1.setModel_Name("INDICA");
	car1.setHas_Hatchback(false);
	car1.setSeater_Type("4-SEATERS");
	car1.setLuxury_level("MEDIUM");
	db.EnterCarDetails(car1);
	boolean res=db.UpdateCarDetails("DL125783259TRQ2F", "Mercedes");
    //res=db.DeleteCarRecord("BL12357845962PU87M");
	System.out.println(res);*/
}
}
