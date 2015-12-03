package cn.kenneth.scanneedledemo;

import android.content.Context;
import android.text.Spannable;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kenneth on 15/11/6.
 */
public class FontsHandler {

    private static final Map<String, Integer> mFonts = new HashMap<>(12);

    static {
        mFonts.put("分", R.drawable.ic_face_rate);
        mFonts.put("0", R.drawable.ic_face_rate_0);
        mFonts.put("1", R.drawable.ic_face_rate_1);
        mFonts.put("2", R.drawable.ic_face_rate_2);
        mFonts.put("3", R.drawable.ic_face_rate_3);
        mFonts.put("4", R.drawable.ic_face_rate_4);
        mFonts.put("5", R.drawable.ic_face_rate_5);
        mFonts.put("6", R.drawable.ic_face_rate_6);
        mFonts.put("7", R.drawable.ic_face_rate_7);
        mFonts.put("8", R.drawable.ic_face_rate_8);
        mFonts.put("9", R.drawable.ic_face_rate_9);
        mFonts.put("balala", R.drawable.ic_face_rate_balala);
    }

    public static boolean addFonts(Context context, Spannable spannable, int size, int textSize) {
        boolean hasChanges = false;
        for (Map.Entry<String, Integer> entry : mFonts.entrySet()) {
            String key = entry.getKey();
            Matcher matcher = Pattern.compile(Pattern.quote(key)).matcher(spannable);
            while (matcher.find()) {
                boolean set = true;
                for (FontsSpan span : spannable.getSpans(matcher.start(),
                        matcher.end(), FontsSpan.class))
                    if (spannable.getSpanStart(span) >= matcher.start()
                            && spannable.getSpanEnd(span) <= matcher.end())
                        spannable.removeSpan(span);
                    else {
                        set = false;
                        break;
                    }
                if (set) {
                    hasChanges = true;
                    spannable.setSpan(new FontsSpan(context, entry.getValue(), size, textSize),
                            matcher.start(), matcher.end(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }
        return hasChanges;
    }
}
