**Для запуска тестов необходимо выполнить следующую инструкцию:**

**1. Понадобится програмное обеспечение :**
* Операционная система Windows 10
* Браузер Google Chrome, Версия 131.0.6778.87 (Официальная сборка), (64 бит)
* IntelliJ IDEA 2023.2.2 (Community Edition)
* Java: OpenJDK 11
* Docker version 4.34.2
* Запуск SUT

**2. Шаги воспроизведения:**
* Клонировать репозиторий командой git clone https://github.com/TsybulkaAlina/DiplomQATrip
* Открыть проект в IntelliJ IDEA
*  Из ./src в скаченном репозитории, запустить Docker командой docker compose up -d
* Запустить в терминале файл командой "java -jar aqa-shop.jar"
* Открыть в браузере адрес "localhost:8080"

**3. Для запуска тестов необходимо:**
* В терминале ввести команду ./gradlew clean test

**4. Для запуска отчетов Allure необходимо:**
* В терминале ввести команду ./gradlew allureReport
