package com.SelfMyMuseum.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.SelfMyMuseum.R;
import com.SelfMyMuseum.activitys.SearchResultActivity;
import com.SelfMyMuseum.adapter.TypeListAdapter;
import com.SelfMyMuseum.classes.Type;

import java.util.ArrayList;
import java.util.List;

;

/**
 * Created by Reene on 2017/5/19.
 */

public class SecondFragment extends android.app.Fragment{

    // 展览的信息
    private List<Type> types;

    private SwipeRefreshLayout swipeRefreshLayout;

    private TypeListAdapter typesListAdapter;

    private Context context;

    private EditText searchEditText;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment,container,false);

        searchEditText = (EditText)view.findViewById(R.id.search_product);
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //EditorInfo.IME_ACTION_SEARCH、EditorInfo.IME_ACTION_SEND等分别对应EditText的imeOptions属性
                    String searchText = searchEditText.getText().toString();
                    search(searchText);
                }
                return false;
            }
        });
        types = new ArrayList<>();
        initData(view);
        context = view.getContext();
        return view;
    }

    private void initData(final View view) {
        types.clear();
        /*AVQuery<AVObject> avQuery = new AVQuery<>("ProductType");

        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    types.addAll(list);
           //         showData(types);
                    showRecyclerView(view,types);
                } else {
                    e.printStackTrace();
                }
            }
        });*/

        Type fan=new Type("扇子",R.mipmap.s3);
        types.add(fan);
        Type writing=new Type("文房四宝",R.mipmap.t5);
        types.add(writing);
        Type draw=new Type("书画",R.mipmap.draw);
        types.add(draw);
        Type ciqi=new Type("瓷器",R.mipmap.ciqi);
        types.add(ciqi);
        Type qingtong=new Type("青铜器",R.mipmap.qingtong);
        types.add(qingtong);

        showRecyclerView(view,types);


    }

    private void showRecyclerView(final View view, List<Type> list) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.type_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
        recyclerView.setLayoutManager(layoutManager);
        typesListAdapter = new TypeListAdapter(list);
        recyclerView.setAdapter(typesListAdapter);

        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.type_swipe_refresh);
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
        //    final View view1 = view;

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
                        typesListAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    // 查询标题包含输入字段的展览
    private void search(String searchText) {
        // 交给SearchResult类处理
        String searchType = "Product";
        String searchString = searchText;

        Intent intent = new Intent(context,SearchResultActivity.class);
        intent.putExtra("searchType",searchType);
        intent.putExtra("searchString",searchString);
        context.startActivity(intent);
    }

}
