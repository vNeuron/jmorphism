package morphism;

/**
 * A morphism is a structure-preserving mappings
 * between two objects.
 *
 * It's a one-to-one correspondence that preserves
 * some properties.
 *
 * @param <A> Is the domain of the morphism
 * @param <B> Is the codomain of the morphism
 */
public interface IMorphism<A, B> {
    /**
     * Get the image of an element of the domain.
     *
     * @param p An element of A
     * @return The image in B
     */
    B map(A p);

    /**
     * Get the inverse morphism.
     *
     * @return The inverse morphism from B to A.
     */
    IMorphism<B, A> inverse();
}
