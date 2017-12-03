package com.studyx.urgames.code2job;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rahula on 13/11/17.
 */

public class menu extends AppCompatActivity {
    private String[] mPlanetTitles;
    protected DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private Intent drawer;
    RelativeLayout mDrawerPane;
    private TextView name;
    private ActionBarDrawerToggle mDrawerToggle;
    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);



        mNavItems.add(new NavItem("My Courses", "let the learning begin", R.drawable.ic_launcher_background));
        mNavItems.add(new NavItem("Discover", "Whats Happening ", R.drawable.ic_launcher_background));
        mNavItems.add(new NavItem("Account", "Know yourself", R.drawable.ic_launcher_background));


        // DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);


                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);


        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        mDrawerList = (ListView) findViewById(R.id.navList);
        name=(TextView)findViewById(R.id.userName);

        name.setText(getIntent().getStringExtra("Name"));

        if(String.valueOf(name.getText())=="")
        {
            name.setText(getIntent().getStringExtra("Email"));
        }
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        mDrawerList.setAdapter(adapter);

        // Drawer Item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);

            }
        });

    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }


    private void selectItemFromDrawer(int position) {
        Fragment fragment = new PreferencesFragment();

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mainContent, fragment)
                .commit();

        mDrawerList.setItemChecked(position, true);
        setTitle(mNavItems.get(position).mTitle);

        switch (mNavItems.get(position).mTitle)
        {

            case "My Courses":
                drawer=new Intent(this,course.class);
                break;
                case "Discover":
                    drawer=new Intent(this,discover.class);
                    break;
            case "Account":
                drawer=new Intent(this,account.class);
                break;
                default:
                    break;

        }
     startActivity(drawer);

        // Close the drawer
        mDrawerLayout.closeDrawer(mDrawerPane);
    }



}

class NavItem {
    String mTitle;
    String mSubtitle;
    int mIcon;

    public NavItem(String title, String subtitle, int icon) {
        mTitle = title;
        mSubtitle = subtitle;
        mIcon = icon;
    }
}
class DrawerListAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<NavItem> mNavItems;

    public DrawerListAdapter(Context context, ArrayList<NavItem> navItems) {
        mContext = context;
        mNavItems = navItems;
    }

    @Override
    public int getCount() {
        return mNavItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mNavItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.drawer_item, null);

        }
        else {
            view = convertView;
        }

        TextView titleView = (TextView) view.findViewById(R.id.title);
        TextView subtitleView = (TextView) view.findViewById(R.id.subTitle);
        ImageView iconView = (ImageView) view.findViewById(R.id.icon);

        titleView.setText( mNavItems.get(position).mTitle );
        subtitleView.setText( mNavItems.get(position).mSubtitle );
        iconView.setImageResource(mNavItems.get(position).mIcon);

        return view;
    }
}
