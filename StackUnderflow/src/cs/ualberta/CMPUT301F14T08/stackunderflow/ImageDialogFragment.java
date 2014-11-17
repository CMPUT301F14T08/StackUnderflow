package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

public class ImageDialogFragment extends DialogFragment {
	
	protected Uri mImageFileUri;
	protected Drawable mImage;
	protected Bitmap mBitmap;
	protected Drawable mDrawable;
	protected ImageButton mImageButton;
	private static final int CAPTURE_IMAGE_REQUEST_CODE = 12345;
	
	int getImageButtonID() {
        return R.id.image_prompt_fragment_image_button;
    }
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		final View v = inflater.inflate(R.layout.image_prompt, null);
		
		mImageButton = (ImageButton)v.findViewById(getImageButtonID());
		mImageButton.setOnClickListener(new View.OnClickListener() {            
            @Override
            public void onClick(View v) {
            	selectPhoto();
            }
		});
            
		return new AlertDialog.Builder(getActivity())
                // Set Dialog Title
                .setTitle("Select a photo")
                .setView(v)
                // Positive button
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //getTarg
                    }
                })
 
                // Negative Button
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,    int which) {
                        // Do something else
                    }
                }).create();
    }
	
	public void selectPhoto() {
		String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Image";
		File folder = new File(path);
		if (!folder.exists())
			folder.mkdir();
		String imagePathAndFileName = path + File.separator + String.valueOf(System.currentTimeMillis()) + ".jpg";
		File imageFile = new File(imagePathAndFileName);
		mImageFileUri = Uri.fromFile(imageFile);
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageFileUri);
		startActivityForResult(intent, CAPTURE_IMAGE_REQUEST_CODE);
	}
	
	//jpeg object is loaded into memory, then scaled down by a factor of two until
	// file size is below 64kb. Scaling is donw by converting JPEG to bitmap at half size,
	//then converted back to JPEG
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if (requestCode == CAPTURE_IMAGE_REQUEST_CODE) {
			if (resultCode == Activity.RESULT_OK) {
				String imageFilePath = mImageFileUri.getPath();
				File imageFile = new File(imageFilePath);					//loads JPEG to memory
				BitmapFactory.Options o = new BitmapFactory.Options();
				o.inSampleSize = 2;											//Factor for scaling down
				Bitmap modified = null;										
				FileOutputStream fos = null;
				long fileSize = imageFile.length();
				//Log.d("MYTAGE", String.valueOf(fileSize), new Exception());
				
				//each iteration shrinks image by a factor of two
				while(fileSize > 64000){
					try {
						modified = BitmapFactory.decodeStream(new FileInputStream(imageFile), null, o); //converts JPEG to bitmap at half size
						fos = new FileOutputStream(imageFile);
					} 
					catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					modified.compress(Bitmap.CompressFormat.JPEG, 100, fos);	//converts bitmap back to JPEG
					fileSize = imageFile.length();
					//Log.d("MYTAGE", String.valueOf(fileSize), new Exception());
				}
				
				//converts JPEG to bitmap without scaling, so that it can be drawn.
				try {
					modified = BitmapFactory.decodeStream(new FileInputStream(imageFile));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				mDrawable = new BitmapDrawable(getResources(), modified); 	//creates drawable from bitmap
				mImageButton.setImageDrawable(mDrawable);					//updates thumbnail view
				}
		}
	}
}
