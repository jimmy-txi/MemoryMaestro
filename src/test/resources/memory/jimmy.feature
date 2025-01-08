# TESTS conditions of launching the game
Feature: Launching a MemoryMaestro game


# .-----------.
# | no Option |
# '-----------'
  Scenario: Launch the game without selecting any options
    Given I run the application
    When I start the game
    Then the game should not start


# .------------.
# | One Option |
# '------------'
  Scenario: Launch the game by selecting only a grid size
    Given I run the application
    And I have selected a "4x4" grid size
    When I start the game
    Then the game should not start

  Scenario: Launch the game by selecting only a game type
    Given I run the application
    And I have selected the "cheat" game type
    When I start the game
    Then the game should not start

  Scenario: Launch the game by selecting only a game mode
    Given I run the application
    And I have selected the "numbers" game mode
    When I start the game
    Then the game should not start


# .------------.
# | two Option |
# '------------'
  Scenario: Launch the game by selecting only a game mode on a 4x4 grid
    Given I run the application
    And I have selected the "numbers" game mode
    When I start the game
    Then the game should not start

  Scenario: Launch a cheat game on a 6x4 grid
    Given I run the application
    And I have selected the "numbers" game mode
    When I start the game
    Then the game should not start

# .------------.
# | All Option |
# '------------'
  Scenario: Launch a 2 players game with letters on a 4x4 grid
    Given I run the application
    And I have selected the "2player" game type
    And I have selected the "letters" game mode
    And I have selected a "4x4" grid size
    When I start the game
    Then the game should start

  Scenario: Launch a cheat game with numbers on a 6x4 grid
    Given I run the application
    And I have selected the "cheat" game type
    And I have selected the "numbers" game mode
    And I have selected a "6x4" grid size
    When I start the game
    Then the game should start