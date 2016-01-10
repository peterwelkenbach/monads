import scala.concurrent.Future
import scalaz._
import Scalaz._



// retrieve a number from a map (but it might not be there)
def get( key: String): Option[Int] = ???

//perform division (but might try to divide by zero
def divide( num: Int, denom: Int): Option[Int] = ???

// Make an HTTP call (eventually)
def httpGet( url: String): Future[String] = ???


// store a value in the database (eventually)
def persist( data: String): Future[Unit] = ???


case class Address( plz: String, city: String, street: String)
case class User( n: String, v: String, age: Int)

def getUserId( username: String): Option[Int]
def getUser( id: Int): Option[User]
def getAddress( user: User): Option[Address]


def addressFromUsername( username: String) =
    getUserId(username)
       .flatMap( getUser )
       .flatMap(getAddress)

def addressFromUsername2( username: String ) = {
    for {
      id <- getUserId( username )
      user <- getUser( id )
      address <- getAddress( user )
    } yield address
}




