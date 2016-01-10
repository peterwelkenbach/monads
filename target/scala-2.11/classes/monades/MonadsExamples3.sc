import scala.concurrent.{Await, Future}
import scalaz._
import Scalaz._


def expFunc_1( a: Int, b: Int): Future[Int] = {
   Future {  a*b /* expensive calculation here */  }
}


def expFunc_2[M[_]]( a: Int, b: Int)
                   (implicit m: Monad[M]): M[Int] = {
  m.point {  a*b /* expensive calculation here */  }
}


expFunc_2[Future](2,4)

expFunc_2[Id](2,4)

