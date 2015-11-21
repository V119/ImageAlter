package edu.xjtu.parallel.GUI;

import javax.swing.JScrollPane;
import java.awt.Image;
import java.awt.Dimension;
import java.io.File;
import java.util.*;

/**
 * Created by Yh on 2015/11/15.
 */
public class LoadImg {
    private List<File> files;
    private Image image;
    private ImgPanel imgPanel;
    private JScrollPane scrollPane;

    private static int num = 0;

    public void setFiles(List<File> files, ImgPanel panel, JScrollPane scrollPane) {
        this.imgPanel = panel;
        this.files = files;
        this.scrollPane = scrollPane;

        num = 0;
    };

    public void setFiles(File file, ImgPanel panel) {
        this.imgPanel = panel;
        this.files = new ArrayList<File>();
        this.files.add(file);

        num = 0;
    }

    public void showPic() {
        imgPanel.setImgByFile(files.get(0));

        scrollPane.setViewportView(imgPanel);
        imgPanel.setPreferredSize(
                new Dimension(imgPanel.getWidth(), imgPanel.getHeight()));
    }

    public void showPicSerial(long delay) {
        Timer showPicTimer = new Timer();

        showPicTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("num: " + num + "size: " + files.size());
                if(num > files.size() - 1) {
                    cancel();
                }else {
                    imgPanel.setImgByFile(files.get(num++));
                    System.out.println("num " + files.get(num - 1));
                    scrollPane.setViewportView(imgPanel);
                    imgPanel.setPreferredSize(
                            new Dimension(imgPanel.getWidth(), imgPanel.getHeight()));
                }
            }
        }, 0,delay);

        if(num > files.size()) {
            showPicTimer.cancel();
        }
    }

}
