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

public class HygrometerFragment extends Fragment implements SensorEventListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "HygrometerFragment";

    private String mParam1;
    private String mParam2;

    private SensorManager sensorManager;
    private Sensor mHumidity;
    private TextView textViewHumidity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Log.d(TAG, "onCreate: ");
    }

    public HygrometerFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sensor_hygrometer, container, false);
        textViewHumidity = view.findViewById(R.id.text_humidity);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mHumidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        Log.d(TAG, "onCreateView: ");
        return view;
    }

    public static HygrometerFragment newInstance(String param1, String param2) {
        HygrometerFragment fragment = new HygrometerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float relativeHumidity = sensorEvent.values[0];

        float relativeHumidityRounded = (float) (Math.round(relativeHumidity * Math.pow(10.0, 2)) / Math.pow(10.0, 2));
        textViewHumidity.setText(getResources().getString(R.string.humidity_format, String.valueOf(relativeHumidityRounded), getResources().getString(R.string.humidity_unit)));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onStart() {
        super.onStart();
            if(!sensorManager.registerListener(this, mHumidity, SensorManager.SENSOR_DELAY_UI)){
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


    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, mHumidity, SensorManager.SENSOR_DELAY_UI);
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
