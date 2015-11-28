package edu.xjtu.parallel.calculate;

import edu.xjtu.parallel.Utils.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Yh on 2015/11/24.
 */
public class CalIntermediate {
    private BufferedImage imageA;
    private BufferedImage imageB;

    public CalIntermediate(File file1, File file2) throws IOException {
        this.imageA = ImageIO.read(file1);
        this.imageB = ImageIO.read(file2);
    }

    public CalIntermediate(BufferedImage imageA, BufferedImage imageB) {
        this.imageA = imageA;
        this.imageB = imageB;
    }

    public List<BufferedImage> calImgSerial() {
        int widthA = imageA.getWidth();
        int heightA = imageA.getHeight();
        int widthB = imageB.getWidth();
        int heightB = imageB.getHeight();
        Color color = new Color(243, 243, 243);
        if(widthA != widthB || heightA != heightB) {
            BufferedImage finalImgA,
                          finalImgB;
            int width = widthA > widthB ? widthA : widthB;
            int height = heightA > heightB ? heightA: heightB;

            try {
                finalImgA = ImageUtil.patchImage(imageA, width, height, color);
                finalImgB = ImageUtil.patchImage(imageB, width, height, color);
                CalImage cal = new CalImage(finalImgA, finalImgB);
                return cal.getImageList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        CalImage cal = new CalImage(imageA, imageB);
        return cal.getImageList();
    }
}
