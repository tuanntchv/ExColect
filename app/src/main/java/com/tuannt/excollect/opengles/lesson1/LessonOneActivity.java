package com.tuannt.excollect.opengles.lesson1;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.tuannt.excollect.BaseActivity;

/**
 * Comment
 *
 * @author TuanNT
 */
public class LessonOneActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLSurfaceView glSurfaceView = new GLSurfaceView(this);
        glSurfaceView.setEGLContextClientVersion(2);
        glSurfaceView.setRenderer(new LessonOneRender());
        setContentView(glSurfaceView);
    }
}
