import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class BFS {
    private long current;
    private Set<Node> players;

    public int caminhamento_largura(List<LinkedList<Node>> graph, Node player) {
        int count = 0;
        List<Node> nodos = new LinkedList<>();
        List<Node> doors = new LinkedList<>();
        Set<String> keys = new HashSet<>();
        nodos.add(player);

        while (!nodos.isEmpty()) {
            Node v = nodos.remove(0);
            v.marca();
            count++;

            for (int t1 = 0, t2 = 3; t1 <= 3; t1++, t2--) {
                int temp1 = t1 < 2 ? (int) Math.pow(-1, t1) : 0;
                int temp2 = t2 < 2 ? (int) Math.pow(-1, t2-2) : 0;
                
                Node w = graph.get(v.getI()+temp1).get(v.getJ()+temp2);
                String simbolo = w.getSimbolo();
                if (!w.isMarcado() && !simbolo.equals("#")) {
                    if (Pattern.matches("[A-Z]", simbolo)) {
                        doors.add(w);
                    } else {
                        if(Pattern.matches("[a-z]", simbolo))
                            keys.add(simbolo);
                        w.marca();
                        nodos.add(w);
                    }
                }
            }
            for (Iterator <?> it = doors.iterator(); it.hasNext();) {
                Node aux = (Node) it.next();
                if (keys.contains(aux.getSimbolo().toLowerCase()) && !aux.isMarcado()) {
                    aux.marca();
                    nodos.add(aux);
                }
            }
        }
        return count; 
    }

    public void caso(String caso) throws IOException {
        current = System.currentTimeMillis();
        players = new HashSet<>();
        List<LinkedList<Node>> graph = new LinkedList<LinkedList<Node>>();
        
        try {
            int i=0,j=0;
            BufferedReader reader = new BufferedReader(new FileReader("../lib/caso"+caso));
            String line = reader.readLine();
            while (line != null) {
                LinkedList<Node> linha = new LinkedList<Node>();
                for (String simbolo : line.split("")) {
                    linha.add(new Node(i, j, simbolo));
                    
                    if (Pattern.matches("[0-9]", simbolo))
                        players.add(linha.get(j));
                        j++;
                }
                graph.add(linha);
                line = reader.readLine();
                i++; j =0;
            }
            reader.close();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        }

        System.out.println("\nCaso: "+caso);
        for (Node player : players) {
            System.out.println("\n Jogador: "+ player.getSimbolo()+ ", Numero de casas percorridas: " + caminhamento_largura(graph, player));
            for (int i = 0; i < graph.size(); i++) {
                for (int j = 0; j < graph.get(i).size(); j++) {
                    graph.get(i).get(j).desmarca();
                }
            }
        }
        System.out.println("\nTempo total: " + (System.currentTimeMillis() - current) + " milissegundos\n\n");
        System.out.println(" ----------------------------------------- ");
    }
    
    public static void main (String [] args) throws IOException {
        BFS casox = new BFS();
        casox.caso(args[0]);
    }
}
