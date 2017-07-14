package com.Niladri.abc.timezone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {



    protected static final int UPDATE_UI = 0;
    protected ListView listView1;
    protected ArrayAdapter listAdapter1;
    protected String realID = " ";
    protected TimeZone tz;
    protected String store=" ";
    protected String text=" ";
    protected String showID=" ";
    protected int notifyID=1;
    protected EditText inputSearch;
    protected ArrayList ImpZoneList;

    String ImpZone[]={"(UTC-12:00) Dateline Standard Time","(UTC-11:00) Coordinated Universal Time-11","(UTC-10:00) Aleutian Standard Time",
            "(UTC-10:00) Hawaiian Standard Time","(UTC-09:00) Alaskan Standard Time","(UTC-09:00) Coordinated Universal Time-09",
            "(UTC-08:00) Pacific Standard Time (Mexico) ","(UTC-08:00) Coordinated Universal Time-08","(UTC-08:00) Pacific Standard Time",
            "(UTC-07:00) US Mountain Standard Time","(UTC-07:00) Mountain Standard Time (Mexico)","(UTC-07:00) Mountain Standard Time ",
            "(UTC-06:00) Central America Standard Time ","(UTC-06:00) Central Standard Time","(UTC-06:00) Easter Island Standard Time ",
            "(UTC-06:00) Central Standard Time (Mexico)","(UTC-06:00) Canada Central Standard Time ","(UTC-05:00) SA Pacific Standard Time ",
            "(UTC-05:00) Eastern Standard Time (Mexico) ",
            "(UTC-05:00) Eastern Standard Time","(UTC-05:00) Haiti Standard Time ","(UTC-05:00) Cuba Standard Time  ","(UTC-05:00) US Eastern Standard Time",
            "(UTC-04:00) Paraguay Standard Time","(UTC-04:00) Atlantic Standard Time ","(UTC-04:00) Venezuela Standard Time","(UTC-04:00) Central Brazilian Standard Time  ",
            "(UTC-04:00) SA Western Standard Time ","(UTC-04:00) Pacific SA Standard Time","(UTC-04:00) Turks And Caicos Standard Time",
            "(UTC-03:30) Newfoundland Standard Time","(UTC-03:00) Tocantins Standard Time","(UTC-03:00) Tocantins Standard Time","(UTC-03:00) SA Eastern Standard Time ",
            "(UTC-03:00) SA Eastern Standard Time ","(UTC-03:00) Greenland Standard Time ","(UTC-03:00) Montevideo Standard Time","(UTC-03:00) Saint Pierre Standard Time",
            " (UTC-03:00) Bahia Standard Time","(UTC-02:00) Coordinated Universal Time-02","(UTC-01:00) Azores Standard Time ","(UTC-01:00) Cape Verde Standard Time",
            "(UTC) Coordinated Universal Time","(UTC+00:00) Morocco Standard Time","(UTC+00:00) GMT Standard Time","(UTC+00:00) Greenwich Standard Time ",
            "(UTC+01:00) W. Europe Standard Time","(UTC+01:00) W. Europe Standard Time",
            "(UTC+01:00) W. Europe Standard Time","(UTC+01:00) Central European Standard Time","(UTC+01:00) W. Central Africa Standard Time",
            "(UTC+01:00) Namibia Standard Time","(UTC+02:00) Jordan Standard Time","(UTC+02:00) GTB Standard Time","(UTC+02:00) Middle East Standard Time","(UTC+02:00) Egypt Standard Time",
            "(UTC+02:00) E. Europe Standard Time",
            "(UTC+02:00) Syria Standard Time","(UTC+02:00) West Bank Standard Time","(UTC+02:00) South Africa Standard Tim","(UTC+02:00) FLE Standard Time",
            "(UTC+02:00) Israel Standard Time","(UTC+02:00) Kaliningrad Standard Time","(UTC+02:00) Libya Standard Time","(UTC+03:00) Arabic Standard Time","(UTC+03:00) Turkey Standard Time",
            "(UTC+03:00) Arab Standard Time",
            "(UTC+03:00) Belarus Standard Time","(UTC+03:00) Russian Standard Time","(UTC+03:00) E. Africa Standard Time","(UTC+03:30) Iran Standard Time","(UTC+04:00) Arabian Standard Time",
            "(UTC+04:00) Astrakhan Standard Time","(UTC+04:00) Azerbaijan Standard Time","(UTC+04:00) Russia Time Zone 3","(UTC+04:00) Mauritius Standard Time","(UTC+04:00) Georgian Standard Time",
            "(UTC+04:00) Caucasus Standard Time","(UTC+04:30) Afghanistan Standard Time",
            "(UTC+05:00) West Asia Standard Time","(UTC+05:00) Ekaterinburg Standard Time","(UTC+05:00) Pakistan Standard Time",
            "(UTC+05:30) India Standard Time","(UTC+05:30) Sri Lanka Standard Time","(UTC+05:45) Nepal Standard Time","(UTC+06:00) Central Asia Standard Time",
            "(UTC+06:00) Bangladesh Standard Time","(UTC+06:00) Omsk Standard Time","(UTC+06:30) Myanmar Standard Time","(UTC+07:00) SE Asia Standard Time","(UTC+07:00) Altai Standard Time",
            "(UTC+07:00) W. Mongolia Standard Time","(UTC+07:00) North Asia Standard Time","(UTC+07:00) N. Central Asia Standard Time","(UTC+07:00) Tomsk Standard Time",
            "(UTC+08:00) China Standard Time","(UTC+08:00) North Asia East Standard Time","(UTC+08:00) Singapore Standard Time","(UTC+08:00) W. Australia Standard Time",
            "(UTC+08:00) Taipei Standard Time",
            "(UTC+08:00) Ulaanbaatar Standard Time","(UTC+08:30) North Korea Standard Time","(UTC+08:30) North Korea Standard Time","(UTC+09:00) Transbaikal Standard Time",
            "(UTC+09:00) Tokyo Standard Time",
            "(UTC+09:00) Korea Standard Time","(UTC+09:00) Yakutsk Standard Time","(UTC+09:30) Cen. Australia Standard Time","(UTC+09:30) AUS Central Standard Time","(UTC+10:00) E. Australia Standard Time",
            "(UTC+10:00) AUS Eastern Standard Time","(UTC+10:00) West Pacific Standard Time","(UTC+10:00) Tasmania Standard Time","(UTC+10:00) Vladivostok Standard Time",
            "(UTC+10:30) Lord Howe Standard Time",
            "(UTC+11:00) Bougainville Standard Time","(UTC+12:00) Russia Time Zone 10","(UTC+11:00) Magadan Standard Time","(UTC+11:00) Norfolk Standard Time","(UTC+11:00) Sakhalin Standard Time",
            "(UTC+11:00) Central Pacific Standard Time","(UTC+12:00) Russia Time Zone 11","(UTC+12:00) New Zealand Standard Time",
            "(UTC+12:00) Coordinated Universal Time+12","(UTC+12:00) Fiji Standard Time","(UTC+12:45) Chatham Islands Standard Time","(UTC+13:00) Tonga Standard Time",
            "(UTC+13:00) Samoa Standard Time","(UTC+14:00) Line Islands Standard Time"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar to get the action bar name
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Arrays.sort(ImpZone);//To soert the array according to UTC


        listView1 = (ListView) findViewById(R.id.list_item1);

        listAdapter1 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, ImpZone);

        listView1.setAdapter(listAdapter1);//Filled up all the items in the listView

        inputSearch=(EditText)findViewById(R.id.inputSearch);//SearchBox


        //input Search function
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.this.listAdapter1.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                text=(String)listAdapter1.getItem(position);

                SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                    switch(text) {

                        case "(UTC-12:00) Dateline Standard Time":
                            showID="(UTC-12:00) Dateline Standard Time";
                            realID="Etc/GMT+12";
                            tz = TimeZone.getTimeZone("Etc/GMT+12");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;

                        case "(UTC-11:00) Coordinated Universal Time-11":
                            showID="(UTC-11:00) Coordinated Universal Time-11";
                            realID="Etc/GMT+11";
                            tz = TimeZone.getTimeZone("Etc/GMT+11");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-10:00) Aleutian Standard Time":
                            showID="(UTC-10:00) Aleutian Islands";
                            realID="America/Adak";
                            tz = TimeZone.getTimeZone("America/Adak");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;

                        case "(UTC-10:00) Hawaiian Standard Time":
                            showID="(UTC-10:00) Hawaiian Standard Time";
                            tz = TimeZone.getTimeZone("Pacific/Honolulu");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-09:00) Alaskan Standard Time":
                            showID="(UTC-09:00) Alaskan Standard Time";
                            realID="America/Anchorage";
                            tz = TimeZone.getTimeZone("America/Anchorage");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-09:00) Coordinated Universal Time-09":
                            showID="(UTC-09:00) Coordinated Universal Time-09";
                            realID="Etc/GMT+9";
                            tz = TimeZone.getTimeZone("Etc/GMT+9");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;

                        case "(UTC-08:00) Pacific Standard Time (Mexico) ":
                            showID="(UTC-08:00) Pacific Standard Time (Mexico) ";
                            realID="America/Tijuana";
                            tz = TimeZone.getTimeZone("America/Tijuana");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;

                        case "(UTC-08:00) Coordinated Universal Time-08":
                            showID="(UTC-08:00) Coordinated Universal Time-08";
                            realID="Etc/GMT+8";
                            tz = TimeZone.getTimeZone("Etc/GMT+8");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-08:00) Pacific Standard Time":
                            showID="(UTC-08:00) Pacific Standard Time";
                            realID="America/Los_Angeles";
                            tz = TimeZone.getTimeZone("America/Los_Angeles");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-07:00) US Mountain Standard Time":
                            showID="(UTC-07:00) US Mountain Standard Time";
                            realID="America/Phoenix";
                            tz = TimeZone.getTimeZone("America/Phoenix");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-07:00) Mountain Standard Time (Mexico)":
                            showID="(UTC-07:00) Mountain Standard Time (Mexico)";
                            realID="America/Chihuahua";
                            tz = TimeZone.getTimeZone("America/Chihuahua");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-07:00) Mountain Standard Time ":
                            showID="(UTC-07:00) Mountain Standard Time ";
                            realID="America/Denver";
                            tz = TimeZone.getTimeZone("America/Denver");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-06:00) Central America Standard Time ":
                            showID="(UTC-06:00) Central America Standard Time ";
                            realID="America/Guatemala";
                            tz = TimeZone.getTimeZone("America/Guatemala");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-06:00) Central Standard Time":
                            showID="(UTC-06:00) Central Standard Time";
                            realID="America/Chicago";
                            tz = TimeZone.getTimeZone("America/Chicago");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-06:00) Easter Island Standard Time ":
                            showID="(UTC-06:00) Easter Island Standard Time ";
                            realID="Pacific/Easter";
                            tz = TimeZone.getTimeZone("Pacific/Easter");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;

                        case "(UTC-06:00) Central Standard Time (Mexico)":
                            showID="(UTC-06:00) Central Standard Time (Mexico)";
                            realID="America/Mexico_City";
                            tz = TimeZone.getTimeZone("America/Mexico_City");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-06:00) Canada Central Standard Time ":
                            showID="(UTC-06:00) Canada Central Standard Time ";
                            realID="America/Regina";
                            tz = TimeZone.getTimeZone("America/Regina");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-05:00) SA Pacific Standard Time ":
                            showID="(UTC-05:00) SA Pacific Standard Time ";
                            realID="America/Bogota";
                            tz = TimeZone.getTimeZone("America/Bogota");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-05:00) Eastern Standard Time (Mexico) ":
                            showID="(UTC-05:00) Eastern Standard Time (Mexico) ";
                            realID="America/Cancun";
                            tz = TimeZone.getTimeZone("America/Cancun");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-05:00) Eastern Standard Time":
                            showID="(UTC-05:00) Eastern Standard Time";
                            realID="America/New_York";
                            tz = TimeZone.getTimeZone("America/New_York");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-05:00) Haiti Standard Time ":
                            showID="(UTC-05:00) Haiti Standard Time ";
                            realID="America/Port-au-Prince";
                            tz = TimeZone.getTimeZone("America/Port-au-Prince");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-05:00) Cuba Standard Time  ":
                            showID="(UTC-05:00) Cuba Standard Time  ";
                            realID="America/Havana";
                            tz = TimeZone.getTimeZone("America/Havana");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-05:00) US Eastern Standard Time":
                            showID="(UTC-05:00) US Eastern Standard Time";
                            realID="America/Indianapolis";
                            tz = TimeZone.getTimeZone("America/Indianapolis");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;

                        case "(UTC-04:00) Paraguay Standard Time":
                            showID="(UTC-04:00) Paraguay Standard Time";
                            realID="America/Asuncion";
                            tz = TimeZone.getTimeZone("America/Asuncion");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-04:00) Atlantic Standard Time ":
                            showID="(UTC-04:00) Atlantic Standard Time ";
                            realID="America/Halifax";
                            tz = TimeZone.getTimeZone("America/Halifax");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;

                        case "(UTC-04:00) Venezuela Standard Time":
                            showID= "(UTC-04:00) Venezuela Standard Time";
                            realID="America/Caracas";
                            tz = TimeZone.getTimeZone("America/Caracas");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-04:00) Central Brazilian Standard Time  ":
                            showID="(UTC-04:00) Central Brazilian Standard Time  ";
                            realID="America/Cuiaba";
                            tz = TimeZone.getTimeZone("America/Cuiaba");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;

                        case "(UTC-04:00) SA Western Standard Time ":
                            showID="(UTC-04:00) SA Western Standard Time ";
                            realID="America/La_Paz";
                            tz = TimeZone.getTimeZone("America/La_Paz");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-04:00) Pacific SA Standard Time":
                            showID="(UTC-04:00) Pacific SA Standard Time";
                            realID="America/Santiago";
                            tz = TimeZone.getTimeZone("America/Santiago");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-04:00) Turks And Caicos Standard Time":
                            showID="(UTC-04:00) Turks And Caicos Standard Time";
                            realID="America/Grand_Turk";
                            tz = TimeZone.getTimeZone("America/Grand_Turk");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-03:30) Newfoundland Standard Time":
                            showID="(UTC-03:30) Newfoundland Standard Time";
                            realID="America/St_Johns";
                            tz = TimeZone.getTimeZone("America/St_Johns");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-03:00) Tocantins Standard Time":
                            showID="(UTC-03:00) Tocantins Standard Time";
                            realID="America/Araguaina";
                            tz = TimeZone.getTimeZone("America/Araguaina");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-03:00) E. South America Standard Time ":
                            showID="(UTC-03:00) E. South America Standard Time ";
                            realID="America/Sao_Paulo";
                            tz = TimeZone.getTimeZone("America/Sao_Paulo");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-03:00) SA Eastern Standard Time ":
                            showID="(UTC-03:00) SA Eastern Standard Time ";
                            realID="America/Cayenne";
                            tz = TimeZone.getTimeZone("America/Cayenne");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-03:00) Argentina Standard Time":
                            showID="(UTC-03:00) Argentina Standard Time";
                            realID="America/Buenos_Aires";
                            tz = TimeZone.getTimeZone("America/Buenos_Aires");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-03:00) Greenland Standard Time ":
                            showID="(UTC-03:00) Greenland Standard Time ";
                            realID="America/Godthab";
                            tz = TimeZone.getTimeZone("America/Godthab");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-03:00) Montevideo Standard Time":
                            showID="(UTC-03:00) Montevideo Standard Time";
                            realID="America/Montevideo";
                            tz = TimeZone.getTimeZone("America/Montevideo");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-03:00) Saint Pierre Standard Time":
                            showID="(UTC-03:00) Saint Pierre Standard Time";
                            realID="America/Miquelon";
                            tz = TimeZone.getTimeZone("America/Miquelon");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case " (UTC-03:00) Bahia Standard Time":
                            showID=" (UTC-03:00) Bahia Standard Time";
                            realID="America/Bahia";
                            tz = TimeZone.getTimeZone("America/Bahia");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-02:00) Coordinated Universal Time-02":
                            showID="(UTC-02:00) Coordinated Universal Time-02";
                            realID="Etc/GMT+2";
                            tz = TimeZone.getTimeZone("Etc/GMT+2");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-01:00) Azores Standard Time ":
                            showID="(UTC-01:00) Azores Standard Time ";
                            realID="Atlantic/Azores";
                            tz = TimeZone.getTimeZone("Atlantic/Azores");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC-01:00) Cape Verde Standard Time":
                            showID="(UTC-01:00) Cape Verde Standard Time";
                            realID="Atlantic/Cape_Verde";
                            tz = TimeZone.getTimeZone("Atlantic/Cape_Verde");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC) Coordinated Universal Time":
                            showID="(UTC) Coordinated Universal Time";
                            realID="Etc/GMT";
                            tz = TimeZone.getTimeZone("Etc/GMT");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+00:00) Morocco Standard Time":
                            showID="(UTC+00:00) Morocco Standard Time";
                            realID="Africa/Casablanca";
                            tz = TimeZone.getTimeZone("Africa/Casablanca");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+00:00) GMT Standard Time":
                            showID="(UTC+00:00) GMT Standard Time";
                            realID="Europe/London";
                            tz = TimeZone.getTimeZone("Europe/London");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+00:00) Greenwich Standard Time ":
                            showID="(UTC+00:00) Greenwich Standard Time ";
                            realID="Atlantic/Reykjavik";
                            tz = TimeZone.getTimeZone("Atlantic/Reykjavik");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+01:00) W. Europe Standard Time":
                            showID="(UTC+01:00) W. Europe Standard Time";
                            realID="Europe/Berlin";
                            tz = TimeZone.getTimeZone("Europe/Berlin");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+01:00) Central Europe Standard Time ":
                            showID="(UTC+01:00) Central Europe Standard Time ";
                            realID="Europe/Budapest";
                            tz = TimeZone.getTimeZone("Europe/Budapest");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+01:00) Romance Standard Time":
                            showID="(UTC+01:00) Romance Standard Time";
                            realID="Europe/Paris";
                            tz = TimeZone.getTimeZone("Europe/Paris");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+01:00) Central European Standard Time":
                            showID="(UTC+01:00) Central European Standard Time";
                            realID="Europe/Warsaw";
                            tz = TimeZone.getTimeZone("Europe/Warsaw");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+01:00) W. Central Africa Standard Time":
                            showID="(UTC+01:00) W. Central Africa Standard Time";
                            realID="Africa/Lagos";
                            tz = TimeZone.getTimeZone("Africa/Lagos");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+01:00) Namibia Standard Time":
                            showID="(UTC+01:00) Namibia Standard Time";
                            realID="Africa/Windhoek";
                            tz = TimeZone.getTimeZone("Africa/Windhoek");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+02:00) Jordan Standard Time":
                            showID="(UTC+02:00) Jordan Standard Time";
                            realID="Asia/Amman";
                            tz = TimeZone.getTimeZone("Asia/Amman");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+02:00) GTB Standard Time":
                            showID="(UTC+02:00) GTB Standard Time";
                            realID="Europe/Bucharest";
                            tz = TimeZone.getTimeZone("Europe/Bucharest");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+02:00) Middle East Standard Time":
                            showID="(UTC+02:00) Middle East Standard Time";
                            realID="Asia/Beirut";
                            tz = TimeZone.getTimeZone("Asia/Beirut");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+02:00) Egypt Standard Time":
                            showID="(UTC+02:00) Egypt Standard Time";
                            realID="Africa/Cairo";
                            tz = TimeZone.getTimeZone("Africa/Cairo");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+02:00) E. Europe Standard Time":
                            showID="(UTC+02:00) E. Europe Standard Time";
                            realID="Europe/Chisinau";
                            tz = TimeZone.getTimeZone("Europe/Chisinau");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+02:00) Syria Standard Time":
                            showID="(UTC+02:00) Syria Standard Time";
                            realID="Asia/Damascus";
                            tz = TimeZone.getTimeZone("Asia/Damascus");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+02:00) West Bank Standard Time":
                            showID="(UTC+02:00) West Bank Standard Time";
                            realID="Asia/Hebron";
                            tz = TimeZone.getTimeZone("Asia/Hebron");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+02:00) South Africa Standard Tim":
                            showID="(UTC+02:00) South Africa Standard Tim";
                            realID="Africa/Johannesburg";
                            tz = TimeZone.getTimeZone("Africa/Johannesburg");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+02:00) FLE Standard Time":
                            showID="(UTC+02:00) FLE Standard Time";
                            realID="Europe/Kiev";
                            tz = TimeZone.getTimeZone("Europe/Kiev");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+02:00) Israel Standard Time":
                            showID="(UTC+02:00) Israel Standard Time";
                            realID="Asia/Jerusalem";
                            tz = TimeZone.getTimeZone("Asia/Jerusalem");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+02:00) Kaliningrad Standard Time":
                            showID="(UTC+02:00) Kaliningrad Standard Time";
                            realID="Europe/Kaliningrad";
                            tz = TimeZone.getTimeZone("Europe/Kaliningrad");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+02:00) Libya Standard Time":
                            showID="(UTC+02:00) Libya Standard Time";
                            realID="Africa/Tripoli";
                            tz = TimeZone.getTimeZone("Africa/Tripoli");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+03:00) Arabic Standard Time":
                            showID="(UTC+03:00) Arabic Standard Time";
                            realID="Asia/Baghdad";
                            tz = TimeZone.getTimeZone("Asia/Baghdad");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+03:00) Turkey Standard Time":
                            showID="(UTC+03:00) Turkey Standard Time";
                            realID="Europe/Istanbul";
                            tz = TimeZone.getTimeZone("Europe/Istanbul");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+03:00) Arab Standard Time":
                            showID="(UTC+03:00) Arab Standard Time";
                            realID="Asia/Riyadh";
                            tz = TimeZone.getTimeZone("Asia/Riyadh");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+03:00) Belarus Standard Time":
                            showID="(UTC+03:00) Belarus Standard Time";
                            realID="Europe/Minsk";
                            tz = TimeZone.getTimeZone("Europe/Minsk");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+03:00) Russian Standard Time":
                            showID="(UTC+03:00) Russian Standard Time";
                            realID="Europe/Moscow";
                            tz = TimeZone.getTimeZone("Europe/Moscow");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+03:00) E. Africa Standard Time":
                            showID="(UTC+03:00) E. Africa Standard Time";
                            realID="Africa/Nairobi";
                            tz = TimeZone.getTimeZone("Africa/Nairobi");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+03:30) Iran Standard Time":
                            showID="(UTC+03:30) Iran Standard Time";
                            realID="Asia/Tehran";
                            tz = TimeZone.getTimeZone("Asia/Tehran");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+04:00) Arabian Standard Time":
                            showID="(UTC+04:00) Arabian Standard Time";
                            realID="Asia/Dubai";
                            tz = TimeZone.getTimeZone("Asia/Dubai");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+04:00) Astrakhan Standard Time":
                            showID="(UTC+04:00) Astrakhan Standard Time";
                            realID="Europe/Astrakhan";
                            tz = TimeZone.getTimeZone("Europe/Astrakhan");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+04:00) Azerbaijan Standard Time":
                            showID="(UTC+04:00) Azerbaijan Standard Time";
                            realID="Asia/Baku";
                            tz = TimeZone.getTimeZone("Asia/Baku");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+04:00) Russia Time Zone 3":
                            showID="(UTC+04:00) Russia Time Zone 3";
                            realID="Europe/Samara";
                            tz = TimeZone.getTimeZone("Europe/Samara");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;

                        case "(UTC+04:00) Mauritius Standard Time":
                            showID="(UTC+04:00) Mauritius Standard Time";
                            realID="Indian/Mauritius";
                            tz = TimeZone.getTimeZone("Indian/Mauritius");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+04:00) Georgian Standard Time":
                            showID="(UTC+04:00) Georgian Standard Time";
                            realID="Asia/Tbilisi";
                            tz = TimeZone.getTimeZone("Asia/Tbilisi");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+04:00) Caucasus Standard Time":
                            showID="(UTC+04:00) Caucasus Standard Time";
                            realID="Asia/Yerevan";
                            tz = TimeZone.getTimeZone("Asia/Yerevan");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+04:30) Afghanistan Standard Time":
                            showID="(UTC+04:30) Afghanistan Standard Time";
                            realID="Asia/Kabul";
                            tz = TimeZone.getTimeZone("Asia/Kabul");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+05:00) West Asia Standard Time":
                            showID="(UTC+05:00) West Asia Standard Time";
                            realID="Asia/Tashkent";
                            tz = TimeZone.getTimeZone("Asia/Tashkent");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+05:00) Ekaterinburg Standard Time":
                            showID="(UTC+05:00) Ekaterinburg Standard Time";
                            realID="Asia/Yekaterinburg";
                            tz = TimeZone.getTimeZone("Asia/Yekaterinburg");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+05:00) Pakistan Standard Time":
                            showID="(UTC+05:00) Pakistan Standard Time";
                            realID="Asia/Karachi";
                            tz = TimeZone.getTimeZone("Asia/Karachi");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+05:30) India Standard Time":
                            showID="(UTC+05:30) India Standard Time";
                            realID="Asia/Calcutta";
                            tz = TimeZone.getTimeZone("Asia/Calcutta");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;

                        case "(UTC+05:30) Sri Lanka Standard Time":
                            showID="(UTC+05:30) Sri Lanka Standard Time";
                            realID="Asia/Colombo";
                            tz = TimeZone.getTimeZone("Asia/Colombo");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;

                        //Kathmandu

                        case "(UTC+05:45) Nepal Standard Time":
                            showID="(UTC+05:45) Nepal Standard Time";
                            realID="Asia/Katmandu";
                            tz = TimeZone.getTimeZone("Asia/Katmandu");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+06:00) Central Asia Standard Time":
                            showID="(UTC+06:00) Central Asia Standard Time";
                            realID="Asia/Almaty";
                            tz = TimeZone.getTimeZone("Asia/Almaty");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+06:00) Bangladesh Standard Time":
                            showID="(UTC+06:00) Bangladesh Standard Time";
                            realID="Asia/Dhaka";
                            tz = TimeZone.getTimeZone("Asia/Dhaka");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+06:00) Omsk Standard Time":
                            showID="(UTC+06:00) Omsk Standard Time";
                            realID="Asia/Omsk";
                            tz = TimeZone.getTimeZone("Asia/Omsk");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+06:30) Myanmar Standard Time":
                            showID="(UTC+06:30) Myanmar Standard Time";
                            realID="Asia/Rangoon";
                            tz = TimeZone.getTimeZone("Asia/Rangoon");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+07:00) SE Asia Standard Time":
                            showID="(UTC+07:00) SE Asia Standard Time";
                            realID="Asia/Bangkok";
                            tz = TimeZone.getTimeZone("Asia/Bangkok");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+07:00) Altai Standard Time":
                            showID="(UTC+07:00) Altai Standard Time";
                            realID="Asia/Barnaul";
                            tz = TimeZone.getTimeZone("Asia/Barnaul");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+07:00) W. Mongolia Standard Time":
                            showID="(UTC+07:00) W. Mongolia Standard Time";
                            realID="Asia/Hovd";
                            tz = TimeZone.getTimeZone("Asia/Hovd");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+07:00) North Asia Standard Time":
                            showID="(UTC+07:00) North Asia Standard Time";
                            realID="Asia/Krasnoyarsk";
                            tz = TimeZone.getTimeZone("Asia/Krasnoyarsk");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+07:00) N. Central Asia Standard Time":
                            showID="(UTC+07:00) N. Central Asia Standard Time";
                            realID="Asia/Novosibirsk";
                            tz = TimeZone.getTimeZone("Asia/Novosibirsk");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+07:00) Tomsk Standard Time":
                            showID="(UTC+07:00) Tomsk Standard Time";
                            realID="Asia/Tomsk";
                            tz = TimeZone.getTimeZone("Asia/Tomsk");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+08:00) China Standard Time":
                            showID="(UTC+08:00) China Standard Time";
                            realID="Asia/Shanghai";
                            tz = TimeZone.getTimeZone("Asia/Shanghai");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+08:00) North Asia East Standard Time":
                            showID="(UTC+08:00) North Asia East Standard Time";
                            realID="Asia/Irkutsk";
                            tz = TimeZone.getTimeZone("Asia/Irkutsk");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+08:00) Singapore Standard Time":
                            showID="(UTC+08:00) Singapore Standard Time";
                            realID="Asia/Singapore";
                            tz = TimeZone.getTimeZone("Asia/Singapore");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+08:00) W. Australia Standard Time":
                            showID="(UTC+08:00) W. Australia Standard Time";
                            realID="Australia/Perth";
                            tz = TimeZone.getTimeZone("Australia/Perth");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+08:00) Taipei Standard Time":
                            showID="(UTC+08:00) Taipei Standard Time";
                            realID="Asia/Taipei";
                            tz = TimeZone.getTimeZone("Asia/Taipei");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+08:00) Ulaanbaatar Standard Time":
                            showID="(UTC+08:00) Ulaanbaatar Standard Time";
                            realID="Asia/Choibalsan";
                            tz = TimeZone.getTimeZone("Asia/Choibalsan");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+08:30) North Korea Standard Time":
                            showID="(UTC+08:30) North Korea Standard Time";
                            realID="Asia/Pyongyang";
                            tz = TimeZone.getTimeZone("Asia/Pyongyang");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+08:45) Aus Central W. Standard Time":
                            showID="(UTC+08:45) Aus Central W. Standard Time";
                            realID="Australia/Eucla";
                            tz = TimeZone.getTimeZone("Australia/Eucla");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+09:00) Transbaikal Standard Time":
                            showID="(UTC+09:00) Transbaikal Standard Time";
                            realID="Asia/Chita";
                            tz = TimeZone.getTimeZone("Asia/Chita");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+09:00) Tokyo Standard Time":
                            showID="(UTC+09:00) Tokyo Standard Time";
                            realID="Asia/Tokyo";
                            tz = TimeZone.getTimeZone("Asia/Tokyo");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+09:00) Korea Standard Time":
                            showID="(UTC+09:00) Korea Standard Time";
                            realID="Asia/Seoul";
                            tz = TimeZone.getTimeZone("Asia/Seoul");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+09:00) Yakutsk Standard Time":
                            showID="(UTC+09:00) Yakutsk Standard Time";
                            realID="Asia/Yakutsk";
                            tz = TimeZone.getTimeZone("Asia/Yakutsk");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+09:30) Cen. Australia Standard Time":
                            showID="(UTC+09:30) Cen. Australia Standard Time";
                            realID="Australia/Adelaide";
                            tz = TimeZone.getTimeZone("Australia/Adelaide");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+09:30) AUS Central Standard Time":
                            showID="(UTC+09:30) AUS Central Standard Time";
                            realID="Australia/Darwin";
                            tz = TimeZone.getTimeZone("Australia/Darwin");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+10:00) E. Australia Standard Time":
                            showID="(UTC+10:00) E. Australia Standard Time";
                            realID="Australia/Brisbane";
                            tz = TimeZone.getTimeZone("Australia/Brisbane");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;

                        //garbar

                        case "(UTC+10:00) AUS Eastern Standard Time":
                            showID="(UTC+10:00) AUS Eastern Standard Time";
                            realID="Australia/Sydney";
                            tz = TimeZone.getTimeZone("Australia/Sydney");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+10:00) West Pacific Standard Time":
                            showID="(UTC+10:00) West Pacific Standard Time";
                            realID="Pacific/Port_Moresby";
                            tz = TimeZone.getTimeZone("Pacific/Port_Moresby");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+10:00) Tasmania Standard Time":
                            showID="(UTC+10:00) Tasmania Standard Time";
                            realID="Australia/Hobart";
                            tz = TimeZone.getTimeZone("Australia/Hobart");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+10:00) Vladivostok Standard Time":
                            showID="(UTC+10:00) Vladivostok Standard Time";
                            realID="Asia/Vladivostok";
                            tz = TimeZone.getTimeZone("Asia/Vladivostok");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+10:30) Lord Howe Standard Time":
                            showID="(UTC+10:30) Lord Howe Standard Time";
                            realID="Australia/Lord_Howe";
                            tz = TimeZone.getTimeZone("Australia/Lord_Howe");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+11:00) Bougainville Standard Time":
                            showID="(UTC+11:00) Bougainville Standard Time";
                            realID="Pacific/Bougainville";
                            tz = TimeZone.getTimeZone("Pacific/Bougainville");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+11:00) Russia Time Zone 10":
                            showID="(UTC+11:00) Russia Time Zone 10";
                            realID="Asia/Srednekolymsk";
                            tz = TimeZone.getTimeZone("Asia/Srednekolymsk");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+11:00) Magadan Standard Time":
                            showID="(UTC+11:00) Magadan Standard Time";
                            realID="Asia/Magadan";

                            tz = TimeZone.getTimeZone("Asia/Magadan");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+11:00) Norfolk Standard Time":
                            showID="(UTC+11:00) Norfolk Standard Time";
                            realID="Pacific/Norfolk";
                            tz = TimeZone.getTimeZone("Pacific/Norfolk");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+11:00) Sakhalin Standard Time":
                            showID="(UTC+11:00) Sakhalin Standard Time";
                            realID="Asia/Sakhalin";
                            tz = TimeZone.getTimeZone("Asia/Sakhalin");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+11:00) Central Pacific Standard Time":
                            showID="(UTC+11:00) Central Pacific Standard Time";
                            realID="Pacific/Guadalcanal";
                            tz = TimeZone.getTimeZone("Pacific/Guadalcanal");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+12:00) Russia Time Zone 11":
                            showID="(UTC+12:00) Russia Time Zone 11";
                            realID="Asia/Kamchatka";
                            tz = TimeZone.getTimeZone("Asia/Kamchatka");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+12:00) New Zealand Standard Time":
                            showID="(UTC+12:00) New Zealand Standard Time";
                            realID="Pacific/Auckland";
                            tz = TimeZone.getTimeZone("Pacific/Auckland");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+12:00) Coordinated Universal Time+12":
                            showID="(UTC+12:00) Coordinated Universal Time+12";
                            realID="Pacific/Tarawa";
                            tz = TimeZone.getTimeZone("Pacific/Tarawa");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+12:00) Fiji Standard Time":
                            showID="(UTC+12:00) Fiji Standard Time";
                            realID="Pacific/Fiji";
                            tz = TimeZone.getTimeZone("Pacific/Fiji");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+12:45) Chatham Islands Standard Time":
                            showID="(UTC+12:45) Chatham Islands Standard Time";
                            realID="Pacific/Chatham";
                            tz = TimeZone.getTimeZone("Pacific/Chatham");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+13:00) Tonga Standard Time":
                            showID="(UTC+13:00) Tonga Standard Time";

                            realID="Pacific/Tongatapu";
                            tz = TimeZone.getTimeZone("Pacific/Tongatapu");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+13:00) Samoa Standard Time":
                            showID="(UTC+13:00) Samoa Standard Time";
                            realID="Pacific/Apia";
                            tz = TimeZone.getTimeZone("Pacific/Apia");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                        case "(UTC+14:00) Line Islands Standard Time":
                            showID="(UTC+14:00) Line Islands Standard Time";
                            realID="Pacific/Kiritimati";
                            tz = TimeZone.getTimeZone("Pacific/Kiritimati");
                            sourceFormat.setTimeZone(tz);
                            store = sourceFormat.format(date);
                            Toast.makeText(MainActivity.this, store, Toast.LENGTH_SHORT).show();
                            break;


                }

                Intent intent = new Intent(MainActivity.this, ThreadRunning.class);
                Intent intent1 = new Intent(MainActivity.this, ThreadRunning1.class);
                Intent intent2 = new Intent(MainActivity.this, ThreadRunning2.class);

                Bundle mainActiBundle=getIntent().getExtras();

                if(mainActiBundle!=null){
                    notifyID=mainActiBundle.getInt("notifyID");
                    intent.putExtra("notifyID",notifyID);
                }

                //notifyID=1;
                if(notifyID==1 || notifyID==0){
                    notifyID=1;
                    intent.putExtra("ID",realID);
                    intent.putExtra("ShowID",showID);
                    intent.putExtra("notifyID",notifyID);
                    startActivity(intent);
                }
               else if(notifyID==2){


                    intent1.putExtra("ID1",realID);
                    intent1.putExtra("ShowID1",showID);
                    intent1.putExtra("notifyID1",notifyID);
                    startActivity(intent1);
                }
                else if(notifyID==3){
                    intent2.putExtra("ID2",realID);
                    intent2.putExtra("ShowID2",showID);
                    intent2.putExtra("notifyID2",notifyID);
                    startActivity(intent2);
                }
            }
        });

    }

}
