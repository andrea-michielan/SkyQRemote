package com.michielan.skyqremote;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

public class RemoteFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_remote, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get ip settings
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String ip = sharedPreferences.getString("ip", "");
        final SkyRemote remote = new SkyRemote(ip);

        view.findViewById(R.id.btn_power).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("power"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.btn_sky).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("sky"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("search"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.btn_help).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("help"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.btn_rewind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("rewind"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.btn_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("play"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.btn_fastforward).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("fastforward"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.btn_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("up"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.btn_down).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("down"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.btn_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("left"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.btn_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("right"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.btn_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("select"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.btn_channelup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("channelup"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.btn_channeldown).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("channeldown"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.btn_record).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("record"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.btn_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("home"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.btn_dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("dismiss"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.btn_interactive).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("interactive"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.btn_red).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("red"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.btn_green).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("green"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.btn_yellow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("yellow"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.btn_blue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("blue"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.btn_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("i"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.btn_dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("dismiss"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.button0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("0"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("1"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("2"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("3"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("4"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("5"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("6"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("7"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("8"));
                new Thread(r).start();
            }
        });

        view.findViewById(R.id.button9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Send the command on a new thread
                Runnable r = new SkyThread(remote, SkyRemote.commands.get("9"));
                new Thread(r).start();
            }
        });

    }

    static class SkyThread implements Runnable {

        private SkyRemote remote;
        private int command;

        public SkyThread(SkyRemote remote, int command) {
            this.remote = remote;
            this.command = command;
        }

        @Override
        public void run() {
            try {
                remote.sendCommand(command);
            } catch (IOException e) {
                Log.d(MainActivity.TAG, e.toString());
            }
        }
    }

}