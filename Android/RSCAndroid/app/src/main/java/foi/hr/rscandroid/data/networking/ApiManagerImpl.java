package foi.hr.rscandroid.data.networking;


import foi.hr.rscandroid.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManagerImpl implements ApiManager {

    private static final String TAG = "Network";

    private static final String API_ENDPOINT = BuildConfig.API_URL;

    private ApiService service;

    private static ApiManagerImpl instance;

    public static synchronized ApiManagerImpl getInstance() {
        if (instance == null) {
            instance = new ApiManagerImpl();
        }
        return instance;
    }

    private ApiManagerImpl() {

    }

    public void init() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new RequestInterceptor())
                .addInterceptor(httpLoggingInterceptor)
                .build();

        setup(client);
    }

    private void setup(OkHttpClient client) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_ENDPOINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create());

        service = builder.build().create(ApiService.class);
    }

    @Override
    public ApiService getApiService() {
        return service;
    }
}
