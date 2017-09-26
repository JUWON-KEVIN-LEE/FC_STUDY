package com.immymemine.kevin.gallery;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends BaseActivity {
    private static final int REQ_CAMERA = 74;
    private static final int REQ_GALLERY = 84;
    ImageView imageView; Button cameraBtn, galleryBtn;
    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView() {
        imageView = (ImageView)findViewById(R.id.imageView);
        cameraBtn = (Button)findViewById(R.id.cameraBtn);
        galleryBtn = (Button)findViewById(R.id.galleryBtn);
    }

    Uri fileUri = null;
    public void onClickedCamera(View view) {
        // 카메라 앱 띄워서 결과 이미지 저장하기
        // 1. Intent 만들기
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 2. 호환성 처리 버전체크 - LOLLIPOP 이상...
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 3. 실제 파일이 저장되는 파일 객체 < 빈 파일을 생성해둔다.
            // 3.1 실제 파일이 저장되는 곳에 권한이 부여되어 있어야 한다.
                    // LOLLIPOP 부터는 File Provider 를 선언해줘야만 한다. > Manifest
            try {
                File photoFile = createFile();
                fileUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID+".provider", photoFile);
                                                    // Context, Authorities, file
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(intent, REQ_CAMERA);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            startActivityForResult(intent, REQ_CAMERA);
        }
    }
    // 이미지를 저장하기 위해 쓰기 권한이 있는 빈 파일을 생성해두는 함수
    private File createFile() throws IOException {
        // 임시파일명 생성
        String tempFileName = "Temp_"+System.currentTimeMillis();
        // 임시파일 저장용 디렉토리 생성
        File tempDir = new File(Environment.getExternalStorageDirectory() + "/CameraN/");
        // 생성체크
        if(!tempDir.exists())
            tempDir.mkdirs();
        // 실제 임시파일을 생성
        File tempFile = File.createTempFile(tempFileName, ".jpg", tempDir);
                                            // 파일명, 생성자, 저장될 디렉토리
        return tempFile;
    }
    public void onClickedGallery(View view) {
        // 인텐트를 사용해서 갤러리 액티비티 호출
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQ_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            Uri imageUri = null;
            switch (requestCode) {
                case REQ_GALLERY :
                    if(data != null) {
                        // data 에 값이 있으면 갤러리에서 선택한 이미지를
                        // 이미지뷰에 세팅해준다.
                        imageUri = data.getData();
                        imageView.setImageURI(imageUri);
                        break;
                    }
                case REQ_CAMERA :
                    if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                        imageUri = data.getData();
                    } else {
                        imageUri = fileUri;
                    }
                    imageView.setImageURI(imageUri);
                    break;
            }
        }
    }

//    private void refreshMedia(File file) {
//        String[] files = { file.getAbsolutePath() };
//        MediaScannerConnection.scanFile(this, files, new MediaScannerConnection.OnScanCompletedListener() {
//            @Override
//            public void onScanCompleted(String path, Uri uri) {
//
//            }
//        });
//    }
}
