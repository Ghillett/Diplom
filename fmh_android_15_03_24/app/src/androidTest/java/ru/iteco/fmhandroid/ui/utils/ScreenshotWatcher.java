package ru.iteco.fmhandroid.ui.utils;

import static android.os.Environment.DIRECTORY_PICTURES;

import android.graphics.Bitmap;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.runner.screenshot.BasicScreenCaptureProcessor;
import androidx.test.runner.screenshot.ScreenCapture;
import androidx.test.runner.screenshot.Screenshot;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.IOException;

public class ScreenshotWatcher extends TestWatcher {

    @Override
    protected void failed(Throwable e, Description description) {
        super.failed(e, description);

        takeScreenshot(description);
    }

    private void takeScreenshot(Description description) {
        String filename = description.getTestClass().getSimpleName() + "-" + description.getMethodName();

        ScreenCapture capture = Screenshot.capture();
        capture.setName(filename);
        capture.setFormat(Bitmap.CompressFormat.PNG);

        //HashSet<ScreenCaptureProcessor> processors = new HashSet<>();
        //processors.add(new BasicScreenCaptureProcessor());
        //processors.add(new CustomScreenCaptureProcessor());

        CustomScreenCaptureProcessor processor = new CustomScreenCaptureProcessor();

        try {
            processor.process(capture);
            //capture.process(processors);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class CustomScreenCaptureProcessor extends BasicScreenCaptureProcessor {

        public CustomScreenCaptureProcessor() {
            mTag = "CustomScreenCaptureProcessor";
            mFileNameDelimiter = "-";
            mDefaultFilenamePrefix = "screenshot";
            mDefaultScreenshotPath = ApplicationProvider.getApplicationContext().getExternalFilesDir(DIRECTORY_PICTURES);
        }
    }
}