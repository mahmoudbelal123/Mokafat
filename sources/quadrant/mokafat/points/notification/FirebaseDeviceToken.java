package quadrant.mokafat.points.notification;

import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/* JADX INFO: loaded from: classes.dex */
public class FirebaseDeviceToken extends FirebaseInstanceIdService {
    private static final String TAG = "FireBaseDeviceToken";

    @Override // com.google.firebase.iid.FirebaseInstanceIdService
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token:" + refreshedToken);
        storeToken(refreshedToken);
    }

    private void storeToken(String token) {
        SharedPrefManager.getInstance(getApplicationContext()).saveDeviceToken(token);
        Log.d("token", token);
    }
}
