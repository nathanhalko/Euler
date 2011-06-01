package com.spotinfluence

import nphEuler.primes._

/*
The sequence of triangle numbers is generated by adding the natural numbers. So the 7th triangle number would be 1 + 2 + 3 + 4 + 5 + 6 + 7 = 28. The first ten terms would be:

1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...

Let us list the factors of the first seven triangle numbers:

 1: 1
 3: 1,3
 6: 1,2,3,6
10: 1,2,5,10
15: 1,3,5,15
21: 1,3,7,21
28: 1,2,4,7,14,28
We can see that 28 is the first triangle number to have over five divisors.

What is the value of the first triangle number to have over five hundred divisors?
 */


object Prob012 {
  def main(args: Array[String]) {

  def factors(n:Int) = primeFactors(n).map(f => f._2+1).product
    /*
    To find how many factors n has, let f_j,m_j be the jth prime factor with
     multiplicity m_j, then sum(m_j +1)
     */

  def triNum(n:Int): Int = {
    (n*(n+1))/2
  }

  val t = Iterator.range(1,Int.MaxValue).map(n => triNum(n)).find(factors(_) > 500).get

    print("The first triangle number to have more than 500 factors is %d".format(t))

    //Answer:  76576500       runs in ~ 1 min


  }
}


