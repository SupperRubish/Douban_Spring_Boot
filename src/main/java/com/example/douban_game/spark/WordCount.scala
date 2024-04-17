package com.example.douban_game.spark


import com.example.douban_game.fakeData.test1
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/*
* Spark测试--local模式
* */

object WordCount {

  def main(args: Array[String]): Unit = {

    val t1 = new test1();

    //---Spark 环境
    val conf = new SparkConf().setAppName("wc").setMaster("")
    val sc : SparkContext = new SparkContext(conf)
    //sc.setLogLevel("WARN")

    //---读取数据source
    //RDD-弹性分布式数据集
    val lines: RDD[String] = sc.textFile("data/input/words.csv")

    //---数据转换
    //切割
    val words: RDD[String] = lines.flatMap(_.split(" "))
    //RDD[(单词，1)]
    val wordAndOnes: RDD[(String, Int)]=words.map((_,1))
    //分组聚合
    val res: RDD[(String, Int)] = wordAndOnes.reduceByKey(_ + _)

    //---结果输出
    //直接输出
    //res.foreach(println)
    //收集为本地集合再输出 toBuffer-数组输出
    //System.err.println(res.collect().toBuffer)
    //输出到本地文件
    //res.repartition(1).saveAsTextFile("data/output/res")

//    for (data <- res.collect()){
//      System.err.println("============================"+data)
//      t1.WriteLineContent("data/output/result.txt", data)
//    }

  }

}
