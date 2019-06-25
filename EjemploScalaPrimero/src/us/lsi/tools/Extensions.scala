package us.lsi.tools

import scala.collection.SortedMap

object Extensions {
  
  implicit class MapExtension[K<: Ordered[K],V](val x: Map[K,V]) extends AnyVal { 
    def toSortedMap = SortedMap(x.toSeq:_*) 
  }
  
  implicit class SeqExtension[E](val sq: Seq[E]) extends AnyVal { 
    def counting = 
      sq.groupBy(identity).mapValues(_.size)
      
    def counting[F](fKey: E=>F) = 
      sq.groupBy(fKey).mapValues(_.size)
      
    def grouping: Map[E,Seq[E]] = 
      sq.groupBy(identity)
      
    def grouping[F](fKey: E=>F): Map[F,Seq[E]]  = 
      sq.groupBy(fKey)
      
    def grouping[F,R](fKey: E=>F, fMap: E=>R): Map[F,Seq[R]] = 
      sq.groupBy(fKey).mapValues(e=>e.map(x=>fMap(x)))
      
    def grouping[F,R](fKey: E=>F, fMap: E=>R, fRed:(R,R)=>R):  Map[F,R] = 
      sq.groupBy(fKey).mapValues(e=>e.map(x=>fMap(x)).reduce(fRed))
      
    def grouping[F,R,S](fKey: E=>F, fMap: E=>R, fAcum:Seq[R]=>S):  Map[F,S] = 
      sq.groupBy(fKey).mapValues(e=>fAcum(e.map(x=>fMap(x))))
  }
  
  implicit class SeqTuplesExtension[K,V](val sq: Seq[(K,V)]) extends AnyVal {
    def toSortedMap(ord: Ordering[K]): SortedMap[K,V] = {
      SortedMap(sq: _*)(ord)
    }
  }
  
  implicit class ListExtension[E](val ls:List[E]) extends AnyVal {
    def second = ls.tail.head
  }
  
  
}