/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.exceptions;

/**
 * @author Daniel Brun
 *
 * Exception for 'Communication'-Exception
 */
public class CitrCommunicationException extends CitrException {

	/**
	 * Generated Serial version UID.
	 */
	private static final long serialVersionUID = 7756445376457351873L;
	
	/**
	 * Creates a new instance of this class.
	 * 
	 * @param aMessage The message.
	 * @param aCause The cause.
	 */
	public CitrCommunicationException(String aMessage, Throwable aCause) {
		super(aMessage, aCause,CitrExceptionTypeEnum.CONNECTION_UNKNOWN_ERROR);
	}

	/**
	 * Creates a new instance of this class.
	 * 
	 * @param aMessage The message.
	 * @param tType The type.
	 */
	public CitrCommunicationException(String aMessage,
			CitrExceptionTypeEnum aType) {
		super(aMessage, aType);
	}

	/**
	 * Creates a new instance of this class.
	 * 
	 * @param aMessage The message.
	 * @param aCause The cause.
	 * @param tType The type.
	 */
	public CitrCommunicationException(String aMessage, Throwable aCause,
			CitrExceptionTypeEnum aType) {
		super(aMessage, aCause, aType);
	}
}
