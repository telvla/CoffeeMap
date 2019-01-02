package at.telvla.coffeemap;

public class Info {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String link_img;
    private Double longs;
    private Double lats;

    Info(String id, String name, String address, String phone, String link_img, Double longs, Double lats){
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.link_img = link_img;
        this.longs = longs;
        this.lats = lats;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getLink_img() {
        return link_img;
    }

    public void setLink_img(String link_img) {
        this.link_img = link_img;
    }

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
