package corp.laouni.luxmdm;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static corp.laouni.luxmdm.PolicyManager.PolicyActivationCode;

public class MainActivity extends Activity {
    dataInterchange postDATA = new dataInterchange();
    boolean True = true;
    boolean False = false;

    //private PolicyManager ;
    EditText staffID, staffName, staffDept;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DevicePolicyManager xManager = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName xAdminComp =new ComponentName (this, AdminReceiver.class);
        if (!xManager.isAdminActive(xAdminComp)){

            Intent activateDevAdmin=new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);

            activateDevAdmin.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, xAdminComp);
            activateDevAdmin.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,"After activating admin, you will be able to block applications uninstallation");

            startActivityForResult(activateDevAdmin, PolicyActivationCode);
           // setContentView(R.layout.activity_main);

        }
        //onStart();
        //setPWDconfigs();
    }
    private void setPWDconfigs(){

        DevicePolicyManager xManager = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName xAdminComp =new ComponentName (this, AdminReceiver.class);

        // set new device passwords configurations
        //set password quality (alphanumeric)
        xManager.setPasswordQuality(xAdminComp, DevicePolicyManager.PASSWORD_QUALITY_ALPHANUMERIC);
        //set password minLength
        xManager.setPasswordMinimumLength(xAdminComp, 8);
        //set failedPass wipe (upon 5 failed password entries, the device data is wiped clean)
        xManager.setMaximumFailedPasswordsForWipe(xAdminComp, 5);
        //set password expiration timeout to 3 months
        xManager.setPasswordExpirationTimeout(xAdminComp, 131400000L);

        //prompt user for new password
        Intent setPasswordIntent = new Intent(DevicePolicyManager.ACTION_SET_NEW_PASSWORD);
        startActivity(setPasswordIntent);

    }

   // private void deviceInfo(){}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();

        setContentView(R.layout.activity_main);

        staffName = (EditText) findViewById(R.id.staffName);
        staffID = (EditText) findViewById(R.id.staffID);
        staffDept = (EditText) findViewById(R.id.staffDept);
        submit = (Button) findViewById(R.id.submit_button);

        String user_Name;
        String user_id;
        String user_dept;

        user_id = staffID.getText().toString();
        user_Name = staffName.getText().toString();
        user_dept = staffDept.getText().toString();

        //postDATA.sendDataToServer(user_id, user_Name, user_dept);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deviceInfo = new Intent(MainActivity.this, device_info.class);
                startActivity(deviceInfo);
            }
        });

    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
