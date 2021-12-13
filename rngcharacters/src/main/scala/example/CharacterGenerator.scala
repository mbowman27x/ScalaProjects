package example

import org.apache.spark.sql.SparkSession

import org.apache.spark.sql.Row
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._

object CharacterGenerator {
    
    val spark: SparkSession = SparkSession
        .builder()
        .master("local[1]")
        .appName("RNG")
        .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    import spark.implicits._

    // create a List of Row Datatypes, this is a matrix (a collection of Arrays)
    var characterData: List[Row] = List()

    val schema = StructType(
        Array(
            StructField("id", IntegerType, true),
            StructField("race", StringType, true),
            StructField("Strength", IntegerType, true),
            StructField("Defense", IntegerType, true),
            StructField("Range", IntegerType, true),
            StructField("Magic", IntegerType, true),
            StructField("Stamina", IntegerType, true),
            StructField("Coins", IntegerType, true)
        )
    )

    var id = 0
    var race = ""
    var raceSeq: Seq[String] = Seq("Human", "Orc", "Wizard", "Elf")
    var strength = 0
    var defense = 0
    var range = 0
    var magic = 0
    var stamina = 0
    var coins = 0

    var RNG = scala.util.Random
    var x = 0

    var selection = 0

    def runCharacterGenerator = {

        for(y <- 1 to 100){

            id = id + 1

            x = RNG.nextInt(4)
            race = raceSeq(x)

            strength = RNG.nextInt(99)
            defense = RNG.nextInt(99)
            range = RNG.nextInt(99)
            magic = RNG.nextInt(99)
            stamina = RNG.nextInt(99)

            var newList: List[Row] = List(Row(id, race, strength, defense, range, magic, stamina, coins))

            characterData = characterData ++ newList
        }

        val rdd = spark.sparkContext.parallelize(characterData)

        println("Creating Character DataFrame...")

        val dataFrame = spark.createDataFrame(rdd, schema)

        dataFrame.show()

        def menuSelection: Unit = {
            println("Make a Selection")
            println("1: Show Total Count for each Race")
            println("2: Show Avg Strength for each Race")
            println("3: Show Character with Max Magic")
            println("4: Show Characters where Stamina greater than 90")
            println("9: Exit")

            selection = scala.io.StdIn.readInt()

            selection match{
                case 1 =>   println("Grouping by Race, then counting total")
                            dataFrame.groupBy("race").count().show(false)
                            menuSelection

                case 2 =>   println("Grouping by Race, then averaging strength")
                            dataFrame.groupBy("race").avg("strength").show(false)
                            menuSelection  

                case 3 =>   println("Showing Character with Max Magic")
                            dataFrame.orderBy(col("magic").desc).show(1, false)
                            menuSelection  

                case 4 =>   println("Showing Characters with Stamina greater than 70")
                            dataFrame.orderBy(col("stamina").desc).where(col("stamina") > 90).show(false)
                            menuSelection

                case 9 =>   

                case _ =>   println("Invalid Selection")
                            menuSelection
            }
        }

        menuSelection

        // stops Spark Session
        spark.stop
    }
}
