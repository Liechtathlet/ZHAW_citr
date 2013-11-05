/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.dto;

import java.util.Date;
import java.util.List;

/**
 * @author Daniel Brun
 *
 * DTO-Class for 'Message'.
 */
public class MessageDTO {

	private String groupId;
	
	private List<String> tags;
	
	private long id;
	private Date sendDate;
	private String message;

}
