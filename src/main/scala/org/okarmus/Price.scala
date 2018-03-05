package org.okarmus

import BigDecimal.RoundingMode.HALF_UP

case class Price(net: BigDecimal) {

  def tax(taxRate: BigDecimal): TaxedPrice = {
    val gross = (taxRate * net).setScale(2, HALF_UP).rounded

    TaxedPrice(gross, gross - net)
  }
}

case class TaxedPrice(gross: BigDecimal, tax: BigDecimal) {
  def + (other: TaxedPrice): TaxedPrice = TaxedPrice(gross + other.gross, tax + other.tax)
}
