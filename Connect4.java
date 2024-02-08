public class Connect4 {
    int turno = 1;
    int[][] tablero = new int[][]{
        {0, 0, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 1},
        {0, 0, 0, 0, 1, 1, 1},
        {0, 0, 0, 0, 0, 1, 1}};

    public void play(int columna) {
        ImpJuego();
    }

    public void ImpJuego() {
        for (int i=0; i<=tablero.length-1; i++) {
            for (int j=0; j<=tablero[0].length-1; j++) {
                System.out.print(tablero[i][j]);
                if (j==6) { // si llega al final de la fila
                    System.out.println(" "); // nueva linea
                }
            }
        }
    }

    public void ColocarPieza(int fila) {
        if (fila>=0 && fila<=tablero[0].length-1) { // al ser lista empiezo desde el 0 hasta el 5 => 1-6
            for (int i=tablero.length-1; i>=0; i--) { // voy iterando desde abajo de cada fila hasta arriba
                if (tablero[i][fila] == 0) {
                    tablero[i][fila] = turno; // pongo ficha del jugador que tiene turno
                    break;
                }
            }
        }
    }

    public boolean ComprobarHorizontal() {
        int jugador_anterior=0, contador_seg=0;
        for (int i=0; i<=tablero.length-1; i++) {
            for (int j=0; j<=tablero[0].length-1; j++) {
                if (tablero[i][j]==jugador_anterior && tablero[i][j]!=0) {
                    contador_seg+=1;
                    if (contador_seg==4) {
                        return true;
                    }

                    if (j==tablero[0].length-1) {
                        contador_seg=0;
                        jugador_anterior=0;
                    }
                } else {
                    jugador_anterior=tablero[i][j];
                    contador_seg=1;
                }
            }
        }

        return false;
    }

    public boolean ComprobarVertical() {
        int jugador_anterior=0, contador_seg=0;
        for (int i=0; i<=tablero[0].length-1; i++) {
            for (int col=0; col<=tablero.length-1; col++) { // Itero sobre cada columna de cada fila
                if (tablero[col][i]==jugador_anterior && tablero[col][i]!=0) {
                    contador_seg+=1;
                    if (contador_seg==4) {
                        return true;
                    }

                    if (col==tablero.length-1) { // al llegar final de columna reseteo contador
                        contador_seg=0;
                        jugador_anterior=0;
                    }
                } else {
                    jugador_anterior=tablero[col][i];
                    contador_seg=1;
                }
            }
        }

        return false;
    }

    public boolean ComprobarDiagonales() {
        return false;
    }

    public boolean TableroLleno() {
        for (int i=0; i<=tablero.length-1; i++) {
            for (int j=0; j<=tablero[0].length-1; j++) {
                if (tablero[i][j]==0) { // si me encuentro un 0 se que no esta lleno!
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Connect4 j1 = new Connect4();
        // j1.ImpJuego();
        // j1.ColocarPieza(0);
        // j1.ColocarPieza(0);

        j1.ImpJuego();
        System.out.println(j1.ComprobarVertical());
    }
}
