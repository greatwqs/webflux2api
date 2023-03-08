package com.greatwqs.app.config;

import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.servlet.annotation.WebServlet;

/**
 * JspConfig
 * 访问 JSP 自动解析
 *
 * @author wang.qingsong
 * Create on 2023/03/08
 */
@ServletComponentScan
@WebServlet(urlPatterns = "*.jsp", name = "JspConfig")
public class JspConfig extends org.apache.jasper.servlet.JspServlet {
}