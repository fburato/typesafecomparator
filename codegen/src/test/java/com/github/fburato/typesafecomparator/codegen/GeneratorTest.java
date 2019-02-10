package com.github.fburato.typesafecomparator.codegen;

import io.github.glytching.junit.extension.folder.TemporaryFolder;
import io.github.glytching.junit.extension.folder.TemporaryFolderExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Generator")
@ExtendWith(TemporaryFolderExtension.class)
public class GeneratorTest {

  TemporaryFolder temporaryFolder;

  @BeforeEach
  public void prepare(TemporaryFolder temporaryFolder) {
    this.temporaryFolder = temporaryFolder;
  }

  @Test
  @DisplayName("should generate all Functions in the path according to argument")
  void testFunction() throws Exception {
    File output = temporaryFolder.createDirectory("out");

    Generator.main(new String[]{output.getAbsolutePath(),"20"});

    String expectedDir = Paths.get(output.getAbsolutePath(),"com","github","fburato","typesafecomparator","api").toString();
    assertThat(new File(expectedDir))
        .exists()
        .isDirectory();
    assertThat(Arrays
        .stream(new File(expectedDir).listFiles())
        .map(File::getName)
        .collect(Collectors.toList()))
        .containsAnyElementsOf(IntStream
            .rangeClosed(1,20)
        .mapToObj( i -> "Function" + i +".java")
        .collect(Collectors.toList()));
  }

  @Test
  @DisplayName("should generate all ChainComparator in the path according to argument")
  void testChainComparator() throws Exception {
    File output = temporaryFolder.createDirectory("out");

    Generator.main(new String[]{output.getAbsolutePath(),"20"});

    String expectedDir = Paths.get(output.getAbsolutePath(),"com","github","fburato","typesafecomparator","api").toString();
    assertThat(Arrays
        .stream(new File(expectedDir).listFiles())
        .map(File::getName)
        .collect(Collectors.toList()))
        .containsAnyElementsOf(IntStream
            .rangeClosed(1,20)
            .mapToObj( i -> "ChainComparator" + i +".java")
            .collect(Collectors.toList()));
  }
}
