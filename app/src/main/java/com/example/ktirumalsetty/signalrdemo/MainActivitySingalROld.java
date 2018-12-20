package com.example.ktirumalsetty.signalrdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;

import microsoft.aspnet.signalr.client.LogLevel;
import microsoft.aspnet.signalr.client.Logger;
import microsoft.aspnet.signalr.client.Platform;
import microsoft.aspnet.signalr.client.SignalRFuture;
import microsoft.aspnet.signalr.client.http.android.AndroidPlatformComponent;
import microsoft.aspnet.signalr.client.hubs.HubConnection;
import microsoft.aspnet.signalr.client.hubs.HubProxy;
import microsoft.aspnet.signalr.client.hubs.SubscriptionHandler1;
import microsoft.aspnet.signalr.client.transport.ClientTransport;
import microsoft.aspnet.signalr.client.transport.ServerSentEventsTransport;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivitySingalROld extends AppCompatActivity {
    ArrayAdapter<String> arrayAdapter;
    HubConnection mHubConnection;
    HubProxy mHubProxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_j);

        TextView textView = (TextView) findViewById(R.id.tvMain);
        ListView listView = (ListView) findViewById(R.id.lvMessages);
        Button sendButton = (Button) findViewById(R.id.bSend);
        EditText editText = (EditText) findViewById(R.id.etMessageText);

        List<String> messageList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(MainActivitySingalROld.this,
                android.R.layout.simple_list_item_1, messageList);
        listView.setAdapter(arrayAdapter);



        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = editText.getText().toString();
                editText.setText("");
                try {
                    String SERVER_METHOD_SEND = "moveShape";
                    mHubProxy.invoke(SERVER_METHOD_SEND, "kondal",message);
//                    mHubConnection.send("SendMessage", message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
//
        startSignalR();

    }


    private void startSignalR() {
        Platform.loadPlatformComponent(new AndroidPlatformComponent());

//        String serverUrl = "http://10.33.16.28:9393/chat";
//        String serverUrl = "https://swiftadmin.primehealthcare.com/signalR/hubs";
        String serverUrl = "http://10.154.71.231:95/";
//        mHubConnection = new HubConnection(serverUrl);
        mHubConnection = new HubConnection(serverUrl,"", true, new Logger() {
            @Override
            public void log(String message, LogLevel level) {

                Log.d("HubConnection",message);
            }
        });
        String SERVER_HUB_CHAT = "moveShape";
        mHubProxy = mHubConnection.createHubProxy(SERVER_HUB_CHAT);
        ClientTransport clientTransport = new ServerSentEventsTransport(mHubConnection.getLogger());
        SignalRFuture<Void> signalRFuture = mHubConnection.start(clientTransport);

        try {
            signalRFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.e("SimpleSignalR", e.toString());
            return;
        }
        String mConnectionID = mHubConnection.getConnectionId();
        Log.d("MainActivitySingalROld","mConnectionID "+mConnectionID);
//        sendMessage("Hello from BNK!");

//        String CLIENT_METHOD_BROADAST_MESSAGE = "broadcastMessage";
        String CLIENT_METHOD_BROADAST_MESSAGE = "NewMessage";

        mHubProxy.subscribe(CLIENT_METHOD_BROADAST_MESSAGE);
        mHubProxy.on(CLIENT_METHOD_BROADAST_MESSAGE,
                new SubscriptionHandler1<Object>() {
                    @Override
                    public void run(final Object msg) {
//                        final String finalMsg = msg.UserName + " says " + msg.Message;
                        // display Toast message
//                        mHandler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(getApplicationContext(), finalMsg, Toast.LENGTH_SHORT).show();
//                            }
//                        });
                        arrayAdapter.add(msg.toString());
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
                , Object.class);
    }
}
