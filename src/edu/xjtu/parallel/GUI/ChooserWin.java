package edu.xjtu.parallel.GUI;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by Yh on 2015/11/14.
 */
public class ChooserWin {
    public ChooserWin(){

    }

    public File[] getFiles(){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);//可选文件和文件夹

        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                String name = f.getName();
                return f.isDirectory()
                        || name.toLowerCase().endsWith(".jpg");
            }

            @Override
            public String getDescription() {
                return "*.jpg";
            }
        };
        chooser.setFileFilter(fileFilter);
        chooser.setMultiSelectionEnabled(true);//可选择多个文件
        int statue = chooser.showDialog(new JLabel(), "请选择文件");
        if (statue == 1){
            return null;
        }

        return chooser.getSelectedFiles();
    }
}
