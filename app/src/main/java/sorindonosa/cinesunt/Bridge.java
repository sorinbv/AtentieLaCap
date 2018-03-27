package sorindonosa.cinesunt;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Bridge extends Activity {

    private TextView poza;
    private MediaPlayer ding;
    private CountDownTimer cT;
    private Button toggle;

    public Bridge() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.bridge);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // ActionBar actionBar = getActionBar();
        // actionBar.hide();

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/cesar_font.otf");

        poza = findViewById(R.id.txtPoza);
        poza.setTypeface(typeFace);
        int culoareAccent = Color.parseColor("#FFC107");

        Drawable drawablePozitie = getResources().getDrawable(R.drawable.pozitie_burned);
        drawablePozitie.setBounds(0, 0, (int) (drawablePozitie.getIntrinsicWidth() * 0.7),
                (int) (drawablePozitie.getIntrinsicHeight() * 0.7));
        ScaleDrawable sdPozitie = new ScaleDrawable(drawablePozitie, 0, 4, 4);
        poza.setCompoundDrawablesWithIntrinsicBounds(null, sdPozitie.getDrawable(), null, null);
        // poza.setTextColor(culoareAccent);
        poza.setText(
                "Înclină telefonul și așează-l pe frunte ca în imagine!" + "\n\n" + "OFERĂ INDICII DESPRE CUVINTE");
        if (MainActivity.getCategorie() != null) {
            if (MainActivity.getCategorie().equals("Animale") || MainActivity.getCategorie().equals("Meserii")
                    || MainActivity.getCategorie().equals("Emotii") || MainActivity.getCategorie().equals("Personaje")
                    || MainActivity.getCategorie().equals("Sporturi") || MainActivity.getCategorie().equals("Casnice")) {
                poza.setText("Înclină telefonul și așează-l pe frunte ca în imagine!" + "\n\n" + "MIMEAZĂ CUVINTELE");
            }
        }
        RelativeLayout rLayout = findViewById(R.id.ingame);
        int culoareFundal = Color.parseColor("#3b3a36");
        rLayout.setBackgroundColor(culoareFundal);
        ding = MediaPlayer.create(getApplicationContext(), R.raw.three_seconds);

        LinearLayout linLayout = findViewById(R.id.linearLayout1);
        int culoareDeschisa = Color.parseColor("#c0dfd9");
        linLayout.setBackgroundColor(culoareDeschisa);

        toggle = findViewById(R.id.btnActiveazaRotire);
        toggle.setVisibility(View.GONE);
        if (android.provider.Settings.System.getInt(getContentResolver(), Settings.System.ACCELEROMETER_ROTATION,
                0) == 0) {
            toggle.setVisibility(View.VISIBLE);

        }
        toggle.setText("ACTIVEAZĂ AICI ROTIRE ECRAN!");
        toggle.setTextColor(Color.WHITE);
        toggle.setBackgroundColor(Color.parseColor("#C15A5A"));
        toggle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (Settings.System.canWrite(getApplicationContext())) {
                        android.provider.Settings.System.putInt(getContentResolver(), Settings.System.ACCELEROMETER_ROTATION,
                                1);
                        toggle.setVisibility(View.GONE);
                        Toast.makeText(Bridge.this, "Rotire ecran ON", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);
                        intent.setData(Uri.parse("package:" + getApplicationContext().getPackageName()));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                } else {
                    android.provider.Settings.System.putInt(getContentResolver(), Settings.System.ACCELEROMETER_ROTATION,
                            1);
                    toggle.setVisibility(View.GONE);
                    Toast.makeText(Bridge.this, "Rotire ecran ON", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            poza.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            poza.setText("");
            toggle.setVisibility(View.GONE);
            poza.setTextSize(TypedValue.COMPLEX_UNIT_SP, 60);
            cT = new CountDownTimer(3500, 1000) {
                @Override
                public void onFinish() {
                    Intent myIntent = new Intent(Bridge.this, Play.class);
                    startActivity(myIntent);
                }

                @Override
                public void onTick(long millisUntilFinished) {
                    String v = String.format("%02d", millisUntilFinished / 60000);
                    int va = (int) ((millisUntilFinished % 60000) / 1000);
                    poza.setText(v + ":" + String.format("%02d", va));
                    if (poza.getText().equals("00:03")) {
                        ding.start();
                    }
                }
            };
            cT.start();
        }

    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(myIntent);
        ding.stop();
        if (cT != null) {
            cT.cancel();
        }
    }
}
