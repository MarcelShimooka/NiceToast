package com.marcelshimooka.example.nicetoast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.marcelshimooka.nicetoast.NiceToast;

public class DemoActivity extends AppCompatActivity implements View.OnClickListener {

    //region Fields
    private Button btnSimpleMessage;
    private Button btnSuccessMessage;
    private Button btnWarningMessage;
    private Button btnErrorMessage;
    private Button btnTopMessage;
    private Button btnMiddleMessage;
    private Button btnLongDurationMessage;
    //endregion

    //region Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        btnSimpleMessage = (Button) findViewById(R.id.activity_demo_btn_simple_message);
        btnSuccessMessage = (Button) findViewById(R.id.activity_demo_btn_success_message);
        btnWarningMessage = (Button) findViewById(R.id.activity_demo_btn_warning_message);
        btnErrorMessage = (Button) findViewById(R.id.activity_demo_btn_error_message);
        btnTopMessage = (Button) findViewById(R.id.activity_demo_btn_top_message);
        btnMiddleMessage = (Button) findViewById(R.id.activity_demo_btn_middle_message);
        btnLongDurationMessage = (Button) findViewById(R.id.activity_demo_btn_long_duration_message);

        setupListeners();
    }
    //endregion

    //region Methods

    //region Overridden methods
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_demo_btn_simple_message:
                new NiceToast.Builder(getApplicationContext())
                        .withMessage(R.string.simple_message)
                        .build()
                        .show();
                break;
            case R.id.activity_demo_btn_success_message:
                new NiceToast.Builder(getApplicationContext())
                        .withMessage(R.string.success_message)
                        .withTheme(NiceToast.THEME_SUCCESS)
                        .build()
                        .show();
                break;
            case R.id.activity_demo_btn_warning_message:
                new NiceToast.Builder(getApplicationContext())
                        .withMessage(R.string.warning_message)
                        .withTheme(NiceToast.THEME_WARNING)
                        .build()
                        .show();
                break;
            case R.id.activity_demo_btn_error_message:
                new NiceToast.Builder(getApplicationContext())
                        .withMessage(R.string.error_message)
                        .withTheme(NiceToast.THEME_ERROR)
                        .build()
                        .show();
                break;
            case R.id.activity_demo_btn_top_message:
                new NiceToast.Builder(getApplicationContext())
                        .withMessage(R.string.top_message)
                        .withPosition(NiceToast.POSITION_TOP)
                        .build()
                        .show();
                break;
            case R.id.activity_demo_btn_middle_message:
                new NiceToast.Builder(getApplicationContext())
                        .withMessage(R.string.middle_message)
                        .withPosition(NiceToast.POSITION_CENTER)
                        .build()
                        .show();
                break;
            case R.id.activity_demo_btn_long_duration_message:
                new NiceToast.Builder(getApplicationContext())
                        .withMessage(R.string.long_duration_message)
                        .withDuration(NiceToast.DURATION_LONG)
                        .build()
                        .show();
                break;
        }
    }
    //endregion

    //region Private methods
    private void setupListeners() {
        btnSimpleMessage.setOnClickListener(this);
        btnSuccessMessage.setOnClickListener(this);
        btnWarningMessage.setOnClickListener(this);
        btnErrorMessage.setOnClickListener(this);
        btnTopMessage.setOnClickListener(this);
        btnMiddleMessage.setOnClickListener(this);
        btnLongDurationMessage.setOnClickListener(this);
    }
    //endregion

    //endregion
}
