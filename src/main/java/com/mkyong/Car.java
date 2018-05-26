package com.mkyong;

public class Car {
	
	private String VIN;
	private String Brand_Name;
	private String Model_Name;
	private boolean has_Hatchback;
	private String Seater_Type;
	private String Luxury_level;
	public String getVIN() {
		return VIN;
	}
	public void setVIN(String vIN) {
		VIN = vIN;
	}
	public String getBrand_Name() {
		return Brand_Name;
	}
	public void setBrand_Name(String brand_Name) {
		Brand_Name = brand_Name;
	}
	public String getModel_Name() {
		return Model_Name;
	}
	public void setModel_Name(String model_Name) {
		Model_Name = model_Name;
	}
	public boolean isHas_Hatchback() {
		return has_Hatchback;
	}
	public void setHas_Hatchback(boolean has_Hatchback) {
		this.has_Hatchback = has_Hatchback;
	}
	public String getSeater_Type() {
		return Seater_Type;
	}
	public void setSeater_Type(String seater_Type) {
		Seater_Type = seater_Type;
	}
	public String getLuxury_level() {
		return Luxury_level;
	}
	public void setLuxury_level(String luxury_level) {
		Luxury_level = luxury_level;
	}
}
