package com.example.teo.notificaciones;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener{
    int notifID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnNotif = (Button) findViewById(R.id.btnNotif);
        btnNotif.setOnClickListener(this);
        btnNotif.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNotif:
                NotificationCompat.Builder constructorNotificaciones = new
                        NotificationCompat.Builder(this);

                constructorNotificaciones.setSmallIcon(R.mipmap.ic_launcher);
                constructorNotificaciones.setContentTitle(getString(R.string.TituloNotificacion));
                constructorNotificaciones.setContentText(getString(R.string.TextoNotificacion));

                Intent resultadoIntent = new Intent(this, MainActivity.class);

                TaskStackBuilder pila = TaskStackBuilder.create(this);
                pila.addParentStack(MainActivity.class);
                pila.addNextIntent(resultadoIntent);

                PendingIntent resultadoPendingIntent =
                        pila.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

                constructorNotificaciones.setContentIntent(resultadoPendingIntent);

                NotificationManager GestorNotificaciones = (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);
                GestorNotificaciones.notify(notifID,constructorNotificaciones.build());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()){
            case R.id.btnNotif:
                NotificationManager GestorNotificaciones = (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);
                GestorNotificaciones.cancel(notifID);
                return true;
        }
        return false;
    }
}
