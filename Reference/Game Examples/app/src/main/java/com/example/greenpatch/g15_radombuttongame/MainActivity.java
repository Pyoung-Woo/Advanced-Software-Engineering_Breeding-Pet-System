package com.example.greenpatch.g15_radombuttongame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_1, btn_2, btn_3, btn_4, btn_restart;
    TextView tv_result;
    int ran = (int) (Math.random() * 4) + 1;//1~4사이의 랜덤변수 생성

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_restart = (Button) findViewById(R.id.btn_restart);
        tv_result = (TextView) findViewById(R.id.tv_result);

        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_restart.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //버튼의 tag 속성을 이용한것
        int n = Integer.parseInt(v.getTag().toString());//누른 버튼의 태그를 가져와 n변수에 넣음

        if (n == 5) {//재시작 버튼을 눌렀을 경우
            ran = (int) (Math.random() * 4) + 1;//재시작의 경우 랜덤변수를 다시 생성한다.
            tv_result.setText("결과");
            btn_restart.setVisibility(View.INVISIBLE);//버튼을 숨긴다.
        } else {
            if (ran == n) {//누른버튼이 정답일 경우
                tv_result.setText("정답입니다.");
                btn_restart.setVisibility(View.VISIBLE);//재시작버튼을 보이게 만든다.
            } else {//누른버튼이 틀렸을경우
                tv_result.setText("실패");
                btn_restart.setVisibility(View.VISIBLE);//재시작버튼을 보이게 만든다.

            }
        }
    }
}
