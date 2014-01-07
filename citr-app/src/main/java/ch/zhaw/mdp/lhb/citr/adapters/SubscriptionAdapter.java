package ch.zhaw.mdp.lhb.citr.adapters;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ch.zhaw.mdp.lhb.citr.R;
import ch.zhaw.mdp.lhb.citr.dto.SubscriptionDTO;
import ch.zhaw.mdp.lhb.citr.enumeration.SubscriptionStateEnum;

/**
 * @author Michael Hadorn Date: 18.12.13 Time: 09:38
 * 
 *         Adapter to show subscriptions
 */
public class SubscriptionAdapter extends ArrayAdapter<SubscriptionDTO> {

    /**
     * relative context
     */
    private final Context context;

    /**
     * user state text
     */
    private final String userStateText;

    /**
     * color of text
     */
    private final int textColor;

    /**
     * list of all subscription (getted by rest)
     */
    private List<SubscriptionDTO> subscriptionDTOs;

    /**
     * single subscription
     */
    private SubscriptionDTO subscriptionDTO;

    /**
     * the adapter for groups
     * 
     * @param context
     * @param subscriptionDTOs
     */
    public SubscriptionAdapter(Context context,
	    List<SubscriptionDTO> subscriptionDTOs) {
	super(context, R.layout.row_group, subscriptionDTOs);
	this.context = context;
	this.subscriptionDTOs = subscriptionDTOs;
	userStateText = "";
	textColor = Color.BLACK;
    }

    /**
     * the creator for each item in a listview
     * 
     * @param position current position of item (to collect data)
     * @param convertView
     * @param parent the parent object
     * @return one row for the listview
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

	LayoutInflater inflater = (LayoutInflater) context
		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	View rowView = inflater.inflate(R.layout.row_group, parent, false);

	TextView groupName = (TextView) rowView.findViewById(R.id.tvGroupName);
	TextView groupTextLeft = (TextView) rowView
		.findViewById(R.id.tvGroupTextLeft);
	TextView groupTextRight = (TextView) rowView
		.findViewById(R.id.tvGroupTextRight);
	subscriptionDTO = subscriptionDTOs.get(position);
	SubscriptionStateEnum state = subscriptionDTO.getState();

	String userStateText = "";
	int textColor = Color.BLACK;

	switch (state) {
	    case APPROVED:
		textColor = Color.parseColor("#669900");
		userStateText = "Mitglied";
		break;
	    case OPEN:
		textColor = Color.parseColor("#FF8800");
		userStateText = "Anfrage ausstehend";
		break;
	}

	// defaults android color from: http://developer.android.com/design/style/color.html
	groupName.setText(subscriptionDTO.getGroup().getName());
	groupTextLeft.setText("");
	groupTextRight.setText(userStateText);
	groupTextRight.setTextColor(textColor);

	return rowView;
    }

    /**
     * @param aSubscriptionDTOs the subscriptionDTOs to set
     */
    public void setSubscriptionDTOs(List<SubscriptionDTO> aSubscriptionDTOs) {
	subscriptionDTOs = aSubscriptionDTOs;
    }

}
