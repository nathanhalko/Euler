
package nphEuler

import scala.math._

object primes {

  /*
     Generate primes up to n
   */

  def genPrime(n:Int): List[Int] = {

    var p:List[Int] = Nil
    var nums   = List(2 to n:_*)

    while (!nums.isEmpty) {
      p    = nums(0) :: p
      nums = nums.filter(_ % nums(0) != 0)

    }

    p.reverse
  }


  def isPrime(n: Int) = {
    genPrime(ceil(sqrt(n)).toInt).find(n % _ == 0).isEmpty
  }


}

class Rational(n:Int,d:Int) {
  require(d != 0)
  override def toString = n + "/" + d
  def this(n:Int) = this(n,1)  //auxiliary constructor

  private val g = gcd(n.abs,d.abs)
  val numer = n / g
  val denom = d / g

  def + (that: Rational) = {
    new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
  }

  def - (that: Rational) = {
    new Rational(numer * that.denom - that.numer * denom, denom * that.denom)
  }

  def * (that: Rational) = {
    new Rational(numer * that.numer, denom * that.denom)
  }

  def / (that: Rational) = {
    new Rational(numer * that.denom, denom * that.numer)
  }

  def lessThan(that: Rational) = {
    numer * that.denom < that.numer * denom
  }

  def max(that: Rational) = {
    if (lessThan(that)) that
    else this
  }



  private def gcd(a:Int, b:Int): Int = {
    if (b == 0) a
    else gcd(b, a % b)
  }




}