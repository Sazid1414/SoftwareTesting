#!/bin/bash

# PiTest Mutation Testing Script
# This script runs mutation testing using PiTest on the Java project

echo "Starting PiTest Mutation Testing..."
echo "======================================"

# Clean and compile the project first
echo "1. Cleaning and compiling project..."
mvn clean compile test-compile

if [ $? -ne 0 ]; then
    echo "❌ Build failed. Please fix compilation errors before running mutation tests."
    exit 1
fi

# Run tests to ensure they pass
echo "2. Running unit tests..."
mvn test

if [ $? -ne 0 ]; then
    echo "❌ Tests failed. Please fix failing tests before running mutation tests."
    exit 1
fi

# Run mutation testing
echo "3. Running PiTest mutation analysis..."
mvn org.pitest:pitest-maven:mutationCoverage

if [ $? -eq 0 ]; then
    echo "✅ Mutation testing completed successfully!"
    echo ""
    echo "📊 Results Summary:"
    echo "==================="
    
    # Extract key metrics from the last run
    REPORT_DIR="target/pit-reports"
    if [ -f "$REPORT_DIR/index.html" ]; then
        echo "📁 HTML Report: $REPORT_DIR/index.html"
        echo "📁 XML Report: $REPORT_DIR/mutations.xml"
        echo ""
        echo "💡 Open $REPORT_DIR/index.html in your browser to view the detailed mutation testing report."
    fi
else
    echo "❌ Mutation testing failed. Check the output above for errors."
    exit 1
fi
