package com.puhui.app.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;

/**
 * dongchen
 */
@Component
public class OauthService {
    @Autowired
    @Qualifier("restTemplate")
    private OAuth2RestTemplate restTemplate;

    /**
     * <p>
     * Title:
     * </p>
     * <p>s
     * Description:构造方法
     * </p>
     *
     * @param restTemplate
     */
    public OauthService(OAuth2RestTemplate restTemplate) {
        super();
        this.restTemplate = restTemplate;
    }

    /**
     * <p>
     * Title:
     * </p>
     * <p>
     * Description:构造方法
     * </p>
     */
    public OauthService() {
        super();
    }

    /**
     * @auth:dongchen @Title: jsonHead @Description:
     * 封装HttpEntity信息 @param @param request @param @return 设定文件 @return
     * HttpEntity 返回类型 @throws
     */
    private HttpEntity<String> jsonHead(Object request) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        return new HttpEntity<>(JSON.toJSONString(request), headers);
    }

    /**
     * @auth:dongchen @Title: jsonPost @Description: rest post请求
     * 参数为object @param @param url @param @param request @param @param
     * responseType @param @param uriVariables @param @param
     * authHeader @param @return 设定文件 @return T 返回类型 @throws
     */
    public <T> T jsonPost(String url, Object request, Class<T> responseType, Object[] uriVariables) {
        ResponseEntity<T> res = this.restTemplate.postForEntity(url, jsonHead(request), responseType,
                uriVariables);
        Assert.isTrue(res.getStatusCode() == HttpStatus.OK, "Failed: HTTP error code: " + res.getStatusCode()// NOSONAR
                + ", error body: " + res.getBody() + ", request url is :" + url);// NOSONAR
        return res.getBody();
    }

    /**
     * @auth:dongchen @Title: jsonPost @Description: rest post请求
     * 参数为map @param @param url @param @param request @param @param
     * responseType @param @param urlVariables @param @param
     * authHeader @param @return 设定文件 @return T 返回类型 @throws
     */
    public <T> T jsonPost(String url, Object request, Class<T> responseType, Map<String, ?> urlVariables) {
        ResponseEntity<T> res = this.restTemplate.postForEntity(url, jsonHead(request), responseType,
                urlVariables);
        Assert.isTrue(res.getStatusCode() == HttpStatus.OK, "Failed: HTTP error code: " + res.getStatusCode()
                + ", error body: " + res.getBody() + ", request url is :" + url);
        return res.getBody();
    }

    /**
     * @auth:dongchen @Title: jsonPost @Description: rest post请求 @param @param
     * url @param @param request @param @param responseType @param @param
     * authHeader @param @return 设定文件 @return T 返回类型 @throws
     */
    public <T> T jsonPost(String url, Object request, Class<T> responseType) {
        ResponseEntity<T> res = this.restTemplate.postForEntity(url, jsonHead(request), responseType);
        Assert.isTrue(res.getStatusCode() == HttpStatus.OK, "Failed: HTTP error code: " + res.getStatusCode()
                + ", error body: " + res.getBody() + ", request url is :" + url);
        return res.getBody();
    }
    
    public <T> T jsonPost(String url, Object request, ParameterizedTypeReference<T> responseType) {
        ResponseEntity<T> res = this.restTemplate.exchange(url, HttpMethod.POST, jsonHead(request), responseType);
        Assert.isTrue(res.getStatusCode() == HttpStatus.OK, "Failed: HTTP error code: " + res.getStatusCode()
                + ", error body: " + res.getBody() + ", request url is :" + url);
        return res.getBody();
    }
    
    /**
     * json put请求
     * 
     * @author yhl
     * @param url
     * @param uriVariables
     */
    public void jsonPut(String url, Object[] uriVariables) {
        ResponseEntity<String> res = this.restTemplate.exchange(url, HttpMethod.PUT, null, String.class,
                uriVariables);
        Assert.isTrue(res.getStatusCode() == HttpStatus.OK, "Failed: HTTP error code: " + res.getStatusCode()
                + ", error body: " + res.getBody() + ", request url is :" + url);
    }

    /**
     * @auth:dongchen @Title: jsonPut @Description: 封装put请求 rest服务 @param @param
     * url @param @param request @param @param uriVariables @param @param
     * authHeader 设定文件 @return void 返回类型 @throws
     */
    public void jsonPut(String url, Object request, Object[] uriVariables) {
        ResponseEntity<String> res = this.restTemplate.exchange(url, HttpMethod.PUT, jsonHead(request),
                String.class, uriVariables);
        Assert.isTrue(res.getStatusCode() == HttpStatus.OK, "Failed: HTTP error code: " + res.getStatusCode()
                + ", error body: " + res.getBody() + ", request url is :" + url);
    }

    /**
     * @auth:dongchen @Title: jsonPut @Description: 封装put请求
     * rest服务地址有参数且为map @param @param url @param @param request @param @param
     * urlVariables @param @param authHeader 设定文件 @return void 返回类型 @throws
     */
    public void jsonPut(String url, Object request, Map<String, ?> urlVariables) {
        ResponseEntity<String> res = this.restTemplate.exchange(url, HttpMethod.PUT, jsonHead(request),
                String.class, urlVariables);
        Assert.isTrue(res.getStatusCode() == HttpStatus.OK, "Failed: HTTP error code: " + res.getStatusCode()
                + ", error body: " + res.getBody() + ", request url is :" + url);
    }

    /**
     * @auth:dongchen @Title: jsonPut @Description: 封装put请求
     * rest服务地址无参方法 @param @param url @param @param request @param @param
     * authHeader 设定文件 @return void 返回类型 @throws
     */
    public void jsonPut(String url, Object request) {
        ResponseEntity<String> res = this.restTemplate.exchange(url, HttpMethod.PUT, jsonHead(request),
                String.class);
        Assert.isTrue(res.getStatusCode() == HttpStatus.OK, "Failed: HTTP error code: " + res.getStatusCode()
                + ", error body: " + res.getBody() + ", request url is :" + url);
    }

    /**
     * @auth:dongchen @Title: JsonGet @Description: 封装rest服务get请求
     * 参数为object调用 @param @param url HTTP地址 @param @param responseType
     * 返回类型(例如：String.class) @param @param uriVariables 参数类型可变参数 @param @return
     * 设定文件 @return T 返回类型 @throws
     */
    public <T> T jsonGet(String url, Class<T> responseType, Object[] uriVariables) {
        ResponseEntity<T> res = this.restTemplate.exchange(url, HttpMethod.GET, null, responseType, uriVariables);
        Assert.isTrue(res.getStatusCode() == HttpStatus.OK, "Failed: HTTP error code: " + res.getStatusCode()
                + ", error body: " + res.getBody() + ", request url is :" + url);
        return res.getBody();
    }
    
    public <T> T jsonGet(String url, ParameterizedTypeReference<T> responseType, Object[] uriVariables) {
        ResponseEntity<T> res = this.restTemplate.exchange(url, HttpMethod.GET, null, responseType, uriVariables);
        Assert.isTrue(res.getStatusCode() == HttpStatus.OK, "Failed: HTTP error code: " + res.getStatusCode()
        + ", error body: " + res.getBody() + ", request url is :" + url);
        return res.getBody();
    }

    /**
     * @auth:dongchen @Title: JsonGet @Description: 封装rest服务get请求
     * 参数为map调用 @param @param url HTTP地址 @param @param responseType
     * 返回类型(例如：String.class) @param @param urlVariables 参数为map @param @return
     * 设定文件 @return T 返回类型 @throws
     */
    public <T> T jsonGet(String url, Class<T> responseType, Map<String, ?> urlVariables) {
        ResponseEntity<T> res = this.restTemplate.exchange(url, HttpMethod.GET, null, responseType, urlVariables);
        Assert.isTrue(res.getStatusCode() == HttpStatus.OK, "Failed: HTTP error code: " + res.getStatusCode()
                + ", error body: " + res.getBody() + ", request url is :" + url);
        return res.getBody();
    }

    /**
     * @auth:dongchen @Title: JsonGet @Description: 封装rest服务get请求
     * 无参调用 @param @param url HTTP地址 @param @param responseType
     * 返回类型(例如：String.class) @param @return 设定文件 @return T 返回类型 @throws
     */
    public <T> T jsonGet(String url, Class<T> responseType) {
        ResponseEntity<T> res = this.restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        Assert.isTrue(res.getStatusCode() == HttpStatus.OK, "Failed: HTTP error code: " + res.getStatusCode()
                + ", error body: " + res.getBody() + ", request url is :" + url);
        return res.getBody();
    }

    /**
     * @auth:dongchen @Title: JsonDelete @Description: 封装rest服务delete请求
     * 无参调用 @param @param url HTTP地址 @return void 返回类型 @throws
     */
    public void jsonDelete(String url) {
        ResponseEntity<String> res = this.restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
        Assert.isTrue(res.getStatusCode() == HttpStatus.OK, "Failed: HTTP error code: " + res.getStatusCode()
                + ", error body: " + res.getBody() + ", request url is :" + url);
    }

    /**
     * @auth:dongchen @Title: JsonDelete @Description: 封装rest服务delete请求
     * 参数为map @param @param url HTTP地址 @param @param urlVariables map参数 @return
     * void 返回类型 @throws
     */
    public void jsonDelete(String url, Map<String, ?> urlVariables) {
        ResponseEntity<String> res = this.restTemplate.exchange(url, HttpMethod.DELETE, null, String.class,
                urlVariables);
        Assert.isTrue(res.getStatusCode() == HttpStatus.OK, "Failed: HTTP error code: " + res.getStatusCode()
                + ", error body: " + res.getBody() + ", request url is :" + url);
    }

    /**
     * @auth:dongchen @Title: jsonDelete @Description:封装rest服务delete请求
     * 参数为任意object @param @param url @param @param uriVariables 设定文件 @return
     * void 返回类型 @throws
     */
    public void jsonDelete(String url, Object[] uriVariables) {
        ResponseEntity<String> res = this.restTemplate.exchange(url, HttpMethod.DELETE, null, String.class,
                uriVariables);
        Assert.isTrue(res.getStatusCode() == HttpStatus.OK, "Failed: HTTP error code: " + res.getStatusCode()
                + ", error body: " + res.getBody() + ", request url is :" + url);
    }
}