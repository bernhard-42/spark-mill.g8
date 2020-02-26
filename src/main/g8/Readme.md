# Usage

- Compile

    ```bash
    mill $name$.compile
    ```

- Run

    ```bash
    mill $name$.standalone.run 'local[*]'
    ```

- Run assembly locally

    ```bash
    mill $name$.standalone.assembly
    java -cp out/$name$/standalone/assembly/dest/out.jar $package$.$name$
    ```

- Assemly with all libraries except Spark and dependencies

    ```bash
    mill $name$.assembly
    ```
