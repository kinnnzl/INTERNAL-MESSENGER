package com.example.abi_spare.oainternalmessenger;

import com.google.gson.annotations.SerializedName;

public class DocItem{

	@SerializedName("DocCode")
	private String DocCode;

	public void setDocCode(String docCode){
		this.DocCode = docCode;
	}

	public String getDocCode(){
		return DocCode;
	}

	@Override
 	public String toString(){
		return 
			"DocItem{" + 
			"docCode = '" + DocCode + '\'' +
			"}";
		}
}