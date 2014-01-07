package ch.zhaw.mdp.lhb.citr.jpa.service;

import java.util.List;

import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.SubscriptionDVO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.UserDVO;

/**
 * @author Simon Lang
 * 
 *         Interface for the Subscription-Persistence-Services
 */
public interface SubscriptionRepository {
    /**
     * Creates a new subscription.
     * 
     * @param aSubscription The subscription to save.
     * @return True if the subscription was saved successfully.
     */
    public boolean save(SubscriptionDVO aSubscription);

    /**
     * Gets all subscription requests for a particular group.
     * 
     * @param aGroup The group to get the subscriptions to.
     * @param aState The state.
     * @return List of subscriptions.
     */
    public List<SubscriptionDVO> getSubscriptionByGroup(GroupDVO aGroup,
	    SubscriptionDVO.State aState);

    /**
     * Checks if a user is already subscripted to a group.
     * 
     * @param aUser User to check.
     * @param aGroup Group to check.
     * @return TRUE = User is subscripted. | FALSE = User is not subscripted.
     */
    public boolean hasUserGroupSubscription(UserDVO aUser, GroupDVO aGroup);

    /**
     * Gets a subscription by user and group.
     * 
     * @param aUserDVO The user.
     * @param aGroupDVO The group.
     * @return The matching subscription.
     */
    public SubscriptionDVO getSubscription(UserDVO aUserDVO, GroupDVO aGroupDVO);

    /**
     * Updates the state on the subscription.
     * 
     * @param aSubscriptionDVO Subscription to update.
     * @param aState State to set.
     */
    public void updateState(SubscriptionDVO aSubscriptionDVO,
	    SubscriptionDVO.State aState);

    /**
     * Removes a subscription.
     * 
     * @param aSubscriptionDVO Subscription to remove.
     * @return true if the entity could be removed successfully.
     */
    public boolean remove(SubscriptionDVO aSubscriptionDVO);
}
