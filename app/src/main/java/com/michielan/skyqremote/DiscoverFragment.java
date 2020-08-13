package com.michielan.skyqremote;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.stealthcopter.networktools.PortScan;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import static com.michielan.skyqremote.NetworkUtils.getIPAddress;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class DiscoverFragment extends Fragment {

    public DiscoverFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // List of ips found by the discovery process
        final List<String> devices = new ArrayList<>();

        // Object useful to check the state of the current scan
        final NetworkUtils.Scan sc = new NetworkUtils.Scan();

        // Progress bar while scanning
        final ProgressBar pb = view.findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);

        // Text view showed when the scan is finished
        final TextView tv = view.findViewById(R.id.devices_text_view);
        tv.setVisibility(View.INVISIBLE);

        // List view containing the result of the scan
        final ListView lv = view.findViewById(R.id.ip_list_view);

        // Button that starts the discovery process
        final Button btn = view.findViewById(R.id.btn_discover);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Disable the button
                btn.setEnabled(false);
                // Make visible the progress bar
                pb.setVisibility(View.VISIBLE);
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        // Clear the list of ips
                        devices.clear();
                        // Start a new scan
                        sc.newScan();

                        // IP of the device in which the code is running/localhost
                        String myip = null;
                        myip = getIPAddress(true);
                        Log.d(MainActivity.TAG, myip);

                        // Check if the ip is localhost
                        if (myip.equals("127.0.0.1")) {
                            Log.d(MainActivity.TAG, "This PC is not connected to any network!");
                        } else {

                            // just a new string to store currently scanning ip
                            String mynetworkips = "";

                            // this loop finds the right most '.' of this PC's IP
                            // suppose your PC's IP is 192.168.0.101, this loop finds the index of the '.' just before '101'
                            // and as soon as it finds the '.', it creates a new string(actually substring of this PC's IP) starting at
                            // index 0 and ending at index containing character '.' and exits from the loop
                            // So here, if the IP was 192.168.0.101, mynetworkips will have the value "192.168.0."
                            for (int i = myip.length() - 1; i >= 0; --i) {
                                if (myip.charAt(i) == '.') {
                                    // .substring(i,j) returns a string starting from index i and ending at index j-1,so in order to
                                    // include '.' , i put (i+1)
                                    mynetworkips = myip.substring(0, i + 1);
                                    break;
                                }
                            }

                            // (loop bellow->) just add the string representation of i and add it to mynetworkips to get full IP
                            // for example, when i=1 the ip will be(if mynetworkips is "192.168.0.") 192.168.0.1,
                            // and then at next iteration it'll be 192.168.0.1
                            // this means tis loop iterates over all possible ips and show you which one is available or not.

                            // you can change i's range if you know that your network's IPs start from another
                            // point(probably for most router(if not customized) , it will start from 192.168.0.100)
                            for (int i = 1; i <= 254; ++i) {
                                try {

                                    // Create an InetAddrss object with new IP
                                    InetAddress addr = InetAddress.getByName(mynetworkips + Integer.valueOf(i).toString());
                                    // String with the ip address
                                    final String host = addr.getHostAddress();

                                    // Synchronously (it takes too long)
                                    // ArrayList<Integer> openPorts = PortScan.onAddress(host).setMethodTCP().setPort(49160).doScan();
                                    // Log.d(MainActivity.TAG, openPorts.toString());

                                    // Asynchronously (really fast)
                                    PortScan.onAddress(host).setTimeOutMillis(1000).setPort(49160).setMethodTCP().doScan(new PortScan.PortListener() {
                                        @Override
                                        public void onResult(int portNo, boolean open) {
                                            if (open) {
                                                // Stub: found open port
                                                //Log.d(MainActivity.TAG, "Host: " + host + " is open");
                                                devices.add(host);
                                            }
                                            synchronized (sc) {
                                                sc.scanFinished();
                                            }
                                            //Log.d(MainActivity.TAG, "Counter: " + sc.getCounter());
                                            if (sc.hasFinished()) {
                                                //Log.d(MainActivity.TAG, "Finished scanning");
                                                getActivity().runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        // Makes the text view visible
                                                        tv.setVisibility(View.VISIBLE);
                                                        // Create a new adapter for the list view containing all the available ips
                                                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, devices);
                                                        // Set the adapter for the list view
                                                        lv.setAdapter(adapter);
                                                        // Hide the progress bar
                                                        pb.setVisibility(View.INVISIBLE);
                                                        // Show a toast with a message
                                                        String toastText = getString(R.string.discover_toast, devices.size());
                                                        Toast.makeText(getContext(), toastText, Toast.LENGTH_LONG).show();
                                                        // Action executed after a click on an item of the list view
                                                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                            @Override
                                                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                                                // Get the item that the user has selected
                                                                final String selectedIp = (String) adapterView.getItemAtPosition(i);
                                                                //Log.d(MainActivity.TAG, "Selected ip: " + selectedIp);

                                                                // Edit the settings preference for the ip address
                                                                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                                editor.putString("ip", selectedIp).apply();

                                                                // Go back to the main activity
                                                                Intent intent = new Intent(getContext(), MainActivity.class);
                                                                startActivity(intent);
                                                            }
                                                        });
                                                        // Enable the button
                                                        btn.setEnabled(true);
                                                    }
                                                });
                                            }
                                        }

                                        @Override
                                        public void onFinished(ArrayList<Integer> openPorts) {
                                            // Stub: Finished scanning
                                            //Log.d(MainActivity.TAG, "Called onFinished()");
                                        }
                                    });

                                } catch (IOException ioex) {
                                    // nothing to do, just catch it if something goes wrong
                                    Log.e(MainActivity.TAG, "" + ioex.getMessage());
                                }
                            }
                        }
                    }
                });
                t.start();
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

}