package MU;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class NoInternet extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
