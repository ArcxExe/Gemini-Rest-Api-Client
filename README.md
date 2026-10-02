# 🤖 Gemini Chat Proxy API 

RESTful веб-сервис на **Spring Boot 3**, выступающий в роли прокси к Google Gemini API. 

---

## 🚀 Основной стек технологий

- **Язык**: Java 21+
- **Фреймворк**: Spring Boot 3 (Spring Web, Spring Data JPA, Servlet Filter)
- **Docker**

##  Запуск

Для использования можно переименовать `.env.example` в `.env` и заполнить поля. Так же можно изменить время жизни Jwt Token в ms на другое.
По умолчанию стоит 1 день - `expiration-ms: ${JWT_EXPIRATION_MS:86400000}` , что эквивалетно в `.env` - `JWT_EXPIRATION_MS=`

Перед запуском нужно поднять Docker с PostgreSQL. 

```shell
docker-compose up --build   
```
После можно выполнить , для запуска проекта:

```shell
./gradlew bootRun
```

По умолчанию запрос идет к gemini-2.5-flash-lite. Но можно изменить в `application.yml`
В секции `gemini.model` = на любую другую модель. После запуска будет ответ какая сегодня дата.

## Использование

Так как использована авторизация по Jwt токену , то нужно будет прикладывать к каждому запросу в Header
`Authorization - Bearer {JWT_TOKEN}`. После получения токена , нужно создать чат , и после получения UUID , можно задать отправить запрос Gemini для получения ответа. Ниже примеры запросов с помощью Connect Kit

```kotlin
POST("http://localhost:8080/v1/auth/login") {
    header("Content-Type", "application/json")
    body(
        """
        {
            "login": "admin", 
            "password": "password"
        }
        """.trimIndent()
    )
}
```
В ответ получим Jwt токен. Как раз с помощью этого токена мы получили все чаты.

```kotlin
GET("http://localhost:8080/v1/api/chat") {
    header("Authorization" , "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc5MDk2ODk1OSwiZXhwIjoxNzkxMDU1MzU5fQ.gbQwaPMwGcPz_icjkI13XhnQ10qw0IovN0H5uudv5WM")
}
```

Ниже с помощью такого request мы создаем чат с именем `Test`. В ответ от сервера мы получаем `uuid`

```kotlin

POST("http://localhost:8080/v1/api/chat") {
    header("Content-Type", "application/json")
    header("Authorization" , "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc5MDk2ODk1OSwiZXhwIjoxNzkxMDU1MzU5fQ.gbQwaPMwGcPz_icjkI13XhnQ10qw0IovN0H5uudv5WM")
    body(
        """
        {
            "title": "Test"
        }
        """.trimIndent()
    )

}
```
Этот `uuid` мы используем , для того чтобы наш запрос и ответ от Gemini сохранился именно в этом чате. Ниже сам запрос готовый к Gemini

```kotlin
POST("http://localhost:8080/v1/api/chat/{chatId}/messages") {
    pathParam("chatId", "8c127cbf-9cea-4b46-9c3a-ee49167db175")
    header("Content-Type", "application/json")
    header("Authorization" , "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc5MDk2ODk1OSwiZXhwIjoxNzkxMDU1MzU5fQ.gbQwaPMwGcPz_icjkI13XhnQ10qw0IovN0H5uudv5WM")
    body(
        """
        {
            "text": "Привет! Как твои дела ?"
        }
        """.trimIndent()
    )
    
}
```
Готовый ответ от сервера.

![img.png](img.png)


## Как получить Google Ai Studio Api Key

Перейти на сайт Google Ai Studio - https://aistudio.google.com/api-keys. Нажать на Create API Keys

