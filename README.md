# TRAIN - Sistema de Tradução e Dicionário Multilíngue 🌍📚

O **TRAIN** é uma aplicação de dicionário multilíngue desenvolvida em Java, que permite traduzir palavras entre diferentes idiomas, incluindo inglês, espanhol e alemão, para o português. A interface gráfica foi desenvolvida com **Swing**, proporcionando uma experiência interativa para os usuários.

## Tecnologias Usadas 💻⚙️

- **Java**
- **Swing** (para interface gráfica)
- **CSV** (para armazenamento de traduções)

## Funcionalidades Principais 🚀✨

- **Seleção de Idioma**: Escolha entre os idiomas disponíveis (Inglês, Espanhol, Alemão).
- **Tradução de Palavras**: Traduzir palavras para português e vice-versa.
- **Busca por Palavra**: Pesquisar palavras e obter suas traduções nos idiomas suportados.
- **Interface Gráfica**: Interface amigável utilizando **Swing** para fácil interação.

## Estrutura do Projeto 📂

```
TRAIN/
├── .classpath
├── .git/
├── .idea/
├── .project
├── bin/                    # Arquivos compilados
├── src/                    # Código fonte
│   ├── core/               # Lógica do dicionário
│   │   └── Dicionario.java
│   ├── dados/              # Arquivos de dados (CSV de traduções)
|   |   └── ingles.csv   
│   ├── gui/                # Interface gráfica (Swing)
│   │   └── TelaDicionario.java
│   ├── imagens/            # Imagens (ícones, bandeiras)
│   └── module-info.java
└── README.md               # Este arquivo
```

## Instalação e Execução ⚙️🔧

### Pré-requisitos

- **JDK 8+**: Certifique-se de ter o Java Development Kit (JDK) instalado.

### Passos para rodar a aplicação

1. **Clone o repositório:**

```bash
git clone https://github.com/pimentaluan/TRAIN.git
```

2. **Compile o projeto:**

```bash
cd TRAIN
javac src/**/*.java -d bin/
```

3. **Execute a aplicação:**

```bash
java -cp bin gui.TelaDicionario
```

4. **Acesse a interface gráfica**: A aplicação abrirá uma janela onde você poderá selecionar o idioma e realizar buscas ou traduções.

## Como Funciona 🧠

1. **Dicionário**: O dicionário carrega as traduções a partir de arquivos CSV localizados na pasta `/dados/`. Cada arquivo CSV contém traduções no formato `palavraidioma;palavaportugues`.
   
2. **Tela de Tradução**: A interface permite que o usuário insira uma palavra para tradução, escolha o idioma e veja as traduções correspondentes. O sistema suporta traduções bidimensionais: de um idioma para o português e vice-versa.

3. **Busca de Palavras**: Também é possível buscar palavras diretamente no idioma selecionado ou em português.

## Exemplos de Tradução 📖

- Se você inserir "computer" na caixa de texto e escolher "Inglês", a aplicação fornecerá a tradução "computador" para português ou vice-versa.
- Para buscar uma palavra em português ou no idioma corrente, insira o termo e clique no botão de busca em português para palavras em português ou buscar no idioma, que retornará sugestões de palavras correspondentes.

## Palavras disponíveis 🔠

#### **Português**:
```markdown
computador
teclado
mouse
monitor
impressora
rede
servidor
software
hardware
arquivo
pasta
email
internet
senha
tela
```

#### **Inglês**:
```markdown
computer
keyboard
mouse
monitor
printer
network
server
software
hardware
file
folder
email
internet
password
screen
```

#### **Espanhol**:
```markdown
computadora
teclado
ratón
monitor
impresora
red
servidor
software
hardware
archivo
carpeta
correo
internet
contraseña
pantalla
```

#### **Alemão**:
```markdown
computer
tastatur
maus
monitor
drucker
netzwerk
server
software
hardware
datei
ordner
email
internet
passwort
bildschirm
```
