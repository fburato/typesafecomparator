package com.github.fburato.typesafecomparator.codegen;
import java.io.File;
import java.io.PrintStream;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Demo {

    public static class ChainComparatorPrinter {
        private final boolean isFinal;
        private final int comparators;
        private final PrintStream printWriter;

        public ChainComparatorPrinter(boolean isFinal, int comparators, PrintStream printWriter) {
            this.isFinal = isFinal;
            this.comparators = comparators;
            this.printWriter = printWriter;
        }

        public void print() {
            printHeader();
            printFields();
            printConstructor();
            printFooter();
        }


        private void printHeader() {
            final Supplier<String> printTypeVariables = () -> "<" + IntStream.range(0,comparators).mapToObj(i -> "T"+(i+1)).collect(Collectors.joining(",")) + ">";
            printWriter.println("package com.github.fburato.typesafecomparator;");
            printWriter.println("import java.util.Comparator;");
            printWriter.print("public class ChainComparator" + comparators + printTypeVariables.get() + "{\n");

        }

        private void printFields() {
            IntStream.range(0,comparators).forEach(i ->
                    printWriter.print(String.format("private final Comparator<%s> t%dComparator;\n","T"+(i+1),(i+1)))
            );

        }

        private void printConstructor() {
            final String parameters = IntStream.range(0,comparators).mapToObj( i ->
                    String.format("final Comparator<T%d> t%dComparator",i+1,i+1)).collect(Collectors.joining(", ")
            );
            final String body = IntStream.range(0,comparators).mapToObj( i ->
                    String.format("this.t%dComparator = t%dComparator;",i+1,i+1)
            ).collect(Collectors.joining("\n"));
            printWriter.print(String.format("public ChainComparator%d(%s){\n%s}",comparators,parameters,body));
        }

        private void printFooter() {
            printWriter.print("}\n");
        }

    }

    public static void main(String[] argv) throws Exception {
        final File f = new File(argv[0]);
        f.getParentFile().mkdirs();
        f.createNewFile();
        PrintStream fileOutputStream = new PrintStream(f);
        final ChainComparatorPrinter printer = new ChainComparatorPrinter(false,30,fileOutputStream);
        printer.print();
    }
}
