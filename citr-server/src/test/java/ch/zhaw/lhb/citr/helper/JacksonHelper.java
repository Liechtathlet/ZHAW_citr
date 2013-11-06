/**
 * 
 */
package ch.zhaw.lhb.citr.helper;

import ch.zhaw.mdp.lhb.citr.dto.MessageDTO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Daniel Brun
 * 
 *         Creates some JSON-Objects via Jackson.
 */
public class JacksonHelper {

	private ObjectMapper mapper;

	/**
	 * Creates a new instance of this class.
	 */
	public JacksonHelper() {
		mapper = new ObjectMapper();
	}

	public String createMessage(MessageDTO aMessage) {

		try {
			return mapper.writeValueAsString(aMessage);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
