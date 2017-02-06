package sorindonosa.cinesunt;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button bFb, bStar, bInfo;
    private RelativeLayout rLayout;
    private Button bAnimale, btnMeserii, btnPlante, btnCelebritati, btnMaiMulte, btnProverbe, btnPersonaje, btnCapitale,
            btnActori, btnSport, btnEmotii, btnCasnice, btnRock, btnDota, btnChimie;
    private static String categorie;
    public static boolean skip = true;
    public static final String PREFS_NAME = "MyPrefsFile1";
    public CheckBox dontShowAgain;

    public static String getCategorie() {
        return categorie;
    }

    public final void launchFacebook() {
        final String urlFb = "fb://page/242844829411231";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(urlFb));
        final PackageManager packageManager = getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() == 0) {
            final String urlBrowser = "https://www.facebook.com/pages/468583609923299";
            intent.setData(Uri.parse(urlBrowser));
        }

        startActivity(intent);
    }

    private void launchMarket() {
        Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        int culoareFundal = Color.parseColor("#3b3a36");
        int culoareDeschisa = Color.parseColor("#c0dfd9");
        int alb = Color.parseColor("#e9ece5");
     //   int culoareAccent = Color.parseColor("#FFC107");
        int culoareAccent = Color.parseColor("#0c264f");


        rLayout = (RelativeLayout) findViewById(R.id.layoutGrid);
        rLayout.setBackgroundColor(culoareFundal);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/cesar_font.otf");

        btnActori = (Button) findViewById(R.id.btnActori);
        btnActori.setBackgroundColor(culoareDeschisa);
        btnActori.setTextColor(culoareFundal);
        btnActori.setTypeface(typeFace);
        btnActori.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                categorie = "Actori";
                Intent myIntent = new Intent(MainActivity.this, Bridge.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });

        bAnimale = (Button) findViewById(R.id.btnAnimale);
        bAnimale.setBackgroundColor(alb);
        bAnimale.setTextColor(culoareFundal);
        bAnimale.setTypeface(typeFace);
        bAnimale.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                categorie = "Animale";
                Intent myIntent = new Intent(MainActivity.this, Bridge.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });

        btnCelebritati = (Button) findViewById(R.id.btnCelebritati);
        btnCelebritati.setBackgroundColor(culoareDeschisa);
        btnCelebritati.setTextColor(culoareFundal);
        btnCelebritati.setTypeface(typeFace);
        btnCelebritati.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                categorie = "Celebritati";
                Intent myIntent = new Intent(MainActivity.this, Bridge.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });

        btnCapitale = (Button) findViewById(R.id.btnCapitale);
        btnCapitale.setBackgroundColor(alb);
        btnCapitale.setTextColor(culoareFundal);
        btnCapitale.setTypeface(typeFace);
        btnCapitale.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                categorie = "Capitale";
                Intent myIntent = new Intent(MainActivity.this, Bridge.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });

        btnEmotii = (Button) findViewById(R.id.btnEmotii);
        btnEmotii.setBackgroundColor(culoareDeschisa);
        btnEmotii.setTextColor(culoareFundal);
        btnEmotii.setTypeface(typeFace);
        btnEmotii.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                categorie = "Emotii";
                Intent myIntent = new Intent(MainActivity.this, Bridge.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });

        btnRock = (Button) findViewById(R.id.btnRock);
        btnRock.setBackgroundColor(alb);
        btnRock.setTextColor(culoareFundal);
        btnRock.setTypeface(typeFace);
        btnRock.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                categorie = "Rock";
                Intent myIntent = new Intent(MainActivity.this, Bridge.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });

        btnMeserii = (Button) findViewById(R.id.btnMeserii);
        btnMeserii.setBackgroundColor(culoareDeschisa);
        btnMeserii.setTextColor(culoareFundal);
        btnMeserii.setTypeface(typeFace);
        btnMeserii.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                categorie = "Meserii";
                Intent myIntent = new Intent(MainActivity.this, Bridge.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });

        btnPersonaje = (Button) findViewById(R.id.btnPersonaje);
        btnPersonaje.setBackgroundColor(alb);
        btnPersonaje.setTextColor(culoareFundal);
        btnPersonaje.setTypeface(typeFace);
        btnPersonaje.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                categorie = "Personaje";
                Intent myIntent = new Intent(MainActivity.this, Bridge.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });

        btnPlante = (Button) findViewById(R.id.btnPlante);
        btnPlante.setBackgroundColor(culoareDeschisa);
        btnPlante.setTextColor(culoareFundal);
        btnPlante.setTypeface(typeFace);
        btnPlante.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                categorie = "Plante";
                Intent myIntent = new Intent(MainActivity.this, Bridge.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });

        btnProverbe = (Button) findViewById(R.id.btnProverbe);
        btnProverbe.setBackgroundColor(alb);
        btnProverbe.setTextColor(culoareFundal);
        btnProverbe.setTypeface(typeFace);
        btnProverbe.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                categorie = "Proverbe";
                Intent myIntent = new Intent(MainActivity.this, Bridge.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });

        btnSport = (Button) findViewById(R.id.btnSporturi);
        btnSport.setBackgroundColor(culoareDeschisa);
        btnSport.setTextColor(culoareFundal);
        btnSport.setTypeface(typeFace);
        btnSport.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                categorie = "Sporturi";
                Intent myIntent = new Intent(MainActivity.this, Bridge.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });

        btnCasnice = (Button) findViewById(R.id.btnCasnice);
        btnCasnice.setBackgroundColor(alb);
        btnCasnice.setTextColor(culoareFundal);
        btnCasnice.setTypeface(typeFace);
        btnCasnice.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                categorie = "Casnice";
                Intent myIntent = new Intent(MainActivity.this, Bridge.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });

        btnDota = (Button) findViewById(R.id.btnDota);
        btnDota.setBackgroundColor(culoareDeschisa);
        btnDota.setTextColor(culoareFundal);
        btnDota.setTypeface(typeFace);
        btnDota.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                categorie = "Dota";
                Intent myIntent = new Intent(MainActivity.this, Bridge.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });

        btnChimie = (Button) findViewById(R.id.btnChimie);
        btnChimie.setBackgroundColor(alb);
        btnChimie.setTextColor(culoareFundal);
        btnChimie.setTypeface(typeFace);
        btnChimie.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                categorie = "Chimie";
                Intent myIntent = new Intent(MainActivity.this, Bridge.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });


        btnChimie = (Button) findViewById(R.id.btnFizica);
        btnChimie.setBackgroundColor(culoareDeschisa);
        btnChimie.setTextColor(culoareFundal);
        btnChimie.setTypeface(typeFace);
        btnChimie.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                categorie = "Fizica";
                Intent myIntent = new Intent(MainActivity.this, Bridge.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });


        btnMaiMulte = (Button) findViewById(R.id.btnMaiMulte);
        btnMaiMulte.setBackgroundColor(alb);
        btnMaiMulte.setTextColor(culoareFundal);
        btnMaiMulte.setTypeface(typeFace);
        btnMaiMulte.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMarket();
            }
        });

        bFb = (Button) findViewById(R.id.btnFb);
        bFb.setBackgroundColor(culoareAccent);
        bFb.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                launchFacebook();
            }
        });

        bStar = (Button) findViewById(R.id.btnAnother);
        bStar.setBackgroundColor(culoareAccent);
        bStar.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                launchMarket();
            }
        });

        bInfo = (Button) findViewById(R.id.btnSame);
        bInfo.setBackgroundColor(culoareAccent);
        bInfo.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Info.class);
                startActivity(myIntent);
            }
        });

        if (skip) {
            AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
            LayoutInflater adbInflater = LayoutInflater.from(MainActivity.this);
            View eulaLayout = adbInflater.inflate(R.layout.checkbox, null);
            dontShowAgain = (CheckBox) eulaLayout.findViewById(R.id.skip);
            adb.setView(eulaLayout);
            adb.setIcon(R.drawable.ic_inform);
            adb.setTitle("Cum se joacă?");
            adb.setMessage("Citește intrucțiunile inainte de primul joc!");
            adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String checkBoxResult = "NOT checked";
                    if (dontShowAgain.isChecked())
                        checkBoxResult = "checked";
                    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("skipMessage", checkBoxResult);
                    // Commit the edits!
                    editor.commit();
                    Intent myIntent = new Intent(MainActivity.this, Info.class);
                    startActivity(myIntent);
                    return;
                }
            });

            adb.setNegativeButton("ANULEAZĂ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String checkBoxResult = "NOT checked";
                    if (dontShowAgain.isChecked())
                        checkBoxResult = "checked";
                    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("skipMessage", checkBoxResult);
                    // Commit the edits!
                    editor.commit();
                    return;
                }
            });
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            String skipMessage = settings.getString("skipMessage", "NOT checked");
            if (!skipMessage.equals("checked"))
                adb.show();

        }
        skip = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle("Ieșire").setMessage("Ești sigur că vrei să ieși?").setCancelable(false)
                .setNegativeButton("Nu", null).setPositiveButton("Da", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.finish();
            }
        }).setIcon(R.drawable.exit).create().show();
    }
}
