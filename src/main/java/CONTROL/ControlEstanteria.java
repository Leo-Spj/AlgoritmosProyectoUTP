package CONTROL;


import MODELO.Libro;

public class ControlEstanteria<L extends Libro>  {

    private Libro listaLibros[] = new Libro[1] ;

    public L obtenerUnLibro(long isbn){
        for(Libro libro : listaLibros){
            if(libro.getISBN() == isbn){
                return (L) libro;
            }
        }
        return null;
    }

    public Libro[] getListaLibros() { //obtener libros en modo array de objetos, usar para mostrar en JTable
        return listaLibros;
    }

    public void listarLibros(){ // Este listado es para mostrar en consola, no usar, solo pruebas
        for(Libro libro : listaLibros){
            System.out.println(libro.getTitulo());
        }
    }

    public void agregarLibro(L libro){
        if(listaLibros[listaLibros.length-1] == null){
            listaLibros[listaLibros.length-1] = libro;
        } else {
            Libro listaLibrosAux[] = new Libro[listaLibros.length + 1];
            for (int i = 0; i < listaLibros.length; i++) {
                listaLibrosAux[i] = listaLibros[i];
            }
            listaLibros = listaLibrosAux;
            listaLibros[listaLibros.length - 1] = libro;
        }
    }

    public void removerLibro(L libro){
        for(int i = 0; i < listaLibros.length; i++){
            if(listaLibros[i] == libro){
                listaLibros[i] = null;

                Libro AuxIzquierda[] = new Libro[i];
                Libro AuxDerecha[] = new Libro[listaLibros.length - i - 1];

                for(int j = 0; j < AuxIzquierda.length; j++){
                    AuxIzquierda[j] = listaLibros[j];
                }
                for(int j = 0; j < AuxDerecha.length; j++){
                    AuxDerecha[j] = listaLibros[j + i + 1];
                }

                listaLibros = new Libro[listaLibros.length - 1];

                for(int j = 0; j < AuxIzquierda.length; j++){
                    listaLibros[j] = AuxIzquierda[j];
                }
                for(int j = 0; j < AuxDerecha.length; j++){
                    listaLibros[j + AuxIzquierda.length] = AuxDerecha[j];
                }

                break;
            }
        }
    }


    // ------------------ Métodos de ordenamiento : ------------------

    public void deBurbuja(String atributo) {
        //se ordenana por anioPublicacion:
        for (int i = 0; i < listaLibros.length; i++){
            for (int j = 0; j < listaLibros.length-1; j++){
                if (atributo.equals("anioPublicacion")){
                    if (listaLibros[j].getAnioPublicacion() > listaLibros[j+1].getAnioPublicacion()){
                        Libro aux = listaLibros[j];
                        listaLibros[j] = listaLibros[j+1];
                        listaLibros[j+1] = aux;
                    }
                } else if (atributo.equals("ISBN")){
                    if (listaLibros[j].getISBN() > listaLibros[j+1].getISBN()){
                        Libro aux = listaLibros[j];
                        listaLibros[j] = listaLibros[j+1];
                        listaLibros[j+1] = aux;
                    }
                }
                if (atributo.equals("titulo")){
                    if (listaLibros[j].getTitulo().compareTo(listaLibros[j+1].getTitulo()) > 0){
                        Libro aux = listaLibros[j];
                        listaLibros[j] = listaLibros[j+1];
                        listaLibros[j+1] = aux;
                    }
                } else if (atributo.equals("autor")){
                    if (listaLibros[j].getAutor().compareTo(listaLibros[j+1].getAutor()) > 0){
                        Libro aux = listaLibros[j];
                        listaLibros[j] = listaLibros[j+1];
                        listaLibros[j+1] = aux;
                    }
                }
            }
        }
    }

    public void porSeleccion_String(String atributo){
        int aux;
        int indice;
        for (int i = 0; i < listaLibros.length; i++){
            indice = i;
            String pequeño = "";
            if (atributo.equals("titulo")){
                pequeño = listaLibros[i].getTitulo();
            } else if (atributo.equals("autor")) {
                pequeño = listaLibros[i].getAutor();
            }
            for (int j = i; j < listaLibros.length; j++){
                if (atributo.equals("titulo")){
                    if (listaLibros[j].getTitulo().compareTo(pequeño) < 0){
                        pequeño = listaLibros[j].getTitulo();
                        indice = j;
                    }
                } else if (atributo.equals("autor")) {
                    if (listaLibros[j].getAutor().compareTo(pequeño) < 0) {
                        pequeño = listaLibros[j].getAutor();
                        indice = j;
                    }
                }
            }
            Libro auxLibro = listaLibros[i];
            listaLibros[i] = listaLibros[indice];
            listaLibros[indice] = auxLibro;
        }
    }


    // ------------------ Métodos de búsqueda : ------------------

    public int busquedaOrdenada(int array[], int valor){

        //Busqueda secuencial
        int indMayor = array.length-1;
        int ind = 0;
        while(ind<indMayor && valor != array[ind]) {
            ind++;
        }
        if(valor == array[ind]){
            return ind;
        }else{
            return -1;
        }
    }

    public Libro[] busquedaSecuencial(String atributo, String buscar){
        Libro[] resultados = new Libro[0];
        for (int i = 0; i < listaLibros.length; i++){
            if (atributo.equals("Titulo")){
                if (listaLibros[i].getTitulo().toLowerCase().indexOf(buscar.toLowerCase()) != -1){
                    Libro[] aux = new Libro[resultados.length + 1];
                    for (int j = 0; j < resultados.length; j++){
                        aux[j] = resultados[j];
                    }
                    aux[resultados.length] = listaLibros[i];
                    resultados = aux;
                }
            } else if (atributo.equals("ISBN")){
                if (String.valueOf(listaLibros[i].getISBN()).indexOf(buscar) != -1){
                    Libro[] aux = new Libro[resultados.length + 1];
                    for (int j = 0; j < resultados.length; j++){
                        aux[j] = resultados[j];
                    }
                    aux[resultados.length] = listaLibros[i];
                    resultados = aux;
                }
            } else if (atributo.equals("Autor")){
                if (listaLibros[i].getAutor().toLowerCase().indexOf(buscar.toLowerCase()) != -1){
                    Libro[] aux = new Libro[resultados.length + 1];
                    for (int j = 0; j < resultados.length; j++){
                        aux[j] = resultados[j];
                    }
                    aux[resultados.length] = listaLibros[i];
                    resultados = aux;
                }
            }
        }
        return resultados;
    }
}
