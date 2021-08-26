package com.example.stickynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private EditText noteEdt;
    private Button plusBtn,subBtn,boldBtn,italicBtn,underlineBtn;
    TextView sizeTv;
    StickyNotes note = new StickyNotes();
    float CurrentSize ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteEdt = findViewById(R.id.idEdt);
        plusBtn = findViewById(R.id.btnPlus);
        subBtn = findViewById(R.id.btnSubtract);
        boldBtn = findViewById(R.id.btnBold);
        italicBtn = findViewById(R.id.btnItalic);
        underlineBtn = findViewById(R.id.btnUnderline);
        sizeTv = findViewById(R.id.TvSize);
        CurrentSize = noteEdt.getTextSize();
        sizeTv.setText(""+CurrentSize);
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeTv.setText("" + CurrentSize);
                CurrentSize++;
                noteEdt.setTextSize(CurrentSize);
            }
        });
        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeTv.setText("" + CurrentSize);
                CurrentSize--;
                noteEdt.setTextSize(CurrentSize);
            }
        });
        boldBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(noteEdt.getTypeface().isBold())
                {
                    noteEdt.setTypeface(Typeface.DEFAULT);
                }
                else
                {
                    noteEdt.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                }
            }
        });
        underlineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(noteEdt.getPaintFlags() == 8)
                {
                    noteEdt.setPaintFlags(noteEdt.getPaintFlags() & (-Paint.UNDERLINE_TEXT_FLAG));
                }
                else
                {
                    noteEdt.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                }
            }
        });
        italicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(noteEdt.getTypeface().isItalic())
                {
                    noteEdt.setTypeface(Typeface.DEFAULT);
                }
                else
                {
                    noteEdt.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                }

            }
        });

    }

    public void SaveButton(View view) {
        note.setStick(noteEdt.getText().toString(), this);
        updateWidget();
        Toast.makeText(this, "Note has been updated", Toast.LENGTH_SHORT).show();
    }

    private void updateWidget() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        RemoteViews remoteViews = new RemoteViews(this.getPackageName(),R.layout.widget_layout);
        ComponentName thisWidget = new ComponentName(this,AppWidget.class);
        remoteViews.setTextViewText(R.id.idTVWidget,noteEdt.getText().toString());
        appWidgetManager.updateAppWidget(thisWidget,remoteViews);
    }
}