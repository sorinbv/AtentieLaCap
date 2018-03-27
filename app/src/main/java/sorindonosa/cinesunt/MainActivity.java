package sorindonosa.cinesunt;

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

import java.util.List;

public class MainActivity extends Activity {

    public static final String PREFS_NAME = "MyPrefsFile1";
    public static boolean skip = true;
    private static String categorie;
    public CheckBox dontShowAgain;

    /*private AdView adView;
    private AdView adView2;
    private AdRequest adRequest;
    private AdRequest adRequest2;*/

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


        RelativeLayout rLayout = findViewById(R.id.layoutGrid);
        rLayout.setBackgroundColor(culoareFundal);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/cesar_font.otf");

        Button btnActori = findViewById(R.id.btnActori);
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

        Button bAnimale = findViewById(R.id.btnAnimale);
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

/*        btnCelebritati = (Button) findViewById(R.id.btnCelebritati);
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
        });*/

        Button btnCapitale = findViewById(R.id.btnCapitale);
        btnCapitale.setBackgroundColor(culoareDeschisa);
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

        Button btnEmotii = findViewById(R.id.btnEmotii);
        btnEmotii.setBackgroundColor(alb);
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

        Button btnRock = findViewById(R.id.btnRock);
        btnRock.setBackgroundColor(culoareDeschisa);
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

        Button btnMeserii = findViewById(R.id.btnMeserii);
        btnMeserii.setBackgroundColor(alb);
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

        Button btnPersonaje = findViewById(R.id.btnPersonaje);
        btnPersonaje.setBackgroundColor(culoareDeschisa);
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

        Button btnPlante = findViewById(R.id.btnPlante);
        btnPlante.setBackgroundColor(alb);
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

        Button btnProverbe = findViewById(R.id.btnProverbe);
        btnProverbe.setBackgroundColor(culoareDeschisa);
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

        // cele doua reclame din interior
        /*adView = (AdView) findViewById(R.id.adView);
        adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        adView2 = (AdView) findViewById(R.id.adView2);
        adRequest2 = new AdRequest.Builder().build();
        adView2.loadAd(adRequest2);*/


        Button btnSport = findViewById(R.id.btnSporturi);
        btnSport.setBackgroundColor(alb);
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

        Button btnCasnice = findViewById(R.id.btnCasnice);
        btnCasnice.setBackgroundColor(culoareDeschisa);
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

        Button btnMasini = findViewById(R.id.btnMasini);
        btnMasini.setBackgroundColor(alb);
        btnMasini.setTextColor(culoareFundal);
        btnMasini.setTypeface(typeFace);
        btnMasini.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                categorie = "Masini";
                Intent myIntent = new Intent(MainActivity.this, Bridge.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });


        Button btnMortal = findViewById(R.id.btnMortal);
        btnMortal.setBackgroundColor(culoareDeschisa);
        btnMortal.setTextColor(culoareFundal);
        btnMortal.setTypeface(typeFace);
        btnMortal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                categorie = "Mortal";
                Intent myIntent = new Intent(MainActivity.this, Bridge.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });


        Button btnDota = findViewById(R.id.btnDota);
        btnDota.setBackgroundColor(alb);
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

        Button btnLol = findViewById(R.id.btnLol);
        btnLol.setBackgroundColor(culoareDeschisa);
        btnLol.setTextColor(culoareFundal);
        btnLol.setTypeface(typeFace);
        btnLol.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                categorie = "Lol";
                Intent myIntent = new Intent(MainActivity.this, Bridge.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });

        Button btnHarry = findViewById(R.id.btnHarry);
        btnHarry.setBackgroundColor(alb);
        btnHarry.setTextColor(culoareFundal);
        btnHarry.setTypeface(typeFace);
        btnHarry.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                categorie = "Harry";
                Intent myIntent = new Intent(MainActivity.this, Bridge.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });

        Button btnChimie = findViewById(R.id.btnChimie);
        btnChimie.setBackgroundColor(culoareDeschisa);
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


        Button btnFizica = findViewById(R.id.btnFizica);
        btnFizica.setBackgroundColor(alb);
        btnFizica.setTextColor(culoareFundal);
        btnFizica.setTypeface(typeFace);
        btnFizica.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                categorie = "Fizica";
                Intent myIntent = new Intent(MainActivity.this, Bridge.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });

        Button btnYoutube = findViewById(R.id.btnYoutube);
        btnYoutube.setBackgroundColor(culoareDeschisa);
        btnYoutube.setTextColor(culoareFundal);
        btnYoutube.setTypeface(typeFace);
        btnYoutube.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                categorie = "Youtube";
                Intent myIntent = new Intent(MainActivity.this, Bridge.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });


        Button btnMaiMulte = findViewById(R.id.btnMaiMulte);
        btnMaiMulte.setBackgroundColor(alb);
        btnMaiMulte.setTextColor(culoareFundal);
        btnMaiMulte.setTypeface(typeFace);
        btnMaiMulte.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMarket();
            }
        });

        Button bFb = findViewById(R.id.btnFb);
        bFb.setBackgroundColor(culoareAccent);
        bFb.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                launchFacebook();
            }
        });

        Button bStar = findViewById(R.id.btnAnother);
        bStar.setBackgroundColor(culoareAccent);
        bStar.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                launchMarket();
            }
        });

        Button bInfo = findViewById(R.id.btnSame);
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
            dontShowAgain = eulaLayout.findViewById(R.id.skip);
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
                    editor.apply();
                    Intent myIntent = new Intent(MainActivity.this, Info.class);
                    startActivity(myIntent);
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
                    editor.apply();
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
