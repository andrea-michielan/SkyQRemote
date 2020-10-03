package com.michielan.skyqremote;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;

import androidx.annotation.NonNull;

public class SkyRemote {

    private static final int connectionTimeOut = 1000;

    // Port used by SkyQ
    public static final int SKY_Q = 49160;
    // if firmware < 060
    public static final int SKY_Q_LEGACY = 5900;

    // HashMap containing all available commands
    public static final HashMap<String, Integer> commands = new HashMap<>();

    // Populating the map in a static block
    static {
        commands.put("power", 0);
        commands.put("select", 1);
        commands.put("backup", 2);
        commands.put("dismiss", 2);
        commands.put("channelup", 6);
        commands.put("channeldown", 7);
        commands.put("interactive", 8);
        commands.put("sidebar", 8);
        commands.put("help", 9);
        commands.put("services", 10);
        commands.put("search", 10);
        commands.put("tvguide", 11);
        commands.put("home", 11);
        commands.put("i", 14);
        commands.put("text", 15);
        commands.put("up", 16);
        commands.put("down", 17);
        commands.put("left", 18);
        commands.put("right", 19);
        commands.put("red", 32);
        commands.put("green", 33);
        commands.put("yellow", 34);
        commands.put("blue", 35);
        commands.put("0", 48);
        commands.put("1", 49);
        commands.put("2", 50);
        commands.put("3", 51);
        commands.put("4", 52);
        commands.put("5", 53);
        commands.put("6", 54);
        commands.put("7", 55);
        commands.put("8", 56);
        commands.put("9", 57);
        commands.put("play", 64);
        commands.put("pause", 65);
        commands.put("stop", 66);
        commands.put("record", 67);
        commands.put("fastforward", 69);
        commands.put("rewind", 71);
        commands.put("boxoffice", 240);
        commands.put("sky", 241);
    }

    private String host;
    private int port;

    // Constructor with default port
    public SkyRemote(String ip) {
        host = ip;
        port = SKY_Q;
    }

    // Constructor with a specified port
    public SkyRemote(String ip, int p) {
        host = ip;
        port = p;
    }

    @NonNull
    @Override
    public String toString() {
        return "SkyRemote{" +
                "host='" + host + '\'' +
                ", port=" + port +
                '}';
    }

    // Function that sends a command (identified by the code) to the sky q
    public void sendCommand(int code) throws IOException {

        int l = 12;

        // Array of bytes containing the required info to press a button
        byte[] commandBytes = {4, 1, 0, 0, 0, 0, (byte) Math.floor(224 + (code / 16.0)), (byte) (code % 16)};

        // New socket
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(host, port), 1000);

        // Output and input stream
        OutputStream out = socket.getOutputStream();
        BufferedInputStream in = new BufferedInputStream(socket.getInputStream());

        while (true) {

            // Read data sent by skyq
            byte[] resultBuff = new byte[100];
            int len = in.read(resultBuff);

            // If the number of bytes are < 24, then we send him back the same message that we received
            if (len < 24) {
                out.write(resultBuff, 0, l);
                l = 1;
            } else {
                // Otherwise we can send the command
                out.write(commandBytes);
                commandBytes[1] = 0;
                out.write(commandBytes);
                // Close the connection
                socket.close();
                break;
            }

        }

    }

}
