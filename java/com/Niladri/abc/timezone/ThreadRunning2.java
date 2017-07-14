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

public class ThreadRunning2 extends AppCompatActivity {

    String originalTime=" ";
    NotificationCompat.Builder notification2;
    String newID2=" ";
    SimpleDateFormat dformat;
    Date date;
    TextView textView,textView1;
    String originalTimeNew=" ";
    NotificationManager nm;
    int notifyID = 1;
    String showID2=" ";
    Handler handler=new Handler();
    int i=1;
    Intent intentNew2;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threadlayout);

        TextView textView2= (TextView) findViewById(R.id.textView2);
        textView2.setText("Page/Notification 3");

        Bundle bundle=getIntent().getExtras();
        newID2=bundle.getString("ID2");

        showID2=bundle.getString("ShowID2");
        notifyID=bundle.getInt("notifyID2");
        dformat=new SimpleDateFormat("yyyy-MM-dd EEE HH:mm:ss");

        date=new Date();
        dformat.setTimeZone(TimeZone.getTimeZone(newID2));
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
                    TimeZone timeZone = TimeZone.getTimeZone(newID2);
                    Date date = new Date();
                    dformat.setTimeZone(timeZone);
                    originalTimeNew = dformat.format(date);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(showID2);
                            textView1.setText(originalTimeNew);

                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }

        }).start();


        startNotification();
    }
    public void startNotification(){
        notification2=new NotificationCompat.Builder(this);
        notification2.setAutoCancel(false);
        notification2.setSmallIcon(R.drawable.ic_stat_name);
        notification2.setWhen(System.currentTimeMillis());
        notification2.setContentTitle("Notification 3-Click");
        notification2.setContentText(showID2);

        nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        intentNew2=new Intent(this, ThreadRunning2.class);
        intentNew2.putExtra("ID2",newID2);
        intentNew2.putExtra("ShowID2",showID2);



        PendingIntent pendingIntent2=PendingIntent.getActivity(this,0,intentNew2,PendingIntent.FLAG_UPDATE_CURRENT);
        notification2.setContentIntent(pendingIntent2);



        notifyID=3;
        nm.notify(notifyID,notification2.build());


    }

    public void NewSession(View v)
    {
        String display="Create/Override Notification 1";
        Toast.makeText(this,display,Toast.LENGTH_SHORT).show();
        Intent newIntent=new Intent(this,MainActivity.class);
        newIntent.putExtra("notifyID",notifyID-2);
        newIntent.putExtra("methodName","myMethod");//goes to previous INtent
        startActivity(newIntent);//will trigger only myMethod in MainActivity

    }

    public void CancelNotification(View v){
        nm.cancel(notifyID);
        Intent back=new Intent(this,MainActivity.class);
        back.putExtra("notifyID",notifyID);
        startActivity(back);
    }

}