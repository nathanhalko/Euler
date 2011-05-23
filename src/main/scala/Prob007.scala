package com.spotinfluence

import scala.math._


object Prob007 {
  def main(args: Array[String]) {

    var primes = List(5,3,2)



    def isComposite(n:Int): Boolean = {
      (2 to ceil(sqrt(n)).toInt).exists(n%_==0)
    }

    def findNextPrime(n:Int): Int = {
      if (isComposite(n)) {
        findNextPrime(n+1)
      }
      else n
    }

    var i = 3
    while (i < 10001) {
/* -ljr
 *
 * List.length() (and List.size()) have to count every element in the list so are O(n).
 * Because you are doing so many tests here you are really slowing down your run.
 * Better to use a Vector or Array which have O(1) length().
 *
 *  -nph  Interesting, I like to pay attention to these things
 *  Is it faster to initialize  new List[Int](1001) or append?  -I'll get read up on this stuff
 */
      primes = findNextPrime(primes(0)+1)::primes
      i += 1
    }

         println("The "+primes.length+"th prime is: "+primes(0))
  }
}

/* -ljr
 *
 * If you keep playing with Euler it's worth your while to develop a library
 * for generating and working with primes.
 *
 * -nph  yes! this is what I'd like to do, make a library with more general
 * codes and error checking
 */
