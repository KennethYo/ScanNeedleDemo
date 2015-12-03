package cn.kenneth.scanneedledemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * 扫描针
 */
public class ScanNeedleView extends View {
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 300;

    private Bitmap mNeedleBitmap;
    private NeedleGravityMode mNeedleGravityMode;
    private Paint mNeedlePaint;
    private int mNeedleBitmapHeight;
    private int mContentWidth;
    private int mContentHeight;
    private int mNeedleMove = 1;
    private boolean mShowNeedle = true;
    /**
     * 扫描是否向上
     */
    private boolean mNeedleUp = true;
    /**
     * 是否自动扫描
     */
    private boolean mNeedleAutoMove = true;
    /**
     * 扫描的上边界
     */
    private int mStartY;
    /**
     * 扫描的下边界
     */
    private int mEndY;
    /**
     * 扫描针的 y 轴位置
     */
    private int mNeedlePositionY;
    private Rect mNeedleRect;

    /**
     * 扫描开始位置
     */
    public enum NeedleGravityMode {
        /**
         * 头部
         */
        TOP(0x0),
        /**
         * 中间
         */
        CENTER(0x1),
        /**
         * 底部
         */
        BOTTOM(0x2),;

        public static NeedleGravityMode mapInt2Value(int modeInt) {
            if (modeInt == 0x0) {
                return TOP;
            }
            if (modeInt == 0x1) {
                return CENTER;
            }
            if (modeInt == 0x2) {
                return BOTTOM;
            }
            return CENTER;
        }

        private int mInt;

        /**
         * 默认扫描开始位置
         *
         * @return
         */
        public static NeedleGravityMode getDefault() {
            return CENTER;
        }

        NeedleGravityMode(int mode) {
            mInt = mode;
        }
    }

    public ScanNeedleView(Context context) {
        super(context);
        init(null, 0);
    }

    public ScanNeedleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ScanNeedleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.ScanNeedleView, defStyle, 0);

        if (a.hasValue(R.styleable.ScanNeedleView_needleGravity)) {
            mNeedleGravityMode = NeedleGravityMode.mapInt2Value(a.getInt(R.styleable.ScanNeedleView_needleGravity, 0x1));
        } else {
            mNeedleGravityMode = NeedleGravityMode.getDefault();
        }

        mNeedleMove = a.getInt(R.styleable.ScanNeedleView_needleMove, 5);
        a.recycle();
        //初始化扫描针
        mNeedleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_needle);
        if (mNeedleBitmap != null) {
            mNeedleBitmapHeight = mNeedleBitmap.getHeight();
        }

        mNeedlePaint = new Paint();
        mNeedlePaint.setAntiAlias(true);

        mNeedleRect = new Rect();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width;
        int height;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_WIDTH, getResources().getDisplayMetrics());
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_HEIGHT, getResources().getDisplayMetrics());
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        calculateNeedlePositon();
    }

    private void calculateNeedlePositon() {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        mContentWidth = getWidth() - paddingLeft - paddingRight;
        mContentHeight = getHeight() - paddingTop - paddingBottom;

        mStartY = paddingTop;
        mEndY = getHeight() - paddingBottom - mNeedleBitmapHeight;

        if (mNeedleGravityMode == NeedleGravityMode.TOP) {
            mNeedlePositionY = mStartY;
        }

        if (mNeedleGravityMode == NeedleGravityMode.CENTER) {
            mNeedlePositionY = (getHeight() - mNeedleBitmapHeight) / 2;
        }

        if (mNeedleGravityMode == NeedleGravityMode.BOTTOM) {
            mNeedlePositionY = getHeight() - paddingBottom - mNeedleBitmapHeight;
        }


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mNeedleBitmap != null && mShowNeedle) {

            mNeedleRect.set(getPaddingLeft(), mNeedlePositionY, getPaddingLeft() + mContentWidth, mNeedlePositionY + mNeedleBitmapHeight);
            canvas.drawBitmap(mNeedleBitmap, null, mNeedleRect, mNeedlePaint);


            if (mNeedlePositionY <= mStartY) {
                mNeedleUp = false;
            }
            if (mNeedlePositionY >= mEndY) {
                mNeedleUp = true;
            }
            mNeedlePositionY = mNeedleUp ? mNeedlePositionY - mNeedleMove : mNeedlePositionY + mNeedleMove;

            if (mNeedleAutoMove) {
                invalidate();
            }
        }

    }

    public void setNeedleGravityMode(NeedleGravityMode mode) {
        this.mNeedleGravityMode = mode;
        calculateNeedlePositon();
        invalidate();
    }

    public void setNeedleMove(int move) {
        this.mNeedleMove = move > 1 && move < mContentHeight ? move : 1;
    }

    public void setNeedleAutoMove(boolean b) {
        this.mNeedleAutoMove = b;
        invalidate();
    }

    public void showNeedle(boolean b) {
        this.mShowNeedle = b;
        invalidate();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mNeedleBitmap != null) {
            mNeedleBitmap.recycle();
            mNeedleBitmap = null;
        }
    }
}
