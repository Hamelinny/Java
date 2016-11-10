/**
 * Created by daphne on 16.10.16.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Collections {
    public static <T, V> ArrayList<V> map(Function1 <? super T, V> f, Iterable<T> src) {
        ArrayList<V> ans = new ArrayList<V>();
        for (Iterator <T> elem = src.iterator(); elem.hasNext();) {
            ans.add(f.apply(elem.next()));
        }
        return ans;
    }

    public static <T> ArrayList<T> filter(Predicate <? super T> f, Iterable<T> src) {
        ArrayList<T> ans = new ArrayList<T>();
        for (Iterator <T> elem = src.iterator(); elem.hasNext();) {
            T x = elem.next();
            if (f.apply(x)) {
                ans.add(x);
            }
        }
        return ans;
    }

    public static <T> ArrayList<T> takeWhile(Predicate <? super T> f, Iterable<T> src) {
        ArrayList<T> ans = new ArrayList<T>();
        for (Iterator <T> elem = src.iterator(); elem.hasNext();) {
            T x = elem.next();
            if (!f.apply(x)) {
                break;
            }
            ans.add(x);
        }
        return ans;
    }

    public static <T> ArrayList<T> takeUnless(Predicate <? super T> f, Iterable<T> src) {
        return takeWhile(f.not(), src);
    }

    public static <T, V> T foldl(Function2 <? super T, ? super V, T> f, T x, Iterable<V> src) {
        T ans = x;
        for (Iterator <V> elem = src.iterator(); elem.hasNext();) {
            V y = elem.next();
            ans = f.apply(ans, y);
        }
        return ans;
    }

    public static <T, V> V foldr(Function2 <? super T, ? super V, V> f, V x, Iterable<T> src) {
        return foldrImpl(f, x, src.iterator());
    }

    private static <T, V> V foldrImpl(Function2 <? super T, ? super V, V> f, V x, Iterator<T> it) {
        if (!it.hasNext())
            return x;
        T first = it.next();
        return f.apply(first, foldrImpl(f, x, it));
    }

}
