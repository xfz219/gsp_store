/**
 * Copyright(c) 2013-2013 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.puhui.app.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 基本控制类
 * @author xiaobowen
 *
 */
public class BaseController {


    /**
     * 处理时间的转换器，默认格式是yyyy-MM-dd HH:mm:ss可以被重载
     * 
     * @author xiaobowen
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
    
    @ExceptionHandler(value=RuntimeException.class)
    public ResponseEntity<String> exception(Exception e){
    	  String contentType = "text/plain;charset=UTF-8";
          HttpHeaders responseHeaders = new HttpHeaders();
          responseHeaders.set("Content-Type", contentType);
          return new ResponseEntity<String>(e.getMessage(), responseHeaders,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}