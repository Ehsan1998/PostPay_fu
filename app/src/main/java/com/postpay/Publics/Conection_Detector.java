package com.postpay.Publics;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import com.postpay.Activities.ConnectionErrorActivity;
import com.postpay.Activities.GetPhoneNumberActivity;
import com.postpay.Activities.HomePageActivity;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Conection_Detector extends AsyncTask<String,Void,Integer> {

    Context context;

    public Conection_Detector(Context context) {
        this.context = context;
    }

    public Boolean isConnected(){

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if (connectivityManager != null){
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null){
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected Integer doInBackground(String... strings) {

        Integer result = 0;
        try {
            Socket socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress("8.8.8.8",53);
            socket.connect(socketAddress,1500);
            socket.close();
            result = 1;
        } catch (IOException e){
            result = 0;
        }
        return result;
    }

    @Override
    protected void onPostExecute(Integer result) {
        if (isConnected()){
            if (result == 1){
                Intent intent = new Intent(context, GetPhoneNumberActivity.class);
                context.startActivity(intent);
            }
            if (result == 0){
                Intent intent = new Intent(context, ConnectionErrorActivity.class);
                context.startActivity(intent);
            }
        }
        else {
            Intent intent = new Intent(context, ConnectionErrorActivity.class);
            context.startActivity(intent);
        }
        super.onPostExecute(result);
    }
}
