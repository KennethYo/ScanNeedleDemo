package cn.kenneth.scanneedledemo;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 脸部分值控件
 * Created by kenneth on 15/11/6.
 */
public class FaceRateTextView extends TextView {

    public FaceRateTextView(Context context) {
        super(context);
        init();
    }


    public FaceRateTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FaceRateTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (!TextUtils.isEmpty(getText())) {
            setText(getText());
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (!TextUtils.isEmpty(text)) {
            SpannableStringBuilder builder = new SpannableStringBuilder(text);
            FontsHandler.addFonts(getContext(), builder, (int) getTextSize(), (int) getTextSize());
            text = builder;
        }
        super.setText(text, type);
    }
}
