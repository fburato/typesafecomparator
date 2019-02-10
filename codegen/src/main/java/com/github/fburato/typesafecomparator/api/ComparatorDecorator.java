package com.github.fburato.typesafecomparator.api;

import java.util.Comparator;

@FunctionalInterface
public interface ComparatorDecorator {
  <S> Comparator<S> decorate(Comparator<S> comparator);
}
