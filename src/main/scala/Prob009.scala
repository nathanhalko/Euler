package com.spotinfluence
import scala.math._

/*
A Pythagorean triplet is a set of three natural numbers, a  b  c, for which,

a2 + b2 = c2
For example, 32 + 42 = 9 + 16 = 25 = 52.

There exists exactly one Pythagorean triplet for which a + b + c = 1000.
Find the product abc.
 */

object Prob009 {
  def main(args: Array[String]) {

  /*

  def pTrip(): List[List[Int]] = {
    for {m <- List(3 to 23:_*); n <- List(1 until m:_*)}
     yield List(pow(m,2).toInt - pow(n,2).toInt, 2*m*n, pow(m,2).toInt + pow(n,2).toInt)
  }
  */


  val x = Iterable.range(3,23).map(m => Iterable.range(1,m).map(n => List(m*m-n*n,2*m*n,m*m+n*n)).find(_.sum == 1000)).flatten.flatten.toList
    /* -nph
    Seems like this should work but I'm getting an error

<console>:7: error: could not find implicit value for parameter num: Numeric[List[Int]]
       val x = Stream.range(3,23).map(m => Stream.range(1,m).map(n => List(m*m-n*n,2*m*n,m*m+n*n))).find(_.sum == 1000).flatten

       *****corrected. The find operation needed to be nested for access the List.  x's type is
       * Iterable[Iterable[List[Int]]]

    Using 'find' and the Iterable should simulate the break since it will only compute until the first triplet is found.

  Also annoying: I can't run say Prob003 while there is a bug here in Prob009????
     */

  //val x = pTrip().filter(_.sum == 1000)(0)
  println("%d + %d + %d = 1000".format(x(0),x(1),x(2)))
  println("and %d + %d = %d".format(pow(x(0),2).toInt,pow(x(1),2).toInt,pow(x(2),2).toInt))
  println("whose product is %d".format(x(0)*x(1)*x(2)))



  }
}

/* -ljr
 *
 * Scala in 2.8 got a Break abstraction.  It only works with the foreach() form of for
 * but can be handy when you want to break early.  Read the section on for{} in
 * Programming in Scala.  They are a lot more powerful for loops than you are taking
 * advantage of.
 *
 *   val mybreaks = new scala.util.control.Breaks
 *   import mybreaks.{break, breakable}
 *
 *   var answer = (0,0,0)
 *
 *   for {
 *     m <- 2 to 23
 *     n <- 1 until m
 *     m2 = m*m
 *     n2 = n*n
 *     a = m2 - n2
 *     b = 2*m*n
 *     c = m2 + n2
 *     if a + b + c == 1000
 *  } {
 *     answer = if (a < b) (a,b,c) else (b,a,c)
 *     break    // Can be placed in other control structures.  E.g., if (...) break
 *  }
 */
