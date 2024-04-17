package org

import java.util.Properties

import org.apache.spark.sql.{SaveMode, SparkSession}

object ResPlatform {
  def Analysis(id: Int): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .master("local")
      .appName("DataFrameFromStuctType")
      .getOrCreate()

    //如果是最普通的数据以英文逗号分隔的CSV文件
    //我们可以直接用spark.read.csv("C:\\Users\\Desktop\\people.csv")拉取
    //但是很多时候我们需要其他的设置，因此我用另一种方式给大家拉取
    val peopleDF = spark.read.format("csv")
      .option("sep", ",") //数据的分隔符
      .option("header", "true") //首行是否字段元数据
      .option("encoding", "utf-8")
      .load("hdfs://10.7.122.53:8020/flume/")
    //      .load("src\\main\\java\\com\\example\\douban_game\\fakeData\\words.csv")

    peopleDF.createOrReplaceTempView("people")
    val url = "jdbc:mysql://10.7.123.197:3306/douban?useUnicode=true&characterEncoding=utf-8"
    val prop = new Properties()
    prop.put("user", "root")
    prop.put("password", "123456")

    spark.sql("select " + id + " as rp_id ,sum(case when platform='PC' then 1 else 0 end) as one_0," +
      "sum(case when when platform='IOS' then 1 else 0 end) as two_0," +
      "sum(case when when platform='XBOX' then 1 else 0 end) as three_0," +
      "sum(case when when platform='PS4' then 1 else 0 end) as four_0," +
      " from people where kind like '%rolePlay%' ")
      .write.mode(SaveMode.Append).jdbc(url, "replatform", prop)
    spark.sql("select " + id + " as rp_id ,sum(case when platform='PC' then 1 else 0 end) as one_0," +
      "sum(case when when platform='IOS' then 1 else 0 end) as two_0," +
      "sum(case when when platform='XBOX' then 1 else 0 end) as three_0," +
      "sum(case when when platform='PS4' then 1 else 0 end) as four_0," +
      " from people where kind like '%advendure%' ")
      .write.mode(SaveMode.Append).jdbc(url, "replatform", prop)
    spark.sql("select " + id + " as rp_id ,sum(case when platform='PC' then 1 else 0 end) as one_0," +
      "sum(case when when platform='IOS' then 1 else 0 end) as two_0," +
      "sum(case when when platform='XBOX' then 1 else 0 end) as three_0," +
      "sum(case when when platform='PS4' then 1 else 0 end) as four_0," +
      " from people where kind like '%action%' ")
      .write.mode(SaveMode.Append).jdbc(url, "replatform", prop)
    spark.sql("select " + id + " as rp_id ,sum(case when platform='PC' then 1 else 0 end) as one_0," +
      "sum(case when when platform='IOS' then 1 else 0 end) as two_0," +
      "sum(case when when platform='XBOX' then 1 else 0 end) as three_0," +
      "sum(case when when platform='PS4' then 1 else 0 end) as four_0," +
      " from people where kind like '%alpinia%' ")
      .write.mode(SaveMode.Append).jdbc(url, "replatform", prop)






    spark.stop()
  }
}