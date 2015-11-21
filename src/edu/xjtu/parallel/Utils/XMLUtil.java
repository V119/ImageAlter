package edu.xjtu.parallel.Utils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by Yh on 2015/11/16.
 */
public class XMLUtil {
    private static String getNameByTag(String tagName) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dFactory.newDocumentBuilder();
        Document doc = builder.parse(new File("src/edu/xjtu/parallel/config/Config.xml"));

        NodeList nodeList = doc.getElementsByTagName(tagName);
        Node node = nodeList.item(0).getFirstChild();

        return node.getNodeValue().trim();
    }

    public static String getImgType() {

        try {
            String imgType = getNameByTag("imgType");
            return imgType;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
