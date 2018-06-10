package com.mkyong.rest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkyong.Car;
import com.mkyong.DatabaseManager;
@Path("/Car")
public class JSONServices {
	@GET
	@Path("/get/{VIN}")
	@Produces(MediaType.APPLICATION_JSON)
	//It will take VIN number of car and then find it in database n will display that record accordingly
	public Response getCarInJSON(@PathParam("VIN")String vin) {
		DatabaseManager db=new DatabaseManager();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Car car= db.ReadCarDetails(vin);
			if(car.getVIN()!=null)
			{
				String carJson;
				carJson = objectMapper.writeValueAsString(car);
				return Response.status(200).entity(carJson).build();
			}
			else {
				String msg="{ \"Error\" : \"Entry not Found \"}";//"entry not found!!!!!!";
				return Response.status(404).entity(msg).build();
			}
		}
		catch (Exception e) {
			String err=e.getMessage();
			return Response.status(409).entity(err).build();
		}

	}

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCarInJSON(Car car) {	
		String result="";
		try {
		DatabaseManager db=new DatabaseManager();
		db.EnterCarDetails(car);
		result += "car saved : " + car;
		return Response.status(201).entity(result).build();
		}
		catch(Exception e) {
			result+="entry already exists!!!VIN need to be unique!";
			return Response.status(409).entity(result).build();
		}
		
	}
	@PUT
	@Path("/put/{VIN}/{Brand}")
	public Response updateCarInJSON(@PathParam("VIN")String vin,@PathParam("Brand")String Brand) {
		try {
		DatabaseManager db=new DatabaseManager();
		boolean res=db.UpdateCarDetails(vin, Brand);
		ObjectMapper obj=new ObjectMapper();
		if(res==true) {
			Car car=new Car();
			car=db.ReadCarDetails(vin);
			String carJson=obj.writeValueAsString(car);
			return Response.status(200).entity(carJson).build();
		}
		else
		{
			String msg="Entry not found!!!";
			return Response.status(404).entity(msg).build();		
		}
	}
		catch(Exception e)
		{
			String err=e.getMessage();
			return Response.status(409).entity(err).build();
		}
}
	@DELETE
	@Path("/delete/{VIN}")
	public Response deleteCarInJSON(@PathParam("VIN")String vin) {
		try {
		DatabaseManager db=new DatabaseManager();
		boolean res=db.DeleteCarRecord(vin);
		if(res==true) {
			String result="successfully deleted a entry!!!!";
			return Response.status(204).entity(result).build();
		}
		else {
			String msg="Entry not found!!!";
			return Response.status(404).entity(msg).build();
		}
		}
		catch(Exception e)
		{
			String err=e.getMessage();
			return Response.status(409).entity(err).build();
		}
	}
}
