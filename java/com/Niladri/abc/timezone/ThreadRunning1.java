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

public class ThreadRunning1 extends AppCompatActivity {

    String originalTime=" ";
    NotificationCompat.Builder notification1;
    String newID1=" ";
    SimpleDateFormat dformat;
    Date date;
    TextView textView,textView1;
    String originalTimeNew=" ";
    NotificationManager nm;
    int notifyID = 1;
    String showID1=" ";
    Handler handler=new Handler();
    int i=1;
    Intent intentNew1;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threadlayout);

        TextView textView2= (TextView) findViewById(R.id.textView2);
        textView2.setText("Page/Notification 2");

        Bundle bundle=getIntent().getExtras();
        newID1=bundle.getString("ID1");

        showID1=bundle.getString("ShowID1");
        notifyID=bundle.getInt("notifyID1");
        dformat=new SimpleDateFormat("yyyy-MM-dd EEE HH:mm:ss");

        date=new Date();
        dformat.setTimeZone(TimeZone.getTimeZone(newID1));
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
                    TimeZone timeZone = TimeZone.getTimeZone(newID1);
                    Date date = new Date();
                    dformat.setTimeZone(timeZone);
                    originalTimeNew = dformat.format(date);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(showID1);
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

        notification1=new NotificationCompat.Builder(this);
        notification1.setAutoCancel(false);
        notification1.setSmallIcon(R.drawable.ic_stat_name);
        notification1.setWhen(System.currentTimeMillis());
        notification1.setContentTitle("Notification 2-Click");
        notification1.setContentText(showID1);

        nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        intentNew1=new Intent(this, ThreadRunning1.class);
        intentNew1.putExtra("ID1",newID1);
        intentNew1.putExtra("ShowID1",showID1);


        PendingIntent pendingIntent1=PendingIntent.getActivity(this,0,intentNew1,PendingIntent.FLAG_UPDATE_CURRENT);
        notification1.setContentIntent(pendingIntent1);

        notifyID=2;
        nm.notify(notifyID,notification1.build());




    }

    public void NewSession(View v)
    {
        String display="Create/Override Notification 3";
        Toast.makeText(this,display,Toast.LENGTH_SHORT).show();
        Intent newIntent=new Intent(this,MainActivity.class);
        newIntent.putExtra("notifyID",++notifyID);
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