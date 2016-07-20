package com.kerwin.utils;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Kerwin on 2016/5/5.
 * Base64 data:image to image
 */
public class Base642Image {
    private static final Logger log = LoggerFactory.getLogger(Base642Image.class);

    /**
     * 从path这个地址获取一张图片然后转为base64码
     *
     * @param imgName 图片的名字 如：123.gif（是带后缀的）
     * @param path    123.gif图片存放的路径
     * @return .
     * @throws Exception
     */
    public static String getImageFromServer(String imgName, String path) throws Exception {
        BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        File f = new File(path + imgName);
        if (!f.exists()) {
            f.createNewFile();
        }
        BufferedImage bi = ImageIO.read(f);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, "gif", baos);
        byte[] bytes = baos.toByteArray();
        return encoder.encodeBuffer(bytes).trim();
    }

    /**
     * 将一个base64转换成图片保存在  path  文件夹下   名为imgName.gif
     *
     * @param base64String Base64字符串
     * @param path         是一个文件夹路径
     * @param imgName      图片名字(没有后缀)
     * @throws Exception
     */
    public static String savePictoServer(String base64String, String path, String imgName) throws Exception {
        BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        byte[] bytes1 = decoder.decodeBuffer(base64String);
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
        BufferedImage bi1 = ImageIO.read(bais);

        Date timeCur = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String formatName = format.format(timeCur);

        File dir = new File(path);
        if (!dir.exists()) dir.mkdirs();
        String fileName = path + formatName + "" + imgName;
        File w2 = new File(fileName); //可以是jpg,png,gif格式
        ImageIO.write(bi1, "gif", w2); //不管输出什么格式图片，此处不需改动

        return fileName;
    }

    public static String randomFileName(String originalName) {
        int i = originalName.lastIndexOf(".");
        if (i == -1) {
            throw new RuntimeException("Unknown file type.");
        }
        String type = originalName.substring(i);
        StringBuilder path = new StringBuilder();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/HH/mm/");
        path.append(dateFormat.format(calendar.getTime()));
        String uuid = UUID.randomUUID().toString();
        path.append(uuid.replaceAll("-", ""));
        path.append(type);
        return path.toString();
    }

    /**
     * 保存文件
     *
     * @param multipartFile 上传的文件内容
     * @param type          文件类型，大类型即可，比如 image / video 等等
     * @return
     */
    public String save(MultipartFile multipartFile, String type) {
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String path = randomFileName(originalFilename);
            ResourceBundle bundle = PropertyResourceBundle.getBundle("config-local");
            String location = bundle.getString("path.upload");
            InputStream inputStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(inputStream, Paths.get(location, path).toFile());
            return path;
        } catch (Exception e) {
            log.error("upload file failed", e);
            return null;
        }
    }
}
