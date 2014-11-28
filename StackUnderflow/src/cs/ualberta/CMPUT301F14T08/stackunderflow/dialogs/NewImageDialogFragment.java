package cs.ualberta.CMPUT301F14T08.stackunderflow.dialogs;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import cs.ualberta.CMPUT301F14T08.stackunderflow.R;
/**
 * The Dialog shows up and tells asks you to input an image. 
 * @author Cmput301 Winter 2014 Group 8
 */
public class NewImageDialogFragment extends DialogFragment {

    protected Uri mJPEGFileUri;
    protected String mJPEGFileName;
    protected byte mJPEGByteArray[];
    protected ImageButton mImageButton;
    protected Button mCameraButton;
    protected Button mGalleryButton;
    protected TextView mTextView;
    //protected File mJPEGFile;
    private static final int CAMERA_IMAGE_REQUEST_CODE = 12345;
    private static final int GALLERY = 12634;
    int getImageButtonID() {
        return R.id.image_prompt_fragment_image_button;
    }
    int getTextViewID() {
        return R.id.image_prompt_fragment_textview;
    }
    int getCameraButtonID() {
        return R.id.image_prompt_fragment_camera_button;
    }
    int getGalleryButtonID() {
        return R.id.image_prompt_fragment_gallery_button;
    }
    
    /**
     * Sets arguments and calls constructor
     * @param fileName name of the file
     * @param byteArray a image saved as an array of bytes.
     * @return
     */
    public static NewImageDialogFragment newInstance(String fileName, byte[] byteArray){
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
        
        mJPEGByteArray = getArguments().getByteArray("byteArray");
        mJPEGFileName = getArguments().getString("fileName");
        
        
        mCameraButton = (Button)v.findViewById(getCameraButtonID());
        mCameraButton.setVisibility(View.GONE);
        mCameraButton.setOnClickListener(new View.OnClickListener() {            
            @Override
            public void onClick(View v) {
                selectCameraPhoto();
            }
        });
        
        mGalleryButton = (Button)v.findViewById(getGalleryButtonID());
        mGalleryButton.setVisibility(View.GONE);
        mGalleryButton.setOnClickListener(new View.OnClickListener() {            
            @Override
            public void onClick(View v) {
            	Intent intent = new Intent();
            	intent.setType("image/*");
            	intent.setAction(Intent.ACTION_GET_CONTENT);
            	startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY);
            }
        });

        mImageButton = (ImageButton)v.findViewById(getImageButtonID());
        mImageButton.setVisibility(View.VISIBLE);
        mImageButton.setOnClickListener(new View.OnClickListener() {            
            @Override
            public void onClick(View v) {
                //selectPhoto();
                mCameraButton.setVisibility(View.VISIBLE);
                mGalleryButton.setVisibility(View.VISIBLE);
                mImageButton.setVisibility(View.GONE);
            }
        });
        
        mTextView = (TextView)v.findViewById(getTextViewID());
        if (mJPEGByteArray != null){
            showPicture();
        }
        else {
            mTextView.setVisibility(View.GONE);
        }

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
    /**
     * Calls camera to take a picture and save it to a file with miliseconds as a name.
     */
    public void selectCameraPhoto() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Image";
        File folder = new File(path);
        if (!folder.exists())
            folder.mkdir();
        String imagePathAndFileName = path + File.separator + String.valueOf(System.currentTimeMillis()) + ".jpg";
        File imageFile = new File(imagePathAndFileName);
        mJPEGFileUri = Uri.fromFile(imageFile);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mJPEGFileUri);
        startActivityForResult(Intent.createChooser(intent, "Choose photo from..."), CAMERA_IMAGE_REQUEST_CODE);
    }

    /**
     * jpeg object is loaded into memory, then scaled down by a factor of two until
     * file size is below 64kb. Scaling is done by converting JPEG to bitmap at half size,
     * then converted back to JPEG
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data){
    	mCameraButton.setVisibility(View.GONE);
        mGalleryButton.setVisibility(View.GONE);
        mImageButton.setVisibility(View.VISIBLE);
        
        if (requestCode == CAMERA_IMAGE_REQUEST_CODE || requestCode == GALLERY) {
            if (resultCode == Activity.RESULT_OK) {
            	String jpegFilePath;
            	if (requestCode == CAMERA_IMAGE_REQUEST_CODE){
                    jpegFilePath = mJPEGFileUri.getPath();
            	}
            	else {
            		mJPEGFileUri = data.getData();
            		jpegFilePath = getPath(mJPEGFileUri);
            	}
                Log.d("URI", jpegFilePath);
                File jpegFile = new File(jpegFilePath); 			//loads JPEG to memory
                Log.d("FILESIZE_B", ""+jpegFile.length());
                long kb = jpegFile.length() / 1024;
                Log.d("FILESIZE_KB", ""+kb);
                mJPEGFileName = jpegFile.getName();
                long fileSize = jpegFile.length();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;							//Factor for scaling down

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
                    mJPEGByteArray = baos.toByteArray();					//loads baos into byte array
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
    /**
     * Displays the image
     */
    public void showPicture(){
        Bitmap bitmap = null;
        bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(mJPEGByteArray)); 	//creates bitmap from jpegByteArray
        Drawable drawable= new BitmapDrawable(getResources(), bitmap);	 				//creates drawable from bitmap
        mImageButton.setImageDrawable(drawable);										//updates thumbnail view
        mTextView.setText(mJPEGFileName);
        mTextView.setVisibility(View.VISIBLE);
    }
    
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor c = getActivity().getContentResolver().query(uri, projection, null, null, null);
        if(c!=null)
        {
            int columnIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            c.moveToFirst();
            return c.getString(columnIndex);
        }
        else return null;
    }
}
