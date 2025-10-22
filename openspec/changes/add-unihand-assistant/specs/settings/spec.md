## ADDED Requirements

### Requirement: Settings Radial Menu
The system SHALL provide a radial menu for settings configuration optimized for Thumb Zone interaction.

#### Scenario: Settings menu access
- **WHEN** user taps settings button in main radial menu
- **THEN** settings radial menu opens
- **AND** menu is positioned in Thumb Zone area
- **AND** menu contains configuration options

### Requirement: Position Configuration
The system SHALL allow users to configure radial menu position (left/right) for optimal thumb access.

#### Scenario: Position selection
- **WHEN** user opens settings menu
- **THEN** position options are displayed
- **AND** user can select left or right position
- **AND** selection is saved and applied immediately

#### Scenario: Position change
- **WHEN** user changes position setting
- **THEN** radial menu moves to new position
- **AND** change is persistent across app sessions

### Requirement: Move Sensitivity Configuration
The system SHALL allow users to configure Move function sensitivity for device tilting.

#### Scenario: Sensitivity selection
- **WHEN** user opens settings menu
- **THEN** Move sensitivity options are displayed
- **AND** user can select from: Low, Medium, High
- **AND** selection is saved and applied immediately

#### Scenario: Sensitivity change
- **WHEN** user changes sensitivity setting
- **THEN** Move function responds with new sensitivity
- **AND** change is persistent across app sessions

### Requirement: Animation Speed Configuration
The system SHALL allow users to configure animation speed for zoom and rotation operations.

#### Scenario: Speed selection
- **WHEN** user opens settings menu
- **THEN** animation speed options are displayed
- **AND** user can select from: Slow, Normal, Fast
- **AND** selection is saved and applied immediately

#### Scenario: Speed change
- **WHEN** user changes animation speed setting
- **THEN** zoom and rotation animations use new speed
- **AND** change is persistent across app sessions

### Requirement: Move Combinations Configuration
The system SHALL allow users to configure Move function combinations for device tilting.

#### Scenario: Combination selection
- **WHEN** user opens settings menu
- **THEN** Move combination options are displayed
- **AND** user can select from: Standard, Opposite
- **AND** selection is saved and applied immediately

#### Scenario: Combination change
- **WHEN** user changes combination setting
- **THEN** Move function uses new combination logic
- **AND** change is persistent across app sessions

### Requirement: Settings Persistence
The system SHALL save all settings and restore them when app is reopened.

#### Scenario: Settings persistence
- **WHEN** user changes any setting
- **THEN** setting is saved to device storage
- **AND** setting is restored when app is reopened
- **AND** setting is applied immediately
