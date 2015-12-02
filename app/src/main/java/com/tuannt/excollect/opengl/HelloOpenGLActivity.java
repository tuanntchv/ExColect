package com.tuannt.excollect.opengl;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.tuannt.excollect.BaseActivity;

/**
 * Comment
 *
 * @author TuanNT
 */
public class HelloOpenGLActivity extends BaseActivity {
    private GLSurfaceView mGlSurfaceView;
    private boolean mIsRenderSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGlSurfaceView = new GLSurfaceView(this);
        mGlSurfaceView.setEGLContextClientVersion(2);
        mGlSurfaceView.setRenderer(new HelloOpenGLRender());
        setContentView(mGlSurfaceView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGlSurfaceView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGlSurfaceView.onResume();
    }
}
