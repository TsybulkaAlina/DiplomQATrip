## Отчет о тестировании веб-сервиса "Путешествие дня"

**Дата:** 17.12.2024

**Описание:**

Проведено автоматизированное тестирование веб-сервиса "Путешествие дня".
Тестирование включало в себя проверку следующего функционала:

* **Оплата:**
    * **Покупка по карте:** проверка корректности обработки платежа, взаимодействие с банковским сервисом.
    * **Покупка в кредит:** проверка корректности обработки платежа, взаимодействие с банковским сервисом.
* **Взаимодействие с банковским сервисом:** проверка корректности обработки ответов банковских сервисов.
* **Работа с СУБД:** проверка корректности работы с базами данных **MySQL** и **PostgreSQL**.
* **Визуализация уведомлений:** проверка корректности отображения уведомлений об успешной покупке и ошибках.
* **Обработка невалидных данных:** проверка корректности выдачи сообщений об ошибках при невалидном заполнении формы.

**Количество тест-кейсов:**

Из **36** тест-кейсов:
* **8** не прошли
* **28** прошли

**Итого:**

* **77,7%** успешных тестов
* **22,2%** неуспешных тестов

**Отчет Allure:** [отчет Allure](https://github.com/TsybulkaAlina/DiplomQATrip/blob/main/documents/allure%20report%20final.png)

**Общие рекомендации:**

* **Провести повторное тестирование:** После исправления ошибок необходимо провести повторное тестирование, чтобы убедиться в том, что все проблемы устранены.
* **Сохранить отчеты:**  Все отчеты о тестировании необходимо сохранять для дальнейшего анализа и отслеживания прогресса. 
* **Добавить test id в операторы селекторов:**  Это позволит более эффективно отслеживать элементы на странице и упростит процесс тестирования.
* **Исправить найденные ошибки приложения:** Необходимо исправить все ошибки, выявленные в ходе тестирования. [Баг-репорты](https://github.com/TsybulkaAlina/DiplomQATrip/issues)
* **Реализовать подцветку уведомлений об успешной покупке и ошибке при покупке разными цветами:** Это позволит пользователям быстрее и легче понимать результат операции.
* **Создать документацию на параметры заполнения полей формы:** Документация должна содержать информацию о допустимых значениях для каждого поля, а также о правилах валидации данных. 



