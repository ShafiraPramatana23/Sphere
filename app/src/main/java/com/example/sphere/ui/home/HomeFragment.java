package com.example.sphere.ui.home;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.sphere.MainActivity;
import com.example.sphere.R;
import com.example.sphere.SplashActivity;
import com.example.sphere.ui.auth.LoginActivity;
import com.example.sphere.ui.profile.MyReportActivity;
import com.example.sphere.util.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    SharedPreferences sharedPreferences;
    String longitude = "";
    String latitude = "";
    String address = "";

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    TextView tvAddress, tvDate, tvTime;
    LinearLayout llLocation;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        sharedPreferences = getContext().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        longitude = sharedPreferences.getString("longitude", "");
        latitude = sharedPreferences.getString("latitude", "");
        String name = sharedPreferences.getString("name", "");

        CardView cvWeather = root.findViewById(R.id.cvWeather);
        CardView cvRiver = root.findViewById(R.id.cvRiver);
        TextView tvName = root.findViewById(R.id.tvName);
        tvAddress = root.findViewById(R.id.tvAddress);
        tvDate = root.findViewById(R.id.tvDate);
        tvTime = root.findViewById(R.id.tvTime);
        llLocation = root.findViewById(R.id.llLocation);

        tvName.setText(name);

        formatCalendar();

        cvWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyReportActivity.class);
                getActivity().startActivity(intent);
            }
        });

        cvRiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WaterLevelActivity.class);
                getActivity().startActivity(intent);
            }
        });

        int delay = 0; // delay for 0 sec.
        int period = 2000; // repeat every 10 sec.
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                address = ((MainActivity)getActivity()).getAddress();
                System.out.println("oii address: "+address);

                if (!address.isEmpty()) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvAddress.setText(address);
                            llLocation.setVisibility(View.VISIBLE);
                        }
                    });
                    t.cancel();
                }
            }
        }, delay, period);

        return root;
    }

    private void formatCalendar() {
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy", new Locale("ID"));
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", new Locale("ID"));
        date = dateFormat.format(calendar.getTime());
        String time = timeFormat.format(calendar.getTime());
        tvDate.setText(date);
        tvTime.setText(time);
    }

}