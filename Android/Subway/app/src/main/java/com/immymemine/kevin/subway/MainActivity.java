package com.immymemine.kevin.subway;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.immymemine.kevin.subway.adapter.ViewPagerAdapter;
import com.immymemine.kevin.subway.fragment.Subway_Fragment;
import com.immymemine.kevin.subway.model.station_info.Json_station;
import com.immymemine.kevin.subway.model.station_info.Row;
import com.immymemine.kevin.subway.model.station_info.SearchSTNBySubwayLineService;
import com.immymemine.kevin.subway.utility.NetworkUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements Subway_Fragment.OnListFragmentInteractionListener{

    EditText editText;
    TabLayout tabLayout;
    ViewPager viewPager;
    Button search_btn;
    public static List<Row> searchList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        load();
        setSearch();
    }
    private void setListener() {
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabLayout.setSelected(false);
                String s = editText.getText().toString();
                List<String> searchResult = new ArrayList<>();
                for(int i=0; i<data.getRow().length; i++) {
                    if( data.getRow()[i].getSTATION_NM().contains(s) ) {
                        searchResult.add(data.getRow()[i].getSTATION_NM());
                    }
                }
            }
        });
    }
    private void setSearch() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = editText.getText().toString().toLowerCase(Locale.getDefault());
                searchList.clear();
                for(int i=0; i<data.getRow().length; i++) {
                    if(data.getRow()[i].getSTATION_NM().contains(text)) {
                        searchList.add(data.getRow()[i]);
                    }
                }

                tabLayout.getTabAt(4).select();
                viewPager.setCurrentItem(4);
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = editText.getText().toString().toLowerCase(Locale.getDefault());
                searchList.clear();
                for(int i=0; i<data.getRow().length; i++) {
                    if(data.getRow()[i].getSTATION_NM().contains(text)) {
                        searchList.add(data.getRow()[i]);
                    }
                }

                tabLayout.getTabAt(4).select();
                viewPager.setCurrentItem(4);
            }
        });
    }
    private void initView() {
        editText = (EditText) findViewById(R.id.editText);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        search_btn = (Button) findViewById(R.id.search_btn);
    }

    private void setTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("1호선"));
        tabLayout.addTab(tabLayout.newTab().setText("2호선"));
        tabLayout.addTab(tabLayout.newTab().setText("3호선"));
        tabLayout.addTab(tabLayout.newTab().setText("4호선"));
        tabLayout.addTab(tabLayout.newTab().setText(""));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }
    ViewPagerAdapter adapter;
    private void setViewPager() {
        List<Fragment> list = new ArrayList<>();
        Fragment line_one = new Subway_Fragment().newInstance("1");
        Fragment line_two = new Subway_Fragment().newInstance("2");
        Fragment line_three = new Subway_Fragment().newInstance("3");
        Fragment line_four = new Subway_Fragment().newInstance("4");
        list.add(line_one); list.add(line_two); list.add(line_three); list.add(line_four);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    public static SearchSTNBySubwayLineService data = null;
    private void load() {
        new AsyncTask<Void, Void, String>() {
            String station = "";
            @Override
            protected void onPreExecute() {
                station = editText.getText().toString();
                if(station.endsWith("역")) {
                    station = station.substring(0, station.length()-1);
                }
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(Void... params) {
                return NetworkUtil.getStationData(NetworkUtil.SUBWAY_URL); // 모든 역의 정보가 담긴다
            }

            @Override
            protected void onPostExecute(String result) {
                Gson gson = new Gson();
                if(result == null) {
                    Log.e("null","exeption");
                } else {
                    Json_station json = gson.fromJson(result, Json_station.class);
                    data = json.getSearchSTNBySubwayLineService();
                    Log.d("onPostExecute","================완료");
                }
                setTabLayout();
                setViewPager();
            }
        }.execute();
    }

    @Override
    public void seeDetail(int position) {

    }
}
