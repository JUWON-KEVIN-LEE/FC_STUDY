package com.immymemine.kevin.customgallery;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends BaseActivity {
    private static final int REQ_GAL = 999;
    ImageView imageView;
    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView);
    }

    public void goToGallery(View view) {
        Intent intent = new Intent(this, GalleryActivity.class);
        startActivityForResult(intent, REQ_GAL);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQ_GAL:
                    if(data != null) {
                        String path = data.getStringExtra("ImagePath");
                        Uri uri = Uri.parse(path);
                        imageView.setImageURI(uri);
                    }
                    break;
            }
        }
    }
}
