Compile

    mill $name$.compile

Assemly with all libraries

    mill $name$.assembly

Assemly with all libraries except Spark and dependencies

    mill $name$.remote.assembly

Run

    mill $name$.run