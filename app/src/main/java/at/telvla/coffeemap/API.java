package at.telvla.coffeemap;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {
    @POST("/map-coffee.html")
    Call<List<Info>> GetAllNewsJson (@Query("all_coffee_json") Integer all_news_json);

    @POST("/json.html")
    Call<List<GoogleAnswerInfo>> GetGoogleAnswerInfo (@Query("parameters") String parameters);

}
