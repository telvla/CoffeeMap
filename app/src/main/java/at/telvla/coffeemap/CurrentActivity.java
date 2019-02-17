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
import android.view.Window;
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
    static List<String> mImageId;
    String id_current;
    String title;
    String address;
    String phone;
    String time_work;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_current);

        TextView title_view = findViewById(R.id.title);
        TextView address_view = findViewById(R.id.address);
        TextView phone_view = findViewById(R.id.phone);
        TextView time_work_view = findViewById(R.id.time_work);

        Intent intentId = getIntent();
        id_current = intentId.getStringExtra("id_current");
        title = intentId.getStringExtra("title");
        address = intentId.getStringExtra("address");
        phone = intentId.getStringExtra("phone");
        time_work = intentId.getStringExtra("time_work");

        mImageId = new ArrayList<String>();
        for (int i = 1; i < 5; i++) {
            if (intentId.getStringExtra("link_img" + i).trim().length() != 0) {
                mImageId.add(intentId.getStringExtra("link_img" + i));
            }
        }
        count_img = mImageId.size();

        title_view.setText(title);
        address_view.setText(address);
        phone_view.setText("Телефон: " + phone);
        time_work_view.setText("Время работы: " + time_work);

        getSupportActionBar().setTitle(title);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
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
            ImageView imageView = rootView.findViewById(R.id.img);

            try {
                Integer width;
                DisplayMetrics metrics = new DisplayMetrics();
                WindowManager windowManager = (WindowManager) getContext()
                        .getSystemService(Context.WINDOW_SERVICE);
                windowManager.getDefaultDisplay().getMetrics(metrics);
                width = metrics.widthPixels + metrics.widthPixels;

                Picasso.with(getContext())
                        .load(mImageId.get(id_img))
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
            return PlaceholderFragment.newInstance(position);
        }
        @Override
        public int getCount() {
            return count_img;
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

         /*for (int i = 1; i < 5; i++) {
            String img_val = new String("link_img_" + i);
            Log.i("greenys","->" + img_val + new String("link_img_" + i) + link_img_1 );
            if (img_val.trim().length() == 0) {
                mImageId.add(img_val);
            }
        }*/