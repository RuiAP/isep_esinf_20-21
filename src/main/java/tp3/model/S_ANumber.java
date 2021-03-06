package tp3.model;

import java.util.Objects;

public class S_ANumber implements Comparable<S_ANumber>,tagSearchable {

    private PTElement pte;

    public S_ANumber (int atomicNumber){
        this.pte = new PTElement(atomicNumber);
    }

    public S_ANumber(PTElement pte){
        this.pte = pte;
    }


    private int getAtomicNumber(){
        return pte.getAtomicNumber();
    }

    public PTElement getPTElement() {
        return pte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        S_ANumber that = (S_ANumber) o;
        return Objects.equals(this.getAtomicNumber(), that.getAtomicNumber());
    }


    @Override
    public int compareTo(S_ANumber o) {
        return this.getAtomicNumber() - o.getAtomicNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(pte);
    }

    @Override
    public String toString() {
        return "S_ANumber{" +
                "atomicNumber=" + this.getAtomicNumber() +
                '}';
    }
}

