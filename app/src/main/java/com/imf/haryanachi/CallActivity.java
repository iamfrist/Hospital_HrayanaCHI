package com.imf.haryanachi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;


public class CallActivity extends AppCompatActivity {


    /* private String docname, paitentId, visitId, doctorId, center_name, ward_name, bed_numberstr, paitents_statusstr,pname;
     CountDownTimer countDownTimer;
 */ WebView webview;
    ImageView pdfImageView;
    boolean pdfboolean;
    TextView helpinetxt ,helpinetxt2, fahelptxt,fahelptxt2, ambulancetxt;

    String googleDocs = "https://docs.google.com/viewer?url=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_activitty);
        pdfImageView = findViewById(R.id.pdfimageView);
        webview = (WebView) findViewById(R.id.webview);
        helpinetxt = findViewById(R.id.helpinetxt);
        helpinetxt2 = findViewById(R.id.helpinetxt2);
        fahelptxt = findViewById(R.id.fahelptxt);
        fahelptxt2 = findViewById(R.id.fahelptxt2);
        ambulancetxt = findViewById(R.id.ambulancetxt);

        pdfImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webview.setVisibility(View.VISIBLE);
                webview.getSettings().setJavaScriptEnabled(true);
                webview.isScrollContainer();
                String pdf = googleDocs+"http://15.207.33.122:2909/images/list.pdf";
                webview.loadUrl(pdf);
                pdfboolean = true;
            }
        });
/*Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "Your Phone_number"));
startActivity(intent);*/

        ambulancetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("108"));
                startActivity(intent);
            }
        });
  helpinetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +"8558893911"));
                startActivity(intent);
            }
        });
  helpinetxt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +"8558891075"));
                startActivity(intent);
            }
        });
  ambulancetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "108"));
                startActivity(intent);

            }
        });

        fahelptxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +"18001806222"));
                startActivity(intent);
            }
        });
   fahelptxt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +"18001801100"));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        //  countDownTimer.cancel();
    }

    @Override
    public void onBackPressed() {
        if (pdfboolean) {
            webview.setVisibility(View.GONE);
        } super.onBackPressed();
    }
}