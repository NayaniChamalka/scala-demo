package com.example.model

case class Article(id: Int, title: String, content: String) {
  def toJSON = {
    import net.liftweb.json.JsonDSL._

    ("id" -> id) ~ ("title" -> title) ~ ("content" -> content)
  }
}

object Article {

  var store: List[Article] = Article(1, "GOT", "seven kingdoms") :: Nil

  def addArticle(title: String, content: String): Article = {
    val nextID = store.map(_.id).max + 1
    val newArticle = new Article(nextID, title, content)
    store ::= newArticle
    newArticle
  }

  def getArticle(id: Int): Option[Article] = store.filter(_.id == id).headOption
  def deleteArticle(id: Int) { store = store.filterNot(_.id == id) }
}
