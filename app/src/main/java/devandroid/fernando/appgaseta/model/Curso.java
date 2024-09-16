package devandroid.fernando.appgaseta.model;

public class Curso {

    private String cursoDesejado;

    public Curso(String nomeDoCursoDesejado) {
        this.cursoDesejado = nomeDoCursoDesejado;
    }

    public String getCursoDesejado() {
        return cursoDesejado;
    }

    public void setCursoDesejado(String cursoDesejado) {
        this.cursoDesejado = cursoDesejado;
    }
}
