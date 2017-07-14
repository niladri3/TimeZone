package com.Niladri.abc.timezone;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ThreadRunning extends AppCompatActivity {


    String originalTime=" ";
    NotificationCompat.Builder notification;
    String newID=" ";
    SimpleDateFormat dformat;
    Date date;
    TextView textView,textView1;
    String originalTimeNew=" ";
    NotificationManager nm;
    int notifyID = 1;
    String showID=" ";
    Handler handler=new Handler();
    static Intent intentNew;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threadlayout);

        //In Which Page I am
        TextView textView2= (TextView) findViewById(R.id.textView2);
        textView2.setText("Page/Notification 1");

        Bundle bundle=getIntent().getExtras();
        newID=bundle.getString("ID");
        showID=bundle.getString("ShowID");
        notifyID=bundle.getInt("notifyID");
        dformat=new SimpleDateFormat("yyyy-MM-dd EEE HH:mm:ss");
        //timeZone=TimeZone.getTimeZone(newID);
        date=new Date();
        dformat.setTimeZone(TimeZone.getTimeZone(newID));
        originalTime=dformat.format(date);



        textView=(TextView)findViewById(R.id.textView);
        textView1=(TextView)findViewById(R.id.textView1);


        notificationStarts();



    }

    public void notificationStarts(){


        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 1000; ) {

                        TimeZone timeZone = TimeZone.getTimeZone(newID);
                        Date date = new Date();
                        dformat.setTimeZone(timeZone);
                        originalTimeNew = dformat.format(date);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(showID);
                                textView1.setText(originalTimeNew);

                            }
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        //Checking is done here when to stop the Thread
                    }

                }


        }).start();


        startNotification();
    }
    public void startNotification(){

        notification=new NotificationCompat.Builder(this);
        notification.setAutoCancel(false);
        notification.setSmallIcon(R.drawable.ic_stat_name);
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Notification 1-Click");
        notification.setContentText(showID);


        nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        intentNew=new Intent(this,ThreadRunning.class);
        intentNew.putExtra("ID",newID);
        intentNew.putExtra("ShowID",showID);

        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intentNew,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        notifyID=1;
        nm.notify(notifyID,notification.build());




    }

    public void NewSession(View v)
    {
        String display="Create/Override Notification 2";
        Toast.makeText(this,display,Toast.LENGTH_SHORT).show();
        Intent newIntent=new Intent(this,MainActivity.class);
        newIntent.putExtra("notifyID",++notifyID);
        startActivity(newIntent);//will trigger only myMethod in MainActivity
        //startActivity(newIntent);

    }

    public void CancelNotification(View v){

        nm.cancel(notifyID);
        Intent back=new Intent(this,MainActivity.class);
        startActivity(back);


    }


}