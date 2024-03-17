## Overview

The Ambition Scripting API Library is a template-library designed specifically for integration with Ambition Client.
It serves as an API interface for generating scripting bindings and json documentation, facilitating scripting capabilities.

## Purpose

This library is intended to be used as a resource for learning and experimentation with scripting in general, you may use this in your own project as-is (pre existing binding-templates).
However this is not recommended and we would prefer you make it unique to your own api. Simply use the pre existing bindings as a reference to make your own!

## Key Features

By providing access to these key features, developers can create dynamic, and modern scripting systems and auto-updated documentation.
- **Bindings Generation**: Automatically generate script bindings to supply to the scripting engine for evaluation.
- **Json Generation**: Generate detailed json documentation for the scripting API, simplifying the process of understanding available functions, parameters, and return types.
- **Structure** The api structure is kept as user-friendly and allows making user-friendly scripting apis, much more accomplishable.

## Usage

### Setup

- **1**: Clone project.
- **2**: Open in any common ide that supports maven.
- **3**: Modify and or add new binding templates and register them.
- **4**: Build project.

### Implementation

- **1**: Add built jar to your build system.
- **2**: Create implementations of your binding templates.
- **3**: Create a new script engine, supply it to the controller, and generate the bindings.
- **4**: Evaluate your script to the script engine with the generated bindings.

### Command Line

The following commands are currently accessible.
- **--genDocs**: Generates the json object and prints it to console.

## Contributing

Contributions to the Scripting API Library are welcome! This project is open-source and welcomes pull requests for improvements, bug fixes, and new features.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For any inquiries or support related to this project, please contact me on discord, thank you.
