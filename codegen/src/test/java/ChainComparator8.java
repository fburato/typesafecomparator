import java.util.Comparator;
public class ChainComparator8<T1,T2,T3,T4,T5,T6,T7,T8>{
    private final Comparator<T1> t1Comparator;
    private final Comparator<T2> t2Comparator;
    private final Comparator<T3> t3Comparator;
    private final Comparator<T4> t4Comparator;
    private final Comparator<T5> t5Comparator;
    private final Comparator<T6> t6Comparator;
    private final Comparator<T7> t7Comparator;
    private final Comparator<T8> t8Comparator;
    public ChainComparator8(final Comparator<T1> t1Comparator, final Comparator<T2> t2Comparator, final Comparator<T3> t3Comparator, final Comparator<T4> t4Comparator, final Comparator<T5> t5Comparator, final Comparator<T6> t6Comparator, final Comparator<T7> t7Comparator, final Comparator<T8> t8Comparator){
        this.t1Comparator = t1Comparator;
        this.t2Comparator = t2Comparator;
        this.t3Comparator = t3Comparator;
        this.t4Comparator = t4Comparator;
        this.t5Comparator = t5Comparator;
        this.t6Comparator = t6Comparator;
        this.t7Comparator = t7Comparator;
        this.t8Comparator = t8Comparator;}}