package a4336.a0.lab4.james.lab9;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FileESActivity extends AppCompatActivity {

    public static final int MY_PERMISSIONS_REQUEST_WIFI = 1;
    WifiWrapper wifiInterface;
    String filename;
    WifiReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_es);

       int check = ContextCompat.checkSelfPermission(this,
               Manifest.permission.ACCESS_COARSE_LOCATION);

        filename = "Lab9_AP_Scan";
        wifiInterface = new WifiWrapper((WifiManager) getSystemService(Context.WIFI_SERVICE));
        receiver = new WifiReceiver();
        if(check == -1){
            TextView error = (TextView) findViewById(R.id.ESFileTitleView);
            error.setText("Permissions havent been requested.");

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.CHANGE_WIFI_STATE, Manifest.permission.CHANGE_NETWORK_STATE},
                    MY_PERMISSIONS_REQUEST_WIFI);
        }



        int status = wifiInterface.getWifiState();
        if(status == 1){
            TextView error = (TextView) findViewById(R.id.ESFileTitleView);
            error.setText("wifi disabled.");
        }
        final Button backButton = (Button) findViewById(R.id.ESFileBackbutton);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });


        final Button APScan = (Button) findViewById(R.id.Scanbutton);
        APScan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                registerReceiver(receiver, new IntentFilter(wifiInterface.manager.SCAN_RESULTS_AVAILABLE_ACTION));
                wifiInterface.startScan();


            }
        });
    }
    protected void onPause() {
        unregisterReceiver(receiver);
        super.onPause();
    }

    protected void onResume() {
        registerReceiver(receiver, new IntentFilter(wifiInterface.manager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }

    private ArrayList<String> readFile(String filename){

        //S//tring temp = "";
        try {


            FileInputStream fos = openFileInput(filename);
            //String fileString = "";

           // int fileStream;
            //while((fileStream = fos.read()) != -1){

               // fileString.concat(Integer.toString(fileStream));

            //}
            InputStreamReader isr = new InputStreamReader(fos);
            BufferedReader bufferedReader = new BufferedReader(isr);
            //StringBuilder sb = new StringBuilder();
            String line;
            ArrayList<String> arrayS = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                arrayS.add(line);
            }
            //  String[] arrayS = fileString.toString().split("\n");
            //String[] arrayS = sb.
            fos.close();
            return arrayS;

        } catch (FileNotFoundException e) {

            TextView error = (TextView) findViewById(R.id.ESFileTitleView);
            error.setText("File Not Found.");
            e.printStackTrace();
        }catch(IOException e){
            TextView error = (TextView) findViewById(R.id.ESFileTitleView);
            error.setText("Error: IOException thrown.");
            e.printStackTrace();
        }

        return null;

    }
    private boolean writeFile(ArrayList<String> ap_scan){


        //ArrayList<String> ap_scan = this.wifiInterface.getAllAP();
        if(ap_scan.isEmpty()){
            TextView error = (TextView) findViewById(R.id.ESFileTitleView);
            error.setText("Error: Empty ap_scan");
        }

        try {

            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
            for(String ap : ap_scan){

                ap.concat("\n");
                fos.write(ap.getBytes());
                //eadFile(filename);
            }

            fos.close();

            return true;
        }catch(FileNotFoundException e){
            TextView error = (TextView) findViewById(R.id.ESFileTitleView);
            error.setText("Error: FileNotFoundException thrown.");
            e.printStackTrace();
        }catch(IOException e){
            TextView error = (TextView) findViewById(R.id.ESFileTitleView);
            error.setText("Error: IOException thrown.");
            e.printStackTrace();
        }
        return false;
    }

    private class WifiReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

            ArrayList<String> results = wifiInterface.getAllAP();
            writeFile(results);
            ArrayList<String> readResults = readFile(filename);

            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(FileESActivity.this, android.R.layout.simple_list_item_1, readResults);
            ListView ScanList = (ListView) findViewById(R.id.FileESlistView);

            ScanList.setAdapter(adapter2);

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WIFI: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // wifi-related task you need to do.
                    TextView error = (TextView) findViewById(R.id.ESFileTitleView);
                    error.setText("Permission Granted");

                } else {
                    TextView error = (TextView) findViewById(R.id.ESFileTitleView);
                    error.setText("Error: Permission denied");
                }
                // permission denied, boo! Disable the
                // functionality that depends on this permission.
            }
            return;
        }
    }
}
