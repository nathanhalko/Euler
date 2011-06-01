package com.spotinfluence

/*
2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.

What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */


object Prob005 {
  def main(args: Array[String]) {

    val N = 20

    val easy = Iterator.range(N,Int.MaxValue).find(n => List.range(2,N+1).forall(n%_==0)).get

    print("%d is the smallest number divisible by all numbers up to %d".format(easy,N))

  }
}