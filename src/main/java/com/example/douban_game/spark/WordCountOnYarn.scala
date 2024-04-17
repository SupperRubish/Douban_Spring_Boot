package com.example.douban_game.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/*
*
* Spark测试--Yarn模式
*
* */

object WordCountOnYarn {

  def main(args: Array[String]): Unit = {

    if(args.length == 0){
      println("请指定input与output")
      System.exit(1)
    }

    //---Spark 环境
    val conf = new SparkConf().setAppName("wc")
    val sc : SparkContext = new SparkContext(conf)
    sc.setLogLevel("WARN")

    //---读取数据source
    //RDD-弹性分布式数据集
    val lines: RDD[String] = sc.textFile(args(0))  //提交任务时需要指定input参数

    //---数据转换
    //切割
    val words: RDD[String] = lines.flatMap(_.split(" "))
    //RDD[(单词，1)]
    val wordAndOnes: RDD[(String, Int)]=words.map((_,1))
    //分组聚合
    val res: RDD[(String, Int)] = wordAndOnes.reduceByKey(_ + _)

    //输出到本地路径
    res.repartition(1).saveAsTextFile(args(1))  //提交任务时需要指定output参数

    Thread.sleep(1000 * 60)

    //关闭资源
    sc.stop()

  }

}
