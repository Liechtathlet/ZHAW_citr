/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.response;

import java.io.Serializable;

/**
 * @author Daniel Brun
 *
 * Response-Object for REST-Requests.
 */
public class ResponseObject<T> implements Serializable{

	/**
	 * Generated Serial-Version UID.
	 */
	private static final long serialVersionUID = 1593002438651489353L;
	
	private T responseObject;
	private boolean successfull;
	private String displayMessage;
	
	/**
	 * Default Constructor
	 */
	public ResponseObject() {
		super();
	}

	/**
	 * Creates an ew instance of this class.
	 * 
	 * @param aResponseObject The response object.
	 * @param isSuccessfull True if the request can be considered as successfull, false otherwise.
	 * @param aDisplayMessage The message to display.
	 */
	public ResponseObject(T aResponseObject, boolean isSuccessfull,
			String aDisplayMessage) {
		super();
		responseObject = aResponseObject;
		successfull = isSuccessfull;
		displayMessage = aDisplayMessage;
	}

	/**
	 * @return the responseObject
	 */
	public T getResponseObject() {
		return responseObject;
	}

	/**
	 * @return the successfull
	 */
	public boolean isSuccessfull() {
		return successfull;
	}

	/**
	 * @return the displayMessage
	 */
	public String getDisplayMessage() {
		return displayMessage;
	}
	
}
