/**
 * Created by daphne on 16.10.16.
 */
public abstract class Function1 <T, V> {
    public abstract V apply(T x);

    public <U> Function1 <T, U> compose(final Function1<? super V, U> g) {
        return new Function1 <T, U> () {
            public U apply(T x) {
                return g.apply(Function1.this.apply(x));
            }

        };
    }

}
