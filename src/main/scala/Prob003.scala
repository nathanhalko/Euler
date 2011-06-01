package com.spotinfluence

import nphEuler.primes._

/*  Problem 3
The prime factors of 13195 are 5, 7, 13 and 29.

What is the largest prime factor of the number 600851475143 ?
 */

object Prob003 {
  def main(args: Array[String]) {

    val stupidLargeNumber = 600851475143d

    val lpf = primeFactors(stupidLargeNumber).map(a => a._1).max

    println("The largest prime factor of %f is %d".format(stupidLargeNumber,lpf))

  }
}