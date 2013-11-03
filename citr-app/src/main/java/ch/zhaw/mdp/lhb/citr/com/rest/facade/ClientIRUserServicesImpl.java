/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.com.rest.facade;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import android.os.AsyncTask;
import android.util.Log;
import ch.zhaw.mdp.lhb.citr.activities.CitrBaseActivity;
import ch.zhaw.mdp.lhb.citr.com.rest.RESTBackgroundTask;
import ch.zhaw.mdp.lhb.citr.dto.UserDTO;
import ch.zhaw.mdp.lhb.citr.exceptions.CitrCommunicationException;
import ch.zhaw.mdp.lhb.citr.exceptions.CitrExceptionTypeEnum;
import ch.zhaw.mdp.lhb.citr.rest.IRUserServices;
import ch.zhaw.mdp.lhb.citr.util.PropertyHelper;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Daniel Brun
 * 
 *         Clean implementation of the Service-Interface {@link IRUserServices}.
 */
public class ClientIRUserServicesImpl implements IRUserServices {

	public static final String TAG = "ClientIRUserServicesImpl";

	private CitrBaseActivity activity;
	private RESTBackgroundTask restTask;
	private ObjectMapper mapper;

	/**
	 * Creates a new instance of this class.
	 * 
	 * @param anActivity
	 *            The underlining activity.
	 */
	public ClientIRUserServicesImpl(CitrBaseActivity anActivity) {
		activity = anActivity;

		mapper = new ObjectMapper();
		restTask = new RESTBackgroundTask(anActivity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.zhaw.mdp.lhb.citr.rest.IRUserServices#getUser(java.lang.String)
	 */
	@Override
	public UserDTO getUser(String anOpenId) {
		restTask.setHttpRequestType(RESTBackgroundTask.HTTP_GET_TASK);

		restTask.addParameter("openId", anOpenId);

		StringBuffer url = new StringBuffer();
		url.append(PropertyHelper.get("rest.service.user"));
		url.append("details");

		String result = execute(url.toString());

		return map(result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.zhaw.mdp.lhb.citr.rest.IRUserServices#registerUser(ch.zhaw.mdp.lhb
	 * .citr.dto.UserDTO)
	 */
	@Override
	public boolean registerUser(UserDTO aUser) {
		restTask.setHttpRequestType(RESTBackgroundTask.HTTP_POST_TASK);

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

		String result = execute(url.toString());

		// TODO: Implement return value.
		Log.w(TAG, "MY RESULT: " + result);

		return true;
	}

	/**
	 * Executes the prepared call with the given url.
	 * 
	 * @param aUrl
	 *            The url to call.
	 * @return the result string.
	 */
	private String execute(String aUrl) {
		if (aUrl == null) {
			throw new IllegalArgumentException(
					"The argument aUrl must not be null!");
		}

		String result = null;
		try {
			AsyncTask<String, Integer, String> task = restTask.execute(aUrl);
			if (task != null) {
				result = task.get();
			}
		} catch (InterruptedException e) {
			Log.e(TAG, "Exception during task processing", e);
			throw new CitrCommunicationException(
					"Exception during task processing", e,
					CitrExceptionTypeEnum.BACKGROUND_ERROR);
		} catch (ExecutionException e) {
			Log.e(TAG, "Exception during task processing", e);
			throw new CitrCommunicationException(
					"Exception during task processing", e,
					CitrExceptionTypeEnum.BACKGROUND_ERROR);
		}

		return result;
	}

	/**
	 * Mapps the given string to an object.
	 * 
	 * @param aResult
	 *            The string to map.
	 * @return the mapped object.
	 */
	private UserDTO map(String aResult) {
		UserDTO user = null;

		if (aResult != null) {
			try {
				user = mapper.readValue(aResult, new TypeReference<UserDTO>() {
				});
			} catch (JsonParseException e) {
				Log.e(TAG, "Exception during parse process of JSON-Data", e);
				throw new CitrCommunicationException(
						"Exception during parse process of JSON-Data", e,
						CitrExceptionTypeEnum.DESERIALIZATION_ERROR);
			} catch (JsonMappingException e) {
				Log.e(TAG, "Exception during map process of JSON-Data", e);
				throw new CitrCommunicationException(
						"Exception during map process of JSON-Data", e,
						CitrExceptionTypeEnum.DESERIALIZATION_ERROR);
			} catch (IOException e) {
				Log.e(TAG, "Exception during io process of JSON-Data", e);
				throw new CitrCommunicationException(
						"Exception during io process of JSON-Data", e,
						CitrExceptionTypeEnum.DESERIALIZATION_ERROR);
			}
		}
		return user;
	}
}
