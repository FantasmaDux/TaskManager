## Порядок настройки keycloak
1. Задаем нужный параметры в app.yml
```
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: http://keycloak:8080/realms/task-manager # кто выпустил токен
          jwk-set-uri: http://keycloak:8080/realms/task-manager/protocol/openid-connect/certs # публичные ключи Keycloak

keycloak:
  auth-server-url: http://keycloak:8080 # Адрес Keycloak сервера.
  realm: task-manager # realm, где создаются пользователи, проверяются токены
  resource: task-manager-client # Это clientId. backend логинится как client_id = task-manager-client
  credentials:
    secret: yNEGVJRDryuRfgz5pfwfmPd1AeZSnsB7 # Backend получает токен с помощью секрета

```
2. Запускаем только keycloak. Узнаем secret из clients -> task-manager-client -> Credentials
3. В kyecloak в clients нужно выбрать task-manager-client
4. Назначаем роли (assign)
5. Для разрешения бэкенду работать с пользователями надо filter by clients -> manage-users, view-users, query-users
6. В сервисе keycloak в бэкенде нужно с указанными в yml параметрами получить клиент
7. При получении ошибок:

    7.1. Проверяем, чтобы секреты совпадали

    7.2. Проверяем, чтобы Client authentication было on, чтобы клиент был confidential (разрешит бэкенду получать токен)
    
    7.3. Проверяем, чтобы были выбраны Service Account Roles в settings
8. Запускаем бэкенд

## Экспорт и импорт
Экспорт выполняется:

realm settings -> action -> partial export

Импорт выполняется:

сохранение в папке проекта и монтирование папки с json файлом в докер компоуз в /opt/keycloak/data/import (нужная keycloak папка по умолчанию) 