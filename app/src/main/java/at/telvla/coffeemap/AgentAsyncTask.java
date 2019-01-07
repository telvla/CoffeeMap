package at.telvla.coffeemap;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.List;

public class AgentAsyncTask extends AsyncTask<Void, Void, Integer> {

    //Prevent leak
    private WeakReference<Activity> weakActivity;

    private String id;
    private String name;
    private String address;
    private String phone;
    private String time_work;
    private String link_img1;
    private String link_img2;
    private String link_img3;
    private String link_img4;
    private String longs;
    private String lats;
    Context context;


    public AgentAsyncTask(Activity activity, String id, String name, String address, String phone, String time_work, String link_img1, String link_img2, String link_img3, String link_img4, String longs, String lats ) {
        weakActivity = new WeakReference<>(activity);
        this.context = activity;
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.time_work = time_work;
        this.link_img1 = link_img1;
        this.link_img2 = link_img2;
        this.link_img3 = link_img3;
        this.link_img4 = link_img4;
        this.longs = longs;
        this.lats = lats;

    }

    @Override
    protected Integer doInBackground(Void... params) {


        //AgentDao agentDao = MyApp.DatabaseSetup.getDatabase().agentDao();

        AppDatabase personDAO;
        CoffeeDao employeeDao;

        personDAO = DatabaseCreator.getPersonDatabase(context);
        employeeDao = personDAO.coffeeDao();

        Coffee coffee = new Coffee();
        /*coffee.date_name = "rgrs";
        coffee.date_address = "srgs";
        coffee.date_phone = "st5hsrh";
        coffee.date_time_work = "sthsrh";
        coffee.date_link_img1 = "sthsh";
        coffee.date_link_img2 = "shts";
        coffee.date_link_img3 = "shts";
        coffee.date_link_img4 = "shsth";
        coffee.date_longs = "52,5";
        coffee.date_lats = "68,6855";*/


        /*coffee.name = "John Smith 3";
        coffee.salary = 10000;

        employeeDao.insert(coffee);*/


        Coffee coffeeR = employeeDao.getById(3);

        Log.i("test_map", "async ----------- " + coffeeR.name);






        return 0;
    }

    @Override
    protected void onPostExecute(Integer agentsCount) {
        Activity activity = weakActivity.get();
        if(activity == null) {
            return;
        }

        if (agentsCount > 0) {
            //2: If it already exists then prompt user
            Toast.makeText(activity, "Agent already exists!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(activity, "Agent does not exist! Hurray :)", Toast.LENGTH_LONG).show();
            //activity.onBackPressed();
        }
    }
}