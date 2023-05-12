## RECUPERAÇÃO UC12

# - INSTALAÇÃO DO LINUX UBUNTU

# Primeiramente instalei a VM do UBUNTU no VirtualBox, ao término instalei o cockpit pelo terminal do UBUNTU e apontei as portas dentro da
# VirtualBox para acessar o cockpit em um navegador colocando o seguinte endereço: http://127.0.0.1:9090. IP automático do Linux é: “127.0.0.1”. A PORTA pode ser qualquer uma, mas por questão de organização coloquei “9090” e no IP do convidado SEMPRE coloca “10.0.2.15”.

# - INSTALAÇÃO DO DOCKER, DOCKER-COMPOSE, ADMINER E WORDPRESS

# Depois de instalar o DOCKER dentro do cockpit usando o seguinte código: sudo apt install docker, Instalei o DOCKER-COMPOSE com o código: sudo apt install docker-compose.
# Para instalar o ADMINER e o WORDPRESS criei um editor de texto usando VIM com o seguinte código: vim docker-compose.yml.
# Dentro desse editor de texto foi inserido o seguinte código para instalação do ADMINER e o WORDPRESS:

version: '3.1'

services:
   
    db:
        image: mysql:latest
        restart: always
        environment:
            MYSQL_ROOT_PASSWORD: senac@123
            MYSQL_DATABASE: site
        ports:
            - '6556-3306'
        volumes:
            - ~/site-db:/var/lib/mysql
        expose:
            - '3306'
           
    wordpress:
        image: wordpress
        restart: always
        environment:
            WORDPRESS_DB_HOST: db
            WORDPRESS_DB_USER: root
            WORDPRESS_DB_PASSWORD: senac@123
            WORDPRESS_DB_NAME: site
            WORDPRESS_TABLE_PREFIX: ps
        ports:
            - '8084:80'
        volumes:
            - ~/site-wordpress:/var/www/html
    adminer:
        image: adminer
        restart: always
        ports:
            - '8085:8080'

# Depois saia com ESC, :wq “wq” para sair e salvar.
# E para executar o código se usa o: docker-compose up

## ATENÇÃO
# Tome muito cuidado com a tabulação no caso o TAB e sempre reveja o código antes de prosseguir para evitar possivéis erros.

# - CONFIGURAÇÃO DO ADMINER E O WORDPRESS

# Depois da instalação do ADMINER e o WORDPRESS, apontei os IPS e as PORTAS dentro do VirtualBox.

# Ubuntu 127.0.0.1 9090 10.0.2.15 9090
# MYSQL_Docker 127.0.0.1 6556 10.0.2.15 6556
# Adminer 127.0.0.1 8085 10.0.2.15 8085
# Wordpress IpDaSuaMáquina ou IpAutomáticoDoLinux 8084 10.0.2.15 8084

# Para acessar o ADMINER em qualquer navegador: http://127.0.0.1:8085.

# Para acessar o WORDPRESS em qualquer navegador: http://10.26.44.36:8084.
# OBS: O ip utilizado é da minha máquina própria, no caso minha máquina real.

# O MYSQL é usado para criar um Banco de Dados para armazenamento e manipulação de dados.

# O ADMINER é uma ferramenta de gerenciamento de Banco de Dados baseados em PHP.

# E o WORDPRESS é uma das ferramentas mais simples para criar e desenvolver um site.

# Camada DAO é onde vamos controlar o que vai ser recebido ou que será enviado.

# Camada POJO é a base.

# Camada VIEW é a configuração do que aparece no site.

# O cenário dado é necessário que o sistema permita cadastrar os seguintes dados como: solicitar o suporte, o chamado do suporte, remoção do suporte, etc.
