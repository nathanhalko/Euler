package com.spotinfluence

object Prob016 {
  def main(args: Array[String]) {

    val n = 1000
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

    println("The sum of the digits in 2^"+n+" is: "+v.sum)

  }
}