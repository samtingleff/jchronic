# jchronic #

## Introduction ##
A natural language date parser in Java. Originally written by Mike Schrag as a direct port of Ruby's chronic.

I've forked it out of [WONDER](http://sourceforge.net/projects/wonder/), "an umbrella project for the WebObjects community."

## Maven ##

    <dependencies>
        <dependency>
            <groupId>com.rubiconproject.oss</groupId>
            <artifactId>jchronic</artifactId>
            <version>0.2.6</version>
        </dependency>
    </dependencies>

## Parsing of two-digit years ##

The parsing of two-digit years can be controlled with one of the `Pointer.PointerType` values `NONE`, `PAST` or
`FUTURE` like this:

```
Options opts = new Options(Pointer.PointerType.PAST);
Span result = Chronic.parse("1/2/38", options);   // Year is treated as 1938 because of PointerType.PAST.
```

The behavior is like this:

| `PointerType` | 1-19 | 20-37 | 38-68 | 69-99 | 100-137 |
|---------------|------|-------|-------|-------|---------|
| `NONE`        | 20xx | 20xx  | error | 19xx  | 20xx    |
| `PAST`        | 20xx | 19xx  | 19xx  | 19xx  | 20xx    |
| `FUTURE`      | 20xx | 20xx  | 20xx  | 19xx  | 20xx    |

Earlier versions of this library ignored the given options and always behaved like the `NONE` line in the table.

## Credits ##
This was originally written by Mike Schrag as part of the WONDER project.

## License ##
The MIT license. See LICENSE.
