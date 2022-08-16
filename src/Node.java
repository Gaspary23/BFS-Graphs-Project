public class Node {
    String simbolo;
    int x, y;
    boolean marcado;

    public Node(int i, int j, String s) {
        x = i;
        y = j;
        simbolo = s;
        marcado = false;
    }

    public int getI() {
        return x;
    }

    public int getJ() {
        return y;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public boolean isMarcado() {
        return marcado;
    }

    public void marca() {
        marcado = true;
    }

    public void desmarca() {
        marcado = false;
    }

    public String toString() {
        return "Simbolo: "+getSimbolo()+"\nPosicao: ("+getI()+", "+getJ()+")"+"Está marcado: "+isMarcado();
    }
}
