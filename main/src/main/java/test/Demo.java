package test;

import com.github.fburato.typesafecomparator.api.ChainComparator1;
import com.github.fburato.typesafecomparator.api.ChainableComparator;

import java.util.Comparator;
import java.util.function.Function;

public class Demo {

  static class MyChainable implements ChainableComparator<String> {

    public <S> ChainComparator1<String,S> addComparator(Comparator<S> comparator) {
      return new ChainComparator1<>(this,comparator);
    }

    @Override
    public <S> void chain(Function<String, S> fieldGetter, Comparator<S> sComparator) {

    }

    @Override
    public int compare(String s, String t1) {
      return 0;
    }
  }

  public static void main(String[] argv) {
    MyChainable myChainable = new MyChainable();
  }

}