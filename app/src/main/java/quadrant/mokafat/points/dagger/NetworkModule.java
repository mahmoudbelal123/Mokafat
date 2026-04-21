package quadrant.mokafat.points.dagger;

import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import quadrant.mokafat.points.apiClient.ApiInterface;
import quadrant.mokafat.points.apiClient.EndPoints;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/* JADX INFO: loaded from: classes.dex */
@Module
public class NetworkModule {
    private static final String NAME_BASE_URL = "NAME_BASE_URL";

    @Provides
    @Named(NAME_BASE_URL)
    String provideBaseUrlString() {
        return EndPoints.BASE_URL;
    }

    @Provides
    @Singleton
    Converter.Factory provideGsonConverter() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHTTp(Interceptor interceptor) {
        return new OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(100L, TimeUnit.SECONDS).writeTimeout(100L, TimeUnit.SECONDS).readTimeout(100L, TimeUnit.SECONDS).build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(@Named(NAME_BASE_URL) String baseUrl, Converter.Factory converter) {
        return new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(converter).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    }

    @Provides
    @Singleton
    ApiInterface provideUsdaApi(Retrofit retrofit) {
        return (ApiInterface) retrofit.create(ApiInterface.class);
    }
}
