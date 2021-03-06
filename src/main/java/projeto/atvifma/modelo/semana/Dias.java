package projeto.atvifma.modelo.semana;

public enum Dias {

    DOMINGO,
    SEGUNDA,
    TERCA,
    QUARTA,
    QUINTA,
    SEXTA,
    SABADO;

    @Override
    public String toString() {
        switch(this) {
            case DOMINGO: return "Domingo";
            case SEGUNDA: return "Segunda-feira";
            case TERCA: return "Terça-feira";
            case QUARTA: return "Quarta-feira";
            case QUINTA: return "Quinta-feira";
            case SEXTA: return "Sexta-feira";
            case SABADO: return "Sábado";
        }
        return "";
    }
}
