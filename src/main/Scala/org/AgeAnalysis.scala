package org

import java.util.Properties

import org.apache.spark.sql.{SaveMode, SparkSession}

object AgeAnalysis {
  def ageAnalysis(id:Int): Unit ={
    val spark: SparkSession = SparkSession
      .builder()
      .master("local")
      .appName("DataFrameFromStuctType")
      .getOrCreate()

    //如果是最普通的数据以英文逗号分隔的CSV文件
    //我们可以直接用spark.read.csv("C:\\Users\\Desktop\\people.csv")拉取
    //但是很多时候我们需要其他的设置，因此我用另一种方式给大家拉取
    val peopleDF = spark.read.format("csv")
      .option("sep", ",")//数据的分隔符
      .option("header", "true")//首行是否字段元数据
      .option("encoding","utf-8")
//      .load("hdfs://10.7.122.53:8020/flume/")
          .load("hdfs://10.7.122.53:8020/flume/")

    peopleDF.createOrReplaceTempView("people")
    val url = "jdbc:mysql://10.7.123.197:3306/douban?useUnicode=true&characterEncoding=utf-8"
    val prop = new Properties()
    prop.put("user", "root")
    prop.put("password", "123456")



//    spark.sql("select * from people").show()

    spark.sql("select "+id+" as ra_id ,sum(case when people.publishedYear between 1998 and 2001 then 1 else 0 end) as year_1998," +
      "sum(case when people.publishedYear between 2001 and 2004 then 1 else 0 end) as year_2001," +
      "sum(case when people.publishedYear between 2004 and 2007 then 1 else 0 end) as year_2004," +
      "sum(case when people.publishedYear between 2007 and 2010 then 1 else 0 end) as year_2007," +
      "sum(case when people.publishedYear between 2010 and 2013 then 1 else 0 end) as year_2010," +
      "sum(case when people.publishedYear between 2013 and 2016 then 1 else 0 end) as year_2013," +
      "sum(case when people.publishedYear between 2016 and 2019 then 1 else 0 end) as year_2016," +
      "sum(case when people.publishedYear between 2019 and 2022 then 1 else 0 end) as year_2019," +
      "sum(case when people.publishedYear='2022' then 1 else 0 end) as year_2022" +
      " from people")
      .write.mode(SaveMode.Append).jdbc(url, "resage", prop)
    spark.stop()}

}
