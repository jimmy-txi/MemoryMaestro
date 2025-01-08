Feature: Jeu de mémoire
  Teste les fonctionnalités principales de la classe Jeu.

  Scenario: Démarrer une partie avec 3 paires
    Given je crée un jeu avec 3 paires
    Then le nombre total de cartes doit être 6
    And aucune carte ne doit être trouvée
    And la partie ne doit pas être terminée

  Scenario: Jouer un coup valide et gagner une paire
    Given je crée un jeu avec 2 paires
    When je joue la première carte numéro 0
    And je joue la seconde carte avec le même numéro 1
    Then je dois avoir trouvé une paire
    And le nombre de coups joués doit être 2

  Scenario: Jouer un coup avec une carte invalide
    Given je crée un jeu avec 2 paires
    When je joue une carte invalide avec le numéro -1
    Then une erreur doit être retournée

