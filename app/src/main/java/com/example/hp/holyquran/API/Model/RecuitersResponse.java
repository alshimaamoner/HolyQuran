package com.example.hp.holyquran.API.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RecuitersResponse{

	@SerializedName("reciters")
	private List<RecitersItem> reciters;

	public void setReciters(List<RecitersItem> reciters){
		this.reciters = reciters;
	}

	public List<RecitersItem> getReciters(){
		return reciters;
	}

	@Override
 	public String toString(){
		return 
			"RecuitersResponse{" + 
			"reciters = '" + reciters + '\'' + 
			"}";
		}
}