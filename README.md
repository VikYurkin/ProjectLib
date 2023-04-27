# ProjectLib
Catalog of library

Technology: Maven, Spring MVC, Spring Core, JDBC Template, Thymeleaf, PostgreSQL.

This web apps is a library catalog. The database stores data about people and books.

Для запуска через docker compose:
1) Создать файл  docker compose.yml:


```sh
version: '3.7'

services:
  lb:
    depends_on:
      - postgres
    image: vyurkin/projectlib
    environment:
      DB_USERNAME: postgres
      DB_PASSWORD: 9876543219
      DB_URL: jdbc:postgresql://postgres:5432/lbDB
    restart: always
    ports:
      - 8083:8080

  postgres:
    image: postgres:15.2
    restart: always
    environment:
      POSTGRES_PASSWORD: 9876543219
      POSTGRES_USER: postgres
      POSTGRES_DB: lbDB
    ports:
      - 5433:5432
```

2) Запустить сборку образа командой:

 docker compose -p "project-lib" -f "docker-compose.yml" up -d.
 
если образы vyurkin/projectlib и postgres:15.2 отсутствуют, они будут скачаны с DockerHub.


Функционал:
1)	По адресу http://localhost:8083/projectlib/people доступен перечень пользователей библиотеки, главная страница пользователей библиотеки (для примера добавлено несколько пользователей миграцией).
Ссылки на пользователей активные, при выборе переводят на страницу пользователя http://localhost:8083/projectlib/people/1, также на главной странице присутствует активная ссылка на добавление нового пользователя http://localhost:8083/projectlib/people/new.

2)	На странице пользователя приведена информация о пользователе и  список взятых им книг.
Также на странице пользователя активные ссылки на редактирование информации о пользователе(http://localhost:8083/projectlib/people/1/edit) и удаление пользователя.

3)	По адресу http://localhost:8083/projectlib/books доступен перечень книг библиотеки, главная страница книг библиотеки (для примера добавлено несколько книг миграцией).
Ссылки на главной странице книг активные, при выборе переводят на страницу книги http://localhost:8083/projectlib/books/1, также на главной странице книги присутствует активная ссылка  на добавление новой книги и страницы поиска книги (http://localhost:8083/projectlib/books/search).

4)	На странице книги приведена информация о книге и кто ее взял, если книга свободна, то из выпадающего списка можно назначить пользователя, который ее возьмет, если книга уже взята, то ее можно освободить при возврате книги.
Также на странице книги присутствуют активные ссылки на редактирование информации о книге (http://localhost:8083/projectlib/books/1/edit) и ее удаление.

5)	На странице поиска книги осуществляется поиск по первым буквам названия книги, при наличии книги с такими буквами выдается книга и кто ее взял, в противном случае выдается сообщение, что книг не найдено. 

