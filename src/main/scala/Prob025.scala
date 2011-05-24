package com.spotinfluence


object Prob025 {
  def main(args: Array[String]) {

    /* Recursive definitions not feasible here
    def fib(n:Int): Array[Int] = {
      if (n <= 2) Array(1)
      else add(fib(n-1),fib(n-2))
    }
    */

    /*
    def fib(n:Int): Int = {
      if (n<=2) 1
      else fib(n-1)+fib(n-2)
    }
    */

    /*
  def addBigNums(a:Array[Int], b:Array[Int]):Array[Int] = {
    var tmp = (0,0)
    val c = new Array[Int](a.length)

    for (i <- 0 until  a.length) {
      tmp = add(a(i),b(i),tmp._1)
      c(i) = tmp._2
    }
    c
  }

    def add(a:Int, b:Int, c:Int) : (Int,Int) = {
      val res = a + b +c
      if (res < 10) (0,res)
      else (1,res - 10)
    }

    val digits = 100

    var x   = new Array[Int](digits)
    var y   = new Array[Int](digits)
    var z   = new Array[Int](digits)

    x(0) = 1
    y(0) = 1
    z(0) = 2

    var fn = 3
    while (z.last == 0 && fn < 500) {
      x = y
      y = z
      z = addBigNums(x,y)
      fn += 1
      //println(fn)
      //println(z.reverse.deep.mkString)
    }
    */

     def fib(a:BigInt, b:BigInt, max:Int, z:List[BigInt] = Nil):List[BigInt] = {
      if (a.toString.length >= max) a :: z
      else fib(b, a+b, max, a :: z)
    }

    val max = 100
    val z =  fib(1,1,max)
    println("The %d th Fibonacci number has %d digits".format(z.length,max))
    println(z.head)

    /*
    println("The "+fn+"th Fibonacci number has "+digits+" digits")
    println(z.reverse.deep.mkString)
    */

  }
}