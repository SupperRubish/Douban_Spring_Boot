//package com.example.douban_game.config;
//
//import org.apache.spark.SparkConf;
//import org.apache.spark.api.java.JavaSparkContext;
//import org.apache.spark.sql.SparkSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
//
//@Configuration
//public class SparkConfig {
//
//    @Value("${spark.app.name}")
//    private String appName;
//    @Value("${spark.master.uri}")
//    private String sparkMasterUri;
//    @Value("${spark.driver.memory}")
//    private String sparkDriverMemory;
//    @Value("${spark.worker.memory}")
//    private String sparkWorkerMemory;
//    @Value("${spark.executor.memory}")
//    private String sparkExecutorMemory;
//    @Value("${spark.rpc.message.maxSize}")
//    private String sparkRpcMessageMaxSize;
//
//    @Bean
//    public SparkConf sparkConf() {
//        return new SparkConf()
//                .setAppName(appName)
//                .setMaster(sparkMasterUri)
//                .set("spark.driver.memory",sparkDriverMemory)
//                .set("spark.worker.memory",sparkWorkerMemory) //"26g"
//                .set("spark.executor.memory",sparkExecutorMemory)
//                .set("spark.rpc.message.maxSize",sparkRpcMessageMaxSize);
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(JavaSparkContext.class)
//    public JavaSparkContext javaSparkContext(){
//        return new JavaSparkContext(sparkConf());
//    }
//
//    @Bean
//    public SparkSession sparkSession(){
//        return SparkSession
//                .builder()
//                .sparkContext(javaSparkContext().sc())
//                .appName(appName)
//                .getOrCreate();
//    }
//
//}
