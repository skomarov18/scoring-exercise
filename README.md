
## Scoring Application
#  Building Scoring Application
       gradle bootJar

# Running Scoring Application 
      * java -jar build/libs/scoring-exercise-0.0.1-SNAPSHOT.jar  --file.name=sample.txt   --spring.profiles.active=cli

      * java -jar build/libs/scoring-exercise-0.0.1-SNAPSHOT.jar  --file.name="OCC Take Home Coding names.txt"   --spring.profiles.active=cli

# Troubleshooting tips

     * Check that the file you scoring exists and you point to correct location
  
     * Validate the command line arguments are correct (see #Running )
  
     * See scoring-app.log for additional debug and error information
  
  
