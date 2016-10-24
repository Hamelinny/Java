/**
 * Created by daphne on 16.10.16.
 */
public abstract class Function2 <T, E, V> {
    public abstract V apply(T x, E y);

    public <U> Function2 <T, E, U> compose(final Function1<? super V, U> g) {
        return new Function2 <T, E, U> () {
            public U apply(T x, E y) {
                return g.apply(Function2.this.apply(x, y));
            }

        };
    }

    public Function1 <E, V> bind1(final T x) {
        return new Function1 <E, V> () {
            public V apply(E y) {
                return Function2.this.apply(x, y);
            }
        };

    }

    public Function1 <T, V> bind2(final E y) {
        return new Function1 <T, V> () {
            public V apply(T x) {
                return Function2.this.apply(x, y);
            }
        };
    }

    public Function1 <T, Function1 <E, V> > curry() {
        return new Function1 <T, Function1 <E, V> >() {
            public Function1 <E, V> apply(T x) {
                return Function2.this.bind1(x);
            }
        };
    }

}
