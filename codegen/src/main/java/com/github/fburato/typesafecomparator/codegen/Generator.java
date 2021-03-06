package com.github.fburato.typesafecomparator.codegen;

import java.io.File;
import java.io.PrintWriter;
import java.util.stream.IntStream;

public class Generator {

  private static final String apiPackage = String.join(File.separator, "com.github.fburato.typesafecomparator.api".split("\\."));

  public static void main(String[] argv) {
    final String baseDir = argv[0];
    final int types = Integer.parseInt(argv[1]);
    final File baseDirectory = new File(baseDir + File.separator + apiPackage);
    baseDirectory.mkdirs();
    IntStream
        .rangeClosed(1, types)
        .forEach(i -> {
          final String functionFile = baseDirectory.getAbsolutePath() + File.separator + "Function" + i + ".java";
          generateFile(functionFile, new FunctionGenerator(i));
        });
    IntStream
        .rangeClosed(1, types)
        .forEach(i -> {
          final String chainComparatorFile = baseDirectory.getAbsolutePath() + File.separator + "ChainComparator" + i + ".java";
          generateFile(chainComparatorFile, new ChainComparatorGenerator(i, i == types));
        });
  }

  private static void generateFile(String fileName, CodeGenerator generator) {
    try (final PrintWriter writer = new PrintWriter(new File(fileName))) {
      generator.generate(writer);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
