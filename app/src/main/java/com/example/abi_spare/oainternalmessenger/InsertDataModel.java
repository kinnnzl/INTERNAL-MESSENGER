package com.example.abi_spare.oainternalmessenger;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class InsertDataModel{

	@SerializedName("MessengerID")
	private String MessengerID;

	@SerializedName("TayCode")
	private String TayCode;

	@SerializedName("TayType")
	private String TayType;

	@SerializedName("Doc")
	private List<DocItem> Doc;

	public void setMessengerID(String messengerID){
		this.MessengerID = messengerID;
	}

	public String getMessengerID(){
		return MessengerID;
	}

	public void setTayCode(String tayCode){
		this.TayCode = tayCode;
	}

	public String getTayCode(){
		return TayCode;
	}

	public void setTayType(String tayType){
		this.TayType = tayType;
	}

	public String getTayType(){
		return TayType;
	}

	public void setDoc(List<DocItem> doc){
		this.Doc = doc;
	}

	public List<DocItem> getDoc(){
		return Doc;
	}

	@Override
 	public String toString(){
		return 
			"InsertDataModel{" + 
			"messengerID = '" + MessengerID + '\'' +
			",tayCode = '" + TayCode + '\'' +
			",tayType = '" + TayType + '\'' +
			",Doc = '" + Doc + '\'' +
			"}";
		}
}