package com.yanl.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * @Author: YanL
 * @Time: 15:54 2020/1/6
 * @Email: imyanl.dt@gmail.com
 * @Description:
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    /**
     * 传统方式文件上传
     *
     * @return success
     */
    @RequestMapping("/fileupload1")
    public String fileUpload1(HttpServletRequest request) throws Exception {
        //使用fileupload
        //获取要上传的文件的目录
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        //判断路径是否存在
        File file = new File(path);

        if (!file.exists()) {
            /*
            不存在则新建文件夹
            在tomcat路径下 webapp下
             */

            file.mkdirs();
        }
        //解析request对象，获取上传文件项
        //创建磁盘文件项工厂
        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
        //解析request
        List<FileItem> items = fileUpload.parseRequest(request);
        //遍历
        for (FileItem item : items) {
            //判断文件项是普通字段还是上传的文件
            if (item.isFormField()) {
                //普通表单项
            } else {
                //上传文件项
                //获取上传文件的名称
                String filename = item.getName();
                //把文件名设置唯一值 UUID
                String uuid = UUID.randomUUID().toString().replace("-", "");
                filename = uuid + "_" + filename;
                //上传文件 参数 文件路径和文件名
                item.write(new File(file, filename));
                //删除临时文件
                item.delete();
            }
        }

        return "success";
    }

    /**
     * springMVC文件上传
     * 配置文件解析器
     * 通过文件解析器解析request 返回一个上传对象
     * -------------------固定------------------
     * 再通过controller执行上传(multipart file upload)
     * @return success
     */
    @RequestMapping("/fileupload2")
    public String fileupload2(HttpServletRequest request, MultipartFile upload) throws Exception {
        //获取上传文件目录
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        //获取文件名称
        String filename = upload.getOriginalFilename();
        //把文件名设置唯一值 UUID
        String uuid = UUID.randomUUID().toString().replace("-", "");

        filename = uuid + "_" + filename;

        //上传文件
        upload.transferTo(new File(file, filename));
        return "success";
    }

    /**
     * 跨服务器上传文件
     * @param upload
     * @return
     */
    @RequestMapping("/fileupload3")
    public String fileupload3(MultipartFile upload) throws Exception {

        String path = "http://localhost:8082/response/uploads/";

        //获取文件名称
        String filename = upload.getOriginalFilename();
        //把文件名设置唯一值 UUID
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid + "_" + filename;

        //向图片服务器上传图片
        //创建客户端对象
        Client client = Client.create();
        //连接图片服务器
        WebResource webResource = client.resource(path + filename);
        //上传文件
        webResource.put(upload.getBytes());

        return "success";
    }

}
