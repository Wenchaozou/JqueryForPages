package com.cn.action;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Properties;

/**
 * Created by Nolimitors on 2017/3/20.
 */
public class DownloadFile extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取所要下载文件的路径
        Properties props = new Properties();
        InputStream in = new BufferedInputStream(new FileInputStream(this.getClass().getResource("/fileroot.properties").getPath()));
        props.load(in);
        String rootPath = props.getProperty("Root");
        String name = req.getParameter("filename");
        name = new String(name.getBytes("ISO8859-1"),"UTF-8");
        System.out.println(name);
        //处理请求
        //读取要下载的文件
        File f = new File(rootPath+"\\"+ name);
        if(f.exists()){
            FileInputStream  fis = new FileInputStream(f);
            String filename=java.net.URLEncoder.encode(f.getName(),"utf-8"); //解决中文文件名下载乱码的问题
            byte[] b = new byte[fis.available()];
            fis.read(b);
            //解决中文文件名下载后乱码的问题
            resp.setContentType("application/x-msdownload");
            resp.setHeader("Content-Disposition", "attachment;filename="+
                    new String(filename.getBytes("utf-8"),"ISO-8859-1"));
            //获取响应报文输出流对象
            ServletOutputStream out =resp.getOutputStream();
            //输出
            out.write(b);
            out.flush();
            out.close();
        }
    }
}
