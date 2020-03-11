package com.example.abi_spare.oainternalmessenger;

import com.google.gson.annotations.SerializedName;

public class ResposeInsert{

	@SerializedName("Status")
	private boolean Status;

	@SerializedName("Message")
	private String Message;

	public void setStatus(boolean status){
		this.Status = status;
	}

	public boolean isStatus(){
		return Status;
	}

	public void setMessage(String message){
		this.Message = message;
	}

	public String getMessage(){
		return Message;
	}

	@Override
 	public String toString(){
		return 
			"ResposeInsert{" + 
			"status = '" + Status + '\'' +
			",message = '" + Message + '\'' +
			"}";
		}
}