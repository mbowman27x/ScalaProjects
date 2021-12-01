package example

object HighScore {
    var score = 0
    
    def recordScore = {
        score = User.coins
        
        println("Your Score is: " + User.coins)
    }
}
