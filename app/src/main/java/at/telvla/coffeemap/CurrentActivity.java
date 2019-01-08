package at.telvla.coffeemap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CurrentActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    static int count_img;

    String id_current;
    String title;
    String address;
    String phone;
    String time_work;
    static String link_img_1;
    static String link_img_2;
    static String link_img_3;
    static String link_img_4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_current);

        TextView title_view = (TextView) findViewById(R.id.title);
        TextView address_view = (TextView) findViewById(R.id.address);
        TextView phone_view = (TextView) findViewById(R.id.phone);
        TextView time_work_view = (TextView) findViewById(R.id.time_work);

        Intent intentId = getIntent();
        id_current = intentId.getStringExtra("id_current");
        title = intentId.getStringExtra("title");
        address = intentId.getStringExtra("address");
        phone = intentId.getStringExtra("phone");
        time_work = intentId.getStringExtra("time_work");
        link_img_1 = intentId.getStringExtra("link_img1");
        link_img_2 = intentId.getStringExtra("link_img2");
        link_img_3 = intentId.getStringExtra("link_img3");
        link_img_4 = intentId.getStringExtra("link_img4");

        title_view.setText(title);
        address_view.setText(address);
        phone_view.setText(phone);
        time_work_view.setText(time_work);

        getSupportActionBar().setTitle(title);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        /*
        RelativeLayout gameBoard = (RelativeLayout) findViewById(R.id.RelGameboard);
        //RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(100, 100);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT,1);

        for (int i = 0 ; i <  3 ; i++) {
            ImageButton btnGreen = new ImageButton(this);
            btnGreen.setImageResource(R.drawable.no_photo);
            btnGreen.setLayoutParams(lp);
            btnGreen.setOnClickListener(mGreenBallOnClickListener);
            btnGreen.setBackgroundColor(Color.TRANSPARENT);
            btnGreen.setTag(i);
            btnGreen.setId(i);
            gameBoard.addView(btnGreen);
        }
        */
    }

    private View.OnClickListener mGreenBallOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //check which green ball was clicked
            ImageButton imgBtn = (ImageButton) v;
            Log.i("greeny","Clicked on green ball->"+imgBtn.getTag()+" v.ID->"+v.getId());
        }
    };


    public static class PlaceholderFragment extends Fragment {
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
            View rootView = inflater.inflate(R.layout.fragment_slaider_img, container, false);
            Integer id_img = getArguments().getInt(ARG_SECTION_NUMBER);
            ImageView imageView = (ImageView) rootView.findViewById(R.id.img);

            try {

                Integer width;
                String link_img;
                List<String> mImageId = new ArrayList<String>();

                mImageId.add("");
                mImageId.add("https://s3.eu-central-1.amazonaws.com/krasota-style/img/blog1/articles/content/ZAQFmQGZ5CiTNhUWAxO7qPEeA-_Yhy2J-5BRyDCsd.jpg");
                mImageId.add("http://cdn.appaix.com/2016/0128/live-cricket-2014-11_1.jpg");
                mImageId.add("https://9968c6ef49dc043599a5-e151928c3d69a5a4a2d07a8bf3efa90a.ssl.cf2.rackcdn.com/84263-7.jpg");

                /*for (int i = 1; i < 5; i++) {
                    String img_val = new String("link_img_" + i);
                    Log.i("greenys","->" + img_val + new String("link_img_" + i) + link_img_1 );
                    if (img_val.trim().length() == 0) {
                        mImageId.add(img_val);
                    }
                }*/

                /*if (link_img_1.trim().length() != 0) {
                    mImageId.add(link_img_1);
                }

                if (link_img_2.trim().length() != 0) {
                    mImageId.add(link_img_2);
                }

                if (link_img_3.trim().length() != 0) {
                    mImageId.add(link_img_3);
                }

                if (link_img_4.trim().length() != 0) {
                    mImageId.add(link_img_4);
                }

                if (mImageId.size() == 0) {
                    count_img = 1;
                    mImageId.add("http://cdn.appaix.com/2016/0128/live-cricket-2014-11_1.jpg");
                } else {
                    count_img = mImageId.size();
                }*/

                DisplayMetrics metrics = new DisplayMetrics();
                WindowManager windowManager = (WindowManager) getContext()
                        .getSystemService(Context.WINDOW_SERVICE);
                windowManager.getDefaultDisplay().getMetrics(metrics);
                width = metrics.widthPixels;

                if (mImageId.get(id_img).length() == 0) {
                    link_img = "http://cdn.appaix.com/2016/0128/live-cricket-2014-11_1.jpg";
                } else {
                    link_img = mImageId.get(id_img);
                }

                Picasso.with(getContext())
                        .load(link_img)
                        .resize(width, 800)
                        .centerCrop()
                        .error(R.drawable.no_photo)
                        .into(imageView);

            } catch (NullPointerException e) {
            }
            return rootView;
        }
    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }
        @Override
        public int getCount() {
            return 3;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CurrentActivity.this, MapsActivity.class);
        startActivity(intent);
        finish();
    }
}
