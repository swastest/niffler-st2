package guru.qa.niffler.test.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class BaseRetrofitTest {
    private static final OkHttpClient httpClient = new OkHttpClient.Builder()
            .build();

    protected Retrofit getRetrofit(String url) {
        return new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }
}
