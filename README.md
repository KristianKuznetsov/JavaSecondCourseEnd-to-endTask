# JavaSecondCourseEnd-to-endTask

**[Верхний уровень](https://github.com/KristianKuznetsov/JavaPractice)**

# Сквозная Задача 

## Описание: 
**1. В качестве входных данных принимается файл. Приложение автоматически определяет тип исходного файла `(например, test.txt.enc или test.json.zip)`, затем начинается процесс расшифровки и разархивирования до исходного файла с расширениями `.txt`, `.json`, `.xml`**

- ***приложение поддерживает случаи многоуровневой вложенности архивов шифрования и архивирования, например `test.txt.enc.zip.enc.enc.enc.zip.enc` и т.д.***
- ***За данную операцию отвечает класс `Reader` оформленный как Абстрактная фабрика***

**2. Считывает текст исходного файла и ищет в нем математические выражения  `(пример : (17-2) * 3 )`**

- ***Для поиска используются регулярные выражения***
- ***Об ограничениях смотреть ниже***

**3. Вычисляет найденные математические выражения и заменяет их на результат в исходном тексте**

- ***Об ограничениях смотреть ниже***

**4. Записывает преобразованный текст в указанный выходной файл в одном из следующих типов `.txt`, `.json`, `.xml`** 

**5. Полученный преобразованный файл шифрует и архивирует по желанию пользователя** 

___

## Ограничения: 

### Поддерживает:
   - Оператор скобок: `"()"`
   - Простые математические операции: `"+"`, `"-"`, `"*"`, `"/"`
   - Действительные числа
   - Числа с плавающей точкой (В записе возможна как точка, так и с запятая)
   - Сообщает об ошибке деления на ноль
   - Сообщает об ощибке неверной скобочной последовательности в математическом выражении
   - Остальные возможные ошибки вычисления сообщаются как неизвестная ошибка.
 
### Не поддерживается:
   - Любые математические функции: `"ln"`, `"fabs"`, `"pow"`, `"sqrt"`, ...
   - Любые тригонометрические и обратные тригонометрические функции: `"cos"`, `"sin"`, `"tn"`, `"arcsin"`, ...
   - Логические операции: `"||"`, `"&&"`, `"or"`, `"and"`, `"xor"`, ...
   - Математические операторы (Помимо базовых): (`"%"`, `"^"`, `"++"`, ...
   - Операторы присваиывания: `"+="`, `"*="`, `"="`, ...
   - Неравенства/Операторы сравнения: `">"`, `">="`, `"=="`, `"<"`, `"<="`
   - Нет проверки на выход за пределы типа данных
   - Нет обработки неопределенностей: `"Infinity / Infinity"`, `"0 / 0"`, `"0 * Infinity"`, ...
___
## UI: 
### Вид
### Подробное описание кнопок

## Библиотеки проекта, не входяшие в ядро Java: 
## Описание классов: 

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
