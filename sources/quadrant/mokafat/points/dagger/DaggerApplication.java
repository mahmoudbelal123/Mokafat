package quadrant.mokafat.points.dagger;

import android.app.Application;
import quadrant.mokafat.points.helper.ConnectivityReceiver;

/* JADX INFO: loaded from: classes.dex */
public class DaggerApplication extends Application {
    public static DaggerApplication mDaggerApplication;
    private AppComponent appComponent;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        this.appComponent = initDagger(this);
        mDaggerApplication = this;
    }

    protected AppComponent initDagger(DaggerApplication application) {
        return DaggerAppComponent.builder().appModule(new AppModule(application)).build();
    }

    public AppComponent getAppComponent() {
        return this.appComponent;
    }

    public static DaggerApplication getDaggerApplication() {
        return mDaggerApplication;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
