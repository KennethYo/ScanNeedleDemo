package cn.kenneth.scanneedledemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;

import java.lang.ref.WeakReference;

/**
 * Created by kenneth on 15/11/6.
 */
public class FontsSpan extends DynamicDrawableSpan {
    private final Context mContext;

    private final int mResourceId;

    private final int mSize;

    private final int mTextSize;

    private int mHeight;

    private int mWidth;

    private int mTop;

    private Drawable mDrawable;

    private WeakReference<Drawable> mDrawableRef;

    public FontsSpan(Context context, int resourceId, int size, int textSize) {
        super(DynamicDrawableSpan.ALIGN_BASELINE);
        mContext = context;
        mResourceId = resourceId;
        mWidth = mHeight = mSize = size;
        mTextSize = textSize;
    }

    public Drawable getDrawable() {
        if (mDrawable == null) {
            try {
                mDrawable = mContext.getResources().getDrawable(mResourceId);
                mHeight = mSize;
                mWidth = mHeight * mDrawable.getIntrinsicWidth() / mDrawable.getIntrinsicHeight();
                mTop = (mTextSize - mHeight) / 2;
                mDrawable.setBounds(0, mTop, mWidth, mTop + mHeight);
            } catch (Exception e) {
                // swallow
            }
        }
        return mDrawable;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        //super.draw(canvas, text, start, end, x, top, y, bottom, paint);
        Drawable b = getCachedDrawable();
        canvas.save();

        int transY = bottom - b.getBounds().bottom;
        if (mVerticalAlignment == ALIGN_BASELINE) {
            transY = top + ((bottom - top) / 2) - ((b.getBounds().bottom - b.getBounds().top) / 2) - mTop;
        }

        canvas.translate(x, transY);
        b.draw(canvas);
        canvas.restore();
    }

    private Drawable getCachedDrawable() {
        if (mDrawableRef == null || mDrawableRef.get() == null) {
            mDrawableRef = new WeakReference<Drawable>(getDrawable());
        }
        return mDrawableRef.get();
    }
}

