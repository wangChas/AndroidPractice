package chas.fuzzysearchedit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private Button mBtnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.main_tv);
        mBtnSearch = (Button) findViewById(R.id.main_btn_search);
        mBtnSearch.setOnClickListener(mOnClickListener);
    }

    private OnClickListenerWrapper mOnClickListener = new OnClickListenerWrapper() {
        @Override
        protected void onSingleClick(View v) {
            switch (v.getId()) {
                case R.id.main_btn_search:
                    Intent i = new Intent(MainActivity.this, SearchActivity.class);
                    startActivityForResult(i, 1000);

                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000 && resultCode == 1001)
        {
            String result_value = data.getStringExtra("result");
            mTextView.setText(result_value);
        }
    }
}
