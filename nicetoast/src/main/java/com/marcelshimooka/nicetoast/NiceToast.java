/**
 * Copyright (C) 2016 Marcel Shimooka
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.marcelshimooka.nicetoast;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * A simple custom toast
 */
public class NiceToast {

    //region FIELDS
    public static final int THEME_DEFAULT = 0;
    public static final int THEME_SUCCESS = 1;
    public static final int THEME_WARNING = 2;
    public static final int THEME_ERROR = 3;

    public static final int DURATION_SHORT = Toast.LENGTH_SHORT;
    public static final int DURATION_LONG = Toast.LENGTH_LONG;

    public static final int POSITION_TOP = Gravity.TOP;
    public static final int POSITION_CENTER = Gravity.CENTER;
    public static final int POSITION_BOTTOM = Gravity.BOTTOM;

    private Context mContext;
    private int mMessageResId;
    private int mIconResId;
    private int mTextColorId;
    private int mBackgroundResId;
    @Theme
    private int mThemeId;
    @Position
    private int mPosition;
    @Duration
    private int mDuration;

    private Toast mToast;
    private TextView tvwMessage;
    //endregion

    //region CONSTRUCTORS
    public NiceToast(Context context, int messageResId) {
        mContext = context;
        tvwMessage = new TextView(mContext);
        mToast = new Toast(mContext);

        mMessageResId = messageResId;
        tvwMessage.setGravity(Gravity.CENTER_VERTICAL);
        mPosition = POSITION_BOTTOM;
        mDuration = DURATION_SHORT;
    }
    //endregion

    //region PROPERTIES
    public void setThemeId(@Theme int themeId) {
        this.mThemeId = themeId;
    }

    public void setPosition(@Position int position) {
        this.mPosition = position;
    }

    public void setDuration(@Duration int duration) {
        this.mDuration = duration;
    }
    //endregion

    //region METHODS

    //region PUBLIC METHODS
    public void show() {
        applyTheme();

        tvwMessage.setText(mMessageResId);
        mToast.setGravity(mPosition, 0, getYOffset());
        mToast.setDuration(mDuration);
        mToast.setView(tvwMessage);
        mToast.show();
    }
    //endregion

    //region PRIVATE METHODS
    private void applyTheme() {
        switch (mThemeId) {
            case THEME_DEFAULT:
                mIconResId = 0;
                mTextColorId = ContextCompat.getColor(mContext, R.color.nice_toast_default);
                mBackgroundResId = R.drawable.nice_toast_bg_default;
                break;
            case THEME_SUCCESS:
                mIconResId = R.drawable.ic_nice_toast_success;
                mTextColorId = ContextCompat.getColor(mContext, R.color.nice_toast_success);
                mBackgroundResId = R.drawable.nice_toast_bg_success;
                break;
            case THEME_WARNING:
                mIconResId = R.drawable.ic_nice_toast_warning;
                mTextColorId = ContextCompat.getColor(mContext, R.color.nice_toast_warning);
                mBackgroundResId = R.drawable.nice_toast_bg_warning;
                break;
            case THEME_ERROR:
                mIconResId = R.drawable.ic_nice_toast_error;
                mTextColorId = ContextCompat.getColor(mContext, R.color.nice_toast_error);
                mBackgroundResId = R.drawable.nice_toast_bg_error;
                break;
        }

        if (mIconResId != 0) {
            tvwMessage.setCompoundDrawablesWithIntrinsicBounds(mIconResId, 0, 0, 0);
            tvwMessage.setCompoundDrawablePadding(mContext.getResources().getDimensionPixelSize(R.dimen.nice_toast_icon_padding));
        }

        tvwMessage.setTextColor(mTextColorId);
        tvwMessage.setBackgroundResource(mBackgroundResId);
    }

    private int getYOffset() {
        int offset = 0;

        if (mPosition != POSITION_CENTER) {
            offset = mContext.getResources().getDimensionPixelSize(R.dimen.nice_toast_y_offset);
        }

        return offset;
    }
    //endregion

    //endregion

    //region INTERFACES
    @IntDef({THEME_DEFAULT, THEME_SUCCESS, THEME_WARNING, THEME_ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Theme {
    }

    @IntDef({DURATION_SHORT, DURATION_LONG})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    @IntDef({POSITION_TOP, POSITION_BOTTOM, POSITION_CENTER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Position {
    }
    //endregion

    //region INNER CLASS
    public static class Builder {
        private NiceToast instance;

        public Builder(Context context, int messageResId) {
            instance = new NiceToast(context, messageResId);
        }

        public Builder theme(@Theme int themeId) {
            instance.setThemeId(themeId);
            return this;
        }

        public Builder position(@Position int position) {
            instance.setPosition(position);
            return this;
        }

        public Builder duration(@Duration int duration) {
            instance.setDuration(duration);
            return this;
        }

        public NiceToast build() {
            return instance;
        }
    }
    //endregion
}