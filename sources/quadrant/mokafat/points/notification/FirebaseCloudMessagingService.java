package quadrant.mokafat.points.notification;

import android.content.Intent;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;
import quadrant.mokafat.points.view.profile.get_profile.ProfileActivity;

/* JADX INFO: loaded from: classes.dex */
public class FirebaseCloudMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFireBaseMsgService";

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData().size() > 0) {
            try {
                Map<String, String> map = remoteMessage.getData();
                sendPushNotification(map);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void sendPushNotification(Map map) {
        String providerCorporate = (String) map.get("provider");
        String voucherCode = (String) map.get("code");
        String voucherValue = (String) map.get(FirebaseAnalytics.Param.VALUE);
        String title = (String) map.get("title");
        String message = (String) map.get("message");
        MyNotificationManager mNotificationManager = new MyNotificationManager(getApplicationContext());
        Intent intent = new Intent(getApplicationContext(), (Class<?>) ProfileActivity.class);
        mNotificationManager.showSmallNotification(title, message, intent, voucherCode, providerCorporate, voucherValue);
    }
}
