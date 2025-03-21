<h1 >Проект автоматизации тестирования сайта <a href="https://parabank.parasoft.com/parabank ">parabank.parasoft.com</a></h1>

## Содержание

* <a href="#annotation">Описание</a>
* <a href="#tools">Технологии и инструменты</a>
* <a href="#cases">Реализованные проверки</a>
* <a href="#jenkins">Запуск тестов в Jenkins</a>
* <a href="#allure">Отчеты в Allure</a>
* <a href="#testops">Интеграция с Allure TestOps</a>
* <a href="#telegram">Уведомления в Telegram с использованием бота</a>
* <a href="#video">Пример прогона теста в Selenoid</a>
* <a href="#console">Запуск тестов из терминала</a>

<a id="annotation"></a>
## Описание
Тестовый проект состоит из веб-тестов (UI), тестов API и мобильных тестов (Android).\
Краткий список интересных фактов о проекте:
- [x] Конфигурация с использованием техноголии `Owner` для запуска тестов в зависимости от параметров сборки
- [x] Возможность локального и удалённого запуска тестов
- [x] Возможность запуска как всех тестов, так и отдельно WEB, API
- [x] Использование `Faker` для генерации данных
- [x] Использование request/response спецификаций для API тестов
- [x] Custom Allure listener для API requests/responses логов
- [x] Интеграция с `Allure TestOps`
- [x] Возможность запуска тестов напрямую из `Allure TestOps`
- [x] Автотесты оформлены как тестовая документация посредством аннотаций `Allure`
- [x] `Page Object` проектирование


<a id="tools"></a>
## Технологии и инструменты

<div align="center">
<a href="https://github.com/"><img alt="GitHub" height="50" src="images/logo/GitHub.png" width="50"/></a>  
<a href="https://www.java.com/"><img alt="Java" height="50" src="images/logo/Java_logo.png" width="50"/></a>
<a href="https://gradle.org/"><img alt="Gradle" height="50" src="images/logo/Gradle.png" width="50"/></a>  
<a href="https://junit.org/junit5/"><img alt="JUnit 5" height="50" src="images/logo/JUnit5.png" width="50"/></a>
<a href="https://selenide.org/"><img alt="Selenide" height="50" src="images/logo/Selenide.png" width="50"/></a>
<a href="https://aerokube.com/selenoid/"><img alt="Selenoid" height="50" src="images/logo/Selenoid.png" width="50"/></a>
<a href="https://rest-assured.io/"><img alt="RestAssured" height="50" src="images/logo/RestAssured.png" width="50"/></a>
<a href="https://www.jenkins.io/"><img alt="Jenkins" height="50" src="images/logo/Jenkins.png" width="50"/></a>
<a href="https://github.com/allure-framework/"><img alt="Allure Report" height="50" src="images/logo/AllureReports.png" width="50"/></a>
<a href="https://qameta.io/"><img alt="Allure TestOps" height="50" src="images/logo/AllureTestOps.svg" width="50"/></a>
<a href="https://telegram.org/"><img alt="Telegram" height="50" src="images/logo/Telegram.png" width="50"/></a>
</div>

