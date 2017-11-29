package corp.laouni.luxmdm;

//import android.app.admin.PolicyManager;
import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;

/**
 * Created by Ishimwe on 18/9/2017.
 */

public class PolicyManager extends Activity {
    public static final int PolicyActivationCode=1;
    private final static String LOG_TAG =  "DevicePolicyManager";
    private Context mContext;
    private DevicePolicyManager DPM;
    public ComponentName adminComponent;

    public PolicyManager(Context context){
        this.mContext =context;
        DPM = (DevicePolicyManager) mContext.getSystemService(Context.DEVICE_POLICY_SERVICE);
        adminComponent = new ComponentName(mContext.getPackageName(), mContext.getPackageName()+".AdminReceiver");
    }

    public void setPolicies(){
        //set password quality
        DPM.setPasswordQuality(adminComponent, DevicePolicyManager.PASSWORD_QUALITY_ALPHANUMERIC);
        //set password minLength
        DPM.setPasswordMinimumLength(adminComponent, 8);
        //set failedPass wipe (upon 5 failed password entries, the device data is wiped clean)
        DPM.setMaximumFailedPasswordsForWipe(adminComponent, 5);
        //set password expiration timeout to 3 months
        DPM.setPasswordExpirationTimeout(adminComponent, 131400000L);
        //enable backup service
    }

}
