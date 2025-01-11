Student Management System Michal Nowacki

## 1. Instrukcje dotyczące kompilacji i uruchamiania aplikacji

### Wymagania wstępne:
- Java 8 lub nowsza.
- Maven (do zarządzania zależnościami).
- SQLite (bazę danych można utworzyć z wykorzystaniem pliku `students.db`).

### Kompilacja projektu:
1. Skopiuj cały projekt do wybranego katalogu.
2. Otwórz terminal/wiersz poleceń.
3. Przejdź do katalogu głównego projektu (tam, gdzie znajduje się plik `pom.xml`).
4. Wykonaj polecenie, aby zbudować projekt za pomocą Mavena:

mvn clean install

Uruchamianie aplikacji:
Po zbudowaniu projektu, uruchom aplikację za pomocą poniższego polecenia Maven:

mvn exec:java

Lub, jeśli używasz IDE (np. IntelliJ IDEA), uruchom klasę MainFrame bezpośrednio jako aplikację Java.

2. Przegląd funkcjonalności oferowanej przez system zarządzania studentami
System zarządzania studentami (SMS) umożliwia użytkownikom:

Dodawanie studentów do bazy danych.
Usuwanie studentów z bazy danych na podstawie unikalnego ID.
Aktualizowanie danych studenta (np. zmiana imienia, wieku, oceny).
Wyświetlanie wszystkich studentów przechowywanych w bazie danych.
Obliczanie średniej ocen wszystkich studentów.
Aplikacja posiada graficzny interfejs użytkownika (GUI), umożliwiający łatwą interakcję z systemem. Zawiera pola do wprowadzania danych studenta (ID, imię, wiek, ocena) oraz przyciski umożliwiające realizację operacji na danych.

Ekran główny:
Pole ID studenta: Unikalny identyfikator studenta.
Pole imienia: Imię studenta.
Pole wieku: Wiek studenta (liczba całkowita).
Pole oceny: Ocena studenta (w skali 0.0 - 100.0).
Przyciski:
Add Student: Dodaje nowego studenta.
Remove Student: Usuwa studenta na podstawie ID.
Update Student: Umożliwia aktualizację danych studenta.
Display All Students: Wyświetla wszystkich studentów.
Calculate Average: Oblicza średnią ocen dla wszystkich studentów.

3. Instrukcje dotyczące konfiguracji bazy danych
Baza danych używa SQLite do przechowywania informacji o studentach. Projekt jest skonfigurowany do używania lokalnej bazy danych zapisanej w pliku students.db.

Krok 1: Stworzenie bazy danych
Jeśli nie masz bazy danycg możesz ją stworzyh przy pomocy np db browser albo poprostu polecen z SQLite:

CREATE TABLE IF NOT EXISTS students (
    studentID TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    age INTEGER NOT NULL,
    grade REAL NOT NULL
);

Krok 2: Połączenie z bazą danych
Aplikacja wykorzystuje JDBC do połączenia z bazą danych SQLite. Ścieżka do bazy danych jest skonfigurowana w klasie StudentManagerImpl w zmiennej DATABASE_URL. Ścieżka domyślna to:

private static final String DATABASE_URL = "jdbc:sqlite:src/main/resources/students.db";

Możesz zmienić tę ścieżkę, jeśli chcesz używać innej lokalizacji dla pliku bazy danych.


Obsługa błędów: Aplikacja zawiera odpowiednią obsługę błędów, zarówno dla walidacji danych wprowadzanych przez użytkownika, jak i błędów związanych z połączeniem z bazą danych.
GUI: Interfejs graficzny wykorzystuje bibliotekę Swing, umożliwiającą intuicyjne operowanie danymi w systemie.
