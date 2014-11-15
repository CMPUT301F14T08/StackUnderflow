package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.io.ByteArrayOutputStream;
import java.io.File;

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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

public class ImageDialogFragment extends DialogFragment {
	
	Uri imageFileUri;
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
                        // Do something else
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
		imageFileUri = Uri.fromFile(imageFile);
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
		startActivityForResult(intent, CAPTURE_IMAGE_REQUEST_CODE);
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if (requestCode == CAPTURE_IMAGE_REQUEST_CODE) {
			if (resultCode == Activity.RESULT_OK) {
				//mImage = Drawable.createFromPath(imageFileUri.getPath());
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				mBitmap = BitmapFactory.decodeFile(imageFileUri.getPath());
				mBitmap.compress(Bitmap.CompressFormat.JPEG, 70, out);
				mDrawable = new BitmapDrawable(getResources(), mBitmap);
				mImageButton.setImageDrawable(mDrawable);
				
			}
		}
	}
}
