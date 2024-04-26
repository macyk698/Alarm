package com.example.zegar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = findViewById(R.id.timePicker);
    }

    public void setAlarm(View view) {
        // Pobieramy godzinę i minutę wybraną przez użytkownika
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();

        // Tworzymy kalendarz i ustawiamy go na wybraną godzinę i minutę
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        // Tworzymy Intent dla AlarmReceiver
        Intent intent = new Intent(this, AlarmReceiver.class);

        // Tworzymy PendingIntent dla AlarmReceiver z ustawioną flagą FLAG_IMMUTABLE
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // Pobieramy AlarmManager
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        // Ustawiamy alarm za pomocą AlarmManager
        if (alarmManager != null) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            Toast.makeText(this, "Alarm ustawiony na " + hour + ":" + minute, Toast.LENGTH_SHORT).show();

        }
    }
}