package corp.laouni.luxmdm;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Ishimwe on 7/1/2018.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    dataInterchange uploadkey = new dataInterchange();

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();

        Log.d("Refreshed Token >>> ", token);

        uploadkey.sendKeyToServer(token);

    }
}
