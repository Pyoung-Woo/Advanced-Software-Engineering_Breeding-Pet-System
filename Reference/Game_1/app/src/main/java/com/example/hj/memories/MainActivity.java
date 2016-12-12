package com.example.hj.memories;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends ActionBarActivity {

    ProcessThread pThread;
    SendMessageHandler sendMsg = new SendMessageHandler();

    TableLayout tableLayout;
    TableRow tableRow[];
    TextView textViews[];

    LinearLayout statusLayout;
    TextView statusViews[];

    TextView levels;
    Button btn_start;

    int chk_index = 0;

    ArrayList<Integer> rand_num;
    ArrayList<Integer> temp_num;
    ArrayList<Integer> click_num;

    final int LIGHT_ON = 0;
    final int LIGHT_OFF = 1;
    final int TOUCH_ON = 2;
    final int TOUCH_OFF = 3;

    int level = 1;

    int gameSpeed = 600;
    final int MAX_SPEED = 1000;
    final int MIN_SPEED = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableLayout = (TableLayout) findViewById(R.id.game_pad);
        levels = (TextView) findViewById(R.id.level);
        btn_start = (Button) findViewById(R.id.btn_start);

        statusLayout = (LinearLayout) findViewById(R.id.status_layout);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                process_game();
            }
        });

        levels.setText(""+level);

    }
    public void onResume(){
        super.onResume();
        level = 1;
        levels.setText(""+level);
        btn_start.setVisibility(View.VISIBLE);

        statusLayout.removeAllViews();
        tableLayout.removeAllViews();
    }
    //스레드가 실행중일 때는 pad를 터치하지 못하도록 설정
    class ProcessThread extends Thread implements Runnable{
        boolean isStart = false;
        int cnt_rand_num = 1;

        public void ProcessThread(){ this.isStart = false; }
        public void StartProcess(){ this.isStart = true; }
        public void StopProcess(){ this.isStart = false; }
        public void run(){
            super.run();
            sendMsg.sendEmptyMessage(TOUCH_OFF);
            try {
                sleep(500);
                for(int get_index = 0; ; get_index++) {
                    temp_num.add(rand_num.get(get_index));

                    Message send_index = sendMsg.obtainMessage();
                    send_index.what = 0;
                    send_index.arg1 = get_index;
                    sendMsg.sendMessage(send_index);
                    sleep((int)(gameSpeed*0.8));

                    Message reset_index = sendMsg.obtainMessage();
                    reset_index.what = 1;
                    reset_index.arg1 = get_index;
                    sendMsg.sendMessage(reset_index);
                    sleep((int)(gameSpeed*0.2));

                    if(rand_num.size() == textViews.length*cnt_rand_num){
                        cnt_rand_num = cnt_rand_num + 1;
                        set_random_number();
                    }
                    if(temp_num.size() == level)  break;
                }
            } catch (InterruptedException e) { }
            sendMsg.sendEmptyMessage(TOUCH_ON);
        }

    }
    class SendMessageHandler extends Handler{
        public void handleMessage(Message msg){
            super.handleMessage(msg);

            int rev_index = msg.arg1;

            switch(msg.what){
                case LIGHT_ON:
                    textViews[rand_num.get(rev_index)].setBackgroundColor(Color.argb(255,255,0,0));
                    set_status_layout(rev_index);
                    break;
                case LIGHT_OFF:
                    textViews[rand_num.get(rev_index)].setBackgroundColor(Color.argb(255,255,255,255));
                    break;
                case TOUCH_OFF:
                    for(int i = 0; i < textViews.length; i++)
                        textViews[i].setClickable(false);
                    break;
                case TOUCH_ON:
                    for(int i = 0; i < textViews.length; i++)
                        textViews[i].setClickable(true);
                    break;
            }
        }
    }
    //랜덤하게 생성한 난수(index)를 가져오고,
    //해당 index의 TextView의 색을 깜빡이게 한다.
    //level이 TextView.length를 넘어가면 난수를 재생성
    //level이 증가할 때마다 호출
    public void process_game(){
        //onResume(){super.onResume();}
        btn_start.setVisibility(View.INVISIBLE);

        statusLayout.removeAllViews();
        statusViews = new TextView[level];

        set_layout(4);
        chk_index = 0;

        rand_num = new ArrayList<>();
        temp_num = new ArrayList<>();
        click_num = new ArrayList<>();

        set_random_number();

        pThread = new ProcessThread();
        pThread.StartProcess();
        pThread.start();
    }

    //게임이 종료되었을 때 이벤트
    //게임이 종료되면 레이아웃이 사라지고, 마지막에 클리어한 LEVEL까지만 alert로 보여줌
    public void game_over(){
        show_alert();

        onResume();
    }

    //게임이 진행중일 때, 몇개를 터치해야하는지 알려주는 layout...
    public void set_status_layout(int status_index){
        statusViews[status_index] = new TextView(this);
        statusViews[status_index].setTextSize(30);
        statusViews[status_index].setBackgroundColor(Color.GRAY);
        statusViews[status_index].setText(" ");

        statusLayout.addView(statusViews[status_index]);
    }

    public void show_alert(){
        int end_level = 1;
        if(level > 1) end_level = level - 1;

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("축하합니다").
                setMessage("LEVEL : "+ end_level ).
                setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        alert.show();
    }

    //현재 터치한 View의 index값(id)와 깜빡인 숫자의 순서가 모두 맞아야함.
    public void process_touch_chk(int click_index){
        if(temp_num.get(chk_index) != click_index) {
            game_over();
        } else {
            statusViews[chk_index].setText(" ");
            statusViews[chk_index].setBackgroundColor(Color.YELLOW);

            chk_index = chk_index + 1;

            if (chk_index == temp_num.size()) {
                level = level + 1;
                levels.setText("" + level);
                process_game();
            }
        }
    }

    //중복되지않는 난수를 생성하는 이벤트
    //level만큼만 생성되로고 함
    //random_index ??
    public void set_random_number(){
        for(int al_index = 0; al_index < textViews.length; al_index++){
            rand_num.add(al_index);
        }
        Collections.shuffle(rand_num);
    }

    //game_pad의 버튼들에 깜빡이는 효과를 주는 이벤트
    public void set_animation(View v){
        Animation alpha_ani = new AlphaAnimation(1, 0);
        alpha_ani.setDuration(200);
        v.setAnimation(alpha_ani);
        v.startAnimation(alpha_ani);
    }

    //터치가 가능한 게임판을 생성하기 위한 이벤트
    public void set_layout(int rows){
        int tvs = rows*rows;

        tableLayout.removeAllViews();

        tableRow = new TableRow[rows];
        textViews = new TextView[tvs];

        int tv_index = 0;

        for(int row_index = 0; row_index < rows; row_index++){
            tableRow[row_index] = new TableRow(this);
            TableLayout.LayoutParams tbl_params = new TableLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    1
            );
            tableRow[row_index].setLayoutParams(tbl_params);

            for(; tv_index < rows*(row_index+1); tv_index++){
                textViews[tv_index] = new TextView(this);
                TableRow.LayoutParams img_btn_params = new TableRow.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        1
                );
                img_btn_params.setMargins(5,5,5,5);
                textViews[tv_index].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        set_animation(view);
                        process_touch_chk(view.getId());
                    }
                });
                textViews[tv_index].setId(tv_index);
                textViews[tv_index].setBackgroundColor(Color.argb(255, 255, 255, 255));
                textViews[tv_index].setLayoutParams(img_btn_params);

                tableRow[row_index].addView(textViews[tv_index]);
            }
            tableLayout.addView(tableRow[row_index]);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        menu.add(0,0,0,"게임속도");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case 0:
                LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                final View layout = inflater.inflate(R.layout.alert_speed, null);

                SeekBar seekBar = (SeekBar) layout.findViewById(R.id.speed);

                seekBar.setMax(MAX_SPEED);
                seekBar.setProgress(gameSpeed);
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        TextView tv = (TextView) layout.findViewById(R.id.show_speed);
                        tv.setText("" + i);
                        gameSpeed = i;
                        if (i < 200) {
                            seekBar.setProgress(200);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        Toast.makeText(getApplicationContext(), "" + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setView(layout).
                        setTitle("게임속도")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //게임속도가 변경되면 게임이 초기화되도록 변경
                                //메인화면에서 수정할 경우에 새로운 메소드가 필요함
                            }
                        }).show();

                return true;
            case 1:

                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
