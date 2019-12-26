package com.example.user.mycameracapturetest;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivResult;
    private Button btnCapture;  //이미지 공유하고 하기위해 xml폴더를 만들고 만들어야함.
    private String imageFilePath;
    private Uri photoUri;
    private static final int REQUEST_IMAGE_CAPTURE = 672;


    @Override
    protected void onCreate(Bundle savedInstanceState) {   //테드창 해줘야함 그래이들
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivResult = findViewById(R.id.ivResult);
        btnCapture = findViewById(R.id.btnCapture);

        TedPermission.with(getApplicationContext())
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        //허용했을때
                        toastDisplay("잘하셨어요...");
                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                        //거절했을때
                        toastDisplay("그러시면 안되는데.. 사용거절 못함.");
                    }
                })
                .setRationaleMessage("카메라권한필요합니다")
                .setDeniedMessage("거부하지마유 ,,,")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();

        btnCapture.setOnClickListener(this);

    }//end of onCreate

    private void toastDisplay(String s) {

        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnCapture) {
            //1.카메라를 띄워 주세여
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //카메라한테 야 너 카메라앱띄우는데 니가 카메라찍으면 그걸 캡쳐하는기능을 가진 앱을 켜줘

            if (intent.resolveActivity(getPackageManager()) != null) {
                File photoFile = null;
                try {                //요청했는데 받아줄까 ? 널이아니면 받아준다.
                    //파일명을 만들어야됨 20191127103123_~랜덤~.jpg    랜덤을 줌으로 인해 절대 중복될일이 없다.

                    photoFile = createImageFile(); //함수로 만든다.
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (photoFile != null) {
                    photoUri = FileProvider.getUriForFile(getApplicationContext(), getPackageName(), photoFile);//파일을 유알아이로 얻어온다 어디서 ? 겟 어플레케이션 컨텍스트에서 어딘데 여기 패키지네임에서 누구거를 포토파일거
                }
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri); //카메라부르는데 너는 이미지캡쳐해서 어느장소에넣어주야되
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //내가 줬던코드                                  정해져있는것
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //요청한것을 돌려주는것과              작업상황을 알려줌
            Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath);

            //비트맵을 가져왔는데 속성을 체크해줘야한다. 뭔말이냐 카메라찍는데 이사람이 옆으로찍을수도있자나 그럼 이미지가 옆으로나와야지 그래서
            //고런 기능을 체크해야안다.
            ExifInterface exifInterface = null; //요것이 그 속성을 가져온다
            try {
                exifInterface = new ExifInterface(imageFilePath); //이미지파일패쓰에있는 속성 췤
            } catch (IOException e) {
                e.printStackTrace();
            }
            int exifOrientation; //방향설정값을 저장하는 변수
            int exifDegree=0; //각도 설정값을 저장하는 변수

            if (exifInterface != null) { //이게 널이아니냐 제대로가져왔냐 ?
                exifOrientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                exifOrientationToDegreess(exifOrientation); //각도를 요구해서 함수화처리
            } else {
                exifDegree = 0;
            }

            Bitmap bitmapTemp = rotate(bitmap, exifDegree);
            ivResult.setImageBitmap(bitmapTemp);
        }


    }

    private Bitmap rotate(Bitmap bitmap, int exifDegree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(exifDegree);
        Bitmap tempBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return tempBitmap;
    }

    private int exifOrientationToDegreess(int exifOrientation) {
        switch (exifOrientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return 90;
            case ExifInterface.ORIENTATION_ROTATE_180:
                return 180;
            case ExifInterface.ORIENTATION_ROTATE_270:
                return 270;
        }
        return 0;
    }

    private File createImageFile() throws IOException {
        //시간배정값
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy:mm:dd_hhmmss");
        //timestamp= 20191127103523 할때마다 수시로 값이 바뀜

        String timestamp = simpleDateFormat.format(new Date());//어느 날짜인데 오늘 ?
        //임시값이다 이말이야

        String imageFilename = "test_" + timestamp + "_";
        //외부장치의 디렉토리 명을 파일로 가져옴.

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);//이 파일을 저장할 위치 디렉토리를 붙여가지고 요거를 파일명을주려고한다
        File image = File.createTempFile(imageFilename, ".jpg", storageDir);//요기있는 디렉토리에다가 요 이름을 붙여서 그중간에 랜덤을 붙여서 .jpg를 불러서
        //파일명          확장자명  디렉토리

        //"storage/emulate/0/Android/data/ ~~~ 경로를 가져옴.
        imageFilePath = image.getAbsolutePath(); //절대경로

        return image;
    }
}
