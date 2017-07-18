package com.shide56.kitchenstories.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shide56.kitchenstories.BaseFragment;
import com.shide56.kitchenstories.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 查找
 */

public class LookupFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lookup, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view != null) {
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
            Adapter adapter = new Adapter();
            recyclerView.setAdapter(adapter);

            String[] categories = {
                    "亚洲风味",
                    "低于400卡",
                    "低卡",
                    "复活节",
                    "好食懒做",
                    "工作日晚餐",
                    "快手甜品",
                    "快手菜",
                    "意面",
                    "披萨",
                    "早餐",
                    "春日食谱",
                    "晚餐派对",
                    "汤羹",
                    "沙拉",
                    "海鲜",
                    "烘培",
                    "爽心美食",
                    "牛肉",
                    "纯素",
                    "自带午餐",
                    "蛋糕",
                    "适合儿童",
                    "面包",
                    "饮品",
                    "鸡肉"
            };
            if (Collections.addAll(adapter.items, categories)) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    final class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
        final List<String> items = new ArrayList<>();

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View itemView = inflater.inflate(R.layout.item_fragment_lookup, parent, false);
            return new Holder(itemView);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            String category = items.get(position);
            holder.tv_category.setText(category);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        final class Holder extends RecyclerView.ViewHolder {

            private final ImageView iv_picture;
            private final TextView tv_category;

            Holder(View itemView) {
                super(itemView);
                iv_picture = (ImageView) itemView.findViewById(R.id.iv_picture);
                tv_category = (TextView) itemView.findViewById(R.id.tv_category);
            }
        }
    }
}
