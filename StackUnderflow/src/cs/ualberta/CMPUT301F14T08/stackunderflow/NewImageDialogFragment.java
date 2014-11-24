package cs.ualberta.CMPUT301F14T08.stackunderflow;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
import android.widget.TextView;

public class NewImageDialogFragment extends DialogFragment {

    protected Uri mJPEGFileUri;
    protected String mJPEGFileName;
    protected byte mJPEGByteArray[];
    protected ImageButton mImageButton;
    protected TextView mTextView;
    //protected File mJPEGFile;
    private static final int CAPTURE_IMAGE_REQUEST_CODE = 12345;

    int getImageButtonID() {
        return R.id.image_prompt_fragment_image_button;
    }
    int getTextViewID() {
        return R.id.image_prompt_fragment_textview;
    }
    //Sets arguments and calls constructor
    static NewImageDialogFragment newInstance(String fileName, byte[] byteArray){
        NewImageDialogFragment f = new NewImageDialogFragment();
        Bundle args = new Bundle();
        args.putString("fileName", fileName);
        args.putByteArray("byteArray", byteArray);
        f.setArguments(args);
        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View v = inflater.inflate(R.layout.image_prompt_fragment, null);

        mTextView = (TextView)v.findViewById(getTextViewID());
        mImageButton = (ImageButton)v.findViewById(getImageButtonID());

        mJPEGByteArray = getArguments().getByteArray("byteArray");
        mJPEGFileName = getArguments().getString("fileName");

        if (mJPEGByteArray != null){
            showPicture();
        }
        else {
            mTextView.setVisibility(View.GONE);
        }

        mImageButton.setOnClickListener(new View.OnClickListener() {            
            @Override
            public void onClick(View v) {
                selectPhoto();
            }
        });

        return new AlertDialog.Builder(getActivity())
        .setTitle("Select a photo")
        .setView(v)
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent();
                Bundle extras = new Bundle();
                extras.putByteArray("BYTES", mJPEGByteArray);
                extras.putString("NAME", mJPEGFileName);
                i.putExtras(extras);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
            }
        })
        .setNegativeButton("Remove", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,    int which) {
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED, null);
            }
        }).create();
    }
    //Calls camera to take a picture and save it to a file with miliseconds as a name.
    public void selectPhoto() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Image";
        File folder = new File(path);
        if (!folder.exists())
            folder.mkdir();
        String imagePathAndFileName = path + File.separator + String.valueOf(System.currentTimeMillis()) + ".jpg";
        File imageFile = new File(imagePathAndFileName);
        mJPEGFileUri = Uri.fromFile(imageFile);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mJPEGFileUri);
        startActivityForResult(Intent.createChooser(intent, "Choose photo from..."), CAPTURE_IMAGE_REQUEST_CODE);
    }

    //jpeg object is loaded into memory, then scaled down by a factor of two until
    // file size is below 64kb. Scaling is done by converting JPEG to bitmap at half size,
    //then converted back to JPEG
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String jpegFilePath = mJPEGFileUri.getPath();
                File jpegFile = new File(jpegFilePath); //loads JPEG to memory
                Log.d("FILESIZE_B", ""+jpegFile.length());
                long kb = jpegFile.length() / 1024;
                Log.d("FILESIZE_KB", ""+kb);
                mJPEGFileName = jpegFile.getName();
                long fileSize = jpegFile.length();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;										//Factor for scaling down

                FileInputStream fis = null;
                mJPEGByteArray = null;				
                //loads jpeg into jpegByteArray
                try {
                    fis = new FileInputStream(jpegFile);
                    mJPEGByteArray = new byte[(int)fileSize];
                    fis.read(mJPEGByteArray);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Bitmap bitmap = null;
                ByteArrayOutputStream baos = null;
                //compresses jpegByteArray
                while(fileSize > 64*1024){
                    bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(mJPEGByteArray), null, options); //converts JPEG to bitmap at half size
                    baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);	//converts bitmap back to JPEG in baos
                    mJPEGByteArray = baos.toByteArray();						//loads baos into byte array
                    try {baos.flush();}
                    catch (IOException e) {e.printStackTrace();	}						
                    fileSize = mJPEGByteArray.length;						//recalculates fileSize
                    //Log.d("MYTAG", String.valueOf(fileSize), new Exception());

                    Log.d("FILESIZE_B", ""+fileSize);
                    long kbs = fileSize / 1024;
                    Log.d("FILESIZE_KB", ""+kbs);
                }
                showPicture();				
            }
        }
    }
    public void showPicture(){
        Bitmap bitmap = null;
        bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(mJPEGByteArray)); //creates bitmap from jpegByteArray
        Drawable drawable= new BitmapDrawable(getResources(), bitmap);	 	//creates drawable from bitmap
        mImageButton.setImageDrawable(drawable);							//updates thumbnail view
        mTextView.setText(mJPEGFileName);
        mTextView.setVisibility(View.VISIBLE);
    }
}
