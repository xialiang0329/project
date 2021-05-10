package com.example.demo.common.util;

import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class pdfUtil {

    /**
     * 获取pdfDocument
     * @param docSize pdf纸张大小
     * @param marginLeft 左边距
     * @param marginRight 右边距
     * @param marginTop 上边距
     * @param marginBottom 下边距
     * @return
     */
    public static Document getDocument(Rectangle docSize,int marginLeft,int marginRight,int marginTop,int marginBottom){
        Document doc = null;
        try {
            doc = new Document(docSize,marginLeft,marginRight,marginTop,marginBottom);
            //doc保存位置
            File file = new File("C:\\Users\\pp\\Desktop\\pdf\\test\\" + new Date().getTime() + ".pdf");
            file.createNewFile();
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(file));
            writer.setStrictImageSequence(true);//按顺序添加
        } catch (Exception e) {
            //文件生成错误
            e.printStackTrace();
        }
        return doc;
    }

    /**
     * 获取图片
     * @param doc pdf文档
     * @param imageUrl 图片路径
     * @param scalePercent 图片大小比例百分比
     * @param alignment 对齐方式
     * @throws IOException
     * @throws DocumentException
     */
    public static Image getImage(Document doc,String imageUrl,int scalePercent,int alignment) throws IOException, BadElementException {
        Image image = Image.getInstance(imageUrl);
        image.setAlignment(alignment);
        image.scalePercent(scalePercent);
        return image;
    }

    /**
     * 获取段落
     * @param doc
     * @param spacingBefore 段落上空白
     * @param spacingAfter 段落下空白
     * @param alignment 对齐方式 0靠左   1，居中     2，靠右
     * @param chunks 需要添加的内容
     * @return
     */
    public static Paragraph getParagraph(Document doc,int alignment,float spacingBefore,float spacingAfter,List<Chunk> chunks) {
        Paragraph paragraph = new Paragraph();
        paragraph.setSpacingBefore(spacingBefore); //设置段落上空白
        paragraph.setSpacingAfter(spacingAfter); //设置段落下空白
        paragraph.setAlignment(alignment);
        if (!CollectionUtils.isEmpty(chunks)) {//添加chunk内容
            chunks.forEach(chunk ->{
                paragraph.add(chunk);
            });
        }
        return paragraph;
    }

    /**
     * 获取表格
     * @param doc
     * @param numColumns 表格列数
     * @param widthPercentage 表格宽度比例
     * @param widths 单元格宽度比例
     * @param horizontalAlignment 对齐方式
     * @return
     * @throws DocumentException
     */
    public static PdfPTable getPdfPTable(Document doc,int numColumns,float widthPercentage,int[] widths,int horizontalAlignment,List<PdfPCell> pdfPCells) throws DocumentException {
        PdfPTable pdfPTable = new PdfPTable(numColumns);
        pdfPTable.setWidthPercentage(widthPercentage);
        pdfPTable.setWidths(widths);
        pdfPTable.setHorizontalAlignment(horizontalAlignment);
        if (!CollectionUtils.isEmpty(pdfPCells)) {
            pdfPCells.forEach(cell ->{
                pdfPTable.addCell(cell);
            });
        }
        return pdfPTable;
    }




    public static void main(String[] args) {
        //获取doc
        Document doc = getDocument(PageSize.A4, 30, 30, 10, 10);
        // 1.打开文档
        doc.open();

        Font font0 = getFont(15, BaseColor.GREEN, Font.NORMAL);
        Font font1 = getFont(12, BaseColor.BLACK, Font.NORMAL);
        Font font11 = getFont(12, BaseColor.BLACK, Font.BOLD);
        Font font2 = getFont(18, BaseColor.BLACK, Font.NORMAL);
        Font font3 = getFont(15, BaseColor.BLACK, Font.BOLD);
        Font font31 = getFont(15, BaseColor.WHITE, Font.BOLD);

        try {
            //2.添加图片
            String imgUrl = "C:\\Users\\pp\\Desktop\\pdf\\1619402407.jpg";
            Image image1 = getImage(doc, imgUrl, 60, Image.ALIGN_CENTER);
            doc.add(image1);
            //3.图片下单位名称 - 段落
            List<Chunk> chunks = new ArrayList<>();
            chunks.add(new Chunk("体检机构:",font0));
            chunks.add(new Chunk("   "+"pdf测试公司",font1));
            Paragraph p1 = getParagraph(doc, Element.ALIGN_CENTER, 12f, 12f,chunks);
            doc.add(p1);
            //4.添加图片(二维码) - 人员基本信息
            List<PdfPCell> pdfPCells1 = new ArrayList<>();
            //二维码
            String imgUrl2 = "C:\\Users\\pp\\Desktop\\pdf\\二维码.jpg";
            Image image2 = getImage(doc, imgUrl2, 50, Image.ALIGN_CENTER);
            PdfPCell pdfPCell1 = new PdfPCell();
            pdfPCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfPCell1.setBorderWidth(0f);
            pdfPCell1.addElement(image2);
            pdfPCells1.add(pdfPCell1);
            //人员基本信息
            List<Chunk> chunks2 = new ArrayList<>();
            Chunk chunk3 = new Chunk("某某某  先生\n", font2);
            Chunk chunk4 = new Chunk(new Chunk(new LineSeparator()));
            chunks2.add(chunk3);chunks2.add(chunk4);
            Paragraph p2 = getParagraph(doc, Element.ALIGN_CENTER, 0f, 3f, chunks2);
            PdfPCell pdfPCell2 = new PdfPCell();
            pdfPCell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfPCell2.setBorderWidth(0f);
            pdfPCell2.addElement(p2);
            List<String> strList = new ArrayList<>();
            strList.add("单位名称：");
            strList.add("广州劳动健教健康咨询有限公司南京分公司（2020年度）");
            strList.add("身份证：");
            strList.add("320322198710062210");
            strList.add("年龄：");
            strList.add("34");
            strList.add("体检号：");
            strList.add("D2866401");
            strList.add("体检时间：");
            strList.add("2021/3/13 7:34:41");
            PdfPTable pdfPTable2 = getPdfPTable(doc,2,100f,new int[] { 30, 70 },Element.ALIGN_CENTER,null);
            for (int i = 0; i < strList.size(); i++) {
                PdfPCell cell3 = new PdfPCell(new Paragraph(new Chunk(strList.get(i),font1)));
                cell3.disableBorderSide(15);
                cell3.setBorderWidth(0f);
                cell3.setPadding(4f);
                if ((i+1) % 2 != 0) {
                    cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
                }
                pdfPTable2.addCell(cell3);
            }
            pdfPCell2.addElement(pdfPTable2);
            pdfPCells1.add(pdfPCell2);
            PdfPTable pdfPTable = getPdfPTable(doc, 2, 100f, new int[] {40, 60}, Element.ALIGN_CENTER,pdfPCells1);
            doc.add(pdfPTable);

            //5.阅读报告说明
            Paragraph p3 = new Paragraph("报告阅读说明报 :", font3);
            p3.setAlignment(Element.ALIGN_LEFT);
            doc.add(p3);

            /* 第一种
                List<String> strDesc1 = new ArrayList<>();
                strDesc1.add("1 您好，欢迎您选择美年大健康集团体检！我们为您提供健康管理、健康体检、临床检验、特色诊疗、中医养生 等全方位的医疗健康服务。");
                strDesc1.add("2 医学科技发展至今，现有的医疗技术手段，对于疾病的筛查仍具有局限性和时效性，本次体检由于所选项目受 限，会无法发现某些潜在疾病；体检后未发现异常的项目，并不说明就没有潜在疾病，如果有疾病症状出现，请 立即就医。");
                strDesc1.add("3 本报告书仅对受检者本次所检结果负责;警示部位请您关注，示意图仅供参考。特别说明：对于结论为详见纸质 报告的检测项目异常信息及国家规定的单独出报告的检测项目异常信息不纳入健康警示灯及主要阳性结果及建议 中。");
                for (int i = 0; i < strDesc1.size(); i++) {
                    Paragraph paragraph = new Paragraph(strDesc1.get(i), font1);
                    paragraph.setAlignment(Element.ALIGN_LEFT);
                    doc.add(paragraph);
                }
            */

            /* 第二种 */
            String desc = "1 您好，欢迎您选择美年大健康集团体检！我们为您提供健康管理、健康体检、临床检验、特色诊疗、中医养生 等全方位的医疗健康服务。\n" +
            "2 医学科技发展至今，现有的医疗技术手段，对于疾病的筛查仍具有局限性和时效性，本次体检由于所选项目受 限，会无法发现某些潜在疾病；体检后未发现异常的项目，并不说明就没有潜在疾病，如果有疾病症状出现，请 立即就医。\n" +
            "3 本报告书仅对受检者本次所检结果负责;警示部位请您关注，示意图仅供参考。特别说明：对于结论为详见纸质 报告的检测项目异常信息及国家规定的单独出报告的检测项目异常信息不纳入健康警示灯及主要阳性结果及建议 中。";
            Paragraph p4 = new Paragraph(desc, font1);
            p4.setAlignment(Element.ALIGN_LEFT);
            p4.setIndentationLeft(10f);//缩进
            doc.add(p4);


            //6.人体图片
            String imgUrl3 = "C:\\Users\\pp\\Desktop\\pdf\\人体.jpg";
            Image image3 = getImage(doc, imgUrl3, 30, Image.ALIGN_CENTER);
            doc.add(image3);

            Paragraph p5 = new Paragraph("主要阳性结果及异常情况 主 (报告医师 报 : 陈昕陈 )", font1);
            p5.setAlignment(Element.ALIGN_CENTER);
            doc.add(p5);

            List<Chunk> chunks3 = new ArrayList<>();
            Chunk chunk5 = new Chunk("★ 一般检查（体重身高）:\n 体重指数:27.2 \n血压:129/87mmHg  血压正常高值,建议随访复查 \n脉搏:83 次/分",font1);
            Chunk chunk6 = new Chunk("★ 彩超（肝胆脾胰肾彩超）（需空腹）:\n 肝:脂肪肝趋势 \n胆、胰、脾、左肾、右肾未发现明显异常",font1);
            chunks3.add(chunk5);chunks3.add(chunk6);
            Paragraph p6 = getParagraph(doc, Element.ALIGN_LEFT, 1f, 1f, chunks3);
            p6.setLeading(20f);//行间距
            doc.add(p6);


            //7.异常指标解读
            Paragraph p7 = new Paragraph("异常指标解读", font3);
            p7.setAlignment(Element.ALIGN_CENTER);
            p7.setSpacingBefore(5f);//行间距
            p7.setSpacingAfter(5f);//行间距
            doc.add(p7);


            //8.结果-范围
            List<PdfPCell> pdfPCells2 = new ArrayList<>();

            font31.setColor(BaseColor.WHITE);
            PdfPCell pdfPCell3 = new PdfPCell(new Paragraph(new Chunk("体重指数偏高",font31)));
            pdfPCell3.setBackgroundColor(new BaseColor(0,102,94));//背景色
            pdfPCell3.setBorderWidth(0f);//无边框
            pdfPCell3.setHorizontalAlignment(Element.ALIGN_CENTER);//对齐居中
            pdfPCells2.add(pdfPCell3);

            PdfPCell pdfPCell4 = new PdfPCell();
            Chunk chunk7 = new Chunk("结果: 27.2 结果: 0.31 %                体重指数偏高 体 范围:18.5-23.9\n", font1);
            Chunk chunk8 = new Chunk("【医学解释】", font11);
            pdfPCell4.addElement(chunk7);
            pdfPCell4.addElement(chunk8);
            Paragraph p9 = new Paragraph("医学解释：BMI指数（即身体质量指数，简称体质指数又称体重指数，英文为Body Mass Index，简称 BMI），是用体重公斤数除以身高米数平方得出的数字，是目前国际上常用的衡量人体胖瘦程度以及是否健康 的一个标准。主要用于统计用途，当我们需要比较及分析一个人的体重对于不同高度的人所带来的健康影响 时，BMI值是一个中立而可靠的指标。体重指数24-27.9为超重；大于或等于28为肥胖。", font1);
            p9.setIndentationLeft(10f);
            pdfPCell4.addElement(p9);

            Chunk chunk9 = new Chunk("【常见原因及后果】", font11);
            pdfPCell4.addElement(chunk9);
            Paragraph p10 = new Paragraph("BMI是世界公认的一种评定肥胖程度的分级方法，世界卫生组织(WHO)也以BMI来对肥胖或超重进行定义。体 重指数增高，冠心病和脑卒中发病率也会随之上升，超重和肥胖是冠心病和脑卒中发病的独立危险因素。体 重指数每增加2，冠心病、脑卒中、缺血性脑卒中的相对危险分别增加15.4%、6.1%和18.8%。 一旦体重指数 达到或超过24时，患高血压、糖尿病、冠心病和血脂异常等严重危害健康的疾病的概率会显著增加。", font1);
            p10.setIndentationLeft(10f);
            pdfPCell4.addElement(p10);

            Chunk chunk10 = new Chunk("【建议】", font11);
            pdfPCell4.addElement(chunk10);
            Paragraph p11 = new Paragraph("体重控制方案可使用4种疗法：饮食和营养咨询，行为治疗，药物和外科手术，正规医院专科治疗。", font1);
            p11.setIndentationLeft(10f);
            pdfPCell4.addElement(p11);
            pdfPCell4.setBorderWidth(0f);//无边框
            pdfPCells2.add(pdfPCell4);

            PdfPTable pdfPTable3 = getPdfPTable(doc, 1, 100f, new int[] {100}, Element.ALIGN_CENTER, pdfPCells2);
            doc.add(pdfPTable3);


            //【一般检查 【 (1)】
            PdfPTable pdfPTable4 = new PdfPTable(2);
            pdfPTable4.setWidthPercentage(100f);
            PdfPCell pdfPCell5 = new PdfPCell(new Paragraph("【一般检查 【 (1)】", font11));
            pdfPCell5.setBorderWidth(0f);//无边框
            pdfPCell5.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell pdfPCell6 = new PdfPCell(new Paragraph("检查者检:吴美", font11));
            pdfPCell6.setBorderWidth(0f);//无边框
            pdfPCell6.setHorizontalAlignment(Element.ALIGN_RIGHT);
            pdfPTable4.addCell(pdfPCell5);
            pdfPTable4.addCell(pdfPCell6);
            doc.add(pdfPTable4);


            //表格数据
            BaseColor baseColor = new BaseColor(0, 102, 94);
            List<PdfPCell> pdfPCells3 = new ArrayList<>();
            PdfPCell cell1 = new PdfPCell(new Paragraph(new Chunk("检查项目名称", font31)));
            cell1.setBackgroundColor(baseColor);
            cell1.setBorderWidth(0f);
            PdfPCell cell2 = new PdfPCell(new Paragraph(new Chunk("结果", font31)));
            cell2.setBackgroundColor(baseColor);
            cell2.setBorderWidth(0f);
            PdfPCell cell3 = new PdfPCell(new Paragraph(new Chunk("单位", font31)));
            cell3.setBackgroundColor(baseColor);
            cell3.setBorderWidth(0f);
            PdfPCell cell4 = new PdfPCell(new Paragraph(new Chunk("正常范围值", font31)));
            cell4.setBackgroundColor(baseColor);
            cell4.setBorderWidth(0f);


            pdfPCells3.add(cell1);
            pdfPCells3.add(cell2);
            pdfPCells3.add(cell3);
            pdfPCells3.add(cell4);
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.fluentPut("a","收缩压");
            jsonObject1.fluentPut("b","129");
            jsonObject1.fluentPut("c","mmHg");
            jsonObject1.fluentPut("d","90--139mmHg");
            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.fluentPut("a","舒张压");
            jsonObject2.fluentPut("b","87");
            jsonObject2.fluentPut("c","mmHg");
            jsonObject2.fluentPut("d","60--89mmHg");
            JSONObject jsonObject3 = new JSONObject();
            jsonObject3.fluentPut("a","身高");
            jsonObject3.fluentPut("b","173.5");
            jsonObject3.fluentPut("c","Cm");
            jsonObject3.fluentPut("d","");

            List<JSONObject> jsonObjects = new ArrayList<>();
            jsonObjects.add(jsonObject1);
            jsonObjects.add(jsonObject2);
            jsonObjects.add(jsonObject3);

            String[] properties = new String[]{"a","b","c","d"};
            jsonObjects.forEach(l ->{
                for (int i = 0; i < properties.length; i++) {
                    PdfPCell cell = new PdfPCell(new Paragraph(new Chunk(l.getString(properties[i]), font1)));
                    cell.setBorderWidth(0f);
                    pdfPCells3.add(cell);
                }
            });
            PdfPTable pdfPTable5 = getPdfPTable(doc, 4, 100f, new int[] {25, 25, 25, 25}, Element.ALIGN_CENTER, pdfPCells3);
            doc.add(pdfPTable5);

            doc.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    public static OutputStream getOutputStream(HttpServletRequest request, HttpServletResponse response,String fileName)
        throws IOException
    {
        if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
            fileName = URLEncoder.encode(fileName, "UTF-8");//IE
        }else {
            fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
        response.setHeader("connection", "close");
        //刷新缓冲
        response.flushBuffer();
        return response.getOutputStream();
    }



    /**
     *
     * @param fontSize 字体大小
     * @param color 字体颜色
     * @param thickness 字体粗细
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Font getFont(int fontSize,BaseColor color,int thickness) {
        Font font = null;
        try {
            BaseFont bf = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            //字体
            font = new Font(bf,fontSize,thickness);
            font.setColor(color);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return font;
    }
}
