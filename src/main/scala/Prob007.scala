package com.spotinfluence

import scala.math._


object Prob007 {
  def main(args: Array[String]) {

     var primes:List[Int] = List(5,3,2)

    def isComposite(n:Int): Boolean = {
      (2 to ceil(sqrt(n)).toInt).exists(n%_==0)
    }

    def findNextPrime(n:Int): Int = {
      if (isComposite(n)) {
        findNextPrime(n+1)
      }
      else n
    }

    while (primes.length < 10001) {
      primes = findNextPrime(primes(0)+1)::primes
    }

         println("The "+primes.length+"th prime is: "+primes(0))
  }
}