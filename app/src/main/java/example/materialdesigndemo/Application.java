package example.materialdesigndemo;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import rx.Observable;

public class Application extends android.app.Application {

    public static Application instance;

    private APIService service;

    public APIService getService() { return service; }

    public Application getInstance() { return instance; }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://")
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        service = restAdapter.create(APIService.class);
    }
}
