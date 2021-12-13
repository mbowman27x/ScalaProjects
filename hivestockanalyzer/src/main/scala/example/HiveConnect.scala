package example

import scala.util.Try
import java.io.IOException
import java.sql.SQLException
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement
import java.sql.DriverManager

object HiveConnect {

    def hiveLoad = {

        Space.printSpace
        println("Connecting to Hive")
        
        var connection: Connection = null

        try{
            val driverName = "org.apache.hive.jdbc.HiveDriver"
            val connectionString = "jdbc:hive2://sandbox-hdp.hortonworks.com:10000/stocksdatabase"

            Class.forName(driverName)
            connection = DriverManager.getConnection(connectionString, "", "")

            val statement = connection.createStatement()
            var hiveCommand = ""    
            //  statement.execute(hiveCommand) -> for executing hive commands

            println("Creating Table: " + CreateHdfsFile.symbol + " Table")

            // drop table if it exists
            hiveCommand = ("DROP TABLE IF EXISTS " + CreateHdfsFile.symbol)
            statement.execute(hiveCommand)

            // create table
            hiveCommand = ("CREATE TABLE " + CreateHdfsFile.symbol + "(time String, open String, high String, low String, close String, volume String) " +
                           "row format delimited " +
                           "fields terminated by ','")
            statement.execute(hiveCommand)

            println(CreateHdfsFile.symbol + " Table Created")

            // load data into table
            hiveCommand = ("LOAD DATA INPATH '/user/maria_dev/StocksFolder/" + CreateHdfsFile.symbol + "_File.csv' INTO TABLE " + CreateHdfsFile.symbol)
            statement.execute(hiveCommand)

        }catch{
            case ex: Throwable => {
                ex.printStackTrace();
                throw new Exception(s"${ex.getMessage}")
            }
        }finally{
            try{
                if(connection != null)
                connection.close();
            }catch{
                case ex: Throwable => {
                    ex.printStackTrace();
                    throw new Exception(s"${ex.getMessage}")
                }
            }
        }
    }

    def hiveQuery = {

        Space.printSpace
        
        var connection: Connection = null

        try{
            val driverName = "org.apache.hive.jdbc.HiveDriver"
            val connectionString = "jdbc:hive2://sandbox-hdp.hortonworks.com:10000/stocksdatabase"

            Class.forName(driverName)
            connection = DriverManager.getConnection(connectionString, "", "")

            val statement = connection.createStatement()
            var hiveCommand = ""    
            //  statement.execute(hiveCommand) -> for executing hive commands


        }catch{
            case ex: Throwable => {
                ex.printStackTrace();
                throw new Exception(s"${ex.getMessage}")
            }
        }finally{
            try{
                if(connection != null)
                connection.close();
            }catch{
                case ex: Throwable => {
                    ex.printStackTrace();
                    throw new Exception(s"${ex.getMessage}")
                }
            }
        }
    }  
}
