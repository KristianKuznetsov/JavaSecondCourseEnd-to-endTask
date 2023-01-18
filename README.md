# JavaSecondCourseEnd-to-endTask

**[Верхний уровень](https://github.com/KristianKuznetsov/JavaPractice)**

# Сквозная Задача 
## Описание 
** 1. В качестве входных данных принимается файл. Приложение автоматически определяет для исходного файла (например, test.txt.encrypted.encryptedили test.json.zip), затем начинается процесс расшифровки и разархивирования до исходного файла с расширениями .txt, .json,.xml **

- приложение поддерживает случаи многоуровневой вложенности архивов шифрования и архивирования, например  test.txt.encrypted.zip.encrypted.encrypted.encrypted.zip.encryptedт.п .;

** 2. Обрабатывает полученную информацию путем поиска токенов арифметических выражений в любом из файлов .txt, .json, ;.xml
3. Обрабатывает любое арифметическое выражение в скобках любой сложности (в пределах двойного ( 4.9e-324 to 1.7e+308), а также с учетом операции деления на 0.
4. Записывает данные в выходной файл в одном из типов .txt, .jsonв .xmlзависимости от входного файла; **


- **Разработать клас обувь содержащий следующие поля:**
   - Название обуви
   - Название фирмы
   - Дата поставки
   - Колличество пар в магазине
   - Стоимость

___

- **Разработать класс обувной магазин, реализующий следующие функции:**
   - Ввод(вывод) информации из(в) файла
   - Добавление элемента
   - Сортировка по названию обуви
   - Сортировка по цене
   - Бинарный поиск по названию обуви (после сортировки)
   - Фильтр обуви по году поставки 

___

- **Данные находящиеся во входном файле перемешаны, и имеют следующую структуру:**
   - Название обуви заключено в двойные ковычки "..."
   - Название фирмы заключено в круглые скобки (...)
   - Дата находиться в интервале от 01.2020 до 11.2022
   - Количество пар в интервале от 1 до 100
   - Стоимость действительное число с двумя знаками после запятой
   
___
  
- **Разработать UI интерфейс, визуализирующий класс магазин**

![Итоговый вид](https://github.com/KristianKuznetsov/JavaPractice/blob/main/Additional%20materials/2022-12-23_02-01-12.png)



Описание:
В качестве входных данных принимается файл. Приложение автоматически определяет для исходного файла (например, test.txt.encrypted.encryptedили test.json.zip), затем начинается процесс расшифровки и разархивирования до исходного файла с расширениями .txt, .json,.xml
ВАЖНО: приложение поддерживает случаи многоуровневой вложенности архивов шифрования и архивирования, например и test.txt.encrypted.zip.encrypted.encrypted.encrypted.zip.encryptedт.п .;
Обрабатывает полученную информацию путем поиска токенов арифметических выражений в любом из файлов .txt, .json, ;.xml
Обрабатывает любое арифметическое выражение в скобках любой сложности (в пределах двойного ( 4.9e-324 to 1.7e+308), а также с учетом операции деления на 0.
Записывает данные в выходной файл в одном из типов .txt, .jsonв .xmlзависимости от входного файла;
В консольном приложении используются следующие шаблоны проектирования: Strategy (для выбора стратегии дешифрования/разархивирования в зависимости от расширения), Singleton.

FileEncryption.java
Утилита, которая шифрует файл с помощью шифрования AES.

Технические подробности
Это приложение позволяет вам зашифровать файл с помощью алгоритма шифрования AES , запрашивая у вас пароль, чтобы использовать его в качестве ключа. В процессе преобразования файла гарантируется отсутствие потери данных: шифруется не исходный файл, а его копия.

Функциональность
Шифрование файлов (или безопасно отменить его без сбоя приложения)
Расшифровка файла (или безопасно отменить без сбоя приложения)
Дружественный графический пользовательский интерфейс, показывающий ход выполнения задачи
ZipUtility.java
ZipArchive — это простой служебный класс для сжатия и распаковки файлов в Windows, Linux.

Разархивируйте zip-файлы;
Создавайте zip-файлы;
Создавайте большие архивные файлы;
Библиотеки, используемые в проекте:
googlecode.json.simple- для работы с .jsonфайлами и jsonобъектами.
junit-jupiter-5.8.1- для работы с юнит-тестами.
Приложение части GUI (на основе библиотеки JavaFX):
Почему JavaFX? Swing — это устаревшая библиотека, которая полностью включает и предоставляет подключаемые компоненты пользовательского интерфейса, тогда как JavaFX имеет компоненты пользовательского интерфейса, которые все еще развиваются с более продвинутым внешним видом.
