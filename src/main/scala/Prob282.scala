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


/* -ljr
 *
 * Ah, Ackermann's function.  How I hate thee.
 *
 * It's impossible to calculate ack(6,6) so the trick is going to be
 * figuring out ack(m, n) mod q. Wolfram points to Sloane's for A(n, n)
 * which goes up to n=5 == 2^2^...^2 (2 hyper-power 65536) - 3.  This
 * paper
 *
 *  https://files.oakland.edu/users/grossman/web/ackermod.pdf
 *
 * seems to indicate that mod-n Ackermann stabalizes quickly.  I'm not
 * able to make any progress without blowing the stack so I suspect this
 * is gonna take some mad numerical methods.  Best of luck.  :)
 */

  }
}
