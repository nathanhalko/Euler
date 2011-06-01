package com.spotinfluence

/*
2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.

What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */

/* -ljr
 *
 * Don't check your brain at the door.  It's easy to brute-force these but it's a good habit to get into
 * to think through problems.  In the real world it will often save time or space.  This is the LCM
 * problem.  Check MathWorld for the formula but basically to find lcm of set S you take the prime
 * factors and for each prime find the max exponent.  Then multiply those together.
 *
 * 1-10 broken down by prime factors
 * [1] [2] [3] [2^2] [5] [2,3] [7] [2^3] [3^2] [2,5]
 * answer = 2^3 * 3^2 * 5 * 7 = 8 * 9 * 5 * 7 = 2520
 *
 * [11] [2^2,3] [13] [2,7] [3,5] [2^4] [17] [2,3^2] [2^2,5]
 * answer = 2^4 * 3^2 * 5 * 7 * 11 * 13 * 17 * 19
 */


object Prob005 {
  def main(args: Array[String]) {

    val N = 20

    val easy = Iterator.range(N,Int.MaxValue).find(n => List.range(2,N+1).forall(n%_==0)).get

    print("%d is the smallest number divisible by all numbers up to %d".format(easy,N))

  }
}
