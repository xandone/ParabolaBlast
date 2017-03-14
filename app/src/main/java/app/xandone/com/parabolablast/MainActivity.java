package app.xandone.com.parabolablast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ParabolaView.AnimEndInterface {
    private RelativeLayout rl;
    private ParabolaView parabolaView;
    private BoomView boomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boomView = (BoomView) findViewById(R.id.boomView);
        rl = (RelativeLayout) findViewById(R.id.rl);
        parabolaView = (ParabolaView) findViewById(R.id.parentPanel);
        rl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    parabolaView.startAnim((int) event.getX(), (int) event.getY());
                }
                return true;
            }
        });
    }

    @Override
    public void onDrawBall(List<LittleBall> littleBalls) {
        boomView.startAnim(littleBalls);
    }
}
