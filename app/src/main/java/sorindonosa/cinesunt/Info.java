package sorindonosa.cinesunt;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Info extends Activity {

    private TextView tiltUp, tiltDown, bCategorie, bAseaza, bFun, bSucces;
    private RelativeLayout rLayout;
    private LinearLayout linLayout;
    private Button btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.info);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        tiltUp = (TextView) findViewById(R.id.txtUp);
        tiltDown = (TextView) findViewById(R.id.txtDown);
        bCategorie = (TextView) findViewById(R.id.txtCategorie);
        bFun = (TextView) findViewById(R.id.txtFun);
        bAseaza = (TextView) findViewById(R.id.txtAseaza);
        bSucces = (TextView) findViewById(R.id.txtSucces);
        //	int culoareAccent = Color.parseColor("#FFC107");
        int culoareText = Color.parseColor("#e9ece5");
        int culoareAccent = Color.parseColor("#0c264f");


        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/cesar_font.otf");

        rLayout = (RelativeLayout) findViewById(R.id.info);
        int culoareFundal = Color.parseColor("#3b3a36");
        rLayout.setBackgroundColor(culoareFundal);

        linLayout = (LinearLayout) findViewById(R.id.linearLayout1);
        int culoareDeschisa = Color.parseColor("#c0dfd9");
        linLayout.setBackgroundColor(culoareDeschisa);

        Drawable drawableUp = getResources().getDrawable(R.drawable.tilt_down);
        drawableUp.setBounds(0, 0, (int) (drawableUp.getIntrinsicWidth() * 0.7),
                (int) (drawableUp.getIntrinsicHeight() * 0.7));
        ScaleDrawable sdUp = new ScaleDrawable(drawableUp, 0, 4, 4);
        tiltUp.setCompoundDrawablesWithIntrinsicBounds(null, null, null, sdUp.getDrawable());
        tiltUp.setTypeface(typeFace);
        tiltUp.setText("■ Dacă ghicești cuvântul, înclină telefonul cu fața în jos");

        Drawable drawableDown = getResources().getDrawable(R.drawable.tilt_up);
        drawableDown.setBounds(0, 0, (int) (drawableDown.getIntrinsicWidth() * 0.7),
                (int) (drawableDown.getIntrinsicHeight() * 0.7));
        ScaleDrawable sdDown = new ScaleDrawable(drawableDown, 0, 4, 4);
        tiltDown.setCompoundDrawablesWithIntrinsicBounds(null, null, null, sdDown.getDrawable());
        tiltDown.setTypeface(typeFace);
        tiltDown.setText("■ Dacă vrei să treci la următorul cuvânt, înclină telefonul cu fața în sus");

        bCategorie.setTypeface(typeFace);
        bCategorie.setText("■ Alege o categorie");


        bAseaza.setTypeface(typeFace);
        bAseaza.setText("■ Așează telefonul la nivelul frunții, astfel încât doar prietenii"
                + " tăi să poată vedea cuvintele care apar pe ecran");

        bFun.setTypeface(typeFace);
        bFun.setText("■ Încearcă să ghicești cât mai multe cuvinte pe baza indiciilor oferite de coechipierii tăi");

        bSucces.setTypeface(typeFace);
        bSucces.setText("■ Distracție plăcută!");

        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPlay.setBackgroundColor(culoareAccent);
        btnPlay.setText("Joacă acum!");
        btnPlay.setTextColor(culoareText);
        btnPlay.setTypeface(typeFace);
        btnPlay.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Info.this, MainActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });

    }

}
