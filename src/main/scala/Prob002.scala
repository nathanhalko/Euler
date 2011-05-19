package com.spotinfluence


object Prob002 {
  def main(args: Array[String]) {

  var sum = 2
  var x   = 1
  var y   = 2
  var z   = 3

  while (sum < 4000000) {

    x = y
    y = z
    z = x + y


    if (z % 2 == 0) {
      sum = sum + z
      println("Partial sum: "+sum)
    }
  }

     println("Sum of even Fibonacci numbers less than 4,000,000: "+sum)
  }
}

