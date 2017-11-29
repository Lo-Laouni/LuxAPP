package corp.laouni.luxmdm;

import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import static android.content.Context.DEVICE_POLICY_SERVICE;

/**
 * Created by Ishimwe on 18/9/2017.
 */

public class AdminReceiver extends DeviceAdminReceiver {
    Context context;
    DevicePolicyManager localDPM = (DevicePolicyManager) context.getSystemService(DEVICE_POLICY_SERVICE);
    ComponentName localComponent =new ComponentName(context, AdminReceiver.class);


    @Override
    public void onEnabled(Context context, Intent intent) {
        super.onEnabled(context, intent);
        Toast.makeText(context,"LuxMDM successfully enabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public CharSequence onDisableRequested(Context context, Intent intent) {
        return "Requesting to disable Admin control";
    }
    @Override
    public void onDisabled(Context context, Intent intent) {
        Toast.makeText(context, "LuxMDM Disabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPasswordChanged(Context context, Intent intent) {
        Toast.makeText(context,"Device Password is Changed",Toast.LENGTH_SHORT).show();
        localDPM.setPasswordExpirationTimeout(localComponent,0L);
    }
    @Override
    public void onPasswordFailed(Context context, Intent intent) {
        Toast.makeText(context, "Password Failed", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onPasswordSucceeded(Context context, Intent intent) {
        Toast.makeText(context, "Access granted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPasswordExpiring(Context context, Intent intent) {
        Toast.makeText(context, "Device password is going to expire, please update to a new one",Toast.LENGTH_LONG).show();

        long expirationDate = localDPM.getPasswordExpiration(localComponent);
        long delta = expirationDate - System.currentTimeMillis();
        boolean expired = delta < 0L;

        if(expired){
            localDPM.setPasswordExpirationTimeout(localComponent, 30000L);
        }
        Intent passwordChange = new Intent (DevicePolicyManager.ACTION_SET_NEW_PASSWORD);
        passwordChange.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(passwordChange);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final String LOG_TAG= "DeviceAdmin";
        Log.i(LOG_TAG, "AdminReceiver Received: " + intent.getAction());
        super.onReceive(context,intent);
    }
}
