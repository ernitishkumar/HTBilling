package com.ht.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorBean {
	
	private String errorMessage;
	
	public ErrorBean(){
		
	}
	
	public ErrorBean(String errorMessage){
		this.errorMessage=errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
