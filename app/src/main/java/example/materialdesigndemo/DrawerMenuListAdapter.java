package example.materialdesigndemo;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DrawerMenuListAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;

    private ArrayList<DrawerListItem> mData = new ArrayList<DrawerListItem>();

    private int selectedIndex;

    private boolean mHighlightSelection;

    public DrawerMenuListAdapter(Context context, List<DrawerListItem> data, boolean highlightSelection) {
        mLayoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mData.addAll(data);
        selectedIndex = -1;
        mHighlightSelection = highlightSelection;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View menuRow = mLayoutInflater.inflate(R.layout.menu_list_item, viewGroup, false);

        ImageView menuImage = (ImageView) menuRow.findViewById(R.id.menu_item_image);
        TextView textView = (TextView) menuRow.findViewById(R.id.menu_item_text);

        if (mData.get(position).resourceImageId != null) {
            menuImage.setImageDrawable(
                    viewGroup.getResources().getDrawable(mData.get(position).resourceImageId));
        } else {
            menuImage.setVisibility(View.GONE);
            textView.setTextColor(viewGroup.getResources().getColor(android.R.color.darker_gray));
        }

        textView.setText(mData.get(position).itemText);

        if(selectedIndex!= -1 && position == selectedIndex && mHighlightSelection) {
            textView.setTextColor(Color.parseColor("#5677fc"));
        }

        return menuRow;
    }

    /**
     * Ensure that a header list item cannot be selected
     *
     * @param position
     * @return
     */
    @Override
    public boolean isEnabled(int position) {
        return mData.get(position).resourceImageId != null;
    }

    public void setSelectedIndex(int position) {
        this.selectedIndex = position;
        notifyDataSetChanged();
    }

    public static class DrawerListItem {
        public Integer resourceImageId;
        public String itemText;

        public DrawerListItem (Integer resourceImageId, String itemText) {
            this.resourceImageId = resourceImageId;
            this.itemText = itemText;
        }
    }
}
