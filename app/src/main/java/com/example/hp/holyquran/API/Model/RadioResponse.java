package com.example.hp.holyquran.API.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class RadioResponse{

	@SerializedName("Radios")
	private List<RadiosItems> radios;

	public void setRadios(List<RadiosItems> radios){
		this.radios = radios;
	}

	public List<RadiosItems> getRadios(){
		return radios;
	}

	@Override
 	public String toString(){
		return 
			"RadioResponse{" + 
			"radios = '" + radios + '\'' + 
			"}";
		}
}