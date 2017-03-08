package app.xandone.com.parabolablast;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

/**
 * Created by xandone on 2017/3/6.
 */
public class ParabolaView extends ImageView {
    private Context mContext;
    private int mDefaultSize;
    private int mWidth, mHeight;
    private int mStart_X;
    private int mStart_Y;
    private int mEnd_X;
    private int mEnd_Y;
    private int mControl_X;
    private int mControl_Y;

    private Bitmap mBitmapBuffer;
    private Canvas mCanvas;
    private Paint mPaint;
    private float mBuff_X, mBuff_Y;

    private float mAnimValue;

    private ValueAnimator mValueAnimator;

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
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

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
//        mBitmapBuffer = Utils.createBitmapFromView(this).copy(Bitmap.Config.ARGB_4444, true);
//        mCanvas = new Canvas(mBitmapBuffer);
//        mBitmapBuffer = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_4444);
    }

    public void startAnim(int x, int y) {
        mEnd_X = x;
        mEnd_Y = y;
        mControl_X = (mStart_X + mEnd_X) / 2;
        mControl_Y = mStart_Y - Utils.dp2px(mContext, 200);
        mValueAnimator.start();
    }

    public void drawBit() {
        mBuff_X = getX();
        mBuff_Y = getY();
        mCanvas.drawBitmap(mBitmapBuffer, mBuff_X, mBuff_Y, mPaint);
    }

    public void setXY() {
        this.setX((1 - mAnimValue) * (1 - mAnimValue) * mStart_X + 2 * mAnimValue * (1 - mAnimValue) * mControl_X + mAnimValue * mAnimValue * mEnd_X);
        this.setY((1 - mAnimValue) * (1 - mAnimValue) * mStart_Y + 2 * mAnimValue * (1 - mAnimValue) * mControl_Y + mAnimValue * mAnimValue * mEnd_Y);
    }

}