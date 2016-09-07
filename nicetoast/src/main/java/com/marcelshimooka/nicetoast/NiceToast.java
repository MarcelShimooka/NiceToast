/**
 * Copyright (C) 2016 Marcel Shimooka
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.marcelshimooka.nicetoast;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * A simple custom toast
 */
public class NiceToast extends Toast {

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
    private String mMessage;
    private int mIconResource;
    private int mTextColorResource;
    private int mBackgroundResource;
    @Theme
    private int mTheme;
    @Duration
    private int mDuration;
    @Position
    private int mPosition;

    private TextView tvwMessage;
    //endregion

    //region CONSTRUCTORS
    public NiceToast(Context context) {
        super(context);
        mContext = context;
        tvwMessage = new TextView(mContext);
        setDefaultValues();
    }
    //endregion

    //region PROPERTIES
    public Context getContext() {
        return mContext;
    }

    public void setMessage(String message) {
        this.mMessage = message;
    }

    public void setThemeId(@Theme int themeId) {
        this.mTheme = themeId;
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

        if (!TextUtils.isEmpty(mMessage)) {
            tvwMessage.setText(mMessage);
        }

        super.setGravity(mPosition, 0, getCustomYOffset());
        super.setDuration(mDuration);
        super.setView(tvwMessage);
        super.show();
    }
    //endregion

    //region PRIVATE METHODS
    private void setDefaultValues() {
        tvwMessage.setGravity(Gravity.CENTER_VERTICAL);
        mDuration = DURATION_SHORT;
        mPosition = POSITION_BOTTOM;
    }

    private void applyTheme() {
        switch (mTheme) {
            case THEME_DEFAULT:
                mIconResource = 0;
                mTextColorResource = ContextCompat.getColor(mContext, R.color.nice_toast_default);
                mBackgroundResource = R.drawable.nice_toast_bg_default;
                break;
            case THEME_SUCCESS:
                mIconResource = R.drawable.ic_nice_toast_success;
                mTextColorResource = ContextCompat.getColor(mContext, R.color.nice_toast_success);
                mBackgroundResource = R.drawable.nice_toast_bg_success;
                break;
            case THEME_WARNING:
                mIconResource = R.drawable.ic_nice_toast_warning;
                mTextColorResource = ContextCompat.getColor(mContext, R.color.nice_toast_warning);
                mBackgroundResource = R.drawable.nice_toast_bg_warning;
                break;
            case THEME_ERROR:
                mIconResource = R.drawable.ic_nice_toast_error;
                mTextColorResource = ContextCompat.getColor(mContext, R.color.nice_toast_error);
                mBackgroundResource = R.drawable.nice_toast_bg_error;
                break;
        }

        if (mIconResource != 0) {
            tvwMessage.setCompoundDrawablesWithIntrinsicBounds(mIconResource, 0, 0, 0);
            tvwMessage.setCompoundDrawablePadding(mContext.getResources().getDimensionPixelSize(R.dimen.nice_toast_icon_padding));
        }

        tvwMessage.setTextColor(mTextColorResource);
        tvwMessage.setBackgroundResource(mBackgroundResource);
    }

    private int getCustomYOffset() {
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

        public Builder(Context context) {
            instance = new NiceToast(context);
        }

        /**
         * Set the message using the given resource id.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder withMessage(@StringRes int messageId) {
            instance.setMessage(instance.getContext().getString(messageId));
            return this;
        }

        /**
         * Set the message displayed in the {@link NiceToast}.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder withMessage(String message) {
            instance.setMessage(message);
            return this;
        }

        /**
         * Set the theme used in the {@link NiceToast}.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder withTheme(@Theme int themeId) {
            instance.setThemeId(themeId);
            return this;
        }

        /**
         * Set the duration used in the {@link NiceToast}.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder withDuration(@Duration int duration) {
            instance.setDuration(duration);
            return this;
        }

        /**
         * Set the position used in the {@link NiceToast}.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder withPosition(@Position int position) {
            instance.setPosition(position);
            return this;
        }

        public NiceToast build() {
            return instance;
        }
    }
    //endregion
}