import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    static class Resultado {
        int id;
        double sigma;
        int caixas;
        int clientes;
        double media;
        double desvioPadrao;

        Resultado(int id, double sigma, int caixas, int clientes, double media, double dp) {
            this.id = id;
            this.sigma = sigma;
            this.caixas = caixas;
            this.clientes = clientes;
            this.media = media;
            this.desvioPadrao = dp;
        }
    }

    public static void main(String... args) {

        final int NUMERO_SIMULACOES = 1000;
        final double MU = 5.0;

        double[] sigmas = {0.25, 0.5, 1.0, 2.0};
        int[] caixas = {1, 2, 3};
        int[] clientes = {50, 100, 200};

        SimulacaoCaixaSupermercado simulador = new SimulacaoCaixaSupermercado();
        List<Resultado> resultados = new ArrayList<>();

        System.out.printf("%-4s %-8s %-6s %-8s %-12s %-12s%n",
                "ID", "Sigma", "Caixas", "Clientes", "Média (min)", "Desvio-padrão");
        System.out.println("---------------------------------------------------------------------");

        int idCounter = 1;

        for (double sigma : sigmas) {
            for (int nCaixas : caixas) {
                for (int nClientes : clientes) {

                    List<Double> mediasAtendimento = new ArrayList<>();

                    for (int i = 0; i < NUMERO_SIMULACOES; i++) {
                        simulador.setNumeroCaixas(nCaixas);
                        simulador.setMediaAtendimentos(nClientes);
                        simulador.setMediaTempoAtendimentoPorCliente(MU);
                        simulador.setDesvioPadraoTempoAtendimentoPorCliente(sigma);

                        double mediaAtendimento = simulador.simular();
                        double mediaPorCaixa = mediaAtendimento / nCaixas;

                        mediasAtendimento.add(mediaPorCaixa);
                    }

                    double media = media(mediasAtendimento);
                    double dp = desvioPadrao(mediasAtendimento, media);

                    Resultado r = new Resultado(idCounter, sigma, nCaixas, nClientes, media, dp);
                    resultados.add(r);

                    System.out.printf("%-4d %-8.2f %-6d %-8d %-12.3f %-12.3f%n",
                            r.id, r.sigma, r.caixas, r.clientes, r.media, r.desvioPadrao);

                    idCounter++;
                }
            }
        }

        //ranking por menor media
        resultados.sort(Comparator.comparingDouble(r -> r.media));
        System.out.println("\n=== Top 5 cenários com menor média de atendimento ===");
        for (int i = 0; i < Math.min(5, resultados.size()); i++) {
            Resultado r = resultados.get(i);
            System.out.printf("ID=%d | sigma=%.2f | Caixas=%d | Clientes=%d | Média=%.3f | DP=%.3f%n",
                    r.id, r.sigma, r.caixas, r.clientes, r.media, r.desvioPadrao);
        }

        //ranking por menor desvio-padrao
        resultados.sort(Comparator.comparingDouble(r -> r.desvioPadrao));
        System.out.println("\n=== Top 5 cenários com menor desvio-padrão (mais estáveis) ===");
        for (int i = 0; i < Math.min(5, resultados.size()); i++) {
            Resultado r = resultados.get(i);
            System.out.printf("ID=%d | sigma=%.2f | Caixas=%d | Clientes=%d | Média=%.3f | DP=%.3f%n",
                    r.id, r.sigma, r.caixas, r.clientes, r.media, r.desvioPadrao);
        }
    }

    private static double media(List<Double> xs) {
        double s = 0.0;
        for (double x : xs) s += x;
        return s / xs.size();
    }

    private static double desvioPadrao(List<Double> xs, double m) {
        double s2 = 0.0;
        for (double x : xs) {
            double d = x - m;
            s2 += d * d;
        }
        return Math.sqrt(s2 / (xs.size() - 1));
    }
}
