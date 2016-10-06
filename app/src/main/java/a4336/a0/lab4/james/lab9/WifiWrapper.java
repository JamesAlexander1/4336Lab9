package a4336.a0.lab4.james.lab9;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by james on 5/10/2016.
 */
public class WifiWrapper {

    WifiManager manager;

    public WifiWrapper(WifiManager man){
        manager = man;
    }

    public ArrayList<String> getAllAP(){

       // manager.setWifiEnabled(true);
        List<ScanResult> scanresults = manager.getScanResults();


        ArrayList<String> results = convertList(scanresults);

        return results;
    }

    private ArrayList<String> convertList(List<ScanResult> scans){

        ArrayList<String> results = new ArrayList<String>();

        for(ScanResult scan : scans){
           results.add(scan.SSID + " signal strength: " + scan.frequency + " time: " + scan.timestamp);

        }

        return results;
    }
    public int getWifiState(){
        return manager.getWifiState();
    }
    public boolean startScan(){
        return manager.startScan();
    }

}
