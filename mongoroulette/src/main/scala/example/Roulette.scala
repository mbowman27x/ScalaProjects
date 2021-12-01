package example

object Roulette {

    var RNG = scala.util.Random

    var betType = 0
    var betAmount = 0

    var spinNumber: Int = 0
    var singleNumber: Int = 0

    var b = Console.BLACK
    var r = Console.RED
    var w = Console.WHITE
    var g = Console.GREEN

    def showTable = {
        
        var row1 = "[ ] "+r+"[3] "+b+"[6] "+r+"[9] [12] "+b+"[15] "+r+"[18] [21] "+b+"[24] "+r+"[27] [30] "+b+"[33] "+r+"[36] "+w+"[2 to 1 Odds]"
        var row2 = g+"[0] "+b+"[2] "+r+"[5] "+b+"[8] [11] "+r+"[14] "+b+"[17] [20] "+r+"[23] "+b+"[26] [29] "+r+"[32] "+b+"[35] "+w+"[2 to 1 Odds]"
        var row3 = "[ ] "+r+"[1] "+b+"[4] "+r+"[7] "+b+"[10] [13] "+r+"[16] [19] "+b+"[22] "+r+"[25] "+b+"[28] [31] "+r+"[34] "+w+"[2 to 1 Odds]"
        var row4 = "    [   1st 12     ] [    2nd  12      ] [    3rd  12      ]"
        var row5 = "    [ 1-18 ][ EVEN ] "+b+"[ BLACK ] "+r+"[  RED  ] "+w+"[  ODD  ][  19-36 ]"

        println(" ")
        println(" _____________________________ Roulette Table ______________________________")
        println("| " + row1 + " |")
        println("| " + row2 + " |")
        println("| " + row3 + " |")
        println("| " + row4 + "               |")
        println("| " + row5 + "               |")
        println(" ___________________________________________________________________________")
        println(" ")
    }

