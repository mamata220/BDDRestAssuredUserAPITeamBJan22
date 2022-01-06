@put
Feature: Put user feature

  Scenario Outline: To update user name with Valid input
    Given User is on Put Method with endpoint<userid>
    When user sends request to update user name with Valid input
    Then User should receive 201 OK Created status code for put

    Examples: 
      | userid |
      | U01    |

  Scenario Outline: To update with invalid phone number
    Given User is on Put Method with endpoint<userid>
    When user sends request to update phone number with alphanumeric input
    Then User should receive 400 Bad Request status code for put

    Examples: 
      | userid |
      | U02    |

  Scenario Outline: To update time zone with valid input.
    Given User is on Put Method with endpoint<userid>
    When user sends request to update time zone with valid input
    Then User should receive 200 OK Valid status code for put

    Examples: 
      | userid |
      | U03    |

  Scenario Outline: To update time zone with invalid input.
    Given User is on Put Method with endpoint<userid>
    When user sends request to update time zone with invalid input
    Then User should receive 500 Internal Server Error status code for put

    Examples: 
      | userid |
      | U04    |

  Scenario Outline: To update visa status with invalid input
    Given User is on Put Method with endpoint<userid>
    When user sends request to update visa status with invalid input
    Then User should receive 500 Internal Server Error status code for put

    Examples: 
      | userid |
      | U04    |

  Scenario Outline: To update Linkedin with valid input
    Given User is on Put Method with endpoint<userid>
    When user sends request to update Linkedin with valid input
    Then User should receive 200 OK Valid status code for put

    Examples: 
      | userid |
      | U08    |

