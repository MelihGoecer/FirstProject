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

public class ThermometerFragment extends Fragment implements SensorEventListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "ThermometerFragment";

    private String mParam1;
    private String mParam2;

    private Sensor mTemp;
    private SensorManager sensorManager;
    private TextView textViewDegree;
    private AlertDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Log.d(TAG, "onCreate: ");
    }

    public static ThermometerFragment newInstance(String param1, String param2) {
        ThermometerFragment fragment = new ThermometerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ThermometerFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sensor_thermometer, container, false);
        textViewDegree = view.findViewById(R.id.text_degree);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mTemp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        Log.d(TAG, "onCreateView: ");
        return view;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float ambientTemperature = sensorEvent.values[0];

        float ambientTemperatureRounded = (float) (Math.round(ambientTemperature * Math.pow(10.0, 2)) / Math.pow(10.0, 2));
        textViewDegree.setText(getResources().getString(R.string.degree_format,  String.valueOf(ambientTemperatureRounded), getResources().getString(R.string.degree_sign)));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onStart() {
        super.onStart();
        if(!sensorManager.registerListener(this, mTemp, SensorManager.SENSOR_DELAY_UI)){
            AlertDialog dialog = new AlertDialog.Builder(getActivity())
                    .setTitle("Sensor unavailable")
                    .setMessage("Your device has not a built-in " + TAG.replace("Fragment", " ").trim().toUpperCase())
                    .setPositiveButton("continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).show();

        }

        Log.d(TAG, "onStart: ");
    }

    private void closeDialog(){ dialog.cancel(); }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, mTemp, SensorManager.SENSOR_DELAY_UI);
        Log.d(TAG, "onResume: ");

    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sensorManager.unregisterListener(this);
        Log.d(TAG, "onDetach: ");
    }
}
