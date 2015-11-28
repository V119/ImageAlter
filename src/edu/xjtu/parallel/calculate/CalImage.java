package edu.xjtu.parallel.calculate;

import edu.xjtu.parallel.Utils.ImageUtil;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Yh on 2015/11/25.
 */
public class CalImage {
    private BufferedImage imageA;
    private BufferedImage imageB;
    private List<BufferedImage> imageList;

    public CalImage(BufferedImage imageA, BufferedImage imageB) {
        this.imageA = imageA;
        this.imageB = imageB;
        this.imageList = new ArrayList<BufferedImage>();
    }

    private void setImageList(int num) {
        float[][] rgbA;
        float[][] rgbB;

        int width = imageA.getWidth();
        int height = imageA.getHeight();
        int type = imageA.getType();

        ExecutorService exec = Executors.newCachedThreadPool();
        List<Future<float[]>> rResult = new ArrayList<Future<float[]>>();
        List<Future<float[]>> gResult = new ArrayList<Future<float[]>>();
        List<Future<float[]>> bResult = new ArrayList<Future<float[]>>();
        if(num == 0) {
            throw new ArithmeticException("\\ by zero");
        }
        float piece = 1.0f / num;

        try {
            rgbA = ImageUtil.getImgRGB(imageA);
            rgbB = ImageUtil.getImgRGB(imageB);

            for(int i = 0; i < num; i++) {
                rResult.add(exec.submit(new CalPixelTask(rgbA[0], rgbB[0], 1 - i * piece)));
                gResult.add(exec.submit(new CalPixelTask(rgbA[1], rgbB[1], 1 - i * piece)));
                bResult.add(exec.submit(new CalPixelTask(rgbA[2], rgbB[2], 1 - i * piece)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            for(int i = 0; i < rResult.size(); i++) {
                float[] r = rResult.get(i).get();
                float[] g = gResult.get(i).get();
                float[] b = bResult.get(i).get();
                imageList.add(ImageUtil.setImgByRGB(r, g, b, width, height, type));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<BufferedImage> getImageList() {
        this.setImageList(255);
        return imageList;
    }

}
