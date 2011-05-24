package com.spotinfluence

/* -ljr
 *
 * val answer = BigInt(2).pow(1000).toString.map{Character.toString(_).toInt}.sum
 *
 * This question either determines if you can translate pencil-and-paper multiplication
 * to the computer as you did or if you are intimately familiar with your libraries.  :/
 *
 *  -nph  Skills to pay the bills, I like it
 */

object Prob016 {
  def main(args: Array[String]) {

    val n = 1000

    /*
    var v = new Array[Int](310)
    v(0)  = 2


    def multBy2(v: Array[Int]) = {
      var rmd = 0
      for (i <- 0 until  v.length) {

        v(i) = 2*v(i)+rmd
        if (v(i) >= 10) {
          rmd = 1
          v(i) = v(i)-10
        }
          else
          rmd = 0

      }
    }

    for (i <- 2 to  n) {
      multBy2(v)
    }

    */

    //println("The sum of the digits in 2^"+n+" is: "+v.sum)
    val sum = BigInt(2).pow(n).toString.map{Character.toString(_).toInt}.sum

    println("The sum of the digits in 2^%d is %d".format(n,sum))
  }
}
