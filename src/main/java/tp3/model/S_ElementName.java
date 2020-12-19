package tp3.model;

import java.util.Objects;

public class S_ElementName implements Comparable<S_ElementName> {

    private PTElement pte;

    public S_ElementName(PTElement pte){
        this.pte = pte;
    }


    private String getElementName(){
        return pte.getElementName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        S_ElementName that = (S_ElementName) o;
        return Objects.equals(this.getElementName(), that.getElementName());
    }


    @Override
    public int compareTo(S_ElementName o) {
        return this.getElementName().compareTo(o.getElementName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(pte);
    }
}

