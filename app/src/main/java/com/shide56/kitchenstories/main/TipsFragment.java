package com.shide56.kitchenstories.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shide56.kitchenstories.BaseFragment;
import com.shide56.kitchenstories.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 小贴士
 */

public class TipsFragment extends BaseFragment {


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tips, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view != null) {
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
            Adapter adapter = new Adapter();
            recyclerView.setAdapter(adapter);
//            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

            String[] tips = {"用刀技巧", "日常基础烹调法", "烘培方法", "自制"};
            if (Collections.addAll(adapter.items, tips)) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    final class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
        String[][] handles = {
                {
                        "如何处理抱子甘蓝",
                        "如何处理羽衣甘蓝",
                        "如何处理甜菜",
                        "如何处理姜黄",
                        "如何取下玉米粒",
                        "如何打开扇贝",
                        "如何切橄榄",
                        "如何处理奇异果"
                },
                {
                        "如何处理新鲜松露",
                        "丹麦肉丸",
                        "简易糖浆",
                        "如何制作碎冰",
                        "如何稀释锅底结块",
                        "给豆奶起泡到三种方法",
                        "炒饭",
                        "德士炒土豆"
                },
                {
                        "如何炮制生奶油",
                        "如何判断焦糖的硬度",
                        "如何揉面",
                        "两种方法做派皮",
                        "巧克力酱",
                        "折叠派皮和制作派格",
                        "自制红糖",
                        "完美漩涡布朗尼"
                },
                {
                        "如何制作棉花糖糖霜",
                        "自制棉花糖",
                        "土豆煎饼",
                        "自制土豆沙拉",
                        "怎样制作春卷皮",
                        "自制草莓果酱",
                        "蔓越莓开心果干",
                        "自制花椰菜饭"
                }
        };
        final List<String> items = new ArrayList<>();

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View itemView = inflater.inflate(R.layout.item_fragment_tips, parent, false);
            return new Holder(itemView);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            String tip = items.get(position);
            holder.tv_tip.setText(tip);
            ChildAdapter childAdapter = new ChildAdapter();
            holder.recycler_view.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            holder.recycler_view.setAdapter(childAdapter);

            String[] handle = handles[position];
            for (String aHandle : handle) {
                Bean bean = new Bean();
                bean.resId = R.mipmap.ic_launcher_round;
                bean.handle = aHandle;
                bean.time = new Date().getTime();
                bean.viewingQuantity = 24400;
                childAdapter.items.add(bean);
            }
            childAdapter.notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        final class Holder extends RecyclerView.ViewHolder {

            private final TextView tv_tip;
            private final RecyclerView recycler_view;

            Holder(View itemView) {
                super(itemView);
                tv_tip = (TextView) itemView.findViewById(R.id.tv_tip);
                recycler_view = (RecyclerView) itemView.findViewById(R.id.recycler_view);
            }
        }
    }

    static class Bean {
        int resId;
        String handle;
        long time;
        int viewingQuantity;
    }

    final class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.Holder> {
        final List<Bean> items = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");

        @Override
        public ChildAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View itemView = inflater.inflate(R.layout.item_fragment_tips_child, parent, false);
            return new Holder(itemView);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            Bean bean = items.get(position);
            holder.iv_picture.setImageResource(bean.resId);
            holder.tv_handle.setText(bean.handle);
            holder.tv_time.setText(format.format(new Date(bean.time)) + " 分钟");
            holder.tv_viewing_quantity.setText(" . " + bean.viewingQuantity + " 次观看");

        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        final class Holder extends RecyclerView.ViewHolder {

            private final ImageView iv_picture;
            private final TextView tv_handle;
            private final TextView tv_time;
            private final TextView tv_viewing_quantity;

            Holder(View itemView) {
                super(itemView);
                iv_picture = (ImageView) itemView.findViewById(R.id.iv_picture);
                tv_handle = (TextView) itemView.findViewById(R.id.tv_handle);
                tv_time = (TextView) itemView.findViewById(R.id.tv_time);
                tv_viewing_quantity = (TextView) itemView.findViewById(R.id.tv_viewing_quantity);
            }
        }
    }
}
