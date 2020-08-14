package com.huayu.ssm.bo;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

/**
 * @author 旗木卡卡西
 * @date 2020/7/22 17:07
 */
public class FileUtils {
    public static String getFileName(MultipartFile multipartFile, HttpServletRequest request) throws IOException {
        String str = UUID.randomUUID().toString();
        /*获取上传的文件名*/
        String filename = multipartFile.getOriginalFilename();
        /*获取上传文件名的后缀*/
        String lastfilename = filename.substring(filename.lastIndexOf("."));
        /*目标文件名*/
        String filename1 = str + lastfilename;
        /*获取日历*/
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        /*放图片的文件夹名  一个月一个*/
        String wenjianjia = "sssmpicture" + year + month;
        String genpath = request.getServletContext().getRealPath("/");
        File directory=new File(genpath,wenjianjia);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        multipartFile.transferTo(new File(directory,filename1));
        String rpFileName=wenjianjia+"\\"+filename1;
        return rpFileName;
    }
}
