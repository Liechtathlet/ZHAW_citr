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
public interface GroupServices {

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
	 * Deletes the group with the given id.
	 * 
	 * @param aGroupId the group to delete.
	 * @return true if the group could be deleted successfully.
	 */
	public ResponseObject<Boolean> deleteGroup(int aGroupId);
	
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
	public ResponseObject<List<GroupDTO>> getUserGroups();
	
	/**
	 * Gets the subscription request for a group.
	 *
	 * @param aGroupId The id of the group
	 * @return True if the request could be created successfully.
	 */
	public ResponseObject<List<SubscriptionDTO>> getGroupSubscriptionRequests(int aGroupId);

	/**
	 * Updates the given subscription.
	 * 
	 * @param aSubscription The subscription to update.
	 * @return True if the update was successfull.
	 */
	public ResponseObject<Boolean> updateGroupSubscriptionRequest(SubscriptionDTO aSubscription);
	
	/**
	 * Subscribe to a group.
	 *
	 * @param aGroupId The group to subscribe to.
	 * @return True if the subscription was successfully.
	 */
	public ResponseObject<Boolean> subscribe(int aGroupId);

	/**
	 * Gets the newest messages of a group.
	 *
	 * @param aGroupId The id of the group.
	 * @return Newest message of the group.
	 */
	public ResponseObject<MessageDTO> getNewestMessage(int aGroupId);

	/**
	 * Accepts a subscription request.
	 * @param aGroupId The id of the group.
	 * @param aUserId The id of the user.
	 * @return True if the subscription has been accepted.
	 */
	public ResponseObject<Boolean> acceptSubscription(int aGroupId, int aUserId);

	/**
	 * Declines a subscription request.
	 * @param aGroupId The id of the group.
	 * @param aUserId The id of the user.
	 * @return True if the subscription has been declined.
	 */
	public ResponseObject<Boolean> declineSubscription(int aGroupId, int aUserId);
}
