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
import ch.zhaw.mdp.lhb.citr.dto.MessageDTO;
import ch.zhaw.mdp.lhb.citr.exceptions.CitrCommunicationException;
import ch.zhaw.mdp.lhb.citr.exceptions.CitrExceptionTypeEnum;
import ch.zhaw.mdp.lhb.citr.rest.IRMessageServices;
import ch.zhaw.mdp.lhb.citr.util.PropertyHelper;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Daniel Brun
 * 
 *         Client implementation of the Service-Interface {@link IRMessageServices}.
 */
public class ClientIRMessageServicesImpl implements IRMessageServices {

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
	public ClientIRMessageServicesImpl(CitrBaseActivity anActivity) {
		activity = anActivity;

		mapper = new ObjectMapper();
	}


	/* (non-Javadoc)
	 * @see ch.zhaw.mdp.lhb.citr.rest.IRMessageServices#sendMessage(ch.zhaw.mdp.lhb.citr.dto.MessageDTO)
	 */
	@Override
	public boolean sendMessage(MessageDTO aMessage) {
		
		if(aMessage == null){
			throw new IllegalArgumentException("The argument aMessage must not be null");
		}
		
		preInit(RESTBackgroundTask.HTTP_GET_TASK);
		
		try {
			restTask.addParameter("message", mapper.writeValueAsString(aMessage));
		} catch (JsonProcessingException e) {
			Log.e(TAG, "Exception during JSON serialization prcoess.", e);
			throw new CitrCommunicationException(
					"Exception during JSON serialization prcoess.", e,
					CitrExceptionTypeEnum.SERIALIZATION_ERROR);
		}

		StringBuffer url = new StringBuffer();
		url.append(PropertyHelper.get("rest.service.user"));
		url.append("details");

		String result = execute(url.toString());

		Log.e(TAG,"TEST: " + result);
		
		return true;
	}

	/**
	 * Performs the pre initialization.
	 * 
	 * @param aHttpType The http type.
	 */
	private void preInit(int aHttpType){
		restTask = new RESTBackgroundTask(activity);
		restTask.setHttpRequestType(aHttpType);
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
	private MessageDTO map(String aResult) {
		MessageDTO msg = null;

		if (aResult != null && aResult.trim().length() > 0) {
			try {
				msg = mapper.readValue(aResult, new TypeReference<MessageDTO>() {
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
		return msg;
	}
}
