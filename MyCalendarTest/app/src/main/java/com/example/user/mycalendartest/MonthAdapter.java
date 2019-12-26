package com.example.user.mycalendartest;

import android.content.Context;
import android.graphics.Color;
import android.text.format.Time;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Switch;

import java.util.Calendar;
import java.util.jar.Attributes;

//무슨 의미일까 ??
//  selectedPosition = -1 ;

public class MonthAdapter extends BaseAdapter {

    public Context monthContext;
    public MonthItem[] items; // ArrayList<MonthItem> <=42개만
    public Calendar mCalendar;
    public int firstDay; //예를 들자면 11월1일이 금요일이면 이값을 -> 5로 준다.
    public int mStartDay; //Calendar.SUNDAY 현재달력 시작 위치요일(일욜부터 보여줌)
    public int startDay; //Time.SUNDAY

    public int curYear; //현재달력년도
    public int curMonth; //현재달력월
    public int lastDay; //현재달의 마지막 날짜가 몇일까지 가냐
    public int selectedPosition = -1 ;

    public MonthAdapter() {

    }

    public MonthAdapter(Context monthContext) {
        this.monthContext = monthContext;
        //이 생성자를 부르면 달력을 계산할 내용으로 셋팅을 진행한다.
        init();
    }
    public MonthAdapter(Context context , Attributes attributes){
        monthContext=context;
        init();
    }

    private void init() {
        items = new MonthItem[42];
        //현재년도 ,달,날짜,시간까지 값을 가져오는 명령어
        mCalendar = Calendar.getInstance();
        //만약우리가 11월을 선택했다 그러면 1일부터 30일까지,그 다음 1일이 무슨요일인가,그 다음에 이 달력이 시작이 언젠가
        //뭔말입니까 ? 우리나라 달력은 일욜부터 시작 근데 어떤 달력은 토욜부터 시작하는달력이있음 또 어떤데는 월욜부터시작인곳이 있다 전세계적으로다가
        //우리나라는 일요일부터 달력이 시작인데 요 값들을 계산해야한다.
        //년도 ,월 , 마지막일(윤년) , 1일 -> 요일위치 , 달력위치(월,일,토 중 선택)
        recalculate(); // 고게 이것
        //11월달에 뿌려질 객체자료를 배열화시켜서 MonthItem 객체배열에다가 넣어줘야합니다.
        resetDayNumbers();
    }


    private void resetDayNumbers() {
        for(int i=0;i<42;i++){
            int dayNumber = (i+1) - firstDay;
            if(dayNumber<1||dayNumber>lastDay){
                dayNumber=0;
            }
            items[i] = new MonthItem(dayNumber);
        }
    }

    private void recalculate() {
        //현재 월과 날짜를 기준으로해서 1일로 세팅하는것
        //현재 11월27일 기준으로해서 -> 11월1일로 셋팅
        mCalendar.set(Calendar.DAY_OF_MONTH, 1);

        //11월1일이 무슨 요일이냐를 따져야한다.(금요일이면 ->5)(일요일이면 ->0)
        int dayOfweek = mCalendar.get(Calendar.DAY_OF_WEEK); //나한테 날짜를 준다
        firstDay = getFirstDay(dayOfweek);

        //현재 시스템에 달력에 첫번째를 무엇으로 시작하느냐 무슨요일로시작하느냐 ? 요걸 물어보는것.
        //예) 일욜에 시작되는 달력이 있고 토욜에시작 달력있고 월욜 시작달력있고 우리는 일욜이 찍어진다.
        //누가 찍어주느느ㅑ Time.SUNDAY
        mStartDay = mCalendar.getFirstDayOfWeek();
        //현재년도를 구한다.
        curYear=mCalendar.get(Calendar.YEAR);
        //현재달
        curMonth = mCalendar.get(Calendar.MONTH);
        //현재달 마지막날짜. 11월 30일받아옴
        lastDay = getMonthLastDay(curYear, curMonth);
        //우리가 계산하는 방식으로 하는것.
        startDay = getFirstDayOfWeek();
        resetDayNumbers();


    }


    private int getMonthLastDay(int curYear, int curMonth) {

        switch (curMonth + 1) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                if ((curYear % 4 == 0) && (curYear % 100 != 0) || (curYear % 400 == 0)) { //윤년을 찾는 방법 외워둘것.
                    return 29;
                } else {
                    return 28;
                }
        }
    }

    //각자의 나라에 맞게 요일을 숫자로 표현하는 함수.
    private int getFirstDay(int dayOfweek) {
        int result = 0;    //우리나라방식에 맞게 리절트값을 바꿔라
        switch (dayOfweek) {
            case Calendar.SUNDAY:
                result = 0;
                break;
            case Calendar.MONDAY:
                result = 1;
                break;
            case Calendar.TUESDAY:
                result = 2;
                break;
            case Calendar.WEDNESDAY:
                result = 3;
                break;
            case Calendar.THURSDAY:
                result = 4;
                break;
            case Calendar.FRIDAY:
                result = 5;
                break;
            case Calendar.SATURDAY:
                result = 6;
                break;

        }
        return result;
    }


    @Override
    public int getCount() {
        return 42;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        //먼뜨아이템뷰를 객체를 만들어야한다.
        MonthItemView itemView=null;
        if(itemView==null){
            itemView = new MonthItemView(monthContext);
        }else{
            itemView = (MonthItemView) view;
        }

        //뷰위젯에 속성을 부여하지않아서 속성을 부여함
        GridView.LayoutParams params = new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT,120);
        itemView.setItem(items[position]);
        itemView.setLayoutParams(params);
        itemView.setPadding(2,2,2,2);
        itemView.setGravity(Gravity.LEFT);

        int columnIndex= position % 7 ;
        if(columnIndex==0){
            itemView.setTextColor(Color.RED);
        }else{
            itemView.setTextColor(Color.BLACK);
        }
        return itemView;
    }

    private int getFirstDayOfWeek() {
        startDay = Calendar.getInstance().getFirstDayOfWeek();
        switch (startDay) {
            case Calendar.SATURDAY:
                return Time.SATURDAY;
            case Calendar.MONDAY:
                return Time.MONDAY;
            case Calendar.SUNDAY:
                return Time.SUNDAY;
        }
        return 0;
    }

    public void setPreviousMonth(){
        //현재가 11월이라면 10월달로 간다~
        mCalendar.add(Calendar.MONTH,-1); //현재달의 달력을 주는데 -1해서 저번달로간다.
        recalculate();
        resetDayNumbers();//객체를 다 만든다 42개
        //무슨 의미일까 ??
        selectedPosition = -1 ;
    }
    public void setNextMonth(){
        //현재가 11월이라면 12월달로 간다~
        mCalendar.add(Calendar.MONTH,+1); //현재달의 달력을 주는데 +1해서 다음달로간다.
        recalculate();
        resetDayNumbers();//객체를 다 만든다 42개
        //무슨 의미일까 ??
        selectedPosition = -1 ;
    }


}
