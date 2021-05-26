package com.imf.haryanachi.networkModel.overall_state_count;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("finaldischg")
	private int finaldischg;

	@SerializedName("uniquemoderate")
	private int uniquemoderate;

	@SerializedName("uniqueadmit")
	private int uniqueadmit;

	@SerializedName("uniquemild")
	private int uniquemild;

	@SerializedName("all_positive")
	private int allPositive;

	@SerializedName("uniquesevere")
	private int uniquesevere;

	@SerializedName("finalhospital")
	private int finalhospital;

	public int getFinaldischg(){
		return finaldischg;
	}

	public int getUniquemoderate(){
		return uniquemoderate;
	}

	public int getUniqueadmit(){
		return uniqueadmit;
	}

	public int getUniquemild(){
		return uniquemild;
	}

	public int getAllPositive(){
		return allPositive;
	}

	public int getUniquesevere(){
		return uniquesevere;
	}

	public int getFinalhospital(){
		return finalhospital;
	}
}