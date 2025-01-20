# Project Overview

This project involves testing the functionalities of **Calibre-Web**, an open-source e-book library manager. The primary features tested include:

- Search Functionality
- Metadata Editing
- Reading in Browser
- Database Integration

The tests ensure the application meets its functional and usability requirements while maintaining reliability and performance.

### Objectives:

- Validate the functionality and usability of critical features.
- Ensure data integrity and consistency through database operations.
- Identify and log potential defects for resolution.

### Scope:

The following functionalities are tested:

- Search Functionality: Covers full/partial title searches, special characters, non-English titles, case sensitivity, and author-based searches.
- Metadata Editing: Editable fields include Title, Author, Tags, Series, Language, and Rating.
- Reading in Browser: Verifies the ability to read books directly in the browser.
- Database Integration: Includes database connection, book ID retrieval, and metadata validation.

### Test Completion Criteria:

- Execution of 100% of the defined test cases.
- Resolution of all critical and high-severity defects.
- At least 90% of total test cases must pass.
- Full coverage of functional requirements.

### Metrics:

- Test Execution Metrics: Number of test cases run, passed, or failed.
- Defect Metrics: Total defects found, categorized by severity.
- Requirements Coverage: Percentage of covered requirements.

## Environment Setup

### Software Requirements:

- Supported Browsers: Google Chrome, Microsoft Edge
- Maven for Java build automation
- Python 3.7+ 
- Calibre Database API
- A local environment running Windows with Calibre-Web installed
- A sample eBook library with at least one book

### Installation Steps:

1. Clone the Repository:
git clone https://github.com/ahedkhatib/TestAutomationProject

2. Set Up Dependencies:
- Install Maven for Java project management:
sudo apt install maven # For Linux
For Windows: Download the Maven binary from [Maven Download Page](https://maven.apache.org/download.cgi), extract it, and add the `bin` directory to the PATH environment variable.
- Install Python 3.7 or later.
- Install the Calibre Database API.
3. Prepare Test Data:
   - Ensure the Calibre library contains sample books with varying metadata (e.g., missing fields, special characters, and different languages).
4. Configure the Environment:
   - Set the `GRID_URL` environment variable if using a Selenium Grid.
   - Configure browser-specific drivers (e.g., ChromeDriver).

## Test Execution Steps

### Automated Tests:

1. Navigate to the Java test directory:
cd src/test/java
2. Run tests using Maven:
mvn test

### Retesting and Regression Testing:

- Retesting occurs after defect fixes to ensure issues are resolved.
- Regression testing is triggered after changes to ensure existing functionality remains unaffected.

## Test Case Coverage

### Test Design:

1. Search Tests:
- Validate search results for full titles, partial titles, and special characters.
- Test case sensitivity and author-based searches.
2. Metadata Editing Tests:
- Verify modifications for Title, Author, Tags, Series, Language, and Rating fields.
- Check handling of missing metadata fields.
3. Read in Browser Tests:
- Ensure books open in the browser for reading.
4. Database Tests:
- Test database connection and validate book metadata retrieval.

### Completion Criteria:

- Execution of all high-priority test cases.
- Defect limits met: No unresolved critical defects.

