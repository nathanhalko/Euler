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

/* -ljr
 *
 * It's probably about the same.  Note that for ALL scala collection classes you can drop the
 * new and just
 *
 *  List(1001) // aka new List[Int](1001)
 *
 * Scala will figure out the type through inference.
 *
 *  List(1, "A") // aka new List[Any](1, "A")
 *
 * I do know that * is faster than sqrt() so given a list of (in order) primes you probably want
 *
 *  def isComposite(n: Int, ps: List[Int]): Boolean = {
 *    if (ps.isEmpty) return true
 *
 *    def step(qs: List[Int]): Boolean = {
 *      val q :: qz = qs
 *      if (q * q > n) false
 *      else if (n % q == 0) true
 *      else step(qz)
 *    }
 *
 *    step(ps)
 *  }
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
