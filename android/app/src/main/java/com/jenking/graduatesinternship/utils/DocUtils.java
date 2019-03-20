package com.jenking.graduatesinternship.utils;

import android.content.Context;
import android.os.Environment;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class DocUtils {

    // 创建生成的文件地址
//    public static final String newPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/学生综合素质管理系统/学生报告.doc";
    public static final String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/学生综合素质管理系统";

    /**
     * newFile 生成文件
     * map 要填充的数据
     */
    public static void writeDoc(InputStream in, File newFile, Map<String, String> map) {
        try {

            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }

            HWPFDocument hdt = new HWPFDocument(in);
            // Fields fields = hdt.getFields();
            // 读取word文本内容
            Range range = hdt.getRange();
            // System.out.println(range.text());
            // 替换文本内容
            for (Map.Entry<String, String> entry : map.entrySet()) {
                range.replaceText(entry.getKey(), entry.getValue());
            }
            ByteArrayOutputStream ostream = new ByteArrayOutputStream();
            FileOutputStream out = new FileOutputStream(newFile, true);
            hdt.write(ostream);
            // 输出字节流
            out.write(ostream.toByteArray());
            out.close();
            ostream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 这个是Button的点击事件
     */
    public static void save(Context context,Map<String,String> sources,String username) {
        try {
            //从assets读取我们的Word模板
            InputStream is = context.getAssets().open("Name.doc");
            //创建生成的文件
            String newPath = filePath+"/"+username+"-学生报告.doc";
            File newFile = new File(newPath);
            writeDoc(is, newFile, sources);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
