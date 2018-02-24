package com.snappyappsdev.nonverbalapp.details;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bluelinelabs.conductor.Controller;
import com.snappyappsdev.nonverbalapp.R;
import com.snappyappsdev.nonverbalapp.base.BaseController;
import com.snappyappsdev.nonverbalapp.database.model.Pec;
import com.snappyappsdev.nonverbalapp.ui.ScreenNavigator;
import com.snappyappsdev.nonverbalapp.util.PictureUtils;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;

import static com.snappyappsdev.nonverbalapp.util.audio.AudioUtils.getPhotoFile;

/**
 * Created by lrocha on 2/21/18.
 */

public class PecDetailsController extends BaseController {
    public static final String PEC_TITLE_KEY = "pec_title";
    private static final int REQUEST_PHOTO = 0;

    private File mPhotoFile;

    public static Controller newInstance(String pecTitle) {
        Bundle bundle = new Bundle();
        bundle.putString(PEC_TITLE_KEY, pecTitle);
        return new PecDetailsController(bundle);
    }

    @Inject PecDetailsViewModel viewModel;
    @Inject PecDetailsPresenter presenter;
    @Inject ScreenNavigator screenNavigator;


    @BindView(R.id.tv_pec_title) TextView pecTitleText;
    @BindView(R.id.enter_title_text) EditText pecTitleEditText;
    @BindView(R.id.pec_photo) ImageButton mPecPhotoButton;
    @BindView(R.id.pec_microphone) ImageButton micButton;
    @BindView(R.id.pec_camera) ImageButton cameraButton;
    @BindView(R.id.pec_reset) Button resetButton;
    @BindView(R.id.pec_save) Button saveButton;

    private Pec pec;

    public PecDetailsController(Bundle bundle) {
        super(bundle);
        pec = new Pec(bundle.getString(PEC_TITLE_KEY));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK){
            return;
        }
        if (requestCode == REQUEST_PHOTO) {
            Uri uri = FileProvider.getUriForFile(getActivity(),
                    "com.snappyappsdev.nonverbalapp.fileprovider",
                    mPhotoFile);

            getActivity().revokeUriPermission(uri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            updatePhotoView();
        }
    }


    @Override
    protected void onViewBound(View view) {
        setEditTest();
        micButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screenNavigator.goToAudioRecorder(pec.getTitle());
            }
        });
        setCameraButton();
        setResetButton();
        //saveButton.setOnClickListener(new ToastListener("Save Button"));

    }

    private void setResetButton(){
        resetButton.setOnClickListener( view -> {
            pecTitleText.setText("Enter Pec Name");
            pecTitleEditText.setText("");
            pec.setTitle("");
            mPhotoFile.delete();
            updatePhotoView();
            mPhotoFile = getPhotoFile(getApplicationContext());
        });
    }

    private void updatePhotoView() {

        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPecPhotoButton.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(
                    mPhotoFile.getPath(), getActivity());
            mPecPhotoButton.setImageBitmap(bitmap);
        }
    }

    private void setCameraButton(){
        mPhotoFile = getPhotoFile(getApplicationContext());
        PackageManager packageManager = getActivity().getPackageManager();
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        boolean canTakePhoto = mPhotoFile != null &&
                captureImage.resolveActivity(packageManager) !=null;
        cameraButton.setEnabled(canTakePhoto);

        cameraButton.setOnClickListener(view -> {
            Uri uri = FileProvider.getUriForFile(getActivity(),
                    "com.snappyappsdev.nonverbalapp.fileprovider",
                    mPhotoFile);
            captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);

            List<ResolveInfo> cameraActivities = getActivity()
                    .getPackageManager().queryIntentActivities(captureImage,
                            PackageManager.MATCH_DEFAULT_ONLY);

            for (ResolveInfo activity : cameraActivities) {
                getActivity().grantUriPermission(activity.activityInfo.packageName,
                        uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }

            startActivityForResult(captureImage, REQUEST_PHOTO);
        });
    }

    private void setEditTest(){
        pecTitleEditText.setText(pec.getTitle());
        pecTitleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                pecTitleText.setText(charSequence.toString());
                pec.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected Disposable[] subscriptions() {
        return new Disposable[]{
        };
    }

    @Override
    protected int layoutRes() {
        return R.layout.screen_pec_details;
    }
}
