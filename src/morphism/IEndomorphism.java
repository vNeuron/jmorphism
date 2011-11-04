package morphism;

/**
 * An endomorphism is a particular morphism where
 * the domain = codomain.
 *
 * @param <T> The domain/codomain of the morphism.
 */
public interface IEndomorphism<T> extends IMorphism<T, T> {
    /**
     * Get the inverse endomorphism.
     *
     * @return The inverse endomorphism.
     */
    IEndomorphism<T> inverse();
}
