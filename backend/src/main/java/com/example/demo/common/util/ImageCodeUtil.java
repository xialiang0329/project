package com.example.demo.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

public   class ImageCodeUtil
{


    @Autowired
    private static StringRedisTemplate redisTemplate;


    private static final String SESSIONIMAGECODEKEY  = "imageCode";

    /**
     * 随机产生数字与字母组合的字符串
     */
    private static String randString = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 图片宽
     */
    private static int width = 100;

    /**
     * 图片高
     */
    private static int height = 30;

    /**
     * 干扰线数量
     */
    private static int lineSize = 40;

    /**
     * 随机产生字符数量
     */
    private static int stringNum = 4;

    private static Random random = new Random();

    /**
     * 获得字体
     */
    private static Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
    }

    /**
     * 获得颜色
     */
    private static Color getRandColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    private static String dataStr = "data:image/jpeg;base64,";

    /**
     * 生成随机图片
     */
    public static  String getRandCode(HttpServletRequest request) {
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);

        //产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        Graphics g = image.getGraphics();
        //图片大小
        g.fillRect(0, 0, width, height);
        //字体大小
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
        //字体颜色
        g.setColor(getRandColor(110, 133));
        // 绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drowLine(g);
        }
        // 绘制随机字符
        String randomString = "";
        for (int i = 1; i <= stringNum; i++) {
            randomString = drowString(g, randomString, i);
        }
        g.dispose();

        HttpSession session = request.getSession();
        session.setAttribute(SESSIONIMAGECODEKEY,randomString);

        //输出流
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", stream);
            return dataStr + imageToBase64Str(new ByteArrayInputStream(stream.toByteArray()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("验证码获取失败!");
        }
    }

    public static boolean verityImageCode(String imageCode,HttpServletRequest request){
        HttpSession session = request.getSession();
        String sessionImageCode = session.getAttribute(SESSIONIMAGECODEKEY).toString();
        if (!StringUtils.isEmpty(imageCode) && imageCode.equalsIgnoreCase(sessionImageCode)) {
            session.removeAttribute(SESSIONIMAGECODEKEY);
            return true;
        }
        return false;
    }



    public static String imageToBase64Str(InputStream inputStream) {
            byte[] data = null;
            try {
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
            } catch (IOException e) {
             e.printStackTrace();
            }
            // 加密
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(data);
    }


    /**
     * 绘制字符串
     */
    private static String drowString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
            .nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(randString
            .length())));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }

    /**
     * 绘制干扰线
     */
    private static void drowLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 获取随机的字符
     */
    public  static String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }





    /**
     * 将图片文件转为byte数字
     * @param imgFile
     * @return
     */
    public static  byte[] getImageByte(String imgFile) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        //String imgFile = "d:\\111.jpg";// 待处理的图片
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 返回Base64编码过的字节数组字符串
        return data;
    }

    /**
     * 将字符串转为图片
     * @param imgStr
     * @return
     */
    public static  boolean generateImage(String imgStr,String imgFile)throws Exception {
        // 对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成jpeg图片
            String imgFilePath = imgFile;// 新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 图片是否符合 jpg gjf png格式
     * @param format
     * @return
     */
    public static  boolean isRightFormat(String format){

        return (format.equals("jpg") || format.equals("gif") || format.equals("png"))?true:false;
    }

    /**
     * 对图片进行放大
     * @param originalImage 原始图片
     * @param times 放大倍数
     * @return
     */

    public static  BufferedImage  zoomInImage(BufferedImage  originalImage, Integer times){

        int width = originalImage.getWidth()*times;
        int height = originalImage.getHeight()*times;

        BufferedImage newImage = new BufferedImage(width,height,originalImage.getType());
        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage, 0,0,width,height,null);
        g.dispose();
        return newImage;
    }

    /**
     * 对图片进行放大
     * @param srcPath 原始图片路径(绝对路径)
     * @param newPath 放大后图片路径（绝对路径）
     * @param times 放大倍数
     * @return 是否放大成功
     */

    public static  boolean zoomInImage(String srcPath,String newPath,Integer times){

        BufferedImage bufferedImage = null;
        try{

            File of = new File(srcPath);
            if(of.canRead()){

                bufferedImage =  ImageIO.read(of);

            }
        }catch(Exception e){
            //TODO: 打印日志
            return false;
        }
        if(bufferedImage != null){

            bufferedImage = zoomInImage(bufferedImage,times);
            try {
                //TODO: 这个保存路径需要配置下子好一点
                //保存修改后的图像,全部保存为JPG格式
                ImageIO.write(bufferedImage, "JPG", new File(newPath));
            } catch (IOException e) {
                // TODO 打印错误信息
                return false;
            }
        }
        return true;

    }

    /**
     * 对图片进行缩小
     * @param originalImage 原始图片
     * @param times 缩小倍数
     * @return 缩小后的Image
     */
    public static BufferedImage  zoomOutImage(BufferedImage  originalImage, Integer times){
        int width = originalImage.getWidth()/times;
        int height = originalImage.getHeight()/times;
        BufferedImage newImage = new BufferedImage(width,height,originalImage.getType());
        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage, 0,0,width,height,null);
        g.dispose();
        return newImage;
    }

    /**
     * 对图片进行放大
     * @param srcPath 原始图片路径(绝对路径)
     * @param newPath 放大后图片路径（绝对路径）
     * @param times 放大倍数
     * @return 是否放大成功
     */

    public static  boolean zoomOutImage(String srcPath,String newPath,Integer times){
        BufferedImage bufferedImage = null;
        try {
            File of = new File(srcPath);
            if(of.canRead()){
                bufferedImage =  ImageIO.read(of);
            }
        } catch (IOException e) {
            //TODO: 打印日志
            return false;
        }
        if(bufferedImage != null){
            bufferedImage = zoomOutImage(bufferedImage,times);
            try {
                //TODO: 这个保存路径需要配置下子好一点
                //保存修改后的图像,全部保存为JPG格式
                ImageIO.write(bufferedImage, "JPG", new File(newPath));
            } catch (IOException e) {
                // TODO 打印错误信息
                return false;
            }
        }
        return true;
    }
}
