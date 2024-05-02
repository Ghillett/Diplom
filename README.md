# Запуск автотестов

1. Установить приложение Android Studio;
2. Склонировать репозиторий;
3. Открыть папку fmh_android_15_03_24 (1) как проект в Android Studio;
4. Создать эмулятор (API 29);
5. Синхронизировать Build Gradle;
   
Тесты находятся в папке *fmh_android_15_03_24 (1)\app\src\androidTest\java\ru\iteco\fmhandroid\ui\tests*

Тесты запускаются нажатием на стрелочку напротив класса теста.

## Просмотр отчетов Allure

Для просмотра отчетов необходимо установить Allure в проект с помощью команды:
**npm install --save-dev allure-commandline**

Файлы отчетов генерируются на устройстве в папке *data > data > ru.iteco.fmhandroid > files > allure-results.*

Для просмотра отчета файлы должны быть перенесены в папку проект с название allure-results, после этого можно сгенерировать отчет с помощью команды:
**npx allure serve**