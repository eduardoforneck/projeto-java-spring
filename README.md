Projeto Java usando Spring Boot.
Utiliza o banco de dados em memória H2 com Spring Data.
O projeto possui três classes: Carro, Modelo e Marca, que estão relacionados da seguinte maneria:
Cada Carro possui um Modelo, cada Modelo possui uma Marca.
Para uma Marca pode haver muitos Modelos, para um Modelo pode haver muitos Carros.

Cada entidade possui um Controller, fornecedendo endpoints de CRUDs.
O Controller de carros, em especial, possui o endpoint "/listarParaVisualizacao", que lista Carros com informações do Modelo para ser consumido no frontend.
