package edu.xjtu.parallel.calculate;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 * Created by Yh on 2015/11/24.
 */
public class CalIntermediate {
    private File file1;
    private File file2;

    private List<BufferedImage> imgArray;

    public CalIntermediate(File file1, File file2) {
        this.file1 = file1;
        this.file2 = file2;
    }
}
