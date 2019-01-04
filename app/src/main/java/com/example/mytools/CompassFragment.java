package com.example.mytools;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CompassFragment extends Fragment implements SensorEventListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private TextView textViewCompass;
    private ImageView imageViewCompass;
    private SensorManager sensorManager;
    private Sensor sensorAccelerometer;
    private Sensor sensorMagnetometer;

    private float[] gravityData;
    private float[] geomagneticData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public CompassFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sensor_compass, container, false);
        textViewCompass = (TextView) view.findViewById(R.id.text_compass);
        imageViewCompass = (ImageView) view.findViewById(R.id.imageView_compass);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorMagnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        return view;
    }

    public static CompassFragment newInstance(String param1, String param2) {
        CompassFragment fragment = new CompassFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            gravityData = sensorEvent.values;
        if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            geomagneticData = sensorEvent.values;
        if (gravityData != null && geomagneticData != null) {
            float r[] = new float[9];
            float i[] = new float[9];
            boolean success = SensorManager.getRotationMatrix(r, i, gravityData, geomagneticData);
            if (success) {
                float[] orientation = new float[3];
                SensorManager.getOrientation(r, orientation);
                float compassAngle = orientation[0] * 180 / (float) Math.PI;
                textViewCompass.setText(Float.toString(compassAngle));
                imageViewCompass.setRotation(-compassAngle);
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    public void onStart() {
        super.onStart();
        sensorManager.registerListener(this, sensorAccelerometer, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, sensorMagnetometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorAccelerometer, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, sensorMagnetometer, SensorManager.SENSOR_DELAY_UI);
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
