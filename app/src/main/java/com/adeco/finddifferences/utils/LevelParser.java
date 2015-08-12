package com.adeco.finddifferences.utils;

import android.content.res.AssetManager;

import com.adeco.finddifferences.game.DifferencePoint;
import com.adeco.finddifferences.game.levels.Level;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by agorbach on 12.08.2015.
 */
public class LevelParser {
    private final static String XML_FOLDER = "xml/";

    public static Level[] GetLevels(AssetManager assets, String fileName) {
        String fullPath = XML_FOLDER + fileName;
        Level[] levels;
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(assets.open(fullPath));

            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("stage");
            int totalLevels = list.getLength();
            levels = new Level[totalLevels];
            for (int i = 0; i < totalLevels; i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) node;

                    NodeList img1s = el.getElementsByTagName("image1");
                    String img1 = img1s.item(0).getTextContent();

                    NodeList img2s = el.getElementsByTagName("image2");
                    String img2 = img2s.item(0).getTextContent();

                    NodeList diffs = el.getElementsByTagName("difference");
                    int difCount = diffs.getLength();
                    DifferencePoint[] differences = new DifferencePoint[difCount];
                    for (int d = 0; d < difCount; d++) {
                        Element item = (Element) diffs.item(d);
                        int x = Integer.parseInt(item.getAttribute("x"));
                        int y = Integer.parseInt(item.getAttribute("y"));
                        int r = Integer.parseInt(item.getAttribute("radius"));
                        differences[d] = new DifferencePoint(x, y, r);
                    }
                    levels[i] = new Level(img1, img2, differences);
                }
            }
            return levels;
        } catch (Exception e) {
            System.out.print("Parsing error: " + e);
        }
        return null;
    }
}
