PROJECT_ZERO
    A customizable Pokedex Application that allows the user to showcase basic CRUD operations in a Scala program.
 
Tech Stack
    Visual Studio Code
    Scala 2.13 + SBT
    MongoDB
    Git / Github
 
Features
    A Pokedex that can display 800+ different pokemon and cool statistics
    The ability to create your own custom pokemon and add it to the pokedex
    The ability to update or delete existing pokemon in the pokedex
 
    Future Improvements:
        Create more queries that implement aggregations of the data
        Searching pokemon by type and best stats
 
Project Setup
    Clone this repository to the desired location on your machine
    To use and run this application, the user must have Mongo installed and should be connected to a Mongo Database Server.
    Then the user must have or create the following database and collection:
    Database = ‘ScalaProjectsDB’
    Collection = ‘pokedexData’

    Go to this path and import the JSON File into your mongoDB collection
    C:\Program Files\MongoDB\Tools\100\bin>mongoimport --db ScalaProjectsDB --collection pokedexData --jsonArray --file C:\Users\18284\Desktop\ScalaProjects\pokedex\src\main\scala\example\pokedex.json
    
    In Mongo-Shell, delete the following fields in your collection:
        - name.japanese
        - name.chinese
        - name.french
        
        Command = db.pokedexData.update({}, {$unset: {"name.chinese": 1}}, {multi: true})

Usage
    Open the project in Visual Studio Code or the preferred IDE
    Run the program and you will be presented with a menu of options
    Now you can enjoy the application and perform some CRUD operations
 
Contributors
    Matthew Bowman