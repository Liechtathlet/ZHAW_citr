package ch.zhaw.mdp.lhb.citr.adapters;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import ch.zhaw.mdp.lhb.citr.R;
import ch.zhaw.mdp.lhb.citr.dto.GroupDTO;

/**
 * @author michael
 * Date: 18.11.13
 * Time: 09:38
 *
 * Adapter for used to show groups in view lists. Generate the single items for the listview
 */
public class GroupAdapter extends ArrayAdapter<GroupDTO> {

    /**
     * Current context (used for inflater)
     */
    private final Context context;

    /**
     * Container with groups
     */
    private final List<GroupDTO> groups;

    /**
     * the adapter for groups
     * @param context
     * @param groups
     */
    public GroupAdapter(Context context, List<GroupDTO> groups) {
        super(context, R.layout.row_group, groups);
        this.context = context;
        this.groups = groups;
    }

    /**
     * the creator for each item in a listview
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

        RelativeLayout rowEntry = (RelativeLayout) rowView.findViewById(R.id.rlRowGroup);
        TextView groupName = (TextView) rowView.findViewById(R.id.tvGroupName);
        TextView groupTextLeft = (TextView) rowView.findViewById(R.id.tvGroupTextLeft);
        TextView groupTextRight = (TextView) rowView.findViewById(R.id.tvGroupTextRight);

        // defaults android color from: http://developer.android.com/design/style/color.html
        int bgInt = Color.WHITE;
        int textColor = Color.BLACK;
        String userStateText = "";

        GroupDTO.State userGroupState = (GroupDTO.State) this.groups.get(position).getState();
        if (userGroupState == null) {
            userGroupState = GroupDTO.State.none;
        }

        switch (userGroupState) {
            case approved:
                // bgInt = Color.parseColor("#99CC00");
                textColor = Color.parseColor("#669900");
                userStateText = "Mitglied";
                break;
            case open:
                // bgInt = Color.parseColor("#FFBB33");
                textColor = Color.parseColor("#FF8800");
                userStateText = "Anfrage ausstehend";
                break;
            default: // none
                // bgInt = Color.parseColor("#FF4444");
                // textColor = Color.parseColor("#CC0000");
                userStateText = "";
                break;
        }

        // rowEntry.setBackgroundColor(bgInt);

        groupName.setText(this.groups.get(position).getName());
        groupTextLeft.setText("");
        groupTextRight.setText(userStateText);
        groupTextRight.setTextColor(textColor);

        // textView.setText(values[position]);

        return rowView;
    }
}
