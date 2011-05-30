package com.spotinfluence
import scala.math._


object Prob009 {
  def main(args: Array[String]) {



  def pTrip(): List[List[Int]] = {
    for {m <- List(3 to 23:_*); n <- List(1 until m:_*)}
     yield List(pow(m,2).toInt - pow(n,2).toInt, 2*m*n, pow(m,2).toInt + pow(n,2).toInt)
  }

  val x = pTrip().filter(_.sum == 1000)(0)
  println("%d + %d + %d = 1000".format(x(0),x(1),x(2)))
  println("and %d + %d= %d".format(pow(x(0),2).toInt,pow(x(1),2).toInt,pow(x(2),2).toInt))
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
