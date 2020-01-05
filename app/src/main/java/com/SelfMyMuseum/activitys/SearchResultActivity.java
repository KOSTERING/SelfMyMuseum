package com.SelfMyMuseum.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.SelfMyMuseum.R;
import com.SelfMyMuseum.adapter.SearchResultAdapter;
import com.SelfMyMuseum.classes.Product;

import java.util.ArrayList;
import java.util.List;


public class SearchResultActivity extends AppCompatActivity {
    // 搜索的类型 ：exhibit: 搜索展览 Product: 搜索展品 type :点击种类进去显示的页面
    private String searchType;

    private RecyclerView searchRecyclerView;

    private SearchResultAdapter searchResultAdapter;

    private String adapterString;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        //     Log.d("produtdetails","produtdetails");
        Toolbar toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        toolbar.setTitle("搜索结果");
        toolbar.setNavigationIcon(R.mipmap.back);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        intent = getIntent();
        searchType = intent.getStringExtra("searchType");

        initData(searchType);



    }

    private void initData(String searchType) {
        Product product =new Product
                ("道光帝绘山水图折扇"
                        ,R.mipmap.dgs,"清道光"
                        ,"纵29.1cm,横48cm",
                        "扇面黑，泥银行书诗句",
                        "扇子");
        List<Product> lists = new ArrayList<>();
        lists.add(product);
        lists.add(product);
        switch (searchType) {
            case "type":
                String typeName = intent.getStringExtra("searchString");
                searchType(typeName,lists);
                break;

            case "Product":
                String productTitle = intent.getStringExtra("searchString");
                searchProduct(productTitle,lists);
                break;
            case "exhibit":
                String exhibitTitle = intent.getStringExtra("searchString");
                searchExhibit(exhibitTitle);
                break;

        }
    }

    // 每种类型下的展品
    private void searchType(String searchString,List<Product> list) {
        List<Product> searchResults = new ArrayList<>();

        for (Product product : list) {
            String temp = product.getSearchtype().toString();

            if (temp.equals(searchString)) {
                searchResults.add(product);
            }
        }
        adapterString = "Product";
        loadData(searchResults);


    }

    private void searchProduct(String searchString,List<Product> list) {
        List<Product> searchResults = new ArrayList<>();

        for (Product product : list) {
            String temp = product.getTitle().toString();

            if (temp.contains(searchString)) {
                searchResults.add(product);
            }
        }
        adapterString = "Product";
        loadData(searchResults);
    }

    private void searchExhibit(String searchString) {
        /*List<AVObject> lists = new ArrayList<>();
        AVQuery<AVObject> query = new AVQuery<>("Exhibit");

        query.whereContains("title", searchString);

        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                adapterString = "exhibit";
                loadData(list);
            }
        });*/
    }

    private void loadData(List<Product> list) {
        searchRecyclerView = (RecyclerView)findViewById(R.id.search_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        searchRecyclerView.setLayoutManager(layoutManager);

        intent.putExtra("searchType",searchType);
        searchResultAdapter  = new SearchResultAdapter(list,adapterString);
        searchRecyclerView.setAdapter(searchResultAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //AVAnalytics.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //AVAnalytics.onResume(this);
    }
}


