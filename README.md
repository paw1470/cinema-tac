# cinema-tac

[![Build Status](https://travis-ci.org/paw1470/cinema-tac.svg?branch=master)](https://travis-ci.org/paw1470/cinema-tac)


Projekt zaliczeniowy system kina. 
Początkowo trochę przesadziłem z funkcjonalnością. Część możliwości nie jest używana ale postanowiłem zostawić na przyszłe modyfikacje.
Prawdopodobnie trochę rozbuduję dla siebie.

Jak działa. 
1. Administrator musi dodać kino.
2. Administrator musi dodać sale w kinie (room).
3. Administrator musi dodać film.
4. administrator musi dodać seans.
5. administrator musi dodać bilety. 

6. Użytkownik może wybrać seans i typ biletu.
7. Uzytkownik moze anulowac rezerwacje podając id biletu (tutaj ktos moze anulowac komus bilet wiec musze dac jakies potwierdzenia).
8. Kasjer moze potwierdzic bilet jezeli uzytkownik zaplaci. 
9. Kasjer może anulować wszystkie rezerwacje w dowolnym momencie. 
10. Kasjer może zarezerwowac bilet i go oplacic

Port aplikacji:
9666

dodany swager jako dokumentacja
http://localhost:9666/swagger-ui.html

budowanie testow 
mvn test

uruchamianie
mvn clean install

znane błedy:
dużo nadmiarowego kodu ponieważ początkowo aplikacja miałą być dużo większa ale zabrakło czasu i umiejętności. 
przez brak czasu jest kilka problemow z testami i pewne testy mogą nie sprawdzać tego co powinny. 
testy repozytoriów i serwisów są jako całość.
część testów wpływa na zachowanie innych. zabrakło czasu na rozwiązanie tego problemu.


Co jest z wymagań
klient:
    wszystko ale rezerwacja tylko jednego miejsca. zabraklo czasu na dodanie kilku linijek
Administrator:
    jest wszystko
kasjer:
    nie ma automatycznego anulowania i kasjer musi recznie wyslac post z id seansu

