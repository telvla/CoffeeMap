package at.telvla.coffeemap;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {
    @POST("/map-coffee.html")
    Call<List<CoffeeInfo>> GetAllNewsJson (@Query("all_coffee_json") Integer all_news_json);
}
