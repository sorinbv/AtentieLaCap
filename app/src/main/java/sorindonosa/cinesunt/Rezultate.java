package sorindonosa.cinesunt;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
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

	private TextView rezult, titlu, bAnother, bAgain, bShare, scor;
	private RelativeLayout rLayout;
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
		// ActionBar actionBar = getActionBar();
		// actionBar.hide();

		Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/cesar_font.otf");
		int culoareFundal = Color.parseColor("#3b3a36");
	//	int culoareAccent = Color.parseColor("#FFC107");
		int culoareVerde = Color.parseColor("#007000");
		int culoareDeschisa = Color.parseColor("#c0dfd9");
		int culoareAccent = Color.parseColor("#0c264f");

		rezult = (TextView) findViewById(R.id.txtRezultate);
		titlu = (TextView) findViewById(R.id.txtTitlu);
		scor = (TextView) findViewById(R.id.txtScor);
		rLayout = (RelativeLayout) findViewById(R.id.rezultate);
		rLayout.setBackgroundColor(culoareDeschisa);

		linLayout = (LinearLayout) findViewById(R.id.linearLayout1);

		linLayout.setBackgroundColor(culoareDeschisa);

		rezult.setTypeface(typeFace);
		titlu.setTypeface(typeFace);
		scor.setTypeface(typeFace);

		bAnother = (Button) findViewById(R.id.btnAnother);
		bAnother.setBackgroundColor(culoareAccent);
		/*Drawable drawableFb = getResources().getDrawable(R.drawable.lista_70);
		drawableFb.setBounds(0, 0, (int) (drawableFb.getIntrinsicWidth() * 0.2),
				(int) (drawableFb.getIntrinsicHeight() * 0.2));
		ScaleDrawable sdFb = new ScaleDrawable(drawableFb, 0, 2, 2);
		bAnother.setCompoundDrawablesWithIntrinsicBounds(null, null, null, sdFb.getDrawable());*/
		bAnother.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(Rezultate.this, MainActivity.class);
				myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(myIntent);
			}
		});

		bAgain = (Button) findViewById(R.id.btnSame);
		bAgain.setBackgroundColor(culoareAccent);
		/*Drawable drawablesame = getResources().getDrawable(R.drawable.again_70);
		drawablesame.setBounds(0, 0, (int) (drawablesame.getIntrinsicWidth() * 0.2),
				(int) (drawablesame.getIntrinsicHeight() * 0.2));
		ScaleDrawable sdSame = new ScaleDrawable(drawablesame, 0, 2, 2);
		bAgain.setCompoundDrawablesWithIntrinsicBounds(null, null, null, sdSame.getDrawable());*/
		bAgain.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(Rezultate.this, Bridge.class);
				myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(myIntent);
			}
		});

		bShare = (Button) findViewById(R.id.btnShare);
		bShare.setBackgroundColor(culoareAccent);
		/*Drawable drawableShare = getResources().getDrawable(R.drawable.share_70);
		drawableShare.setBounds(0, 0, (int) (drawableShare.getIntrinsicWidth() * 0.2),
				(int) (drawableShare.getIntrinsicHeight() * 0.2));
		ScaleDrawable sdShare = new ScaleDrawable(drawableShare, 0, 2, 2);
		bShare.setCompoundDrawablesWithIntrinsicBounds(null, null, null, sdShare.getDrawable());*/
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
