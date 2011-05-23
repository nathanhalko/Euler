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