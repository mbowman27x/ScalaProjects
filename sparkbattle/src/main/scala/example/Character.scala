package example

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.Row
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._

object Character {
    
    val spark: SparkSession = SparkSession
        .builder()
        .master("local[1]")
        .appName("Character")
        .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    import spark.implicits._

    // Create Empty List of Row Datatypes
    var characterData: List[Row] = List()

    val schema = StructType(
        Array(
            StructField("ID", IntegerType, true),
            StructField("Name", StringType, true),
            StructField("Race", StringType, true),
            StructField("HP", IntegerType, true),
            StructField("Strength", IntegerType, true),
            StructField("Defense", IntegerType, true),
            StructField("Range", IntegerType, true),
            StructField("Magic", IntegerType, true),
            StructField("Stamina", IntegerType, true),
            StructField("Coins", IntegerType, true)
        )
    )

    // Create variables for holding character stats
    var id: Any = 1
    var name: Any = ""
    var race: Any = ""
    var raceSeq: Seq[String] = Seq("Human", "Orc", "Wizard", "Elf")
    var hp: Any = 100
    var strength: Any = 10
    var defense: Any = 10
    var range: Any = 10
    var magic: Any = 10
    var stamina: Any = 10
    var coins: Any = 100

    var selection = 0

    def runCharacterGenerator = {

        println("Enter a name for your character")
        name = scala.io.StdIn.readLine()
        println("Welcome " + name)

        def raceSelection: Unit = {
            println("Select Race")
            println("1: " + raceSeq(0))
            println("2: " + raceSeq(1))
            println("3: " + raceSeq(2))
            println("4: " + raceSeq(3))
            
            selection = scala.io.StdIn.readInt()

            selection match{
                case 1 =>   race = raceSeq(0)
                case 2 =>   race = raceSeq(1)
                case 3 =>   race = raceSeq(2)
                case 4 =>   race = raceSeq(3)
                case _ =>   println("Invalid Selection, enter a valid number")
                            raceSelection
            }
        }

        raceSelection

        saveCharacter
    }

    def saveCharacter = {

        // Re-assign characterData as new List with new values
        characterData = List(Row(id, name, race, hp, strength, defense, range, magic, stamina, coins))

        val rdd = spark.sparkContext.parallelize(characterData)

        // Create Character DataFrame
        println("Saving Character " + name)
        val dataFrame = spark.createDataFrame(rdd, schema)

        // Save Dataframe to HDFS
        println("Saving Character to HDFS...")
        dataFrame.write
            .mode("overwrite")
            .option("sep", ",")
            .option("header", "true")
            .csv("hdfs://sandbox-hdp.hortonworks.com:8020/user/maria_dev/RNGcharacters/character1.csv")

        dataFrame.show()
    }

    def loadCharacter = {

        // Create and Show Dataframe by reading csv from HDFS
        println("Loading Character from HDFS")
        val dfFromHDFS = spark.read
            .option("header", "true")
            .csv("hdfs://sandbox-hdp.hortonworks.com:8020/user/maria_dev/RNGcharacters/character1.csv")
        
        println("Reading Character CSV File")
        dfFromHDFS.show()

        // Create an Array from Dataframe
        val characterArray = dfFromHDFS.collect()

        // Re-assign character variables to indexes of val characterArray
        id = characterArray(0)(0)
        name = characterArray(0)(1)
        race = characterArray(0)(2)
        hp = characterArray(0)(3)
        strength = characterArray(0)(4)
        defense = characterArray(0)(5)
        range = characterArray(0)(6)
        magic = characterArray(0)(7)
        stamina = characterArray(0)(8)
        coins = characterArray(0)(9)

    }
}
