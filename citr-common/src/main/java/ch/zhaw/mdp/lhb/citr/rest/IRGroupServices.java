/**
 * 
 */
package ch.zhaw.mdp.lhb.citr.rest;

import java.util.List;

import ch.zhaw.mdp.lhb.citr.dto.GroupDTO;
import ch.zhaw.mdp.lhb.citr.dto.MessageDTO;
import ch.zhaw.mdp.lhb.citr.dto.SubscriptionDTO;
import ch.zhaw.mdp.lhb.citr.response.ResponseObject;

/**
 * @author Daniel Brun
 *
 * Interface for the 'Group' Rest-Services.
 */
public interface IRGroupServices {

	/**
	 * Gets a list with all available groups.
	 * 
	 * @return a list with all groups.
	 */
	public ResponseObject<List<GroupDTO>> getAllGroups();
	
	/**
	 * Looks for groups which match the given group.
	 * 
	 * @param aGroup The group to search.
	 * @return A list with all matching groups.
	 */
	public ResponseObject<List<GroupDTO>> searchGroup(GroupDTO aGroup);

	/**
	 * Creates a new group.'Group'.
	 * 
	 * @param aGroup the group to create.
	 * @return true if the group could be created successfully.
	 */
	public ResponseObject<Boolean> createGroup(GroupDTO aGroup);
	
	/**
	 * Gets the group subscriptions of a user.
	 * 
	 * @return All group subscriptions of the current user.
	 */
	public ResponseObject<List<SubscriptionDTO>> getUserSubscriptions();

	/**
	 * Gets the groups of a user.
	 *
	 * @return All group the user owns.
	 */
	public ResponseObject<List<GroupDTO>> getOwnedGroup();
	
	
	/**
	 * Creates a request for a subscription for a private group.
	 * 
	 * @param aGroupId The id of the group
	 * @return True if the request could be created successfully.
	 */
	public ResponseObject<Boolean> createRequestForGroupSubscription(int aGroupId);


	/**
	 * Gets the subscriptions for a group.
	 *
	 * @param aGroupId The id of the group
	 * @return True if the request could be created successfully.
	 */
	public ResponseObject<List<SubscriptionDTO>> getGroupSubscriptions(int aGroupId);


	/**
	 * Subscribe to a group.
	 *
	 * @param aGroupId The group to subscribe to.
	 * @return True if the subscribed successfully.
	 */
	public ResponseObject<Boolean> subscribe(int aGroupId);

	/**
	 * Gets the newest messages of a group.
	 *
	 * @param aGroupId The id of the group.
	 * @return Newest message of the group.
	 */
	public ResponseObject<MessageDTO> getNewestMessage(int aGroupId);
}
