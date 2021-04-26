# Mate
A prototype compiler for a language based on https://craftinginterpreters.com/

Following is the language description

# Literals
Literals are sequence of characters that represents a fixed value

## Boolean literals
Boolean literals represent truth values

* true
* false

## Integer literals
Integer literals represents integer values

* Decimal integer: [0..9]
* Binary integer: 0b[0..1]
* Octal integer: 0o[0..7]
* Hexadecimal integer: 0x[0..9 | A..F]

Examples:
```
12345       // 12345
0b110       // 6
0o15        // 13
0xFF        // 255
```

## Decimal literals
Decimal literals represents decimal values. Explicit decimal values must be specified. No integers.

* Decimal literal: [0-9]*(\.[0-9]+)

Example:
```
123.45      // Valid
0.1234      // Valid
.1234       // Valid
1234        // Invalid
```

### String literals
String literals represents text as series of characters enclosed in double quoutes.

Example:
```
"Hello World 2020"
```
(Complete with a list of escape caracters?)

# Data Types

Following is a list of mate's datatypes

## Boolean
A boolean value represents Boolean thruth values by using the constants `true` or `false`. The mate type is `bool`

Examples:

```
bool trueValue = true
bool falseValue = false
```

## Integers
This types represents integer values:

### Unsigned integers
* `uint8` - Unsigned 8-it integer (0 to 65535)
* `uint16` - Unsigned 16-it integer (0 to 65535)
* `uint32` - Unsigned 32-it integer (0 to 4294967295)
* `uint64` - Unsigned 64-bit integer (0 to 18446744073709551615)
* `uint` - Unsigned platform dependent integer (32 or 64 bit depending on platform type)

### Signed integers
* `int8` - Signed 8-it integer (-128 to 127)
* `int16` - Signed 8-it integer (-32768 to 32767)
* `int32` - Signed 8-it integer (-2147483648 to 2147483647)
* `int64` - Signed 64-bit integer (-9223372036854775808 to 9223372036854775807)
* `int` - Signed platform dependent integer (32 or 64 bit depending on platform type)

### Decimal
This types represents decimal values:

* `decimal32` - 32 bits decimal numbers
* `decimal64` - 64 bits decimal numbers

## Strings
Strings are array of chars.

`char` - Represents only one character
`string` - Alias for `char[]` which represents an array of characters.

Examples:
```
char[] hello = "Hello world 2021"
string goodbye = "Good bye world 2020"
```

## Arrays
An indexed sequence of an specific type. For an array of size N the possible subindexes of the array goes from 0 to N - 1

The generic type of the array is specified with type plus open and close brackets. For example:

```
int32[] arrayOfInts

// These expressions are equivalent
char[] arrayOfChars
string anotherArrayOfChars

decimal32[] arrayOfDecimals
```

The function `len(<array>)` will return the size of the array. To read an specific value of the array the index must be specified between brackets, for example:

```
// Will return the first element
arrayOfIntegers[0]

// Will return the last element
arrayOfIntegers[len(arrayOfIntegers) - 1]
```

Arrays are one dimensional but can contains other arrays effectively creating a multi dimensional type.

For example:

```
// Array of int32 arrays initialized to a matrix of 4 by 4 with all consecutive numbers
int32[][] matrix = int32[4]((row, width) => {
    return int32[4]((column, height) => {
        return (row * width) + column
    })
})

printf(matrix[0][0]) // Upper left element (0)
printf(matrix[3][3]) // Lower right element (15)
```

### By function
First the size must be specified by declaring the array type plus the size between brackets. After that, a function that has the following signature must be specified:

`<array type> <function name>(int index, int length)`

This function will be called for each index of the array and must return the value that must be assigned there. The parameter `length` will always contain the length of the array.

