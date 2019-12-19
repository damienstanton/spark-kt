package com.damienstanton.spark

import org.apache.spark.sql.SparkSession

class Main {
    companion object {
        private var file = ""
        @JvmStatic
        fun main(args: Array<String>) {
            if (args.isEmpty()) {
                this.file = this::class.java.getResource("/words.txt").toString()
            } else {
                this.file = args[0]
            }
            val spark = SparkSession
                .builder()
                .appName("Kotlin Smoke Test")
                .config("spark.master", "local")
                .orCreate
            val logData = spark
                .read()
                .textFile(this.file)
                .cache()

            val numAs = logData.filter { it.contains("a") }.count()
            val numBs = logData.filter { it.contains("b") }.count()

            println("A count: $numAs, B count: $numBs")
            spark.stop()
        }
    }
}