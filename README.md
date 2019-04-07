## Требования
- jdk 1.8
- mssql
## Подготовка
1. Создать базу данных
2. Обновить данные с вашими учетными и портом: `src\main\resources\application.properties`.
## Запуск
```
mvn spring-boot:run
```
зайти на: `http://localhost:8080/`
## Запросы
**Получить все контакты:**
```JavaScript
fetch('/contacts?login=test&password=test', 
    {method: 'GET', headers: {'Content-Type': 'application/json'}}).then(console.log)
```
**Получить контакт по id:**
```JavaScript
fetch('/contacts/11?login=test&password=test', 
    {method: 'GET', headers: {'Content-Type': 'application/json'}}).then(console.log)
```
**Добавить контакт:**
```JavaScript
fetch('/contacts?login=test&password=test', 
    {method: 'POST', headers: {'Content-Type': 'application/json'}, 
    body: JSON.stringify({firstName: "", secondName: "", email: "", phoneNumber: ""})}).then(console.log)
```
**Изменить контакт:**
```JavaScript
fetch('/contacts/11?login=test&password=test', 
    {method: 'PUT', headers: {'Content-Type': 'application/json'}, 
    body: JSON.stringify({firstName: "1", secondName: "2", email: "3", phoneNumber: "4"})}).then(console.log)
```
**Добавить нового пользователя:**
```JavaScript
fetch('/sign-up?login=test1&password=test1', 
    {method: 'POST', headers: {'Content-Type': 'application/json'}}).then(console.log)
```
**Удалить контакт:**
```JavaScript
fetch('/contacts/11?login=test&password=test',
    {method: 'DELETE', headers: {'Content-Type': 'application/json'}}).then(console.log)
```
**Поиск контактов по полям:**
```JavaScript
fetch('/search?field=email:1,firstName:2&login=test&password=test',
    {method: 'GET', headers: {'Content-Type': 'application/json'}}).then(console.log)
    
fetch('/search?field=phoneNumber:5,firstName:2&login=test&password=test',
    {method: 'GET', headers: {'Content-Type': 'application/json'}}).then(console.log)
```
