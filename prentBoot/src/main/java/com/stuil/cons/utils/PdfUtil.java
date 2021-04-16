package com.stuil.cons.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import org.nutz.lang.Strings;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @description: PDF
 */
public class PdfUtil {
    /**
     * 根据模板生成pdf
     * @param templatePath 模板pdf路径
     * @param newPdfPath  新pdf路径
     * @param map
     * @return
     */
    public static  boolean  fillTemplate(String templatePath,String newPdfPath,Map<String,String> map,String signature){//利用模板生成pdf
        PdfReader reader=null;
        FileOutputStream out=null;
        ByteArrayOutputStream bos=null;
        PdfStamper stamper =null;
        boolean flag=false;
        try {
            out = new FileOutputStream(newPdfPath);
            reader = new PdfReader(templatePath);//读取pdf模板
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            BaseFont bfChinese = BaseFont.createFont("STSONG.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            for (String key:map.keySet()) {
                form.setFieldProperty(key, "textfont", bfChinese, null);//设置字体
                form.setField(key, map.get(key),true);
            }
            stamper.setFormFlattening(true);//如果为false那么生成的PDF文件还能编辑，一定要设为true

            if (Strings.isNotBlank(signature)) {
                int pageNo = form.getFieldPositions("img").get(0).page;
                Rectangle signRect = form.getFieldPositions("img").get(0).position;
                float x = signRect.getLeft();
                float y = signRect.getBottom();
                // 读图片
                Image image = Image.getInstance(signature);
                // 获取操作的页面
                PdfContentByte under = stamper.getOverContent(pageNo);
                // 根据域的大小缩放图片
                image.scaleToFit(signRect.getWidth(), signRect.getHeight());
                // 添加图片
                image.setAbsolutePosition(x, y);
                under.addImage(image);
            }
            stamper.close();
            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();
            flag=true;
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        } finally {
            try {
                if(out !=null){
                    out.close();
                }
                if(bos !=null){
                    bos.close();
                }
                if(reader !=null){
                    reader.close();
                }
                if(stamper !=null){
                    stamper.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return flag;
    }


    /**
     * 合成签名
     * @param document
     * @param imgSrc
     * @return
     */
    private static Document synthesisAutograph(Document document,String imgSrc) {
        try {
            if (Strings.isNotBlank(imgSrc)) {
                //合成签名图片
                Image image1 = Image.getInstance(imgSrc);
                //设置图片位置的x轴和y轴
                image1.setAbsolutePosition(400f, 100f);
                //设置图片的宽度和高度
                image1.scaleAbsolute(100, 100);
                //将图片1添加到pdf文件中
                document.add(image1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }

    public static void main(String[] args) {

    }
}