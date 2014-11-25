package cs.ualberta.CMPUT301F14T08.stackunderflow.dialogs;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import cs.ualberta.CMPUT301F14T08.stackunderflow.R;
import cs.ualberta.CMPUT301F14T08.stackunderflow.R.id;
import cs.ualberta.CMPUT301F14T08.stackunderflow.R.layout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class ViewImageDialogFragment extends DialogFragment {

    ImageView mImageView;

    int getImageViewID() {
        return R.id.view_image_dialog_imageView;
    }

    public static ViewImageDialogFragment newInstance(String imageString){
        ViewImageDialogFragment f = new ViewImageDialogFragment();
        Bundle args = new Bundle();
        args.putString("byteArray", imageString);
        Log.d("MYTAG", String.valueOf(imageString.length()));
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
        String imgString = getArguments().getString("byteArray");
        byte[] byteArray = null;
        //Convert back from string to byte array
        byteArray = Base64.decode(imgString, Base64.DEFAULT);
        if (byteArray != null) {Log.d("MYTAG2", "byte");}
        Bitmap bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(byteArray)); //creates bitmap from jpegByteArray
        if (bitmap != null) {Log.d("MYTAG2", "bitmap");}
        Drawable drawable = new BitmapDrawable(getResources(), bitmap);	 	//creates drawable from bitmap
        if (drawable != null) {Log.d("MYTAG2", "drawable");}
        mImageView.setImageDrawable(drawable);							//updates thumbnail view
    }
}
