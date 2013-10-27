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
	 * @param aMessage The message.
	 * @param aCause The cause.
	 */
	public CitrCommunicationException(String aMessage, Throwable aCause) {
		super(aMessage, aCause);
	}

}
