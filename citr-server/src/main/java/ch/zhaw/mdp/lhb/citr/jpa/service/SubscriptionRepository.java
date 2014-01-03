package ch.zhaw.mdp.lhb.citr.jpa.service;

import java.util.List;

import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.SubscriptionDVO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.UserDVO;

/**
 * @author Simon Lang
 *
 * Interface for the Subscription-Persistence-Services
 */
public interface SubscriptionRepository {
	/**
	 * Creates a new subscription.
	 *
	 * @param subscription The subscription to save.
	 * @return True if the subscription was saved successfully.
	 */
	boolean save(SubscriptionDVO subscription);

	/**
	 * Gets all subscription requests for a particular group.
	 * @param group The group to get the subscriptions to.
	 * @param aState The state.
	 * @return List of subscriptions.
	 */
	List<SubscriptionDVO> getSubscriptionByGroup(GroupDVO group, SubscriptionDVO.State aState);

	/**
	 * Checks if a user is already subscripted to a group.
	 *
	 * @param user User to check.
	 * @param group Group to check.
	 * @return TRUE = User is subscripted. | FALSE = User is not subscripted.
	 */
	boolean hasUserGroupSubscription(UserDVO user, GroupDVO group);

	/**
	 * Gets a subscription by user and group.
	 * @param userDVO The user.
	 * @param groupDVO The group.
	 * @return The matching subscription.
	 */
	SubscriptionDVO getSubscription(UserDVO userDVO, GroupDVO groupDVO);

	/**
	 * Updates the state on the subscription.
	 * @param subscriptionDVO Subscription to update.
	 * @param state State to set.
	 */
	void updateState(SubscriptionDVO subscriptionDVO, SubscriptionDVO.State state);

	/**
	 * Removes a subscription.
	 * @param subscriptionDVO Subscription to remove.
	 */
	void remove(SubscriptionDVO subscriptionDVO);
}
