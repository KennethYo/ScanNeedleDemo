package cn.kenneth.scanneedledemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ScanNeedleView mScanNeedleView;
    private FaceRateTextView mFaceRateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScanNeedleView = (ScanNeedleView) findViewById(R.id.view);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScanNeedleView.setNeedleGravityMode(ScanNeedleView.NeedleGravityMode.TOP);
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScanNeedleView.setNeedleGravityMode(ScanNeedleView.NeedleGravityMode.CENTER);
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScanNeedleView.setNeedleGravityMode(ScanNeedleView.NeedleGravityMode.BOTTOM);
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScanNeedleView.setNeedleMove(1);
            }
        });
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScanNeedleView.setNeedleMove(5);
            }
        });
        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScanNeedleView.setNeedleMove(10);
            }
        });
        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScanNeedleView.setNeedleAutoMove(true);
            }
        });
        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScanNeedleView.setNeedleAutoMove(false);
            }
        });
        findViewById(R.id.button9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScanNeedleView.showNeedle(true);
            }
        });
        findViewById(R.id.button10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScanNeedleView.showNeedle(false);
            }
        });
        mFaceRateView = (FaceRateTextView) findViewById(R.id.view2);
        findViewById(R.id.button11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFaceRateView.setText("1分");
            }
        });
        findViewById(R.id.button12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFaceRateView.setText("60分");
            }
        });
        findViewById(R.id.button13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFaceRateView.setText("100分");
            }
        });
        findViewById(R.id.button14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFaceRateView.setText("balala");
            }
        });
    }
}
