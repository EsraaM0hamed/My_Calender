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
    private ArrayList<DateModelE> mon_act, tus_act;
    private ArrayList<DateModelE> current_week_ac;
    private DateListAdapterE myadapter;
    private ArrayList<ShowEventsModelE> showEventsModelList;
    ArrayList<Calendar> bb = new ArrayList<>();
    String name;
    int image;
    RecyclerView mon_recy, tus_recy;
    private RecyclerView.LayoutManager mLayoutManager;
    private SimpleDateFormat sdfWeekDay = new SimpleDateFormat("dd MMM");
    private Calendar calendar = Calendar.getInstance();
    Context context;
    ArrayList<Integer> weedays;
    ArrayList<String> dateStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findItemsById();
        showWeekView();
        // initReci();

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

    }

    private void initReci() {
        tus_act = new ArrayList<>();

        weedays = new ArrayList<>();
        current_week_ac = new ArrayList<>();
        dateStrings = new ArrayList<>();
        mylList = new ArrayList<>();
        tus_act = new ArrayList<>();
        DateModelE dateModelE = new DateModelE("E1", "run", "Tus 23/01/2019", R.mipmap.row);
        mylList.add(dateModelE);
        DateModelE dateModelE2 = new DateModelE("E2", "run", "Mon 21/01/2019", R.mipmap.run);
        mylList.add(dateModelE2);
        DateModelE dateModelE4 = new DateModelE("E4", "run", "Mon 21/01/2019", R.mipmap.row);
        mylList.add(dateModelE4);
        DateModelE dateModelE3 = new DateModelE("E3", "row", "Tus 23/01/2019", R.mipmap.run);
        mylList.add(dateModelE3);
        DateModelE dateModelE5 = new DateModelE("E5", "row", "Tus 23/01/2019", R.mipmap.row);
        mylList.add(dateModelE5);
        getcurrerntdayString();
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        //for calender****************
        for (int k = 0; k < mylList.size(); k++) {

            System.out.println("dates " + mylList.get(k).getDate());
            if (mylList.get(k).getDate().contains("Mon")) {
                mon_act.add(mylList.get(k));
                myadapter = new DateListAdapterE(context, mon_act);
                mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                mon_recy.setLayoutManager(mLayoutManager);
                mon_recy.setAdapter(myadapter);
            } else if (mylList.get(k).getDate().contains("Tus")) {
                tus_act.add(mylList.get(k));
                myadapter = new DateListAdapterE(context, tus_act);
                mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                tus_recy.setLayoutManager(mLayoutManager);
                tus_recy.setAdapter(myadapter);

            }
        }


    }

    private ArrayList<String> getcurrerntdayString() {
        Calendar a = Calendar.getInstance();
        ArrayList<Calendar> b = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        DateFormat df = new SimpleDateFormat("EEE dd/MM/yyyy");
        for (int i = 0; i < 7; i++) {

            c.add(Calendar.DATE, 1);
            a.add(Calendar.DATE, i > 0 ? 1 : 0);
            b.add(a);
            a = (Calendar) a.clone();

        }

        for (int j = 0; j < b.size(); j++) {
            String dateString = String.valueOf(df.format(b.get(j).getTime()));
            dateStrings.add(dateString);
        }
        return dateStrings;
    }

    //****


    public void showWeekView() {
        setWeekView("");
    }


    private void setWeekView(String sign) {
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setWeekView("sub");


            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWeekView("add");
            }
        });


        //**************************


        dateModelList = new ArrayList<>();
        showEventsModelList = new ArrayList<>();


        if (sign.equals("add")) {

            //display the next 7 days
            calendar.set(Calendar.DAY_OF_MONTH, (calendar.get(Calendar.DAY_OF_MONTH) + 7));


        } else if (sign.equals("sub")) {
            calendar.set(Calendar.DAY_OF_MONTH, (calendar.get(Calendar.DAY_OF_MONTH) - 7));
            //display the previous 7 days

        }


        String weekStartDay = sdfWeekDay.format(calendar.getTime());
        Log.d("hi","*** "+weekStartDay);
        dateModelList.clear();
        DateFormat df = new SimpleDateFormat("EEE dd/MM/yyyy");
        while (dateModelList.size() < 7) {
            DateModel model = new DateModel();
            model.setDates(calendar.getTime());
            model.setFlag("week");
            dateModelList.add(model);
            calendar.add(Calendar.DAY_OF_MONTH, 1);

        }
        mon_act = new ArrayList<>();
        mylList = new ArrayList<>();
        DateModelE dateModelE = new DateModelE("E1", "run", "Wed 30/01/2019", R.mipmap.row);
        mylList.add(dateModelE);
        DateModelE dateModelE2 = new DateModelE("E2", "run", "Mon 21/01/2019", R.mipmap.run);
        mylList.add(dateModelE2);
        DateModelE dateModelE4 = new DateModelE("E4", "run", "Mon 21/01/2019", R.mipmap.row);
        mylList.add(dateModelE4);
        for (int i = 0; i < dateModelList.size(); i++) {
            for (int j = 0; j < mylList.size(); j++) {
                Log.d("*", "*" + String.valueOf(df.format(dateModelList.get(i).getDates())) + " " + mylList.get(j).getDate());
                if (String.valueOf(df.format(dateModelList.get(i).getDates())).equals(mylList.get(j).getDate())) {
                    Toast.makeText(this, "Yes", Toast.LENGTH_SHORT).show();
                    mon_act.add(mylList.get(j));
                    myadapter = new DateListAdapterE(context, mon_act);
                    mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                    mon_recy.setLayoutManager(mLayoutManager);
                    mon_recy.setAdapter(myadapter);
                    myadapter.notifyDataSetChanged();

                }
            }
            Log.d("nw", "* " + String.valueOf(df.format(dateModelList.get(i).getDates())));

        }

        calendar.add(Calendar.DAY_OF_MONTH, -1);

        String weekEndDay = sdfWeekDay.format(calendar.getTime());

        date.setText(String.format("%s - %s", weekStartDay, weekEndDay));

        calendar.add(Calendar.DAY_OF_MONTH, -6);


    }


}
