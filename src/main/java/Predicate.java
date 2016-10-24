/**
 * Created by daphne on 16.10.16.
 */
public abstract class Predicate <T> extends Function1<T, Boolean> {
    final static Predicate <Object> ALWAYS_TRUE = new Predicate <Object>() {
        public Boolean apply(Object x) {
            return true;
        }
    };

    final static Predicate <Object> ALWAYS_FALSE = new Predicate <Object>() {
        public Boolean apply(Object x) {
            return false;
        }
    };

    public <V extends T> Predicate <V> or(final Predicate <? super V> g) {
        return new Predicate <V>() {
            public Boolean apply(V x) {
                return Predicate.this.apply(x) || g.apply(x);
            }
        };
    }

    public <V extends T> Predicate <V> and(final Predicate <? super V> g) {
        return new Predicate <V>() {
            public Boolean apply(V x) {
                return Predicate.this.apply(x) && g.apply(x);
            }
        };
    }

    public Predicate <T> not() {
        return new Predicate <T>() {
            public Boolean apply(T x) {
                return !Predicate.this.apply(x);
            }
        };
    }

}
