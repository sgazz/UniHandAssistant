## Why
Gradle build greška koja sprečava kompajliranje UniHand Assistant aplikacije. Greška je uzrokovana neispravnim dependency-jima i nedostajućim Gradle wrapper fajlovima.

## What Changes
- **FIXED**: Neispravni dependency `androidx.sensors:sensors:1.0.0` koji ne postoji
- **ADDED**: Gradle wrapper fajlovi (gradlew, gradlew.bat, gradle/wrapper/)
- **UPDATED**: Dependency verzije za kompatibilnost
- **FIXED**: Build.gradle konfiguracija za Android Studio
- **ADDED**: Nedostajući drawable resursi i ikone

## Impact
- Affected specs: project-setup, build-configuration
- Affected code: build.gradle, gradle wrapper, drawable resources
- **BREAKING**: Nema breaking changes - samo ispravke
- Build: Omogućava uspešno kompajliranje aplikacije
- Development: Uklanja blokere za dalji razvoj
