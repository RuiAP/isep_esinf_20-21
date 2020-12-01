package TP2.model;

public class Constantes {


    public static final String S_USERS_PATH = "src/main/resources/small-network/susers.txt";
    public static final String S_RELATIONSHIP_PATH = "src/main/resources/small-network/srelationships.txt";

    public static final String BIG_USERS_PATH = "src/main/resources/big-network/busers.txt";
    public static final String BIG_RELATIONSHIP_PATH = "src/main/resources/big-network/brelationships.txt";

    public static final String S_COUNTRIES_PATH = "src/main/resources/small-network/scountries.txt";
    public static final String S_BORDERS_PATH = "src/main/resources/small-network/sborders.txt";

    public static final String BIG_COUNTRIES_PATH = "src/main/resources/big-network/bcountries.txt";
    public static final String BIG_BORDERS_PATH = "src/main/resources/big-network/bborders.txt";





    public static final String TEXTO_FUNCIONALIDADE1 =
            "Construir os grafos a partir da informação fornecida nos ficheiros de texto.";

    public static final String TEXTO_FUNCIONALIDADE2 =
            "Devolver os amigos comuns entre os n utilizadores mais populares da rede.";

    public static  final String TEXTO_FUNCIONALIDADE3 =
            "Verificar se a rede de amizades é conectada e em caso positivo devolver o número mínimo de ligações "
            + "necessário para nesta rede qualquer utilizador conseguir contactar um qualquer outro utilizador.";

    public static  final String TEXTO_FUNCIONALIDADE4 =
            "Devolver para um utilizador os amigos que se encontrem nas proximidades, isto é, amigos que habitem em cidades "
            + "que distam um dado número de fronteiras da cidade desse utilizador. Devolver para cada cidade os respetivos amigos.";

    public static  final String TEXTO_FUNCIONALIDADE5 =
            "Devolver as n cidades com maior centralidade ou seja, as cidades que em média estão mais próximas de "
            + "todas as outras cidades e onde habitem pelo menos p% dos utilizadores da rede de amizades, onde p% é a "
            + "percentagem relativa de utilizadores em cada cidade.";
    public static  final String TEXTO_FUNCIONALIDADE6 =
            "Devolver o caminho terrestre mais curto entre dois utilizadores, passando obrigatoriamente pelas "
            + "cidade(s) intermédias onde cada utilizador tenha o maior número de amigos.";


}
