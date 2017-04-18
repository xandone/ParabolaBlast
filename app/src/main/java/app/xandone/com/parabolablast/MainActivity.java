package app.xandone.com.parabolablast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout rl;
    private ParabolaView parabolaView;
    private BoomView boomView;
    private AnimEnd mAnimEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAnimEnd = new AnimEnd();
        boomView = (BoomView) findViewById(R.id.boomView);
        rl = (RelativeLayout) findViewById(R.id.rl);
        parabolaView = (ParabolaView) findViewById(R.id.parentPanel);
        parabolaView.setmAnimEndInterface(mAnimEnd);
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

    class AnimEnd implements ParabolaView.AnimEndInterface {

        @Override
        public void onDrawBall(List<LittleBall> littleBalls) {
            boomView.startAnim(littleBalls);
        }
    }
}
