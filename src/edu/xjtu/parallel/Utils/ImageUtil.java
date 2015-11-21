package edu.xjtu.parallel.Utils;

import edu.xjtu.parallel.DTO.ShapeDTO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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
}
