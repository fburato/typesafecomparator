package com.github.fburato.typesafecomparator.codegen;

import java.io.PrintWriter;

public class FunctionGenerator implements CodeGenerator {

  private final int suffix;

  public FunctionGenerator(final int suffix) {
    this.suffix = suffix;
  }

  @Override
  public void generate(PrintWriter printWriter) {
    printHeader(printWriter);
    printDeclaration(printWriter);
    printFooter(printWriter);
  }

  private void printHeader(PrintWriter writer) {
    writer.println("package com.github.fburato.typesafecomparator.api;");
  }

  private void printDeclaration(PrintWriter writer) {
    writer.println("@FunctionalInterface");
    writer.println("public interface Function"+suffix+" <T1,T2> extends FunctionLike<T1,T2> {");
  }

  private void printFooter(PrintWriter writer) {
    writer.println("}");
  }

}
