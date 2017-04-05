package com.wordpress.priyankvex.paintapp.Actvity;



import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.wordpress.priyankvex.paintapp.File_Confix_Data;
import com.wordpress.priyankvex.paintapp.R;

public class VideoStream2 extends Activity{

	private int[] play = new int[] {
			R.raw.a, R.raw.b,
			R.raw.c, R.raw.d, R.raw.e, R.raw.f,
			R.raw.g, R.raw.h, R.raw.i, R.raw.j,
			R.raw.k, R.raw.l, R.raw.m, R.raw.n,
			R.raw.o, R.raw.p, R.raw.q, R.raw.r,
			R.raw.s, R.raw.t, R.raw.u, R.raw.v,
			R.raw.w, R.raw.x, R.raw.y, R.raw.z };
	private int value ;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			value = extras.getInt("key_2");
			//The key argument here must match that used in the other activity
		}


		VideoView videoView =(VideoView)findViewById(R.id.surface_view);
	    MediaController mediaController= new MediaController(this);
	    mediaController.setAnchorView(videoView);        
	    Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+play[value]);
	    videoView.setMediaController(mediaController);
	    videoView.setVideoURI(uri);        
	    videoView.requestFocus();
	    videoView.start();
	}
	@Override
	public void onBackPressed() {
		VideoStream2.this.finish();
	}
}
