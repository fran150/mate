package ar.org.pachisoft.matelang.utils;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import lombok.AllArgsConstructor;

/**
 * Extracts the configuration value from a particular class and allows to set a default
 * provider, transformations and validations.
 *
 * @param <T> Type of value to extract.
 */
public class ConfigValueExtractor<T> {
    private final T value;

    ConfigValueExtractor(T value) {
        this.value = value;
    }

    /**
     * Allows to apply a default value if the config value is not present.
     *
     * @param <S> Type of value to extract.
     */
    @AllArgsConstructor
    public static class DefaultValueApplier<S> {
        private final boolean hasValue;
        private final S value;

        public <R> DefaultValueApplier<R> transform(Function<S, R> transformer) {
            return new DefaultValueApplier<>(hasValue, transformer.apply(value));
        }

        /**
         * Supplier of a default value to apply in case the configuration is not defined.
         *
         * @param defaultValue Supplier of the default value to use.
         * @return Validator class.
         */
        public Validator<S> defaultValue(Supplier<S> defaultValue) {
            if (!hasValue) {
                return new Validator<>(defaultValue.get());
            } else {
                return new Validator<>(value);
            }
        }
    }

    /**
     * Allows to apply validations to the configuration value.
     *
     * @param <S> Type of value to extract.
     */
    @AllArgsConstructor
    public static class Validator<S> {
        private final S value;

        public <R> Validator<R> transform(Function<S, R> transformer) {
            return new Validator<>(transformer.apply(value));
        }

        public S validator(Consumer<S> validator) {
            validator.accept(value);
            return value;
        }

        public S validator() {
            return value;
        }
    }

    public <R> ConfigValueExtractor<R> transform(Function<T, R> transformer) {
        return new ConfigValueExtractor<>(transformer.apply(value));
    }

    public DefaultValueApplier<T> hasValue(Predicate<T> hasValuePredicate) {
        return new DefaultValueApplier<>(hasValuePredicate.test(value), value);
    }

    public static <T> ConfigValueExtractor<T> getter(Supplier<T> getter) {
        return new ConfigValueExtractor<>(getter.get());
    }
}
