package com.example.owner.sinchstuffie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.sinch.android.rtc.ClientRegistration;
import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.SinchClientListener;
import com.sinch.android.rtc.SinchError;
import com.sinch.android.rtc.messaging.MessageClient;
import com.sinch.android.rtc.messaging.WritableMessage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.content.Context context = this.getApplicationContext();

        SinchClient sinchClient = Sinch.getSinchClientBuilder().context(context)
                .applicationKey("52f37bcc-5c85-4124-9f00-b4e1bf6bc696")
                .applicationSecret("wu5VaCwnb0KyyXazW6JnXw==")
                .environmentHost("sandbox.sinch.com")
                .userId("lui.frankie.c@gmail.com")
                .build();
        sinchClient.setSupportMessaging(true);
        sinchClient.start();

        MessageClient messageClient = sinchClient.getMessageClient();
        WritableMessage message = new WritableMessage("someRecipientUserId", "Hello someRecipientUserId!");
        sinchClient.addSinchClientListener(new SinchClientListener() {
            @Override
            public void onClientStarted(SinchClient sinchClient) {
                sinchClient.startListeningOnActiveConnection();
            }

            @Override
            public void onClientStopped(SinchClient sinchClient) {

            }

            @Override
            public void onClientFailed(SinchClient sinchClient, SinchError sinchError) {

            }

            @Override
            public void onRegistrationCredentialsRequired(SinchClient sinchClient, ClientRegistration clientRegistration) {

            }

            @Override
            public void onLogMessage(int level, String area, String message) {

            }
        });

        messageClient.send(message);
        //messageClient.addMessageClientListener();
        sinchClient.stopListeningOnActiveConnection();
        sinchClient.terminate();
    }
}
