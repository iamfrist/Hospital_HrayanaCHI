package com.imf.haryanachi.networkModel.dashActiveCount;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("membercount")
	private int membercount;

	@SerializedName("familycountts")
	private int familycountts;

	@SerializedName("uniquemoderate")
	private int uniquemoderate;

	@SerializedName("positivecount")
	private int positivecount;

	@SerializedName("comorbcount")
	private int comorbcount;

	@SerializedName("uniquemild")
	private int uniquemild;

	@SerializedName("uniquesevere")
	private int uniquesevere;

	public int getMembercount(){
		return membercount;
	}

	public int getFamilycountts(){
		return familycountts;
	}

	public int getUniquemoderate(){
		return uniquemoderate;
	}

	public int getPositivecount(){
		return positivecount;
	}

	public int getComorbcount(){
		return comorbcount;
	}

	public int getUniquemild(){
		return uniquemild;
	}

	public int getUniquesevere(){
		return uniquesevere;
	}
}