//package com.example.ktirumalsetty.signalrdemo;
//
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.*;
//import microsoft.aspnet.signalr.client.*;
//import microsoft.aspnet.signalr.client.http.android.AndroidPlatformComponent;
//import microsoft.aspnet.signalr.client.hubs.HubConnection;
//import microsoft.aspnet.signalr.client.hubs.HubProxy;
//import microsoft.aspnet.signalr.client.hubs.SubscriptionHandler1;
//import microsoft.aspnet.signalr.client.transport.ClientTransport;
//import microsoft.aspnet.signalr.client.transport.ServerSentEventsTransport;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//
//
//public class MainActivityJ extends AppCompatActivity {
//    ArrayAdapter<String> arrayAdapter;
//    HubConnection mHubConnection;
//    HubProxy mHubProxy;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_j);
////        HubConnection hubConnection = HubConnectionBuilder.create("https://swiftadmin.primehealthcare.com/signalR/hubs").build();
////            HubConnection hubConnection = HubConnectionBuilder.create("http://10.154.71.232:93/signalr/hubs").build();
////            HubConnection hubConnection = HubConnectionBuilder.create("http://10.154.71.232:93/signalr/chat").build();
////            HubConnection hubConnection = HubConnectionBuilder.create("http://10.154.71.232:93/chat").build();
////            HubConnection hubConnection = HubConnectionBuilder.create("http://10.33.16.28:9393/chat").build();
//        TextView textView = (TextView)findViewById(R.id.tvMain);
//        ListView listView = (ListView)findViewById(R.id.lvMessages);
//        Button sendButton = (Button)findViewById(R.id.bSend);
//        EditText editText = (EditText)findViewById(R.id.etMessageText);
//
//        List<String> messageList = new ArrayList<String>();
//        arrayAdapter = new ArrayAdapter<String>(MainActivityJ.this,
//                android.R.layout.simple_list_item_1, messageList);
//        listView.setAdapter(arrayAdapter);
//
////        hubConnection.on("ReceiveMessage", (message)-> {
////            runOnUiThread(new Runnable() {
////                @Override
////                public void run() {
////                    arrayAdapter.add(message);
////                    arrayAdapter.notifyDataSetChanged();
////                }
////            });
////        }, String.class);
////
//        sendButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String message = editText.getText().toString();
//                editText.setText("");
//                try {
//                    String SERVER_METHOD_SEND = "SendMessage";
//                    mHubProxy.invoke(SERVER_METHOD_SEND, message);
////                    mHubConnection.send("SendMessage", message);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
////
////        new HubConnectionTask().execute(hubConnection);
//        startSignalR();
//
//    }
//
////    class HubConnectionTask extends AsyncTask<HubConnection, Void, Void>{
////
////        @Override
////        protected void onPreExecute() {
////            super.onPreExecute();
////        }
////
////        @Override
////        protected Void doInBackground(HubConnection... hubConnections) {
////            HubConnection hubConnection = hubConnections[0];
////            hubConnection.start().blockingAwait();
////            return null;
////        }
////    }
//
//    private void startSignalR() {
//        Platform.loadPlatformComponent(new AndroidPlatformComponent());
//
//        String serverUrl = "http://10.33.16.28:9393/chat/connect";
//        mHubConnection = new HubConnection(serverUrl);
//        String SERVER_HUB_CHAT = "ChatHub";
//        mHubProxy = mHubConnection.createHubProxy(SERVER_HUB_CHAT);
//        ClientTransport clientTransport = new ServerSentEventsTransport(mHubConnection.getLogger());
//        SignalRFuture<Void> signalRFuture = mHubConnection.start(clientTransport);
//
//        try {
//            signalRFuture.get();
//        } catch (InterruptedException | ExecutionException e) {
//            Log.e("SimpleSignalR", e.toString());
//            return;
//        }
//
////        sendMessage("Hello from BNK!");
//
//        String CLIENT_METHOD_BROADAST_MESSAGE = "broadcastMessage";
//        mHubProxy.on(CLIENT_METHOD_BROADAST_MESSAGE,
//                new SubscriptionHandler1<Object>() {
//                    @Override
//                    public void run(final Object msg) {
////                        final String finalMsg = msg.UserName + " says " + msg.Message;
//                        // display Toast message
////                        mHandler.post(new Runnable() {
////                            @Override
////                            public void run() {
////                                Toast.makeText(getApplicationContext(), finalMsg, Toast.LENGTH_SHORT).show();
////                            }
////                        });
//                        arrayAdapter.add(msg.toString());
//                    arrayAdapter.notifyDataSetChanged();
//                    }
//                }
//                , Object.class);
//    }
//
//}