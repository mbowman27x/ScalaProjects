package example

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.Row
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._

object Enemy {
    
    val spark: SparkSession = SparkSession
        .builder()
        .master("local[1]")
        .appName("Enemy")
        .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    import spark.implicits._

    var enemyData: List[Row] = List()

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

    var RNG = scala.util.Random

    def runEnemyGenerator = {
        
        race = raceSeq(RNG.nextInt(4))

        hp = RNG.nextInt(50)
        strength = RNG.nextInt(5) + 5
        defense = RNG.nextInt(5) + 5
        range = RNG.nextInt(5) + 5
        magic = RNG.nextInt(5) + 5
        stamina = RNG.nextInt(5) + 5
        coins = RNG.nextInt(50)
    }

    def saveEnemy = {

        // Re-assign enemyData as new List with new values
        enemyData = List(Row(id, name, race, hp, strength, defense, range, magic, stamina, coins))

        val rdd = spark.sparkContext.parallelize(enemyData)

        // Create Enemy DataFrame
        println("Saving Enemy " + name)
        val dataFrame = spark.createDataFrame(rdd, schema)

        // Save Dataframe to HDFS
        println("Saving Enemy to HDFS...")
        dataFrame.write
            .mode("overwrite")
            .option("sep", ",")
            .option("header", "true")
            .csv("hdfs://sandbox-hdp.hortonworks.com:8020/user/maria_dev/RNGcharacters/enemy1.csv")

        dataFrame.show()
    }

    def loadEnemy = {

        // Create and Show Dataframe by reading csv from HDFS
        println("Loading Enemy from HDFS")
        val dfFromHDFS = spark.read
            .option("header", "true")
            .csv("hdfs://sandbox-hdp.hortonworks.com:8020/user/maria_dev/RNGcharacters/character1.csv")
        
        println("Reading Enemy CSV File")
        dfFromHDFS.show()

        // Create an Array from Dataframe
        val enemyArray = dfFromHDFS.collect()

        // Re-assign enemy variables to indexes of val enemyArray
        id = enemyArray(0)(0)
        name = enemyArray(0)(1)
        race = enemyArray(0)(2)
        hp = enemyArray(0)(3)
        strength = enemyArray(0)(4)
        defense = enemyArray(0)(5)
        range = enemyArray(0)(6)
        magic = enemyArray(0)(7)
        stamina = enemyArray(0)(8)
        coins = enemyArray(0)(9)

    }
}
