# Testing OPENCART using Provengo
This directory contains the Provengo project for testing OPENCART.

## Running the tests

To run a single random test, run:
```shell 
provengo run OPENCART
```

See the [Provengo README](OPENCART/README.md) for more useful commands.

## API
See [Provengo README](OPENCART/README.md) for a short description of the API.
For a full documentation go to [https://docs.provengo.tech](https://docs.provengo.tech)

## How we tested:
1. We started by creating the [story files](OPENCART/spec/js/AddComment.story.js).
2. We then ran the following command to generate the test model:
```shell
provengo analyze -f PDF OPENCART   
```
3. We repeated steps 1-2 until we were satisfied with the generated model.
4. We added [Event definitions](OPENCART/spec/js/AddComment.EventDef.js) to define how the stories actuate the website using Selenium.
5. We used the following command to run the tests:
```shell
provengo run --show-sessions OPENCART
```
6. We repeated steps 4-5 until we were satisfied with the result.
7. We recorded a video of the running tests and added it to the report. Since more than one browser session was opened, we recorded the entire screen. The link for the video is https://easyupload.io/cch6kf.
8. We copied the generated graph of the model to a file named [model.pdf](model.pdf) inside this directory (the link should work).

### Story files
The test stories are in [Tests.story.js](OPENCART/spec/js/AddComment.story.js). See the file for a detailed description of the stories.


### Events definition files
The specification for the events are given in [EventDef.js](OPENCART/spec/js/AddComment.EventDef.js). See the file for a detailed description of the events.

