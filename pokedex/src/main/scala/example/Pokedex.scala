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
        
        while(runStatus == true){
            menu
        }

        println("Exiting Program...")
    }

    var runStatus = true

    val client: MongoClient = MongoClient()
    val database: MongoDatabase = client.getDatabase("ScalaProjectsDB")
    val collection: MongoCollection[Document] = database.getCollection("pokedexData")

    var selection = 0
    var stringSelection = ""
    var idSelect = 0
    var nameSelect = ""

    def menu = {
        Space.printSpace
        println("Make a selection")
        println("1: Query the Pokedex")
        println("2: Create Pokemon")
        println("3: Update Pokemon")
        println("4: Delete Pokemon")
        println("5: Exit Program")

        selection = scala.io.StdIn.readInt()

        selection match{
            case 1 => queryPokedex  
            case 2 => createPokemon 
            case 3 => updatePokemon
            case 4 => deletePokemon
            case 5 => runStatus = false  
            case _ => 
        }
    }

    def queryPokedex: Unit = {

        Space.printSpace
        println("Select a Query")
        println("1: Show First 10 Pokemon")
        println("2: Show Last 10 Pokemon")
        println("3: Search by ID")
        println("4: Search Range of IDs")
        println("5: Search by Name")

        selection = scala.io.StdIn.readInt()

        selection match{
            case 1 =>   collection.find().sort(ascending("id")).limit(10).printResults()
            case 2 =>   collection.find().sort(descending("id")).limit(10).printResults()  

            case 3 =>   println("Enter an ID number")
                        selection = scala.io.StdIn.readInt()
                        collection.find(equal("id", selection)).printResults()
            
            case 4 =>   println("Enter the first number")
                        var x = scala.io.StdIn.readInt()
                        println("Enter the second number")
                        var y = scala.io.StdIn.readInt()
                                
                            for(z <- x to y){
                                collection.find(equal("id", z)).printResults()
                            }

            case 5 =>   println("Enter a name (1st letter capitalized)")
                        stringSelection = scala.io.StdIn.readLine()
                        collection.find(equal("name.english", stringSelection)).printResults()

            case _ =>   println("Invalid Selection")
                        queryPokedex
        }
    }

    def createPokemon = {

        println("Enter a number for ID")
        var id = scala.io.StdIn.readInt()

        println("Type in a name for your Pokemon")
        var pokemonName = scala.io.StdIn.readLine()

        println("Type in a type for your Pokemon")
        var type1 = scala.io.StdIn.readLine()

        println("Type in a second type for your Pokemon")
        var type2 = scala.io.StdIn.readLine()

        println("Enter a number for HP: ")
        var HP = scala.io.StdIn.readInt()

        println("Enter a number for Attack Stat: ")
        var Attack = scala.io.StdIn.readInt()
                        
        println("Enter a number for Defense Stat: ")
        var Defense = scala.io.StdIn.readInt()

        println("Enter a number for Special Attack Stat: ")
        var SpAttack = scala.io.StdIn.readInt()

        println("Enter a number for Special Defense Stat: ")
        var SpDefense = scala.io.StdIn.readInt()    

        println("Enter a number for Speed Stat: ")
        var Speed = scala.io.StdIn.readInt()

        val document: Document = Document(
            "id" -> id,
            "name" -> Document("english" -> pokemonName),
            "type" -> Seq(type1, type2),
            "base" -> Document("HP" -> HP,
                               "Attack" -> Attack,
                               "Defense" -> Defense,
                               "SpecialAttack" -> SpAttack,
                               "SpecialDefense" -> SpDefense,
                               "Speed" -> Speed )
        )

        collection.insertOne(document).results()
        println("Creating Pokemon: " + pokemonName)
        collection.find(equal("name.english", pokemonName)).printResults()
    }

    def updatePokemon: Unit = {
        Space.printSpace
        println("Select Pokemon by ID or Name")
        println("1: ID")
        println("2: Name")
        println("3: Exit")

        selection = scala.io.StdIn.readInt()

        selection match{
            case 1 =>   Space.printSpace
                        println("Enter an ID")
                        idSelect = scala.io.StdIn.readInt()
                        collection.find(equal("id", idSelect)).printResults()

                        Space.printSpace
                        println("Select a field to UPDATE")
                        println("1: ID")
                        println("2: Name")
                        println("3: HP")
                        println("4: Attack")
                        println("5: Defense")
                        println("6: Special Attack")
                        println("7: Special Defense")
                        println("8: Speed")
                        
                        selection = scala.io.StdIn.readInt()

                        Space.printSpace

                        selection match{
                            case 1 =>   println("Enter a value to replace the current value of ID")
                                        selection = scala.io.StdIn.readInt()
                                        collection.updateOne(equal("id", idSelect), set("id", selection)).printResults()
                                        Space.printSpace
                                        println("New Value: ")
                                        Space.printSpace
                                        collection.find(equal("id", selection)).printResults()
                                        updatePokemon

                            case 2 =>   println("Enter a value to replace the current value of Name")
                                        stringSelection = scala.io.StdIn.readLine()
                                        collection.updateOne(equal("id", idSelect), set("name.english", stringSelection)).printResults()
                                        Space.printSpace
                                        println("New Value: ")
                                        Space.printSpace
                                        collection.find(equal("id", idSelect)).printResults()
                                        updatePokemon

                            case 3 =>   println("Enter a value to replace the current value of HP")
                                        selection = scala.io.StdIn.readInt()
                                        collection.updateOne(equal("id", idSelect), set("base.HP", selection)).printResults()
                                        Space.printSpace
                                        println("New Value: ")
                                        Space.printSpace
                                        collection.find(equal("id", idSelect)).printResults()
                                        updatePokemon

                            case 4 =>   println("Enter a value to replace the current value of Attack")
                                        selection = scala.io.StdIn.readInt()
                                        collection.updateOne(equal("id", idSelect), set("base.Attack", selection)).printResults()
                                        Space.printSpace
                                        println("New Value: ")
                                        Space.printSpace
                                        collection.find(equal("id", idSelect)).printResults()
                                        updatePokemon

                            case 5 =>   println("Enter a value to replace the current value of Defense")
                                        selection = scala.io.StdIn.readInt()
                                        collection.updateOne(equal("id", idSelect), set("base.Defense", selection)).printResults()
                                        Space.printSpace
                                        println("New Value: ")
                                        Space.printSpace
                                        collection.find(equal("id", idSelect)).printResults()
                                        updatePokemon

                            case 6 =>   println("Enter a value to replace the current value of Special Attack")
                                        selection = scala.io.StdIn.readInt()
                                        collection.updateOne(equal("id", idSelect), set("base.SpecialAttack", selection)).printResults()
                                        Space.printSpace
                                        println("New Value: ")
                                        Space.printSpace
                                        collection.find(equal("id", idSelect)).printResults()
                                        updatePokemon

                            case 7 =>   println("Enter a value to replace the current value of Special Defense")
                                        selection = scala.io.StdIn.readInt()
                                        collection.updateOne(equal("id", idSelect), set("base.SpecialDefense", selection)).printResults()
                                        Space.printSpace
                                        println("New Value: ")
                                        Space.printSpace
                                        collection.find(equal("id", idSelect)).printResults()
                                        updatePokemon

                            case 8 =>   println("Enter a value to replace the current value of Speed")
                                        selection = scala.io.StdIn.readInt()
                                        collection.updateOne(equal("id", idSelect), set("base.Speed", selection)).printResults()
                                        Space.printSpace
                                        println("New Value: ")
                                        Space.printSpace
                                        collection.find(equal("id", idSelect)).printResults()
                                        updatePokemon

                            case _ =>   println("Invalid Selection")
                                        updatePokemon   
                        }

            case 2 =>   Space.printSpace
                        println("Enter a Name")
                        nameSelect = scala.io.StdIn.readLine()
                        collection.find(equal("name.english", nameSelect)).printResults()

                        Space.printSpace
                        println("Select a field to UPDATE")
                        println("1: ID")
                        println("2: Name")
                        println("3: HP")
                        println("4: Attack")
                        println("5: Defense")
                        println("6: Special Attack")
                        println("7: Special Defense")
                        println("8: Speed")
                        
                        selection = scala.io.StdIn.readInt()

                        Space.printSpace

                        selection match{
                            case 1 =>   println("Enter a value to replace the current value of ID")
                                        selection = scala.io.StdIn.readInt()
                                        collection.updateOne(equal("name.english", nameSelect), set("id", selection)).printResults()
                                        Space.printSpace
                                        println("New Value: ")
                                        Space.printSpace
                                        collection.find(equal("name.english", nameSelect)).printResults()
                                        updatePokemon

                            case 2 =>   println("Enter a value to replace the current value of Name")
                                        stringSelection = scala.io.StdIn.readLine()
                                        collection.updateOne(equal("name.english", nameSelect), set("name.english", stringSelection)).printResults()
                                        Space.printSpace
                                        println("New Value: ")
                                        Space.printSpace
                                        collection.find(equal("name.english", stringSelection)).printResults()
                                        updatePokemon

                            case 3 =>   println("Enter a value to replace the current value of HP")
                                        selection = scala.io.StdIn.readInt()
                                        collection.updateOne(equal("name.english", nameSelect), set("base.HP", selection)).printResults()
                                        Space.printSpace
                                        println("New Value: ")
                                        Space.printSpace
                                        collection.find(equal("name.english", nameSelect)).printResults()
                                        updatePokemon

                            case 4 =>   println("Enter a value to replace the current value of Attack")
                                        selection = scala.io.StdIn.readInt()
                                        collection.updateOne(equal("name.english", nameSelect), set("base.Attack", selection)).printResults()
                                        Space.printSpace
                                        println("New Value: ")
                                        Space.printSpace
                                        collection.find(equal("name.english", nameSelect)).printResults()
                                        updatePokemon

                            case 5 =>   println("Enter a value to replace the current value of Defense")
                                        selection = scala.io.StdIn.readInt()
                                        collection.updateOne(equal("name.english", nameSelect), set("base.Defense", selection)).printResults()
                                        Space.printSpace
                                        println("New Value: ")
                                        Space.printSpace
                                        collection.find(equal("name.english", nameSelect)).printResults()
                                        updatePokemon

                            case 6 =>   println("Enter a value to replace the current value of Special Attack")
                                        selection = scala.io.StdIn.readInt()
                                        collection.updateOne(equal("name.english", nameSelect), set("base.SpecialAttack", selection)).printResults()
                                        Space.printSpace
                                        println("New Value: ")
                                        Space.printSpace
                                        collection.find(equal("name.english", nameSelect)).printResults()
                                        updatePokemon

                            case 7 =>   println("Enter a value to replace the current value of Special Defense")
                                        selection = scala.io.StdIn.readInt()
                                        collection.updateOne(equal("name.english", nameSelect), set("base.SpecialDefense", selection)).printResults()
                                        Space.printSpace
                                        println("New Value: ")
                                        Space.printSpace
                                        collection.find(equal("name.english", nameSelect)).printResults()
                                        updatePokemon

                            case 8 =>   println("Enter a value to replace the current value of Speed")
                                        selection = scala.io.StdIn.readInt()
                                        collection.updateOne(equal("name.english", nameSelect), set("base.Speed", selection)).printResults()
                                        Space.printSpace
                                        println("New Value: ")
                                        Space.printSpace
                                        collection.find(equal("name.english", nameSelect)).printResults()
                                        updatePokemon

                            case _ =>   println("Invalid Selection")
                                        updatePokemon   
                        }
                        
            case 3 =>   

            case _ =>   println("Invalid Selection")
                        updatePokemon
        }
    }

    def deletePokemon: Unit = {
        Space.printSpace
        println("Select Pokemon by ID or Name")
        println("1: ID")
        println("2: Name")
        println("3: Exit")

        selection = scala.io.StdIn.readInt()

        selection match{
            case 1 =>   Space.printSpace
                        println("Enter an ID")
                        idSelect = scala.io.StdIn.readInt()
                        Space.printSpace
                        collection.find(equal("id", idSelect)).printResults()
                        Space.printSpace
                        collection.deleteOne(equal("id", idSelect)).printResults()

            case 2 =>   Space.printSpace
                        println("Enter a Name")
                        nameSelect = scala.io.StdIn.readLine()
                        Space.printSpace
                        collection.find(equal("name.english", nameSelect)).printResults()
                        Space.printSpace
                        collection.deleteOne(equal("name.english", nameSelect)).printResults()

            case 3 =>

            case _ =>   println("Invalid Selection")
                        deletePokemon
        }
    }
}
