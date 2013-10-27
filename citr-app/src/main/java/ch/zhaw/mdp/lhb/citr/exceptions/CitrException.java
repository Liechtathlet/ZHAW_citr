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

	/**
	 * @param aMessage The message.
	 * @param aCause The cause.
	 */
	public CitrException(String aMessage, Throwable aCause) {
		super(aMessage, aCause);
	}
	
}
