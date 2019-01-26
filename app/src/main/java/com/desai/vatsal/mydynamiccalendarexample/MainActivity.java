package com.desai.vatsal.mydynamiccalendarexample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    Button pre, next;
    TextView date;
    private Toolbar toolbar;
    private ArrayList<DateModel> dateModelList;
    private ArrayList<DateModelE> mylList;
    private ArrayList<DateModelE> mon_act, tus_act, wen_act, thr_act, fri_act, sat_act, sun_act;
    private ArrayList<DateModelE> current_week_ac;
    private DateListAdapterE myadapter;
    ArrayList<Calendar> bb = new ArrayList<>();
    String name;
    boolean flag = false;
    int image;
    RecyclerView mon_recy, tus_recy, wen_recy, thr_recy_, fri_recy, sat_recy, sun_recy;
    private RecyclerView.LayoutManager mLayoutManager;
    private SimpleDateFormat sdfWeekDay = new SimpleDateFormat("dd MMM");
    private Calendar calendar = Calendar.getInstance();
    Context context;
    ArrayList<Integer> weedays;
    ArrayList<String> dateStrings;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findItemsById();
        showWeekView();
        checkForActivites();

    }

    private void findItemsById() {
        pre = (Button) findViewById(R.id.pre);
        next = (Button) findViewById(R.id.next);
        date = (TextView) findViewById(R.id.date);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setWeekView("");
        setSupportActionBar(toolbar);
        mon_recy = (RecyclerView) findViewById(R.id.mon_recy);
        tus_recy = (RecyclerView) findViewById(R.id.tus_recy);
        wen_recy = (RecyclerView) findViewById(R.id.wen_recy);
        thr_recy_ = (RecyclerView) findViewById(R.id.thr_recy);
        fri_recy = (RecyclerView) findViewById(R.id.fri_recy);
        sat_recy = (RecyclerView) findViewById(R.id.sat_recy);
        sun_recy = (RecyclerView) findViewById(R.id.sun_recy);

    }


    public void showWeekView() {
        setWeekView("");
    }


    private void setWeekView(String sign) {
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setWeekView("sub");
                checkForActivites();

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initArrays();
                setWeekView("add");
                checkForActivites();

            }
        });


        //**************************


        dateModelList = new ArrayList<>();


        if (sign.equals("add")) {

            //display the next 7 days
            calendar.set(Calendar.DAY_OF_MONTH, (calendar.get(Calendar.DAY_OF_MONTH) + 7));


        } else if (sign.equals("sub")) {
            calendar.set(Calendar.DAY_OF_MONTH, (calendar.get(Calendar.DAY_OF_MONTH) - 7));
            //display the previous 7 days

        }


        String weekStartDay = sdfWeekDay.format(calendar.getTime());
        dateModelList.clear();
        while (dateModelList.size() < 7) {
            DateModel model = new DateModel();
            model.setDates(calendar.getTime());
            model.setFlag("week");
            dateModelList.add(model);
            calendar.add(Calendar.DAY_OF_MONTH, 1);

        }


        //to set the first Day of week
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        String weekEndDay = sdfWeekDay.format(calendar.getTime());
        date.setText(String.format("%s - %s", weekStartDay, weekEndDay));
        calendar.add(Calendar.DAY_OF_MONTH, -6);


    }

    private void checkForActivites() {

        DateFormat df = new SimpleDateFormat("EEE dd/MM/yyyy");


        initArrays();
        fullList();

        for (int i = 0; i < dateModelList.size(); i++) {
            for (int j = 0; j < mylList.size(); j++) {
                myadapter = new DateListAdapterE(this, mylList);
                Log.d("*", "*" + String.valueOf(df.format(dateModelList.get(i).getDates())) + " " + mylList.get(j).getDate());
                if (String.valueOf(df.format(dateModelList.get(i).getDates())).equals(mylList.get(j).getDate())) {
                    Toast.makeText(this, "Yes", Toast.LENGTH_SHORT).show();
                    String currentString = mylList.get(j).getDate();
                    String[] separated = currentString.split(" ");
                    key = separated[0];
                    switch (key) {
                        case "Mon":

                            mon_act.add(mylList.get(j));
                            mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                            mon_recy.setLayoutManager(mLayoutManager);
                            myadapter = new DateListAdapterE(context, mon_act);
                            mon_recy.setAdapter(myadapter);

                            break;


                        case "Tue":
                            tus_act.add(mylList.get(j));
                            myadapter = new DateListAdapterE(context, tus_act);
                            mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                            tus_recy.setLayoutManager(mLayoutManager);
                            tus_recy.setAdapter(myadapter);

                            break;
                        case "Wed":
                            wen_act.add(mylList.get(j));
                            myadapter = new DateListAdapterE(context, wen_act);
                            mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                            wen_recy.setLayoutManager(mLayoutManager);
                            wen_recy.setAdapter(myadapter);

                            break;
                        case "Thu":
                            thr_act.add(mylList.get(j));
                            myadapter = new DateListAdapterE(context, thr_act);
                            mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                            thr_recy_.setLayoutManager(mLayoutManager);
                            thr_recy_.setAdapter(myadapter);

                            break;
                        case "Fri":
                            fri_act.add(mylList.get(j));
                            myadapter = new DateListAdapterE(this, fri_act);
                            mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                            fri_recy.setLayoutManager(mLayoutManager);
                            fri_recy.setAdapter(myadapter);


                            break;
                        case "Sat":
                            sat_act.add(mylList.get(j));
                            myadapter = new DateListAdapterE(this, sat_act);
                            mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                            sat_recy.setLayoutManager(mLayoutManager);
                            sat_recy.setAdapter(myadapter);

                            break;
                        case "Sun":
                            sun_act.add(mylList.get(j));
                            myadapter = new DateListAdapterE(this, sun_act);
                            mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                            sun_recy.setLayoutManager(mLayoutManager);
                            sun_recy.setAdapter(myadapter);

                            break;

                    }
                    myadapter.notifyDataSetChanged();

                } else {


                }

            }
            Log.d("nw", "* " + String.valueOf(df.format(dateModelList.get(i).getDates())));

        }

    }


    private void initArrays() {
        mon_act = new ArrayList<>();
        tus_act = new ArrayList<>();
        wen_act = new ArrayList<>();
        thr_act = new ArrayList<>();
        fri_act = new ArrayList<>();
        sat_act = new ArrayList<>();
        sun_act = new ArrayList<>();
        mylList = new ArrayList<>();
        myadapter = new DateListAdapterE(this, fri_act);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        fri_recy.setLayoutManager(mLayoutManager);
        fri_recy.setAdapter(myadapter);

        myadapter = new DateListAdapterE(this, mon_act);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mon_recy.setLayoutManager(mLayoutManager);
        mon_recy.setAdapter(myadapter);

        myadapter = new DateListAdapterE(this, tus_act);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        tus_recy.setLayoutManager(mLayoutManager);
        tus_recy.setAdapter(myadapter);

        myadapter = new DateListAdapterE(this, thr_act);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        thr_recy_.setLayoutManager(mLayoutManager);
        thr_recy_.setAdapter(myadapter);

        myadapter = new DateListAdapterE(this, sat_act);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        sat_recy.setLayoutManager(mLayoutManager);
        sat_recy.setAdapter(myadapter);

        myadapter = new DateListAdapterE(this, sun_act);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        sun_recy.setLayoutManager(mLayoutManager);
        sun_recy.setAdapter(myadapter);

        myadapter = new DateListAdapterE(this, wen_act);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        wen_recy.setLayoutManager(mLayoutManager);
        wen_recy.setAdapter(myadapter);
    }


    private void fullList() {

        DateModelE dateModelE = new DateModelE("E1", "run", "Tue 22/01/2019", R.mipmap.run);
        mylList.add(dateModelE);
        DateModelE dateModel5 = new DateModelE("E1", "run", "Wed 23/01/2019", R.mipmap.run);
        mylList.add(dateModel5);
        DateModelE dateModel3 = new DateModelE("E1", "row", "Tue 22/01/2019", R.mipmap.row);
        mylList.add(dateModel3);

        DateModelE dateModelE6 = new DateModelE("E1", "run", "Sat 19/01/2019", R.mipmap.run);
        mylList.add(dateModelE6);
        DateModelE dateModelE7 = new DateModelE("E1", "run", "Sun 20/01/2019", R.mipmap.run);
        mylList.add(dateModelE7);
        DateModelE dateModel8 = new DateModelE("E1", "run", "Sun 13/01/2019", R.mipmap.run);
        mylList.add(dateModel8);

        DateModelE mon11 = new DateModelE("E1", "run", "Mon 14/01/2019", R.mipmap.run);
        mylList.add(mon11);
        DateModelE mon12 = new DateModelE("E1", "run", "Mon 28/01/2019", R.mipmap.run);
        mylList.add(mon12);
        DateModelE dateModelE2 = new DateModelE("E2", "run", "Mon 21/01/2019", R.mipmap.run);
        mylList.add(dateModelE2);
        DateModelE dateModelE4 = new DateModelE("E4", "row", "Mon 21/01/2019", R.mipmap.row);
        mylList.add(dateModelE4);
        DateModelE dateModelE11 = new DateModelE("E4", "row", "Fri 25/01/2019", R.mipmap.row);
        mylList.add(dateModelE11);

        DateModelE d2 = new DateModelE("E2", "run", "Fri 01/02/2019", R.mipmap.run);
        mylList.add(d2);
        DateModelE d3 = new DateModelE("E4", "row", "Tue 01/01/2019", R.mipmap.row);
        mylList.add(d3);
        DateModelE d4= new DateModelE("E4", "row", "Wed 09/01/2019", R.mipmap.row);
        mylList.add(d4);
//        DateModelE dateModel10 = new DateModelE("E1", "run", "Tue 29/01/2019", R.mipmap.run);
//        mylList.add(dateModel10);
//        DateModelE dateModel9 = new DateModelE("E1", "run", "Mon 21/01/2019", R.mipmap.run);
//        mylList.add(dateModel9);
//        DateModelE dateModelE8 = new DateModelE("E1", "run", "Thu 31/01/2019", R.mipmap.run);
//        mylList.add(dateModelE8);

    }


}
