/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.exceptions;

/**
 * @author Daniel Brun
 *
 * Enumeration for exception types.
 */
public enum CitrExceptionTypeEnum {

	UNKNOWN,
	CONNECTION_NOT_AVAILABLE,
	CONNECTION_UNKNOWN_ERROR,
	CONNECTION_RESPONSE_ERROR,
	CONNECTION_REQUEST_ERROR,
	SERIALIZATION_ERROR,
	DESERIALIZATION_ERROR,
	BACKGROUND_ERROR;
}
