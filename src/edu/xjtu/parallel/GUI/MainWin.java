package edu.xjtu.parallel.GUI;

import edu.xjtu.parallel.Utils.FileUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Yh on 2015/11/14.
 */
public class MainWin{
    private int width;
    private int height;

    private JFrame frame;
    private JPanel mainPanel;
    private ImgPanel imgPanel;
    private JScrollPane scrollPane;
    private BorderLayout borderLayout;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem openItem;

    private LoadImg loadImg;

    public MainWin(){
        width = 600;
        height = 400;
    }

    public MainWin(int width, int height){
        this.width = width;
        this.height = height;
    }

    private void drawWin(){
        frame = new JFrame("Image browser");
        Container container = frame.getContentPane();

        mainPanel = new JPanel();
        scrollPane = new JScrollPane();
        borderLayout = new BorderLayout();
        menuBar = new JMenuBar();
        fileMenu = new JMenu("open");
        openItem = new JMenuItem("open files");
        imgPanel = new ImgPanel();
        loadImg = new LoadImg();

        //布局管理器
        mainPanel.setLayout(borderLayout);
//        imgPanel.setLayout(null);

//        pic = new JLabel("hello");
//        pic.setSize(400, 200);
//        pic.setLocation(10, 10);

        fileMenu.add(openItem);
        menuBar.add(fileMenu);

        MenuListener listen = new MenuListener();
        openItem.addActionListener(listen);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

//        picPanel.add(pic);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(menuBar, BorderLayout.NORTH);
        container.add(mainPanel);
    }

    //事件
    class MenuListener implements ActionListener{
        File[] files = null;

        @Override
        public void actionPerformed(ActionEvent e) {
            if((JMenuItem)e.getSource() == openItem){
                ChooserWin chooser = new ChooserWin();
                files = chooser.getFiles();
                java.util.List<File> imgFiles = new ArrayList<File>();
                if(files == null){
                    System.out.println("null");
                }else{
                    //将选择的所有规定格式的图片文件存到list中
                    for(File file: files){
                        if(file.isFile()) {
                            imgFiles.add(file);
                        } else if(file.isDirectory()) {
                            try {
                                File[] dirFiles = FileUtil.getFilesFromDir(file);
                                for(File dirFile: dirFiles) {
                                    imgFiles.add(dirFile);
                                }
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                    loadImg.setFiles(imgFiles, imgPanel, scrollPane);
//                        loadImg.showPic();
                    loadImg.showPicSerial(1000);
                }
                //处理img

            }
        }

        public File[] getChooseFile(){
            return files;
        }
    }

    public void getWin(){
        this.drawWin();

        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
