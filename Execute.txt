@echo off
set JAVA_HOME=C:\Program Files\Amazon Corretto\jdk15.0.2_7
set JAR_NAME=main.java.Application.jar
set MANIFEST_FILE=C:\Users\ahieo\IdeaProjects\CourseProject\out\production\CourseProject\META-INF\MANIFEST.MF
set ARTIFACTS_FOLDER=C:\Users\ahieo\IdeaProjects\CourseProject\out\artifacts\main.java.Application

:: Створення JAR-файлу з файлом MANIFEST.MF та залежностями
"%JAVA_HOME%\bin\jar" cfm "%JAR_NAME%" "%MANIFEST_FILE%" -C C:\Users\ahieo\IdeaProjects\CourseProject\out\production\CourseProject .

:: Переміщення JAR-файлу в бажану теку (ARTIFACTS_FOLDER)
move "%JAR_NAME%" "%ARTIFACTS_FOLDER%"

:: Запуск JAR-файлу з вказаною версією Java
"%JAVA_HOME%\bin\java" -jar "%ARTIFACTS_FOLDER%\%JAR_NAME%"