package example

object StockAnalyzer {
    def main(args: Array[String]){
        Space.printSpace
        println("Welcome to Hive Stock Analyzer")
        
        while(status == true){
            menu
        }
    }

    var status = true

    var selection = 0

    def menu: Unit = {
        Space.printSpace
        println("Make a Selection")
        println("1: API Request")
        println("2: Query Stock Tables")
        println("3: Exit Program")

        selection = scala.io.StdIn.readInt()

        selection match{
            case 1 =>   CreateHdfsFile.getApiData
            case 2 =>   HiveConnect.hiveQuery
            case 3 =>   status = false
            case _ =>   println("Invalid Selection")
                        menu
        }
    }
}
