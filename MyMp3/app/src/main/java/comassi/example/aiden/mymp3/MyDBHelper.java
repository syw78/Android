package comassi.example.aiden.mymp3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final  String DB_NAME = "musicTBL"; //테이블명
    private static final  int VERSION = 1;


    //데이터베이스 생성  컨텍스트 : 시스템이 관리하는 정보접근 가능하게 , 안드제공하는 API호출기능
    public MyDBHelper(Context context) {
        super(context,DB_NAME,null,VERSION);
    }


    //테이블 생성 SQLiteOpenHelper 상속받으면 오버라이드 하는 함수들
    @Override
    public void onCreate(SQLiteDatabase db) { //테이블 생성
        String str = "CREATE TABLE musicTBL (title CHAR(100) ,joayo Integer);";
        db.execSQL(str);          //테이블명 title CHAR(100), 조아요 인테저?
        //실행

    }

    //테이블을 삭제하고 다시 생성함
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS musicTBL;"); //만약 musicTBL이 있으면 삭제하고
        onCreate(db); //다시 생성 (oncreate 호출)
    }

}