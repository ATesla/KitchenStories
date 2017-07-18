package com.shide56.kitchenstories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shide56.kitchenstories.main.HomeFragment;
import com.shide56.kitchenstories.main.LookupFragment;
import com.shide56.kitchenstories.main.MyHomeFragment;
import com.shide56.kitchenstories.main.ShoppingListFragment;
import com.shide56.kitchenstories.main.TipsFragment;

/**
 * Created by LCJ on 2017/4/14.
 */

public class MainFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view != null) {
            ViewPager no_flip_view_pager = (ViewPager) view.findViewById(R.id.no_flip_view_pager);
            TabLayout bottom_bar = (TabLayout) view.findViewById(R.id.bottom_bar);
            PagerAdapter pagerAdapter = new PagerAdapter(getChildFragmentManager());
            no_flip_view_pager.setAdapter(pagerAdapter);
            bottom_bar.setupWithViewPager(no_flip_view_pager);

            String[] names = {
                    HomeFragment.class.getName(),
                    TipsFragment.class.getName(),
                    LookupFragment.class.getName(),
                    ShoppingListFragment.class.getName(),
                    MyHomeFragment.class.getName()
            };
            SparseArray<Fragment> items = pagerAdapter.items;
            for (int i = 0; i < names.length; i++) {
                Bundle bundle = new Bundle();
                bundle.putInt("index", i);
                items.append(i, Fragment.instantiate(getContext(), names[i], bundle));
            }
            pagerAdapter.notifyDataSetChanged();

            String[] titles = {"主页", "小贴士", "查找", "购物单", "我的主页"};
            for (int i = 0; i < bottom_bar.getTabCount(); i++) {
                TabLayout.Tab tab = bottom_bar.getTabAt(i);
                if (tab != null) {
                    tab.setText(titles[i]);
                }
            }
        }
    }

    private final class PagerAdapter extends FragmentPagerAdapter {
        final SparseArray<Fragment> items = new SparseArray<>();

        PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }
}
