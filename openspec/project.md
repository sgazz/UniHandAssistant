# Project Context

## Purpose
**UniHand Assistant** - Android aplikacija za inkluzivno korišćenje slika jednom rukom. Aplikacija omogućava pregled i uvoz slika iz Gallery-a sa radijalnim menijem optimizovanim za Thumb Zone, uključujući funkcionalnosti za zoom, rotaciju, pomeranje pomoću žiroskopa/akcelerometra, i resetovanje.

## Tech Stack
- **Platform**: Android
- **Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **IDE**: Android Studio
- **Sensors**: Gyroscope + Accelerometer
- **UI**: Custom Radial Menu + Thumb Zone optimization

## Project Conventions

### Code Style
- Kotlin idiomatic code
- camelCase for variables and functions
- PascalCase for classes
- UPPER_CASE for constants
- 4-space indentation
- Maximum line length: 120 characters

### Architecture Patterns
- **MVVM Pattern**: Separation of concerns with ViewModels
- **Repository Pattern**: Data access abstraction
- **Observer Pattern**: LiveData for reactive UI updates
- **Dependency Injection**: Manual DI or Hilt (TBD)

### Testing Strategy
- Unit tests for ViewModels and business logic
- Integration tests for sensor functionality
- UI tests for accessibility and Thumb Zone optimization
- Manual testing with one-handed users

### Git Workflow
- Feature branches from main
- Conventional commits
- PR reviews required
- Semantic versioning

## Domain Context

### Thumb Zone Optimization
- **Primary users**: One-handed users (49% of mobile users hold phone with one hand)
- **Thumb Zone**: Easy-to-reach area for thumb interaction
- **Radial Menu**: Central button opens/closes radial menu in Thumb Zone
- **Accessibility**: 100% inclusive design for single-hand usage

### Core Functionality
1. **Gallery Integration**: Browse and import images
2. **Radial Menu**: 7 functions (Zoom, Unzoom, Rotate Left, Rotate Right, Reset, Settings, Move)
3. **Move Function**: Gyroscope + accelerometer for image positioning
4. **Settings**: Radial menu with configuration options
5. **Reset**: Return image to full-screen mode

### User Experience
- **Offline operation**: No internet required
- **Minimum clicks**: Optimized for single-thumb interaction
- **Smooth animations**: Continuous zoom/rotation with configurable speed
- **Gesture-free**: No pinch-to-zoom (thumb-only interaction)

## Important Constraints

### Accessibility Requirements
- **Single-hand usage**: All interactions must be possible with one hand
- **Thumb Zone compliance**: Critical UI elements in easy-to-reach area
- **No pinch gestures**: Only button-based interactions
- **Left/right hand support**: Configurable Thumb Zone positioning

### Technical Constraints
- **Offline operation**: No network dependencies
- **Performance**: Smooth 60fps animations
- **Battery optimization**: Efficient sensor usage
- **Memory management**: Large image handling

### UX Constraints
- **Radial menu priority**: Use radial menus wherever possible
- **Minimum interaction**: Reduce clicks to absolute minimum
- **Consistent behavior**: Predictable interactions across all features

## External Dependencies

### Android System
- **Gallery API**: Access to device photo library
- **Sensor Framework**: Gyroscope and accelerometer data
- **File System**: Local image storage and caching

### Libraries (TBD)
- **Image Processing**: For zoom/rotate operations
- **Animation**: Smooth transitions and effects
- **Sensors**: Gyroscope/accelerometer data processing

### No External APIs
- **Offline operation**: No internet connectivity required
- **Local processing**: All image operations performed locally
