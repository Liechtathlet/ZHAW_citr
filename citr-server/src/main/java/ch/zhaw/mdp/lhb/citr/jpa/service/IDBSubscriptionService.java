package ch.zhaw.mdp.lhb.citr.jpa.service;

import ch.zhaw.mdp.lhb.citr.jpa.entity.GroupDVO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.SubscriptionDVO;
import ch.zhaw.mdp.lhb.citr.jpa.entity.UserDVO;

import java.util.List;

/**
 * @author Simon Lang
 *
 * Interface for the Subscription-Persistence-Services
 */
public interface IDBSubscriptionService {
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
	 * @return List of subscriptions.
	 */
	List<SubscriptionDVO> getSubscriptionRequestByGroup(GroupDVO group);

	/**
	 * Checks if a user is already subscripted to a group.
	 *
	 * @param user User to check.
	 * @param group Group to check.
	 * @return TRUE = User is subscripted. | FALSE = User is not subscripted.
	 */
	boolean hasUserGroupSubscription(UserDVO user, GroupDVO group);
}