Examples:
```
package com.fran.Initializer {
    int naturalNumbers(int index, int length) {
        return index + 1
    }

    int evenNumbers(int index, int length) {
        return (index + 1) * 2
    }
}

...

// Will create an array of the natural numbers from 1 to 10
int[] naturals = int[10](Initializer.naturalNumbers)

// Will create an array of the even numbers from 1 to 20
int[] evens = int[20](Initializer.evenNumbers)

// Will create an array of the negative numbers from 1 to 10
int[] negatives = int[10]((idx, lgt) => {
    return -1 * (idx + 1)
})
```

### By extension
First the size must be specified by declaring the array type plus the size between brackets. After that, the value for each element must be specified separated by comas.

For example:
```
decimal32[] prices = decimal32[3](0.10, 0.99, 100.32)
```

## Structs
Structs allows to define a type that is composed by multiple other different types and are grouped into a single structure.

Definition:
```
struct <fully qualified identifier> {
    <type> <identifier> [, <type> <identifier> ... ]
}
```

A fully qualified name is the name of the namespace followed by a dot and the name of the struct.

Namespaces are used to organize code and avoid naming conflicts of different elements. Namespaces are arranged in a hierachical system similar to directories and each part of the namespace is also separated by dots.

For example:
```
struct ar.com.myfoodpage.vegetables.Potato {
  ...
}

struct ar.com.myfoodpage.vegetables.Onion {
  ...
}

struct ar.com.myfoodpage.fruits.Orange {
  ...
}

struct ar.com.myfoodpage.fruits.Apple {
  ...
}
```

In the example above `Potato` and `Onion` are members of the `ar.com.myfoodpage.vegetables` namespace, while `Orange` and `Apple` belong to `ar.com.myfoodpage.fruits`, also and also both namespaces also belong to `ar.com.myfoodpage` namespace.

Initialization is done by specifying the struct type and the values for each field between brackets. Each field must be separated by coma, and it has to be specified as a pair of identifier and value separated by colon.

For example:
```
struct ar.org.mate.Person {
    string name,
    string surname,
    string alias,
    int age
}

Person pilot = Person {
    name: "Pete",
    surname: "Mitchell",
    alias: "Maverick",
    age: 28
}

Person rio = Person {
    name: "Nick",
    surname: "Bradshaw",
    alias: "Goose",
    age: 34
}
```

Struct fields can be accessed using the dot operator after the variable identifier.
Following the example above:

```
printf(pilot.alias + ": " + pilot.name + " " + pilot.surname) // Maverick: Pete Mitchell

printf(rio.alias + ": " + rio.name + " " + rio.surname) // Goose: Nick Bradshaw
```

### Access modifiers
Access modifiers allows to control if the struct can be used from other namespaces. By default structs are visible only to their namespace and it's childs.

The statement `public for <namespace>` before the struct declaration allows to specify that a struct can be accessed from a different namespace or package, `*` can be used as a wildcard.

In constrast, the `private for <namespace>` statement allows to restrict the usage from specific namespaces.

Multiple access modifiers can be specified for the same struct.

Example:
```
public for ar.org.mate.*
private for ar.org.mate.test.integration.*
struct ar.org.mate.test.SomeStruct {
    int value
}
```

In the above example, the struct by default is accesible by all members of `ar.org.mate.test`. Its also been made accesible to all `ar.org.mate` member, and denied access to all `ar.org.mate.test.integration` members.

### Member access modifiers
Member access modifiers allows to control who can read or write each struct member. By default, all members can be read or written by any package that has access to the struct.

`readonly for <package>` can be used for specifying packages that can only read, not write an specific member.

`writable for <package>` is the opossite to the above, and is used to specify packages that can write the value

`writeonly for <package>` can be used for specifying packages that can only read, not write an specific member.

`readable for <package>` is the opossite to the above, and is used to specify packages that can read the value

Example:

```
public for *
struct ar.org.mate.test.SomeStruct {
    readonly for *,
    writable for ar.org.mate.test.* {
        int value1,
        int value2
    }
}
```

