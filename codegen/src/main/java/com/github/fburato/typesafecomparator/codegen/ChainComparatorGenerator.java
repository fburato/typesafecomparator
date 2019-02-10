package com.github.fburato.typesafecomparator.codegen;

import java.io.PrintWriter;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChainComparatorGenerator implements CodeGenerator {

  private final int types;
  private final boolean isFinal;
  private final static String CLASS_NAME = "ChainComparator";

  public ChainComparatorGenerator(final int types) {
    this(types, false);
  }

  public ChainComparatorGenerator(final int types, final boolean isFinal) {
    this.types = types;
    this.isFinal = isFinal;
  }

  private class InternalGenerator {
    private final PrintWriter writer;
    private final String classSimpleName;
    private final String typeDeclaration;

    public InternalGenerator(final PrintWriter writer) {
      this.writer = writer;
      this.classSimpleName = CLASS_NAME + types;
      this.typeDeclaration = IntStream.rangeClosed(1, types).mapToObj(i -> "T" + i).collect(Collectors.joining(","));
    }

    public void generate() {
      header();
      classDeclaration();
      fields();
      compareMethod();
      constructor();
      basicChainMethod();
      if (!isFinal) {
        addComparatorMethod();
      }
      IntStream.rangeClosed(1, types).forEach(this::chainMethod);
      classFooter();
    }

    private void header() {
      writer.println("package com.github.fburato.typesafecomparator.api;");
      writer.println("import java.util.Comparator;");
      writer.println("import java.util.function.Function;");
    }

    private void classDeclaration() {
      writer.println(String.format("public final class %s<T,%s> implements Comparator<T> {", classSimpleName, typeDeclaration));
    }

    private void compareMethod() {
      writer.println("@Override");
      writer.println("public int compare(T t1, T t2){return this.chainableComparator.compare(t1,t2);}");
    }


    private void fields() {
      writer.println("private final ChainableComparator<T> chainableComparator;");
      writer.println(IntStream
          .rangeClosed(1, types)
          .mapToObj(i -> String.format("private final Comparator<T%d> comparator%d;", i, i))
          .collect(Collectors.joining("\n"))
      );
    }

    private void constructor() {
      final String parameterList = IntStream
          .rangeClosed(1, types)
          .mapToObj(i -> String.format("Comparator<T%d> comparator%d", i, i)).collect(Collectors.joining(", "));
      writer.println(
          String.format("public %s(ChainableComparator<T> chainableComparator, %s){",
              classSimpleName,
              parameterList)
      );
      writer.println("this.chainableComparator = chainableComparator;");
      writer.println(IntStream
          .rangeClosed(1, types)
          .mapToObj(i -> String.format("this.comparator%d = comparator%d;", i, i))
          .collect(Collectors.joining("\n"))
      );
      writer.println("}");
    }

    private void basicChainMethod() {
      writer.println(
          String.format(
              "public <S> %s<T,%s> chain(Function<T,S> fieldGetter, Comparator<S> comparator){", classSimpleName, typeDeclaration
          )
      );
      writer.println("this.chainableComparator.chain(fieldGetter,comparator);");
      writer.println("return this;");
      writer.println("}");
    }

    private void addComparatorMethod() {
      writer.println(
          String.format(
              "public <T%d> %s%d<T,%s,T%d> addComparator(Comparator<T%d> comparator){",
              types + 1,
              CLASS_NAME,
              types + 1,
              typeDeclaration,
              types + 1,
              types + 1
          )
      );
      writer.println(String.format(
          "return new %s%d<>(this.chainableComparator,%s,comparator);",
          CLASS_NAME,
          types + 1,
          IntStream
              .rangeClosed(1, types)
              .mapToObj(i -> String.format("this.comparator%d", i))
              .collect(Collectors.joining(", "))
      ));
      writer.println("}");
    }

    private void chainMethod(int type) {
      writer.println(
          String.format(
              "public %s<T,%s> chain(Function%d<T,T%d> fieldGetter){",
              classSimpleName,
              typeDeclaration,
              type,
              type
          )
      );
      writer.println(String.format("this.chainableComparator.chain(fieldGetter.asFunction(),comparator%d);", type));
      writer.println("return this;");
      writer.println("}");
    }

    private void classFooter() {
      writer.println("}");
    }
  }

  @Override
  public void generate(PrintWriter printWriter) {
    InternalGenerator generator = new InternalGenerator(printWriter);
    generator.generate();
  }
}
