package com.shide56.kitchenstories.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.shide56.kitchenstories.BaseFragment;
import com.shide56.kitchenstories.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 主页
 */

public class HomeFragment extends BaseFragment {


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
//        BaseQuickAdapter
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view != null) {
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
            Adapter adapter = new Adapter();
            recyclerView.setAdapter(adapter);

            String[] categories = {
                    "今日故事",
                    "新食谱",
                    "简单快捷",
                    "本周晚餐",
                    "自制风味蛋糕",
                    "新意新味",
                    "最新文章",
                    "亚洲美食攻略",
                    "大家都在做......",
                    "食谱速览"
            };
            if (Collections.addAll(adapter.items, categories)) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    private final class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
        final List<String> items = new ArrayList<>();

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View itemView = inflater.inflate(R.layout.item_fragment_home, parent, false);
            return new Holder(itemView);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            String category = items.get(position);
            holder.tv_category.setText(category);
            holder.recycler_view.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            ChildAdapter childAdapter = new ChildAdapter();
            holder.recycler_view.setAdapter(childAdapter);
            for (int i = 0; i < 7; i++) {
                childAdapter.items.add("i" + i);
            }
            childAdapter.notifyDataSetChanged();
            holder.recycler_view.smoothScrollToPosition(1);
            holder.radio_group.removeAllViews();
            for (int i = position; i > 0; i--) {
                RadioButton radioButton = new RadioButton(getContext());
                holder.radio_group.addView(radioButton);
                radioButton.setChecked(true);
            }
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        final class Holder extends RecyclerView.ViewHolder {

            private final TextView tv_category;
            private final RecyclerView recycler_view;
            private final RadioGroup radio_group;
            GestureDetector detector = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
                int currentPosition = 0;

                @Override
                public boolean onDown(MotionEvent e) {
                    return false;
                }

                @Override
                public void onShowPress(MotionEvent e) {

                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return false;
                }

                @Override
                public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                    return false;
                }

                @Override
                public void onLongPress(MotionEvent e) {

                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    Log.i("phc", "onFling");
                    if (e1.getX() - e2.getX() > 0 && currentPosition < recycler_view.getChildCount()) {
                        currentPosition++;
                        // 手向左滑动，图片下一张
                    } else if (e2.getX() - e1.getX() > 0 && currentPosition > 0) {
                        // 向右滑动，图片上一张
                        currentPosition--;

                    }
                    recycler_view.smoothScrollToPosition(currentPosition);
                    return false;
                }
            });

            Holder(View itemView) {
                super(itemView);
                tv_category = (TextView) itemView.findViewById(R.id.tv_category);
                recycler_view = (RecyclerView) itemView.findViewById(R.id.recycler_view);
                radio_group = (RadioGroup) itemView.findViewById(R.id.radio_group);

                recycler_view.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return detector.onTouchEvent(event);
                    }
                });
            }
        }
    }

    private final class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.Holder> {
        final List<String> items = new ArrayList<>();

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View itemView = inflater.inflate(R.layout.item_fragment_home_child, parent, false);
            return new Holder(itemView);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            String category = items.get(position);
//            holder.tv_category.setText(category);
            holder.iv_picture.setImageResource(R.mipmap.ic_launcher_round);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        final class Holder extends RecyclerView.ViewHolder {

            //            private final TextView tv_category;
            private final ImageView iv_picture;

            Holder(View itemView) {
                super(itemView);
//                tv_category = (TextView) itemView.findViewById(R.id.tv_category);
                iv_picture = (ImageView) itemView.findViewById(R.id.iv_picture);
            }
        }
    }
}
