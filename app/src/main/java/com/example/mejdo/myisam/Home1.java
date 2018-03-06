package com.example.mejdo.myisam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;

public class Home1 extends AppCompatActivity {
CardView mycard ;
    Intent i ;
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);
        ll = (LinearLayout) findViewById(R.id.ll);
        mycard = (CardView) findViewById(R.id.add);
        mycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null ;
                intent = new Intent(Home1.this , add_club.class);
                startActivity(intent);
            }
        });

    }
}
