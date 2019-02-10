package com.github.fburato.typesafecomparator.codegen;

import com.github.fburato.typesafecomparator.api.ChainableComparator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mdkt.compiler.InMemoryJavaCompiler;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.github.fburato.typesafecomparator.codegen.CodeGenUtils.assertCompiles;
import static com.github.fburato.typesafecomparator.codegen.CodeGenUtils.qualifiedClassName;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ChainComparatorGenerator")
class ChainComparatorGeneratorTest {

  private static final InMemoryJavaCompiler compiler = InMemoryJavaCompiler.newInstance();

  @BeforeAll
  static void setUpCompiler() {
    IntStream
        .rangeClosed(1, 50)
        .forEach(i -> assertCompiles(qualifiedClassName("Function" + i), new FunctionGenerator(i), compiler));
  }

  @Nested
  @DisplayName("compiled class")
  class CompiledClassTest {

    final Class<?> finalTestee = assertCompiles(qualifiedClassName("ChainComparator50"), new ChainComparatorGenerator(50, true), compiler);
    final Class<?> twoComparatorTestee = assertCompiles(qualifiedClassName("ChainComparator2"), new ChainComparatorGenerator(2, true), compiler);
    final Class<?> nonFinalTestee = assertCompiles(qualifiedClassName("ChainComparator1"), new ChainComparatorGenerator(1), compiler);

    @Test
    @DisplayName("should extend Comparator")
    void testHasInternalFunction() {
      assertThat(Comparator.class).isAssignableFrom(nonFinalTestee);
      assertThat(Comparator.class).isAssignableFrom(twoComparatorTestee);
    }

    @Test
    @DisplayName("should have constructor that takes a ChainableComparator and many Comparators")
    void testChainableComparator() throws Exception {
      assertThat(twoComparatorTestee.getConstructor(ChainableComparator.class, Comparator.class, Comparator.class)).isNotNull();
      assertThat(nonFinalTestee.getConstructor(ChainableComparator.class, Comparator.class)).isNotNull();
    }

    @Test
    @DisplayName("should have a chain method with a getter and a comparator")
    void testBasicChain() throws Exception {
      assertThat(twoComparatorTestee.getMethod("chain", Function.class, Comparator.class)).isNotNull();
      assertThat(nonFinalTestee.getMethod("chain", Function.class, Comparator.class)).isNotNull();
    }

    @Test
    @DisplayName("should not have an addComparator method if it is final")
    void testFinalNoAddComparator() {
      assertThat(Arrays.stream(finalTestee.getMethods())
          .map(Method::getName)
          .collect(Collectors.toList())
      ).doesNotContain("addComparator");
    }

    @Test
    @DisplayName("should have addComparator method if it is final")
    void testNonFinalAddComparator() throws Exception {
      assertThat(nonFinalTestee.getMethod("addComparator", Comparator.class)).isNotNull();
    }

    @Test
    @DisplayName("should add as many chain method as types")
    void testChain() {
      assertThat(Arrays.stream(finalTestee.getMethods())
          .map(Method::getName)
          .filter(s -> s.equals("chain"))
          .collect(Collectors.toList())
      ).hasSize(51);
      assertThat(Arrays.stream(twoComparatorTestee.getMethods())
          .map(Method::getName)
          .filter(s -> s.equals("chain"))
          .collect(Collectors.toList())
      ).hasSize(3);
      assertThat(Arrays.stream(nonFinalTestee.getMethods())
          .map(Method::getName)
          .filter(s -> s.equals("chain"))
          .collect(Collectors.toList())
      ).hasSize(2);
    }
  }
}
