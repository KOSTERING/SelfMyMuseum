package com.SelfMyMuseum.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.SelfMyMuseum.BannerView;
import com.SelfMyMuseum.R;
import com.SelfMyMuseum.adapter.ExhibitListAdapter;
import com.SelfMyMuseum.classes.Exhibitshort;

import java.util.ArrayList;
import java.util.List;

/*import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;*/

//import leancloud.zry.mymuseum.BannerView;



/**
 * Created by Reene on 2017/5/15.
 */

public class FirstFragment extends android.app.Fragment {
    private String title;

    private TextView textView;

    // 展览的信息
    private List<Exhibitshort> exhibits;

    private Context context;

    private SwipeRefreshLayout swipeRefreshLayout;

    private ExhibitListAdapter exhibitListAdapter;

    private EditText searchEditText;


    public FirstFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.exhibit_lists,container,false);

        searchEditText = (EditText)view.findViewById(R.id.search_exhibit);

        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {//EditorInfo.IME_ACTION_SEARCH、EditorInfo.IME_ACTION_SEND等分别对应EditText的imeOptions属性
                    String searchText = searchEditText.getText().toString();
                    search(searchText,view);
                }
                return false;
            }
        });



        context = view.getContext();

        exhibits = new ArrayList<>();
  //      exhibits = getData.getList();
        /*
        if (exhibits.size()>0) {
            Log.d("getdata",">0");
        } else {
            Log.d("getdata","<0");
        }*/

  //      showRecyclerView(view,exhibits);
        initData(view);
        return view;
    }

    private void initData(final View view) {
        exhibits.clear();
        /*AVQuery<AVObject> avQuery = new AVQuery<>("Exhibit");
        avQuery.orderByDescending("createdAt");
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    exhibits.addAll(list);
                    showData(exhibits);
                    showRecyclerView(view,exhibits);
                    BannerView bannerView = new BannerView(context,view,exhibits);
                } else {
                    e.printStackTrace();
                }
            }
        });*/

        for(int i=0;i<2;i++){
            Exhibitshort draw=new Exhibitshort("菊香晚艳",R.mipmap.ehibit4,"菊花题材文物联展","2017-6-1","2017-6-10");
            exhibits.add(draw);
            Exhibitshort butterfly=new Exhibitshort("四僧书画展",R.mipmap.siseng,"清末四僧书画展，走过路过不要错过啦","2017-5-1","2017-6-10");
            exhibits.add(butterfly);
            Exhibitshort hui=new Exhibitshort("徽匠神韵",R.mipmap.hui,"安徽徽州传统工艺展","2017-5-1","2017-6-10");
            exhibits.add(hui);
        }
        showRecyclerView(view,exhibits);
        BannerView bannerView = new BannerView(context,view,exhibits);

    }

    private void showRecyclerView(final View view, List<Exhibitshort> list) {

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(layoutManager);
        exhibitListAdapter = new ExhibitListAdapter(list);
        recyclerView.setAdapter(exhibitListAdapter);

        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                refreshFruits(view);
            }
        });
    }

    private void refreshFruits(final View view) {

        final AppCompatActivity appCompatActivity = (AppCompatActivity)getActivity();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                appCompatActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
              //          initCardView(view1);
                        initData(view);
                        exhibitListAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
        Log.d("TestFragment","refresh");
    }


    private void showData(List<Exhibitshort> list) {
        for (int i = 0; i < list.size(); i++ ){
   //         Log.d("exhibitcount", "qq");
            String temp = (list.get(i)).getTitle().toString();
            Log.d("exhibits", temp);
        }
    }

    // 查询标题包含输入字段的展览
    private void search(String searchText, View view) {
        Toast.makeText(context, searchText, Toast.LENGTH_SHORT).show();
        List<Exhibitshort> searchResults = new ArrayList<>();
        for (Exhibitshort product : exhibits) {
            String temp = product.getTitle().toString();

            if (temp.contains(searchText)) {
                searchResults.add(product);
            }
        }

        // 更新数据
        showRecyclerView(view,searchResults);
    }
}
