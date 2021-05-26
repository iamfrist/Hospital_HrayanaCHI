package com.imf.haryanachi.networkModel.todays_state_count;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("todayscount")
	private int todayscount;

	@SerializedName("peradmitcount")
	private int peradmitcount;

	@SerializedName("uniquemoderate")
	private int uniquemoderate;

	@SerializedName("todays_positive")
	private int todaysPositive;

	@SerializedName("uniqueadmit")
	private int uniqueadmit;

	@SerializedName("uniquemild")
	private int uniquemild;

	@SerializedName("todayspositive")
	private int todayspositive;

	@SerializedName("uniquesevere")
	private int uniquesevere;

	public int getTodayscount(){
		return todayscount;
	}

	public int getPeradmitcount(){
		return peradmitcount;
	}

	public int getUniquemoderate(){
		return uniquemoderate;
	}

	public int getTodaysPositive(){
		return todaysPositive;
	}

	public int getUniqueadmit(){
		return uniqueadmit;
	}

	public int getUniquemild(){
		return uniquemild;
	}

	public int getTodayspositive(){
		return todayspositive;
	}

	public int getUniquesevere(){
		return uniquesevere;
	}
}