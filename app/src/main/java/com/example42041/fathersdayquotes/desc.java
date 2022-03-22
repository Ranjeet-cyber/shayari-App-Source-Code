package com.example42041.fathersdayquotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class desc extends AppCompatActivity {
    private TextView txt;
    private ImageView sbtn,cbtn,whatsapp,pre,next;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        txt=findViewById(R.id.second_frameTxt);
        sbtn=findViewById(R.id.share);
        cbtn=findViewById(R.id.copy);
        whatsapp=findViewById(R.id.fav);
        final String rQuote=getIntent().getStringExtra("quote");
        txt.setText(rQuote);

        sbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,txt.getText().toString());
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, "Share by");
                startActivity(shareIntent);

            }
        });
        cbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard =(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip= ClipData.newPlainText("editText",txt.getText().toString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(desc .this,"Copied", Toast.LENGTH_SHORT).show();
            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent whatsPP = new Intent(Intent.ACTION_SEND );
                    whatsPP.setType("text/plain");
                    whatsPP.putExtra(Intent.EXTRA_TEXT,txt.getText().toString());
                    whatsPP.setPackage("com.whatsapp");
                    startActivity(whatsPP);
                }catch (Exception e ){
                    e.printStackTrace();
                }


            }
        });

        getWindow().setAttributes(attrs);
    }
}