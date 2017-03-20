package com.cn.action;

import com.alibaba.fastjson.JSON;
import com.cn.entity.Downloadfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Nolimitors on 2017/3/17.
 */
public class PagesServlet extends HttpServlet{


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         *@Author: Nolimitor
         *@Params:  * @param req
         * @param resp
         *@Date: 17:55 2017/3/17
         */
        doPost(req,resp);

    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /**
        *@Author: Nolimitor
        *@Params:  * @param req
        * @param resp
        *@Date: 17:55 2017/3/17
        */
        Properties props = new Properties();
        InputStream in = new BufferedInputStream(new FileInputStream(this.getClass().getResource("/fileroot.properties").getPath()));
        props.load(in);
        String rootPath = props.getProperty("Root");
        List fileList = new ArrayList();

            File file = new  File(rootPath);
            File []files = file.listFiles();
            Downloadfile df = new Downloadfile();
            for(File f:files) {
                df.setName(f.getName());
                df.setFilesize(Long.toString(f.length()));
                System.out.println(f.getName());
                fileList.add(JSON.toJSONString(df));
            }
        resp.addHeader("Content-type","application/json");
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        resp.getWriter().print(JSON.toJSONString(fileList));
    }
}
