package app.xandone.com.parabolablast;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by xandone on 2017/3/6.
 */
public class LittleBall {
    private int mStart_X;
    private int mStart_Y;
    private int mEnd_X;
    private int mEnd_Y;
    private int mRadius;
    private int mColor;
    private int mControl_X;
    private int mControl_Y;
    private int mView_w, mView_h;//View的宽高
    private int mView_endX, mView_endY;//View的宽高
    private Paint mPaint;

    public LittleBall(int endX, int endY, int w, int h, Paint paint, int color) {
        this.mView_endX = endX;
        this.mView_endY = endY;
        this.mView_w = w;
        this.mView_h = h;
        this.mPaint = paint;
        this.mColor = color;
        init();
    }

    public void init() {
        this.mStart_X = Utils.randomIntPositive(mView_endX + mView_w, mView_endX);
        this.mStart_Y = Utils.randomIntPositive(mView_endY + mView_h, mView_endY);
        this.mEnd_X = Utils.randomInt(2 * mView_w);
        this.mEnd_Y = Utils.randomInt(2 * mView_h);
        this.mRadius = Utils.randomIntPositive(40, 20);
        mControl_X = (mStart_X + mEnd_X) / 2;
        mControl_Y = mStart_Y - 200;
    }

    public void drawBall(Canvas canvas) {
        canvas.drawCircle(90, 90, 50, mPaint);
        Log.d("balldgfgfdg", mStart_X + "     " + mStart_Y + "    " + mRadius);
    }

    public int getmStart_X() {
        return mStart_X;
    }

    public void setmStart_X(int mStart_X) {
        this.mStart_X = mStart_X;
    }

    public int getmStart_Y() {
        return mStart_Y;
    }

    public void setmStart_Y(int mStart_Y) {
        this.mStart_Y = mStart_Y;
    }

    public int getmEnd_X() {
        return mEnd_X;
    }

    public void setmEnd_X(int mEnd_X) {
        this.mEnd_X = mEnd_X;
    }

    public int getmEnd_Y() {
        return mEnd_Y;
    }

    public void setmEnd_Y(int mEnd_Y) {
        this.mEnd_Y = mEnd_Y;
    }

    public int getmRadius() {
        return mRadius;
    }

    public void setmRadius(int mRadius) {
        this.mRadius = mRadius;
    }

    public int getmColor() {
        return mColor;
    }

    public void setmColor(int mColor) {
        this.mColor = mColor;
    }

    public int getmControl_X() {
        return mControl_X;
    }

    public void setmControl_X(int mControl_X) {
        this.mControl_X = mControl_X;
    }

    public int getmControl_Y() {
        return mControl_Y;
    }

    public void setmControl_Y(int mControl_Y) {
        this.mControl_Y = mControl_Y;
    }

    public int getmView_w() {
        return mView_w;
    }

    public void setmView_w(int mView_w) {
        this.mView_w = mView_w;
    }

    public int getmView_h() {
        return mView_h;
    }

    public void setmView_h(int mView_h) {
        this.mView_h = mView_h;
    }

    public int getmView_endX() {
        return mView_endX;
    }

    public void setmView_endX(int mView_endX) {
        this.mView_endX = mView_endX;
    }

    public int getmView_endY() {
        return mView_endY;
    }

    public void setmView_endY(int mView_endY) {
        this.mView_endY = mView_endY;
    }

    public Paint getmPaint() {
        return mPaint;
    }

    public void setmPaint(Paint mPaint) {
        this.mPaint = mPaint;
    }
}
