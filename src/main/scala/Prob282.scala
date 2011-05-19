package com.spotinfluence


object Prob282 {
  def main(args: Array[String]) {


    def Ack(m:BigInt, n:BigInt): BigInt = {

      if (m == 0)           n+1
      if (n == 0)           Ack(m-1,1)
      else                  Ack(m-1,Ack(m,n-1))
    }

    /* Never gonna work...

     */






  }
}