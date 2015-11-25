package edu.xjtu.parallel.GUI;

import edu.xjtu.parallel.DTO.ShapeDTO;
import edu.xjtu.parallel.Utils.ImageUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Yh on 2015/11/15.
 */
public class ImgPanel extends JPanel {
    private Image image;
    private ShapeDTO widthAndHeight;

    public ImgPanel() {
        widthAndHeight = new ShapeDTO();
    }

    public void setImgByFile(File file) {
//        BufferedImage bufImage;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            bufImage = ImageIO.read(file);
//            image = ImageUtil.patchImage(bufImage, bufImage.getWidth() + 100, bufImage.getHeight() + 100);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        this.setWidth(image.getWidth(this));
        this.setHeight(image.getHeight(this));

        repaint();
    }

    public void setImgByPath(String path) {
        File file = new File(path);
        widthAndHeight = ImageUtil.getImgWidthAndHeight(file);
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        repaint();
    }

    public void setWidth(int width) {
        this.widthAndHeight.setWidth(width);
    }

    public void setHeight(int height) {
        this.widthAndHeight.setHeight(height);
    }

    public int getWidth() {
        if(widthAndHeight == null) {
            return 0;
        }
        return widthAndHeight.getWidth();
    }

    public int getHeight() {
        if(widthAndHeight == null) {
            return 0;
        }
        return widthAndHeight.getHeight();
    }

//    @Override
//    public void paint(Graphics graphics) {
//        super.paint(graphics);
//        int x = 0;
//        int y = 0;
//        if(null == image) {
//            return;
//        }
//
//        graphics.drawImage(image, x, y, image.getWidth(this),
//                image.getHeight(this), null);
//    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        int x = 0;
        int y = 0;
        if(null == image) {
            return;
        }

        graphics.drawImage(image, x, y, image.getWidth(this),
                image.getHeight(this), this);
    }
}
