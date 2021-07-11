package com.example.sphere.ui.lapor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.sphere.MainActivity;
import com.example.sphere.R;
import com.example.sphere.ui.lapor.adapter.SpinnerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StepOneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StepOneFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String[] ctg = { "Kebersihan Lingkungan", "Kerusakan Fasilitas Umum"};

    private Spinner spin;

    public StepOneFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StepOneFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StepOneFragment newInstance(String param1, String param2) {
        StepOneFragment fragment = new StepOneFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_step_one, container, false);

        RelativeLayout btnNext = view.findViewById(R.id.btnNext);
        spin = view.findViewById(R.id.spinner);

        setAdapterSpinner();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LaporFragment.getInstance().nextStep();
            }
        });

        return view;
    }

    private void setAdapterSpinner() {
        SpinnerAdapter aa = new SpinnerAdapter(getContext(), R.layout.item_spinner, ctg);
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
    }
}