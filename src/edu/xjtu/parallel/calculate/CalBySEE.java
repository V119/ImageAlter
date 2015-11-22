package edu.xjtu.parallel.calculate;

/**
 * Created by Yh on 2015/11/22.
 */
public class CalBySEE {
    static {
        String abPath = System.getProperty("user.dir") + "\\src\\sse.dll";
        System.load(abPath);
    }

    private native float[] calPixel(float[] pixelA, float[] pixelB, float fade);

    public float[] cal(float[] pixelA, float[] pixelB, float fade) {
        return this.calPixel(pixelA, pixelB, fade);
    }
}
