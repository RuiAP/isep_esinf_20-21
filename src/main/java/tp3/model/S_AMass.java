package tp3.model;

import java.util.Objects;

public class S_AMass  implements Comparable<S_AMass> {

    private PTElement pte;

    public S_AMass(PTElement pte){
        this.pte = pte;
    }


    private double getAtomicMass(){
        return pte.getAtomicMass();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        S_AMass that = (S_AMass) o;
        return Objects.equals(this.getAtomicMass(), that.getAtomicMass());
    }

    @Override
    public int compareTo(S_AMass o) {
        if (this.getAtomicMass() - o.getAtomicMass() >0){
            return 1;
        }else if (this.getAtomicMass() - o.getAtomicMass() <0){
            return -1;
        }else
            return 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pte);
    }

}

