package foi.hr.rscandroid.data.push;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import foi.hr.rscandroid.R;
import foi.hr.rscandroid.data.models.Answer;
import foi.hr.rscandroid.data.models.QuestionData;
import foi.hr.rscandroid.ui.game.GameActivity;
import foi.hr.rscandroid.ui.main.MainActivity;

public class RSCFirebaseMessagingService extends FirebaseMessagingService {

    public static final String question = "question";

    public static final String answer = "answer";

    public RSCFirebaseMessagingService() {
    }

    private static final String TAG = "FireMessagingService";

    public static final int FIREBASE_REQUEST_CODE = 420;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        startQuestionActivity(remoteMessage.getData().get(question), remoteMessage.getData().get(answer));

    }

    private void startQuestionActivity(String s, String s1) {
        Intent intent = new Intent(this, GameActivity.class);
        Gson gson = new Gson();
        QuestionData qd = gson.fromJson(s, QuestionData.class);
        Answer[] a = gson.fromJson(s1, Answer[].class);
        for (int i = 0; i < a.length; i++) {
            qd.setASingleAnswer(a[i]);
        }
        intent.putExtra(GameActivity.EXTRA_QUESTION, (Parcelable) qd);
        startActivity(intent);
    }

    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, FIREBASE_REQUEST_CODE, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_app)
                .setContentTitle("App message")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(FIREBASE_REQUEST_CODE, notificationBuilder.build());
    }

}
