package com.example.sphere.ui.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.sphere.MainActivity;
import com.example.sphere.R;
import com.example.sphere.ui.profile.MyReportActivity;
import com.example.sphere.util.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    SharedPreferences sharedPreferences;
    String longitude = "";
    String latitude = "";
    String village = "";
    String district = "";
    String city = "";

    TextView tvAddress;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        sharedPreferences = getContext().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        longitude = sharedPreferences.getString("longitude", "");
        latitude = sharedPreferences.getString("latitude", "");

        CardView cvWeather = root.findViewById(R.id.cvWeather);
        CardView cvRiver = root.findViewById(R.id.cvRiver);
        tvAddress = root.findViewById(R.id.tvAddress);

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

        getLocationAdress();

        return root;
    }

    private void getLocationAdress() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading ....");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        String uRl = "https://api.mapbox.com/geocoding/v5/mapbox.places/" + longitude + "," + latitude + ".json?types=poi&access_token=" + getString(R.string.mapbox_access_token);
        System.out.println("URL nyaaa: "+uRl);
        StringRequest request = new StringRequest(Request.Method.GET,
                uRl,
                (String response) -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("features");
                        JSONObject obj = jsonArray.getJSONObject(0);
                        JSONArray arrContext = obj.getJSONArray("context");

                        for (int i = 0; i < arrContext.length(); i++) {
                            JSONObject objContext = arrContext.getJSONObject(i);
                            if (i == 0) {
                                village = objContext.getString("text");
                            } else if (i == 2) {
                                district = objContext.getString("text");
                            } else if (i == 3) {
                                city = objContext.getString("text");
                            }
                        }

                        tvAddress.setText(village + ", " + district + " - " + city);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    progressDialog.dismiss();
                }, error -> {
            Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        });
        request.setRetryPolicy(
                new DefaultRetryPolicy(30000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        MySingleton.getmInstance(getContext()).addToRequestQueue(request);
    }
}