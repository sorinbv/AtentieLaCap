package sorindonosa.cinesunt;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;

public class Rezultate extends Activity {

    SpannableStringBuilder res;
    LinearLayout linLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.rezultate);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/cesar_font.otf");
        int culoareVerde = Color.parseColor("#007000");
        int culoareDeschisa = Color.parseColor("#c0dfd9");
        int culoareAccent = Color.parseColor("#0c264f");

        TextView rezult = findViewById(R.id.txtRezultate);
        TextView titlu = findViewById(R.id.txtTitlu);
        TextView scor = findViewById(R.id.txtScor);
        RelativeLayout rLayout = findViewById(R.id.rezultate);
        rLayout.setBackgroundColor(culoareDeschisa);

        linLayout = findViewById(R.id.linearLayout1);

        linLayout.setBackgroundColor(culoareDeschisa);

        rezult.setTypeface(typeFace);
        titlu.setTypeface(typeFace);
        scor.setTypeface(typeFace);

        TextView bAnother = (Button) findViewById(R.id.btnAnother);
        bAnother.setBackgroundColor(culoareAccent);
        bAnother.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Rezultate.this, MainActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });

        TextView bAgain = (Button) findViewById(R.id.btnSame);
        bAgain.setBackgroundColor(culoareAccent);
        bAgain.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Rezultate.this, Bridge.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });

        TextView bShare = (Button) findViewById(R.id.btnShare);
        bShare.setBackgroundColor(culoareAccent);
        bShare.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Am acumulat " + Play.scor
                        + " puncte! Încearcă și tu aplicația și vezi câte cuvinte ghicești\n\n https://play.google.com/store/apps/details?id=sorindonosa.cinesunt");
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(Intent.createChooser(intent, "Share cu prietenii"));
            }
        });

        Play.listaRezultateInt.add(0);
        Play.scor--;
        titlu.setText("Rezultate:");
        // titlu.setTextColor(culoareAccent);
        res = new SpannableStringBuilder();
        res.append(System.getProperty("line.separator"));
        for (int i = 0; i < Play.listaRezultateString.size(); i++) {
            String cuvantNou = "■ " + Play.listaRezultateString.get(i);
            SpannableString redSpannable = new SpannableString(cuvantNou);
            if (Play.listaRezultateInt.get(i) == 0) {
                redSpannable.setSpan(new ForegroundColorSpan(Color.RED), 0, cuvantNou.length(), 0);
            } else {
                redSpannable.setSpan(new ForegroundColorSpan(culoareVerde), 0, cuvantNou.length(), 0);
            }
            res.append(redSpannable);
            res.append(System.getProperty("line.separator"));
        }
        rezult.setText(res, BufferType.SPANNABLE);

        scor.setText("Ai acumulat: " + Play.scor + " puncte!");

    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(myIntent);
    }

}
