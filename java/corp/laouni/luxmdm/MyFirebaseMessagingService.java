package corp.laouni.luxmdm;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Ishimwe on 7/1/2018.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if(remoteMessage.getData().size()>0 ){
            //handle data
            final String command = remoteMessage.getData().get("command");
            switch(command){
                case "1":
                    //function 1
                    break;
                case "2":
                    //function 2
                    break;
                case "3":
                    //function 3
                    break;
                case "4":
                    //function 4
                    break;
                case "5":
                    //function 5
                    break;
            }
        }
    }
}
