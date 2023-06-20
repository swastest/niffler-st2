package guru.qa.niffler.test.api;

import guru.qa.niffler.config.Config;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class BaseRetrofitTest {
    protected static final Config config = Config.getConfig();
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
