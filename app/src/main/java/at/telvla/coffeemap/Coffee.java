package at.telvla.coffeemap;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Coffee {
    @PrimaryKey
    public long id;

    public String date_name;
    public String date_address;
    public String date_phone;
    public String date_time_work;
    public String date_link_img1;
    public String date_link_img2;
    public String date_link_img3;
    public String date_link_img4;
    public String date_longs;
    public String date_lats;

}
