package example

object SparkMain {
    def main(args: Array[String]) = {
        println("Welcome to Spark Battle!")

        while(status == true){
            menuSelection
        }

        println("Goodbye")
    }

    var status = true

    var selection = 0

    def menuSelection: Unit = {
        println("Make a Selection")
        println("1: Start New Game")
        println("2: Load Game")
        println("3: Exit Program")

        selection = scala.io.StdIn.readInt()

        selection match{
            case 1 =>   Character.runCharacterGenerator
            case 2 =>   Character.loadCharacter
            case 3 =>   status = false
            case _ =>   println("Invalid Selection")
                        menuSelection
        }
    }
}
