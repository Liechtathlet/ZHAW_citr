package ch.zhaw.mdp.lhb.citr.adapters;

/**
 * Created with IntelliJ IDEA.
 * User: michael
 * Date: 18.11.13
 * Time: 09:38
 * Adapter for used to show groups in view lists
 */


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ch.zhaw.mdp.lhb.citr.R;
import ch.zhaw.mdp.lhb.citr.dto.GroupDTO;

public class GroupAdapter extends ArrayAdapter<GroupDTO> {
    private final Context context;
    private final List<GroupDTO> groups;

    public GroupAdapter(Context context, List<GroupDTO> groups) {
        super(context, R.layout.row_group, groups);
        this.context = context;
        this.groups = groups;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_group, parent, false);

        TextView groupName = (TextView) rowView.findViewById(R.id.tvGroupName);
        TextView groupTextLeft = (TextView) rowView.findViewById(R.id.tvGroupTextLeft);
        TextView groupTextRight = (TextView) rowView.findViewById(R.id.tvGroupTextRight);

        groupName.setText(this.groups.get(position).getName());
        groupTextLeft.setText("links");
        groupTextRight.setText("rechts");

        // textView.setText(values[position]);

        return rowView;
    }
}
