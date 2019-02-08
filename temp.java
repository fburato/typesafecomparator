package com.intuit.admin.message.shared.rpc.dto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TypeSafeChainComparator<T> implements Comparator<T> {

    public interface FunctionLike<T1, T2> {
        T2 apply(T1 value);

        default Function<T1, T2> asFunction() {
            return this::apply;
        }
    }

    @FunctionalInterface
    public interface Function1<T1, T2> extends FunctionLike<T1, T2> {
    }

    @FunctionalInterface
    public interface Function2<T1, T2> extends FunctionLike<T1, T2> {
    }

    @FunctionalInterface
    public interface Function3<T1, T2> extends FunctionLike<T1, T2> {
    }

    @FunctionalInterface
    public interface Function4<T1, T2> extends FunctionLike<T1, T2> {
    }

    @FunctionalInterface
    public interface Function5<T1, T2> extends FunctionLike<T1, T2> {
    }

    @FunctionalInterface
    public interface Function6<T1, T2> extends FunctionLike<T1, T2> {
    }

    @FunctionalInterface
    public interface Function7<T1, T2> extends FunctionLike<T1, T2> {
    }

    @FunctionalInterface
    public interface Function8<T1, T2> extends FunctionLike<T1, T2> {
    }

    private class InternalComparator implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return TypeSafeChainComparator.this.compare(o1, o2);
        }
    }

    public class ChainComparator1<T1> extends InternalComparator {

        private final Comparator<T1> t1Comparator;

        private ChainComparator1(Comparator<T1> t1Comparator) {
            this.t1Comparator = t1Comparator;
        }

        public <T2> ChainComparator2<T1, T2> addComparator(Comparator<T2> t2Comparator) {
            return new ChainComparator2<>(t1Comparator, t2Comparator);
        }

        public ChainComparator1<T1> chain(Function1<T, T1> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t1Comparator);
            return this;
        }

        public <S> ChainComparator1<T1> chain(Function<T, S> fieldGetter, Comparator<S> comparator) {
            TypeSafeChainComparator.this.chain(fieldGetter, comparator);
            return this;
        }
    }

    public class ChainComparator2<T1, T2> extends InternalComparator {

        private final Comparator<T1> t1Comparator;
        private final Comparator<T2> t2Comparator;

        private ChainComparator2(Comparator<T1> t1Comparator, Comparator<T2> t2Comparator) {
            this.t1Comparator = t1Comparator;
            this.t2Comparator = t2Comparator;
        }

        public <T3> ChainComparator3<T1, T2, T3> addComparator(Comparator<T3> t3Comparator) {
            return new ChainComparator3<>(t1Comparator, t2Comparator, t3Comparator);
        }

        public ChainComparator2<T1, T2> chain(Function2<T, T2> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t2Comparator);
            return this;
        }

        public ChainComparator2<T1, T2> chain(Function1<T, T1> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t1Comparator);
            return this;
        }

        public <S> ChainComparator2<T1, T2> chain(Function<T, S> fieldGetter, Comparator<S> comparator) {
            TypeSafeChainComparator.this.chain(fieldGetter, comparator);
            return this;
        }
    }

    public class ChainComparator3<T1, T2, T3> extends InternalComparator {
        private final Comparator<T1> t1Comparator;
        private final Comparator<T2> t2Comparator;
        private final Comparator<T3> t3Comparator;

        private ChainComparator3(Comparator<T1> t1Comparator, Comparator<T2> t2Comparator, Comparator<T3> t3Comparator) {
            this.t1Comparator = t1Comparator;
            this.t2Comparator = t2Comparator;
            this.t3Comparator = t3Comparator;
        }

        public <T4> ChainComparator4<T1, T2, T3, T4> addComparator(Comparator<T4> t4Comparator) {
            return new ChainComparator4<>(t1Comparator, t2Comparator, t3Comparator, t4Comparator);
        }

        public ChainComparator3<T1, T2, T3> chain(Function3<T, T3> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t3Comparator);
            return this;
        }

        public ChainComparator3<T1, T2, T3> chain(Function2<T, T2> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t2Comparator);
            return this;
        }

        public ChainComparator3<T1, T2, T3> chain(Function1<T, T1> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t1Comparator);
            return this;
        }

        public <S> ChainComparator3<T1, T2, T3> chain(Function<T, S> fieldGetter, Comparator<S> comparator) {
            TypeSafeChainComparator.this.chain(fieldGetter, comparator);
            return this;
        }
    }

    public class ChainComparator4<T1, T2, T3, T4> extends InternalComparator {
        private final Comparator<T1> t1Comparator;
        private final Comparator<T2> t2Comparator;
        private final Comparator<T3> t3Comparator;
        private final Comparator<T4> t4Comparator;

        private ChainComparator4(Comparator<T1> t1Comparator,
                Comparator<T2> t2Comparator,
                Comparator<T3> t3Comparator,
                Comparator<T4> t4Comparator) {
            this.t1Comparator = t1Comparator;
            this.t2Comparator = t2Comparator;
            this.t3Comparator = t3Comparator;
            this.t4Comparator = t4Comparator;
        }

        public <T5> ChainComparator5<T1, T2, T3, T4, T5> addComparator(Comparator<T5> t5Comparator) {
            return new ChainComparator5<>(t1Comparator, t2Comparator, t3Comparator, t4Comparator, t5Comparator);
        }

        public ChainComparator4<T1, T2, T3, T4> chain(Function4<T, T4> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t4Comparator);
            return this;
        }

        public ChainComparator4<T1, T2, T3, T4> chain(Function3<T, T3> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t3Comparator);
            return this;
        }

        public ChainComparator4<T1, T2, T3, T4> chain(Function2<T, T2> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t2Comparator);
            return this;
        }

        public ChainComparator4<T1, T2, T3, T4> chain(Function1<T, T1> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t1Comparator);
            return this;
        }

        public <S> ChainComparator4<T1, T2, T3, T4> chain(Function<T, S> fieldGetter, Comparator<S> comparator) {
            TypeSafeChainComparator.this.chain(fieldGetter, comparator);
            return this;
        }
    }

    public class ChainComparator5<T1, T2, T3, T4, T5> extends InternalComparator {
        private final Comparator<T1> t1Comparator;
        private final Comparator<T2> t2Comparator;
        private final Comparator<T3> t3Comparator;
        private final Comparator<T4> t4Comparator;
        private final Comparator<T5> t5Comparator;

        private ChainComparator5(Comparator<T1> t1Comparator,
                Comparator<T2> t2Comparator,
                Comparator<T3> t3Comparator,
                Comparator<T4> t4Comparator,
                Comparator<T5> t5Comparator) {
            this.t1Comparator = t1Comparator;
            this.t2Comparator = t2Comparator;
            this.t3Comparator = t3Comparator;
            this.t4Comparator = t4Comparator;
            this.t5Comparator = t5Comparator;
        }

        public ChainComparator5<T1, T2, T3, T4, T5> chain(Function1<T, T1> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t1Comparator);
            return this;
        }

        public ChainComparator5<T1, T2, T3, T4, T5> chain(Function2<T, T2> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t2Comparator);
            return this;
        }

        public ChainComparator5<T1, T2, T3, T4, T5> chain(Function3<T, T3> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t3Comparator);
            return this;
        }

        public ChainComparator5<T1, T2, T3, T4, T5> chain(Function4<T, T4> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t4Comparator);
            return this;
        }

        public ChainComparator5<T1, T2, T3, T4, T5> chain(Function5<T, T5> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t5Comparator);
            return this;
        }

        public <S> ChainComparator5<T1, T2, T3, T4, T5> chain(Function<T, S> fieldGetter, Comparator<S> comparator) {
            TypeSafeChainComparator.this.chain(fieldGetter, comparator);
            return this;
        }

        public <T6> ChainComparator6<T1, T2, T3, T4, T5, T6> addComparator(Comparator<T6> t6Comparator) {
            return new ChainComparator6<>(t1Comparator, t2Comparator, t3Comparator, t4Comparator, t5Comparator, t6Comparator);
        }
    }

    public class ChainComparator6<T1, T2, T3, T4, T5, T6> extends InternalComparator {
        private final Comparator<T1> t1Comparator;
        private final Comparator<T2> t2Comparator;
        private final Comparator<T3> t3Comparator;
        private final Comparator<T4> t4Comparator;
        private final Comparator<T5> t5Comparator;
        private final Comparator<T6> t6Comparator;

        private ChainComparator6(Comparator<T1> t1Comparator,
                Comparator<T2> t2Comparator,
                Comparator<T3> t3Comparator,
                Comparator<T4> t4Comparator,
                Comparator<T5> t5Comparator,
                Comparator<T6> t6Comparator) {
            this.t1Comparator = t1Comparator;
            this.t2Comparator = t2Comparator;
            this.t3Comparator = t3Comparator;
            this.t4Comparator = t4Comparator;
            this.t5Comparator = t5Comparator;
            this.t6Comparator = t6Comparator;
        }

        public ChainComparator6<T1, T2, T3, T4, T5, T6> chain(Function1<T, T1> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t1Comparator);
            return this;
        }

        public ChainComparator6<T1, T2, T3, T4, T5, T6> chain(Function2<T, T2> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t2Comparator);
            return this;
        }

        public ChainComparator6<T1, T2, T3, T4, T5, T6> chain(Function3<T, T3> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t3Comparator);
            return this;
        }

        public ChainComparator6<T1, T2, T3, T4, T5, T6> chain(Function4<T, T4> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t4Comparator);
            return this;
        }

        public ChainComparator6<T1, T2, T3, T4, T5, T6> chain(Function5<T, T5> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t5Comparator);
            return this;
        }

        public ChainComparator6<T1, T2, T3, T4, T5, T6> chain(Function6<T, T6> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t6Comparator);
            return this;
        }

        public <S> ChainComparator6<T1, T2, T3, T4, T5, T6> chain(Function<T, S> fieldGetter, Comparator<S> comparator) {
            TypeSafeChainComparator.this.chain(fieldGetter, comparator);
            return this;
        }

        public <T7> ChainComparator7<T1, T2, T3, T4, T5, T6, T7> addComparator(Comparator<T7> t7Comparator) {
            return new ChainComparator7<>(t1Comparator, t2Comparator, t3Comparator, t4Comparator, t5Comparator, t6Comparator,
                    t7Comparator);
        }
    }

    public class ChainComparator7<T1, T2, T3, T4, T5, T6, T7> extends InternalComparator {
        private final Comparator<T1> t1Comparator;
        private final Comparator<T2> t2Comparator;
        private final Comparator<T3> t3Comparator;
        private final Comparator<T4> t4Comparator;
        private final Comparator<T5> t5Comparator;
        private final Comparator<T6> t6Comparator;
        private final Comparator<T7> t7Comparator;

        private ChainComparator7(Comparator<T1> t1Comparator,
                Comparator<T2> t2Comparator,
                Comparator<T3> t3Comparator,
                Comparator<T4> t4Comparator,
                Comparator<T5> t5Comparator,
                Comparator<T6> t6Comparator,
                Comparator<T7> t7Comparator) {
            this.t1Comparator = t1Comparator;
            this.t2Comparator = t2Comparator;
            this.t3Comparator = t3Comparator;
            this.t4Comparator = t4Comparator;
            this.t5Comparator = t5Comparator;
            this.t6Comparator = t6Comparator;
            this.t7Comparator = t7Comparator;
        }

        public ChainComparator7<T1, T2, T3, T4, T5, T6, T7> chain(Function1<T, T1> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t1Comparator);
            return this;
        }

        public ChainComparator7<T1, T2, T3, T4, T5, T6, T7> chain(Function2<T, T2> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t2Comparator);
            return this;
        }

        public ChainComparator7<T1, T2, T3, T4, T5, T6, T7> chain(Function3<T, T3> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t3Comparator);
            return this;
        }

        public ChainComparator7<T1, T2, T3, T4, T5, T6, T7> chain(Function4<T, T4> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t4Comparator);
            return this;
        }

        public ChainComparator7<T1, T2, T3, T4, T5, T6, T7> chain(Function5<T, T5> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t5Comparator);
            return this;
        }

        public ChainComparator7<T1, T2, T3, T4, T5, T6, T7> chain(Function6<T, T6> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t6Comparator);
            return this;
        }

        public ChainComparator7<T1, T2, T3, T4, T5, T6, T7> chain(Function7<T, T7> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t7Comparator);
            return this;
        }

        public <S> ChainComparator7<T1, T2, T3, T4, T5, T6, T7> chain(Function<T, S> fieldGetter, Comparator<S> comparator) {
            TypeSafeChainComparator.this.chain(fieldGetter, comparator);
            return this;
        }

        public <T8> ChainComparator8<T1, T2, T3, T4, T5, T6, T7, T8> addComparator(Comparator<T8> t8Comparator) {
            return new ChainComparator8<>(t1Comparator, t2Comparator, t3Comparator, t4Comparator, t5Comparator, t6Comparator,
                    t7Comparator, t8Comparator);
        }
    }

    public class ChainComparator8<T1, T2, T3, T4, T5, T6, T7, T8> extends InternalComparator {
        private final Comparator<T1> t1Comparator;
        private final Comparator<T2> t2Comparator;
        private final Comparator<T3> t3Comparator;
        private final Comparator<T4> t4Comparator;
        private final Comparator<T5> t5Comparator;
        private final Comparator<T6> t6Comparator;
        private final Comparator<T7> t7Comparator;
        private final Comparator<T8> t8Comparator;

        private ChainComparator8(Comparator<T1> t1Comparator,
                Comparator<T2> t2Comparator,
                Comparator<T3> t3Comparator,
                Comparator<T4> t4Comparator,
                Comparator<T5> t5Comparator,
                Comparator<T6> t6Comparator,
                Comparator<T7> t7Comparator,
                Comparator<T8> t8Comparator) {
            this.t1Comparator = t1Comparator;
            this.t2Comparator = t2Comparator;
            this.t3Comparator = t3Comparator;
            this.t4Comparator = t4Comparator;
            this.t5Comparator = t5Comparator;
            this.t6Comparator = t6Comparator;
            this.t7Comparator = t7Comparator;
            this.t8Comparator = t8Comparator;
        }

        public ChainComparator8<T1, T2, T3, T4, T5, T6, T7, T8> chain(Function1<T, T1> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t1Comparator);
            return this;
        }

        public ChainComparator8<T1, T2, T3, T4, T5, T6, T7, T8> chain(Function2<T, T2> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t2Comparator);
            return this;
        }

        public ChainComparator8<T1, T2, T3, T4, T5, T6, T7, T8> chain(Function3<T, T3> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t3Comparator);
            return this;
        }

        public ChainComparator8<T1, T2, T3, T4, T5, T6, T7, T8> chain(Function4<T, T4> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t4Comparator);
            return this;
        }

        public ChainComparator8<T1, T2, T3, T4, T5, T6, T7, T8> chain(Function5<T, T5> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t5Comparator);
            return this;
        }

        public ChainComparator8<T1, T2, T3, T4, T5, T6, T7, T8> chain(Function6<T, T6> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t6Comparator);
            return this;
        }

        public ChainComparator8<T1, T2, T3, T4, T5, T6, T7, T8> chain(Function7<T, T7> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t7Comparator);
            return this;
        }

        public ChainComparator8<T1, T2, T3, T4, T5, T6, T7, T8> chain(Function8<T, T8> fieldGetter) {
            TypeSafeChainComparator.this.chain(fieldGetter.asFunction(), t8Comparator);
            return this;
        }

        public <S> ChainComparator8<T1, T2, T3, T4, T5, T6, T7, T8> chain(Function<T, S> fieldGetter,
                Comparator<S> comparator) {
            TypeSafeChainComparator.this.chain(fieldGetter, comparator);
            return this;
        }
    }

    public interface ComparatorDecorator {
        <S> Comparator<S> decorate(Comparator<S> comparator);
    }

    public static class NullSafeComparatorDecorator implements ComparatorDecorator {

        @Override
        public <S> Comparator<S> decorate(Comparator<S> comparator) {
            return (o1, o2) -> {
                if (o1 == null && o2 == null) {
                    return 0;
                } else if (o1 == null) {
                    return 1;
                } else if (o2 == null) {
                    return -1;
                } else {
                    return comparator.compare(o1, o2);
                }
            };
        }
    }

    public static ComparatorDecorator nullSafeDecorator = new NullSafeComparatorDecorator();
    public static ComparatorDecorator identityDecorator = new IdentityComparatorDecorator();

    private static class IdentityComparatorDecorator implements ComparatorDecorator {

        @Override
        public <S> Comparator<S> decorate(Comparator<S> comparator) {
            return comparator;
        }
    }

    private final List<BiFunction<T, T, Integer>> comparisons = new ArrayList<>();
    private final ComparatorDecorator decorator;

    private TypeSafeChainComparator(ComparatorDecorator decorator) {
        this.decorator = decorator;
    }

    public <T1> ChainComparator1<T1> addComparator(Comparator<T1> t1Comparator) {
        return new ChainComparator1<>(t1Comparator);
    }

    public static <T1> TypeSafeChainComparator<T1> create() {
        return new TypeSafeChainComparator<>(identityDecorator);
    }

    public static <T1> TypeSafeChainComparator<T1> createNullSafe() {
        return new TypeSafeChainComparator<>(nullSafeDecorator);
    }

    public static <T1> TypeSafeChainComparator<T1> createWithDecorator(ComparatorDecorator decorator) {
        return new TypeSafeChainComparator<>(decorator);
    }

    @Override
    public int compare(T o1, T o2) {
        for (BiFunction<T, T, Integer> i : comparisons) {
            final Integer value = i.apply(o1, o2);
            if (value != 0) {
                return value;
            }
        }
        return 0;
    }

    public <S> TypeSafeChainComparator<T> chain(Function<T, S> fieldGetter, Comparator<S> comparator) {
        final Comparator<S> c = decorator.decorate(comparator);
        comparisons.add((o1, o2) -> c.compare(fieldGetter.apply(o1), fieldGetter.apply(o2)));
        return this;
    }
}
