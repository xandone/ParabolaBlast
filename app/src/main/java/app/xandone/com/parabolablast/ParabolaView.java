package app.xandone.com.parabolablast;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xandone on 2017/3/6.
 */
public class ParabolaView extends ImageView {
    private AnimEndInterface mAnimEndInterface;
    private Context mContext;
    private int mDefaultSize;
    private int mWidth, mHeight;
    private int mStart_X;
    private int mStart_Y;
    private int mEnd_X;
    private int mEnd_Y;
    private int mControl_X;
    private int mControl_Y;

    private float mAnimValue;
    private ValueAnimator mValueAnimator;

    private Bitmap mBitmap;
    private Paint mBallPaint;
    private int ballCount;
    private List<LittleBall> ballList;

    public ParabolaView(Context context) {
        this(context, null);
    }

    public ParabolaView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParabolaView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    public void init() {
        ballCount = 40;

        mBallPaint = new Paint();
        mBallPaint.setStyle(Paint.Style.FILL);
        ballList = new ArrayList<>();

        mDefaultSize = Utils.dp2px(mContext, 60);
        mValueAnimator = ValueAnimator.ofFloat(0, 1f);
        mValueAnimator.setDuration(1200);
        mValueAnimator.setInterpolator(new AccelerateInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimValue = (float) animation.getAnimatedValue();
                setXY();
                invalidate();
            }
        });
        mValueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                createBalls();
                resetView();
                setOnAnimEndInterface(mAnimEndInterface);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = measureDimension(widthMeasureSpec);
        int h = measureDimension(heightMeasureSpec);
        setMeasuredDimension(w, h);
    }

    public int measureDimension(int measureDir) {
        int size;
        int specMode = MeasureSpec.getMode(measureDir);
        int specSize = MeasureSpec.getSize(measureDir);

        switch (specMode) {
            case MeasureSpec.AT_MOST:
                size = Math.min(mDefaultSize, specSize);
                break;
            case MeasureSpec.EXACTLY:
                size = specSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                size = mDefaultSize;
                break;
            default:
                size = mDefaultSize;
                break;
        }
        return size;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;
        mStart_X = (int) getX();
        mStart_Y = (int) getY();
        mBitmap = Utils.createBitmapFromView(this);
    }

    public void startAnim(int x, int y) {
        mEnd_X = x;
        mEnd_Y = y;
        mControl_X = (mStart_X + mEnd_X) / 2;
        mControl_Y = mStart_Y - Utils.dp2px(mContext, 300);
        mValueAnimator.start();
    }

    public void setXY() {
        this.setX((1 - mAnimValue) * (1 - mAnimValue) * mStart_X + 2 * mAnimValue * (1 - mAnimValue) * mControl_X + mAnimValue * mAnimValue * mEnd_X);
        this.setY((1 - mAnimValue) * (1 - mAnimValue) * mStart_Y + 2 * mAnimValue * (1 - mAnimValue) * mControl_Y + mAnimValue * mAnimValue * mEnd_Y);
    }

    public void resetView() {
        this.setX(mStart_X);
        this.setY(mStart_Y);
    }

    public void createBalls() {
        if (ballList != null) {
            ballList.clear();
        }
        for (int i = 0; i < ballCount; i++) {
            ballList.add(new LittleBall(mEnd_X, mEnd_Y, mWidth, mHeight, mBallPaint,
                    mBitmap.getPixel(Utils.randomIntPositive(mBitmap.getWidth() - 1, 0), Utils.randomIntPositive(mBitmap.getHeight() - 1, 0))));
            Log.d("xandone", mEnd_X + "    " + mEnd_Y + "    " + mWidth + "    " + mHeight + "    ");
        }
    }

    public void setOnAnimEndInterface(AnimEndInterface animEndInterface) {
        animEndInterface.onDrawBall(ballList);
    }

    public interface AnimEndInterface {
        void onDrawBall(List<LittleBall> littleBalls);
    }

    public AnimEndInterface getmAnimEndInterface() {
        return mAnimEndInterface;
    }

    public void setmAnimEndInterface(AnimEndInterface mAnimEndInterface) {
        this.mAnimEndInterface = mAnimEndInterface;
    }
}