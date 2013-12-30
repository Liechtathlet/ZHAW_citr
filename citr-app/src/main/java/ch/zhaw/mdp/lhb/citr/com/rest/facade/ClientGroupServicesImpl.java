/**
 *
 */
package ch.zhaw.mdp.lhb.citr.com.rest.facade;

import java.util.List;

import android.content.Context;
import android.util.Log;
import ch.zhaw.mdp.lhb.citr.com.rest.AbstractClientRBaseServiceImpl;
import ch.zhaw.mdp.lhb.citr.com.rest.RESTBackgroundTask;
import ch.zhaw.mdp.lhb.citr.dto.GroupDTO;
import ch.zhaw.mdp.lhb.citr.dto.MessageDTO;
import ch.zhaw.mdp.lhb.citr.dto.SubscriptionDTO;
import ch.zhaw.mdp.lhb.citr.exceptions.CitrCommunicationException;
import ch.zhaw.mdp.lhb.citr.exceptions.CitrExceptionTypeEnum;
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;
import ch.zhaw.mdp.lhb.citr.rest.GroupServices;
import ch.zhaw.mdp.lhb.citr.util.PropertyHelper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * @author Daniel Brun
 * 
 *         Client implementation of the Service-Interface {@link IRGroupServices}.
 */
public class ClientGroupServicesImpl extends AbstractClientRBaseServiceImpl
	implements GroupServices {

    public static final String TAG = "ClientIRGroupServicesImpl";

    /**
     * Creates a new instance of this class.
     * 
     * @param aContext The context.
     */
    public ClientGroupServicesImpl(Context aContext) {
	super(aContext);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ch.zhaw.mdp.lhb.citr.rest.IRGroupServices#createGroup(ch.zhaw.mdp.lhb.citr.dto.GroupDTO)
     */
    @Override
    public ResponseObject<Boolean> createGroup(GroupDTO aGroup) {
	if (aGroup == null) {
	    throw new IllegalArgumentException(
		    "The argument aGroup must not be null");
	}

	preInit(RESTBackgroundTask.HTTP_POST_TASK);

	try {
	    restTask.addParameter("group", mapper.writeValueAsString(aGroup));
	} catch (JsonProcessingException e) {
	    Log.e(TAG, "Exception during JSON serialization prcoess.", e);
	    throw new CitrCommunicationException(
		    "Exception during JSON serialization prcoess.", e,
		    CitrExceptionTypeEnum.SERIALIZATION_ERROR);
	}

	StringBuffer url = new StringBuffer();
	url.append(PropertyHelper.get("rest.service.group"));
	url.append("create");

	return execute(url.toString(),
		new TypeReference<ResponseObject<Boolean>>() {
		});
    }

    /*
     * (non-Javadoc)
     * 
     * @see ch.zhaw.mdp.lhb.citr.rest.IRGroupServices#getAllGroups()
     */
    @Override
    public ResponseObject<List<GroupDTO>> getAllGroups() {
	preInit(RESTBackgroundTask.HTTP_GET_TASK);

	StringBuffer url = new StringBuffer();
	url.append(PropertyHelper.get("rest.service.group"));
	url.append("list");

	return execute(url.toString(),
		new TypeReference<ResponseObject<List<GroupDTO>>>() {
		});
    }

    @Override
    public ResponseObject<List<SubscriptionDTO>> getGroupSubscriptionRequests(
	    int aGroupId) {
	preInit(RESTBackgroundTask.HTTP_POST_TASK);

	StringBuffer url = new StringBuffer();
	url.append(PropertyHelper.get("rest.service.group"));
	url.append(aGroupId);
	url.append("/getSubscriptions");

	return execute(url.toString(),
		new TypeReference<ResponseObject<List<SubscriptionDTO>>>() {
		});
    }

    /*
     * (non-Javadoc)
     * 
     * @see ch.zhaw.mdp.lhb.citr.rest.IRGroupServices#getGroupSubscriptions()
     */
    @Override
    public ResponseObject<List<SubscriptionDTO>> getUserSubscriptions() {
	preInit(RESTBackgroundTask.HTTP_GET_TASK);

	StringBuffer url = new StringBuffer();
	url.append(PropertyHelper.get("rest.service.group"));
	url.append("listSubscriptions");

	return execute(url.toString(),
		new TypeReference<ResponseObject<List<SubscriptionDTO>>>() {
		});
    }

    @Override
    public ResponseObject<List<GroupDTO>> getUserGroups() {
	preInit(RESTBackgroundTask.HTTP_GET_TASK);

	StringBuffer url = new StringBuffer();
	url.append(PropertyHelper.get("rest.service.group"));
	url.append("listOwnedGroups");

	return execute(url.toString(),
		new TypeReference<ResponseObject<List<GroupDTO>>>() {
		});
    }

    /*
     * (non-Javadoc)
     * 
     * @see ch.zhaw.mdp.lhb.citr.rest.IRGroupServices#searchGroup(ch.zhaw.mdp.lhb.citr.dto.GroupDTO)
     */
    @Override
    public ResponseObject<List<GroupDTO>> searchGroup(GroupDTO aGroup) {
	preInit(RESTBackgroundTask.HTTP_POST_TASK);

	try {
	    restTask.addParameter("group", mapper.writeValueAsString(aGroup));
	} catch (JsonProcessingException e) {
	    Log.e(TAG, "Exception during JSON serialization prcoess.", e);
	    throw new CitrCommunicationException(
		    "Exception during JSON serialization prcoess.", e,
		    CitrExceptionTypeEnum.SERIALIZATION_ERROR);
	}

	StringBuffer url = new StringBuffer();
	url.append(PropertyHelper.get("rest.service.group"));
	url.append("search");

	return execute(url.toString(),
		new TypeReference<ResponseObject<List<GroupDTO>>>() {
		});
    }

    /*
     * (non-Javadoc)
     * 
     * @see ch.zhaw.mdp.lhb.citr.rest.IRGroupServices#getMessage(int)
     */
    @Override
    public ResponseObject<MessageDTO> getNewestMessage(int aGroupId) {
	preInit(RESTBackgroundTask.HTTP_GET_TASK);

	StringBuffer url = new StringBuffer();
	url.append(PropertyHelper.get("rest.service.group"));
	url.append(aGroupId);
	url.append("/getNewestMessage");

	return execute(url.toString(),
		new TypeReference<ResponseObject<MessageDTO>>() {
		});
    }

    /*
     * (non-Javadoc)
     * 
     * @see ch.zhaw.mdp.lhb.citr.rest.IRGroupServices#subscribe(int)
     */
    @Override
    public ResponseObject<Boolean> subscribe(int aGroupId) {
	preInit(RESTBackgroundTask.HTTP_POST_TASK);
	
	StringBuffer url = new StringBuffer();
	url.append(PropertyHelper.get("rest.service.group"));
	url.append(aGroupId);
	url.append("/subscribe");

	return execute(url.toString(),
		new TypeReference<ResponseObject<Boolean>>() {
		});
    }

    /*
     * (non-Javadoc)
     * 
     * @see ch.zhaw.mdp.lhb.citr.rest.GroupServices#deleteGroup(int)
     */
    @Override
    public ResponseObject<Boolean> deleteGroup(int aGroupId) {
	preInit(RESTBackgroundTask.HTTP_DELETE_TASK);

	StringBuffer url = new StringBuffer();
	url.append(PropertyHelper.get("rest.service.group"));
	url.append(aGroupId);
	url.append("/delete");

	return execute(url.toString(),
		new TypeReference<ResponseObject<Boolean>>() {
		});
    }

    /*
     * (non-Javadoc)
     * 
     * @see ch.zhaw.mdp.lhb.citr.rest.GroupServices#updateGroupSubscriptionRequest(ch.zhaw.mdp.lhb.citr.dto.SubscriptionDTO)
     */
    @Override
    public ResponseObject<Boolean> updateGroupSubscriptionRequest(
	    SubscriptionDTO aSubscription) {
	preInit(RESTBackgroundTask.HTTP_PUT_TASK);

	try {
	    restTask.addParameter("subscription", mapper.writeValueAsString(aSubscription));
	} catch (JsonProcessingException e) {
	    Log.e(TAG, "Exception during JSON serialization prcoess.", e);
	    throw new CitrCommunicationException(
		    "Exception during JSON serialization prcoess.", e,
		    CitrExceptionTypeEnum.SERIALIZATION_ERROR);
	}
	
	StringBuffer url = new StringBuffer();
	url.append(PropertyHelper.get("rest.service.group"));
	url.append("/updateSubscription");

	return execute(url.toString(),
		new TypeReference<ResponseObject<Boolean>>() {
		});
    }
}
