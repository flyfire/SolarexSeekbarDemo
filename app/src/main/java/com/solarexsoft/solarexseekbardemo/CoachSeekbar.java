package com.solarexsoft.solarexseekbardemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 14:23/2018/11/29
 *    Desc:
 * </pre>
 */

public class CoachSeekbar extends LinearLayout implements View.OnClickListener, SeekBar
        .OnSeekBarChangeListener {
    private static final int DEFAULT_MAX_PROGRESS = 100;
    private static final int DEFAULT_MIN_PROGRESS = 0;
    private static final int DEFAULT_STEP_PROGRESS = 1;
    private static final int DEFAULT_CURRENT_PROGRESS = 50;

    private int mMaxProgress;
    private int mMinProgress;
    private int mStepProgress;
    private int mCurrentProgress;
    private int mDesiredMax;

    ImageView mMinus, mAdd;
    SeekBar mSeekBar;

    OnProgressChangeListener mListener;

    public CoachSeekbar(Context context) {
        this(context, null);
    }

    public CoachSeekbar(Context context, @android.support.annotation.Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CoachSeekbar(Context context, @android.support.annotation.Nullable AttributeSet attrs,
                        int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CoachSeekbar);
        mMaxProgress = typedArray.getInteger(R.styleable.CoachSeekbar_maxProgress,
                DEFAULT_MAX_PROGRESS);
        mMinProgress = typedArray.getInteger(R.styleable.CoachSeekbar_minProgress,
                DEFAULT_MIN_PROGRESS);
        mCurrentProgress = typedArray.getInteger(R.styleable.CoachSeekbar_currentProgress,
                DEFAULT_CURRENT_PROGRESS);
        mStepProgress = typedArray.getInteger(R.styleable.CoachSeekbar_stepProgress, DEFAULT_STEP_PROGRESS);
        typedArray.recycle();
        LayoutInflater.from(context).inflate(R.layout.layout_seekbar, this, true);
        mMinus = findViewById(R.id.iv_minus);
        mAdd = findViewById(R.id.iv_add);
        mSeekBar = findViewById(R.id.sb_progress);
        mMinus.setOnClickListener(this);
        mAdd.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(this);
        mDesiredMax = mMaxProgress - mMinProgress;
        mSeekBar.setMax(mDesiredMax);
        mSeekBar.setProgress(mCurrentProgress - mMinProgress);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        int currentProgress = mSeekBar.getProgress();
        int newProgress = 0;
        if (viewId == R.id.iv_minus) {
            newProgress = currentProgress - mStepProgress;
            checkProgress(newProgress);
        } else if (viewId == R.id.iv_add){
            newProgress = currentProgress + mStepProgress;
            checkProgress(newProgress);
        }
    }

    private void checkProgress(int progress) {
        if (progress < 0) {
            progress = 0;
        }
        if (progress > mDesiredMax) {
            progress = mDesiredMax;
        }
        mSeekBar.setProgress(progress);
        if (mListener != null) {
            mListener.onProgressChanged(progress + mMinProgress);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int newProgress = mMinProgress + progress;
        if (mListener != null) {
            mListener.onProgressChanged(newProgress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void setMaxProgress(int maxProgress) {
        mMaxProgress = maxProgress;
        mDesiredMax = mMaxProgress - mMinProgress;
        mSeekBar.setMax(mDesiredMax);
    }

    public void setMinProgress(int minProgress) {
        mMinProgress = minProgress;
        mDesiredMax = mMaxProgress - mMinProgress;
        mSeekBar.setMax(mDesiredMax);
    }

    public void setStepProgress(int stepProgress) {
        mStepProgress = stepProgress;
    }

    public void setOnProgressChangeListener(OnProgressChangeListener listener) {
        mListener = listener;
    }

    interface OnProgressChangeListener {
        void onProgressChanged(int newProgress);
    }
}
