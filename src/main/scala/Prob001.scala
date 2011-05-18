package com.spotinfluence

object Prob001 {
  def main(av: Array[String]) {
    val nums = for {
      n <- 1 until 1000
      if n % 3 == 0 || n % 5 == 0
    } yield n

    println(nums.sum)
  }
}