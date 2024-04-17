package org

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

object Test2 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[8]").setAppName("sparkCSV")

    val session: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()

    session.sparkContext.setLogLevel("WARN")
    val frame: DataFrame = session
      .read
      .option("inferSchema", "true")
      .format("csv")
      .option("timestampFormat", "yyyy/MM/dd HH:mm:ss ZZ")
      .option("header", "true")
      .option("delimiter","@")
      .option("multiLine","true")
      .option("ignoreLeadingWhiteSpace", true)
      .option("multiLine", true)
      .load("file:///D:\\专综2\\test.csv")

    frame.createOrReplaceTempView("job_detail")
    session.sql("select * from job_detail  ").show(80)

    print(frame)


  }

}
