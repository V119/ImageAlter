package edu.xjtu.parallel.Utils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;

/**
 * Created by Yh on 2015/11/16.
 */
public class FileUtil {
    public static File[] getFilesFromDir(File directory) throws FileNotFoundException {
        if(!directory.isDirectory()) {
            throw new FileNotFoundException("this file is not a directory!!");
        }

        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if(pathname.isDirectory()) {
                    return false;
                }
                String imgType = XMLUtil.getImgType();
                if(imgType != null
                        && pathname.getPath().endsWith(imgType)) {
                    return true;
                }
                return false;
            }
        };
        return directory.listFiles(filter);
    }
}
