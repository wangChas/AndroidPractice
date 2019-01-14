package chas.fuzzysearchedit;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by chas on 2019/1/12.
 */

public class RvAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RvAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_search_tv, item)
        .addOnClickListener(R.id.item_search_tv);
    }
}
