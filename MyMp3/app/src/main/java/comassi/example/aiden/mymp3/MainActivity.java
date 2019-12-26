package comassi.example.aiden.mymp3;


import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


     int plus;    //?
     ConstraintLayout conLay;   //콘레이아웃.

    //리사이클러뷰
    LinearLayoutManager linearLayoutManager; //리사이클러뷰 사용을 위해 리니어레이아웃매니저 필요.
    MainAdapter mainAdapter;    //메인 어댑터도 필요
    ArrayList<MainData> dataList = new ArrayList<MainData>(); //값을 담을 어레이리스트 MainData형 필요.
    RecyclerView recyclerViewMP3; //리사이클러뷰 필요



    //데이터에이스
    static MyDBHelper myHelper;
    static SQLiteDatabase sqlDB; // DB에 데이터를 추가삭제수정조회하기위해 필요.
    Cursor cursor; //값을 받아서 전달한다?
    int good; //좋아요?

    //인텐트
    static int now=0; //?
    static boolean check=true;  //췤
    private ContentResolver res;  //데이터를 생성 검색 업데이트 삭제등을 할수있다 ContentProvicer(콘텐트제공자[연락처 ,통화기록,오디오,비디오 등등])
                                                                              //그래서 권한 획득이 필요함. 매니페스트 ㄱ


    //메인
    static ImageButton btnPlay, btnNext, btnBack; //메인화면
    LinearLayout linearLayout;
    ImageView imageView;
    TextView tvTitle, tvSinger;
    static SeekBar pbMP3;
    MediaPlayer mediaPlayer; //음악을듣기위해서는 미디어플레이어가 필요.
    String selectedMP3;
    Boolean flag = true;
    int playbackPosition = 0;
    static final String MP3_PATH = Environment.getExternalStorageDirectory().getPath() + "/Music/"; //음악경로


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //음악파일의 내용이 리스트에 들어옴
        getMusicList();
        res = getContentResolver();
        mediaPlayer = new MediaPlayer();

        myHelper = new MyDBHelper(this);



        linearLayout = findViewById(R.id.linearLayout);
        conLay = findViewById(R.id.conLay);
        conLay.getBackground().setAlpha(50); //투명도 설정 .
        recyclerViewMP3 = findViewById(R.id.recyclerViewMP3);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewMP3.setLayoutManager(linearLayoutManager);
        mainAdapter = new MainAdapter(dataList, this);
        recyclerViewMP3.setAdapter(mainAdapter);


        btnPlay = findViewById(R.id.btnPlay);
        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);
        tvTitle = findViewById(R.id.tvTitle);
        tvSinger = findViewById(R.id.tvSinger);
        pbMP3 = findViewById(R.id.pbMP3);
        imageView = findViewById(R.id.imageView);


        ActivityCompat.requestPermissions(this, new String[]         //권한요청?
                {android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);


        btnPlay.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        pbMP3.setProgress(0); //첨에 0부터 시작.
        selectedMP3 = dataList.get(0).getTitle(); //첫번째 타이틀 가져온다 ?


        pbMP3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(),"보조시간"+ MusicActivity.mSeek,Toast.LENGTH_SHORT).show();
               // mediaPlayer.seekTo(MusicActivity.mSeek);
                mediaPlayer.start();
                mediaPlayer.seekTo(seekBar.getProgress());



            }
        });


        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(now+1 < dataList.size()){
                    flag = true;
                    check = true;
                    now++;
                    btnPlay.callOnClick();
                }
            }
        });

        Intent intent = new Intent(MainActivity.this, MusicActivity.class);
        startActivity(intent);

        imageView.setOnClickListener(new View.OnClickListener() { //이미지뷰눌렀을때 뮤직액티비티 가져온다
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MusicActivity.class);
                startActivity(intent);
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() { //리니어레이아웃 눌렀을때 뮤직액티비티 가져온다.
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MusicActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getMusicList() {
        dataList = new ArrayList<>();
        //가져오고 싶은 컬럼 명을 나열합니다. 음악의 아이디, 앰블럼 아이디, 제목, 아스티스트 정보를 가져옵니다.
        String[] projection = {MediaStore.Audio.Media._ID, MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST
        };

        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection, MediaStore.Audio.Media.DATA + " like ? " ,
                new String[]{"%mp3Projact%"}, null);

        while (cursor.moveToNext()) {
            MainData mainData = new MainData();
            mainData.setId(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID))); //가져온 정보들을 세팅한다 매개변수값으로 넣어준다.
            mainData.setAlbumId(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)));
            mainData.setTitle(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
            mainData.setArtist(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
            mainData.setLike(false);// ?
            dataList.add(mainData); //그리고 리스트에 추가한다.
        }
        cursor.close();
    }


    @Override
    public void onClick(View v) {
        sqlDB = myHelper.getWritableDatabase();

        if(MusicActivity.abc ==1){
            plus = 1;
        }else if(MusicActivity.abc ==2){
            plus =  (int)(Math.random()*dataList.size());
        }else if(MusicActivity.abc ==3){
            plus = 0;
        }

        switch (v.getId()) {
            case R.id.btnPlay:

                //처음부터 재생(다른거)
                if (check) {
                    flag=false;
                    check=false;
                    playbackPosition = 0;
                    btnPlay.setImageResource(R.mipmap.pause);
                    MusicActivity.play.setImageResource(R.mipmap.pause2);
                    playMusic(dataList.get(now));
                    mediaPlayer.seekTo(playbackPosition);//싴투 움직임 ? 1초단위 ?근데 0?

                //다시재생(같은거 정지중)
                } else {
                    if (flag ) {
                        flag=false;
                        btnPlay.setImageResource(R.mipmap.pause);
                        MusicActivity.play.setImageResource(R.mipmap.pause2);
                        playMusic(dataList.get(now));
                        mediaPlayer.seekTo(playbackPosition);
                //중지(같은거 재생중)
                    } else {
                        flag=true;
                        btnPlay.setImageResource(R.mipmap.play);
                        MusicActivity.play.setImageResource(R.mipmap.play2);
                        mediaPlayer.pause();
                        playbackPosition = mediaPlayer.getCurrentPosition();

                    }
                }

                break;

            case R.id.btnNext:
                playbackPosition = 0;
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                }
                flag = false;
                if (now + plus >  dataList.size() - 1) {
                    now = now + plus - (dataList.size() -1);
                } else {
                    now = now + plus;
                }
                playMusic(dataList.get(now));
                break;

            case R.id.btnBack:
                playbackPosition = 0;
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                }
                flag = false;
                if (now - plus < 0) {
                    now = now - plus + (dataList.size() - 1);
                } else {
                    now = now - plus;
                }
                playMusic(dataList.get(now));

                break;
        }

    }
    public void playMusic(MainData mainData) {
        try {
            tvTitle.setSelected(true);
            tvSinger.setSelected(true);

            tvTitle.setText(mainData.getTitle());
            tvSinger.setText(mainData.getArtist());

            cursor = sqlDB.rawQuery("SELECT joayo FROM musicTBL wHERE title ='"+mainData.getTitle()+"';", null);
                      //셀렉트 명령어를 사용해 쿼리를 실행하려면 로우쿼리를 사용하면 된다.
           if(cursor.getCount() == 0){
               MusicActivity.like.setImageResource(R.mipmap.likef);
               MusicActivity.tvInit.setText("2");
           }else{
               while (cursor.moveToNext()){
                   if(cursor.getInt(0) ==1){
                       MusicActivity.like.setImageResource(R.mipmap.liket);
                       MusicActivity.tvInit.setText("1");
                   }else if(cursor.getInt(0)==2){
                       MusicActivity.like.setImageResource(R.mipmap.likef);
                       MusicActivity.tvInit.setText("2");
                   }
               }

           }
            MusicActivity.textView.setText(mainData.getTitle());
            MusicActivity.title.setText(mainData.getTitle());
            MusicActivity.singer.setText(mainData.getArtist());

            Bitmap bitmap = BitmapFactory.decodeFile(getCoverArtPath(Long.parseLong(mainData.getAlbumId()), getApplication()));
            imageView.setImageBitmap(bitmap);
            MusicActivity.album.setImageBitmap(bitmap);


            Uri musicURI = Uri.withAppendedPath(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, "" + mainData.getId());
            mediaPlayer.reset();
            mediaPlayer.setDataSource(this, musicURI);
            mediaPlayer.prepare();
            mediaPlayer.start();

            //프로그래스바 쓰레드
            Thread thread = new Thread() {
                SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
                @Override
                public void run() {
                    if (mediaPlayer == null) {
                        return;
                    }

                    //쓰레드 안에서는 위젯값을 바꾸면 안된다
                    //1.총 재생시간
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pbMP3.setMax(mediaPlayer.getDuration());
                            MusicActivity.seekBar.setMax(mediaPlayer.getDuration());
                            MusicActivity.sbEnd.setText(sdf.format(mediaPlayer.getDuration()));

                        }
                    });
                    while (mediaPlayer.isPlaying()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pbMP3.setProgress(mediaPlayer.getCurrentPosition());
                                MusicActivity.seekBar.setProgress(mediaPlayer.getCurrentPosition());
                                MusicActivity.sbStart.setText(sdf.format(mediaPlayer.getCurrentPosition()));

                            }
                        });

                        SystemClock.sleep(200);
                    }
                }
            };
            thread.start();

        } catch (Exception e) {
            Log.e("SimplePlayer", e.getMessage());
        }

    }

    public String getCoverArtPath(long albumId, Context context) {

        Cursor albumCursor = context.getContentResolver().query(
                MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Audio.Albums.ALBUM_ART},
                MediaStore.Audio.Albums._ID + " = ?",
                new String[]{Long.toString(albumId)},
                null
        );
        boolean queryResult = albumCursor.moveToFirst();
        String result = null;
        if (queryResult) {
            result = albumCursor.getString(0);
        }
        albumCursor.close();
        return result;
    }


}
