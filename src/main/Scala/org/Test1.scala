package org

import org.apache.hadoop.conf.Configuration
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

object Test1 {
  def analysis(): Unit ={
    println("执行操作")
//org.Test1.analysis
    val conf = new SparkConf().setMaster("local[*]").setAppName("kafka")
    conf.set("dfs.client.use.datanode.hostname", "true");
//    conf.set("spark.testing.memory", "571859200")
//     conf.set("fs.defaultFS", "hdfs://121.41.116.141:8020");
    val sc= new SparkContext(conf)
//    val rrd=sc.textFile("hdfs://121.41.116.141:8020/lrx/input/words.csv")
    val lines: RDD[String] = sc.textFile(path = "hdfs://10.7.122.53:8020/lrx/output/res1/part-00000")
    lines.collect().foreach(println)
    sc.stop()


//    val conf = new SparkConf().setMaster("local[*]").setAppName("read kp data to kafka") //默认分为两个分区
//    val sc= new SparkContext(conf)
//    //支持通配符路径，支持压缩文件读取
//    val rrd=sc.textFile("hdfs://hadoop102:8020/comprehension/2022-09-17")




//        val conf: SparkConf = new SparkConf().setAppName("hdfsFile").setMaster("local")
//    val sc = new SparkContext(conf)
//    val lines: RDD[String] = sc.textFile("hdfs://121.41.116.141:8020/lrx/input/words.csv")
//    val words: RDD[String] = lines.flatMap(_.split(","))
//    val A: RDD[(String, Int)] = words.map((_,1))
//    val B: RDD[(String, Int)] = A.reduceByKey(_+_)
////        B.saveAsTextFile("hdfs://hdp-1:9000/aaa.txt")
//    B.foreach(println)
//    println(lines)

  }



}