In the above example, `SomeStruct.value1` and `SomeStruct.value2` can be read from any package that has access to `SomeStruct`, but it can only be written by elemnts .

### Composition
You can compose an struct from others, meaning that any member available on the component struct will be available in the composed struct as well. 
Composition is specified by using the `import` keyword inside the struct.

For example:
```
struct ar.org.mate.Character {
    string name,
    int level
}

struct ar.org.mate.Player {
    import ar.org.mate.Character

    int remainingLives
}
```

`Player` contains the members `name`, `level` and `remainingLives` and they are accesible with the dot operator as described above.
A composed member can be aliased by using the `:` and specifying the component struct `.` the member.

For example:

```
struct ar.org.mate.FpsPlayer : ar.org.mate.Player {
    int remainingRespawns : ar.org.mate.Player.remainingLives
}
```

Of course, the data type of both members must match.

In case that two or more component structs contains members with the same name, they all must be aliased or the program will fail to compile.

### Casting structs

Upcasts can be implicit, for example:
```
Character character = Player {
    name: "Joe Higashi",
    level: 5,
    remainingLives: 2
}
```

Downcasts must be explicitly declared by using the `as` operator and the target type. The following is valid:
```
Player player = character as Player
```

Obviously to be able to downcast a value, the struct must be composed by desired type. This is invalid:
```
Character npc = Character {
    name: "Andy Bogart",
    level: 9
}

Player andy = npc as Player // Will throw exception
```

They operator `is` can be used to check if a specific value is a struct of a given type or composed by that struct:
```
npc is Character        // true
npc is Player           // false
player is Character     // true
player is Player        // true
```

Finally, `into` keyword can be used after an `is` statement and before a block to automatically create a variable with the casted value if the `is` result is `true`. For example:

```
if (character is Player into player2) {
    printf(player2.name) // Joe Higashi
}
```

This is a shorthand for:

```
if (character is Player) {
    Player player2 = character as Player
    
    printf(player2.name) // Joe Higashi
}
```

### Empty type

Appending a question mark to any types refers to the empty type. The empty type is always a component of the specified struct type, it has no members and has the same access modifiers of the actual struct.

This value allows to specify types that can hold empty values, but if the value is not empty, a downcast is required to be able to access the type members.

This allows to avoid in compile time any potential issues from trying to access the members of empty values (which in other languages will result in a null pointer exception)

For example:

```
struct ar.org.mate.Character {
    string name,
    int level
}

Character? character;              // Empty types don't require initialization

printf(character is Character)     // false
printf(character is Character?)    // true

character = Character {
    name: "Liu Kang",
    level: 12
}

printf(character is Character?)    // true

printf(character.name)             // Exception: character does not have member "name"

if (character is Character into myPlayer) {
    printf(myPlayer.name)          // Prints Liu Kang
}
```

Notice how `Character?` composes `Character` but both are diferent types.

# Use clause

The keyword `use` can be used inside a file to import an identifier to that file thus avoiding to fully qualify references to that element:

```
struct ar.org.mate.Address {
    string street
    int number
    string zipCode
}
```

Then on a different file:

```
use ar.org.mate.Address

struct com.test.Person {
    string fullName,
    Address address
}
```

An `as` clause can be specified after `use` to assign an alias to the imported element. This allows two or more elements with the same name but from different namespaces to be used in the same file.

```
struct com.test.email.Address {
    string user,
    string domain
}
```

Then:

```
use ar.org.mate.Address as StreetAddress
use com.test.email.Address as EmailAddress

struct ar.test.Company {
    string name,
    StreetAddress address,
    EmailAddress email
}
```

# Services

As structs groups multiple types together, a service allows to group related functions.

It's declaration is similar to structs:

```
service <fully qualified name> {
    <Function Declaration 1>[,
    ... Function Declaration N]
}
```

Each function declaration is:
```
<return type> <identifier>([<Type> <param1 identifier> [, ... param N]]) {
    <function implementation>
}
```

