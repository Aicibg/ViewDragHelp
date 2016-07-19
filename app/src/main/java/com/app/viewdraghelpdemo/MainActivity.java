package com.app.viewdraghelpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.viewdraghelpdemo.inter.ContentView;
import com.app.viewdraghelpdemo.inter.ViewInject;
import com.app.viewdraghelpdemo.inter.ViewInjectUtils;

@ContentView(values = R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewInject(values = R.id.tv_one)
    private TextView tvOne;
    @ViewInject(values = R.id.bt_one)
    private Button btOne;
    @ViewInject(values = R.id.bt_two)
    private Button btTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inJect(this);

        btOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"点击button1",Toast.LENGTH_SHORT).show();
            }
        });

        btTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"点击button2",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
