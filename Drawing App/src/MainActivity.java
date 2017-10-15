package com.example.rishabh.imagedraw;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private CanvasView CanvasView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CanvasView =(CanvasView)findViewById(R.id.canvas);
        button=(Button)findViewById(R.id.clear);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CanvasView.clearCanvas();
            }
        });
    }

    public void end(View view){
        Toast.makeText(this, "End of drawing area", Toast.LENGTH_SHORT).show();
    }

}
