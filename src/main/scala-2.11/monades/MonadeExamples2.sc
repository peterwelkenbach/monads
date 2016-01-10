import scala.concurrent.{Await, Future}
import scalaz._
import Scalaz._


case class Address( plz: String, city: String, street: String)
case class User( id: Int, n: String, v: String )


def getUser( id: Int): Future[User]
def getAddress( user: User): Future[Address]


def addressFromUserId( id: Int ): Future[Address] = {
  for {
    user <- getUser( id )
    address <- getAddress( user )
  } yield address
}


def addressFromUserId_1
(
  getUserFunc: Int => Future[User],
  getAdrFunc: User => Future[Address]
  )
( id: Int ): Future[Address] = {
  for {
    user <- getUser( id )
    address <- getAddress( user )
  } yield address
}


val userTestF: Int => Future[User] =
     i => Future(User(i, "Pete", "Welki"))

val addressTestF: User => Future[Address] =
     _ => Future(Address("5200", "Schilda", "Irgendwo"))

val wiringTest = addressFromUserId_1( userTestF, addressTestF)(1)

//assert( Await.result( wiringTest,   ))


// ==============================================================

def addressFromUserId_2[ M[_]: Monad  ]
(
  getUserFunc: Int => M[User],
  getAdrFunc: User => M[Address]
  )
( id: Int ): M[Address] = {
  for {
    user <- getUser( id )
    address <- getAddress( user )
  } yield address
}


val userTestF_2: Int => Id[User] =
  i => User(i, "Pete", "Welki")

val addressTestF_2: User => Id[Address] =
  _ => Address("5200", "Schilda", "Irgendwo")

val wiringTest_2 = addressFromUserId_2[Id]( userTestF_2, addressTestF_2)(1)








