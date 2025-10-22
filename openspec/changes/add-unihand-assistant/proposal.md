## Why
Potreba za inkluzivnom Android aplikacijom koja omogućava jednorukim korisnicima da lako pregledaju, uvoze i manipulišu slikama koristeći optimizovani Thumb Zone interfejs sa radijalnim menijem i senzorima za pomeranje.

## What Changes
- **ADDED**: Image Editor capability - pregled i uvoz slika iz Gallery-a sa full screen prikazom
- **ADDED**: Radial Menu capability - radijalni meni sa 7 funkcionalnosti (Zoom, Unzoom, Rotate Left, Rotate Right, Reset, Settings, Move) optimizovan za Thumb Zone
- **ADDED**: Settings capability - radijalni meni sa konfiguracijama za poziciju, osetljivost, brzinu animacija i Move kombinacije
- **ADDED**: Sensors capability - žiroskop i akcelerometar za pomeranje slika naginjanjem uređaja
- **ADDED**: Thumb Zone optimization - svi kritični UI elementi u easy-to-reach oblasti za jednoruke korisnike

## Impact
- Affected specs: image-editor, radial-menu, settings, sensors
- Affected code: Android Studio project, Kotlin MVVM architecture
- **BREAKING**: Nema breaking changes - nova aplikacija
- Accessibility: 100% inkluzivnost za jednoruke korisnike
- UX: Minimum klikova, samo palac za interakciju, radijalni meniji gde god je moguće