    def bet: Unit = {

        println(" ")
        User.displayStats

        println("Choose your Bet Type")
        println(" 1: Row One      4: 1st 12      7: (1-18)      10: ODD      13: Single Number ")
        println(" 2: Row Two      5: 2nd 12      8: (19-36)     11: Black ")
        println(" 3: Row Three    6: 3rd 12      9: EVEN        12: Red ")

        println(" ")

        betTypeMatch

        println("Choose your Bet Amount")
        println(" ")
        println("BetAmount:")

        betAmount = scala.io.StdIn.readInt

        if(betAmount > 0 & betAmount <= User.coins){
            println(" ")
        }else if(betAmount > User.coins){
            println(" ")
            println("- Not enough coins")
            println("- Bet = 0")
            betAmount = 0
            println("")
        }else{
            println(" ")
            println("- Not a valid number")
            println("- Bet = 0")
            betAmount = 0
            println("")
        }

        User.coins -= betAmount

        spin

        betType match{
            case 1 =>   spinNumber match{
                            case 3 | 6 | 9 | 12 | 15 | 18 | 21 | 24 | 27 | 30 | 33 | 36 =>  User.coins += betAmount * 3
                                                                                            println(Console.GREEN + "Prize: " + Console.WHITE + (betAmount * 3) + " coin(s)")
                            case _ =>   println(Console.RED + "Lost: " + Console.WHITE + betAmount + " coin(s)")
                        }
                        Thread.sleep(1000)

            case 2 =>   spinNumber match{
                            case 2 | 5 | 8 | 11 | 14 | 17 | 20 | 23 | 26 | 29 | 32 | 35 =>  User.coins += betAmount * 3
                                                                                            println(Console.GREEN + "Prize: " + Console.WHITE + (betAmount * 3) + " coin(s)")
                            case _ =>   println(Console.RED + "Lost: " + Console.WHITE + betAmount + " coin(s)")
                        }
                        Thread.sleep(1000)

            case 3 =>   spinNumber match{
                            case 1 | 4 | 7 | 10 | 13 | 16 | 19 | 22 | 25 | 28 | 31 | 34 =>  User.coins += betAmount * 3
                                                                                            println(Console.GREEN + "Prize: " + Console.WHITE + (betAmount * 3) + " coin(s)")
                            case _ =>   println(Console.RED + "Lost: " + Console.WHITE + betAmount + " coin(s)")
                        }
                        Thread.sleep(1000)

            case 4 =>   spinNumber match{
                            case 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 =>    User.coins += betAmount * 3
                                                                                        println(Console.GREEN + "Prize: " + Console.WHITE + (betAmount * 3) + " coin(s)")
                            case _ =>   println(Console.RED + "Lost: " + Console.WHITE + betAmount + " coin(s)")
                        }
                        Thread.sleep(1000)   

            case 5 =>   spinNumber match{
                            case 13 | 14 | 15 | 16 | 17 | 18 | 19 | 20 | 21 | 22 | 23 | 24 =>   User.coins += betAmount * 3
                                                                                                println(Console.GREEN + "Prize: " + Console.WHITE + (betAmount * 3) + " coin(s)")
                            case _ =>   println(Console.RED + "Lost: " + Console.WHITE + betAmount + " coin(s)")
                        }
                        Thread.sleep(1000)  

            case 6 =>   spinNumber match{
                            case 25 | 26 | 27 | 28 | 29 | 30 | 31 | 32 | 33 | 34 | 35 | 36 =>   User.coins += betAmount * 3
                                                                                                println(Console.GREEN + "Prize: " + Console.WHITE + (betAmount * 3) + " coin(s)")
                            case _ =>   println(Console.RED + "Lost: " + Console.WHITE + betAmount + " coin(s)")
                        }
                        Thread.sleep(1000)

            case 7 =>   spinNumber match{
                            case 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 | 13 | 14 | 15 | 16 | 17 | 18 =>  User.coins += betAmount * 2
                                                                                                                    println(Console.GREEN + "Prize: " + Console.WHITE + (betAmount * 2) + " coin(s)")
                            case _ =>   println(Console.RED + "Lost: " + Console.WHITE + betAmount + " coin(s)")
                        }
                        Thread.sleep(1000)

            case 8 =>   spinNumber match{
                            case 19 | 20 | 21 | 22 | 23 | 24 | 25 | 26 | 27 | 28 | 29 | 30 | 31 | 32 | 33 | 34 | 35 | 36 => User.coins += betAmount * 2
                                                                                                                            println(Console.GREEN + "Prize: " + Console.WHITE + (betAmount * 2) + " coin(s)")
                            case _ =>   println(Console.RED + "Lost: " + Console.WHITE + betAmount + " coin(s)")
                        }
                        Thread.sleep(1000)   

            case 9 =>   if((spinNumber + 2) % 2 == 0){
                            User.coins += betAmount * 2
                            println(Console.GREEN + "Prize: " + Console.WHITE + (betAmount * 2) + " coin(s)")
                        }else{
                            println(Console.RED + "Lost: " + Console.WHITE + betAmount + " coin(s)")
                        }
                        Thread.sleep(1000)

            case 10 =>  if((spinNumber + 2) % 2 != 0){
                            User.coins += betAmount * 2
                            println(Console.GREEN + "Prize: " + Console.WHITE + (betAmount * 2) + " coin(s)")
                        }else{
                            println(Console.RED + "Lost: " + Console.WHITE + betAmount + " coin(s)")
                        }
                        Thread.sleep(1000)

            case 11 =>  spinNumber match{
                            case 2 | 4 | 6 | 8 | 10 | 11 | 13 | 15 | 17 | 20 | 22 | 24 | 26 | 28 | 29 | 31 | 33 | 35 => User.coins += betAmount * 2
                                                                                                                        println(Console.GREEN + "Prize: " + Console.WHITE + (betAmount * 2) + " coin(s)")
                            case _ =>   println(Console.RED + "Lost: " + Console.WHITE + betAmount + " coin(s)")
                        }
                        Thread.sleep(1000)

            case 12 =>  spinNumber match{
                            case 1 | 3 | 5 | 7 | 9 | 12 | 14 | 16 | 18 | 19 | 21 | 23 | 25 | 27 | 30 | 32 | 34 | 36 =>  User.coins += betAmount * 2
                                                                                                                        println(Console.GREEN + "Prize: " + Console.WHITE + (betAmount * 2) + " coin(s)")
                            case _ =>   println(Console.RED + "Lost: " + Console.WHITE + betAmount + " coin(s)")
                        }
                        Thread.sleep(1000)

            case 13 =>  if(spinNumber == singleNumber){
                            User.coins += betAmount * 37
                            println(Console.GREEN + "Prize: " + Console.WHITE + (betAmount * 37) + " coin(s)")
                        }else{
                            println(Console.RED + "Lost: " + Console.WHITE + betAmount + " coin(s)")
                        }
                        Thread.sleep(1000)
        }

        playOrCollect
    }

    def spin = {
        println(Console.GREEN + "Spinning...")
        Thread.sleep(1000)
        spinNumber = RNG.nextInt(36)

        println(Console.WHITE + "")
        println("Number is  - " + Console.CYAN + "[" + spinNumber + "]" + Console.WHITE + " - ")
        println("")
    }

    def betTypeMatch: Unit = {
        println("Choose Bet:")

        betType = scala.io.StdIn.readInt
        println("")

        betType match{
            case 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 => println(" ")

            case 13 =>  println("Choose your number")
                        singleNumber = scala.io.StdIn.readInt

            case _ =>   println("Not a valid number")
                        println("- Bet = 0")
                        println("")
                        betTypeMatch
        }
    }

    def playOrCollect: Unit = {
        println("")
        User.displayStats
        
        println("1: Play Again")
        println("2: Collect Winnings")

        singleNumber = scala.io.StdIn.readInt

        singleNumber match{
            case 1 =>   println("Good Luck!")

            case 2 =>   println("")
                        println("Are you sure?")
                        println("1: Yes")
                        println("2: No")

                        singleNumber = scala.io.StdIn.readInt

                        singleNumber match{
                            case 1 =>   StartGame.sitting = false
                            case 2 =>   println("")
                                        playOrCollect
                            case _ =>   println("Enter a valid number")
                                        playOrCollect
                        }

            case _ =>   println("Enter a valid number")   
                        playOrCollect
        }
    }

    def chooseTheme: Unit = {
        println("")
        println("Choose Color Theme:")
        println("1: Black + Red")
        println("2: Blue + Green")

        singleNumber = scala.io.StdIn.readInt()

        singleNumber match{
            case 1 => println("")

            case 2 => b = Console.CYAN
                      r = Console.GREEN
                      println("")

            case _ => println("Enter Valid Option")
                      chooseTheme
        }
    }
}

