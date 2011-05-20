package com.spotinfluence

object Prob003 {
  def main(args: Array[String]) {

    val n      = 100

    val sum_sq = (n*(n+1)*(2*n+1))/6

    val sq_sum =  (n*n*(n+1)*(n+1))/4

    println("Difference between the sum of squares and the square sum of the first "+n+" natural numbers")
    println("sum_sq = "+sum_sq)
    println("sq_sum = "+sq_sum)
    println(sum_sq - sq_sum)

  }
}

/* -ljr

Nice.

*/
