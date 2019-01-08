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

    public Integer server_id;
    private String name;
    private String address;
    private String phone;
    private String time_work;
    private String link_img1;
    private String link_img2;
    private String link_img3;
    private String link_img4;
    private Double longs;
    private Double lats;

    Context context;

    public AgentAsyncTask(Activity activity, Integer server_id, String name, String address, String phone, String time_work, String link_img1, String link_img2, String link_img3, String link_img4, Double longs, Double lats ) {
        weakActivity = new WeakReference<>(activity);
        this.context = activity;
        this.server_id = server_id;
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

        AppDatabase personDAO;
        CoffeeDao employeeDao;

        personDAO = DatabaseCreator.getPersonDatabase(context);
        employeeDao = personDAO.coffeeDao();

        /*Coffee coffee = new Coffee();
        coffee.server_id = server_id;
        coffee.date_name = name;
        coffee.date_address = address;
        coffee.date_phone = phone;
        coffee.date_time_work = time_work;
        coffee.date_link_img1 = link_img1;
        coffee.date_link_img2 = link_img2;
        coffee.date_link_img3 = link_img3;
        coffee.date_link_img4 = link_img4;
        coffee.date_longs = longs;
        coffee.date_lats = lats;

        employeeDao.insert(coffee);*/

        Coffee coffeeR = employeeDao.getById(3);
        Log.i("test_map", "async ----------- " + coffeeR.date_name);

        return 0;
    }

    @Override
    protected void onPostExecute(Integer agentsCount) {
        /*Activity activity = weakActivity.get();
        if(activity == null) {
            return;
        }

        if (agentsCount > 0) {
            //2: If it already exists then prompt user
            Toast.makeText(activity, "Agent already exists!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(activity, "Agent does not exist! Hurray :)", Toast.LENGTH_LONG).show();
            //activity.onBackPressed();
        }*/
    }
}