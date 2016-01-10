package nels


import scalaz._
import Scalaz._




object NelExamples extends App {

  //===================================================
  //  simple example
  //===================================================
  val list = NonEmptyList( 1,2,3)

  val res = list.tails

  println( res )

  println( res.head )

  println( res.list(0) )
  println( res.list(1) )
  println( res.list(2) )


  def f(i: Int): Int = i * 2

  val res2 = list.tails.map( _.map(f) )

  println( res2 )


  println("########################################################################")

 //====================================================
 //  event store example
 //===================================================
 case class Event( id: Int, event: String)

 val eventStore = NonEmptyList[Event](  Event(4, "pay"),
                                        Event(3, "buy"),
                                        Event(2, "saveToBasket"),
                                        Event(1, "search"),
                                        Event(0, "login")  )

 def printHistoryAtPointInTome( pointInTime: Int,
                                store: NonEmptyList[Event]) = {
    val res = store.tails.list( pointInTime )

    res.reverse.foreach {
      ev => {
        println( ev.event )
      }
    }
 }

  printHistoryAtPointInTome(2, eventStore )

  println("=========================================================================")

  val tree: Tree[Int] = 1.node(  2.leaf,
                                  3.node(
                                     4.leaf,
                                     5.node(
                                     6.leaf,
                                     7.leaf )
                                  )
                          )

  println( tree.foldMap( _.toString )  )

  // A tree of TreeLocs (aka Zipper). Each TreeLoc is rooted at `tree` but focussed on a different node.
  val allTreeLocs: Tree[TreeLoc[Int]] = tree.loc.cojoin.toTree

  // Getting the label of the focussed node from each TreeLoc restores the original tree
  println("-----------------------------")
  println( allTreeLocs.map(_.getLabel) )
  println("-----------------------------")

  // Alternatively, we can get the path to root from each node
  allTreeLocs.map(_.path).drawTree.println


  println("=========================================================================")


  /*
  * Returns the paths from each leaf node back to the root node.
  */
  def leafPaths[T](tree: Tree[T]): Stream[Stream[T]]
  = tree.loc.cojoin.toTree.flatten.filter(_.isLeaf).map(_.path)

  // And finally wrap this up as a function:
  println( leafPaths(tree).toList.map(_.toList.reverse) )


  println( tree.levels.indices )

}
