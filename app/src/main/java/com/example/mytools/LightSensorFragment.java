package com.example.mytools;

import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class LightSensorFragment extends Fragment implements SensorEventListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "LightSensorFragment";


    private String mParam1;
    private String mParam2;

    private SensorManager sensorManager;
    private Sensor mIlluminance;
    private TextView textViewIlluminance;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public LightSensorFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sensor_light, container, false);
        textViewIlluminance = view.findViewById(R.id.text_illuminance);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mIlluminance = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        return view;
    }

    public static LightSensorFragment newInstance(String param1, String param2) {
        LightSensorFragment fragment = new LightSensorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float illuminance = sensorEvent.values[0];

        float illuminanceRounded = (float) (Math.round(illuminance * Math.pow(10.0, 2)) / Math.pow(10.0, 2));
        textViewIlluminance.setText(getResources().getString(R.string.illuminance_format, String.valueOf(illuminanceRounded), getResources().getString(R.string.illuminance_unit)));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onStart() {
        super.onStart();
        sensorManager.registerListener(this, mIlluminance, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, mIlluminance, SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sensorManager.unregisterListener(this);
    }
}
