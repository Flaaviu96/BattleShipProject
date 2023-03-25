

public class Coordinates {
    private int A;
    private int B;
    private boolean checked = false;

    Coordinates(int A,int B) throws IllegalAccessException {
        setA(A);
        setB(B);
    }
    void setA(int A) throws IllegalAccessException {
        if(!(A >= 0 && A < 10)) {
            throw new IllegalAccessException("Error! You entered the wrong coordinates!");
        }
        else {
            this.A = A;
        }
    }
    void setB(int B) throws IllegalAccessException {
        if(!(B >= 0 && B < 10)) {
            throw new IllegalAccessException("Error! You entered the wrong coordinates!");
        }
        else {
            this.B = B;
        }
    }
    void setChecked(boolean checked) {
        this.checked = checked;
    }
    boolean getChecked() {
        return checked;
    }
    int getA(){
        return A;
    }
    int getB(){
        return B;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Coordinates)) {
            return false;
        }

        Coordinates other = (Coordinates) obj;
        return this.A == other.A && this.B == other.B;
    }
}
