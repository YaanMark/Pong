# 🏓 Pong

Um clone simples do jogo **Pong**, desenvolvido em **Java**, como projeto do IntelliJ IDEA.

Este projeto recria o clássico jogo de arcade da Atari de 1972, permitindo que dois jogadores disputem uma partida usando raquetes para rebater uma bola na tela.

---

## 📋 Funcionalidades



✅ Movimentação das raquetes e detecção de colisão

✅ Física e quicagem da bola

✅ Contagem de pontos

✅ Janela de jogo simples e responsiva

✅ Arquitetura orientada a objetos

---

## 🛠️ Tecnologias Utilizadas


### Linguagem


- Java

### Gráficos


- Java Swing / AWT


---

## 📂 Estrutura do Projeto


```
PONG
│
├── .idea                  # Configurações do projeto no IntelliJ IDEA
│
├── src                     # Código-fonte Java (lógica do jogo, entidades, renderização)
│
├── .gitignore
└── Game01.iml              # Arquivo de módulo do IntelliJ
```

---

## ⚙️ Instalação


### 1. Clone o repositório


```
git clone https://github.com/YaanMark/Pong.git
```

### 2. Abra o projeto


Abra a pasta do projeto no **IntelliJ IDEA** (ou em qualquer IDE compatível com Java).

### 3. Compile o projeto


Certifique-se de que um **JDK (Java Development Kit)** esteja configurado na sua IDE e então compile o projeto.

---

## 🚀 Executando a Aplicação


### Executar pela IDE


Abra a classe principal localizada na pasta `src` e execute-a diretamente pelo IntelliJ IDEA.

### Executar pelo terminal


```
javac -d out src/*.java
java -cp out Main
```

> ⚠️ Ajuste o nome da classe acima de acordo com a classe principal real dentro da pasta `src`.

---

## 🎮 Controles


| Jogador   | Mover para Esquerda | Mover para Direita |
| --------- | ---------------- | ------------------ |
| Jogador 1 | A               | D                   |

> Os controles podem variar de acordo com a implementação dentro da pasta `src`.

---

## 💻 Jogabilidade


O jogo oferece uma experiência simples e intuitiva de Pong, onde os jogadores podem:

- Mover suas raquetes de um lado para outro
- Rebater a bola para o adversário
- Marcar pontos quando o adversário erra a bola

---

## 🎯 Objetivos de Aprendizado


Este projeto demonstra:

- Programação Orientada a Objetos (POO) em Java
- Implementação de game loop
- Detecção de colisão em 2D
- Renderização gráfica com Java Swing / AWT
- Tratamento de eventos (entrada do teclado)
- Física básica de jogos (velocidade, quicagem)

---

## 📸 Melhorias Futuras


- Níveis de dificuldade
- Tela de  Pausa
- Cores personalizáveis para raquetes e bola
- Persistência de pontuação

---

## 📄 Licença


Este projeto está licenciado sob a Licença MIT.

---

## 👨‍💻 YaanMark


Desenvolvido como uma recriação do clássico jogo de arcade Pong utilizando Java.

⭐ Se você achou este projeto útil, considere dar uma estrela!
