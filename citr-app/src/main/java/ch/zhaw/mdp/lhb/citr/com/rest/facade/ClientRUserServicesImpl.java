/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.com.rest.facade;

import java.util.ArrayList;

import android.util.Log;
import ch.zhaw.mdp.lhb.citr.activities.CitrBaseActivity;
import ch.zhaw.mdp.lhb.citr.com.rest.AbstractClientRBaseServiceImpl;
import ch.zhaw.mdp.lhb.citr.com.rest.RESTBackgroundTask;
import ch.zhaw.mdp.lhb.citr.dto.GroupDTO;
import ch.zhaw.mdp.lhb.citr.dto.UserDTO;
import ch.zhaw.mdp.lhb.citr.exceptions.CitrCommunicationException;
import ch.zhaw.mdp.lhb.citr.exceptions.CitrExceptionTypeEnum;
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;
import ch.zhaw.mdp.lhb.citr.rest.IRUserServices;
import ch.zhaw.mdp.lhb.citr.util.PropertyHelper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * @author Daniel Brun
 * 
 *         Client implementation of the Service-Interface {@link IRUserServices}
 *         .
 */
public class ClientRUserServicesImpl extends AbstractClientRBaseServiceImpl
		implements IRUserServices {

	public static final String TAG = "ClientIRUserServicesImpl";

	/**
	 * Creates a new instance of this class.
	 * 
	 * @param anActivity
	 *            The underlining activity.
	 */
	public ClientRUserServicesImpl(CitrBaseActivity anActivity) {
		super(anActivity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.zhaw.mdp.lhb.citr.rest.IRUserServices#getUser(java.lang.String)
	 */
	@Override
	public ResponseObject<UserDTO> loginUser(String anOpenId) {
		preInit(RESTBackgroundTask.HTTP_GET_TASK);

		StringBuffer url = new StringBuffer();
		url.append(PropertyHelper.get("rest.service.user"));
		url.append(anOpenId);
		url.append("/login");

		return execute(url.toString(), new TypeReference<ResponseObject<UserDTO>>(){});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.zhaw.mdp.lhb.citr.rest.IRUserServices#registerUser(ch.zhaw.mdp.lhb
	 * .citr.dto.UserDTO)
	 */
	@Override
	public ResponseObject<Boolean> registerUser(UserDTO aUser) {
		preInit(RESTBackgroundTask.HTTP_POST_TASK);

		try {
			restTask.addParameter("user", mapper.writeValueAsString(aUser));
		} catch (JsonProcessingException e) {
			Log.e(TAG, "Exception during JSON serialization prcoess.", e);
			throw new CitrCommunicationException(
					"Exception during JSON serialization prcoess.", e,
					CitrExceptionTypeEnum.SERIALIZATION_ERROR);
		}

		StringBuffer url = new StringBuffer();
		url.append(PropertyHelper.get("rest.service.user"));
		url.append("registerUser");

		return execute(url.toString(), new TypeReference<ResponseObject<Boolean>>(){});
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.mdp.lhb.citr.rest.IRUserServices#getGroups()
	 */
	@Override
	public ArrayList<GroupDTO> getGroups() {
	    // TODO FIX-It
	    return null;
	}

}
