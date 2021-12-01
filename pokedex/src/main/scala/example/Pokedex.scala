package example

import example.Helpers._
import scala.io.Source
import org.mongodb.scala._
import org.mongodb.scala.model._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Sorts._
import org.mongodb.scala.model.Projections._
import org.mongodb.scala.model.Aggregates._
import org.mongodb.scala.model.Updates._

object Pokedex {
    def main(args: Array[String]){
        Space.printSpace
        println("Starting MongoDB - Scala")
        Space.printSpace

        Thread.sleep(3000)
        
        menu

        
    }

    val client: MongoClient = MongoClient()
    val database: MongoDatabase = client.getDatabase("ScalaProjectsDB")
    val collection: MongoCollection[Document] = database.getCollection("pokedexData")

    var selection = 0

    def menu = {
        Space.printSpace
        println("Make a selection")
        println("1: Query the Pokedex")

        selection = scala.io.StdIn.readInt()

        selection match{
            case 1 => queryPokedex  
            case 2 =>    
            case _ => 
        }
    }

    def queryPokedex = {
        println("Select a Query")
        println("Show First 10 Pokemon")
        println("Show Last 10 Pokemon")

        selection = scala.io.StdIn.readInt()

        selection match{
            case 1 => collection.find().sort(ascending("id")).limit(10).printResults()
            case 2 => collection.find().sort(descending("id")).limit(10).printResults()  
            case _ => 
        }
    }
}
