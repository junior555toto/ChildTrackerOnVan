package com.deadlinesoftware.wikom.childtrackeronvan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.deadlinesoftware.wikom.childtrackeronvan.model.Driver;
import com.deadlinesoftware.wikom.childtrackeronvan.model.Global;
import com.deadlinesoftware.wikom.childtrackeronvan.net.WebServices;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            WebServices.getDrivers(new WebServices.getDriverCallback() {
                @Override
                public void onSuccess() {
                    for (Driver d : Global.driverList)
                    {
                        Log.i(TAG, d.name);
                    }

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
