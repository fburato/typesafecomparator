package com.github.fburato.typesafecomparator.codegen;

import org.mdkt.compiler.InMemoryJavaCompiler;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.fail;

public class CodeGenUtils {

  public static final String BASE_PACKAGE = "com.github.fburato.typesafecomparator.api";

  public static Class<?> assertCompiles(final String className, final CodeGenerator generator, InMemoryJavaCompiler compiler) {
    try {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      PrintWriter writer = new PrintWriter(baos);
      generator.generate(writer);
      writer.close();
      String classSource = new String(baos.toByteArray(), StandardCharsets.UTF_8);
      return compiler.compile(className, classSource);
    } catch (Exception e) {
      fail(String.format("'%s' should have compiled", className), e);
      throw new RuntimeException();
    }
  }

  public static Class<?> assertCompiles(final String className, final CodeGenerator generator) {
    return assertCompiles(className, generator, InMemoryJavaCompiler.newInstance());
  }

  public static String qualifiedClassName(final String className) {
    return BASE_PACKAGE + "." + className;
  }
}
