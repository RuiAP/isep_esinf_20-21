package tp3.model;

import java.util.Objects;

public class S_Symbol implements Comparable<S_Symbol>,tagSearchable {

    private PTElement pte;

    public S_Symbol(String symbol_str){
        this.pte = new PTElement(symbol_str, true);
    }

    public S_Symbol(PTElement pte){
        this.pte = pte;
    }


    private String getSymbol(){
        return pte.getSymbol();
    }

    public PTElement getPTElement() {
        return pte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        S_Symbol that = (S_Symbol) o;
        return Objects.equals(this.getSymbol(), that.getSymbol());
    }

    @Override
    public int compareTo(S_Symbol o) {
       return this.getSymbol().compareTo(o.getSymbol());
    }

    @Override
    public int hashCode() {
        return Objects.hash(pte);
    }

    @Override
    public String toString() {
        return "S_Symbol{" +
                "Symbol=" + this.getSymbol() +
                '}';
    }
}

