/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.exceptions;

/**
 * @author Daniel Brun
 *
 * Base-Citr-Exception
 */
public class CitrException extends RuntimeException {

	/**
	 * Generated Serial Version UID.
	 */
	private static final long serialVersionUID = -1221744245942561891L;

	protected CitrExceptionTypeEnum type;
	
	/**
	 * Creates a new instance of this class.
	 * 
	 * @param aMessage The message.
	 * @param aCause The cause.
	 * @param tType The type.
	 */
	public CitrException(String aMessage, Throwable aCause,CitrExceptionTypeEnum aType) {
		super(aMessage, aCause);
		aType = type;
	}

	
	/**
	 * Creates a new instance of this class.
	 * 
	 * @param aMessage The message.
	 * @param tType The type.
	 */
	public CitrException(String aMessage,CitrExceptionTypeEnum aType) {
		super(aMessage);
		aType = type;
	}


	/**
	 * @return the type
	 */
	public CitrExceptionTypeEnum getType() {
		return type;
	}
	
}
