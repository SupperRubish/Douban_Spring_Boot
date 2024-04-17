package com.example.douban_game.Log;



import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class Slf4jTest {

    // 为了保证使用时，不需要每次都去创建logger 对象，我们声明静态常量
    public static final Logger LOGGER = LoggerFactory.getLogger(Slf4jTest.class);

    // 快速入门
    @Test
    public void testQuick() {
        // 日志输出
        for (int i = 0; i < 1000000; i++) {
            LOGGER.error("error");
            LOGGER.warn("warning");
            LOGGER.info("info");    // 默认的日志级别信息
            LOGGER.debug("debug");
            LOGGER.trace("trace");  // 追踪信息
        }


    }

}
