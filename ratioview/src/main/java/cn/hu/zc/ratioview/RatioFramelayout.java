package cn.hu.zc.ratioview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IntRange;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import cn.hu.zc.RatioUtils;


public class RatioFramelayout extends AppCompatImageView {

    private float ratio;
    private @IntRange(from = 0, to = 2)
    int known = 0;//0=未知,1=宽已知,2=高已知

    public RatioFramelayout(Context context) {
        this(context, null);
    }

    public RatioFramelayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatioFramelayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RatioFramelayout);
        ratio = array.getFloat(R.styleable.RatioFramelayout_ratio, 1);
        known = array.getInt(R.styleable.RatioFramelayout_known, 0);
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
