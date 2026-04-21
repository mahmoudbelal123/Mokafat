package quadrant.mokafat.points.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import com.google.firebase.analytics.FirebaseAnalytics;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.view.TestActivity;
import quadrant.mokafat.points.view.profile.get_profile.ProfileActivity;

/* JADX INFO: loaded from: classes.dex */
public class MyNotificationManager {
    public static final int ID_BIG_NOTIFICATION = 234;
    public static final int ID_SMALL_NOTIFICATION = 235;
    private Context mCtx;

    public MyNotificationManager(Context mCtx) {
        this.mCtx = mCtx;
    }

    public void showBigNotification(String title, String message, Intent intent) {
        Intent intent2 = new Intent(this.mCtx, (Class<?>) TestActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this.mCtx, ID_BIG_NOTIFICATION, intent2, 134217728);
        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
        bigPictureStyle.setBigContentTitle(title);
        bigPictureStyle.setSummaryText(Html.fromHtml(message).toString());
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this.mCtx);
        Notification notification = mBuilder.setSmallIcon(R.drawable.main_logo).setTicker(title).setWhen(0L).setContentIntent(resultPendingIntent).setContentTitle(title).setStyle(bigPictureStyle).setSmallIcon(R.drawable.main_logo).setLargeIcon(BitmapFactory.decodeResource(this.mCtx.getResources(), R.drawable.main_logo)).setContentText(message).build();
        notification.defaults |= 1;
        notification.defaults |= 2;
        notification.flags |= 16;
        NotificationManager notificationManager = (NotificationManager) this.mCtx.getSystemService("notification");
        notificationManager.notify(ID_BIG_NOTIFICATION, notification);
    }

    public void showSmallNotification(String title, String message, Intent intent, String code, String provider, String value) {
        Intent intent2 = new Intent(this.mCtx, (Class<?>) ProfileActivity.class);
        intent2.putExtra("x", "a");
        intent2.putExtra("code", code);
        intent2.putExtra("title", title);
        intent2.putExtra("message", message);
        intent2.putExtra("provider", provider);
        intent2.putExtra(FirebaseAnalytics.Param.VALUE, value);
        Utilities.Notification = 2;
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this.mCtx, ID_SMALL_NOTIFICATION, intent2, 134217728);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this.mCtx);
        Notification notification = mBuilder.setSmallIcon(R.drawable.main_logo).setTicker(title).setWhen(0L).setAutoCancel(true).setContentIntent(resultPendingIntent).setContentTitle(title).setSmallIcon(R.drawable.main_logo).setLargeIcon(BitmapFactory.decodeResource(this.mCtx.getResources(), R.drawable.main_logo)).setContentText(message).build();
        notification.defaults |= 1;
        notification.defaults = 2 | notification.defaults;
        notification.flags |= 16;
        NotificationManager notificationManager = (NotificationManager) this.mCtx.getSystemService("notification");
        notificationManager.notify(ID_SMALL_NOTIFICATION, notification);
    }
}
