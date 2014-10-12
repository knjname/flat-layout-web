# flat-layout-web

A Leiningen template for creating flat layout web-project.

[![Clojars Project](http://clojars.org/flat-layout-web/lein-template/latest-version.svg)](http://clojars.org/flat-layout-web/lein-template)

## Usage

To generate a flat layout leiningen project, just type this.

```
$> lein new flat-layout-web <project-name>
```

You'll get a project having following layout.

```
<project-name>/
  .gitignore
  project.clj
  <project-name>.clj
  Procfile (for Heroku)
  system.properties (for Heroku)
```

This is convenience if you want to write some small Gist snippet having external dependencies which should have been written in its project.clj. After writing your snippet, you can ```git push``` to Gist directly.

(Gist doesn't allow us to upload a project including sub directories.)

## Run a web-server instance from REPL

1. Start a REPL session.
2. Invoke ```start-server``` function defined in ```<project-name>.clj```.
```clojure
(project-name/start-server)
```
3. To stop the server, call ```stop-server``` function defined in ```<project-name>.clj```.
```clojure
(project-name/stop-server)
```

## Run a web-server instance with Leiningen command

This is the command.

```clojure
$> lein with-profile production trampoline ring server
```

The same command is written in Procfile. You can deploy the created project to Heroku.
