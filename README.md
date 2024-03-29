
**[Верхний уровень](https://github.com/KristianKuznetsov/top-levelInformationRepository)**

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
### Выбор файла
![Выбор файла](https://github.com/KristianKuznetsov/JavaPractice/blob/main/Additional%20materials/2023-01-18_20-56-55.png)

### Работа программы
![Во время работы программы](https://github.com/KristianKuznetsov/JavaPractice/blob/main/Additional%20materials/2023-01-18_21-38-46.png)

### Описание кнопок

- Меню ***Данные***
   - Выбрать файл - открывает окно выбора файла, после чего автоматически преобразовывает текст и выводит на экран
   - Записать файл - считывает название файла из текстового поля ниже. При наличии в названии расширения: `.txt`, `.json`, `.xml` создается файл данного типа, при неизвестном расширении, или отцутствии его, используется расширение по умолчанию (`.txt`). Если имя файла не указано овтоматически создается файл `"outStandard.txt"`. Записывает преобразованный текст в созданный файл.
      - **В связи с тем, что автором не до конца продуман вид хранения обычного текста в файлах формата `.json` и `.xml`, итоговая запись может не соответствовать общепринятым нормам**
      - Для `.xml` файла запись будет иметь вид:
      
![](https://github.com/KristianKuznetsov/JavaPractice/blob/main/Additional%20materials/2023-01-19_00-33-50.png)

- 
   - Заархивировать файл - архивирует записанный файл, в случае его отсутствия, ничего не происходит (класс: **`ZIPArchiving`**)
   - Закодировать файл - зашифровывает записанный файл, в случае его отсутствия, ничего не происходит (класс: **`Encryption`**)
      - ***Описание классов ниже***
   
- Меню ***Дополнительно*** 
   - Инструкция - Выводит краткое описание работы в 
   - Очистить поле - при желании пользователя очищает все поля
___

## Библиотеки проекта, не входяшие в ядро Java: 
- [**`jackson-2.14.0-rc2`**](https://github.com/KristianKuznetsov/JavaSecondCourseEnd-to-endTask/tree/main/Libraries/JSON) - для работы с `.json` файлами и  `json` объектами
- [**`junit-4.13.2`**](https://github.com/KristianKuznetsov/JavaSecondCourseEnd-to-endTask/tree/main/Libraries/Unit%20Tests) - для работы с юнит-тестами
- [**`javafx-sdk-19`**](https://github.com/KristianKuznetsov/JavaSecondCourseEnd-to-endTask/tree/main/Libraries/JavaFX) - для работы с UI
___
## Описание классов проекта: 

### Основной блок программы:
- ***ExpressionEvaluation*** 
   - **public static boolean ParenthesesCheck(String str)** - проверяет полученое математическое выражение на правильност последовательности скобок
   - **public ExpressionEvaluation(String str)** - находит математические выражения, вычисляет их и заменяет выражение на результат

### Работа с файлами: (Чтение, запись, архивирование, шифрование)
- ***IReader*** - Интерфейс
   - **String ReadData(String nameFile)** - читает текст, из файла с заданным именем
   - **void WriteData(String fName, String text)** - пишет текст в файл заданного имени
      - **Reader** - переопределяет ReadData и WriteData
      - **ReaderJSON** - переопределяет ReadData и WriteData для файлов расширения `.json`
      - **ReaderTXT** - переопределяет ReadData и WriteData для файлов расширения `.txt`
      - **ReaderXML** - переопределяет ReadData и WriteData для файлов расширения `.xml`
- ***Encryption***
   - **public String Encode(String name)** - кодирует файл имя которого передано, возращает новое имя файла
   - **public String Decode(String name)** - декодирует файл имя которого передано, возращает новое имя файла
      - **Для кодировки ит декодировки используется class Base64** 

- ***ZIPArchiving***
   - **public String Archive(String fileName)** - архивирует файл имя которого передано, возращает новое имя файла
   - **public String UnArchive(String fileName)** - разархивирует файл имя которого передано, возращает новое имя файла
      - **Для архивации используется ZipInputStream и ZipOutputStream** 

### Вычисления:
- ***ArithmeticEvaluator***
- ***ArithmeticParser***
- ***RdpCalculator***
- ***RdpCharacterHelper***
___
**Данное вычислительное ядро поддерживает два метода вычислений:**

Вычислить выражение с использованием алгоритма сортировочной станции **(Используется в коде)**
```java
ArithmeticEvaluator.evaluate(expression);
// public static String evaluate(String expression);
```

Вычислить выражение с использованием метода рекурсивного спуска 
```java
RdpCalculator.evaluate(expression);
//public static String evaluate(String expression);
```
___

***Примечание:***

Данное ядро также поддерживает операции: **`"%"`** и **`"sqrt"`**

При доработке парсинга в классе ***ExpressionEvaluation***, могут сразу поддерживаться в программе


### UI класс:
- ***HelloApplication*** - Отвечает за создание UI и обработку всех кнопок. Из-за простоты UI, к сожалению все в одном классе.
 
### Вспомогательные классы:
- ***TextBuilder***
   - **public static String generateTextWithWidth(ArrayList<String> list, int width);** - форматирует массив строк в текст заданной ширины *(грубое форматирование)*
   
- ***ErrorClass***
   - **public static String bracketError(String str);** - преобразовывает математическое выражение с ошибкой последовательности скобок к новому виду
      - **Используется**
   - **public static String overflowError(String str);** - преобразовывает математическое выражение с ошибкой переполнения типа к новому виду
      - **Не используется**
   - **public static String divideByZeroError(String str);** - преобразовывает математическое выражение с ошибкой деления на ноль к новому виду
      - **Используется**
   - **public static String unknownError(String str);** -  преобразовывает математическое выражение с неотслеживаемой ошибкой к новому виду
      - **Используется**
      
```java
ErrorClass.bracketError("(66 + 17))");
//[ Bracket validation error in expression { "(66 + 17))" } ]

ErrorClass.divideByZeroError("9 / 0");
//[ Division by zero error in expression { "9 / 0" } ]

ErrorClass.unknownError("12*");
//[ Undefined error in expression { "12*" } ]
```
      

### Тестирование:
- ***TestClass*** - класс содержащий все тесты приложения
   - **Список тестов:**
___
  **Проверка собок**
   
      - trueVoidTestBracket
      - trueSimpleTestBracket
      - falseSimpleTestRightBracket
      - falseSimpleTestLeftBracket
      - bracketFileTest
___
   **Парсинг выражений**
   
      - parsingFileTest
___
   **Тестирование класса ошибок**
   
      - testBracketError
      - testOverflowError
      - testDivideByZeroError
___
   **Тестирование вычислительного ядра**
   
      - ArithmeticEvaluatorTest
      - RdpCalculatorTest
___
   **Тестирование работы с файлами**
   
      - ArchiveTest
      - UnArchiveTest
      - EncryptionTest
      - ReadTxtTest
___
  
   **Данные два класса помогают подготовить файлы для тестирования**
   
- ***testNode***
- ***TestWriter***
___
