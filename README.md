# Assignment 3: Software Quality Engineering
This is a repository for assignment 3 of the Software Quality Engineering course at the [Ben-Gurion University](https://in.bgu.ac.il/), Israel.

## Assignment Description
In this assignment, we tested an open-source software called OpenCart(https://www.opencart.com/).

OpenCart is a free shopping cart system. OpenCart is an open source PHP-based online e-commerce solution.
## Installation
Instructions on how to install the software and prepare the testing environment are in this video:
https://www.youtube.com/watch?v=DrEYgPK1NUw&ab_channel=SurfsideMedia

## What we tested

We tested the site module that allows for commenting on products and deleting products. We chose to test the following user stories: 

*User story:* A user is writing a comment to product.

*Preconditions:* There is the product in the system to comment on.

*Expected outcome:* The comment is added to the product page.

*User story:* The admin deletes from the product.

*Preconditions:* There is the product in the system to delete.

*Expected outcome:* The product is deleted


## How we tested
We used two different testing methods:
1. [Cucumber](https://cucumber.io/), a BDD testing framework.
2. [Provengo](https://provengo.tech/), a story-based testing framework.

Each of the testing methods is elaborated in its own directory. 
