Feature: A set of scenarios for testing review section in opencart module

  Scenario Outline: User fills a review on product
    Given user in home page
    And at least one product
    And product featured in home page
    When navigate to review section of first product featured
    And submit review with <ReviewDetails> details
    Then new comment added
    And close
    Examples:
      | ReviewDetails |
      | "name1,review for first person for product,5" |
#      | "name2,review for second person for product,3" |

#  Scenario Outline: Admin deletes product
#    Given admin in Login page that is linked to product list

