public class Connect4 {
  
    boolean juego_en_cur = true;
    int turno = 1;
    int[][] tablero = new int[][]{
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0}};

    public String play(int columna) {
        if (juego_en_cur==false) {
            return "Game has finished!";
        }

        boolean result_col = ColocarPieza(columna);
        if (result_col==false) {
            return "Column full!";
        }

        // Compruebo si hay ganadores
        if (ComprobarHorizontal()==true || ComprobarVertical()==true  || ComprobarDiagonales(turno)==true) {
            juego_en_cur=false;
            return "Player "+turno+" wins!";
        } else if(TableroLleno()==true) {
            juego_en_cur=false;
        } else {
            int t_ant = turno;
            turno = (turno==1) ? 2 : 1; // cambio de turno
            return "Player "+t_ant+" has a turn";
        }

        return "-1";
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

    public boolean ColocarPieza(int fila) {
        if (fila>=0 && fila<=tablero[0].length-1) { // al ser lista empiezo desde el 0 hasta el 5 => 1-6
            for (int i=tablero.length-1; i>=0; i--) { // voy iterando desde abajo de cada fila hasta arriba
                if (tablero[i][fila] == 0) {
                    tablero[i][fila] = turno; // pongo ficha del jugador que tiene turno
                    return true; // todo correcto
                }
            }
        }
        return false; // columna llena!
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
                } else {
                    jugador_anterior=tablero[i][j];
                    contador_seg=1;
                }
            }
          jugador_anterior = 0;
          contador_seg = 0;
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
                } else {
                    jugador_anterior=tablero[col][i];
                    contador_seg=1;
                }
            }
          jugador_anterior = 0;
          contador_seg = 0;
        }

        return false;
    }

    public boolean ComprobarDiagonales(int tipo_jugador) {
        int cont_seg_der_ab=0, cont_seg_izq_arr=0, cont_seg_der_arr=0, cont_seg_izq_ab=0;
        for (int i=0; i<=tablero.length-1; i++) {
            for (int j=0; j<=tablero[0].length-1; j++) {
                if (tablero[i][j] == tipo_jugador) {
                    cont_seg_der_ab=0;
                    cont_seg_izq_arr=0;
                    cont_seg_der_arr=0;
                    cont_seg_izq_ab=0;

                    for (int k=1; k<=3; k++) { 
                        // De centro der abajo
                        if (!(i+3>tablero.length-1 || j+3>tablero[0].length-1)) {
                            if (tablero[i+k][j+k] != tipo_jugador) {
                                cont_seg_der_ab=0; // reseteo contador
                            } else {
                                cont_seg_der_ab+=1;
                            }

                            if (cont_seg_der_ab==3) {
                                return true;
                            }
                        }

                        // De centro izq arr
                        if (!(i-3<0 || j-3<0)) {
                            if (tablero[i-k][j-k] != tipo_jugador) {
                                cont_seg_izq_arr=0; // reseteo contador
                            } else {
                                cont_seg_izq_arr+=1;
                            }


                            if (cont_seg_izq_arr==3) {
                                return true;
                            }
                        }

                        // De centro der arr
                        if (!(i-3<0 || j+3>tablero[0].length-1)) {
                            if (tablero[i-k][j+k] != tipo_jugador) {
                                cont_seg_der_arr=0; // reseteo contador
                            } else {
                                cont_seg_der_arr+=1;
                            }

                            if (cont_seg_der_arr==3) {
                                return true;
                            }
                        }

                        // De centro izq abajo
                        if (!(i+3>tablero.length-1 || j-3<0)) {
                            if (tablero[i+k][j-k] != tipo_jugador) {
                                cont_seg_izq_ab=0; // reseteo contador
                            } else {
                                cont_seg_izq_ab+=1;
                            }

                            if (cont_seg_izq_ab==3) {
                                return true;
                            }
                        }
                    }
                    


                }
            }
        }

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
        ;
    }
}