package org

import java.util.Properties

import org.apache.spark.sql.{SaveMode, SparkSession}


object Test3 {
  def main(args: Array[String]): Unit = {
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
      .load("src\\main\\java\\com\\example\\douban_game\\fakeData\\words.csv")

    peopleDF.createOrReplaceTempView("people")
//    val url = "jdbc:mysql://localhost:3306/douban?useUnicode=true&characterEncoding=utf-8"
//    val prop = new Properties()
//    prop.put("user", "root")
//    prop.put("password", "123456")

    spark.sql("select sum(case when Rate between 8.0 and 10.0 then 1 else 0 end) as 1_0," +
      "sum(case when Rate between 6.0 and 8.0 then 1 else 0 end) as 2_0," +
      "sum(case when Rate between 5.0 and 6.0 then 1 else 0 end) as 3_0," +
      "sum(case when Rate between 4.0 and 5.0 then 1 else 0 end) as 4_0," +
      "sum(case when Rate<4.0 then 1 else 0 end) as 5_0" +
      " from people ").show()



//
//
//    spark.sql("select"+"10"+" as rr_id ,sum(case when Rate between 8.0 and 10.0 then 1 else 0 end) as 1_0," +
//          "sum(case when Rate between 6.0 and 8.0 then 1 else 0 end) as 2_0," +
//          "sum(case when Rate between 5.0 and 6.0 then 1 else 0 end) as 3_0," +
//          "sum(case when Rate between 4.0 and 5.0 then 1 else 0 end) as 4_0," +
//          "sum(case when Rate<4.0 then 1 else 0 end) as 5_0" +
//          " from people ")
//      .write.mode(SaveMode.Append).jdbc(url, "resrating", prop)
//
//    spark.sql("select "+"10"+" as resr_id ,'action' as type,sum(case when Rate between 8.0 and 10.0 then 1 else 0 end) as 4_5," +
//      "sum(case when Rate between 6.0 and 8.0 then 1 else 0 end) as 4_6," +
//      "sum(case when Rate between 5.0 and 6.0 then 1 else 0 end) as 4_7," +
//      "sum(case when Rate between 4.0 and 5.0 then 1 else 0 end) as 4_8," +
//      "sum(case when Rate between 4.0 and 5.0 then 1 else 0 end) as 4_9," +
//      "sum(case when Rate=5.0 then 1 else 0 end) as 5_0" +
//      " from people where Kind like '%动作%'")
//      .write.mode(SaveMode.Append).jdbc(url, "reshighrating", prop)
//
//
//    spark.sql("select "+"10"+" as resr_id ,'roleplay' as type,sum(case when Rate between 8.0 and 10.0 then 1 else 0 end) as 4_5," +
//      "sum(case when Rate between 6.0 and 8.0 then 1 else 0 end) as 4_6," +
//      "sum(case when Rate between 5.0 and 6.0 then 1 else 0 end) as 4_7," +
//      "sum(case when Rate between 4.0 and 5.0 then 1 else 0 end) as 4_8," +
//      "sum(case when Rate between 4.0 and 5.0 then 1 else 0 end) as 4_9," +
//      "sum(case when Rate=5.0 then 1 else 0 end) as 5_0" +
//      " from people where Kind like '%角色扮演%'")
//      .write.mode(SaveMode.Append).jdbc(url, "reshighrating", prop)
//
//
//
//    spark.sql("select "+"10"+" as resr_id ,'adventure' as type,sum(case when Rate between 8.0 and 10.0 then 1 else 0 end) as 4_5," +
//      "sum(case when Rate between 6.0 and 8.0 then 1 else 0 end) as 4_6," +
//      "sum(case when Rate between 5.0 and 6.0 then 1 else 0 end) as 4_7," +
//      "sum(case when Rate between 4.0 and 5.0 then 1 else 0 end) as 4_8," +
//      "sum(case when Rate between 4.0 and 5.0 then 1 else 0 end) as 4_9," +
//      "sum(case when Rate=5.0 then 1 else 0 end) as 5_0" +
//      " from people where Kind like '%冒险%'")
//      .write.mode(SaveMode.Append).jdbc(url, "reshighrating", prop)
//
//    spark.sql("select "+"10"+" as resr_id ,'puzzle' as type,sum(case when Rate between 8.0 and 10.0 then 1 else 0 end) as 4_5," +
//      "sum(case when Rate between 6.0 and 8.0 then 1 else 0 end) as 4_6," +
//      "sum(case when Rate between 5.0 and 6.0 then 1 else 0 end) as 4_7," +
//      "sum(case when Rate between 4.0 and 5.0 then 1 else 0 end) as 4_8," +
//      "sum(case when Rate between 4.0 and 5.0 then 1 else 0 end) as 4_9," +
//      "sum(case when Rate=5.0 then 1 else 0 end) as 5_0" +
//      " from people where Kind like '%益智%'")
//      .write.mode(SaveMode.Append).jdbc(url, "reshighrating", prop)



//      spark.sql("select "+"10"+" as resr_id ,'puzzle' as type,sum(case when Rate between 8.0 and 10.0 then 1 else 0 end) as 4_5," +
//        "sum(case when Rate between 6.0 and 8.0 then 1 else 0 end) as 4_6," +
//        "sum(case when Rate between 5.0 and 6.0 then 1 else 0 end) as 4_7," +
//        "sum(case when Rate between 4.0 and 5.0 then 1 else 0 end) as 4_8," +
//        "sum(case when Rate between 4.0 and 5.0 then 1 else 0 end) as 4_9," +
//        "sum(case when Rate=5.0 then 1 else 0 end) as 5_0" +
//        " from people where Kind like '%益智%'")
//        .write.mode(SaveMode.Append).jdbc(url, "reshighrating", prop)

//    spark.sql("select "+"10"+" as rr_id ,sum(case when Rate between 8.0 and 10.0 then commentNum else 0 end) as 1_0," +
//      "sum(case when Rate between 6.0 and 8.0 then commentNum else 0 end) as 2_0," +
//      "sum(case when Rate between 5.0 and 6.0 then commentNum else 0 end) as 3_0," +
//      "sum(case when Rate between 4.0 and 5.0 then commentNum else 0 end) as 4_0," +
//      "sum(case when Rate<4.0 then commentNum else 0 end) as 5_0" +
//      " from people ").show()

    spark.sql("select "+"2"+" as resl_id ,'action' as type,sum(case when people.Year between 1998 and 2001 then 1 else 0 end) as _1998," +
      "sum(case when people.Year between 2001 and 2004 then 1 else 0 end) as _2001," +
      "sum(case when people.Year between 2004 and 2007 then 1 else 0 end) as _2004," +
      "sum(case when people.Year between 2007 and 2010 then 1 else 0 end) as _2007," +
      "sum(case when people.Year between 2010 and 2013 then 1 else 0 end) as _2010," +
      "sum(case when people.Year between 2013 and 2016 then 1 else 0 end) as _2013," +
      "sum(case when people.Year between 2016 and 2019 then 1 else 0 end) as _2016," +
      "sum(case when people.Year between 2019 and 2022 then 1 else 0 end) as _2019," +
      "sum(case when people.Year=2022 then 1 else 0 end) as _2022" +

      " from people where people.kind like '%action%'").show()

//    spark.sql("select publishedYear from people where kind like '%action%' ").show()



    spark.stop()

  }

}