For example:

```
service ar.org.mate.Math {
    int sum(int a, int b) {
        return a + b
    }

    int diff(int a, int b) {
        return a - b
    }
}
```

Similary to struct members, the functions of the service can be invoked by using the `.` operator and specifying a value for each parameter:

```
use ar.org.mate.Math

int four = Math.sum(3, 1)
int one = Math.diff(four, 3)
```

## Access modifiers
Access modifiers can be specified to control if the methods can be used from other namespaces. By default service functions are visible only to their namespace and it's childs.

The access modifiers `public` and `private` can be applied to the service itself or to an specific function the second overriding the first.

For example:
```
public for * 
service com.example.Printer {
    void printLine(string message) {
        printf(addCarriageReturn(message))
    }

    private for * {
        string addCarriageReturn(string message) {
            return message + "\n"
        }
    }
}
```

### Service Composition
A service can use method of another service but first it has to import it:

```
service com.example.Device {
    import Printer

    void showMessage(string message) {
        Printer.printLine(message)
    }
}
```

### Interfaces
Interfaces are a named collection of method signatures that allows to declare the expected behavior of a service.

Its declaration is similar to services, although no method implementation needs to be provided:

```
inteface <fully qualified name> {
    <Function signature 1>[,
    ... Function signature N]
}
```

Each function signature is:
```
<return type> <identifier>([<Type> <param1 identifier> [, ... param N]])
```

Interfaces can then be imported and used by a service, but an specific implementation
for the interface must be provided by the user or a default implementation can be selected.

The sintaxis for importing an interface goes inside the service declaration an is:

import <service name> [as Service Alias] [{selectable specification}] [default <service name>]

The selectable specification allows or restricts who can select different implementations for the interface and is:

selectable by <namespace selector>[, <namespace selector>]
[nonselectable by <namespace selector>[, <namsepace selector>]]

If an interface import does not specify a default service implementation, and no service implementation is provided the compiler will throw an error

Finally, there is no need to specify that a service implements an interface, when the service is specified the compiler checks that it contains all the required methods.

Interface usage example:

```
interface ar.com.fran.Weapon {
    void attack()
}

service ar.com.fran.BareHand {
    void attack() {
        print "Punch!"
    }
}

service ar.com.fran.Sword {
    void attack() {
        print "Slash"
    }
}

service ar.com.fran.Flower {
    void smell() {
        print "Sniff"
    }
}

package ar.com.fran.Shuriken {
    void attack() {
        print "Throw"
    }
}

use ar.com.fran.Weapon
use ar.com.fran.Armor

service ar.com.fran.NinjaDriver {
    import Weapon as LeftHand selectable by ar.com.fran.* \ 
        default ar.com.fran.Sword

    import Weapon as RightHand selectable by ar.com.fran.* \
        default ar.com.fran.BareHand

    void hit() {
        leftHand.attack()
        rightHand.attack()
    }
}

use ar.com.fran.NinjaDriver
use ar.com.fran.Sword
use ar.com.fran.Shuriken
use ar.com.fran.Flower

service ar.com.fran.Main {
    // No services specified uses default (Sword, BareHand)
    import NinjaDriver as Ninja1

    // Specified services that meet the interface
    import NinjaDriver {
        LeftHand: Shuriken
        RightHand: Sword
    } as Ninja2

    // Throws compilation error as Flower does not implement Weapon interface
    import NinjaDriver {
        LeftHand: Flower
    } as Ninja3

    void run() {
        Ninja1.hit() // Slash Punch!
        Ninja2.hit() // Punch! Throw!
    }
}

service ar.com.example.Main {
    // Works
    import NinjaDriver as Ninja1

    // Error as this service cannot override the default implementations of the interface
    import NinjaDriver {
        LeftHand: Shuriken
        RightHand: Sword
    } as Ninja2    

    ...
}
```

# Keywords

´´´

´´´

