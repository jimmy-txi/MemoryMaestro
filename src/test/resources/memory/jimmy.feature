# TESTS conditions of launching the game
Feature: Launching a MemoryMaestro game

  Scenario: Launch a 2-player game with letters on a 4x4 grid
    Given I run the application
    And I have selected the "2 players" game type
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
