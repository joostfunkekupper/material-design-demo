package example.materialdesigndemo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DrawerFragment extends Fragment {

    /**
     * Remember the position of the selected item.
     */
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    private DrawerLayout mDrawerLayout;
    private View mFragmentContainerView;
    private ActionBarDrawerToggle mDrawerToggle;

    private List<DrawerMenuListAdapter.DrawerListItem> menuListItems;
    private List<DrawerMenuListAdapter.DrawerListItem> subMenuListItems;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        menuListItems = new ArrayList<DrawerMenuListAdapter.DrawerListItem>();
        menuListItems.add(new DrawerMenuListAdapter.DrawerListItem(null, "List header text sample"));
        menuListItems.add(new DrawerMenuListAdapter.DrawerListItem(R.drawable.ic_settings, "Bla bla"));
        menuListItems.add(new DrawerMenuListAdapter.DrawerListItem(R.drawable.ic_settings, "Bla bla"));
        menuListItems.add(new DrawerMenuListAdapter.DrawerListItem(R.drawable.ic_settings, "Bla bla"));

        subMenuListItems = new ArrayList<DrawerMenuListAdapter.DrawerListItem>();
        subMenuListItems.add(new DrawerMenuListAdapter.DrawerListItem(R.drawable.ic_settings, "Settings"));

        return inflater.inflate(R.layout.drawer_fragment, container, false);
    }

    public void setUp(Toolbar toolbar, int fragment_drawer, DrawerLayout viewById) {

        mFragmentContainerView = getActivity().findViewById(fragment_drawer);
        mDrawerLayout = viewById;

        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);

        // Defer code dependent on restoration of previous instance state.
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        setListView((ListView) mFragmentContainerView.findViewById(R.id.menu_list),
                new DrawerMenuListAdapter(getActivity(), menuListItems, true));

        setListView((ListView) mFragmentContainerView.findViewById(R.id.sub_menu_list),
                new DrawerMenuListAdapter(getActivity(), subMenuListItems, false));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Forward the new configuration the drawer toggle component.
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void setListView(ListView view, final DrawerMenuListAdapter adapter) {
        view.setAdapter(adapter);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelectedIndex(position);
            }
        });
    }
}
