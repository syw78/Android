package com.example.user.myvideoplayertest;

import android.Manifest;

import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.IOException;
import java.util.ArrayList;

//카메라를하려면 우선 임플리멘트해라
public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback, View.OnClickListener {
    private Button btnRecordStart;
    private Button btnRecordStop;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private Camera camera;
    private MediaRecorder mediaRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRecordStart = findViewById(R.id.btnRecordStart);
        btnRecordStop = findViewById(R.id.btnRecordStop);
        surfaceView = findViewById(R.id.surfaceView);

        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); //버퍼장치부여

        //다이얼로그 창에서 진행하는 퍼미션 라이브러리 박상권.
        TedPermission.with(this) //이벤트할때
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        toastDisPlay("잘하셨어요..");
                    }


                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                        toastDisPlay("헐 .. 어떻게 하라구ㅠ");
                    }
                })
                .setRationaleMessage("녹화권한 허용여부")
                .setDeniedMessage("녹화권한 허용거부")                  //퍼미션을 다 적는다 한번하면 한번 세번하면 세번물어봐준다.
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO)
                .check();

        btnRecordStart.setOnClickListener(this);
        btnRecordStop.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRecordStart:
                btnRecordStop.setEnabled(true);    //버튼조절하고
                btnRecordStart.setEnabled(false);
                //그리고 카메라를 오픈. <조심할것 임포트 할때 하드웨어로 해야한다!>
                camera = Camera.open();
                //카메라 각도 셋팅
                camera.setDisplayOrientation(90);
                try {
                    if (camera == null) {
                        camera.setPreviewDisplay(surfaceHolder);
                        camera.startPreview();
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    toastDisPlay("녹화시작함");
                                    mediaRecorder = new MediaRecorder();
                                    //카메라를 작동시키기위해서 락을 풀어줌
                                    camera.unlock();
                                    mediaRecorder.setCamera(camera);
                                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER); //동영상 촬영할때 소리설정 띵~
                                    mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);//동영상을 녹화하는데 카메라에서 오는 자료를 가지고 녹화진행
                                    mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_1080P)); //요게 해상도.
                                    mediaRecorder.setOrientationHint(90);
                                    mediaRecorder.setOutputFile("/sdcard/text.mp4"); //저장장소
                                    mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());
                                    mediaRecorder.prepare();
                                    mediaRecorder.start();

                                } catch (IOException e) {
                                    e.printStackTrace();
                                    mediaRecorder.release();
                                    camera.release();
                                }
                            }
                        });

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.btnRecordStop:
                btnRecordStart.setEnabled(true);
                btnRecordStop.setEnabled(false);
                //녹화하는 과정을 정지시킴 카메라기능을 정지
                mediaRecorder.stop();
                mediaRecorder.release();        //미디어레코더를 하려면 이렇게 해야된다.
                //그리고 카메라 반납  카메라기능을 정지시킴
                camera.lock();

                if(camera!=null){
                    camera.stopPreview();
                    camera.release();
                }

                camera = null;

                break;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }


    private void toastDisPlay(String s) {
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
    }


}
