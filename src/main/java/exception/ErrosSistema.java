package exception;

public class ErrosSistema {
    public static class AgenciaNaoEncontradaException extends RuntimeException {
        final static String MESSAGE = "Agência %s NÃO ENCONTRADA.";

        public AgenciaNaoEncontradaException(String cnpj) {
            super(String.format(MESSAGE, cnpj));
        }
    }

    public static class AgenciaInativaException extends RuntimeException {
        final static String MESSAGE = "Agência %s INATIVA.";

        public AgenciaInativaException(String cnpj) {
            super(String.format(MESSAGE, cnpj));
        }
    }
}
