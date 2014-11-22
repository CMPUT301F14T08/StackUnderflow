package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.io.ByteArrayInputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewImageDialogFragment extends DialogFragment {
	
	ImageView mImageView;
	
	int getImageViewID() {
        return R.id.view_image_dialog_imageView;
    }
	
	static ViewImageDialogFragment newInstance(byte[] byteArray){
		ViewImageDialogFragment f = new ViewImageDialogFragment();
		Bundle args = new Bundle();
		args.putByteArray("byteArray", byteArray);
		Log.d("MYTAG", String.valueOf(byteArray.length));
		f.setArguments(args);
		return f;
	}
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		final View v = inflater.inflate(R.layout.image_view_fragment, null);
		
		mImageView = (ImageView)v.findViewById(getImageViewID());
		showPicture();
		
		return new AlertDialog.Builder(getActivity())
        .setView(v)
        // Positive button
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	//
            }
        }).create();
	}
	public void showPicture(){
		
		byte[] byteArray = getArguments().getByteArray("byteArray");
		if (byteArray != null) {Log.d("MYTAG2", "byte");}
		Bitmap bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(getArguments().getByteArray("byteArray"))); //creates bitmap from jpegByteArray
		if (bitmap != null) {Log.d("MYTAG2", "bitmap");}
		Drawable drawable = new BitmapDrawable(getResources(), bitmap);	 	//creates drawable from bitmap
		if (drawable != null) {Log.d("MYTAG2", "drawable");}
		mImageView.setImageDrawable(drawable);							//updates thumbnail view
	}
}
