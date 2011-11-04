package morphism.endomorphism;

import morphism.IEndomorphism;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * represents the composition of one or more endomorphism.
 * @param <T> The domain/codomain of the endomorphisms.
 */
public class MorphismComposition<T> implements IEndomorphism<T> {

    final private ArrayList<IEndomorphism<T>> morphisms;

    public MorphismComposition(Collection<IEndomorphism<T>> morphisms) {
        this.morphisms = new ArrayList<IEndomorphism<T>>();
        this.morphisms.addAll(morphisms);
    }

    /**
     * Compute the inverse of the composition of the endomorphisms.
     * Note that: inv(f(g(x))) = inv(g)(inv(f)(x))
     * @return The inverse endomorphism.
     */
    public IEndomorphism<T> inverse() {
        List<IEndomorphism<T>> invMorphisms = new ArrayList<IEndomorphism<T>>();
        List<IEndomorphism<T>> revMorphisms = new ArrayList<IEndomorphism<T>>(morphisms);

        Collections.reverse(revMorphisms);

        for (IEndomorphism<T> tr : revMorphisms) {
            invMorphisms.add(tr.inverse());
        }

        return new MorphismComposition<T>(invMorphisms);
    }

    public T map(T p) {
        for (IEndomorphism<T> tr : morphisms) {
            p = tr.map(p);
        }

        return p;
    }
}