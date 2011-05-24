package com.spotinfluence

object Prob004 {
  def main(args: Array[String]) {

    def isPal(i: Int): Boolean = {
      val s = i.toString
      s == s.reverse
         //return isPalindrome = true
     }


    /*
    def findPal(i: Int) = {
      List(100 to i:_*).map(_*i).filter{isPal}
    }
    */


/* -ljr
 *
 * Vector is the default for Seq but you'll find List() much more common
 * in code.  Unless you suspect you'll need quick indexing or size() of the
 * Seq you are probably better off with a List.  (Smaller memory footprint
 * mainly.)
 *
 * If you find you want to build a list without having to reverse the
 * elements so they end up in the correct order you can use ListBuffer.
 *
 *    val b = List.newBuilder[Int]
 *    for (i <- to 10) { b += i }
 *    val myList = b.result
 */

    val xs = for {
      i <- 100 until 1000
      j <- 100 until 1000
    }  yield i * j

    val Pals = xs.filter{isPal}
    println(Pals.max)

/* -ljr
 *
 * Consider
 *
 *    val xs = for {
 *      i <- 100 until 1000
 *      j <- 100 until 1000
 *    } yield i * j
 *
 *    xs.filter{isPal(_)}.max
 *
 * It's very difficult to multiply the 900x900 values in such a way that
 * you get the largest values in order.  As such your findPal() is just
 * doing a bit of extra work in recreating a Vector(1 to i: _*).  You are
 * also technically not multiplying 3-digit by 3-digit numbers so if it
 * happened that no 3-digit x 3-digit number was a palindrome you would
 * get an incorrect answer.
<<<<<<< HEAD
 */


/*
val n = 999
var f1= n
var f2= n
var i = 1
var j = 1
var x = n*n
var p = 0
var tmp = 0

while (x > p) {
  x = (n-i+1)*(n-j+1)
  println(x)
  j += 1

  if (isPal(x)) {
    tmp = x
    x = n*n
    p = tmp
    println(x)
    f1 = n-i+1
    f2 = n-j+1
    i += 1
    j = i
  }
}

println("The largest palindrome as the product of two 3-digit numbers is: "+p)
//println("whose factors are: "+(n-i+1)+" x "+(n-j+1))
println("whose factors are "+f1+" x "+f2)

*/



  }
}
