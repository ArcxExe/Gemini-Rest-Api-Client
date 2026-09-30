# 🤖 Gemini Chat Proxy API 

RESTful веб-сервис на **Spring Boot 3**, выступающий в роли прокси к Google Gemini API. 

---

## 🚀 Основной стек технологий

- **Язык**: Java 21+
- **Фреймворк**: Spring Boot 3 (Spring Web, Spring Data JPA, Spring Validation)

##  Запуск

Для использования нужно создать в корневой папке `.env`и указать в нем в Api key

```text
MY_API={API_KEY}
```

После можно выполнить , для запуска проекта:

```shell
./gradlew bootRun
```

По умолчанию запрос идет к gemini-2.5-flash-lite. Но можно изменить в `application.yml`
В секции `gemini.model` = на любую другую модель. После запуска будет ответ какая сегодня дата.

## Использование

Из контекста получить Bean - GeminiService и вызвать метод ask - и как раз в него передать текст для модели 

