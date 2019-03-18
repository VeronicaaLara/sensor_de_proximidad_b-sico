package com.example.sensorproximidad;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener
{

    LinearLayout ln;
    SensorManager sm;
    Sensor sensor;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ln = (LinearLayout) findViewById(R.id.activity_main);
        tv = (TextView) findViewById(R.id.text);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        // DECLARAMOS EL TIPO DE SENSOR QUE NECESITAMOS
        sensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        //CONTEXTO DEL SENSOR, SIN ESTA LÍNEA EL SENSOR NO FUNCIONARÍA
        sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //valor del sensor y que va a estar en la cadena de texto
        String text = String.valueOf(event.values[0]);
        tv.setText(text);
        float valor = Float.parseFloat(text);

        // damos la instruccion de que si hay algo que lo tape o no muestre un color u otro.
        //ROJO: el sensor no tiene nada que lo tape
        //VERDE; el sensor detecta algo que lo tapa a cierta distancia0
        if (valor == 0){
            ln.setBackgroundColor(Color.GREEN);

        }else{

            ln.setBackgroundColor(Color.RED);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
