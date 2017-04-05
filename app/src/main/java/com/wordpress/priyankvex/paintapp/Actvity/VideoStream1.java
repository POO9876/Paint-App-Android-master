package com.wordpress.priyankvex.paintapp.Actvity;



import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.wordpress.priyankvex.paintapp.File_Confix_Data;
import com.wordpress.priyankvex.paintapp.R;

public class VideoStream1 extends Activity{

	private  int value ;
		
	private int[] play = new int[] {R.raw.k1, R.raw.k2,R.raw.k3,R.raw.k4,R.raw.k5,R.raw.k6,R.raw.k7,R.raw.k8,R.raw.k9,R.raw.k10,
			R.raw.k11, R.raw.k12,R.raw.k13,R.raw.k14,R.raw.k15,R.raw.k16,R.raw.k17,R.raw.k18,R.raw.k19,R.raw.k20,
			R.raw.k21, R.raw.k22,R.raw.k23,R.raw.k24,R.raw.k25,R.raw.k26,R.raw.k27,R.raw.k28,R.raw.k29,R.raw.k30,
			R.raw.k31, R.raw.k32,R.raw.k33,R.raw.k34,R.raw.k35,R.raw.k36,R.raw.k37,R.raw.k38,R.raw.k39,R.raw.k40,
			R.raw.k41, R.raw.k42,R.raw.k43,R.raw.k44};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			 value = extras.getInt("key");
			//The key argument here must match that used in the other activity
		}

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

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
		VideoStream1.this.finish();
	}
}
