package example

import org.apache.spark.sql.SparkSession

import org.apache.spark.sql.Row
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._

object HdfsWriteReadDemo {
    
        // Save Dataframe to HDFS
        println("Saving Dataframe to HDFS...")
        dataFrame.write
            .mode("overwrite")
            .option("sep", ",")
            .option("header", "true")
            .csv("hdfs://sandbox-hdp.hortonworks.com:8020/user/maria_dev/RNGcharacters/rngCharSet1.csv")

        // Create and Show Dataframe by reading csv from HDFS
        println("Showing DataFrame by reading csv from HDFS...")
        val dfFromHDFS = spark.read
            .option("header", "true")
            .csv("hdfs://sandbox-hdp.hortonworks.com:8020/user/maria_dev/RNGcharacters/rngCharSet1.csv")
        
        println("Reading rngCharSet1.csv")
        dfFromHDFS.show()

        // Show Dataframe by using .show() on val dataFrame
        // println("Showing DataFrame...")
        // dataFrame.show()

        // Use .collect() on dataframe to make it an Array, then println foreach element in the Array
        println("Printing each collection of Array")
        val result1 = dfFromHDFS.collect()
        result1.foreach(println)

        // print a specific value from result1 matrix
        println(result1(0)(2))

        // Assign specific row to new list 
        var newListId1: List[Row] = List(result1(0)) 
        println(newListId1)

        // Assign specific list values to variables, remember these lists must be indexed like a matrix, which is what they are
        val newID = newListId1(0)(0)
        println(newID)
}
