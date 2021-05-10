package com.example.demo.common.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * 图片定位
 */
public class ImageLocationUtil
{

    private static final Font font = new Font("楷体", Font.BOLD, 20);// 添加字体的属性设置

    private Graphics2D g = null;
    private int x = 0;
    private int y = 0;

    public BufferedImage modifyImage(BufferedImage img, Map<String, JSONObject> contentMap)
    {
        try {
            int w = img.getWidth();
            int h = img.getHeight();
            g = img.createGraphics();
            g.setBackground(Color.WHITE);
            g.setColor(Color.BLACK);
            if (this.font != null)
                g.setFont(this.font);
            // 验证输出位置的纵坐标和横坐标
            Iterator<Map.Entry<String, JSONObject>> iterator = contentMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, JSONObject> next = iterator.next();
                JSONObject jsonObject = next.getValue();
                int x = jsonObject.getIntValue("x");
                int y = jsonObject.getIntValue("y");
                if (x >= h || y >= w) {
                    this.x = h - font.getSize() + 2;
                    this.y = w;
                } else {
                    this.x = x;
                    this.y = y;
                }
                //数据分割 手动换行
                List<String> ls = list2(jsonObject.getString("value"),w-this.x,font.getSize());
                for(int i=0;i<ls.size();i++){
                    g.drawString(ls.get(i), this.x, this.y);
                    this.y+=30;
                }

                g.drawString(jsonObject.getString("value"), x, y);
                this.y +=30;
            }
            g.dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return img;
    }

    /**
     * 把字符串分行
     * @param maxLength 一行长度#像素
     * @param fontSzie 字体大小
     * @return List<String>
     */
    public List<String> list2(String text,int maxLength,int fontSzie){
        int length = 0;
        int lengthcl=maxLength/fontSzie;
        List<String> list = new ArrayList<String>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            if (new String(text.charAt(i) + "").getBytes().length > 1) {
                length += 2;
            } else {
                length += 1;
            }
            if((length+1)/2<=lengthcl){
                sb.append(text.charAt(i));
            }else{
                i--;
                length=0;
                list.add(sb.toString());
                sb= new StringBuffer();
            }
        }
        return list;
    }



    public static void main(String[] args) {
        Map<String, JSONObject> dataMap = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value","jsagvdi阿苏和规范的撒欧菲光萨佛都有撒点哦啊搜到噶是的撒大广赛U盾噶随意打个扫毒应该撒的谎该速度噶啥的撒多啊是的噶是的高压输电suyfgdasfy 数据的规范");
        jsonObject.put("x",600);
        jsonObject.put("y",20);
        dataMap.put("name",jsonObject);
        try
        {
            ImageLocationUtil tt = new ImageLocationUtil();
            //读取模板图片
            BufferedImage bufferedImage = tt.loadImageLocal("image/imageTest.jpg");//模板
            int width = bufferedImage.getWidth();//图片宽度
            int height = bufferedImage.getHeight();//图片高度
            //从图片中读取RGB
            int[] ImageArray = new int[width*height];
            ImageArray = bufferedImage.getRGB(0,0,width,height,ImageArray,0,width);

            //生成新图片
            BufferedImage ImageNew = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            ImageNew.setRGB(0,0,width,height,ImageArray,0,width);//设置RGB
            tt.writeImageLocal("C:\\Users\\Administrator\\Desktop\\"+System.currentTimeMillis()+".jpg",tt.modifyImage(ImageNew,dataMap));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
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
    public void writeImageLocal(String newImage, BufferedImage img)
    {
        File file = new File(newImage);
        try
        {
            ImageIO.write(img,"jpg",file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}