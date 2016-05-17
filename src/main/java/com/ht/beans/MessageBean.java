/**
 * 
 */
package com.ht.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author NITISH
 *
 */
@XmlRootElement
public class MessageBean {

	private String message;

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param message
	 */
	public MessageBean(String message) {
		this.message = message;
	}

	/**
	 * 
	 */
	public MessageBean() {
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MessageBean [message=" + message + "]";
	}
	
}
