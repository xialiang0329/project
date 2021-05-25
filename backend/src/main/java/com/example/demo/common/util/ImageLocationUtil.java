package com.example.demo.common.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImageLocationUtil
{
    private static final Font font = new Font("楷体", Font.PLAIN, 30);// 添加字体的属性设置

    private Graphics2D g = null;

    private int px = 0;

    private int py = 0;

    /**
     *  修改图片
     * @param img 模板图片
     * @param contentMap startX开始坐标 endX结束坐标(进行换行) y坐标 v值
     */
    public BufferedImage modifyImage(BufferedImage img, List<Map<String,String>> contentMap)
    {
        try
        {
            int w = img.getWidth();
            int h = img.getHeight();
            g = img.createGraphics();
            g.setBackground(Color.WHITE);
            float[] floats = Color.RGBtoHSB(89, 88, 83, null);
            g.setColor(Color.getHSBColor(floats[0],floats[1],floats[2]));
            if (this.font != null) {g.setFont(this.font);}

            // 验证输出位置的纵坐标和横坐标
            contentMap.forEach(map ->{
                String v = map.get("v");
                if(!StringUtils.isEmpty(v)) {
                    int startX = Integer.valueOf(map.get("startX"));
                    int y = Integer.valueOf(map.get("y"));
                    int endX = -1;
                    String endXStr = map.get("endX");
                    if (!StringUtils.isEmpty(endXStr)) {
                        endX =  Integer.valueOf(endXStr);
                    }
                    if (startX >= w || y >= h) {
                        this.px = w - font.getSize();
                        this.py = h - (v.length() * font.getSize());
                    } else {
                        this.px = startX;
                        this.py = y;
                    }
                    if (endX != -1) {
                        //需要进行手动换行
                        manualLineFeed(v,endX - this.px);
                    } else {
                        g.drawString(v, this.px, this.py);
                    }
                }
            });
            g.dispose();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return img;
    }

    /**
     * 导入本地图片到缓冲区
     */
    public BufferedImage loadImageLocal(String imgName)
    {
        try
        {
            return ImageIO.read(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + imgName));
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * 生成新图片到本地
     */
    public void writeImageLocal(String localImgUrl, BufferedImage img)
    {
        File file = new File(localImgUrl);
        try
        {
            ImageIO.write(img, "jpg", file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 图片转pdf
     * @param localPDFUrl
     * @param imgUrl
     */
    public void imgToPDF(String localPDFUrl, String imgUrl)
    {
        try
        {
            File file = new File(localPDFUrl);
            com.itextpdf.text.Image instance = com.itextpdf.text.Image.getInstance(imgUrl);
            Document document = new Document(new com.itextpdf.text.Rectangle(instance.getWidth(),instance.getHeight()));
            document.setMargins(0,0,0,0);
            PdfWriter pdfWr = PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            document.add(PDFUtil.getImage(document, imgUrl, 100, 1));
            document.close();
            pdfWr.close();
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * 手动换行
     */
    public void manualLineFeed(String content,int maxLength){
        int length = 0;
        int lengthcl=maxLength/font.getSize();
        List<String> strList = new ArrayList<String>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < content.length(); i++) {
            if (new String(content.charAt(i) + "").getBytes().length > 1) {
                length += 2;
            } else {
                length += 1;
            }
            if((length+1)/2<=lengthcl){
                sb.append(content.charAt(i));
            }else{
                i--;
                length=0;
                strList.add(sb.toString());
                sb= new StringBuffer();
            }
        }
        strList.add(sb.toString());
        for (int i = 0; i < strList.size(); i++)
        {
            g.drawString(strList.get(i), this.px, this.py);
            this.py += font.getSize() + 25;
        }
    }

}
