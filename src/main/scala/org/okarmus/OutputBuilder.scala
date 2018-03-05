package org.okarmus

trait OutputBuilder[A] {
  def build(a: A): String
}

object OutputBuilder{

  def apply[A](implicit builder: OutputBuilder[A]): OutputBuilder[A] = builder

  object implicits {
    def build[A: OutputBuilder](a: A): String = OutputBuilder[A].build(a)

    implicit class Ops[A: OutputBuilder](a: A) {
      def build = OutputBuilder[A].build(a)
    }
  }

  import implicits._
  implicit val itemOutput: OutputBuilder[Item] = item => s"${item.quantity} ${item.name}: ${item.price.net}"

  implicit val itemsOutput: OutputBuilder[List[Item]] = items => items.map(_.build).mkString("\n")

  implicit val taxOutput: OutputBuilder[TaxedPrice] = taxes =>  s"Sales Taxes: ${taxes.tax}\nTotal: ${taxes.gross}"

  implicit val receiptOutput: OutputBuilder[Receipt] = receipt => receipt.items.build + "\n" + receipt.taxes.build

}
