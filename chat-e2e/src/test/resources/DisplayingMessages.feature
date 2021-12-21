Ability: User can send messages
  Scenario: Deniz sending a message to Murat
    Given Deniz and Murat are valid users
    When Deniz wants to send a message "this is message content" to Murat
    Then Murat can display the message