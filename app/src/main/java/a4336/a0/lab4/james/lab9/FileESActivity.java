package a4336.a0.lab4.james.lab9;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FileESActivity extends AppCompatActivity {

    WifiWrapper wifiInterface;
    String filename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_es);
        filename = "Lab9_AP_Scan";
        wifiInterface = new WifiWrapper((WifiManager) getSystemService(Context.WIFI_SERVICE));

        final Button backButton = (Button) findViewById(R.id.ESFileBackbutton);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });

        String[] scan = readFile(filename);
        if(scan == null){
            scan[0] = "Not Found";
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, scan);
        ListView ScanList = (ListView) findViewById(R.id.FileESlistView);
        ScanList.setAdapter(adapter);

        final Button APScan = (Button) findViewById(R.id.Scanbutton);
        APScan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                writeFile();
            }
        });
    }

    private String[] readFile(String filename){

        try {

            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), filename);
            FileInputStream fos = new FileInputStream(file);
            String fileString = "";

            int fileStream;
            while((fileStream = fos.read()) != -1){

                fileString.concat(Integer.toString(fileStream));
            }

            String[] arrayS = fileString.split("\n");
            fos.close();
            return arrayS;

        } catch (FileNotFoundException e) {

            TextView error = (TextView) findViewById(R.id.ESFileTitleView);
            error.setText("Error: FileNotFoundException thrown.");
            e.printStackTrace();
        }catch(IOException e){
            TextView error = (TextView) findViewById(R.id.ESFileTitleView);
            error.setText("Error: IOException thrown.");
            e.printStackTrace();
        }

        return null;

    }
    private boolean writeFile(){

        ArrayList<String> ap_scan = this.wifiInterface.getAllAP();
        try {
            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
            for(String ap : ap_scan){

                ap.concat("\n");
                fos.write(ap.getBytes());
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
}
