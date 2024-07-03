@tag
Feature: purchase the order from generalStore

Background: 
Given: user will open the application 

  @tag2
  Scenario Outline: positive test of purchange
    Given verify the header
    When user enter the <country>, <name> and <gender>
    Then validate toast message
  

    Examples: 
      | country  | name | gender  |
      | Bhutan |     vivek | male |
      | Angola |     rajat | male    |
