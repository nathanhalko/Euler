package com.spotinfluence

object Prob001 {
  def main(av: Array[String]) {

      val N : Int = 1000
      val f1: Int = 5
      val f2: Int = 3

     /* // val range = 1.until(N)
      var sum = 0

      for (i <- 1 to (N-1)) {
        if (i % f1 == 0 || i % f2 == 0)
          sum = sum + i
      }
      */

    val nums = for {
      n <- 1 until N
      if (n % f1 == 0 || n % f2 == 0)
    } yield n

      println("Sum of multiples of "+f1+" and "+f2+" less than "+N+" is:")
      println(nums.sum)

   /*
    val nums = for {
      n <- 1 until 1000
      if n % 3 == 0 || n % 5 == 0
    } yield n

    println(nums.sum)
   */

  }
}