package example

object User {
    var coins = 100
    var userName = ""

    def namingUser = {
        println("")
        println("Enter your name")
        userName = scala.io.StdIn.readLine()
        println("")
        println("Welcome " + userName)
    }
    
    def displayStats = {
        println("Casino Purse")
        println("Coins: " + coins)
        println(" ")
    }
}
