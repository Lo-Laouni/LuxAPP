package corp.laouni.luxmdm;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Ishimwe on 5/11/2017.
 */

public class dataInterchange {

    private JSONObject formatDataAsJSON(String a, String b, String c){

        final JSONObject uploads = new JSONObject();

        try {
            JSONArray data = new JSONArray();

            data.put(a);
            data.put(b);
            data.put(c);

            uploads.put("data", data);
            return  uploads;
        }catch (JSONException e) {
            Log.d("Error formatDataJSON: ", e.toString());
        }
        return null;
    }

    protected void sendDataToServer(String a, String b, String c){
        final JSONObject json = formatDataAsJSON(a, b, c);

        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... params) {
                return getServerResponse(json);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Log.d("DevDataUpload : ",result);
            }
        }.execute();
    }

    protected  void sendKeyToServer(String key){
        final JSONObject jsonKEY = formatKeyAsJSON(key);

        new AsyncTask<Void, Void, String>(){

            @Override
            protected String doInBackground(Void... params) {
                return getServerResponse(jsonKEY);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                Log.d("TokenKey upload : ", s);
            }
        }.execute();
    }

    private String getServerResponse(JSONObject json){
        String apiUrl = "loiclaouni.pythonanywhere.com/api/v1/luxmdm";
        String apiURL2 = "loiclaouni.pythonanywhere.com/api/v1/AppRegToken";
        HttpURLConnection apiconnection = null;
        URL url;
        String response ="";
        try {
            if (json.has("key")){
                 url = new URL(apiURL2);
            }
            else {
                url= new URL(apiUrl);
            }

            apiconnection = (HttpURLConnection) url.openConnection();
            apiconnection.setRequestMethod("POST");
            apiconnection.setRequestProperty("Content-Type", "application/json");
            apiconnection.setRequestProperty("Accept","application/json");
            apiconnection.setDoOutput(true);
            apiconnection.setDoInput(true);
            DataOutputStream dataOut = new DataOutputStream(apiconnection.getOutputStream());
            dataOut.writeBytes(json.toString());
            dataOut.flush();
            dataOut.close();

            int responseCode = apiconnection.getResponseCode();
            String responseMsg = apiconnection.getResponseMessage();
            if (responseCode == -1){
                response = "Invalid HTTP response";
            }
            response = "Response Code: "+responseCode+"\nResponse Message: "+responseMsg;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert apiconnection != null;
            apiconnection.disconnect();
        }
        return  response;
    }

    private JSONObject formatKeyAsJSON (String key){

        final JSONObject JsonKEY = new JSONObject();

        try {
            JsonKEY.put("key", key);
            //return  JsonKEY;
        }catch (JSONException e) {
            Log.d("Error formatKeyAsJSON: ", e.toString());
        }
        return JsonKEY;
    }
}
