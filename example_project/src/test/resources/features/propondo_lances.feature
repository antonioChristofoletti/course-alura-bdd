# language: pt

Funcionalidade: Propondo Lances ao leilao

 Cenario: Propondo um unico lance válido
  Dado um lance de 10.6 reais do usuario "Cleber"
  Quando propoe varios lances ao leilao
  Entao Apenas os lances validos sao aceitos

 Esquema do Cenário: Propondo varios lances validos
  Dado um lance de <valor> reais do usuario <usuario>
  Quando propoe varios lances ao leilao
  Entao Apenas os lances validos sao aceitos

  Exemplos:
   | valor | usuario    |
   | 10.0  | "fulano"   |
   | 15.0  | "beltrano" |

 Esquema do Cenario: Propondo um lance invalido
  Dado um lance invalido de <valor> reais do usuario <usuario>
  Quando propoe varios lances ao leilao
  Entao Apenas os lances validos sao aceitos

  Exemplos:
   | valor  | usuario |
   | -1.0   | "user1" |
   | -100.0 | "user2" |
   | 0.0    | "user3" |

 Cenario: Propondo uma sequencia de lances
  Dado dois lances
   | valor | nomeUsuario |
   | 10.0  | beltrano    |
   | 15.0  | fulano      |
  Quando propoe varios lances ao leilao
  Entao Apenas os lances validos sao aceitos