Автотесты в этом проекте написаны на Java с использованием фреймворка [Selenide](https://selenide.org/).\
<code>Gradle</code> — используется как инструмент автоматизации сборки.\
<code>JUnit5</code> — для выполнения тестов.\
<code>Selenoid</code> — для удаленного запуска браузера в Docker контейнерах.\
<code>REST Assured</code> — для тестирования REST-API сервисов.\
<code>Jenkins</code> — CI/CD для запуска тестов удаленно.\
<code>Allure Report</code> — для визуализации результатов тестирования.\
<code>Allure TestOps</code> — как система управления тестированием.\
<code>Jira</code> — как инструмент управления проектом и таск-трекер.\
<code>Telegram Bot</code> — для уведомлений о результатах тестирования.

Allure-отчет включает в себя:
* шаги выполнения тестов;
* скриншот страницы в браузере в момент окончания автотеста;
* Page Source;
* логи браузерной консоли;
* видео выполнения автотеста.

<a id="cases"></a>
## Реализованные проверки

### Автоматизированное создание тестовых данных TECH
:heavy_check_mark: Регистрация аккаунта для генерации тестовых данных


### Автоматизированные проверки WEB
:heavy_check_mark: Проверка авторизации в системе User=True,Password=True

:heavy_check_mark: Проверка авторизации в системе User=True,Password=False

:heavy_check_mark: Проверка авторизации в системе User=False,Password=True

:heavy_check_mark: Проверка авторизации в системе User=False,Password=False

:heavy_check_mark: Проверка регистрации аккаунта с рандомными данными, заполняем все поля

:heavy_check_mark: Проверка регистрации аккаунта с рандомными данными, заполняем только обязательные поля

:heavy_check_mark: Проверка регистрации аккаунта с рандомными данными, поле Username не заполнено

:heavy_check_mark: Проверка регистрации аккаунта с рандомными данными, пароли не совпадают

:heavy_check_mark: Проверка кнопки \"Забыли пароль?\"


### Автоматизированные проверки API
:heavy_check_mark: Получить список счетов для пользователя, проверить что все счета в списке принадлежат пользователю

:heavy_check_mark: Проверит, наличия счетов у пользователя

:heavy_check_mark: Проверка процедуры получения кредита

:heavy_check_mark: Проверка процедуры обновления информацию о клиенте

:heavy_check_mark: Проверка авторизации через API

:heavy_check_mark: Проверка использования метода DELETE в случае, когда он не предусмотрен

:heavy_check_mark: Получение статистики пользователя

:heavy_check_mark: Попытка удаления статистики пользователя

### Автоматизированные проверки WEB+API
:heavy_check_mark: Проверка процедуры перевода денежных средств с одного счета пользователя на другой

:heavy_check_mark: Проверка авторизации на UI через API"

:heavy_check_mark: Проверка кнопки восстановления пароля


<a id="jenkins"></a>
## Запуск тестов в <a target="_blank" href="https://jenkins.autotests.cloud/job/C31-SerzhSH-java_17_bank/"> Jenkins </a>

> Сборка с параметрами позволяет перед запуском изменить параметры для сборки (путем выбора из списка или прямым указанием значения).

<p align="center">
<img src="images/screenshots/JenkinsJob.png"/>
</p>

<a id="allure"></a>
## Отчеты в <a target="_blank" href="https://jenkins.autotests.cloud/job/C31-SerzhSH-java_17_bank/allure/"> Allure report </a>

### Тесты

<p align="center">
<img src="images/screenshots/AllureOverview%20.png">
</p>

### Графики

<p align="center">
<img src="images/screenshots/AllureGraphs.png">
</p>

<a id="testops"></a>
## Интеграция с <a target="_blank" href="https://allure.autotests.cloud/project/2083/dashboards"> Allure TestOps </a>

### Доска
<p align="center">
<img src="images/screenshots/AllureTestOpsDashboard.PNG">
</p>


### Тест-кейсы
<p align="center">
<img src="images/screenshots/AllureTestCases.PNG">
</p>

<a id="telegram"></a>
## Уведомления в Telegram с использованием бота

<p>
<img src="images/screenshots/TelegramBot.PNG">
</p>

<a id="video"></a>
## Пример прогона теста в Selenoid

> К каждому web-тесту в отчете прилагается видео
<p align="center">
  <img src="images/video/Video_test.gif">
</p>

<details>
<a id="console"></a>
<summary><h2>Запуск тестов из терминала</h2></summary>

### Локальный запуск тестов
#### ALL
```
gradle clean bank_test -Denv=local
```

#### WEB

```
gradle clean bank_web_test -Denv=local
```

#### API

```
gradle clean bank_api_testt -Denv=local
```

#### TECH

```
gradle clean bank_web_api_test -Denv=local
```

Сайт "parabank", является открытой тестовой платформой. Перед запуском тестов, необходимо сгенерировать тестовые данные для этого используется тест таггом TECH.

### Удаленный запуск тестов

```
clean test 
  -Dbrowser=${BROWSER} 
  -DbrowserVersion=${BROWSER_VERSION} 
  -DbrowserSize=${BROWSER_SIZE} 
  -DremoteUrl=${REMOTE_URL}
```


> `${BROWSER}` - наименование браузера (_по умолчанию - <code>chrome</code>_).
>
> `${BROWSER_VERSION}` - номер версии браузера (_по умолчанию - <code>126.0</code>_).
>
> `${BROWSER_SIZE}` - размер окна браузера (_по умолчанию - <code>1980x1080</code>_).
>
> `${REMOTE_URL}` - адрес удаленного сервера, на котором будут запускаться тесты.
</details>

