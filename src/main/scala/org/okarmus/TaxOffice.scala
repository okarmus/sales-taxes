package org.okarmus

sealed trait TaxResolver{
  def taxRate(item: Item): BigDecimal
}

object TaxOffice extends TaxResolver {
  val taxes: List[TaxResolver] = List(ImportTax, BasicTax)
  override def taxRate(item: Item): BigDecimal = taxes.map(_.taxRate(item)).fold(BigDecimal(1.0))(_ + _)
}

object ImportTax extends TaxResolver {
  override def taxRate(item: Item): BigDecimal = if (item.imported) 0.05 else 0.0
}

object BasicTax extends TaxResolver {
  val taxFrees = List("book", "chocolate bar", "box of chocolates")

  override def taxRate(item: Item): BigDecimal = if (taxFrees contains item.name) 0.0 else 0.1
}