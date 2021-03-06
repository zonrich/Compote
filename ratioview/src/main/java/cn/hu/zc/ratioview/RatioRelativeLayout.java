package cn.hu.zc.ratioview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IntRange;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import cn.hu.zc.RatioUtils;

public class RatioRelativeLayout extends RelativeLayout {

    private float ratio;
    private @IntRange(from = 0, to = 2)
    int known = 0;//0=未知,1=宽已知,2=高已知

    public RatioRelativeLayout(Context context) {
        this(context, null);
    }

    public RatioRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatioRelativeLayout(Context context, AttributeSet attrs,
                               int defStyle) {
        super(context, attrs, defStyle);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RatioRelativeLayout);
        ratio = array.getFloat(R.styleable.RatioRelativeLayout_ratio, 1);
        known = array.getInt(R.styleable.RatioRelativeLayout_known, 0);
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = widthMeasureSpec;
        int height = heightMeasureSpec;
        if (ratio != 0) {
            switch (known) {
                case 1:
                    height = RatioUtils.X2Y(width, ratio);
                    break;
                case 2:
                    width = RatioUtils.Y2X(height, ratio);
                    break;
            }
        }

        super.onMeasure(width, height);
    }

}
