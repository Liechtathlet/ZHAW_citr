/**
 *
 */
package ch.zhaw.mdp.lhb.citr.com.rest.facade;

import java.util.List;

import android.util.Log;
import ch.zhaw.mdp.lhb.citr.activities.CitrBaseActivity;
import ch.zhaw.mdp.lhb.citr.com.rest.AbstractClientRBaseServiceImpl;
import ch.zhaw.mdp.lhb.citr.com.rest.RESTBackgroundTask;
import ch.zhaw.mdp.lhb.citr.dto.GroupDTO;
import ch.zhaw.mdp.lhb.citr.exceptions.CitrCommunicationException;
import ch.zhaw.mdp.lhb.citr.exceptions.CitrExceptionTypeEnum;
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;
import ch.zhaw.mdp.lhb.citr.rest.IRGroupServices;
import ch.zhaw.mdp.lhb.citr.util.PropertyHelper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * @author Daniel Brun
 *
 *         Client implementation of the Service-Interface {@link IRGroupServices}.
 */
public class ClientRGroupServicesImpl extends AbstractClientRBaseServiceImpl
        implements IRGroupServices {

    public static final String TAG = "ClientIRGroupServicesImpl";

    /**
     * Creates a new instance of this class.
     *
     * @param anActivity The underlining activity.
     */
    public ClientRGroupServicesImpl(CitrBaseActivity anActivity) {
        super(anActivity);
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

        return execute(url.toString(), new TypeReference<ResponseObject<Boolean>>(){});
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

        return execute(url.toString(), new TypeReference<ResponseObject<List<GroupDTO>>>(){});
    }

    /*
     * (non-Javadoc)
     * 
     * @see ch.zhaw.mdp.lhb.citr.rest.IRGroupServices#createRequestForGroupSubscription(int)
     */
    @Override
    public ResponseObject<Boolean> createRequestForGroupSubscription(
            int aGroupId) {
        preInit(RESTBackgroundTask.HTTP_POST_TASK);

        StringBuffer url = new StringBuffer();
        url.append(PropertyHelper.get("rest.service.group"));
        url.append(aGroupId);
        url.append("/requestSubscription");

        return execute(url.toString(), new TypeReference<ResponseObject<Boolean>>(){});
    }

    /*
     * (non-Javadoc)
     * 
     * @see ch.zhaw.mdp.lhb.citr.rest.IRGroupServices#getGroupSubscriptions()
     */
    @Override
    public ResponseObject<List<GroupDTO>> getGroupSubscriptions() {
        preInit(RESTBackgroundTask.HTTP_POST_TASK);

        StringBuffer url = new StringBuffer();
        url.append(PropertyHelper.get("rest.service.group"));
        url.append("/listSubscriptions");

        return execute(url.toString(), new TypeReference<ResponseObject<List<GroupDTO>>>(){});
    }

	@Override
	public ResponseObject<List<GroupDTO>> getOwnedGroup() {
		return null;    
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
        url.append("/search");

        return execute(url.toString(), new TypeReference<ResponseObject<List<GroupDTO>>>(){});
    }
}
