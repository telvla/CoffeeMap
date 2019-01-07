package at.telvla.coffeemap;

public class Info {
    private Integer id;
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

    Info(Integer id, String name, String address, String phone, String link_img1, String link_img2, String link_img3, String link_img4, Double longs, String time_work,  Double lats){
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

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getLink_img1() {
        return link_img1;
    }
    public void setLink_img1(String link_img1) {
        this.link_img1 = link_img1;
    }
    public String getLink_img2() {
        return link_img2;
    }
    public void setLink_img2(String link_img2) {
        this.link_img2 = link_img2;
    }
    public String getLink_img3() {
        return link_img3;
    }
    public void setLink_img3(String link_img3) {
        this.link_img3 = link_img3;
    }
    public String getLink_img4() {
        return link_img4;
    }
    public void setLink_img4(String link_img4) {
        this.link_img4 = link_img4;
    }
    public String getTime_work() {
        return time_work;
    }
    public void setTime_work(String time_work) {this.time_work = time_work;}
    public Double getLongs() {
        return longs;
    }
    public void setLongs(Double longs) {
        this.longs = longs;
    }
    public Double getLats() {
        return lats;
    }
    public void setLats(Double lats) {
        this.lats = lats;
    }
}