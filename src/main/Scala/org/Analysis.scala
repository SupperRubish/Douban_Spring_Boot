package org

import java.util.Properties

import org.apache.spark.sql.{SaveMode, SparkSession}

object Analysis {
  def analysis(sparkSession: SparkSession,path:String): Unit ={
    println("执行操作")
    val spark = sparkSession
    val csvDf = spark
      .read
      .format("csv")
      .option("header", "true") //表示第一行是列的名称
      .option("multiLine", "true")
      .option("inferSchema", "true") //读取文件时是否自动判断列类型
      .load(s"hdfs://hadoop102:9000/flume/${path}")
    //3.DataFrame注册成表
    csvDf.createTempView("csv_data")
    val url = "jdbc:mysql://localhost:3306/douban?characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai"
    val prop = new Properties()
    prop.put("user", "root")
    prop.put("password", "123456")

//    spark.sql(s"select type, avg(unit_price) avg_price, count(*) count ,zone, ${p} historyId ,${k} userId from csv_data group by zone,type order by type")
//      .write.mode(SaveMode.Append).jdbc(url, "one", prop)
  }

}
