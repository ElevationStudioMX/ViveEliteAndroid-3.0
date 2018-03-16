package com.example.desarrollo_elevation.viveelite;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.desarrollo_elevation.viveelite.R.string.page1;
import static com.example.desarrollo_elevation.viveelite.R.string.page2;

public class MainActivity_menutabbed /*extends AppCompatActivity*/ {




    /*private static final int ALPHA_SELECTED = 255;
    private static final int ALPHA_UNSELECTED = 128;
    private static final int NUM_TABS = 1;

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menutabbed);
    }
      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MainPageAdapter());

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.mipmap.menu);
        tabLayout.getTabAt(1).setIcon(R.mipmap.elite);
        tabLayout.getTabAt(2).setIcon(R.mipmap.exercise);
       // tabLayout.getTabAt(3).setIcon(R.mipmap.tab4);

        selectIcon(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //nothing here
            }

            @Override
            public void onPageSelected(int position) {
                selectIcon(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //nothing here
            }
        });

    }

    private void selectIcon(int position) {
        for (int i = 0; i < NUM_TABS; i++) {
            if (i == position) {
                tabLayout.getTabAt(i).getIcon().setAlpha(ALPHA_SELECTED);
            } else {
                tabLayout.getTabAt(i).getIcon().setAlpha(ALPHA_UNSELECTED);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://danielme" +
                        ".com/2016/03/26/diseno-android-tutorial-pestanas-con-material-design")));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


    class MainPageAdapter extends PagerAdapter {

        private RelativeLayout page1;
        private AbsoluteLayout page2;
        private RelativeLayout page3;
        //private LinearLayout page4;
        private final int[] titles = {R.string.page1/*, R.string.page2, R.string.page3 ,R.string.page4*/};

       /* @Override
        public int getCount() {
            return NUM_TABS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getString(titles[position]);
        }

        @Override
        public Object instantiateItem(ViewGroup collection, int position) {
            View page;
            switch (position) {
                default:
                    if (page1 == null) {
                        page1 = (RelativeLayout) LayoutInflater.from(MainActivity_menutabbed.this).inflate(R.layout
                                .fragment_main_activity_menutabbed, collection, false);
                    }
                    page = page1;
                    break;
               /* case 1:
                    if (page2 == null) {
                        page2 = (AbsoluteLayout) LayoutInflater.from(MainActivity_menutabbed.this).inflate(R.layout
                                .activity_main_menu, collection, false);
                    }
                    page = page2;
                    break;
                default:
                    if (page3 == null) {
                        page3 = (RelativeLayout) LayoutInflater.from(MainActivity_menutabbed.this).inflate(R.layout
                                .fragment_main_activity_menutabbed, collection, false);
                        //initListView();
                    }
                    page = page3;
                    break;
                /*default:
                    if (page4 == null) {
                        page4 = (LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout
                                .page_four, collection, false);
                    }
                    page = page4;
                    break;*/
          /*  }

           collection.addView(page, 0);

          return page;
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        /*private void initListView() {
            String[] items = new String[50];
            for (int i = 0; i < 50; i++) {
                items[i] = "Item " + i;
            }
            page3.setAdapter(new ArrayAdapter<String>(MainActivity_menutabbed.this, R.layout.textview, items));
            page3.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(MainActivity_menutabbed.this, (String) parent.getItemAtPosition(position), Toast
                            .LENGTH_SHORT).show();
                }
            });*/

      /*  }
    }















   /* TabLayout tabLayout;
    private static final int ALPHA_SELECTED = 255;
    private static final int ALPHA_UNSELECTED = 128;
    private static final int NUM_TABS = 4;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
   // private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    /*private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menutabbed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
      //  mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
      /*  mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        //tabLayout.getTabAt(0).setIcon(R.mipmap.menu);
        //tabLayout.getTabAt(1).setIcon(R.mipmap.elite);
       // tabLayout.getTabAt(2).setIcon(R.mipmap.exercise);
       // selectIcon(0);

        tabLayout.setupWithViewPager(mViewPager);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        /*mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //nothing here
            }

            @Override
            public void onPageSelected(int position) {
                selectIcon(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //nothing here
            }
        });*/





    /*private void selectIcon(int position) {
        for (int i = 0; i < NUM_TABS; i++) {
            if (i == position) {
                tabLayout.getTabAt(i).getIcon().setAlpha(ALPHA_SELECTED);
            } else {
                tabLayout.getTabAt(i).getIcon().setAlpha(ALPHA_UNSELECTED);
            }
        }

    }*/
/*

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity_menutabbed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
   /* public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_activity_menutabbed, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }
*/



   /* public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return PlaceholderFragment.newInstance(position + 1);
            switch (position) {
                case 0:
                    MainActivity_busqueda busqueda = new MainActivity_busqueda();

                    return busqueda;
                case 1:
                    MainActivity_menu menu = new MainActivity_menu();

                    return menu;
                case 2:

                    MainActivity main = new MainActivity();
                    return main;
            }
            return null;



        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }



        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    Drawable drawable=   getDrawable(R.mipmap.elite);

                    Bitmap g = BitmapFactory.decodeResource(getResources(), R.mipmap.menu);
                    Canvas c = new Canvas();
                   c.drawBitmap(g, 0, 0, null);


                    return c  ;
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }*/

