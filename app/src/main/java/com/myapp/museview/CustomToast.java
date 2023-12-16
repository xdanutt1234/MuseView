package com.myapp.museview;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class CustomToast {

    private final Activity activity;
    private final String message;
    private final float xCoordinate;
    private final float yCoordinate;

    public CustomToast(Activity activity, String message, float xCoordinate, float yCoordinate) {
        this.activity = activity;
        this.message = message;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public void show() {
        View toastView = LayoutInflater.from(activity).inflate(R.layout.custom_toast_layout, null);

        // Set the message
        TextView textView = toastView.findViewById(R.id.toast_message);
        textView.setText(message);

        // Create and configure the WindowManager
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );
        params.gravity = Gravity.TOP | Gravity.START;
        params.x = Math.round(xCoordinate);
        params.y = Math.round(yCoordinate);

        // Add the view to the WindowManager
        WindowManager windowManager = activity.getWindowManager();
        if (windowManager != null) {
            windowManager.addView(toastView, params);

            // Set a delay to remove the view after a certain time (e.g., 2000 milliseconds)
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                windowManager.removeView(toastView);
            }, 2000);
        }
    }
}