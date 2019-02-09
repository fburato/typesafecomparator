package com.github.fburato.typesafecomparator.codegen;

import com.github.fburato.typesafecomparator.api.FunctionLike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static com.github.fburato.typesafecomparator.codegen.CodeGenUtils.*;
@DisplayName("FunctionGenerator")
class FunctionGeneratorTest {

  @Test
  @DisplayName("should compile Function with expected name")
  void testName() {
    assertCompiles(qualifiedClassName("Function1"),new FunctionGenerator(1));
  }

  @Test
  @DisplayName("should compile Function with different suffix")
  void testDifferentSuffix() {
    assertCompiles(qualifiedClassName("Function100"),new FunctionGenerator(100));
  }

  @Nested
  @DisplayName("compiled class")
  class CompiledClassTests {

    Class<?> testee = assertCompiles(qualifiedClassName("Function1"),new FunctionGenerator(1));
    @Test
    @DisplayName("should be an interface")
    void testInterface() {
      assertThat(testee).isInterface();
    }

    @Test
    @DisplayName("should extend FunctionLike")
    void testFunctionLike() {
      assertThat(FunctionLike.class).isAssignableFrom(testee);
    }

    @Test
    @DisplayName("should be a annotated with @FunctionalInterface")
    void testFunctionalInterface() {
      assertThat(testee).hasAnnotations(FunctionalInterface.class);
    }
  }
}