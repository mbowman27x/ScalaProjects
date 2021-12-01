package example

import User._
import Roulette._
import HighScore._

import example.Helpers._
import org.mongodb.scala._
import org.mongodb.scala.model._
import scala.io.Source
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Sorts._
import org.mongodb.scala.model.Projections._
import org.mongodb.scala.model.Aggregates._
import org.mongodb.scala.model.Updates._

object StartGame {
    def main(args: Array[String]) = {

        Thread.sleep(1000)

        User.namingUser
        Roulette.chooseTheme

        while(sitting == true){
            if(User.coins > 0){
                Roulette.showTable
                Roulette.bet
            }else{
                println("Better luck next time")
                println("GAME OVER")
                sitting = false
            }
        }

        println("")
        println("You walk from the table")
        println("")
        HighScore.recordScore
        archiveScore
    }

    var sitting = true

    val client: MongoClient = MongoClient()
    val database: MongoDatabase = client.getDatabase("ScalaProjectsDB")
    val collection: MongoCollection[Document] = database.getCollection("rouletteHighScores")

    def archiveScore = {
        val document: Document = Document(
            "Score" -> HighScore.score,
            "Name" -> User.userName
        )

        println("")
        println("Recording Score...")
        collection.insertOne(document).results()

        println("")
        println(Console.GREEN + "HIGHSCORES (TOP 10):" + Console.WHITE)
        println("")

        collection.find().sort(descending("Score")).limit(10).printResults()
        println("")
    }
}