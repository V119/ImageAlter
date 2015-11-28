package edu.xjtu.parallel.Utils;

import edu.xjtu.parallel.DTO.ShapeDTO;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;

/**
 * Created by Yh on 2015/11/14.
 */
public class ImageUtil {
    public static boolean isImage(File file){
        ShapeDTO shape = ImageUtil.getImgWidthAndHeight(file);

        if(shape.getHeight() > 0 && shape.getWidth() > 0) {
            return true;
        }
        return false;
    }

    public static ShapeDTO getImgWidthAndHeight(File file) {
        ShapeDTO dto = new ShapeDTO();

        try {
            BufferedImage reader = ImageIO.read(file);
            dto.setWidth(reader.getWidth());
            dto.setHeight(reader.getHeight());
        } catch (Exception e) {
            dto.setWidth(-1);
            dto.setHeight(-1);
            e.printStackTrace();
        }

        return dto;
    }

    public static String getFileType(File file){
        String fileName = file.getName();
        String[] files = fileName.split("\\.");
        if(files == null || files.length == 0){
            return null;
        }
        return files[files.length - 1].toLowerCase();
    }

    //拼接图片
    public static BufferedImage patchImage(BufferedImage srcImg, int width, int height, Color color) throws Exception {
        int imgType = srcImg.getType();
        int srcWidth = srcImg.getWidth();
        int srcHeight = srcImg.getHeight();

        if(srcWidth == width && srcHeight == height) {
            return srcImg;
        } else if(srcWidth > width || srcHeight > height) {
            throw new Exception("wrong width or height!!!");
        }

        BufferedImage desImg = new BufferedImage(width, height, imgType);

        //写入第一部分
        desImg.createGraphics().drawImage(srcImg, 0, 0, null);
        //第二部分
        if(width > srcWidth) {
            Graphics2D graph1 = desImg.createGraphics();
            graph1.setColor(color);
            graph1.fillRect(srcWidth ,0 ,width - srcWidth, height);

        }
        if(height > srcHeight) {
            Graphics2D graph2 = desImg.createGraphics();
            graph2.setColor(color);
            graph2.fillRect(0 ,srcHeight ,width, height - srcHeight);
        }
//        ColorModel cm = ColorModel.getRGBdefault();
//        int rgb = desImg.getRGB(srcWidth + 50,srcHeight + 50);
//
//        System.out.println("AA: " + cm.getAlpha(desImg.getRGB(srcWidth - 50,srcHeight - 50)));
//        System.out.println("A: " + cm.getAlpha(rgb));
//        System.out.println("R: " + cm.getRed(rgb));
//        System.out.println("G: " + cm.getGreen(rgb));
//        System.out.println("B: " + cm.getBlue(rgb));

//        if()
        return desImg;
    }

    public static float[][] getImgRGB(BufferedImage img) throws Exception {
        if(img == null ||
                img.getWidth() == 0 || img.getHeight() == 0) {
            throw new Exception("img is empty");
        }
        int width = img.getWidth();
        int height = img.getHeight();
        float[][] rgbArray = new float[3][];
        for(int i = 0; i < 3; i++) {
            rgbArray[i] = new float[width * height];
        }
        ColorModel cm = ColorModel.getRGBdefault();
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                int rgb = img.getRGB(i, j);
                rgbArray[0][i * height + j] = cm.getRed(rgb);
                rgbArray[1][i * height + j] = cm.getGreen(rgb);
                rgbArray[2][i * height + j] = cm.getBlue(rgb);
            }
        }

        return rgbArray;
    }

    public static BufferedImage setImgByRGB(int[] r, int[] g, int[] b, int width, int height, int type) {
        BufferedImage img = new BufferedImage(width, height, type);
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                int rgb = 255 << 24 | r[i * height + j] << 16 | g[i * height + j] << 8 | b[i * height + j];
                img.setRGB(i, j, rgb);
            }
        }

        return img;
    }

    public static BufferedImage setImgByRGB(float[] fr, float[] fg, float[] fb, int width, int height, int type) {
        BufferedImage img = new BufferedImage(width, height, type);
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                int rgb = 255 << 24 | (int)fr[i * height + j] << 16
                        | (int)fg[i * height + j] << 8 | (int)fb[i * height + j];
                img.setRGB(i, j, rgb);
            }
        }

        return img;
    }
}
