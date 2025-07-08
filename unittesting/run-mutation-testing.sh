#!/bin/bash

# PIT Mutation Testing Script
# This script runs mutation testing on the project

echo "Starting PIT Mutation Testing..."
echo "================================="

# Clean and compile the project first
echo "1. Cleaning and compiling project..."
mvn clean compile test-compile

# Run mutation testing
echo "2. Running mutation testing..."
mvn org.pitest:pitest-maven:mutationCoverage

# Check if mutation testing was successful
if [ $? -eq 0 ]; then
    echo ""
    echo "Mutation testing completed successfully!"
    echo "========================================"
    echo ""
    echo "Reports generated:"
    echo "- HTML Report: target/pit-reports/index.html"
    echo "- XML Report: target/pit-reports/mutations.xml"
    echo ""
    echo "To view the HTML report, open: target/pit-reports/index.html in your browser"
    echo ""
    echo "Mutation Score Summary:"
    echo "----------------------"
    if [ -f "target/pit-reports/mutations.xml" ]; then
        # Extract mutation score from XML report (if available)
        echo "Check the HTML report for detailed mutation scores and coverage."
    fi
else
    echo ""
    echo "Mutation testing failed! Please check the output above for errors."
    exit 1
fi
