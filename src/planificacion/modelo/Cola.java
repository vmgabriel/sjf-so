package planificacion.modelo;

public class Cola {

    private Nodo ultimo;
    private Nodo primero;
    private int tamano;

    public Cola() {
        tamano = 0;
        primero = new Nodo();
        primero.setNombreProceso("SFJ");
        primero.setSig(ultimo);
        ultimo = primero;
        ultimo.setSig(primero);
    }

    /**
     * Implementacion del algortinmo *
     */
    public String insertar(int id, String nombre, int tLlegada, int tRafaga) {
        String msg="";
        
        Nodo q = new Nodo();
        q.setIdProceso(id);
        q.setNombreProceso(nombre);
        q.settLlegada(tLlegada);
        q.settRafaga(tRafaga);
        
        this.ultimo.setSig(q);
        q.setSig(this.primero);
        this.ultimo = q;

//        // forzamos para que empiece el primero de una 
//        if (ultimo.gettFinal() == 0) {
//            q.settComienzo(tLlegada);
//        } else {
//            q.settComienzo(ultimo.gettFinal());
//        }
//
//        q.settFinal(tRafaga + q.gettComienzo());
//        q.settRetorno(q.gettFinal() - q.gettLlegada());
//        q.settEspera(q.gettRetorno() - q.gettRafaga());
//
//        //Forzando no datos negativos 
//        if (q.gettEspera() < 0) {
//            q.settEspera(0);
//        }
//
//        q.setSig(primero);
//
//        primero.settFinal(q.gettFinal());
//
//        ultimo.setSig(q);
//        ultimo = q;

        tamano++;
        return msg;
    }
    
    public void insertar(Nodo nodo) {
        this.ultimo.setSig(nodo);
        nodo.setSig(this.primero);
        this.ultimo = nodo;
    }

    public Nodo retirar() {
        Nodo aux = primero.getSig();
        if (aux != primero) {
            primero.setSig(aux.getSig());
            if (aux == ultimo) {
                ultimo = aux.getSig();
            }
            aux.setSig(null);
            tamano--;
        }
        return aux;

        /*if(aux==p){
            return aux;
        }
        if(aux!=cab){  
            p.setSig(aux.getSig());
            aux.setSig(null);
            return aux;
        }
        else{
            cab=aux.getSig();
        }*/
    }
    
    public Nodo retirarRafagaCorta() {
        Nodo pAnt = this.primero;
        Nodo ant = this.primero;
        Nodo temp = this.primero.getSig();
        Nodo intervalo = this.primero.getSig();
        while(intervalo != this.primero) {
            if (temp.gettRafaga() > intervalo.gettRafaga()) {
                temp = intervalo;
                pAnt = ant;
            }
            ant = intervalo;
            intervalo = intervalo.getSig();
        }
        pAnt.setSig(temp.getSig());
        tamano--;
        return temp;
    }
    
    public void organizarByRafaga() {
        Nodo val = this.primero;
        int tam = this.tamano;
        for(int i=0;i<tam;i++){
            val = retirarRafagaCorta();
            insertar(val);
        }
    }

    public boolean isVacia() {
        return this.primero == this.ultimo;
    }

    public Nodo getPrimero() {
        return primero;
    }
    
    public Nodo getRafagaCorta() {
        Nodo temp = this.primero;
        Nodo intervalo = this.primero;
        while(intervalo != this.ultimo) {
            if (temp.gettRafaga() > intervalo.gettRafaga()) {
                temp = intervalo;
            }
            intervalo = intervalo.getSig();
        }
        return temp;
    }

    public int getTamano() {
        return tamano;
    }

}
