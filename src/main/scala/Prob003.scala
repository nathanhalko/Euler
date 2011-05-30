package com.spotinfluence

object Prob003 {
  def main(args: Array[String]) {

    val n      = 100

    val sum_sq = (n*(n+1)*(2*n+1))/6

    val sq_sum =  (n*n*(n+1)*(n+1))/4

    println("Difference between the sum of squares and the square sum of the first "+n+" natural numbers")
    println("sum_sq = "+sum_sq)
    println("sq_sum = "+sq_sum)
    println(sum_sq - sq_sum)

  }
}

/* -ljr

Nice.

-nph  Do you know any slick way to raise a number to a power without doing

import scala.math._
pow(2,10)                ??

The '**' or '^' doesn't seem to work.

*/

/* -ljr
 *
 * The scala namespace is auto imported so you can just
 *
 *  math.pow(2,10).toInt
 *
 * However I've never liked having to cast from a Double there.  The quick and dirty method
 * is probably
 *
 *   List.fill(2)(10).product
 *
 * The normal functional method is
 *
 *   def fastPow[@specialized(Int, Long, Float, Double) A](n: A, p: Int): A = {
 *     require(n > 0)
 *
 *     def step(x: A, q: Int): A = q match {
 *       case 1 => x
 *       case q if q%2 == 0 => step(x*x, q/2)
 *       case _ => step(n*x, q-1)
 *     }
 *
 *     step(n, p)
 *   }
 *
 * I would like to have extended to the p=0 case but Scala wants to use a java.lang.Integer for
 * 1 which can't get cast based on the type constraint A.
 */
