package org

package object okarmus {
  type TaxedItem = (Item, TaxedPrice)
  type NotTaxedItem = (Item, NetPrice)
}
