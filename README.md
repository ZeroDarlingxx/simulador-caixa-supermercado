# simulador-caixa-supermercado

Simulador estocástico


## 1. Objetivo
Desenvolver um simulador estocástico para estimar o tempo médio de atendimento em caixas de supermercado, considerando variabilidade no tempo de cada cliente.


## 2. Parametros do simulador

- Média (mu):5.0 minutos
- Desvio-padrão (sigma): 0.25, 0.5, 1.0, 2.0
- Número de clientes: 50, 100, 200
- Número de caixas: 1, 2, 3
- Número de simulações: 1000 por cenário

O simulador calcula a média e o desvio-padrão do tempo de atendimento por caixa.

Os cenários podem ser comparados para identificar os melhores tempos médios e os mais estáveis


## 3. Por que é estocástico
   
O simulador é estocástico porque recebe valores de tempo de atendimento aleatoriamente a partir da distribuição normal, então cada resultado vai ser ligeiramente diferente, o que, no geral, representa a imprevisibilidade de situações na vida real.


## 4. Observações
- Mais caixas reduzem o tempo médio de atendimento.
- Maior número de clientes diminui a variabilidade das médias observadas.
- Maior sigma aumenta a dispersão dos tempos de atendimento.
