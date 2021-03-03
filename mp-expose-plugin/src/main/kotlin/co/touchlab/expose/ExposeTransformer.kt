package co.touchlab.expose

import org.jetbrains.kotlin.descriptors.DescriptorVisibilities
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol
import org.jetbrains.kotlin.ir.util.hasAnnotation
import org.jetbrains.kotlin.ir.util.isAnonymousObject
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid


class ExposeTransformer(
  private val exposeAnnotation: IrClassSymbol,
) : IrElementTransformerVoid() {

  override fun visitFunction(declaration: IrFunction): IrStatement {
    if (!declaration.hasAnnotation(exposeAnnotation) &&
      !declaration.isAnonymousObject &&
      declaration.visibility !in DescriptorVisibilities.INVISIBLE_FROM_OTHER_MODULES
    ) {
      declaration.visibility = DescriptorVisibilities.INTERNAL
      println("Name: ${declaration.name}\nVisibility: ${declaration.visibility}\n")

    }
    return super.visitFunction(declaration)
  }
}

