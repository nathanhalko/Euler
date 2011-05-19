package com.spotinfluence

object Prob004 {
  def main(args: Array[String]) {

    def isPal(i: Int): Boolean = {
      val s = i.toString
      s == s.reverse
         //return isPalindrome = true
     }


    def findPal(i: Int) = {
      Vector((1 to i).reverse:_*).view map(_*i) find isPal
    }


    val Pals = for {
      n <- (100 until 1000).reverse
    } yield findPal(n)

    println(Pals.max)

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