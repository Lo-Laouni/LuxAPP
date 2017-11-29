package corp.laouni.luxmdm;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static android.os.Build.BRAND;
import static android.os.Build.DEVICE;
import static android.os.Build.MODEL;
import static android.os.Build.PRODUCT;
import static android.os.Build.SERIAL;

public class device_info extends AppCompatActivity {


    private String Device, Model, Product, Brand, Serial,ID;
    boolean True = true;
    boolean False = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);
        DevicePolicyManager xManager = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName xAdminComp =new ComponentName (this, AdminReceiver.class);

        TextView configsdisplay;
        TextView setNewdisplay;
        TextView encryptdisplay;

        configsdisplay= (TextView) findViewById(R.id.configsDisplay);
        setNewdisplay= (TextView) findViewById(R.id.PassCheck);
        encryptdisplay= (TextView) findViewById(R.id.encryDisplay);

        configsdisplay.setText("PASS");
        if (xManager.isActivePasswordSufficient()){
            setNewdisplay.setText("PASS");
        }
        else {
            setNewdisplay.setTextColor(Color.RED);
            setNewdisplay.setText("FAIL");
        }
        int encryptionSTATUS;
        encryptionSTATUS = xManager.getStorageEncryptionStatus();
        if (encryptionSTATUS == xManager.ENCRYPTION_STATUS_INACTIVE){
            int encryptionResult = xManager.setStorageEncryption(xAdminComp, True);
            if (encryptionResult == xManager.ENCRYPTION_STATUS_ACTIVE){
                encryptdisplay.setText("PASS");
            }
            else{
                encryptdisplay.setTextColor(Color.RED);
            }
        }

        TextView Devicedisplay= (TextView) findViewById(R.id.DeviceDisplay);
        TextView Modeldisplay= (TextView) findViewById(R.id.ModelDisplay);
        TextView Productdisplay= (TextView) findViewById(R.id.productDisplay);
        TextView Branddisplay=(TextView) findViewById(R.id.brandDisplay);
        TextView Serialdisplay=(TextView) findViewById(R.id.serialDisplay);
        TextView IDdisplay=(TextView) findViewById(R.id.id_display);
        Device = DEVICE;
        Devicedisplay.setText(Device);
        Model = MODEL;
        Modeldisplay.setText(Model);
        Product = PRODUCT;
        Productdisplay.setText(Product);
        Brand = BRAND;
        Branddisplay.setText(Brand);
        Serial = SERIAL;
        Serialdisplay.setText(Serial);
        ID = Build.ID;
        IDdisplay.setText(ID);

        // postDATA.sendDataToServer(Serial, Model, ID);
    }
}
