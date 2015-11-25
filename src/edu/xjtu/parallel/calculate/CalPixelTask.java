package edu.xjtu.parallel.calculate;

import java.util.concurrent.Callable;

/**
 * Created by Yh on 2015/11/25.
 */
public class CalPixelTask implements Callable<float[]>{
    private float[] pixelA;
    private float[] pixelB;
    private float fade;

    public CalPixelTask(float[] pixelA, float[] pixelB, float fade) {
        this.pixelA = pixelA;
        this.pixelB = pixelB;
        this.fade = fade;
    }
    @Override
    public float[] call() throws Exception {
        CalBySEE sse = new CalBySEE();
        return sse.cal(pixelA, pixelB, fade);
    }
}
