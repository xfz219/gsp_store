package com.puhui.app;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.junit.BeforeClass;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;

import javax.naming.NamingException;

/**
 * 基础测试类
 * Created by yhl
 */
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class BaseTest {
    /**
     * 初始化jndi
     */
    @BeforeClass
    public static void init() {
        try {
            MysqlDataSource ds = new MysqlDataSource();
            ds.setUrl("jdbc:mysql://10.10.231.135:3306/lend_app");
            ds.setUser("root");
            ds.setPassword("OYLDASuPfbpsEQB6");
            SimpleNamingContextBuilder builder =new SimpleNamingContextBuilder();
            builder.bind("jdbc/puhui", ds);
            builder.activate();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
