package com.deadlinesoftware.wikom.childtrackeronvan.net;

import android.telecom.Call;
import android.util.Log;
import com.deadlinesoftware.wikom.childtrackeronvan.model.Driver;
import com.deadlinesoftware.wikom.childtrackeronvan.model.Global;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by junio on 12/3/2559.
 */
public class WebServices {

    private static final String TAG = WebServices.class.getSimpleName();
    private static OkHttpClient client = new OkHttpClient();

    public interface getDriverCallback{

        void onSuccess();

    }

    public static void getDrivers(final getDriverCallback callback) throws IOException{
        Request request =  new Request.Builder()
                .url("http://10.35.27.190/childtracking/get_drivers.php")
                .build();

         client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG,"Error connection error: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String resultJson = response.body().string();
                parseDriverData(resultJson);
                callback.onSuccess();
            }
        });
    }

    private static void parseDriverData(String resultJson) {
        try {
            JSONObject result = new JSONObject(resultJson);
            int success = result.getInt("success");

                    if (success == 1)
                    {
                        JSONArray driverData = result.getJSONArray("driver_data");

                        Global.driverList.clear();

                        for (int i = 0; i < driverData.length(); i++) {
                        JSONObject driver = driverData.getJSONObject(i);

                            int id = driver.getInt("id");
                            String name = driver.getString("name");
                            int gender = driver.getInt("gender");
                            String citizenId = driver.getString("citizen_id");
                            String avatar = driver.getString("avatar");
                            String uuid = (driver.getString("uuid"));

                            Driver d = new Driver(id, name, gender, citizenId, uuid, avatar);
                            Global.driverList.add(d);
                        }
                        Log.i(TAG,"Number of Drivers size: "+Global.driverList.size());
                    }
            else if (success == 0){}
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
