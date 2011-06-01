package com.spotinfluence

import nphEuler.primes._

/*
The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.

Find the sum of all the primes below two million.
 */

object Prob010 {
  def main(args: Array[String]) {

    /*
    val primes = genPrimes(2000000)
    print(primes.foldLeft(0L)(_+_))

    //!Yikes! takes ~10 mins to run
    */

    lazy val ps: Stream[Int] = 2 #:: ps.map(i => Stream.from(i + 1).find(j => ps.takeWhile(k => k * k <= j).forall(j % _ > 0)).get)
    val r = ps.view.takeWhile(_ < 2000000).foldLeft(0L)(_ + _)
    print(r)

    /* I totally cheated and Googled this code, runs in ~1 sec.
    Answer:  142,913,828,922
    */

    /* -ljr
     *
     * You have a poor primes generator then.  The one I have came back immediately.
     * Ok. Use of lists with a final .reverse is killing you.  I'll comment more in
     * your primes library.
     */

  }
}
