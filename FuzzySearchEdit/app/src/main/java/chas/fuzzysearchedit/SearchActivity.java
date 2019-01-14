package chas.fuzzysearchedit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private List<String> mList;
    private RvAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private EditText mEditText;
    private Button mButton;
    private List<String> mSearchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setList();
        getResultData("");
        initView();
        Log.i("SearchActivity", mList.get(0));
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.search_rv);
        mEditText = (EditText) findViewById(R.id.search_et);
        mButton = (Button) findViewById(R.id.search_btn);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.item_search_tv) {
                    Intent intent = new Intent();
                    intent.putExtra("result", mSearchList.get(position));
                    setResult(1001, intent);
                    finish();
                    Toast.makeText(SearchActivity.this, mSearchList.get(position), Toast.LENGTH_SHORT).show();
                }
            }
        });

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
              }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getResultData(String.valueOf(s));
//                Toast.makeText(SearchActivity.this, "start,"+ start + ";count, "+ count + ";before,"+ before+ String.valueOf(s), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setList() {
        if (mList == null) {
            mList = new ArrayList<>();
        }
        for (int i = 0; i < 100; i++) {
            mList.add("测试"+i+",null");

        }
    }

    private void getResultData(String text) {
        if (mSearchList == null) {
            // 初始化
            mSearchList = new ArrayList<>();
            mSearchList.addAll(mList);
        } else {
            mSearchList.clear();
            for (int i = 0; i < mList.size(); i++) {
                if (mList.get(i).contains(text.trim())) {
                    mSearchList.add(mList.get(i));
                }
            }
        }
        if (mAdapter == null) {
            mAdapter = new RvAdapter(R.layout.item_search, mSearchList);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

}

