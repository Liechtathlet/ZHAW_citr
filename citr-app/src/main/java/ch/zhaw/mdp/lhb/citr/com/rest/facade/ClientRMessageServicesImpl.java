/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.com.rest.facade;

import android.util.Log;
import ch.zhaw.mdp.lhb.citr.activities.CitrBaseActivity;
import ch.zhaw.mdp.lhb.citr.com.rest.AbstractClientRBaseServiceImpl;
import ch.zhaw.mdp.lhb.citr.com.rest.RESTBackgroundTask;
import ch.zhaw.mdp.lhb.citr.dto.MessageDTO;
import ch.zhaw.mdp.lhb.citr.exceptions.CitrCommunicationException;
import ch.zhaw.mdp.lhb.citr.exceptions.CitrExceptionTypeEnum;
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;
import ch.zhaw.mdp.lhb.citr.rest.IRMessageServices;
import ch.zhaw.mdp.lhb.citr.util.PropertyHelper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * @author Daniel Brun
 * 
 *         Client implementation of the Service-Interface {@link IRMessageServices}.
 */
public class ClientRMessageServicesImpl extends AbstractClientRBaseServiceImpl implements IRMessageServices {

	public static final String TAG = "ClientIRUserServicesImpl";

	/**
	 * Creates a new instance of this class.
	 * 
	 * @param anActivity
	 *            The underlining activity.
	 */
	public ClientRMessageServicesImpl(CitrBaseActivity anActivity) {
		super(anActivity);
	}


	/* (non-Javadoc)
	 * @see ch.zhaw.mdp.lhb.citr.rest.IRMessageServices#sendMessage(ch.zhaw.mdp.lhb.citr.dto.MessageDTO)
	 */
	@Override
	public ResponseObject<Boolean> createMessage(MessageDTO aMessage) {
		
		if(aMessage == null){
			throw new IllegalArgumentException("The argument aMessage must not be null");
		}
		
		preInit(RESTBackgroundTask.HTTP_POST_TASK);
		
		try {
			restTask.addParameter("message", mapper.writeValueAsString(aMessage));
		} catch (JsonProcessingException e) {
			Log.e(TAG, "Exception during JSON serialization prcoess.", e);
			throw new CitrCommunicationException(
					"Exception during JSON serialization prcoess.", e,
					CitrExceptionTypeEnum.SERIALIZATION_ERROR);
		}

		StringBuffer url = new StringBuffer();
		url.append(PropertyHelper.get("rest.service.message"));
		url.append("create");

		return execute(url.toString(), new TypeReference<ResponseObject<Boolean>>(){});
	}
	
}
