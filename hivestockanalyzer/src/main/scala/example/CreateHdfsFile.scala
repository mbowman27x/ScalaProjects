package example

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.Path
import java.io.PrintWriter

object CreateHdpFile {
    var symbol = ""

    def tickerSelection = {
        println("Enter Stock Ticker")
        symbol = scala.io.StdIn.readLine()
    }

    def getApiData = {
        tickerSelection

        val url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&datatype=csv&apikey=4TZZOJKGYN1WMDJ9"
        val result = scala.io.Source.fromURL(url).mkString

        println("Retrieving Data from API Pipeline...")
        createFile(result)

        HiveConnect.hiveLoad
    }

    val hdfsPath = "hdfs://sandbox-hdp.hortonworks.com:8020/user/maria_dev/StocksFolder/"

    def createFile(csv: String) = {
        val fileName = hdfsPath + symbol + "_File.csv"
        println("Creating file: " + symbol + "_File.csv")

        val configuration = new Configuration()
        val fileSystem = FileSystem.get(configuration)

        println("Checking if file exists")
        val filePath = new Path(fileName)
        val isExisting = fileSystem.exists(filePath)

        if(isExisting){
            println("File does exist. Deleting File...")
            fileSystem.delete(filePath, false)
        }
        
        val output = fileSystem.create(new Path(fileName))

        val writer = new PrintWriter(output)
        writer.write(csv)
        writer.close()
        
        println(symbol + "_File.csv written")
    }
}
