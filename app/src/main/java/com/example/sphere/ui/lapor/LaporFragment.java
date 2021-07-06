package com.example.sphere.ui.lapor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.sphere.R;
import com.shuhart.stepview.StepView;

public class LaporFragment extends Fragment {

    private LaporViewModel dashboardViewModel;
    StepView stepView;
    ViewPager2 viewPager2;

    private int curStep = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(LaporViewModel.class);
        View root = inflater.inflate(R.layout.fragment_lapor, container, false);
        /*final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        stepView = root.findViewById(R.id.stepView);
        viewPager2 = root.findViewById(R.id.viewPager);

        viewPager2.setAdapter(new MyAdapter(this));
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                curStep= position;
                stepView.go(position, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
        return root;
    }

    public void nextStep() {
        if (curStep < stepView.getStepCount() - 1) {
            curStep++;
            stepView.go(curStep, true);
            viewPager2.setCurrentItem(curStep, true);
        } else {
            stepView.done(true);
        }
    }

    private class MyAdapter extends FragmentStateAdapter {
        public MyAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (position == 0) {
                return new StepOneFragment();
            } else if (position == 1) {
                return new StepOneFragment();
            }
            return null;
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}