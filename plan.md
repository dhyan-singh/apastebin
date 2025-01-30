# Something

## Clojure specific rules
🥵1. **Consistent naming** is important
    - `r` for request


## FIXMEs
1. Give proper error when error occurs, currently it just prints stuff fix all those `try/catch` errors.
2. Make response uniform sometimes it returns `text/plain` and sometimes `application/json`
3. 

## Plan

TOODS

- sanitise input sql 🥵
- home page should show SwaggerUI about api

- only implementing `reader` and `writer` (analytics later)
- define `sql` schema & `json` input/output we will be talking

### Done

- routing is done
  - at paste (/api/v1/paste)
    - post request is routing to writer
    - get request is routing to reader

## Technology used

1. Clojure
    1. Ring
2. SQLite

## Ideas Exploration

The [guide](https://github.com/donnemartin/system-design-primer/blob/master/solutions/system_design/pastebin/README.md) that I'm following suggests everything should be a microservice of itself, reader should be a microservice, writer should be a microservice. Argument for which I just don't understand. In this very specific simple example it makes zero sense to seperate them. they are both such **simple** and **similar** functions, why seperate them?